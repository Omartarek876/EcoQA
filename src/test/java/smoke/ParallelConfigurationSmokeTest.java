package smoke;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import utils.TestNgListener;

import java.util.Arrays;
import java.util.Collections;

public class ParallelConfigurationSmokeTest {
    @Test(description = "Report naming preserves class-level parallel scheduling")
    public void listenerPreservesParallelClasses() {
        XmlSuite suite = new XmlSuite();
        suite.setParallel(XmlSuite.ParallelMode.CLASSES);
        suite.setThreadCount(4);
        XmlTest test = new XmlTest(suite);
        test.setName("Parallel smoke");
        test.setXmlClasses(Arrays.asList(
                new XmlClass(FrameworkConfigSmokeTest.class),
                new XmlClass(JsonFileManagerSmokeTest.class)));

        new TestNgListener().alter(Collections.singletonList(suite));

        Assert.assertEquals(suite.getTests().size(), 1,
                "Classes must stay together so TestNG can schedule them concurrently");
        Assert.assertEquals(suite.getTests().get(0).getClasses().size(), 2);
        Assert.assertEquals(suite.getParallel(), XmlSuite.ParallelMode.CLASSES);
        Assert.assertEquals(suite.getThreadCount(), 4);
    }
}
