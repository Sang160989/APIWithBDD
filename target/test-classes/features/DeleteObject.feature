Feature: Delete Product API

Scenario: verify user can delete the product

Given Restful server is up and running
When User sends POST request to create a new product
Then Response status code should be 200
And Product "Apple Watch Series 8" should be created successfully
When User sends GET request using created product id
Then User validates the Strap Colour as "Elderberry" in response details
When User sends DELETE request for the created product
Then DELETE API should return status code 200 and message
When User sends GET request for deleted product id
Then Response status code should be 404
And Response message should indicate object not found