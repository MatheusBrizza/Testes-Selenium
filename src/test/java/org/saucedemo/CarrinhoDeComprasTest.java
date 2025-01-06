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

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarrinhoDeComprasTest {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mathe\\OneDrive\\Área de Trabalho\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
        realizarLogin("standard_user", "secret_sauce");
    }

    @AfterEach
    public void fecharDriver() {
        driver.quit();
    }

    @Test
    @Disabled
    public void adicionarUmItemCarrinhoPelaPaginaInicial() {
        WebElement addCartButton = driver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
        addCartButton.click();
        clicarIconeCarrinhoCompras();
        WebElement cartItemTitle = driver.findElement(By.cssSelector(".inventory_item_name"));
        assertEquals("Sauce Labs Backpack", cartItemTitle.getText(), "O item no carrinho não é o esperado!");
    }

    @Test
    public void adicionarUmItemCarrinhoPelaPaginaDoItem() {
        WebElement itemTitle = driver.findElement(By.id("item_4_title_link"));
        itemTitle.click();
        String currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains("inventory-item.html?id=4"), "Não entrou na tela da mochila.");
        WebElement addCartButton = driver.findElement(By.id("add-to-cart"));
        addCartButton.click();
        clicarIconeCarrinhoCompras();
        WebElement cartItemTitle = driver.findElement(By.cssSelector(".inventory_item_name"));
        assertEquals("Sauce Labs Backpack", cartItemTitle.getText(), "O item no carrinho não é o esperado!");
    }



    public void realizarLogin(String username, String password) {
        WebElement usernameField = driver.findElement(By.id("user-name"));
        usernameField.sendKeys(username);

        WebElement passwordField = driver.findElement(By.id("password"));
        passwordField.sendKeys(password);

        WebElement loginButton = driver.findElement(By.id("login-button"));
        loginButton.click();
    }

    public void clicarIconeCarrinhoCompras() {
        WebElement cartIcon = driver.findElement(By.cssSelector(".shopping_cart_link"));
        cartIcon.click();
    }
}
