package com.nopcommerce.demo.testsuite;

import com.nopcommerce.demo.customlisteners.CustomListeners;
import com.nopcommerce.demo.pages.HomePage;
import com.nopcommerce.demo.pages.LoginPage;
import com.nopcommerce.demo.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListeners.class)
public class LoginPageTest extends BaseTest {

    HomePage homePage;
    LoginPage loginPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt() {
        homePage = new HomePage();
        loginPage = new LoginPage();

    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void verifyUserShouldNavigateToLoginPage() {

        //Click on login link
        homePage.clickOnLoginLink();

        //verify that "Welcome, Please Sign In!" message display
        String expectedMessage = "Welcome, Please Sign In!";
        String actualMessage = loginPage.getWelcomeText();
        Assert.assertEquals(expectedMessage, actualMessage, "Login page not displayed");
    }

    @Test
    public void verifyErrorMessageWithInvalidCredentials() {

        //Click on login link
        homePage.clickOnLoginLink();

        //Enter EmailId
        loginPage.enterEmailId("prime123@gmail.com");

        //Enter Password
        loginPage.enterPassword("prime123");

        //Click on Login Button
        loginPage.clickOnLoginButton();

        // Verify that the Error message
        String expectedErrorMessage = "Login was unsuccessful. Please correct the errors and try again.\n"
                + "No customer account found";
        String actualErrorMessage = loginPage.getErrorMessage();
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage, "Error message not displayed");
    }

    @Test
    public void verifyThatUserShouldLogInSuccessFullyWithValidCredentials() {

        //Click on login link
        homePage.clickOnLoginLink();

        //Enter EmailId
        loginPage.enterEmailId("nsmith@test.com");

        //Enter Password
        loginPage.enterPassword("Pen2paper");

        //Click on Login Button
        loginPage.clickOnLoginButton();

        //Verify that  LogOut link  is display
        String expectedMessage = "Log out";
        String actualMessage = loginPage.getLogoutText();
        Assert.assertEquals(actualMessage,expectedMessage,"Logout is not displayed");
    }

    @Test(groups = {"sanity", "smoke", "regression"})
    public void verifyThatUserShouldLogOutSuccessFully() {

        //Click on login link
        homePage.clickOnLoginLink();

        //Enter EmailId
        loginPage.enterEmailId("nsmith@test.com");

        //Enter Password
        loginPage.enterPassword("Pen2paper");

        //Click on Login Button
        loginPage.clickOnLoginButton();

        //Click on LogOut Link
        loginPage.clickOnLogout();

        // Verify that LogIn Link Display
        String expectedMessage = "Log in";
        String actualText = homePage.getLoginText();
        Assert.assertEquals(actualText,expectedMessage,"Login is not displayed");
    }

}
