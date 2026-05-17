Feature: Create Product API

Scenario:verify user can add and store a new object using the data provided in the request payload
    Given Restful API is up and running
    When the user sends a request to add the item using API key authentication
    Then "Apple MacBook Pro 16" is created
    And CPU model is "Intel Core i9" and price is 1849.99
    Then a 200 response code is returned
    When User sends GET request using extracted id
    Then User validates GET response