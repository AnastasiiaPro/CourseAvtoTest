package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import ru.netology.data.Api;
import ru.netology.data.SQL;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.data.SQL.getPayments;

public class ApiTest {
    private static List<SQL.PaymentEntity> payment;
    private static List<SQL.CreditRequestEntity> credit;
    private static List<SQL.OrderEntity> order;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide().screenshots(true)
                .savePageSource(true));
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterEach
    void clear() {
        SQL.clear();
    }

    private RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Test
    public void shouldApprovedPaymentCard() {
        String status = given()
                .spec(requestSpec)
                .body(Api.getApiApprovedCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200)
                .extract()
                .path("status");
        payment = getPayments();
        credit = SQL.getCreditsRequest();
        order = SQL.getOrders();
        assertEquals(1, payment.size());
        assertEquals(0, credit.size());
        assertEquals(1, order.size());
        assertEquals("APPROVED", status);
        assertTrue(payment.get(0).getStatus().equalsIgnoreCase("APPROVED"));
        assertEquals(payment.get(0).getTransaction_id(), order.get(0).getPayment_id());
        assertNull(order.get(0).getCredit_id());
    }

    @Test
    public void shouldDeclinedPaymentCard() {
        String status = given()
                .spec(requestSpec)
                .body(Api.getApiDeclinedCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200)
                .extract()
                .path("status");

        payment = getPayments();
        credit = SQL.getCreditsRequest();
        order = SQL.getOrders();
        assertEquals(1, payment.size());
        assertEquals(0, credit.size());
        assertEquals(1, order.size());
        assertEquals("DECLINED", status);
        assertTrue(payment.get(0).getStatus().equalsIgnoreCase("DECLINED"));
        assertEquals(payment.get(0).getTransaction_id(), order.get(0).getPayment_id());
        assertNull(order.get(0).getCredit_id());
    }

    @Test
    public void shouldErrorPaymentCardEmptyNumber() {
        given()
                .spec(requestSpec)
                .body(Api.getApiEmptyNumberCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
        payment = getPayments();
        credit = SQL.getCreditsRequest();
        order = SQL.getOrders();
        assertEquals(0, payment.size());
        assertEquals(0, credit.size());
        assertEquals(0, order.size());
    }

    @Test
    public void shouldErrorPaymentCardEmptyMonth() {
        given()
                .spec(requestSpec)
                .body(Api.getApiEmptyMonthCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
        payment = getPayments();
        credit = SQL.getCreditsRequest();
        order = SQL.getOrders();
        assertEquals(0, payment.size());
        assertEquals(0, credit.size());
        assertEquals(0, order.size());
    }

    @Test
    public void shouldErrorPaymentCardEmptyYear() {
        given()
                .spec(requestSpec)
                .body(Api.getApiEmptyYearCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
        payment = getPayments();
        credit = SQL.getCreditsRequest();
        order = SQL.getOrders();
        assertEquals(0, payment.size());
        assertEquals(0, credit.size());
        assertEquals(0, order.size());
    }

    @Test
    public void shouldErrorPaymentCardEmptyOwner() {
        given()
                .spec(requestSpec)
                .body(Api.getApiEmptyOwnerCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
        payment = getPayments();
        credit = SQL.getCreditsRequest();
        order = SQL.getOrders();
        assertEquals(0, payment.size());
        assertEquals(0, credit.size());
        assertEquals(0, order.size());
    }

    @Test
    public void shouldErrorPaymentCardEmptyCVC() {
        given()
                .spec(requestSpec)
                .body(Api.getApiEmptyCVCCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
        payment = getPayments();
        credit = SQL.getCreditsRequest();
        order = SQL.getOrders();
        assertEquals(0, payment.size());
        assertEquals(0, credit.size());
        assertEquals(0, order.size());
    }

    @Test
    public void shouldErrorPaymentCardEmptyForm() {
        given()
                .spec(requestSpec)
                .body(Api.getApiEmptyFormCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
        payment = getPayments();
        credit = SQL.getCreditsRequest();
        order = SQL.getOrders();
        assertEquals(0, payment.size());
        assertEquals(0, credit.size());
        assertEquals(0, order.size());
    }
}