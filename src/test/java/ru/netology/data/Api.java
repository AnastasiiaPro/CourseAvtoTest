package ru.netology.data;

import lombok.Value;

public class Api {
    @Value
    public static class Options {
        String number;
        String month;
        String year;
        String owner;
        String cvc;
    }

    public static Options getApiApprovedCard() {
        return new Options(Helper.getApprovedNumber(), Helper.getMonth(), Helper.getYear(), Helper.getEnOwner(),
                Helper.getCVC());
    }

    public static Options getApiDeclinedCard() {
        return new Options(Helper.getDeclinedNumber(), Helper.getMonth(), Helper.getYear(), Helper.getEnOwner(),
                Helper.getCVC());
    }

    public static Options getApiEmptyNumberCard() {
        return new Options(Helper.getEmptyNumber(), Helper.getMonth(), Helper.getYear(), Helper.getEnOwner(),
                Helper.getCVC());
    }

    public static Options getApiEmptyMonthCard() {
        return new Options(Helper.getApprovedNumber(), Helper.getEmptyMonth(), Helper.getYear(), Helper.getEnOwner(),
                Helper.getCVC());
    }

    public static Options getApiEmptyYearCard() {
        return new Options(Helper.getApprovedNumber(), Helper.getMonth(), Helper.getEmptyYear(), Helper.getEnOwner(),
                Helper.getCVC());
    }

    public static Options getApiEmptyOwnerCard() {
        return new Options(Helper.getApprovedNumber(), Helper.getMonth(), Helper.getYear(), Helper.getEmptyOwner(),
                Helper.getCVC());
    }

    public static Options getApiEmptyCVCCard() {
        return new Options(Helper.getApprovedNumber(), Helper.getMonth(), Helper.getYear(), Helper.getEnOwner(),
                Helper.getEmptyCVC());
    }

    public static Options getApiEmptyFormCard() {
        return new Options(Helper.getEmptyNumber(), Helper.getEmptyMonth(), Helper.getEmptyYear(), Helper.getEmptyOwner(),
                Helper.getEmptyCVC());
    }
}