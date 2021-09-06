package com.automation.utility;

import java.util.Map;

import org.junit.Assert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;

public class SerenityServices {

	Response response;
	public static Response post(String contentType, String body,String URL) throws Exception {
		return SerenityRest.given().contentType(getContentType(contentType)).body(body).log().all().when().post(URL);
	}
	
	public static Response put(String contentType, String body,String URL) throws Exception {
		return SerenityRest.given().contentType(getContentType(contentType)).body(body).log().all().when().put(URL);
	}
	public static Response doRequest(RequestSpecification requestSpec,
            String methodType, String url) {
        switch (methodType.toLowerCase()) {
        case "get":
            return requestSpec.log().all().get(url);
        case "post":
            return requestSpec.log().all().post(url);
        case "put":
            return requestSpec.log().all().put(url);
        case "delete":
            return requestSpec.log().all().delete(url);
        case "patch":
            return requestSpec.log().all().patch(url);
        default:
            Assert.assertTrue("Invalid method type passed: " + methodType,
                    false);
            return null;
        }
    }
	
	  public static Response getCallWithPathParam(Map<String, String> pathParams,
	            String URL, String contentType) throws Exception {
	        return SerenityRest.given().relaxedHTTPSValidation()
	                .contentType(getContentType(contentType)).pathParams(pathParams)
	                .log().all().get(URL);
	    }
	  
	 @SuppressWarnings("unused")
	private static ContentType getContentType(String contentType)
	            throws Exception {
	        if (contentType.equalsIgnoreCase("JSON")) {
	            return ContentType.JSON;
	        }
	        if (contentType.equalsIgnoreCase("URLENC")) {
	            return ContentType.URLENC;
	        }
	        if (contentType.equalsIgnoreCase("TEXT")) {
	            return ContentType.TEXT;
	        }
	        if (contentType.equalsIgnoreCase("HTML")) {
	            return ContentType.HTML;
	        }
	        if (contentType.equalsIgnoreCase("BINARY")) {
	            return ContentType.BINARY;
	        }
	        if (contentType.equalsIgnoreCase("XML")) {
	            return ContentType.XML;
	        }
	        if (contentType.equalsIgnoreCase("")) {
	            return ContentType.JSON;
	        }
	        if (contentType.equalsIgnoreCase("Any")) {
	            return ContentType.ANY;
	        } else
	            throw new Exception(
	                    "Not a valid Content Type, Please set the valid Content Type");
	    }
}
