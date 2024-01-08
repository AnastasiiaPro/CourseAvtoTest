package ru.netology.test;

import ru.netology.data.DataHelper;
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
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getDeclinedNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getEmptyNumber();
        String month = DataHelper.getEmptyMonth();
        String year = DataHelper.getEmptyYear();
        String owner = DataHelper.getEmptyOwner();
        String cvc = DataHelper.getEmptyCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getEmptyNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getRandomNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
        notPositiveMessage();
        denialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Zero card number")
    public void shouldErrorZeroNumber() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        String number = DataHelper.getZeroNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getRandomOneDigitsNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getRandomFifteenNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getEmptyMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getRandomOneDigitsNumberMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getThirteenMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getZeroMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getEmptyYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getRandomOneDigitYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getMoreThanFiveYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getPastYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getZeroYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getEmptyOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getCyrillicOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getEnOwnerLow();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getSymbolOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getNumbersOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
        sendingRequest.shouldNotBe(Condition.visible);
        notPositiveMessage();
        denialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Quantity symbols owner")
    public void shouldErrorSymbolsOwner() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getQuantitySymbolOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getOneWordOwner();
        String cvc = DataHelper.getCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getEmptyCVC();
        card.fillCardFields(number, month, year, owner, cvc);
        sendingRequest.shouldNotBe(Condition.visible);
        errorEmptyFieldCVC.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("One Symbol CVC")
    public void shouldErrorOneSymbolCVC() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getOneSymbolCVC();
        card.fillCardFields(number, month, year, owner, cvc);
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }

    @Test
    @DisplayName("Two Symbol CVC")
    public void shouldErrorTwoSymbolCVC() {
        long initialNumberPayment = SQL.getNumberPaymentCard();
        var card = choicePaymentCard();
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getTwoSymbolsCVC();
        card.fillCardFields(number, month, year, owner, cvc);
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
        String number = DataHelper.getApprovedNumber();
        String month = DataHelper.getMonth();
        String year = DataHelper.getYear();
        String owner = DataHelper.getOwner();
        String cvc = DataHelper.getZeroCVC();
        card.fillCardFields(number, month, year, owner, cvc);
        sendingRequest.shouldNotBe(Condition.visible);
        errorFormat.shouldBe(Condition.visible);
        notPositiveMessage();
        notDenialMessage();
        checkNumberPayment(initialNumberPayment, 0);
    }
}