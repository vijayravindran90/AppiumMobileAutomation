package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseConfig {

    public DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
    public AppiumServiceBuilder appiumServiceBuilder;
    public AppiumDriverLocalService appiumDriverLocalService;
    public static AppiumDriver<MobileElement> driver = null;

    @BeforeSuite
    public AppiumDriver<MobileElement> getDriver() {
        startServer();
        androidSetup();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        return driver;
    }

    public void androidSetup() {

        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,"com.amazon.mShop.android.shopping" );
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.amazon.mShop.home.HomeActivity");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.SKIP_UNLOCK,"true");
        desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        driver = new AndroidDriver<MobileElement>(appiumDriverLocalService.getUrl(), desiredCapabilities);
    }
    public void startServer() {
        appiumServiceBuilder = new AppiumServiceBuilder();
        appiumServiceBuilder.withIPAddress("127.0.0.1");
        //appiumServiceBuilder.usingPort(4723);
        appiumServiceBuilder.usingAnyFreePort();
        appiumServiceBuilder.withCapabilities(desiredCapabilities);
        appiumServiceBuilder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        appiumServiceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
        appiumServiceBuilder.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"));
        // Tell serviceBuilder where Appium is installed. Or set this path in an environment variable named APPIUM_PATH
        appiumServiceBuilder.withAppiumJS(new File("C:\\npm\\node_modules\\appium\\build\\lib\\main.js"));
        appiumDriverLocalService = AppiumDriverLocalService.buildService(appiumServiceBuilder);
        appiumDriverLocalService.start();
        System.out.println("URL: " + appiumDriverLocalService.getUrl());
    }
    public void stopServer() {
        appiumDriverLocalService.stop();
    }
    @AfterSuite
    public void tearDown() {
        driver.quit();
        stopServer();
    }
    public AppiumDriver<MobileElement> getMobileDriver() {
        if (driver == null) {
            getDriver();
        }
        return driver;
    }
}
