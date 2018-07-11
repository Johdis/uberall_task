package example.pageobjects;

import example.drivercreation.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LoginPage {

    RemoteWebDriver browserDriver = DriverFactory.getInstance().getDriver();

    private String loginLink = ".login-form__link";
    private String emailField = "applicant0.loginForm.email";
    private String passwordField = "applicant0.loginForm.password";
    private String loginSubmitButton = ".button.login-form__submit";
    private String errorMessage = ".field__error";

    public void navigateToLoginForm() {
        browserDriver.findElement(By.cssSelector(loginLink)).click();
    }

    public void enterLoginData() {
        browserDriver.findElement(By.id(emailField)).sendKeys("Hallo@smava.de");
        browserDriver.findElement(By.id(passwordField)).sendKeys("thisisapassword");
    }

    public void submitLoginData() {
        browserDriver.findElement(By.cssSelector(loginSubmitButton)).click();
    }

    public WebElement errorMessage(){
        return browserDriver.findElement(By.cssSelector(errorMessage));
    }
}
