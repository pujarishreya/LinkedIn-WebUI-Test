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
public class HomePage {
    public boolean doesHomeTabExist(WebDriver driver) {
        //Check if the Home tab is enabled.
        return driver.findElement(By.cssSelector("#main-site-nav > ul > li:nth-child(1) > a")).isEnabled();
    }

    public LoginPage signOut(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        //Find UserProfileIcon and click on it.
        WebElement profileButton = driver.findElement(By.cssSelector("#account-nav > ul > li.nav-item.account-settings-tab > a > img"));
        profileButton.click();

        //Wait till the page gets refreshed.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#account-sub-nav > div > div.account-sub-nav-body > ul > li.self > div > span > span.act-set-action > a")));

        //Find the Sign Out button and click on it.
        WebElement signOutButton = driver.findElement(By.cssSelector("#account-sub-nav > div > div.account-sub-nav-body > ul > li.self > div > span > span.act-set-action > a"));
        signOutButton.click();

        return PageFactory.initElements(driver, LoginPage.class);

    }

    public static ProfilePage clickProfileTab(WebDriver driver) {
        //Click on Profile Tab to make changes in the user profile.
        driver.findElement(By.cssSelector("#main-site-nav > ul > li.nav-item:nth-child(2) > a")).click();

        return PageFactory.initElements(driver, ProfilePage.class);

    }
}
