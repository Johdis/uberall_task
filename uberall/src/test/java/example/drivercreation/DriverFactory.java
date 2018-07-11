package example.drivercreation;


import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class DriverFactory {
    private static DriverFactory instance = new DriverFactory();

    DesiredCapabilities capability = new DesiredCapabilities();

    // Local Thread for threadsafe parallel execution
    ThreadLocal<RemoteWebDriver> browserDriver = new ThreadLocal<RemoteWebDriver>() {
        @Override
        protected RemoteWebDriver initialValue() {
            setCapability();
            RemoteWebDriver driver = null;
            try {
                driver = new RemoteWebDriver(new URL(DriverHelper.hubURL), capability);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return driver;
        }
    };

    private DriverFactory() {

    }

    public static DriverFactory getInstance() {
        return instance;
    }

    private void setCapability() {
        switch (DriverHelper.browserName.toLowerCase()) {
            case "firefox":
                capability.setCapability(CapabilityType.BROWSER_NAME, BrowserType.FIREFOX);
                capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
                capability.setCapability("max_duration", "900");
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addPreference("dom.file.createInChild", true);
                firefoxOptions.addPreference("dom.disable_open_during_load", false);
                firefoxOptions.addPreference("dom.disable_beforeunload", true);
                capability.setCapability("moz:firefoxOptions", firefoxOptions);
                break;
            case "chrome":
                capability.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
                capability.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
                ChromeOptions chromeOptions = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("profile.default_content_settings.popups", 1);
                chromeOptions.setExperimentalOption("prefs", prefs);
                break;
            default:
                capability.setCapability(CapabilityType.BROWSER_NAME, BrowserType.CHROME);
        }
    }

    // call this method to get the driver object
    public RemoteWebDriver getDriver()
    {
        return browserDriver.get();
    }

    // Quits the driver and closes the browser
    public void removeDriver()
    {
        browserDriver.get().quit();
        browserDriver.remove();
    }
}
