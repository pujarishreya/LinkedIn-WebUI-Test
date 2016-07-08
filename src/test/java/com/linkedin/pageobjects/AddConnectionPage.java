package com.linkedin.pageobjects;

import com.linkedin.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by shreya on 3/20/16.
 */
public class AddConnectionPage {

    public static AddConnectionPage clickAddConnection(WebDriver driver, By by) {
        //Click on 'My Network' tab.
        WebUtil.click(driver, By.cssSelector("#nav-link-network"));
        //Click on 'Add Connection' from 'My Network' drop down menu.
        WebUtil.click(driver, By.cssSelector("#network-sub-nav > li:nth-child(2) > a"));
        return PageFactory.initElements(driver, AddConnectionPage.class);
    }

    public static void clickOnInvite(WebDriver driver, By by) {
        //Click on the button specified by Locator 'by'.
        WebUtil.click(driver, by);
    }

    public static void enterEmailToSendInvite(WebElement element, String s) {
        //Enter the email specified in String s.
        WebUtil.clearAndEnterText(element, s);
    }

    public void clickOnSubmit(WebDriver driver, By by) {
        //Click on Submit button to send the invite.
        WebUtil.click(driver, by);
    }
}
