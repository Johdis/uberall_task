package example.tests;

import example.drivercreation.BuildDriver;
import example.pageobjects.DashboardPage;
import example.pageobjects.LocationEditPage;
import example.pageobjects.LocationsPage;
import example.pageobjects.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UberallTest extends BuildDriver {

    @Test
    public void changeLocalIds() {
        LoginPage loginPage = new LoginPage();
        Assert.assertEquals(loginPage.checkUberallLogo().isDisplayed(), true, "Login page not loaded");
        loginPage.enterLoginData();

        DashboardPage dashboardPage = loginPage.submitLoginData();
        Assert.assertEquals(dashboardPage.checkLoginStatus().isDisplayed(), true, "Login failed");

        LocationsPage locationsPage = dashboardPage.navigateToLocationsPage();
        locationsPage.performSearch("Braunschweig");
        int numberOfresults = locationsPage.getNumberOfResults();

        for (int i = 1; i <= numberOfresults; i++) {
            LocationEditPage locationEditPage = locationsPage.navigateToLocationEditPage(Integer.toString(i));
            if (locationEditPage.isCorrectCity()) {
                String newIdentifier = locationEditPage.createIdentifier();
                locationEditPage.changeLocationId(newIdentifier);
                Assert.assertEquals(newIdentifier, locationEditPage.checkSavedIdentifier(),
                        "Identifiers are not updated correctly");
            }
            dashboardPage.navigateToLocationsPage();
            locationsPage.performSearch("Braunschweig");
        }
    }

    @Test
    public void failedLoginTest() {
        LoginPage loginPage = new LoginPage();
        Assert.assertEquals(loginPage.checkUberallLogo().isDisplayed(), false, "Login page not loaded");
    }
}
