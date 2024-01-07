package ru.netology.test;

import ru.netology.data.SQL;
import ru.netology.page.Main;
import ru.netology.page.PaymentCard;
import com.codeborne.selenide.Condition;

import io.qameta.allure.selenide.AllureSelenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.page.Messages.*;


public class UiTest {

    @BeforeAll
    static void setup() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDown() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void openSetup() {
        open("http://localhost:8080");
    }

    @AfterEach
    void clear() {
        SQL.clear();
    }

    public void checkNumberPayment(long initialNumberPayment, int x) {
        long finalNumberPayment = SQL.getNumberPaymentCard();
        assertEquals(initialNumberPayment + x, finalNumberPayment);
    }

    PaymentCard choicePaymentCard() {
        Main page = new Main();
        return page.clickButtonPay();
    }

    @Test
    @DisplayName("Payment approved card")
    public void shouldSuccessfulPaymentApprovedCard() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.approvedNumberCard();
        sendingRequest.shouldBe();
        positiveMessage();
        String statusAfterServer = SQL.getStatusPaymentCard();
        checkNumberPayment(initialNumberPayment, 1);
        assertEquals("APPROVED", statusAfterServer);
    }

    @Test
    @DisplayName("Payment declined card")
    public void shouldUnsuccessfulPaymentDeclinedCard() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.declinedNumberCard();
        String statusAfterServer = SQL.getStatusPaymentCard();
        denialMessage();
        checkNumberPayment(initialNumberPayment, 1);
        assertEquals("DECLINED", statusAfterServer);
    }

    @Test
    @DisplayName("Empty form card ")
    public void shouldErrorEmptyForm() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.emptyForm();
        sendingRequest.shouldNotBe(Condition.visible);
        errorEmptyFieldNumber.shouldBe(Condition.visible);
        errorEmptyFieldMonth.shouldBe(Condition.visible);
        errorEmptyFieldYear.shouldBe(Condition.visible);
        errorEmptyFieldOwner.shouldBe(Condition.visible);
        errorEmptyFieldCVC.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Empty card number")
    public void shouldErrorEmptyNumber() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.emptyNumber();
        sendingRequest.shouldNotBe(Condition.visible);
        errorEmptyFieldNumber.shouldBe(Condition.visible);
        notPositiveMessage();
        denialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Random card number")
    public void shouldErrorRandomNumber() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.randomNumber();
        notPositiveMessage();
        denialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Zero card number")
    public void shouldErrorZeroNumber() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.zeroNumber();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("One digit card number")
    public void shouldErrorOneDigitNumber() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.oneDigitNumber();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Fifteen digits card number")
    public void shouldErrorFifteenDigitsNumber() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.fifteenDigitsNumber();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Empty month")
    public void shouldErrorEmptyMonth() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.emptyMonth();
        sendingRequest.shouldNotBe(Condition.visible);
        errorEmptyFieldMonth.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("One digits number month")
    public void shouldErrorIfInvalidMonthFormat() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.oneDigitsNumberMonth();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Thirteenth month")
    public void shouldErrorIfNotExistedMonth13() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.thirteenthMonth();
        sendingRequest.shouldNotBe(Condition.visible);
        errorSpecifiedPeriodCard.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Zero month")
    public void shouldErrorZeroMonth() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.zeroMonth();
        sendingRequest.shouldNotBe(Condition.visible);
        errorSpecifiedPeriodCard.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Empty year field")
    public void shouldErrorEmptyYear() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.emptyYear();
        sendingRequest.shouldNotBe(Condition.visible);
        errorEmptyFieldYear.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("One digit year")
    public void shouldErrorOneDigitYear() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.oneDigitYear();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Year more than five")
    public void shouldErrorIfYearMoreThan5() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.yearMoreThanFive();
        sendingRequest.shouldNotBe(Condition.visible);
        errorSpecifiedPeriodCard.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Past year")
    public void shouldErrorPastYear() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.pastYear();
        sendingRequest.shouldNotBe(Condition.visible);
        errorPeriodCard.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Zero year")
    public void shouldErrorZeroYear() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.zeroYear();
        sendingRequest.shouldNotBe(Condition.visible);
        errorPeriodCard.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Empty owner")
    public void shouldErrorIfEmptyOwnerField() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.emptyOwner();
        sendingRequest.shouldNotBe(Condition.visible);
        errorEmptyFieldOwner.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Cyrillic letters owner")
    public void shouldErrorIfCyrillicLettersInOwnerField() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.cyrillicLettersOwner();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Lower letters owner")
    public void shouldErrorLowLettersInOwnerField() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.lowerLettersOwner();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Symbols owner")
    public void shouldErrorIfsymbolsOwner() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.symbolsOwner();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Number owner")
    public void shouldErrorNumberOwner() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.numberOwner();
        sendingRequest.shouldNotBe(Condition.visible);
//        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        denialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Quantity symbols owner")
    public void shouldErrorSymbolsOwner() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.quantitySymbolsOwner();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("One word owner")
    public void shouldErrorOneWordOwner() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.OneWordOwner();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Empty CVC")
    public void shouldErrorIfEmptyCVCField() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.emptyCVC();
        sendingRequest.shouldNotBe(Condition.visible);
        errorEmptyFieldCVC.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("One digit CVC")
    public void shouldErrorOneDigitCVC() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.oneDigitCVC();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Two digit CVC")
    public void shouldErrorTwoDigitsCVC() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.twoDigitsCVC();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Zero CVC")
    public void shouldErrorZeroCVC() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        card.zeroCVC();
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }
}