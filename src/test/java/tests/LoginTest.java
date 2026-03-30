package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

/**
 * Test class for Login functionality
 */
public class LoginTest extends BaseTest {

    @Test(priority = 1, groups = {"smoke", "regression"}, description = "Verify successful login with valid credentials")
    public void testSuccessfulLogin() {
        navigateToBaseUrl();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("testuser", "testpass123");
        
        Assert.assertTrue(loginPage.isLoggedIn(), "User should be logged in");
        String successMsg = loginPage.getSuccessMessage();
        Assert.assertTrue(successMsg.contains("success"), "Success message should be displayed");
    }

    @Test(priority = 2, groups = {"regression"}, description = "Verify login fails with invalid credentials")
    public void testLoginWithInvalidCredentials() {
        navigateToBaseUrl();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("invaliduser", "wrongpass");
        
        Assert.assertFalse(loginPage.isLoggedIn(), "User should not be logged in");
        String errorMsg = loginPage.getErrorMessage();
        Assert.assertTrue(errorMsg.contains("invalid") || errorMsg.contains("error"), 
                "Error message should be displayed");
    }

    @Test(priority = 3, groups = {"regression"}, description = "Verify login fails with empty username")
    public void testLoginWithEmptyUsername() {
        navigateToBaseUrl();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "testpass123");
        
        Assert.assertFalse(loginPage.isLoggedIn(), "User should not be logged in with empty username");
    }

    @Test(priority = 4, groups = {"regression"}, description = "Verify login fails with empty password")
    public void testLoginWithEmptyPassword() {
        navigateToBaseUrl();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("testuser", "");
        
        Assert.assertFalse(loginPage.isLoggedIn(), "User should not be logged in with empty password");
    }

    @Test(priority = 5, groups = {"smoke"}, description = "Verify logout functionality")
    public void testLogout() {
        navigateToBaseUrl();
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("testuser", "testpass123");
        
        Assert.assertTrue(loginPage.isLoggedIn(), "User should be logged in");
        
        loginPage.logout();
        Assert.assertFalse(loginPage.isLoggedIn(), "User should be logged out");
    }
}
