package com.carpio.loginregisterapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    private final WebDriver webDriver;

    private final WebDriverWait webDriverWait;

    private final String url = "https://practice.expandtesting.com/register";

    private final By usernameInput = By.id("username");

    private final By passwordInput = By.id("password");

    private final By confirmPasswordInput = By.id("confirmPassword");

    private final By registerButton = By.xpath("//button[@type='submit']");

    private final By message = By.id("flash");

    public RegisterPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
    }

    public void open() {
        webDriver.get(url);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
    }

    public void register(String username, String password, String confirmPassword) {
        WebElement button = webDriver.findElement(registerButton);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", button);

        WebElement userEl = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        userEl.clear();
        userEl.sendKeys(username);

        WebElement passEl = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        passEl.clear();
        passEl.sendKeys(password);

        WebElement confirmEl = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(confirmPasswordInput));
        confirmEl.clear();
        confirmEl.sendKeys(confirmPassword);

        webDriverWait.until(ExpectedConditions.elementToBeClickable(button)).click();
    }

    public String getMessage() {
        return webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(message)).getText().trim();
    }
}
