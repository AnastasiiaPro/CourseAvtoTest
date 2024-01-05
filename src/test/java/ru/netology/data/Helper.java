package ru.netology.data;

import lombok.Value;

public class Helper {
    @Value
    public static class Options {
        String number;
        String month;
        String year;
        String owner;
        String cvc;
    }

    public static Options getApiApprovedCard() {
        return new Options(DataHelper.getApprovedNumber(), DataHelper.getMonth(), DataHelper.getYear(), DataHelper.getEnOwner(),
                DataHelper.getCVC());
    }

    public static Options getApiDeclinedCard() {
        return new Options(DataHelper.getDeclinedNumber(), DataHelper.getMonth(), DataHelper.getYear(), DataHelper.getEnOwner(),
                DataHelper.getCVC());
    }

    public static Options getApiEmptyNumberCard() {
        return new Options(DataHelper.getEmptyNumber(), DataHelper.getMonth(), DataHelper.getYear(), DataHelper.getEnOwner(),
                DataHelper.getCVC());
    }

    public static Options getApiEmptyMonthCard() {
        return new Options(DataHelper.getApprovedNumber(), DataHelper.getEmptyMonth(), DataHelper.getYear(), DataHelper.getEnOwner(),
                DataHelper.getCVC());
    }

    public static Options getApiEmptyYearCard() {
        return new Options(DataHelper.getApprovedNumber(), DataHelper.getMonth(), DataHelper.getEmptyYear(), DataHelper.getEnOwner(),
                DataHelper.getCVC());
    }

    public static Options getApiEmptyOwnerCard() {
        return new Options(DataHelper.getApprovedNumber(), DataHelper.getMonth(), DataHelper.getYear(), DataHelper.getEmptyOwner(),
                DataHelper.getCVC());
    }

    public static Options getApiEmptyCVCCard() {
        return new Options(DataHelper.getApprovedNumber(), DataHelper.getMonth(), DataHelper.getYear(), DataHelper.getEnOwner(),
                DataHelper.getEmptyCVC());
    }

    public static Options getApiEmptyFormCard() {
        return new Options(DataHelper.getEmptyNumber(), DataHelper.getEmptyMonth(), DataHelper.getEmptyYear(), DataHelper.getEmptyOwner(),
                DataHelper.getEmptyCVC());
    }
}