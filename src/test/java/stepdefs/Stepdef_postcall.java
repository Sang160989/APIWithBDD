package stepdefs;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
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
	Scenario scenario;
	String prodid;

	@Before
	public void before(Scenario scenarioVal) {
		this.scenario = scenarioVal;
	}

//	 create the product
	@Given("Restful API is up and running")
	public void restful_API_is_up_and_running() {
		REQ_SPEC = given().baseUri(baseuri);
	}

//	using api key to authenticate 
	@When("the user sends request to add the item with the JSON payload")
	public void the_user_sends_request_to_add_the_item_with_the_JSON_payload() {
		String payload = "{\n" + "  \"name\": \"Apple MacBook Pro 16\",\n" + "  \"data\": {\n" + "    \"year\": 2019,\n"
				+ "    \"price\": 1849.99,\n" + "    \"CPU model\": \"Intel Core i9\",\n"
				+ "    \"Hard disk size\": \"1 TB\"\n" + "  }\n" + "}";

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("x-api-key", "52d5eaac-1119-4877-ba2b-bdd00357507a");
		RESP = REQ_SPEC.headers(headers).body(payload).when().post("/objects");
	}

	@Then("a {int} response code is returned")
	public void a_response_code_is_returned(Integer expectedcode) {
		int actualcode = RESP.then().extract().statusCode();
		Assert.assertEquals(actualcode, expectedcode);
		RESP.then().log().all();
	}

	@Then("a {string} is created")
	public void a_is_created(String exp_prodname) {
		String act_prodname = RESP.jsonPath().getString("name");
		Assert.assertEquals(act_prodname, exp_prodname);
		RESP.then().assertThat().body("data.year", equalTo(2019)).body("data.price", equalTo(1849.99f));
		prodid = RESP.jsonPath().getString("id");
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

//	Delete the same product which is created by passing the same id as path parameter
	@When("User deletes the product created")
	public void user_deletes_the_product_created() {
		RESP = given().baseUri(baseuri).pathParam("id", prodid).when().delete("/objects/{id}");
	}

	// validate the status code and message
	@Then("User should receive {int} status code")
	public void user_should_receive_status_code(Integer expectedcode) {
		int actualcode = RESP.then().extract().statusCode();
		Assert.assertEquals(actualcode, expectedcode);
		String responseBody = RESP.getBody().asString();
		System.out.println(responseBody);
		Assert.assertTrue(responseBody.contains("Object with id = "+prodid+" has been deleted."));
		RESP.then().log().all();
	}

//	Get call to confirm the product is deleted and validate the message
	@Then("User sends GET request for deleted product")
	public void user_sends_GET_request_for_deleted_product() {
		RESP = given().baseUri(baseuri).pathParam("id", prodid).when().get("/objects/{id}");
		String msg = RESP.then().extract().path("error");
		Assert.assertEquals(msg, "Object with id=" + prodid + " was not found.");
	}

//	Get call to confirm the product is deleted
	@Then("validate {int} status code")
	public void validate_status_code(Integer expectedcode) {
		int actualcode = RESP.then().extract().statusCode();
		Assert.assertEquals(actualcode, expectedcode);
		RESP.then().log().all();
	}
}
