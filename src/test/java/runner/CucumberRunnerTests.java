package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = {"src/test/resources/apiFullFlow_feature"}, plugin = {}, glue = "api.fullFlow.definitions")
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {
}
