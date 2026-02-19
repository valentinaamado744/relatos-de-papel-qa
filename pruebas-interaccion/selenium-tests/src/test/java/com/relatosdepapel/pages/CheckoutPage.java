package com.relatosdepapel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By fullNameInput = By.id("fullName");
    private By emailInput = By.id("email");
    private By phoneInput = By.id("phone");
    private By addressInput = By.id("address");
    private By cityInput = By.id("city");
    private By postalCodeInput = By.id("postalCode");

    private By cardNumberInput = By.id("cardNumber");
    private By cardNameInput = By.id("cardName");
    private By expiryDateInput = By.id("expiryDate");
    private By cvvInput = By.id("cvv");

    private By confirmButton = By.cssSelector("button[type='submit']");
    private By errorMessages = By.cssSelector(".error-message");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillCheckoutForm() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(fullNameInput))
                .sendKeys("Juan Perez");

        driver.findElement(emailInput).sendKeys("juan@test.com");
        driver.findElement(phoneInput).sendKeys("3001234567");
        driver.findElement(addressInput).sendKeys("Calle 123 #45-67");
        driver.findElement(cityInput).sendKeys("Bogotá");
        driver.findElement(postalCodeInput).sendKeys("110111");

        driver.findElement(cardNumberInput).sendKeys("1234567890123456");
        driver.findElement(cardNameInput).sendKeys("JUAN PEREZ");
        driver.findElement(expiryDateInput).sendKeys("12/30");
        driver.findElement(cvvInput).sendKeys("123");
    }

    public void confirmPurchaseAndTakeScreenshot() throws Exception {

        driver.findElement(confirmButton).click();

        wait.until(ExpectedConditions.alertIsPresent());

        String alertText = driver.switchTo().alert().getText();
        System.out.println("Popup text: " + alertText);

        driver.switchTo().alert().accept();

        wait.until(ExpectedConditions.urlContains("home"));

        File screenshot = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);

        Files.createDirectories(Paths.get("screenshots"));

        Files.copy(
                screenshot.toPath(),
                Paths.get("screenshots/compra_exitosa_"
                        + System.currentTimeMillis()
                        + ".png")
        );
    }

    // ---------- CASO NEGATIVO ----------

    public void confirmPurchaseWithoutFillingForm() {
        driver.findElement(confirmButton).click();
    }

    public int getValidationErrorsCount() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessages));
        List<?> errors = driver.findElements(errorMessages);
        return errors.size();
    }
}