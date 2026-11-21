package smoke;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JsonFileManager;

import java.io.File;
import java.io.FileReader;
import java.util.Set;

/**
 * Verifies the per-page test-data structure: every page template has a
 * <Page>Page_Data folder holding <Page>Messages.json, <Page>Data_EN.json and
 * <Page>Data_AR.json, all parse through JsonFileManager, and the EN/AR data
 * files expose the same key structure.
 */
public class PageDataTemplatesSmokeTest {

    private static final String RESOURCES = "src/test/resources/";

    private static final String[] PAGES = {
            "Account", "Cart", "Checkout", "Contact", "ForgotPassword",
            "Home", "Login", "OrderConfirmation", "Orders", "Payment",
            "ProductDetails", "Products", "Register", "SearchResults", "Wishlist"
    };

    @Test(description = "Every page has a data folder with a messages file and EN/AR data files, all loadable")
    public void everyPageHasLoadableDataFiles() {
        for (String page : PAGES) {
            String folder = RESOURCES + page + "Page_Data/";
            for (String file : new String[]{
                    page + "Messages.json", page + "Data_EN.json", page + "Data_AR.json"}) {
                File dataFile = new File(folder + file);
                Assert.assertTrue(dataFile.isFile(),
                        "Missing test-data file: " + folder + file);
                // Parses and Asserts.fail()s inside JsonFileManager when invalid
                new JsonFileManager(folder + file);
            }
        }
    }

    @Test(description = "EN and AR data files of every page expose identical key structures")
    public void enAndArDataFilesShareKeyStructure() throws Exception {
        JSONParser parser = new JSONParser();
        for (String page : PAGES) {
            String folder = RESOURCES + page + "Page_Data/";
            JSONObject en = (JSONObject) parser.parse(new FileReader(folder + page + "Data_EN.json"));
            JSONObject ar = (JSONObject) parser.parse(new FileReader(folder + page + "Data_AR.json"));
            Assert.assertEquals(ar.keySet(), en.keySet(),
                    page + " data files: EN/AR top-level groups differ");
            for (Object group : en.keySet()) {
                Set<?> enKeys = ((JSONObject) en.get(group)).keySet();
                Set<?> arKeys = ((JSONObject) ar.get(group)).keySet();
                Assert.assertEquals(arKeys, enKeys,
                        page + " data files: EN/AR keys differ inside group " + group);
            }
        }
    }

    @Test(description = "Placeholder data reads through JsonFileManager with JSONPath like production suites")
    public void dataReadsByJsonPath() {
        JsonFileManager loginData = new JsonFileManager(
                RESOURCES + "LoginPage_Data/LoginData_EN.json");
        Assert.assertEquals(loginData.getTestData("$.credentials.validEmail"), "TODO: fill per site",
                "placeholder value should be readable through the same JSONPath pattern suites use");
    }
}
