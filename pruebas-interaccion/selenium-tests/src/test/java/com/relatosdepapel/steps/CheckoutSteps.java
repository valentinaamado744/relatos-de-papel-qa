package com.relatosdepapel.steps;

import com.relatosdepapel.pages.*;
import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.WebDriver;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class CheckoutSteps {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private CheckoutPage checkoutPage;

    @Given("el usuario inicia sesión")
    public void login() {

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false
        ));

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        checkoutPage = new CheckoutPage(driver);

        loginPage.open();
        loginPage.login("email@test.com", "test");
    }

    @And("agrega libros al carrito")
    public void addBooks() {
        homePage.open();
        homePage.addBooksStepOne();
    }

    @When("procede al checkout")
    public void goToCheckout() {
        homePage.openCart();
        homePage.goToCheckout();
    }

    @And("completa el formulario correctamente")
    public void fillForm() throws Exception {
        checkoutPage.fillCheckoutForm();
        checkoutPage.confirmPurchaseAndTakeScreenshot();
    }

    @Then("la compra se procesa exitosamente")
    public void validateSuccess() {
        assertTrue(driver.getCurrentUrl().contains("home"));
        driver.quit();
    }

    @And("intenta confirmar sin llenar datos")
    public void confirmWithoutData() {
        checkoutPage.confirmPurchaseWithoutFillingForm();
    }

    @Then("se muestran errores de validación")
    public void validateErrors() {
        int errors = checkoutPage.getValidationErrorsCount();
        assertTrue(errors > 0);
        driver.quit();
    }
}
