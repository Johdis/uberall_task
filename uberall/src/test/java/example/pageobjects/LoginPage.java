package example.pageobjects;

import example.drivercreation.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LoginPage {

    RemoteWebDriver browserDriver = DriverFactory.getInstance().getDriver();

    private String uberallLogo = ".uberall-logo";
    private String emailField = "email";
    private String passwordField = "password";
    private String loginSubmitButton = ".btn.btn-backend-default.btn-login";

    public WebElement checkUberallLogo() {
        return browserDriver.findElement(By.cssSelector(uberallLogo));
    }

    public void enterLoginData() {
        browserDriver.findElement(By.id(emailField)).sendKeys("maxmustermann_qa3@uberall.com");
        browserDriver.findElement(By.id(passwordField)).sendKeys("uberall-qa-fun");
    }

    public DashboardPage submitLoginData() {
        browserDriver.findElement(By.cssSelector(loginSubmitButton)).click();

        return new DashboardPage();
    }
}
