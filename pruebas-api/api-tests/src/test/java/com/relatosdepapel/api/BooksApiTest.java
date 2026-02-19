package com.relatosdepapel.api;

import com.relatosdepapel.utils.EvidenceManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Tag("api")
@DisplayName("Books API - CRUD Endpoints Validation")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BooksApiTest {

    private static Long createdBookId;
    private static String dynamicIsbn;

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8081;
        dynamicIsbn = "ISBN" + System.currentTimeMillis();
    }

    @Test
    @Order(1)
    @DisplayName("API - Crear libro exitosamente")
    void shouldCreateBook(TestInfo testInfo) {

        String body = """
                {
                  "title": "Libro Test Automatizado",
                  "author": "QA Master",
                  "isbn": "%s",
                  "price": 40000.0,
                  "stock": 20,
                  "category": "Testing",
                  "publicationDate": "2024-01-01",
                  "rating": 5,
                  "visible": true,
                  "coverImageUrl": null
                }
                """.formatted(dynamicIsbn);

        Response response =
                given()
                        .contentType("application/json")
                        .body(body)
                .when()
                        .post("/api/books")
                .then()
                        .log().all()
                        .statusCode(201)
                        .extract()
                        .response();

        createdBookId = response.jsonPath().getLong("id");

        String evidence =
                "REQUEST:\nPOST /api/books\n\n" +
                "BODY:\n" + body + "\n\n" +
                "RESPONSE STATUS:\n" + response.statusCode() + "\n\n" +
                "RESPONSE BODY:\n" + response.asPrettyString();

        EvidenceManager.saveEvidence(testInfo.getDisplayName(), evidence);

        Assertions.assertNotNull(createdBookId);
    }

    @Test
    @Order(2)
    @DisplayName("API - Obtener libro creado")
    void shouldGetCreatedBook(TestInfo testInfo) {

        Response response =
                given()
                .when()
                    .get("/api/books/" + createdBookId)
                .then()
                    .log().all()
                    .statusCode(200)
                    .body("title", equalTo("Libro Test Automatizado"))
                    .extract()
                    .response();

        String evidence =
                "REQUEST:\nGET /api/books/" + createdBookId + "\n\n" +
                "RESPONSE STATUS:\n" + response.statusCode() + "\n\n" +
                "RESPONSE BODY:\n" + response.asPrettyString();

        EvidenceManager.saveEvidence(testInfo.getDisplayName(), evidence);
    }

    @Test
    @Order(3)
    @DisplayName("API - Actualizar libro existente")
    void shouldUpdateBook(TestInfo testInfo) {

        String updatedIsbn = "ISBN" + System.currentTimeMillis();

        String updatedBody = """
                {
                  "title": "Libro Test Actualizado",
                  "author": "QA Master",
                  "isbn": "%s",
                  "price": 50000.0,
                  "stock": 10,
                  "category": "Testing",
                  "publicationDate": "2024-02-01",
                  "rating": 4,
                  "visible": true,
                  "coverImageUrl": null
                }
                """.formatted(updatedIsbn);

        Response response =
                given()
                        .contentType("application/json")
                        .body(updatedBody)
                .when()
                        .put("/api/books/" + createdBookId)
                .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .response();

        String evidence =
                "REQUEST:\nPUT /api/books/" + createdBookId + "\n\n" +
                "BODY:\n" + updatedBody + "\n\n" +
                "RESPONSE STATUS:\n" + response.statusCode() + "\n\n" +
                "RESPONSE BODY:\n" + response.asPrettyString();

        EvidenceManager.saveEvidence(testInfo.getDisplayName(), evidence);
    }

    @Test
    @Order(4)
    @DisplayName("API - Eliminar libro")
    void shouldDeleteBook(TestInfo testInfo) {

        Response response =
                given()
                .when()
                    .delete("/api/books/" + createdBookId)
                .then()
                    .log().all()
                    .statusCode(204)
                    .extract()
                    .response();

        String evidence =
                "REQUEST:\nDELETE /api/books/" + createdBookId + "\n\n" +
                "RESPONSE STATUS:\n" + response.statusCode();

        EvidenceManager.saveEvidence(testInfo.getDisplayName(), evidence);
    }

    @Test
    @Order(5)
    @DisplayName("API - Validar que el libro eliminado no exista")
    void shouldNotFindDeletedBook(TestInfo testInfo) {

        Response response =
                given()
                .when()
                    .get("/api/books/" + createdBookId)
                .then()
                    .log().all()
                    .statusCode(anyOf(is(400), is(404)))
                    .extract()
                    .response();

        String evidence =
                "REQUEST:\nGET /api/books/" + createdBookId + "\n\n" +
                "RESPONSE STATUS:\n" + response.statusCode() + "\n\n" +
                "RESPONSE BODY:\n" + response.asPrettyString();

        EvidenceManager.saveEvidence(testInfo.getDisplayName(), evidence);
    }
}