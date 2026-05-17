package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.specification.RequestSpecification;
import static org.hamcrest.Matchers.equalTo;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class Stepdef_postcall {

	String baseuri = "https://api.restful-api.dev";
	RequestSpecification REQ_SPEC;
	Response RESP;
	String prodid;

//create the product
	@Given("Restful API is up and running")
	public void restful_API_is_up_and_running() {
		REQ_SPEC = given().baseUri(baseuri);
	}

//using api key to authenticate 
	@When("the user sends a request to add the item using API key authentication")
	public void the_user_sends_a_request_to_add_the_item_using_API_key_authentication() {
		String payload = "{\n" + "  \"name\": \"Apple MacBook Pro 16\",\n" + "  \"data\": {\n" + "    \"year\": 2019,\n"
				+ "    \"price\": 1849.99,\n" + "    \"CPU model\": \"Intel Core i9\",\n"
				+ "    \"Hard disk size\": \"1 TB\"\n" + "  }\n" + "}";

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("x-api-key", "52d5eaac-1119-4877-ba2b-bdd00357507a");
		RESP = REQ_SPEC.headers(headers).body(payload).when().post("/objects");
	}

	@Then("{string} is created")
	public void is_created(String exp_prodname) {
		String act_prodname = RESP.jsonPath().getString("name");
		Assert.assertEquals(act_prodname, exp_prodname);
	}

	@Then("CPU model is {string} and price is {float}")
	public void cpu_model_is_and_price_is(String cpumodel, Float price) {
		RESP.then().assertThat().body("data.'CPU model'", equalTo(cpumodel)).body("data.price", equalTo(price));
		prodid = RESP.jsonPath().getString("id");
	}

	@Then("a {int} response code is returned")
	public void a_response_code_is_returned(Integer expectedcode) {
		int actualcode = RESP.then().extract().statusCode();
		Assert.assertEquals(actualcode, expectedcode);
		RESP.then().log().all();
	}

//	Get the product by passing the same id as path parameter
	@When("User sends GET request using extracted id")
	public void user_sends_GET_request_using_extracted_id() {
		RESP = given().baseUri(baseuri).pathParam("id", prodid).when().get("/objects/{id}");
	}

	@Then("User validates GET response")
	public void user_validates_GET_response() {
		String userid = RESP.jsonPath().getString("id");
		Assert.assertEquals(userid, prodid);
		RESP.then().log().all();
	}

}
