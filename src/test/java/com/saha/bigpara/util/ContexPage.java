package com.saha.bigpara.util;


import com.saha.bigpara.base.BaseTest;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Locatable;

import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ContexPage extends BaseTest {

    public static final int DEFAULT_WAIT = 10;

    public static void contextSwitch(String context) {
        Set<String> contextNames = driver.getContextHandles();
        for (String contextName : contextNames) {
            log.info(contextNames);
            if (contextName.contains(context)) {
                driver.context(contextName);
                break;

            }
        }
    }
    public static int getPhoneX() {
        //Dimension d = driver.manage().window().getSize();
        return driver.manage().window().getSize().getWidth();
    }

    public static int getPhoneY() {
        //Dimension d = driver.manage().window().getSize();
        return driver.manage().window().getSize().getHeight();
    }

    public static void swipeDownAccordingToPhoneSize() {
        int height = getPhoneY();
        int width = getPhoneX();

        int swipeStartWidth = width / 2;
        int swipeEndWidth = width / 2;

        int swipeStartHeight = (height * 30) / 100;
        int swipeEndHeight = (height * 70) / 100;
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(swipeStartWidth,swipeStartHeight)).waitAction().moveTo(PointOption.point(swipeEndWidth,swipeEndHeight)).release().perform();
    }

    public static void swipeUpAccordingToPhoneSize() {
        int height = getPhoneY();
        int width = getPhoneX();

        int swipeStartWidth = width / 2;
        int swipeEndWidth = width / 2;

        int swipeStartHeight = (height * 70) / 100;
        int swipeEndHeight = (height * 30) / 100;
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(swipeStartWidth,swipeStartHeight)).waitAction().moveTo(PointOption.point(swipeEndWidth,swipeEndHeight)).release().perform();
    }

    public static void swipeLeftAccordingToPhoneSize() {
        int height = getPhoneY();
        int width = getPhoneX();

        int swipeStartWidth = (width * 80) / 100;
        int swipeEndWidth = (width * 15) / 100;
        int swipeStartHeight = height / 3;
        int swipeEndHeight = height / 3;
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(swipeStartWidth,swipeStartHeight)).waitAction().moveTo(PointOption.point(swipeEndWidth,swipeEndHeight)).release().perform();
    }

    public static void swipeRightAccordingToPhoneSize() {
        int height = getPhoneY();
        int width = getPhoneX();

        int swipeStartWidth = (width * 15) / 100;
        int swipeEndWidth = (width * 80) / 100;
        int swipeStartHeight = height / 3;
        int swipeEndHeight = height / 3;
        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(swipeStartWidth,swipeStartHeight)).waitAction().moveTo(PointOption.point(swipeEndWidth,swipeEndHeight)).release().perform();
    }

    public static void waitSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
    }

    public void checkTextEqual(String text1, String text2){
        Assert.assertEquals(text1,text2);

    }

    public static  MobileElement getElement(By locator) {
        MobileElement element = null;
        if (locator != null) {
            WebDriverWait wait = new WebDriverWait(driver, DEFAULT_WAIT);
            element = (MobileElement) wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }
        return element;
    }

    public static void setParameter(String userKey, String userValue){
        Map<String,String> parameter = new HashMap<>();
        parameter.replace(userKey,userValue);
    }

}
