package com.relatosdepapel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.List;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By addToCartButtons = By.cssSelector(".book-card-cart-button");
    private By cartButton = By.cssSelector(".cart-button");
    private By proceedToCheckoutButton = By.xpath("//button[contains(text(),'Proceder al pago')]");
    private By quantityInputs = By.cssSelector(".quantity-input");
    private By bookCard = By.cssSelector(".book-card");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("http://localhost:5173/home");
    }

    public void addBooksStepOne() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(bookCard));

        List<WebElement> buttons = driver.findElements(addToCartButtons);

        buttons.get(0).click();
        closeCart();

        buttons.get(1).click();
        closeCart();

        buttons.get(2).click();
        closeCart();

        buttons.get(2).click();
        closeCart();
    }

    public void openCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(quantityInputs));
    }

    public int getTotalUnitsInCart() {

        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(quantityInputs, 0));

        int totalUnits = 0;

        for (WebElement input : driver.findElements(quantityInputs)) {
            totalUnits += Integer.parseInt(input.getAttribute("value"));
        }

        return totalUnits;
    }

    public void goToCheckout() {

        wait.until(ExpectedConditions.visibilityOfElementLocated(proceedToCheckoutButton));

        WebElement checkoutBtn = driver.findElement(proceedToCheckoutButton);

        System.out.println("Antes del click URL: " + driver.getCurrentUrl());

        checkoutBtn.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Después del click URL: " + driver.getCurrentUrl());
    }


    private void closeCart() {
        By cartCloseButton = By.cssSelector(".cart-close-button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartCloseButton)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(cartCloseButton));
    }
}
