package ru.netology.page;

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

    public void fillCardFields(String number, String month, String year, String owner, String cvc) {
        cardNumber.setValue(number);
        cardMonth.setValue(month);
        cardYear.setValue(year);
        cardOwner.setValue(owner);
        cardCVC.setValue(cvc);
        buttonContinue.click();
    }
}