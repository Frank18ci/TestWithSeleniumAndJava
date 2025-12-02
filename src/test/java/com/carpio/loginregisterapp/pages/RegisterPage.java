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
    }

    public void register(String username, String password, String confirmPassword) {
        WebElement button = webDriver.findElement(registerButton);
        WebElement usernameInputElement = webDriver.findElement(usernameInput);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", button);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(usernameInputElement));

        try{
            webDriver.findElement(usernameInput).sendKeys(username);
            Thread.sleep(1000);
            webDriver.findElement(passwordInput).sendKeys(password);
            Thread.sleep(1000);
            webDriver.findElement(confirmPasswordInput).sendKeys(confirmPassword);
            Thread.sleep(1000);
            button.click();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String getMessage() {
        return webDriver.findElement(message).getText();
    }
}
