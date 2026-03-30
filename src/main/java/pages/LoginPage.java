package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Login Page Object containing elements and methods for login functionality
 */
public class LoginPage extends BasePage {

    @FindBy(id = "username")
    private WebElement usernameField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = ".error-message")
    private WebElement errorMessage;

    @FindBy(css = ".success-message")
    private WebElement successMessage;

    @FindBy(id = "logout")
    private WebElement logoutButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public void clickLoginButton() {
        click(loginButton);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }

    public boolean isLoggedIn() {
        return isDisplayed(logoutButton);
    }

    public void logout() {
        if (isLoggedIn()) {
            click(logoutButton);
        }
    }
}
