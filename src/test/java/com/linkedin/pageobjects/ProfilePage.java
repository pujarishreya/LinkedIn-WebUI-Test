package com.linkedin.pageobjects;

import com.linkedin.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by shreya on 3/10/16.
 */
public class ProfilePage {

    public void clickLocationField(WebDriver driver) {
        //Click on edit Experience field (by location).
        WebUtil.click(driver, By.cssSelector("#experience-822025192-view > div.date-header-field.field > button > i"));
        //Click on the location field.
        WebUtil.click(driver, By.cssSelector("#positionLocationName-position-editPositionForm"));
    }

    public void enterLocation(WebDriver driver, String s) {
        //Clear Location field.
        WebUtil.clearAndEnterText(driver.findElement(By.cssSelector("#positionLocationName-position-editPositionForm")), s);
        //After making the changes in Location field, we need to click on other area on the screen for making Save button clickable.
        WebUtil.click(driver, By.cssSelector("#title-position-editPositionForm"));
        //Scroll down by 750 pixel for the visibility of Save Button.
        ((JavascriptExecutor) driver).executeScript("scroll(0,750)");
        //Wait for the page to get refreshed.
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ProfilePage clickOnSave(WebDriver driver) {
        WebUtil.click(driver, By.cssSelector("form.standard-form > p.actions > input"));
        return PageFactory.initElements(driver, ProfilePage.class);
    }

    public static boolean doesResponseContains(WebDriver driver, String s) {
        //Check and return if the given string is present in the response.
        return WebUtil.doesResponseContains(driver, s);
    }

}
