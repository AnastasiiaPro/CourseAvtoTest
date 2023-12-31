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

import static org.junit.jupiter.api.Assertions.*;
import static ru.netology.data.SQL.getPayments;

public class ApiTest {
    private static List<SQL.PaymentEntity> payment;
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
        String status = Helper.performPostRequest("/api/v1/pay", Helper.getApiApprovedCard(), 200);
        payment = getPayments();
        order = SQL.getOrders();
        assertEquals(1, payment.size());
        assertEquals(1, order.size());
        assertEquals("APPROVED", status);
        assertTrue(payment.get(0).getStatus().equalsIgnoreCase("APPROVED"));
        assertEquals(payment.get(0).getTransaction_id(), order.get(0).getPayment_id());
        assertNull(order.get(0).getCredit_id());
    }

    @Test
    public void shouldDeclinedPaymentCard() {
        String status = Helper.performPostRequest("/api/v1/pay", Helper.getApiDeclinedCard(), 200);
        payment = getPayments();
        order = SQL.getOrders();
        assertEquals(1, payment.size());
        assertEquals(1, order.size());
        assertEquals("DECLINED", status);
        assertTrue(payment.get(0).getStatus().equalsIgnoreCase("DECLINED"));
        assertEquals(payment.get(0).getTransaction_id(), order.get(0).getPayment_id());
        assertNull(order.get(0).getCredit_id());
    }

    @Test
    public void shouldErrorPaymentCardEmptyNumber() {
        String status = Helper.performPostRequest("/api/v1/pay", Helper.getApiEmptyNumberCard(), 400);
        payment = getPayments();
        order = SQL.getOrders();
        assertEquals(0, payment.size());
        assertEquals(0, order.size());
    }

    @Test
    public void shouldErrorPaymentCardEmptyMonth() {
        String status = Helper.performPostRequest("/api/v1/pay", Helper.getApiEmptyMonthCard(), 400);
        payment = getPayments();
        order = SQL.getOrders();
        assertEquals(0, payment.size());
        assertEquals(0, order.size());
    }

    @Test
    public void shouldErrorPaymentCardEmptyYear() {
        String status = Helper.performPostRequest("/api/v1/pay", Helper.getApiEmptyYearCard(), 400);
        payment = getPayments();
        order = SQL.getOrders();
        assertEquals(0, payment.size());
        assertEquals(0, order.size());
    }

    @Test
    public void shouldErrorPaymentCardEmptyOwner() {
        String status = Helper.performPostRequest("/api/v1/pay", Helper.getApiEmptyOwnerCard(), 400);
        payment = getPayments();
        order = SQL.getOrders();
        assertEquals(0, payment.size());
        assertEquals(0, order.size());
    }

    @Test
    public void shouldErrorPaymentCardEmptyCVC() {
        String status = Helper.performPostRequest("/api/v1/pay", Helper.getApiEmptyCVCCard(), 400);
        payment = getPayments();
        order = SQL.getOrders();
        assertEquals(0, payment.size());
        assertEquals(0, order.size());
    }

    @Test
    public void shouldErrorPaymentCardEmptyForm() {
        String status = Helper.performPostRequest("/api/v1/pay", Helper.getApiEmptyFormCard(), 400);
        payment = getPayments();
        order = SQL.getOrders();
        assertEquals(0, payment.size());
        assertEquals(0, order.size());
    }
}