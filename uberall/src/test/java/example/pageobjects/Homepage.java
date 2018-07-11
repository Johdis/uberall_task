package example.pageobjects;


import example.drivercreation.DriverFactory;
import example.drivercreation.DriverHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Homepage {

    RemoteWebDriver browserDriver = DriverFactory.getInstance().getDriver();

    private String loanDropdown = "myselect";
    private String loanDurationDropdown = "#myselect2";
    private String loanCategoryDropdown = "#myselect3";
    private String continueButton = "#loanSelectionForward";

    public void enterLoanData() {

        WebDriverWait wait = new WebDriverWait(browserDriver, DriverHelper.timeout);
        wait.until(ExpectedConditions.elementToBeClickable(By.id(loanDropdown)));

        browserDriver.findElement(By.id(loanDropdown)).click();
        browserDriver.findElement(By.cssSelector("div[data-value=\"2750\"]")).click();
        browserDriver.findElement(By.cssSelector(loanDurationDropdown)).click();
        browserDriver.findElement(By.cssSelector("div[data-value=\"24\"]")).click();
        browserDriver.findElement(By.cssSelector(loanCategoryDropdown)).click();
        browserDriver.findElement(By.cssSelector("div[data-value=\"886\"]")).click();
    }

    public SummaryPage submit() {
        browserDriver.findElement(By.cssSelector(continueButton)).click();
        return new SummaryPage();
    }
}
