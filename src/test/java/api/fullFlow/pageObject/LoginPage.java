package api.fullFlow.pageObject;

import api.fullFlow.pojo.User;
import com.automation.core.utils.LibMobileGeneric;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.remote.html5.RemoteWebStorage;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.time.Duration;


public class LoginPage {

    AppiumDriver mobileDriver;
    public LoginPage (AppiumDriver mobileDriver){
        this.mobileDriver = mobileDriver;
        PageFactory.initElements(new AppiumFieldDecorator(mobileDriver), this);
    }

    @AndroidFindBy(id = "com.loginmodule.learning:id/textInputEditTextEmail")
//    @iOSXCUITFindBy(id = "com.loginmodule.learning:id/textInputEditTextEmail")
    WebElement txtEmail;
    @AndroidFindBy(id = "com.loginmodule.learning:id/textInputEditTextPassword")
    WebElement txtPassword;
    @AndroidFindBy(id = "com.loginmodule.learning:id/appCompatButtonLogin")
    WebElement btnLogin;
    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id=\"com.loginmodule.learning:id/snackbar_text\"]")
    WebElement msgWrongLogin;

    public void login(User user) throws InterruptedException {
        LibMobileGeneric.waitForElementVisible(mobileDriver ,txtEmail, Duration.ofSeconds(60));
        txtEmail.sendKeys(user.getEmail());
        txtPassword.sendKeys(user.getPassword());
        btnLogin.click();
    }

    public void verifyLoginFail() {
        String actualMessage = msgWrongLogin.getText();
        String expectedMessage = ("Wrong Email or Password");
        Assert.assertEquals(actualMessage, expectedMessage);
    }
}


