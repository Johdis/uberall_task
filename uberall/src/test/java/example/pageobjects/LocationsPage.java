package example.pageobjects;

import example.drivercreation.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class LocationsPage {

    RemoteWebDriver browserDriver = DriverFactory.getInstance().getDriver();

    private String searchField = ".search-input";
    private String resultset = ".location-edit-link";

    public void performSearch(String searchTerm) {
        WebElement searchInputField = browserDriver.findElement(By.cssSelector(searchField));
        searchInputField.clear();
        searchInputField.sendKeys(searchTerm);
        searchInputField.sendKeys(Keys.ENTER);
    }

    public int getNumberOfResults() {
        return browserDriver.findElementsByCssSelector(resultset).size();
    }

    public LocationEditPage navigateToLocationEditPage(String child) {
        browserDriver.findElement(By.cssSelector(String.format("#sync-status-list tr:nth-child(%s) .location-edit-link",
                child))).click();
        return new LocationEditPage();
    }
}
