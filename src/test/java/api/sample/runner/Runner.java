package api.sample.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = {"src/test/resources/api_features/Reqres_api_test.feature"}, plugin = {}, glue = "api.sample.definitions")
public class Runner  extends AbstractTestNGCucumberTests {
}

