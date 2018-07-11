package example.pageobjects;

import example.drivercreation.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SummaryPage {

    RemoteWebDriver browserDriver = DriverFactory.getInstance().getDriver();

    private String nextButton = "#cta_btn_0";

    public LoginPage submit() {
        browserDriver.findElement(By.cssSelector(nextButton)).click();
        return new LoginPage();
    }
}
