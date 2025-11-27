package com.carpio.loginregisterapp.tests;

import com.carpio.loginregisterapp.pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class LoginPageTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private String validUsername = "practice";
    private String validPassword = "SuperSecretPassword!";

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Test
    void loginWithInvalidCredentials() {
        loginPage.open();
        loginPage.login("invalidUser1", "invalidPass");
        String message = loginPage.getMessage();
        assertTrue(message.contains("Your username is invalid!"));
    }

    @Test
    void loginWithValidCredentials() {
        loginPage.open();
        loginPage.login(validUsername, validPassword);
        String message = loginPage.getMessage();
        assertTrue(message.contains("You logged into a secure area!"));
    }

    @Test
    void loginWithInvalidPassword() {
        loginPage.open();
        loginPage.login(validUsername, "wrongPassword");
        String message = loginPage.getMessage();
        assertTrue(message.contains("Your password is invalid!"));
    }
    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
