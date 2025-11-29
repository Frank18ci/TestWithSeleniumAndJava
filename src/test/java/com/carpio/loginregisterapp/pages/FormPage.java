package com.carpio.loginregisterapp.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class FormPage {
    private WebDriver webDriver;
    private WebDriverWait wait;
    String regex = "^[0-9]{3}-[0-9]{7}$";
    Pattern pattern = Pattern.compile(regex);

    @FindBy(id = "validationCustom01")
    private WebElement firstName;

    @FindBy(xpath = "//input[@type='tel']")
    private WebElement contactNumber;

    @FindBy(id = "validationCustom04")
    private WebElement paymentMethod;

    @FindBy(xpath = "//input[@type='date']")
    private WebElement datepicker;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;


    public FormPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        PageFactory.initElements(webDriver, this);
    }

    public void abrir() {
        webDriver.get("https://practice.expandtesting.com/form-validation");
    }

    public void llenarFormulario(String name, String contact, String payment, String date) {
        try {
            LocalDate.parse(date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        } catch (DateTimeParseException e){
            System.out.println("Fecha inválida. Utilice el formato dd/MM/yyyy.");
            throw new IllegalArgumentException("Fecha inválida. Utilice el formato dd/MM/yyyy.");
        }

        wait.until(ExpectedConditions.visibilityOf(firstName)).sendKeys(name);
        if(pattern.matcher(contact).matches()) {
            contactNumber.sendKeys(contact);
        } else {
            System.out.println("Número de contacto inválido. Utilice el formato XXX-XXXXXXX.");
            throw new IllegalArgumentException("Número de contacto inválido. Utilice el formato XXX-XXXXXXX.");
        }
        Select select = new Select(paymentMethod);
        select.selectByVisibleText(payment);
        datepicker.sendKeys(date);
    }
    public void enviar() {
        wait.until(ExpectedConditions.elementToBeClickable(submitButton)).click();
    }
    public String obtenerMensajeExito() {
        WebElement alert = wait.until(ExpectedConditions.visibilityOfElementLocated(new By.ByClassName("alert-info")));
        return alert.getText();
    }
}
