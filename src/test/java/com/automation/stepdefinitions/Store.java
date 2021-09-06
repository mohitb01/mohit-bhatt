package com.automation.stepdefinitions;

import static com.automation.utility.ResponseUtils.response;

import com.automation.base.SerenityBase;
import com.automation.endpoints.APIEndPoints;
import com.automation.utility.ResponseUtils;
import com.automation.utility.SerenityServices;
import com.automation.utility.Utility;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Store extends SerenityBase {
	
	String endPoint;
	
@Given("^User set valid endpoint to get inventory$")
public void user_set_valid_endpoint_to_get_inventory() {
    	endPoint = Utility.createEndPoint(configurationsXlsMap.get("URL"),
    APIEndPoints.STORE_ENDPOINT);
}

@When("^User send the get request with content type \"([^\"]*)\"$")
public void user_send_the_get_request_with_content_type(String cntentType) throws Exception {
	response = SerenityServices.get(cntentType,
            endPoint);
    response.prettyPrint();
    
}

@Then("^Schema should validate for store response$")
public void schema_should_validate_for_store_response() {
	ResponseUtils
    .validateJsonSchema("storeSchema");
    
}

}