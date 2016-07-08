package com.linkedin.pageobjects;

import com.linkedin.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by shreya on 3/10/16.
 */
public class LoginPage {

    public static HomePage clickSignIn(WebDriver driver) {
        //Now find the Sign In button using the locator -> name.
        WebUtil.click(driver, By.name("submit"));
        //Maximize the window.
        driver.manage().window().maximize();
        //Wait for the page to get refreshed.
        WebUtil.waitForElementsToBeVisible(driver, By.linkText("Home"));
        return PageFactory.initElements(driver,HomePage.class);
    }

    public void enterEmail(WebDriver driver, String s) {
        //Creating WebElement emailTextBox.
        WebElement emailTextBox = WebUtil.findingElement(driver, By.id("login-email"));
        //Clear the emailTextBox to ensure that no text is present and enter the login Id.
        WebUtil.clearAndEnterText(emailTextBox, s);
    }

    public void enterPassword(WebDriver driver, String s) {
        //Creating WebElement passwordTextBox.
        WebElement passwordTextBox = WebUtil.findingElement(driver, By.id("login-password"));
        //Clear the passwordTextBox to ensure that no text is present and enter the login Password.
        WebUtil.clearAndEnterText(passwordTextBox, s);
    }

    public static boolean doesResponseContains(WebDriver driver, String s) {
        //Check and return if the given string is present in the response.
        return WebUtil.doesResponseContains(driver, s);
    }

}
