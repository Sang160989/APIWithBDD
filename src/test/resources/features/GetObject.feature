Feature: Get Product API

Background:Server is up and running
Given API server is up and running

Scenario: Verify user can retrieve single item 
When user sends Get request to return an item by passing id as 5
Then "Samsung Galaxy Z Fold2" item and 200 response code are returned
And validated the item color as "Brown"


Scenario:Verify user can retrieve list of items
When user sends GET request to return list of items
Then all object IDs are retrieved successfully and total count should be 13
Then verify if all product names are retrieved


Scenario:verify if the response includes only the specified objects
When user sends GET request to return only the specified items
Then the status code should be 200
And the count of items should be 3