Feature: Create Product API

Scenario:verify user can Create and store a new object using the data provided in the request body
    Given Restful API is up and running
    When the user sends request to add the item with the JSON payload
    Then a 200 response code is returned
    And a "Apple MacBook Pro 16" is created
    
    When User sends GET request using extracted id
    Then User validates GET response
    
Scenario:Ability to delete the product
    Given Restful API is up and running
    When the user sends request to add the item with the JSON payload
    Then a 200 response code is returned
    And a "Apple MacBook Pro 16" is created
    When User sends GET request using extracted id
    Then User validates GET response
    When User deletes the product created
    Then User should receive 200 status code
    And User sends GET request for deleted product
    Then validate 404 status code