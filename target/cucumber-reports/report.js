$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("src/test/resources/features/DeleteObject.feature");
formatter.feature({
  "name": "Delete Product API",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "verify user can delete the product",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "Restful server is up and running",
  "keyword": "Given "
});
formatter.match({
  "location": "Stepdef_deletecall.restful_server_is_up_and_running()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User sends POST request to create a new product",
  "keyword": "When "
});
formatter.match({
  "location": "Stepdef_deletecall.user_sends_POST_request_to_create_a_new_product()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Response status code should be 200",
  "keyword": "Then "
});
formatter.match({
  "location": "Stepdef_deletecall.response_status_code_should_be(Integer)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Product \"Apple Watch Series 8\" should be created successfully",
  "keyword": "And "
});
formatter.match({
  "location": "Stepdef_deletecall.product_should_be_created_successfully(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User sends GET request using created product id",
  "keyword": "When "
});
formatter.match({
  "location": "Stepdef_deletecall.user_sends_GET_request_using_created_product_id()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User validates the Strap Colour as \"Elderberry\" in response details",
  "keyword": "Then "
});
formatter.match({
  "location": "Stepdef_deletecall.user_validates_the_Strap_Colour_as_in_response_details(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User sends DELETE request for the created product",
  "keyword": "When "
});
formatter.match({
  "location": "Stepdef_deletecall.user_sends_DELETE_request_for_the_created_product()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "DELETE API should return status code 200 and message",
  "keyword": "Then "
});
formatter.match({
  "location": "Stepdef_deletecall.delete_API_should_return_status_code_and_message(Integer)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User sends GET request for deleted product id",
  "keyword": "When "
});
formatter.match({
  "location": "Stepdef_deletecall.user_sends_GET_request_for_deleted_product_id()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Response status code should be 404",
  "keyword": "Then "
});
formatter.match({
  "location": "Stepdef_deletecall.response_status_code_should_be(Integer)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "Response message should indicate object not found",
  "keyword": "And "
});
formatter.match({
  "location": "Stepdef_deletecall.response_message_should_indicate_object_not_found()"
});
formatter.result({
  "status": "passed"
});
formatter.uri("src/test/resources/features/GetObject.feature");
formatter.feature({
  "name": "Get Product API",
  "description": "",
  "keyword": "Feature"
});
formatter.background({
  "name": "Server is up and running",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "API server is up and running",
  "keyword": "Given "
});
formatter.match({
  "location": "Stepdef_getcall.api_server_is_up_and_running()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Verify user can retrieve single item",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "user sends Get request to return an item by passing id as 5",
  "keyword": "When "
});
formatter.match({
  "location": "Stepdef_getcall.user_sends_Get_request_to_return_an_item_by_passing_id_as(Integer)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "\"Samsung Galaxy Z Fold2\" item and 200 response code are returned",
  "keyword": "Then "
});
formatter.match({
  "location": "Stepdef_getcall.item_and_response_code_are_returned(String,Integer)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "validated the item color as \"Brown\"",
  "keyword": "And "
});
formatter.match({
  "location": "Stepdef_getcall.validated_the_item_color_as(String)"
});
formatter.result({
  "status": "passed"
});
formatter.background({
  "name": "Server is up and running",
  "description": "",
  "keyword": "Background"
});
formatter.step({
  "name": "API server is up and running",
  "keyword": "Given "
});
formatter.match({
  "location": "Stepdef_getcall.api_server_is_up_and_running()"
});
formatter.result({
  "status": "passed"
});
formatter.scenario({
  "name": "Verify user can retrieve list of items",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "user sends GET request to return list of items",
  "keyword": "When "
});
formatter.match({
  "location": "Stepdef_getcall.user_sends_GET_request_to_return_list_of_items()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "all object IDs are retrieved successfully and total count should be 13",
  "keyword": "Then "
});
formatter.match({
  "location": "Stepdef_getcall.all_object_IDs_are_retrieved_successfully_and_total_count_should_be(Integer)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "verify if all product names are retrieved",
  "keyword": "Then "
});
formatter.match({
  "location": "Stepdef_getcall.verify_if_all_product_names_are_retrieved()"
});
formatter.result({
  "status": "passed"
});
formatter.uri("src/test/resources/features/PostObject.feature");
formatter.feature({
  "name": "Create Product API",
  "description": "",
  "keyword": "Feature"
});
formatter.scenario({
  "name": "verify user can add and store a new object using the data provided in the request payload",
  "description": "",
  "keyword": "Scenario"
});
formatter.step({
  "name": "Restful API is up and running",
  "keyword": "Given "
});
formatter.match({
  "location": "Stepdef_postcall.restful_API_is_up_and_running()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "the user sends a request to add the item using API key authentication",
  "keyword": "When "
});
formatter.match({
  "location": "Stepdef_postcall.the_user_sends_a_request_to_add_the_item_using_API_key_authentication()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "\"Apple MacBook Pro 16\" is created",
  "keyword": "Then "
});
formatter.match({
  "location": "Stepdef_postcall.is_created(String)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "CPU model is \"Intel Core i9\" and price is 1849.99",
  "keyword": "And "
});
formatter.match({
  "location": "Stepdef_postcall.cpu_model_is_and_price_is(String,Float)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "a 200 response code is returned",
  "keyword": "Then "
});
formatter.match({
  "location": "Stepdef_postcall.a_response_code_is_returned(Integer)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User sends GET request using extracted id",
  "keyword": "When "
});
formatter.match({
  "location": "Stepdef_postcall.user_sends_GET_request_using_extracted_id()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "User validates GET response",
  "keyword": "Then "
});
formatter.match({
  "location": "Stepdef_postcall.user_validates_GET_response()"
});
formatter.result({
  "status": "passed"
});
});