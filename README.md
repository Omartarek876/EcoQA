# EcoQA

A ready-to-use E2E test automation framework for e-commerce websites, built with
Selenium 4, TestNG and Allure reporting, with a plug-and-play GitHub Actions CI.
Drop in your store's URL, fill the page locators, and the common flows every
e-commerce site needs are ready to run: login, product search, add to cart,
cart management and checkout.

## Tech stack

- Java 21, Maven
- Selenium WebDriver 4.52 (Chrome, Firefox, Edge)
- TestNG
- Allure (allure-testng 3.0) for HTML reports
- GitHub Actions CI

## What's inside

### Ready e-commerce page flows - `src/main/java/pages/`

Each page class carries the methods any e-commerce site needs, written on top of
the framework utilities with Allure step reporting. The locators are intentionally
empty: fill them for your site and the flows work as-is.

| Class | Ready flows |
| --- | --- |
| `LoginPage` | enter credentials, submit, full `login(email, password)` flow, login error validation |
| `RegisterPage` | registration form, full `register(name, email, password)` flow, registration error validation |
| `ForgotPasswordPage` | full `requestPasswordReset(email)` flow, reset confirmation validation |
| `HomePage` | open the store from the configured `baseUrl`, product search, navigation to login/products/cart |
| `ProductsPage` | list and read products, open product details, add product to cart |
| `SearchResultsPage` | read and count results, sort results, open product details, add to cart from results |
| `ProductDetailsPage` | product name/price, quantity and size selection, add to cart, add to wishlist |
| `CartPage` | read cart items, update quantities, remove items, cart total, proceed to checkout |
| `CheckoutPage` | shipping address, payment method selection, place order, order confirmation validation |
| `PaymentPage` | card details, confirm payment, payment error validation |
| `OrderConfirmationPage` | confirmation message, order number, order confirmation validation, continue shopping |
| `OrdersPage` | order history list, numbers and statuses, order details, reorder items |
| `AccountPage` | profile read/update, add address, navigation to orders and wishlist, update validation |
| `WishlistPage` | wishlist items, move to cart, remove from wishlist, empty check |
| `ContactPage` | contact form, full `sendMessage(...)` flow, success validation |

### Framework utilities - `src/main/java/utils/`

| Class | Purpose |
| --- | --- |
| `DriverFactory` | Thread-local WebDriver management; browser, headless and window settings come from configuration |
| `PropertiesReader` | Loads `environment.properties` and `execution.properties` into system properties (command-line `-D` values win) |
| `ElementActions` | Safe element interactions (click, type, select) with explicit waits |
| `WaitUtils` | Explicit-wait helpers built on the configured timeout |
| `BrowserUtils` | Navigation and browser-level actions |
| `AlertActions` | Alert accept/dismiss/read helpers |
| `Validations` | Assertion helpers with Allure step reporting |
| `JsonFileManager` | Reads test data from JSON files with JSONPath queries |
| `TestNgListener` | TestNG listener: suite/test lifecycle hooks and Allure report generation after the suite |
| `AllureReportGenerator` | Runs `mvn allure:report` and opens the generated HTML report locally |

### Configuration - `src/main/resources/`

- `execution.properties` - run defaults: browser, headless, window maximize, wait timeout
- `environment.properties` - set `baseUrl` to your store under test

### Per-page test data - `src/test/resources/<Page>Page_Data/`

Every page template ships with its own data folder holding three JSON files:

- `<Page>Messages.json` - expected assertion messages, kept in their own file
  for readability
- `<Page>Data_EN.json` - English test data
- `<Page>Data_AR.json` - Arabic data, for sites that have a localized version

Files ship with `TODO: fill per site` placeholders grouped by field and scenario
(valid, empty, invalid, ...). The English and Arabic files always share the same
keys, and a smoke test enforces that.

Tests consume the files through `JsonFileManager`, the same pattern used in
production suites: declare the files as fields, load them in `@BeforeClass`
(the data language is selected with the `language` system property,
`-Dlanguage=AR`, defaulting to EN), and read values inside the fluent page
calls with JSONPath:

```java
@BeforeClass
public void setup() {
    loginMessages = new JsonFileManager("src/test/resources/LoginPage_Data/LoginMessages.json");
    loginData = new JsonFileManager("src/test/resources/LoginPage_Data/LoginData_"
            + System.getProperty("language", "EN") + ".json");
}

new LoginPage()
        .enterEmail(loginData.getTestData("$.credentials.validEmail"))
        .enterPassword(loginData.getTestData("$.credentials.validPassword"))
        .clickLoginButton();
```

A ready-to-copy class showing the full pattern lives at
`src/test/java/templates/PageTestTemplate.java`.

### Smoke tests - `src/test/java/smoke/`

Verify the framework itself: configuration loads correctly, `DriverFactory`
starts a headless session, the core UI utilities work end to end against a local
page (navigation, typing, clicking, waits, validations, form controls, alerts),
`JsonFileManager` reads test data, every e-commerce page template constructs
cleanly with its empty locators, and every page's data folder is complete and
consistent across languages. No external site needed.

## Use it on your store

1. Clone this repository.
2. Set `baseUrl` in `src/main/resources/environment.properties` to your store.
3. Fill the page locators in `src/main/java/pages/` and the page data files in
   `src/test/resources/<Page>Page_Data/` (each is marked `TODO: fill per site`).
4. Add your tests under `src/test/java/` using the ready page flows.
5. Run with `mvn clean test`. Override configuration from the command line when needed,
   for example `mvn clean test -Dbrowser=firefox -Dheadless=false`.
6. Generate the report with `mvn allure:report`; it lands in `target/allure-report/`.

## CI

`.github/workflows/CI.yml` runs the suite on every push to `main`, pull request
and manual trigger: it sets up Java 21, runs `mvn clean test`, generates the Allure
report when results exist, uploads the test results and report as build artifacts
(kept for 14 days), and publishes the report to GitHub Pages (see below). No
project-specific values are hardcoded, so the workflow works unchanged for any
store that adopts this framework.

## Allure reporting

Reports are generated automatically after the suite runs. No reporting code is
needed in individual tests:

1. **During the run**, the Allure TestNG integration writes test results to
   `target/allure-results/`. The framework listener attaches a screenshot when
   a test fails and a browser session is available.
2. **After the suite**, `TestNgListener` calls `AllureReportGenerator`, which
   runs `mvn allure:report` and writes `target/allure-report/index.html`.
   On a desktop with browser support, it also tries to open the report.
   Maven must be available on `PATH` for this automatic step.
3. **In CI**, the workflow generates the report again and publishes it through
   two channels, so you pick whichever you prefer:

   **Option 1 - live GitHub Pages link.** After every run on `main`, the report
   is published to the `gh-pages` branch and served at
   <https://omartarek876.github.io/EcoQA/>, open it from any device, no downloads.
   One-time setup for a fresh copy of this framework: run the CI once (it creates
   the `gh-pages` branch), then in the repository Settings -> Pages choose
   "Deploy from a branch" with `gh-pages` / `(root)`.

   **Option 2 - downloadable artifact.** Every run uploads `ui-test-results` as a
   build artifact (kept for 14 days). Open a completed Actions run, download it,
   unzip it, and open `allure-report/index.html`. The report is configured as a
   single HTML file and includes its data.

To regenerate a report locally, run `mvn allure:report`. To serve the latest
results in your browser, run `mvn allure:serve`.

What the report looks like for this framework's smoke suite:

![Allure report overview](docs/screenshots/allure-overview.png)

![Allure report suites view](docs/screenshots/allure-suites.png)
