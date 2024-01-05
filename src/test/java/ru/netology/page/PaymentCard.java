package ru.netology.page;

import ru.netology.data.DataHelper;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class PaymentCard {

    private SelenideElement cardTitle = $(Selectors.withText("Оплата по карте"));
    private SelenideElement buttonContinue = $(Selectors.withText("Продолжить"));
    private SelenideElement cardNumber = $("[placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement cardMonth = $("[placeholder=\"08\"]");
    private SelenideElement cardYear = $("[placeholder=\"22\"]");
    private SelenideElement cardOwner = $("div:nth-child(3) span:nth-child(1) span.input__box input");
    private SelenideElement cardCVC = $("[placeholder=\"999\"]");

    public PaymentCard() {
        cardTitle.shouldBe(Condition.visible);
    }

    public void validNumber() {
        cardNumber.setValue(DataHelper.getApprovedNumber());
    }

    public void validMonth() {
        cardMonth.setValue(DataHelper.getMonth());
    }

    public void validYear() {
        cardYear.setValue(DataHelper.getYear());
    }

    public void validOwner() {
        cardOwner.setValue(DataHelper.getEnOwner());
    }

    public void validCVC() {
        cardCVC.setValue(DataHelper.getCVC());
    }

    public void approvedNumberCard() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void declinedNumberCard() {
        cardNumber.setValue(DataHelper.getDeclinedNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void emptyForm() {
        buttonContinue.click();
    }

    public void emptyNumber() {
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void randomNumber() {
        cardNumber.setValue(DataHelper.getRandomNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void zeroNumber() {
        cardNumber.setValue(DataHelper.getZeroNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void oneDigitNumber() {
        cardNumber.setValue(DataHelper.getRandomOneDigitsNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void fifteenDigitsNumber() {
        cardNumber.setValue(DataHelper.getRandomFifteenNumber());
        validMonth();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void emptyMonth() {
        validNumber();
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void zeroMonth() {
        validNumber();
        cardMonth.setValue(DataHelper.getZeroMonth());
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void thirteenthMonth() {
        validNumber();
        cardMonth.setValue(DataHelper.getThirteenMonth());
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void oneDigitsNumberMonth() {
        validNumber();
        cardMonth.setValue(DataHelper.getRandomOneDigitsNumberMonth());
        validYear();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void emptyYear() {
        validNumber();
        validMonth();
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void yearMoreThanFive() {
        validNumber();
        validMonth();
        cardYear.setValue(DataHelper.getMoreThanFiveYear());
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void pastYear() {
        validNumber();
        validMonth();
        cardYear.setValue(DataHelper.getPastYear());
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void zeroYear() {
        validNumber();
        validMonth();
        cardYear.setValue(DataHelper.getZeroYear());
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void oneDigitYear() {
        validNumber();
        validMonth();
        cardYear.setValue(DataHelper.getRandomOneDigitYear());
        validOwner();
        validCVC();
        buttonContinue.click();
    }

    public void emptyOwner() {
        validNumber();
        validMonth();
        validYear();
        validCVC();
        buttonContinue.click();
    }

    public void quantitySymbolsOwner() {
        validNumber();
        validMonth();
        validYear();
        cardOwner.setValue(DataHelper.getQuantitySymbolOwner(String.valueOf("?????????? ??????????")));
        validCVC();
        buttonContinue.click();
    }

    public void OneWordOwner() {
        validNumber();
        validMonth();
        validYear();
        cardOwner.setValue(DataHelper.getOneWordOwner());
        validCVC();
        buttonContinue.click();
    }

    public void cyrillicLettersOwner() {
        validNumber();
        validMonth();
        validYear();
        cardOwner.setValue(DataHelper.getCyrillicOwner());
        validCVC();
        buttonContinue.click();
    }

    public void lowerLettersOwner() {
        validNumber();
        validMonth();
        validYear();
        cardOwner.setValue(DataHelper.getEnOwnerLow());
        validCVC();
        buttonContinue.click();
    }

    public void numberOwner() {
        validNumber();
        validMonth();
        validYear();
        cardOwner.setValue(DataHelper.getNumbersOwner());
        validCVC();
        buttonContinue.click();
    }

    public void symbolsOwner() {
        validNumber();
        validMonth();
        validYear();
        cardOwner.setValue(DataHelper.getSymbolOwner());
        validCVC();
        buttonContinue.click();
    }

    public void emptyCVC() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        buttonContinue.click();
    }

    public void oneDigitCVC() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        cardCVC.setValue(DataHelper.getOneSymbolCVC());
        buttonContinue.click();
    }

    public void twoDigitsCVC() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        cardCVC.setValue(DataHelper.getTwoSymbolsCVC());
        buttonContinue.click();
    }

    public void zeroCVC() {
        validNumber();
        validMonth();
        validYear();
        validOwner();
        cardCVC.setValue(DataHelper.getZeroCVC());
        buttonContinue.click();
    }
}