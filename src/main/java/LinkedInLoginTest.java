/**
 * Created by shreya on 3/7/16.
 */

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LinkedInLoginTest {

    WebDriver driver = new FirefoxDriver(); // Creation of WebDriver


    @Test
    public void linkedinSignInShouldBeSuccessful() throws InterruptedException {

        //1. Goto LinkedIn website (https://www.linkedin.com/)
        driver.get("https://www.linkedin.com"); // Opening LinkedIn Website

        //2. Verify Login page is displayed.
        //Ensure that the string "Forgot password?" is present in the HTML Response.
        //For that we will first get all the responses.
        String response = driver.getPageSource();
        //Below command fails if the String "Forgot password?" is not found.
        assertTrue("Validation FAILED - Text \"Forgot password?\" NOT FOUND", response.contains("Forgot password?"));

        //3. Enter email and password.
        //Creating WebElement emailTextBox, using findElement to find the element with id = "login-email"
        //in the HTML Document structure of LinkedIn.
        WebElement loginTextBox = driver.findElement(By.id("login-email"));
        //Clear the login-email box to ensure that no text is present
        loginTextBox.clear();
        //Enter the email in login box.
        loginTextBox.sendKeys("testlinkedinwebsite@gmail.com");
        //Creating WebElement passwordTextBox, using findElement to find the element with id = "login-password"
        //in the HTML Document structure of LinkedIn.
        WebElement passwordTextBox = driver.findElement(By.id("login-password"));
        //Clear the login-password box to ensure that no text is present.
        passwordTextBox.clear();
        //Enter the password in login-password box.
        passwordTextBox.sendKeys("123*****");

        //4. Click on Sign In.
        //Now find the Sign In button using the locator -> name.
        WebElement signInButton = driver.findElement(By.name("submit"));
        signInButton.click();
        //Maximize the window.
        driver.manage().window().maximize();

        //5. Verify that the Sign In was successful.
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Home")));
        assertTrue("Validation FAILED - Text \"Home\" NOT FOUND", (driver.findElement(By.cssSelector("#main-site-nav > ul > li:nth-child(1) > a")).isEnabled()));

        //6. LogOut of the account.
        WebElement profileButton = driver.findElement(By.cssSelector("#account-nav > ul > li.nav-item.account-settings-tab > a > img"));
        profileButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#account-sub-nav > div > div.account-sub-nav-body > ul > li.self > div > span > span.act-set-action > a")));
        WebElement signOutButton = driver.findElement(By.cssSelector("#account-sub-nav > div > div.account-sub-nav-body > ul > li.self > div > span > span.act-set-action > a"));
        signOutButton.click();

        //wait for the visibility of "Sign In" button before verifying for successful logout.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#top-header > div > ul > li:nth-child(3) > a")));

        //7. Verify that the Logout was successful.
        String responseSignOut = driver.getPageSource();
        assertTrue("Validation FAILED - Text \"Sign Out was not successful\"", responseSignOut.contains("You have signed out"));
    }

    @Test
    public void linkedinProfileUpdate() {
        //1. Sign In to the account.
        //1.a) Goto LinkedIn website (https://www.linkedin.com/)
        driver.get("https://www.linkedin.com"); // Opening LinkedIn Website
        //1.b) Verify Login page is displayed.
        //Ensure that the string "Forgot password?" is present in the HTML Response.
        //For that we will first get all the responses.
        String response = driver.getPageSource();
        //Below command fails if the String "Forgot password?" is not found.
        assertTrue("Validation FAILED - Text \"Forgot password?\" NOT FOUND", response.contains("Forgot password?"));
        //1.c) Enter email and password.
        //Creating WebElement emailTextBox, using findElement to find the element with id = "login-email"
        //in the HTML Document structure of LinkedIn.
        WebElement loginTextBox = driver.findElement(By.id("login-email"));
        //Clear the login-email box to ensure that no text is present
        loginTextBox.clear();
        //Enter the email in login box.
        loginTextBox.sendKeys("testlinkedinwebsite@gmail.com");
        //Creating WebElement passwordTextBox, using findElement to find the element with id = "login-password"
        //in the HTML Document structure of LinkedIn.
        WebElement passwordTextBox = driver.findElement(By.id("login-password"));
        //Clear the login-password box to ensure that no text is present.
        passwordTextBox.clear();
        //Enter the password in login-password box.
        passwordTextBox.sendKeys("123*****");
        //1.d) Click on Sign In.
        //Now find the Sign In button using the locator -> name.
        WebElement signInButton = driver.findElement(By.name("submit"));
        signInButton.click();
        //Maximize the window.
        driver.manage().window().maximize();
        //1.e) Verify that the Sign In was successful.
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Home")));
        assertTrue("Validation FAILED - Text \"Home\" NOT FOUND", (driver.findElement(By.cssSelector("#main-site-nav > ul > li:nth-child(1) > a")).isEnabled()));

        //2. Click on the profile tab.
        driver.findElement(By.cssSelector("#main-site-nav > ul > li.nav-item:nth-child(2) > a")).click();

        //3. Update the location of most recent experience.
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

        //4. Save the changes.
        driver.findElement(By.cssSelector("form.standard-form > p.actions > input")).click();

        //5. Verify that the profile update is reflected on the profile page.
        assertTrue("Validation FAILED - Text \"California location\" NOT FOUND", driver.getPageSource().contains("California"));
        //Wait for the page to get refreshed before verification is done.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //6. Log out of the session.
        WebElement profileButton = driver.findElement(By.cssSelector("#account-nav > ul > li.nav-item.account-settings-tab > a > img"));
        profileButton.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#account-sub-nav > div > div.account-sub-nav-body > ul > li.self > div > span > span.act-set-action > a")));
        WebElement signOutButton = driver.findElement(By.cssSelector("#account-sub-nav > div > div.account-sub-nav-body > ul > li.self > div > span > span.act-set-action > a"));
        signOutButton.click();
        //wait for the visibility of "Sign In" button before verifying for successful logout.
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#top-header > div > ul > li:nth-child(3) > a")));
        //Verify that the Logout was successful.
        String responseSignOut = driver.getPageSource();
        assertTrue("Validation FAILED - Text \"Sign Out was not successful\"", responseSignOut.contains("You have signed out"));
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
