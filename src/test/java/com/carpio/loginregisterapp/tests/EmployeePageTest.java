package com.carpio.loginregisterapp.tests;

import com.carpio.loginregisterapp.pages.EmployeePage;
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

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class EmployeePageTest {

    private WebDriver driver;

    private WebDriverWait wait;

    private EmployeePage employeePage;

    private String validUsername = "admin";

    private String validPassword = "admin123";

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        employeePage = new EmployeePage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    void loginWithInvalidCredentials() {
        employeePage.open();
        employeePage.login("invalidUser", "invalidPass");
        String message = employeePage.getMessage();
        assertTrue(message.contains("Invalid login attempt."));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
