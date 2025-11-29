package com.carpio.loginregisterapp.tests;

import com.carpio.loginregisterapp.pages.RegisterPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class RegisterPageTest {

    private WebDriver driver;

    private WebDriverWait wait;

    private RegisterPage registerPage;

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        registerPage = new RegisterPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    void registerSuccess() {
        registerPage.open();
        registerPage.register("newuser12332423w3", "Password!23", "Password!23");

        String message = registerPage.getMessage();
        assert message.contains("Successfully registered, you can log in now.");
    }

    @Test
    void registerWithInvalidUser() {
        registerPage.open();
        registerPage.register("us", "pass", "pass");
        String message = registerPage.getMessage();
        assert message.contains("Username must be at least 3 characters long.");
    }

    @Test
    void registerWithDifferentPasswords() {
        registerPage.open();
        registerPage.register("validuser", "Password!23", "DifferentPass!23");
        String message = registerPage.getMessage();
        assert message.contains("Passwords do not match.");
    }

    @Test
    void registerWithShortPassword() {
        registerPage.open();
        registerPage.register("validuser", "12", "12");
        String message = registerPage.getMessage();
        assert message.contains("Password must be at least 4 characters long.");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
