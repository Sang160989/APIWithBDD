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
public class Stepdef_deletecall {
	
	String baseuri = "https://api.restful-api.dev";
	RequestSpecification REQ_SPEC;
	Response RESP;
	Scenario scenario;
	String prodid;
	
	@Before
	public void before(Scenario scenarioVal) {
		this.scenario = scenarioVal;
	}
	
	@Given("Restful server is up and running")
	public void restful_server_is_up_and_running() {
		REQ_SPEC = given().baseUri(baseuri);
	}

	@When("User sends POST request to create a new product")
	public void user_sends_POST_request_to_create_a_new_product() {
		String payload = "{\n" + "  \"name\": \"Apple MacBook Pro 16\",\n" + "  \"data\": {\n" + "    \"year\": 2019,\n"
				+ "    \"price\": 1849.99,\n" + "    \"CPU model\": \"Intel Core i9\",\n"
				+ "    \"Hard disk size\": \"1 TB\"\n" + "  }\n" + "}";

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json");
		headers.put("x-api-key", "52d5eaac-1119-4877-ba2b-bdd00357507a");
		RESP = REQ_SPEC.headers(headers).body(payload).when().post("/objects");
	}

	@Then("Response status code should be {int}")
	public void response_status_code_should_be(Integer expectedcode) {
		int actualcode = RESP.then().extract().statusCode();
		Assert.assertEquals(actualcode, expectedcode);
		RESP.then().log().all();
	}

	@Then("Product {string} should be created successfully")
	public void product_should_be_created_successfully(String exp_prodname) {
		String act_prodname = RESP.jsonPath().getString("name");
		Assert.assertEquals(act_prodname, exp_prodname);
		RESP.then().assertThat().body("data.year", equalTo(2019)).body("data.price", equalTo(1849.99f));
		prodid = RESP.jsonPath().getString("id");
	}

	@When("User sends GET request using created product id")
	public void user_sends_GET_request_using_created_product_id() {
		RESP = given().baseUri(baseuri).pathParam("id", prodid).when().get("/objects/{id}");
	}

	@Then("User validates the GET response details")
	public void user_validates_the_GET_response_details() {
		String userid = RESP.jsonPath().getString("id");
		Assert.assertEquals(userid, prodid);
		RESP.then().log().all();
	}

	@When("User sends DELETE request for the created product")
	public void user_sends_DELETE_request_for_the_created_product() {
		RESP = given().baseUri(baseuri).pathParam("id", prodid).when().delete("/objects/{id}");
	}

	@Then("DELETE API should return status code {int}")
	public void delete_API_should_return_status_code(Integer expectedcode) {
		int actualcode = RESP.then().extract().statusCode();
		Assert.assertEquals(actualcode, expectedcode);
		String responseBody = RESP.getBody().asString();
		System.out.println(responseBody);
		Assert.assertTrue(responseBody.contains("Object with id = "+prodid+" has been deleted."));
		RESP.then().log().all();
	}

	@When("User sends GET request for deleted product id")
	public void user_sends_GET_request_for_deleted_product_id() {
		RESP = given().baseUri(baseuri).pathParam("id", prodid).when().get("/objects/{id}");
	}

	@Then("Response message should indicate object not found")
	public void response_message_should_indicate_object_not_found() {
		String msg = RESP.then().extract().path("error");
		Assert.assertEquals(msg, "Object with id=" + prodid + " was not found.");
	}



}
