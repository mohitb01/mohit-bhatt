package com.automation.stepdefinitions;

import static com.automation.utility.ResponseUtils.response;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;

import com.automation.base.SerenityBase;
import com.automation.endpoints.APIEndPoints;
import com.automation.utility.ResponseUtils;
import com.automation.utility.SerenityServices;
import com.automation.utility.Utility;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;



public class AddPet extends SerenityBase {
	
	String endPoint;
	Map<String, String> headers = new HashMap();
	Map<String, String> params = new HashMap<String, String>();
	
@Given("^User set valid endpoint to add pet$")
public void user_set_valid_endpoint_to_add_pet() throws Exception{
	endPoint = Utility.createEndPoint(configurationsXlsMap.get("URL"),
            APIEndPoints.POST_ENDPOINT);
}


@When("^User send the Post request with valid json body with content type \"([^\"]*)\"$")
public void user_send_the_Post_request_with_valid_json_body_with_content_type(String cntentType) throws Exception {
	
	String jsonString = Utility.readJson("pet");
	response = SerenityServices.post(cntentType,jsonString,
            endPoint);
    response.prettyPrint();
}
@When("^User send the Put request with valid json body with content type JSON$")
public void user_send_the_Put_request_with_valid_json_body_with_content_type_JSON() throws Exception {
	
	String jsonString = Utility.readJson("petput");
	response = SerenityServices.put("JSON",jsonString,
            endPoint);
    response.prettyPrint();
}

@Then("^Server should return \"([^\"]*)\"$")
public void server_should_return(String status) {
	Assert.assertEquals(status, "200");
    
}

@Then("^Server should return (\\d+) from below table$")
public void server_should_return_from_below_table(int status) {
	Assert.assertEquals(String.valueOf(status), "200");
}


@Then("^Schema should validate for pet response$")
public void schema_should_validate_for_pet_response() {

    ResponseUtils
            .validateJsonSchema("petSchema");

}

}