package com.relatosdepapel.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By emailInput = By.id("email");
    private By passwordInput = By.cssSelector("input[type='password']");
    private By loginButton = By.xpath("//button[contains(text(),'INICIAR SESIÓN')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void open() {
        driver.get("http://localhost:5173/login");
    }

    public void login(String email, String password) {

        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();

        wait.until(ExpectedConditions.urlContains("home"));
    }
}