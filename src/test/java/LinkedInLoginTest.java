/**
 * Created by shreya on 3/7/16.
 */

import com.linkedin.pageobjects.*;
import com.linkedin.util.WebUtil;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class LinkedInLoginTest {

    WebDriver driver = new FirefoxDriver(); // Creation of WebDriver


    @Test
    public void linkedinSignInShouldBeSuccessful() throws InterruptedException {

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

        //6. Verify that the profile update is reflected on the profile page.
        assertTrue("Validation FAILED - Text \"California location\" NOT FOUND", ProfilePage.doesResponseContains(driver, "California"));

        //7. Log out of the session.
        homePage.signOut(driver);
    }


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
        AddConnectionPage addConnectionPage = AddConnectionPage.clickAddConnection(driver,  By.cssSelector("#network-sub-nav > li:nth-child(2) > a"));

        //3. Click on 'Invite by Email' icon.
        addConnectionPage.clickOnInvite(driver, By.cssSelector("#email-origin-container > li:nth-child(6) > button"));
        WebUtil.waitForPageRefresh();

        //4. Type in the email address that you want to send the invite to.
        addConnectionPage.enterEmailToSendInvite(driver.findElement((By.cssSelector("#invitation-list"))), "shreya.ppujari@gmail.com");

        //5. Click on Submit.
        addConnectionPage.clickOnSubmit(driver, By.cssSelector("#abook-import-form-compose-invite > fieldset > div > input"));
        WebUtil.waitForPageRefresh();

        //6. Verify that the invitation was sent to the recipient.
        assertTrue("Validation FAILED - Text \"Your invitation has been sent. Manage your invitations.\" NOT FOUND", WebUtil.doesElementExist(driver, By.cssSelector("#global-alert-queue > div.alert.success.animate-in")));

        //7. Log out of the session.
        homePage.signOut(driver);
    }

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
        WebUtil.waitForPageRefresh();

        //3. Click on 'Compose button'.
        messagesPage.clickOnCompose(driver, By.cssSelector("#compose-button"));

        //4. Add name/ select from connections for sending the message.
        messagesPage.addNameToSendMessage(driver.findElement(By.cssSelector("#pillbox-input")), "Shreya Pujari");
        //Wait for few seconds for the display of existing connections name.
        WebUtil.waitForPageRefresh();
        //Select 'Enter keyboard input method'.
        driver.findElement(By.cssSelector("#pillbox-input")).sendKeys(Keys.RETURN);

        //5. Add message body.
        final String messageBody = "This is a test message.";
        messagesPage.addMessageText(driver.findElement(By.cssSelector("#compose-message")), messageBody);
        WebUtil.waitForPageRefresh();

        //6. Send the message by sending the key 'Enter'.
        messagesPage.clickOnSend(driver, By.cssSelector("#compose-region > div > form > div.compose-action-bar > div.right-actions > div.send-bar > button"));
        WebUtil.waitForPageRefresh();

        //7. Verify that the message is sent.
        WebElement messageBodyPart = driver.findElement(By.cssSelector("#messages-list-wrapper > ul > li > div.selectable-area > div.message-info > div.other-info > div > h4 > p"));
        assertEquals("Validation FAILED - Text \" " + messageBody + "\" NOT FOUND", true, messageBodyPart.getText().contains(messageBody));

        //8. Logout of the session.
        homePage.signOut(driver);
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
