package stepdefs;

import static io.restassured.RestAssured.given;
import java.util.List;
import org.testng.Assert;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Stepdef_getcall {

	String baseURI = "https://api.restful-api.dev";
	RequestSpecification REQ_SPEC;
	Response RESP;
	Scenario scenario;
	String responseBody;
	ReadContext ctx;

	@Before
	public void before(Scenario scenarioVal) {
		this.scenario = scenarioVal;
	}

	@Given("API server is up and running")
	public void api_server_is_up_and_running() {
		REQ_SPEC = given().baseUri(baseURI);
	}
	
	@When("user sends Get request to return an item by passing id as {int}")
	public void user_sends_Get_request_to_return_an_item_by_passing_id_as(Integer prodId) {
		RESP = REQ_SPEC.when().get("/objects/" + prodId);
	}

	@Then("{string} is created and {int} response code is returned")
	public void is_created_and_response_code_is_returned(String exp_prodname, Integer expectedcode) {
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

//	@When("I hit API with product Id as {int}")
//	public void i_hit_API_with_product_Id_as(Integer prodId) {
//		RESP = REQ_SPEC.when().get("/objects/" + prodId);
//
//	}
//
//	@Then("status code comes as {int}")
//	public void status_code_comes_as(Integer respcode) {
//		int responsecode = RESP.then().extract().statusCode();
//		Assert.assertEquals(responsecode, respcode);
//		String act_prodname = RESP.jsonPath().getString("name");
//		Assert.assertEquals(act_prodname, exp_prodname);
//		RESP.then().log().all();
//
//	}
//
//	@Then("Json body contains color as {string}")
//	public void json_body_contains_color_as(String colourdata) {
//		String prodColour = RESP.jsonPath().getString("data.color");
//		Assert.assertEquals(prodColour, colourdata);
//	}

//	Scenario 2: GET all objects

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

////	Then validate the price of "Apple iPad Air" as "419.99"
//	@Then("validate the price of {string} as {string}")
//	public void validate_the_price_of_as(String productName, String expectedPrice) {
//		String actualprice=ctx.read("$[?(@.name==='Apple iPad Air')].Price");
//		Assert.assertEquals(actualprice, expectedPrice);
//}

	
	
}
