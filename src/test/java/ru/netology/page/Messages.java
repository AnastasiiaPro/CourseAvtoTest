package ru.netology.page;

import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class Messages {
    public static SelenideElement errorSpecifiedPeriodCard = $(Selectors.withText("Неверно указан срок действия карты"));
    public static SelenideElement errorPeriodCard = $(Selectors.withText("Истёк срок действия карты"));
    public static SelenideElement errorEmptyFieldOwner = $(Selectors.withText("Поле обязательно для заполнения"));
    public static SelenideElement errorEmptyFieldNumber = $(Selectors.withText("Поле обязательно для заполнения"));
    public static SelenideElement errorEmptyFieldMonth = $(Selectors.withText("Поле обязательно для заполнения"));
    public static SelenideElement errorEmptyFieldYear = $(Selectors.withText("Поле обязательно для заполнения"));
    public static SelenideElement errorEmptyFieldCVC = $(Selectors.withText("Поле обязательно для заполнения"));
    public static SelenideElement errorFormat = $(Selectors.withText("Неверный формат"));
    public static SelenideElement sendingRequest = $(Selectors.withText("Отправляем запрос в Банк..."));
    public static SelenideElement messageSuccess = $(Selectors.withText("Успешно"));
    public static SelenideElement messageApprove = $(Selectors.withText("Операция одобрена Банком."));
    public static SelenideElement messageError = $(Selectors.withText("Ошибка"));
    public static SelenideElement messageDecline = $(Selectors.withText("Ошибка! Банк отказал в проведении операции."));
}
