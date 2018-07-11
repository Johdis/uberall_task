package example.pageobjects;

import example.drivercreation.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.sql.Timestamp;

public class LocationEditPage {

    RemoteWebDriver browserDriver = DriverFactory.getInstance().getDriver();

    private String cityField = "#city";
    private String locationIdField = "#identifier";
    private String saveButton = ".save-button";


    public void changeLocationId(String newIdentifier) {
        WebElement identifier = browserDriver.findElement(By.cssSelector(locationIdField));
        identifier.clear();
        identifier.sendKeys(newIdentifier);
        browserDriver.findElement(By.cssSelector(saveButton)).click();
    }

    public String checkSavedIdentifier() {
        browserDriver.navigate().refresh();
        return browserDriver.findElement(By.cssSelector(locationIdField)).getAttribute("value");
    }

    public boolean isCorrectCity() {
        boolean isCityBraunschweig = true;
        if (!browserDriver.findElement(By.cssSelector(cityField)).getAttribute("value").equalsIgnoreCase(
                "Braunschweig")) {
            isCityBraunschweig = false;
        }

        return isCityBraunschweig;
    }

    public String createIdentifier() {
        long HOUR = 3600 * 1000;
        java.util.Date date = new java.util.Date();
        Timestamp time = new Timestamp(date.getTime() + 2 * HOUR);

        return String.format("changed %s", time.toString().replace(":", "-"));
    }
}
