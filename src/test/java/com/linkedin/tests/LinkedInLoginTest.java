package com.linkedin.tests; /**
 * Created by shreya on 3/7/16.
 */

import com.linkedin.categories.High;
import com.linkedin.categories.Low;
import com.linkedin.categories.Medium;
import com.linkedin.pageobjects.*;
import com.linkedin.util.WebUtil;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.experimental.categories.Category;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class LinkedInLoginTest {

    //Creation of WebDriver such that it is available for all @Test and @After.
    WebDriver driver;

    @Before
    public void setBrowser() {
        //Get the System Env browser. This variable will give the information about available web-browser.
        String browser = System.getenv("browser");
        if ((browser == null) && (browser.equalsIgnoreCase("Chrome"))){
            System.setProperty("webdriver.chrome.driver", "/home/shreya/WebDrivers/chromedriver");
            driver = new ChromeDriver();
        } else {
            driver = new FirefoxDriver();
        }
    }

    @Category({High.class})
    @Test
    public void linkedinSignInSignOutShouldBeSuccessful() {
        //1. Goto LinkedIn website (https://www.linkedin.com/)
        LoginPage loginPage = WebUtil.goToLoginPage(driver);

        //2. Verify Login page is displayed.
        //Below command fails if the String "Forgot password?" is not found.
        assertTrue("Validation FAILED - Text \"Forgot password?\" NOT FOUND", loginPage.doesResponseContains(driver, "Forgot password?"));

        //3. Enter login details.
        loginPage.enterEmail(driver, "testlinkedinwebsite@gmail.com");
        loginPage.enterPassword(driver, "123*****");

        //4. Click on Sign In.
        HomePage homePage = LoginPage.clickSignIn(driver);

        //5. Verify that the Sign In was successful.
        assertTrue("Validation FAILED - Text \"Home\" NOT FOUND", WebUtil.doesElementExist(driver, By.cssSelector("#main-site-nav > ul > li:nth-child(1) > a")));

        //6. LogOut of the account.
        homePage.signOut(driver);

        //7. Verify that the Logout was successful.
        //wait for the visibility of "Sign In" button before verifying for successful logout.
        WebUtil.waitForElementsToBeVisible(driver, By.cssSelector("#top-header > div > ul > li:nth-child(3) > a"));

        assertTrue("Validation FAILED - Text \"Sign Out was not successful\"", loginPage.doesResponseContains(driver, "You have signed out"));
    }

    @Category({High.class})
    @Test
    public void linkedinProfileUpdate() {
        //1. Sign In to the account.
        //Goto LinkedIn website (https://www.linkedin.com/)
        LoginPage loginPage = WebUtil.goToLoginPage(driver);
        //Enter login details.
        loginPage.enterEmail(driver, "testlinkedinwebsite@gmail.com");
        loginPage.enterPassword(driver, "123*****");
        //Click on Sign In.
        HomePage homePage = LoginPage.clickSignIn(driver);

        //2. Click on the profile tab.
        ProfilePage profilePage = homePage.clickProfileTab(driver);

        //3. Click on the Location field.
        profilePage.clickLocationField(driver);

        //4. Update the location to 'California'.
        profilePage.enterLocation(driver, "California");

        //5. Save the changes.
        profilePage.clickOnSave(driver);
        WebUtil.waitForMilliSeconds();

        //6. Verify that the profile update is reflected on the profile page.
        assertTrue("Validation FAILED - Text \"California location\" NOT FOUND", ProfilePage.doesResponseContains(driver, "California"));

        //7. Log out of the session.
        homePage.signOut(driver);
    }

    @Category({High.class})
    @Test
    public void addNewConnection() {
        //1. Sign In to the account.
        //Goto LinkedIn website (https://www.linkedin.com/)
        LoginPage loginPage = WebUtil.goToLoginPage(driver);
        //Enter login details.
        loginPage.enterEmail(driver, "testlinkedinwebsite@gmail.com");
        loginPage.enterPassword(driver, "123*****");
        //Click on Sign In.
        HomePage homePage = LoginPage.clickSignIn(driver);

        //2. Add new connection by sending an invite.
        AddConnectionPage addConnectionPage = AddConnectionPage.clickAddConnection(driver, By.cssSelector("#network-sub-nav > li:nth-child(2) > a"));

        //3. Click on 'Invite by Email' icon.
        addConnectionPage.clickOnInvite(driver, By.cssSelector("#email-origin-container > li:nth-child(6) > button"));
        WebUtil.waitForMilliSeconds();

        //4. Type in the email address that you want to send the invite to.
        addConnectionPage.enterEmailToSendInvite(driver.findElement((By.cssSelector("#invitation-list"))), "shreya.ppujari@gmail.com");

        //5. Click on Submit.
        addConnectionPage.clickOnSubmit(driver, By.cssSelector("#abook-import-form-compose-invite > fieldset > div > input"));

        //6. Verify that the invitation was sent to the recipient.
        assertTrue("Validation FAILED - Text \"Your invitation has been sent. Manage your invitations.\" NOT FOUND", WebUtil.doesElementExist(driver, By.cssSelector("#global-alert-queue > div.alert.success.animate-in")));

        //7. Log out of the session.
        //Go to Home Page before attempting sign out.
        WebUtil.click(driver, By.cssSelector("#header-navigation-main > ul > li:nth-child(1) > a"));
        homePage.signOut(driver);
    }

    @Category({Medium.class})
    @Test
    public void sendMessageToConnection() {
        //1. Sign In to the account.
        //Goto LinkedIn website (https://www.linkedin.com/)
        LoginPage loginPage = WebUtil.goToLoginPage(driver);
        //Enter login details.
        loginPage.enterEmail(driver, "testlinkedinwebsite@gmail.com");
        loginPage.enterPassword(driver, "123*****");
        //Click on Sign In.
        HomePage homePage = LoginPage.clickSignIn(driver);

        //2. Click on Messages tab.
        MessagesPage messagesPage = HomePage.clickOnMessagesTab(driver, By.cssSelector("#account-nav > ul > li:nth-child(1) > a"));
        WebUtil.waitForMilliSeconds();

        //3. Click on 'Compose button'.send
        messagesPage.clickOnCompose(driver, By.cssSelector("#compose-button"));

        //4. Add name/ select from connections for sending the message.
        messagesPage.addNameToSendMessage(driver.findElement(By.cssSelector("#pillbox-input")), "Shreya Pujari");

        //5. Add message body.
        final String messageBody = "This is a test message.";
        messagesPage.addMessageText(driver.findElement(By.cssSelector("#compose-message")), messageBody);
        WebUtil.waitForMilliSeconds();

        //6. Send the message by sending the key 'Enter'.
        messagesPage.clickOnSend(driver, By.cssSelector("#compose-region > div > form > div.compose-action-bar > div.right-actions > div.send-bar > button"));
        WebUtil.waitForMilliSeconds();

        //7. Verify that the message is sent.
        WebElement messageBodyPart = driver.findElement(By.cssSelector("#messages-list-wrapper > ul > li > div.selectable-area > div.message-info > div.other-info > div > h4 > p"));
        assertEquals("Validation FAILED - Text \" " + messageBody + "\" NOT FOUND", true, messageBodyPart.getText().contains(messageBody));

        //8. Logout of the session.
        //Go to Home Page before attempting sign out.
        WebUtil.click(driver, By.cssSelector("#header-navigation-main > ul > li:nth-child(1) > a"));
        homePage.signOut(driver);
    }

    @Category({Medium.class})
    @Test
    public void leavePageBeforeSaving() {
        //1. Sign In to the account.
        //Goto LinkedIn website (https://www.linkedin.com/)
        LoginPage loginPage = WebUtil.goToLoginPage(driver);
        //Enter login details.
        loginPage.enterEmail(driver, "testlinkedinwebsite@gmail.com");
        loginPage.enterPassword(driver, "123*****");
        //Click on Sign In.
        HomePage homePage = LoginPage.clickSignIn(driver);

        //2. Click on Messages tab.
        MessagesPage messagesPage = HomePage.clickOnMessagesTab(driver, By.cssSelector("#account-nav > ul > li:nth-child(1) > a"));
        WebUtil.waitForMilliSeconds();

        //3. Click on 'Compose button'.send
        messagesPage.clickOnCompose(driver, By.cssSelector("#compose-button"));

        //4. Add name/ select from connections for sending the message.
        messagesPage.addNameToSendMessage(driver.findElement(By.cssSelector("#pillbox-input")), "Shreya Pujari");

        //5. Add message body.
        final String messageBody = "This is a test message. Navigate from current page before sending the message";
        messagesPage.addMessageText(driver.findElement(By.cssSelector("#compose-message")), messageBody);
        WebUtil.waitForMilliSeconds();

        //6. Click on Home tab for the message box to appear.
        WebUtil.click(driver, By.cssSelector("#header-navigation-main > ul > li:nth-child(1) > a"));
        WebUtil.waitForMilliSeconds();

        //7. Verify that the Alert window is displayed.
        //Get a handle to the open alert
        final String alertText = "you want to leave";
        Alert alert = driver.switchTo().alert();
        assertEquals("Validation FAILED - Text \" " + alertText + "\" NOT FOUND", true, alert.getText().contains(alertText));

        //8. Click on 'Leave Page'.
        try {
            alert.accept();
            WebUtil.waitForMilliSeconds();
        } catch (Exception e) {
            //Nothing happens when there is no alert.
        }

        //9. Logout of the session.
        homePage.signOut(driver);
    }

    @Category({Low.class})
    @Test
    public void followACompany() {
        //1. Sign In to the account.
        //Goto LinkedIn website (https://www.linkedin.com/)
        LoginPage loginPage = WebUtil.goToLoginPage(driver);
        //Enter login details.
        loginPage.enterEmail(driver, "testlinkedinwebsite@gmail.com");
        loginPage.enterPassword(driver, "123*****");
        //Click on Sign In.
        HomePage homePage = LoginPage.clickSignIn(driver);

        //2. Search for 'LinkedIn' in the search bar
        homePage.searchForCompany(driver, driver.findElement(By.cssSelector("#main-search-box")), "LinkedIn");

        //3. Click on 'Follow' button.
        homePage.clickOnFollow(driver, By.cssSelector("#results > li.mod.result.idx0.company > div > div.srp-actions.blue-button > a"));

        //4. Verify that the company is now being followed.
        //Wait added for the actions to be synchronized.
        WebUtil.waitForMilliSeconds();
        assertEquals("Validation FAILED - Text \"Following \" NOT FOUND", true, WebUtil.findingElement(driver, By.cssSelector("#results > li.mod.result.idx0.company > div > h3 > span > span.follow-company-badge")).getText().contains("Following"));

        //5. Unfollow the company for future testing.
        homePage.clickOnUnfollow(driver, By.cssSelector("#results > li.mod.result.idx0.company > div > div.srp-actions.blue-button > div > ul > li > a"));

        //6. Verify that the company is now not being followed.
        //Wait added for the actions to be synchronized.
        WebUtil.waitForMilliSeconds();
        assertEquals("Validation FAILED - Text \" Follow \" NOT FOUND", true, WebUtil.findingElement(driver, By.cssSelector("#results > li.mod.result.idx0.company > div > div.srp-actions.blue-button > a")).getText().contains("Follow"));

        //7.Logout of the session.
        homePage.signOut(driver);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
