package com.linkedin.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by shreya on 3/10/16.
 */
public class LoginPage {
    public static boolean doesResponseContains(WebDriver driver, String s) {
        //Ensure that the string 's' is present in the HTML Response.
        //For that we will first get all the responses.
        String response = driver.getPageSource();

        //Return the response in 's'.
        return response.contains(s);
    }

    public static void enterEmail(WebDriver driver, String s) {
        //Creating WebElement emailTextBox, using findElement to find the element with id = "login-email"
        //in the HTML Document structure of LinkedIn.
        WebElement loginTextBox = driver.findElement(By.id("login-email"));
        //Clear the login-email box to ensure that no text is present
        loginTextBox.clear();
        //Enter the email in login box.
        loginTextBox.sendKeys(s);
    }

    public void enterPassword(WebDriver driver, String s) {
        //Creating WebElement passwordTextBox, using findElement to find the element with id = "login-password"
        //in the HTML Document structure of LinkedIn.
        WebElement passwordTextBox = driver.findElement(By.id("login-password"));
        //Clear the login-password box to ensure that no text is present.
        passwordTextBox.clear();
        //Enter password in the login-password box.
        passwordTextBox.sendKeys(s);
    }

    public static HomePage clickSignIn(WebDriver driver) {
        //Now find the Sign In button using the locator -> name.
        WebElement signInButton = driver.findElement(By.name("submit"));
        signInButton.click();
        //Maximize the window.
        driver.manage().window().maximize();

        //Wait for the page to get refreshed.
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Home")));

        return PageFactory.initElements(driver,HomePage.class);
    }
}
