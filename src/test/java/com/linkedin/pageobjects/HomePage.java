package com.linkedin.pageobjects;

import com.linkedin.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

    public void searchForCompany(WebDriver driver, WebElement element, String s) {
        //Clear and the text in String s in search bar.
        WebUtil.clearAndEnterText(element, s);
        //Select 'Enter keyboard input method' for generating the search results.
        element.sendKeys(Keys.RETURN);
        //Wait for search results to appear.
        WebUtil.waitForMilliSeconds();
        //Click on 'Companies' category under Search.
        WebUtil.click(driver, By.cssSelector("#search-types > div > ul > li:nth-child(4) > a"));
    }

    public void clickOnFollow(WebDriver driver, By by) {
        //Click on follow button against 'LinkedIn' company.
        WebUtil.click(driver, by);
        WebUtil.findingElement(driver, By.cssSelector("#results > li.mod.result.idx0.company > div > h3 > a > b")).getText().contains("LinkedIn");
    }

    public void clickOnUnfollow(WebDriver driver, By by) {
        //Un-follow recently followed company for future testing.
        WebUtil.click(driver, By.cssSelector("#results > li.mod.result.idx0.company > div > div.srp-actions.blue-button > div"));
        //Wait for being able to click on Unfollow.
        WebUtil.waitForMilliSeconds();
        //Click on Unfollow button.
        WebUtil.click(driver, by);
    }
}
