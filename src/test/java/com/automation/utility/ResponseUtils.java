package com.automation.utility;

import java.util.List;

import org.junit.Assert;

import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;

public class ResponseUtils {
    public static Response response;

    public static String getDataFromResponseUsingJsonPath(String jsonPath) {
        return response.then().extract().jsonPath().getString(jsonPath);
    }

    public static List<String>
           getListFromResponseUsingJsonPath(String jsonPath) {
        System.out.println(
                response.then().extract().jsonPath().getList(jsonPath));
        return response.then().extract().jsonPath().getList(jsonPath);
    }

    public static void assertReponseStatus(int expectedStatusCode) {
        response.prettyPrint();
        response.then().statusCode(expectedStatusCode);

    }

    // Here just pass the JSON schema from your step definition
    // It will validate the JSON Schema With The Response

    public static void validateJsonSchema(String schema) {
        response.prettyPrint();
        response.then().assertThat().body(JsonSchemaValidator
                .matchesJsonSchema(Utility.readResponseJsonSchema(schema)));

    }
    
    public static void compareValues(String actualValue, String expectedValue) {
        Assert.assertTrue(
                "Values are not matching, expected value: " + expectedValue
                        + ", but was: " + actualValue,
                expectedValue.equals(actualValue));
    }
    
    /**
     * @description Method to assert a responseHeader's value
     * @param response,
     *            headerName, expectedHeaderValue
     */
    public void assertResponseHeader(Response response, String headerName,
            String expectedHeaderValue) {
        response.then().assertThat().header(headerName, expectedHeaderValue);
    }

}
