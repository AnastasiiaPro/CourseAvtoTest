package ru.netology.data;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;

import static io.restassured.RestAssured.given;

public class Helper {

    @Value
    public static class Options {
        String number;
        String month;
        String year;
        String owner;
        String cvc;
    }

    public static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(8080)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();


    public static String performPostRequest(String endpoint, Object requestBody, int expectedStatusCode) {
        return given()
                .spec(Helper.requestSpec)
                .body(requestBody)
                .when()
                .post(endpoint)
                .then()
                .statusCode(expectedStatusCode)
                .extract()
                .path("status");
    }

    public static Options getApiApprovedCard() {
        return new Options(DataHelper.getApprovedNumber(), DataHelper.getMonth(), DataHelper.getYear(), DataHelper.getOwner(),
                DataHelper.getCVC());
    }

    public static Options getApiDeclinedCard() {
        return new Options(DataHelper.getDeclinedNumber(), DataHelper.getMonth(), DataHelper.getYear(), DataHelper.getOwner(),
                DataHelper.getCVC());
    }

    public static Options getApiEmptyNumberCard() {
        return new Options(DataHelper.getEmptyNumber(), DataHelper.getMonth(), DataHelper.getYear(), DataHelper.getOwner(),
                DataHelper.getCVC());
    }

    public static Options getApiEmptyMonthCard() {
        return new Options(DataHelper.getApprovedNumber(), DataHelper.getEmptyMonth(), DataHelper.getYear(), DataHelper.getOwner(),
                DataHelper.getCVC());
    }

    public static Options getApiEmptyYearCard() {
        return new Options(DataHelper.getApprovedNumber(), DataHelper.getMonth(), DataHelper.getEmptyYear(), DataHelper.getOwner(),
                DataHelper.getCVC());
    }

    public static Options getApiEmptyOwnerCard() {
        return new Options(DataHelper.getApprovedNumber(), DataHelper.getMonth(), DataHelper.getYear(), DataHelper.getEmptyOwner(),
                DataHelper.getCVC());
    }

    public static Options getApiEmptyCVCCard() {
        return new Options(DataHelper.getApprovedNumber(), DataHelper.getMonth(), DataHelper.getYear(), DataHelper.getOwner(),
                DataHelper.getEmptyCVC());
    }

    public static Options getApiEmptyFormCard() {
        return new Options(DataHelper.getEmptyNumber(), DataHelper.getEmptyMonth(), DataHelper.getEmptyYear(), DataHelper.getEmptyOwner(),
                DataHelper.getEmptyCVC());
    }
}