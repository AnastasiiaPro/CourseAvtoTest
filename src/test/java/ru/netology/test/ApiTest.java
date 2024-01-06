package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import ru.netology.data.Helper;
import ru.netology.data.SQL;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.data.SQL.getPayments;

public class ApiTest {
    private static List<SQL.PaymentEntity> payment;
//    private static List<SQL.CreditRequestEntity> credit;
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

    @Test
    public void shouldApprovedPaymentCard() {
        String status = given()
                .spec(Helper.requestSpec)
                .body(Helper.getApiApprovedCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200)
                .extract()
                .path("status");
        payment = getPayments();
//        credit = SQL.getCreditsRequest();
        order = SQL.getOrders();
        assertEquals(1, payment.size());
//        assertEquals(0, credit.size());
        assertEquals(1, order.size());
        assertEquals("APPROVED", status);
        assertTrue(payment.get(0).getStatus().equalsIgnoreCase("APPROVED"));
        assertEquals(payment.get(0).getTransaction_id(), order.get(0).getPayment_id());
        assertNull(order.get(0).getCredit_id());
    }

    @Test
    public void shouldDeclinedPaymentCard() {
        String status = given()
                .spec(Helper.requestSpec)
                .body(Helper.getApiDeclinedCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(200)
                .extract()
                .path("status");

        payment = getPayments();
//        credit = SQL.getCreditsRequest();
        order = SQL.getOrders();
        assertEquals(1, payment.size());
//        assertEquals(0, credit.size());
        assertEquals(1, order.size());
        assertEquals("DECLINED", status);
        assertTrue(payment.get(0).getStatus().equalsIgnoreCase("DECLINED"));
        assertEquals(payment.get(0).getTransaction_id(), order.get(0).getPayment_id());
        assertNull(order.get(0).getCredit_id());
    }

    @Test
    public void shouldErrorPaymentCardEmptyNumber() {
        given()
                .spec(Helper.requestSpec)
                .body(Helper.getApiEmptyNumberCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
        payment = getPayments();
//        credit = SQL.getCreditsRequest();
        order = SQL.getOrders();
        assertEquals(0, payment.size());
//        assertEquals(0, credit.size());
        assertEquals(0, order.size());
    }

    @Test
    public void shouldErrorPaymentCardEmptyMonth() {
        given()
                .spec(Helper.requestSpec)
                .body(Helper.getApiEmptyMonthCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
        payment = getPayments();
//        credit = SQL.getCreditsRequest();
        order = SQL.getOrders();
        assertEquals(0, payment.size());
//        assertEquals(0, credit.size());
        assertEquals(0, order.size());
    }

    @Test
    public void shouldErrorPaymentCardEmptyYear() {
        given()
                .spec(Helper.requestSpec)
                .body(Helper.getApiEmptyYearCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
        payment = getPayments();
//        credit = SQL.getCreditsRequest();
        order = SQL.getOrders();
        assertEquals(0, payment.size());
//        assertEquals(0, credit.size());
        assertEquals(0, order.size());
    }

    @Test
    public void shouldErrorPaymentCardEmptyOwner() {
        given()
                .spec(Helper.requestSpec)
                .body(Helper.getApiEmptyOwnerCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
        payment = getPayments();
//        credit = SQL.getCreditsRequest();
        order = SQL.getOrders();
        assertEquals(0, payment.size());
//        assertEquals(0, credit.size());
        assertEquals(0, order.size());
    }

    @Test
    public void shouldErrorPaymentCardEmptyCVC() {
        given()
                .spec(Helper.requestSpec)
                .body(Helper.getApiEmptyCVCCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
        payment = getPayments();
//        credit = SQL.getCreditsRequest();
        order = SQL.getOrders();
        assertEquals(0, payment.size());
//        assertEquals(0, credit.size());
        assertEquals(0, order.size());
    }

    @Test
    public void shouldErrorPaymentCardEmptyForm() {
        given()
                .spec(Helper.requestSpec)
                .body(Helper.getApiEmptyFormCard())
                .when()
                .post("/api/v1/pay")
                .then()
                .statusCode(400);
        payment = getPayments();
//        credit = SQL.getCreditsRequest();
        order = SQL.getOrders();
        assertEquals(0, payment.size());
//        assertEquals(0, credit.size());
        assertEquals(0, order.size());
    }
}