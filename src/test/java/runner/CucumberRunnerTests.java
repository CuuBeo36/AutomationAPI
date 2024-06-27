package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = {"src/test/resources/jsonplace_feature"}, plugin = {}, glue = "api.jsonplaceholder.definitions")
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {
}
