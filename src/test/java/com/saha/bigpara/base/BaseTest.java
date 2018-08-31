package com.saha.bigpara.base;

import com.saha.bigpara.step.StepImplementation;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
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
    private static final String APPPACKAGE = "com.foreks.android.bigpara";
    public static DesiredCapabilities capabilities;
    protected static final Logger log = Logger.getLogger(BaseTest.class);
    protected static AppiumDriver<MobileElement> driver;
    String ip = "http://0.0.0.0:4723/wd/hub";

    public void setUp() throws MalformedURLException {

        PropertyConfigurator.configure("properties/log4j.properties");
        capabilities = new DesiredCapabilities();

        if (StringUtils.isEmpty(System.getProperty("key"))) {
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
            driver.manage().timeouts().implicitlyWait(120L, TimeUnit.SECONDS);

        }else{
            capabilities.setCapability("key", System.getProperty("key"));
            String hubURL = "http://10.104.187.184:4444/wd/hub";

            capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            capabilities.setCapability("appPackage",APPPACKAGE);
            capabilities.setCapability("appActivity",APPACTIVITY);
            driver = new AndroidDriver<>(new URL(hubURL),capabilities);
        }


    }

    public void tearDown() throws IOException {

        StepImplementation.driver.quit();
    }
}
