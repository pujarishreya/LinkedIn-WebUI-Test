package com.linkedin.pageobjects;

import com.linkedin.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * Created by shreya on 3/24/16.
 */
public class MessagesPage {

    public void clickOnCompose(WebDriver driver, By by) {
        //Click on Compose Message button.
        WebUtil.click(driver, by);
    }

    public void addNameToSendMessage(WebElement element, String s) {
        //Enter the text in String s to the WebElement specified by element.
        WebUtil.clearAndEnterText(element, s);
        //Wait for few seconds for the display of existing connections name.
        WebUtil.waitForMilliSeconds();
        //Select 'Enter keyboard input method'.
        element.sendKeys(Keys.RETURN);

    }

    public void addMessageText(WebElement element, String s) {
        //Clear and add the text in Locator specified by element.
        WebUtil.clearAndEnterText(element, s);
    }

    public void clickOnSend(WebDriver driver, By by) {
        //Disable the feature to send message by pressing Enter.
        WebUtil.click(driver, By.cssSelector("#enter-to-send-checkbox"));
        //Click on Send button to send the message.
        WebUtil.click(driver, by);
    }
}
