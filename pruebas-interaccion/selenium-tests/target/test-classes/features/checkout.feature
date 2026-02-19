@ui
Feature: Checkout de libros

  Scenario: Compra exitosa
    Given el usuario inicia sesión
    And agrega libros al carrito
    When procede al checkout
    And completa el formulario correctamente
    Then la compra se procesa exitosamente

  Scenario: Compra sin datos
    Given el usuario inicia sesión
    And agrega libros al carrito
    When procede al checkout
    And intenta confirmar sin llenar datos
    Then se muestran errores de validación
