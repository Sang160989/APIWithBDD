Feature: Get Product API

Background:Server is up and running
Given API server is up and running

Scenario: Verify user can retrieve single item 
When user sends Get request to return an item by passing id as 1
Then "Apple MacBook Pro 16" is created and 200 response code is returned
And validated the item color as "Cloudy White"


Scenario:Verify user can retrieve list of items
When user sends GET request to return list of items
Then all object IDs are retrieved successfully and total count should be 13
#Then validate the price of "Apple iPad Air" as "419.99"