package smoke;

import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JsonFileManager;

/** Proves JSON test-data loading through JsonFileManager works after the transfer. */
public class JsonFileManagerSmokeTest {

    @Test(description = "JsonFileManager reads values from a JSON test-data file via JSONPath")
    public void readsTestDataByJsonPath() {
        JsonFileManager testData = new JsonFileManager("src/test/resources/smoke/test-data.json");

        Assert.assertEquals(testData.getTestData("$.smoke.userName"), "Framework Smoke User",
                "userName should be read from the JSON file");
        Assert.assertEquals(testData.getTestData("$.smoke.expectedGreeting"), "Hello, Framework Smoke User!",
                "expectedGreeting should be read from the JSON file");
        Assert.assertEquals(testData.getTestData("$.smoke.alertMessage"), "Smoke alert",
                "alertMessage should be read from the JSON file");
    }
}
