package example.drivercreation;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class BuildDriver {

    private String hub;
    private String port;

    public BuildDriver() {
        super();
    }

    @BeforeClass
    public void setup() {
        readPropertyParameters();
        setHubURL();
    }

    @BeforeMethod
    public void loadHomepage() {
        RemoteWebDriver browserDriver = DriverFactory.getInstance().getDriver();
        browserDriver.setFileDetector(new LocalFileDetector());

        // Setting the browser size
        browserDriver.manage().window().setSize(new Dimension(1920, 1080));

        // Setting timeouts
        browserDriver.manage().timeouts().implicitlyWait(DriverHelper.timeout, TimeUnit.SECONDS);

        browserDriver.get(String.format("https://www.%s", DriverHelper.host));
    }

    @AfterMethod
    public void closeDriver() {
        DriverFactory.getInstance().removeDriver();
    }

    @AfterClass
    public void closeLogWriter() {
        DriverHelper.getWriter().close();
    }

    private void readPropertyParameters() {
        DriverHelper.timeout = Integer.parseInt(System.getenv("TIMEOUT"));
        DriverHelper.browserName = System.getenv("BROWSER");
        DriverHelper.host = System.getenv("HOST");
        hub = System.getenv("HUB_HOST");
        port = System.getenv("PORT");
    }

    private void setHubURL() {
        DriverHelper.hubURL = String.format("http://%s:%s/wd/hub", hub, port);
    }
}
