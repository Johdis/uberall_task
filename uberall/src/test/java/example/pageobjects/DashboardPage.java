package example.pageobjects;

import example.drivercreation.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DashboardPage {

    RemoteWebDriver browserDriver = DriverFactory.getInstance().getDriver();

    private String userDropdownEmail = ".user-dropdown-email";
    private String locationTab = "#locations-link";

    public WebElement checkLoginStatus() {
        return browserDriver.findElement(By.cssSelector(userDropdownEmail));
    }

    public LocationsPage navigateToLocationsPage() {
        browserDriver.findElement(By.cssSelector(locationTab)).click();

        return new LocationsPage();
    }
}
