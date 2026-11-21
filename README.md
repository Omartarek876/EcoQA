# Test Automation Framework

A reusable Selenium + TestNG framework in Java, with Allure reporting and a ready
GitHub Actions pipeline. Extracted as a project-neutral base: clone it, set your
application URL, add your pages and tests, and run.

## Tech stack

- Java 21, Maven
- Selenium WebDriver 4.52 (Chrome, Firefox, Edge)
- TestNG
- Allure (allure-testng 3.0) for HTML reports
- GitHub Actions CI

## What's inside

`src/main/java/utils/`

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

`src/main/resources/`

- `execution.properties` - run defaults: browser, headless, window maximize, wait timeout
- `environment.properties` - set `baseUrl` to your application under test

`src/test/java/smoke/` - two smoke tests that verify the framework itself:
configuration loads correctly and `DriverFactory` starts a headless session.

## Use it in a new project

1. Clone or copy this repository.
2. Set `baseUrl` in `src/main/resources/environment.properties`.
3. Add your page objects under `src/main/java/pages/` and your tests under `src/test/java/`.
4. Run with `mvn clean test`. Override configuration from the command line when needed,
   for example `mvn clean test -Dbrowser=firefox -Dheadless=false`.
5. Generate the report with `mvn allure:report`; it lands in `target/allure-report/`.

## CI

`.github/workflows/CI.yml` runs the suite on every push, pull request and manual
trigger: it sets up Java 21, runs `mvn clean test`, generates the Allure report when
results exist, and uploads the test results and report as build artifacts (kept for
14 days). No project-specific values are hardcoded, so the workflow works unchanged
in any project that adopts this framework.
