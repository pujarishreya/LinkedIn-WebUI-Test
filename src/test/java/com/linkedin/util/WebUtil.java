package com.linkedin.util;

import com.linkedin.pageobjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by shreya on 3/10/16.
 */
public class WebUtil {
    private static final long WAIT_TIME_OUT_IN_SECONDS = 10;
    private static final long SLEEP_TIMEOUT_IN_MILLISECONDS = 5000;

    public static LoginPage goToLoginPage(WebDriver driver) {
        driver.get("https://www.linkedin.com"); // Opening LinkedIn Website
        return PageFactory.initElements(driver, LoginPage.class);
    }

    public static boolean doesElementExist(WebDriver driver, By by) {
        //Check if the Home tab is enabled.
        return findingElement(driver,by).isEnabled();
    }

    public static boolean doesResponseContains(WebDriver driver, String s) {
        //Ensure that the string 's' is present in the HTML Response.
        //For that we will first get all the responses.
        String response = driver.getPageSource();
        //Return the response in 's'.
        return response.contains(s);
    }

    public static WebElement findingElement(WebDriver driver, By by) {
        //Find and return the WebElement required by the program.
        return driver.findElement(by);
    }

    public static void clearAndEnterText(WebElement element, String s) {
        //Clear the text box to ensure that it is empty.
        element.clear();
        //Enter the String in specified WebElement.
        element.sendKeys(s);
    }

    public static void click(WebDriver driver, By by) {
        //Find the element with locator 'by'and click on it.
        WebElement webElement = driver.findElement(by);
        webElement.click();
    }

    public static void waitForElementsToBeVisible(WebDriver driver, By by) {
        //Wait till the element is visible.
        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIME_OUT_IN_SECONDS);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public static void waitForMilliSeconds() {
        //Wait for the page to get refreshed and for the actions to be synchronized.
        try {
            Thread.sleep(SLEEP_TIMEOUT_IN_MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
