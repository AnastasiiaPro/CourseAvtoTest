package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import static java.lang.String.valueOf;

public class DataHelper {

    private static Random random = new Random();
    private static Faker faker = new Faker();
    private static Faker fakerEn = new Faker(new Locale("En"));
    private static Faker fakerRu = new Faker(new Locale("Ru"));

    public static String getApprovedNumber() {
        return "1111 2222 3333 4444";
    }

    public static String getMonth() {
        String[] months = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"};
        return months[random.nextInt(months.length)];
    }

    public static String getYear() {
        List<Integer> years = generateYearList(5);
        int randomIndex = new Random().nextInt(years.size());
        int randomYear = years.get(randomIndex) % 100;
        String formattedYear = String.format("%02d", randomYear);
        return formattedYear;
    }

    private static List<Integer> generateYearList(int numYears) {
        int currentYear = Year.now().getValue();
        List<Integer> years = new ArrayList<>();
        for (int i = 0; i <= numYears; i++) {
            years.add(currentYear + i);
        }
        return years;
    }

    public static String getOwner() {
        var randomFirstName = fakerEn.name().firstName();
        var randomLastName = fakerEn.name().lastName();
        return randomFirstName.toUpperCase() + " " + randomLastName.toUpperCase();
    }

    public static String getCVC() {
        return valueOf(faker.number().numberBetween(100, 999));
    }

    public static String getDeclinedNumber() {
        return "5555 6666 7777 8888";
    }

    public static String getRandomNumber() {
        return faker.numerify("#### #### #### ####");
    }

    public static String getZeroNumber() {
        return "0000 0000 0000 0000";
    }

    public static String getRandomOneDigitsNumber() {
        return faker.numerify("#");
    }

    public static String getRandomFifteenNumber() {
        return faker.numerify("#### #### #### ###");
    }

    public static String getZeroMonth() {
        return "00";
    }

    public static String getThirteenMonth() {
        return "13";
    }

    public static String getRandomOneDigitsNumberMonth() {
        return faker.numerify("#");
    }

    public static String getPastYear() {
        LocalDate date = LocalDate.now().minusYears(1);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy");
        return date.format(format);
    }

    public static String getZeroYear() {
        return "00";
    }

    public static String getMoreThanFiveYear() {
        LocalDate date = LocalDate.now().plusYears(6);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yy");
        return date.format(format);
    }

    public static String getRandomOneDigitYear() {
        return faker.numerify("#");
    }

    public static String getQuantitySymbolOwner() {
        String randomFirstName = faker.letterify("??????????").toUpperCase();
        String randomLastName = faker.letterify("??????????").toUpperCase();
        return randomFirstName + " " + randomLastName;
    }

    public static String getSymbolOwner() {
        return "!@#$%^& *()_+ |";
    }

    public static String getNumbersOwner() {
        return faker.numerify("####### ######");
    }

    public static String getCyrillicOwner() {
        var randomFirstName = fakerRu.name().firstName();
        var randomLastName = fakerRu.name().lastName();
        return randomFirstName.toUpperCase() + " " + randomLastName.toUpperCase();
    }

    public static String getEnOwnerLow() {
        var randomFirstName = fakerEn.name().firstName();
        var randomLastName = fakerEn.name().lastName();
        return randomFirstName.toLowerCase() + " " + randomLastName.toLowerCase();
    }

    public static String getOneWordOwner() {
        return fakerEn.name().firstName().toUpperCase();
    }

    public static String getOneSymbolCVC() {
        return faker.numerify("#");
    }

    public static String getTwoSymbolsCVC() {
        return faker.numerify("##");
    }

    public static String getEmptyNumber() {
        return "";
    }

    public static String getEmptyMonth() {
        return "";
    }

    public static String getEmptyYear() {
        return "";
    }

    public static String getEmptyOwner() {
        return "";
    }

    public static String getEmptyCVC() {
        return "";
    }

    public static String getZeroCVC() {
        return "000";
    }
}