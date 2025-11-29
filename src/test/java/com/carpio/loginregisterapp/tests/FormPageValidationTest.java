package com.carpio.loginregisterapp.tests;

import com.carpio.loginregisterapp.pages.FormPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
public class FormPageValidationTest {

    private WebDriver driver;

    private FormPage formPage;

    @BeforeAll
    void setup() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        formPage = new FormPage(driver);
    }

    @Test
    void testFormComplete() {
        formPage.abrir();
        formPage.llenarFormulario("John", "012-3456789", "cash on delivery", "20/04/2024");
        formPage.enviar();
        String message = formPage.obtenerMensajeExito();
        assertTrue(message.contains("Thank you for validating your ticket"));
    }


    @Test
    void testContactFormBad() {
        formPage.abrir();
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            formPage.llenarFormulario("Carlos", "343-3232", "cash on delivery", "20/04/2024");
        });
        assertEquals("Número de contacto inválido. Utilice el formato XXX-XXXXXXX.", exception.getMessage());
    }

    @Test
    void testDateFormBad() {
        formPage.abrir();
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            formPage.llenarFormulario("Carlos", "012-3456789", "cash on delivery", "20/04/0000");
        });
        assertEquals("Fecha inválida. Utilice el formato dd/MM/yyyy.", exception.getMessage());
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
