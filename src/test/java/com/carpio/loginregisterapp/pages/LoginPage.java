package com.carpio.loginregisterapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver webDriver;

    private final String url = "https://practice.expandtesting.com/login";

    private final By usernameInput = By.id("username");

    private final By passwordInput = By.id("password");

    private final By loginButton = By.xpath("//button[@type='submit']");

    private final By message = By.id("flash");

    public LoginPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void open() {
        webDriver.get(url);
    }

    public void login(String username, String password) {
        webDriver.findElement(usernameInput).sendKeys(username);
        webDriver.findElement(passwordInput).sendKeys(password);
        webDriver.findElement(loginButton).click();
    }

    public String getMessage() {
        return webDriver.findElement(message).getText();
    }
}
