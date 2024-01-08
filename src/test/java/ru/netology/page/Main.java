package ru.netology.page;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Main {

    private SelenideElement buttonPay = $(Selectors.withText("Купить"));

    public PaymentCard clickButtonPay() {
        buttonPay.click();
        return new PaymentCard();
    }
}
