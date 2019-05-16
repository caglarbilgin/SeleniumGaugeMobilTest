package com.saha.bigpara.step;

import com.saha.bigpara.base.BaseTest;
import com.saha.bigpara.mapping.MapMethodType;
import com.saha.bigpara.mapping.Mapper;
import com.saha.bigpara.util.ContexPage;

import com.thoughtworks.gauge.AfterScenario;
import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.Step;

import io.appium.java_client.MobileElement;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;

import static com.saha.bigpara.mapping.Mapper.foundActivity;

public class StepImplementation extends BaseTest {

    private WebDriverWait wait;

    @BeforeScenario
    public void setUpStep() throws MalformedURLException {
        setUp();
        driver = BaseTest.driver;
        wait = new WebDriverWait(driver, 60);

    }

    @Step("<button> butonuna tıkla")
    public void clickButton(String button) {
        wait.until(ExpectedConditions.elementToBeClickable(foundActivity(MapMethodType.CLICK_ELEMENT, button)));
        driver.findElement(Mapper.foundActivity(MapMethodType.CLICK_ELEMENT, button)).click();
    }

    @Step("<i> saniye bekle")
    public void waitSecods(int i) {
        ContexPage.waitSeconds(i);
    }

    @Step("<by> alanına <text> yaz")
    public void inputText(String by, String text) {
        wait.until(ExpectedConditions.presenceOfElementLocated(foundActivity(MapMethodType.INPUT_ELEMENT, by)));
        log.info(text);
        driver.findElement(Mapper.foundActivity(MapMethodType.INPUT_ELEMENT, by)).sendKeys(text);
    }

    @Step("<list> listesinden <i> numaralı elementi seç")
    public void selectFromList(String list, int i) {
        wait.until(ExpectedConditions.presenceOfElementLocated(foundActivity(MapMethodType.SELECT_ELEMENT, list)));
        driver.findElements(Mapper.foundActivity(MapMethodType.SELECT_ELEMENT, list));
        List<MobileElement> listElement = driver.findElements(foundActivity(MapMethodType.SELECT_ELEMENT, list));
        listElement.get(i - 1).click();
    }

    @Step("<i> kere sağa git")
    public void swipeLeft(int i) {
        for (int j = 0; j < i; j++) {
            ContexPage.swipeLeftAccordingToPhoneSize();
        }
    }

    @Step("<i> kere sola git")
    public void swipeRight(int i) {
        for (int j = 0; j < i; j++) {
            ContexPage.swipeRightAccordingToPhoneSize();
        }
    }

    @Step("<i> kere aşağı in")
    public void swipeUp(int i) {
        for (int j = 0; j < i; j++) {
            ContexPage.swipeUpAccordingToPhoneSize();
        }
        log.info(driver.getPageSource());
    }

    @Step("<i> kere yukarı çık")
    public void swipeDown(int i) {
        for (int j = 0; j < i; j++) {
            ContexPage.swipeDownAccordingToPhoneSize();
        }
    }

    @Step("<by> elementini görene kadar aşağı in")
    public void swipeUpUntilSeeElement(String by) {
        for (int i = 0; i < 25; i++) {
            try {
                driver.findElement(foundActivity(MapMethodType.IS_ELEMENT, by)).isDisplayed();
                log.info(by + " elemen ti görünür");
                break;
            } catch (Exception ex) {
                ContexPage.swipeUpAccordingToPhoneSize();
            }
        }
    }

    @Step("<by> elementini görene kadar yukarı çık")
    public void swipeDownUntilSeeElement(String by) {
        for (int i = 0; i < 1000; i++) {
            try {
                driver.findElement(foundActivity(MapMethodType.IS_ELEMENT, by)).isDisplayed();
                log.info(by + " elementi görünür");
                break;
            } catch (Exception ex) {
                ContexPage.swipeDownAccordingToPhoneSize();
            }
        }
    }

    @Step("<by> elementini görene kadar sola git")
    public void swipeRightUntilSeeElement(String by) {
        for (int i = 0; i < 1000; i++) {
            try {
                driver.findElement(foundActivity(MapMethodType.IS_ELEMENT, by)).isDisplayed();
                break;
            } catch (Exception ex) {
                ContexPage.swipeRightAccordingToPhoneSize();
            }
        }
    }

    @Step("<by> elementini görene kadar sağa git")
    public void swipeLeftUntilSeeElement(String by) {
        for (int i = 0; i < 1000; i++) {
            try {
                driver.findElement(foundActivity(MapMethodType.IS_ELEMENT, by)).isDisplayed();
                break;
            } catch (Exception ex) {
                ContexPage.swipeLeftAccordingToPhoneSize();
            }
        }
    }

    @Step("<by> elementinin tekstini al")
    public void getElementText(String by) {

        String elementText = driver.findElement(foundActivity(MapMethodType.IS_ELEMENT, by)).getText();
        log.info(elementText);
    }

    @Step("<by> elementinin görünür olmasını bekle")
    public void waitForElement(String by) {
        driver.findElement(foundActivity(MapMethodType.IS_ELEMENT, by)).isDisplayed();
        log.info(by + " elementi göründü.");
    }

    @Step("<list> listesindeki elementlerin görünürlüğünü kontrol et")
    public void checkElementsDisplayed(String list) {
        driver.findElements(foundActivity(MapMethodType.SELECT_ELEMENT, list));
        List<MobileElement> listElement = driver.findElements(foundActivity(MapMethodType.SELECT_ELEMENT, list));
        for (int i = 0; i < listElement.size(); i++) {
            try {
                listElement.get(i).isDisplayed();
                log.info(i + 1 + "." + list + " elementi görünür");
            } catch (Exception ex) {
                log.info(i + 1 + "." + list + " elementi görünür değil");
            }
        }
    }

    @Step("<by> alanındaki texti <value> değişkenine ata")
    public void saveParameter(String by, String value) {
        MobileElement element = ContexPage.getElement(Mapper.foundActivity(MapMethodType.IS_ELEMENT, by));
        String elementText = element.getText();
        Assert.assertNotNull(elementText + " null geldi", elementText);
        ContexPage.setParameter(elementText, value);
        log.info(value);
    }

    @Step("<value> textini sayfada ara")
    public void searchTest(String value) {
        Assert.assertTrue("İstenilen kelime bulunamadı.", driver.getPageSource().contains(value));
        log.info(value + " sayfada bulundu");
    }

    @Step("<by1> ve <by2> elementlerinin eşitliğini kontrol et")
    public void checkElementsEquals(String by1, String by2) {
        try {
            Assert.assertEquals(by1, by2);
            log.info(by1 + " ve " + by2 + " elementleri eşit");
        } catch (AssertionError e) {
            log.info(by1 + " ve " + by2 + " elementleri eşit değil");
        }
    }

    @Step("<by> ve <text> değerlerinin eşitliğini kontrol et")
    public void checkElementAndTextValueEquals(String by, String text) {
        text = text.toLowerCase();
        String element = driver.findElement(foundActivity(MapMethodType.IS_ELEMENT, by)).getText().toLowerCase();
        Assert.assertEquals("İki değer birbirine eşit değil.", element, text);
    }


    /*
    @Step("<list> alanındaki en büyük değeri loga yazdır")
    public void maximumValue(String list){
        driver.findElements(Mapper.foundActivity(MapMethodType.SELECT_ELEMENT, list));
        List<MobileElement> listElement = driver.findElements(found  Activity(MapMethodType.SELECT_ELEMENT, list));

        double [] valuelist = new double [listElement.size()];

        String stringvalue = "0.080"; //listElement.get(2).getText();

        double value=Double.parseDouble(stringvalue);
        log.info(value);

        for(int i =3 ; i< listElement.size() ; i++) {
            stringvalue = listElement.get(i).getText();
            valuelist[i]=Integer.parseInt(stringvalue);
            if(valuelist[i]>value)
                value =valuelist[i];
        }
         log.info("En büyük değer = "+ value);
    }

    */

    @Step("<list> alanındaki en büyük değeri loga yazdır")
    public void maximumValue(String list) {
        driver.findElements(Mapper.foundActivity(MapMethodType.SELECT_ELEMENT, list));
        List<MobileElement> listElement = driver.findElements(foundActivity(MapMethodType.SELECT_ELEMENT, list));
        NumberFormat f = NumberFormat.getInstance();
        log.info(f);

        double[] valuelist = new double[listElement.size()];
        double value = 0;

        try {
            value = f.parse(listElement.get(2).getText()).doubleValue() / 1000;

            for (int i = 3; i < listElement.size(); i++) {
                valuelist[i] = f.parse(listElement.get(2).getText()).doubleValue() / 1000;
                if (valuelist[i] > value)
                    value = valuelist[i];
            }

        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }

        String as = String.valueOf(value);
        log.info("En büyük değer: " + as);
    }

    //*x********************************************************************************************************************************************

    @Step(" <list> elementine tikla deneme")
    public void tikla(String list) {

        driver.findElement(Mapper.foundActivity(MapMethodType.DENEME_ELEMENT, list)).click();

    }



    @AfterScenario
    public void tearDownStep() throws IOException {
        super.tearDown();
    }
}
