package com.linkedin.pageobjects;

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
        driver.findElement(By.cssSelector("#experience-822025192-view > div.date-header-field.field > button > i")).click();
        //Click on the location field.
        driver.findElement(By.cssSelector("#positionLocationName-position-editPositionForm")).click();
        //Clear Location field.
        driver.findElement(By.cssSelector("#positionLocationName-position-editPositionForm")).clear();
        //Add location - 'California'
        driver.findElement(By.cssSelector("#positionLocationName-position-editPositionForm")).sendKeys("California");
        //Click on Title field for clearing the drop down menu at Location field.
        //   driver.findElement(By.cssSelector("#positionLocationName-position-editPositionForm")).click();
        driver.findElement(By.cssSelector("#title-position-editPositionForm")).click();
        //Scroll down by 750 pixel for the visibility of Save Button.
        ((JavascriptExecutor) driver).executeScript("scroll(0,750)");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public ProfilePage saveChanges(WebDriver driver) {
        driver.findElement(By.cssSelector("form.standard-form > p.actions > input")).click();

        return PageFactory.initElements(driver, ProfilePage.class);
    }

    public static boolean doesResponseConatins(WebDriver driver, String s) {
        //Ensure that the string 's' is present in the HTML Response.
        //For that we will first get all the responses.
        String response = driver.getPageSource();

        //Return the response in 's'.
        return response.contains(s);
    }
}
