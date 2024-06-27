package api.fullFlow.definitions;

import api.fullFlow.pageObject.LoginPage;
import api.fullFlow.pojo.User;
import com.automation.core.utils.BStackJson;
import com.automation.core.utils.JsonUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class FullFlowSteps {
    private static final String BASE_URL = "https://reqres.in/api/users/2";
    private Response response;
    AppiumDriver mobileDriver;

    private String email;

    @Given("Set up the API URL")
    public void theRestAPIIsUpAndRunning() {
        RestAssured.baseURI = BASE_URL;
    }

    //Get email from API
    @When("Get email from API")
    public void iSendAGETRequestToTheAPI() throws MalformedURLException, InterruptedException {
        RequestSpecification httpRequest = RestAssured.given();
        response = httpRequest.get();
        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        email = jsonObject.getJSONObject("data").getString("email");
        System.out.println("Email: " + email);
    }

    //Open app on mobile
    @And("Login on App via AndroidStudio")
    public void loginOnAndroidStudio() throws InterruptedException, MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities = JsonUtils.getCapabilitiesFromJson("Device1");
        String appName = capabilities.getCapability("appium:app").toString();
        String newPath = System.getProperty("user.dir") + "\\" + appName;
        capabilities.setCapability("appium:app", newPath);
        mobileDriver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), capabilities);

        //Login by email from API
        LoginPage loginPage = new LoginPage(mobileDriver);
        User user = new User();
        user.generateLoginUser();
        user.setEmail(email);
        loginPage.login(user);
    }


    @And("Login on App via BrowserStack")
    public void iSendAGETRequestToTheAPI1() throws MalformedURLException, InterruptedException {
        //Open app on mobile
        MutableCapabilities capabilities = new MutableCapabilities();
        String filePath = System.getProperty("user.dir") + "/input/mobile/devices.json";
        capabilities = BStackJson.getCapabilitiesFromJson(filePath,"Device5");
        mobileDriver = new AndroidDriver(new URL("https://hub.browserstack.com/wd/hub"), capabilities);

        //Login by email from API
        LoginPage loginPage = new LoginPage(mobileDriver);
        User user = new User();
        user.generateLoginUser();
        user.setEmail(email);
        loginPage.login(user);
    }

    @Then("Verify Login Fail")
    public void verifyLoginFail() {
        LoginPage loginPage = new LoginPage(mobileDriver);
        loginPage.verifyLoginFail();
    }
}
