package com.saha.bigpara.base;

import com.saha.bigpara.step.StepImplementation;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    private static final String APPACTIVITY = "com.foreks.android.bigpara.view.splash.SplashScreenActivity";
    private static final String APPPACKAGE = "com.pozitron.hepsiburada";
    public static DesiredCapabilities capabilities;
    protected static final Logger log = Logger.getLogger(BaseTest.class);
    protected static AppiumDriver<MobileElement> driver;
    private boolean localAndroid = true;
    String ip = "http://0.0.0.0:4723/wd/hub";

    public void setUp() throws MalformedURLException {

        PropertyConfigurator.configure("properties/log4j.properties");
        capabilities = new DesiredCapabilities();

        if (StringUtils.isEmpty(System.getenv("key"))) {
            if (localAndroid) {
                capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
                capabilities.setPlatform(Platform.ANDROID);
                log.info("*** PLATFORM : " + capabilities.getCapability("PlatformName"));
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,"emulator");
                capabilities.setCapability("appPackage",APPPACKAGE);
                capabilities.setCapability("appActivity",APPACTIVITY);
                capabilities.setCapability("unicodeKeyboard",true);
                capabilities.setCapability("resetKeyboard",false);
                capabilities.setCapability("interKeyDelay",300);
                capabilities.setCapability(MobileCapabilityType.FULL_RESET,false);
                capabilities.setCapability(MobileCapabilityType.NO_RESET,true);
                capabilities.setCapability("autoGrantPermissions","true");
                driver = new AndroidDriver<>(new URL(ip),capabilities);
                driver.manage().timeouts().implicitlyWait(15L, TimeUnit.SECONDS);
            } else {
                DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
                desiredCapabilities
                        .setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                desiredCapabilities
                        .setCapability(MobileCapabilityType.UDID, "c3be3f9d978c1ae01581864cfd880eee5a76f692");
                desiredCapabilities
                        .setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.doganonline.bigpara");
                desiredCapabilities
                        .setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone");
                desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
                desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, false);
                desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 300);
                URL url = new URL("http://127.0.0.1:4723/wd/hub");
                driver = new IOSDriver<>(url, desiredCapabilities);
            }
        } else {
            capabilities.setCapability("key", System.getProperty("key"));
            String hubURL = "http://10.104.187.184:4444/wd/hub";

            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            capabilities.setCapability("appPackage", APPPACKAGE);
            capabilities.setCapability("appActivity", APPACTIVITY);
            driver = new AndroidDriver<>(new URL(hubURL), capabilities);
        }
    }

    public void tearDown() throws IOException {

        StepImplementation.driver.quit();
    }
}

