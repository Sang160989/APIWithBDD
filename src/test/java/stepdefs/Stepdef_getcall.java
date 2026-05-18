package stepdefs;

import static io.restassured.RestAssured.given;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Stepdef_getcall {

	String baseURI = "https://api.restful-api.dev";
	RequestSpecification REQ_SPEC;
	Response RESP;
	String responseBody;
	ReadContext ctx;

	@Given("API server is up and running")
	public void api_server_is_up_and_running() {
		REQ_SPEC = given().baseUri(baseURI);
	}

	@When("user sends Get request to return an item by passing id as {int}")
	public void user_sends_Get_request_to_return_an_item_by_passing_id_as(Integer prodId) {
		RESP = REQ_SPEC.when().get("/objects/" + prodId);
	}

	@Then("{string} item and {int} response code are returned")
	public void item_and_response_code_are_returned(String exp_prodname, Integer expectedcode) {
		int actualcode = RESP.then().extract().statusCode();
		Assert.assertEquals(actualcode, expectedcode);
		String act_prodname = RESP.jsonPath().getString("name");
		Assert.assertEquals(act_prodname, exp_prodname);
		RESP.then().log().all();
	}

	@Then("validated the item color as {string}")
	public void validated_the_item_color_as(String expectedcolor) {
		String actualcolor = RESP.jsonPath().getString("data.color");
		Assert.assertEquals(actualcolor, expectedcolor);
	}

//	Scenario:Verify user can retrieve list of items
	@When("user sends GET request to return list of items")
	public void user_sends_GET_request_to_return_list_of_items() {
		RESP = REQ_SPEC.when().get("/objects");
	}

	@Then("all object IDs are retrieved successfully and total count should be {int}")
	public void all_object_IDs_are_retrieved_successfully_and_total_count_should_be(Integer count) {
		responseBody = RESP.getBody().asString();
		System.out.println(responseBody);
		ctx = JsonPath.parse(responseBody);
		List<Integer> id = ctx.read("$.[*].id");
		System.out.println("Total number of id " + id.size());
		Assert.assertEquals(id.size(), count);
	}

	@Then("verify if all product names are retrieved")
	public void verify_if_all_product_names_are_retrieved() {
		List<String> names = RESP.jsonPath().getList("name");
		System.out.println(names);
	}

//	Scenario:verify if the response includes only the specified objects
	@When("user sends GET request to return only the specified items")
	public void user_sends_GET_request_to_return_only_the_specified_items() {
		Map<String, String> queryparam = new HashMap<String, String>();
		queryparam.put("id", "3");
		queryparam.put("id", "5");
		queryparam.put("id", "10");
		RESP = given().baseUri(baseURI).queryParams(queryparam).when().get("/objects");

	}

	@Then("the status code should be {int}")
	public void the_status_code_should_be(Integer expectedcode) {
		int actualcode = RESP.then().extract().statusCode();
		Assert.assertEquals(actualcode, expectedcode);
	}

	@Then("the count of items should be {int}")
	public void the_count_of_items_should_be(Integer exp_count) {
		List<String> Item_ID = RESP.jsonPath().getList("id");
		System.out.println("The count of specified items " + Item_ID.size());
		Assert.assertEquals(Item_ID.size(), exp_count);
		System.out.println("The item Id's are " + Item_ID);
	}

}
