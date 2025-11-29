package com.carpio.loginregisterapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private final WebDriver webDriver;
    private final WebDriverWait wait; // nuevo: esperas explícitas

    private final String url = "https://practice.expandtesting.com/login";

    private final By usernameInput = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.xpath("//button[@type='submit']");
    private final By message = By.id("flash");

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public void open() {
        webDriver.get(url);
        // Esperar campo usuario para confirmar carga de página
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
    }

    public void login(String username, String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput)).clear();
        webDriver.findElement(usernameInput).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).clear();
        webDriver.findElement(passwordInput).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    public String getMessage() {
        // Esperar que el flash aparezca/sea visible
        return wait.until(ExpectedConditions.visibilityOfElementLocated(message)).getText().trim();
    }
}
