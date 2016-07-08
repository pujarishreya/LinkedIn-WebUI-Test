package com.linkedin.pageobjects;

import com.linkedin.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by shreya on 3/10/16.
 */
public class HomePage {

    public static ProfilePage clickProfileTab(WebDriver driver) {
        //Click on Profile Tab to make changes in the user profile.
        WebUtil.click(driver, By.cssSelector("#main-site-nav > ul > li.nav-item:nth-child(2) > a"));
        return PageFactory.initElements(driver, ProfilePage.class);
    }

    public LoginPage signOut(WebDriver driver) {
        //Click on User Icon.
        WebUtil.click(driver, By.cssSelector("#account-nav > ul > li.nav-item.account-settings-tab > a > img"));
        //Wait till the page gets refreshed.
        WebUtil.waitForElementsToBeVisible(driver, By.cssSelector("#account-sub-nav > div > div.account-sub-nav-body > ul > li.self > div > span > span.act-set-action > a"));
        //Find the Sign Out button and click on it.
        WebUtil.click(driver, By.cssSelector("#account-sub-nav > div > div.account-sub-nav-body > ul > li.self > div > span > span.act-set-action > a"));
        return PageFactory.initElements(driver, LoginPage.class);
    }

    public static MessagesPage clickOnMessagesTab(WebDriver driver, By by) {
        //Click on Messages tab.
        WebUtil.click(driver, by);
        return PageFactory.initElements(driver, MessagesPage.class);
    }
}
