package example.logging;

import example.drivercreation.DriverFactory;
import example.drivercreation.DriverHelper;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;


public class Logger extends TestListenerAdapter {

    private static final String finishLine = "-------------------------------------------------";
    private static String fileSeperator = System.getProperty("file.separator");
    private static String directory;
    private static final long HOUR = 3600 * 1000;

    public static final String targetString = "build";
    public static final String emptyString = "";
    public static final String fileString = "file:";

    @Override
    public void onStart(ITestContext testContext) {
        createLogFile();
        log("Test started");
        log(finishLine);
    }

    @Override
    public void onTestSuccess(ITestResult testresult) {
        String passedString = "' PASSED";
        String testName = testresult.getName();
        log("Test '" + testName + passedString);
        log(finishLine);
        removeDriver();
    }

    @Override
    public void onTestFailure(ITestResult testresult) {
        String failedString = "' FAILED";
        String testName = testresult.getName();
        String errorStacktrace = testresult.getThrowable().getMessage();
        Reporter.log("Messages", true);
        log("Test '" + testName + failedString);
        log(emptyString);
        log("Stacktrace:");
        log(errorStacktrace);
        log(finishLine);

        String testMethodName = String.format(testresult.getName().trim()," ", DriverHelper.browserName);
        String screenshotName = testMethodName + ".png";
        takeScreenShot(screenshotName);
        removeDriver();
    }

    @Override
    public void onTestSkipped(ITestResult testresult) {
        String skippedString = "' SKIPPED";
        String testName = testresult.getName();
        log("Test '" + testName + skippedString);
        log(finishLine);
        removeDriver();
    }

    @Override
    public void onFinish(ITestContext testContext) {
        log("Test finished");
    }

    private void log(String loggingStream) {
        PrintWriter writer = DriverHelper.getWriter();
        writer.println(loggingStream);
        writer.flush();
    }

    private void takeScreenShot(String screenShotName) {
        RemoteWebDriver browserDriver = DriverFactory.getInstance().getDriver();
        if (browserDriver != null) {

            Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).
                    takeScreenshot(browserDriver);

            try {
                ImageIO.write(screenshot.getImage(), "PNG", new File(directory, screenShotName));
            } catch (IOException exc) {
                exc.printStackTrace();
            }
        }
    }

        private void createLogFile() {
        java.util.Date date = new java.util.Date();
        Timestamp time = new Timestamp(date.getTime() + 2 * HOUR);

        directory = String.format("Testresult %s %s", time.toString().replace(":", "-"),
                System.getenv("BROWSER"));

        String location = Logger.class.getProtectionDomain().getCodeSource().getLocation().toString();
        String[] splittedLocation = location.split(targetString);
        DriverHelper.loggingFileLocation = splittedLocation[0].replace(fileString, emptyString).concat(directory);

        new File(DriverHelper.loggingFileLocation).mkdirs();

        try {
            DriverHelper.setWriter(new PrintWriter(directory + fileSeperator + directory + ".txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void removeDriver() {
        DriverFactory.getInstance().removeDriver();
    }
}
