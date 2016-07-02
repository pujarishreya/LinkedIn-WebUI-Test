/**
 * Created by shreya on 3/7/16.
 */

import com.linkedin.pageobjects.HomePage;
import com.linkedin.pageobjects.LoginPage;
import com.linkedin.pageobjects.ProfilePage;
import com.linkedin.util.WebUtil;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LinkedInLoginTest {

    WebDriver driver = new FirefoxDriver(); // Creation of WebDriver


    @Test
    public void linkedinSignInShouldBeSuccessful() throws InterruptedException {

        //1. Goto LinkedIn website (https://www.linkedin.com/)
        LoginPage loginPage = WebUtil.goToLoginPage(driver);

        //2. Verify Login page is displayed.
        //Below command fails if the String "Forgot password?" is not found.
        assertTrue("Validation FAILED - Text \"Forgot password?\" NOT FOUND", loginPage.doesResponseContains(driver, "Forgot password?"));

        //3. Enter email and password.
        //Enter email.
        LoginPage.enterEmail(driver, "testlinkedinwebsite@gmail.com");
        //Enter the password.
        loginPage.enterPassword(driver, "123*****");

        //4. Click on Sign In.
        HomePage homePage = LoginPage.clickSignIn(driver);

        //5. Verify that the Sign In was successful.
        assertTrue("Validation FAILED - Text \"Home\" NOT FOUND", homePage.doesHomeTabExist(driver));

        //6. LogOut of the account.
        loginPage = homePage.signOut(driver);

        //7. Verify that the Logout was successful.
        //wait for the visibility of "Sign In" button before verifying for successful logout.
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#top-header > div > ul > li:nth-child(3) > a")));
        assertTrue("Validation FAILED - Text \"Sign Out was not successful\"", LoginPage.doesResponseContains(driver, "You have signed out"));
    }

    @Test
    public void linkedinProfileUpdate() {
        //1. Sign In to the account.
        //Goto LinkedIn website (https://www.linkedin.com/)
        LoginPage loginPage = WebUtil.goToLoginPage(driver);
        //Enter email.
        LoginPage.enterEmail(driver, "testlinkedinwebsite@gmail.com");
        //Enter the password.
        loginPage.enterPassword(driver, "123*****");
        //Click on Sign In.
        HomePage homePage = LoginPage.clickSignIn(driver);

        //2. Click on the profile tab.
        ProfilePage profilePage = HomePage.clickProfileTab(driver);

        //3. Update the location of most recent experience.
        profilePage.clickLocationField(driver);

        //4. Save the changes.
        profilePage.saveChanges(driver);

        //5. Verify that the profile update is reflected on the profile page.
        assertTrue("Validation FAILED - Text \"California location\" NOT FOUND", ProfilePage.doesResponseConatins(driver, "California"));

        //Wait for the page to get refreshed before logout is attempted.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //6. Log out of the session.
        homePage.signOut(driver);

    }

    @After
    public void tearDown() {
        driver.close();
    }
}
