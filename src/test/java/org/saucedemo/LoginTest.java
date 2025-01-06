package org.saucedemo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class LoginTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mathe\\OneDrive\\Área de Trabalho\\chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }

    @AfterEach
    public void fecharDriver() {
        driver.quit();
    }

    @Test
    @Disabled
    public void loginCredenciaisValidasTest() {
        realizarLogin("standard_user", "secret_sauce");
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("inventory.html"), "Erro no login.");
    }

    @Test
    @Disabled
    public void loginCredenciaisErradas() {
        realizarLogin("abc", "senha");
        WebDriverWait espera = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement mensagemErro = espera.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".error-message-container")));
        assertTrue(mensagemErro.isDisplayed(), "A mensagem de erro não foi exibida!");

        String textoEsperado = "Epic sadface: Username and password do not match any user in this service";
        String textoAtual = mensagemErro.getText();
        assertEquals(textoEsperado, textoAtual, "A mensagem de erro não corresponde ao esperado!");
    }

    @Test
    @Disabled
    public void loginCredenciaisCaixaAlta() {
        realizarLogin("STANDARD_USER", "SECRET_SAUCE");
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("inventory.html"), "Erro no login.");
    }


    public void realizarLogin(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
    }

}
