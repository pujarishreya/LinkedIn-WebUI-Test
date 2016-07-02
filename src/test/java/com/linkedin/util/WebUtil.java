package com.linkedin.util;

import com.linkedin.pageobjects.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by shreya on 3/10/16.
 */
public class WebUtil {
    public static LoginPage goToLoginPage(WebDriver driver) {
        driver.get("https://www.linkedin.com"); // Opening LinkedIn Website

        return PageFactory.initElements(driver, LoginPage.class);
    }
}
