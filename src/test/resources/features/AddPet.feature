@AddPets
Feature: Pet functionality

Scenario: To Verify user is able to add a new pet on the store
  
     Given User set valid endpoint to add pet  
     When User send the Post request with valid json body with content type "JSON"
     Then Server should return "200"
     Then Schema should validate for pet response

Scenario Outline: To Verify user is able to update a existing pet on the store

     Given User set valid endpoint to add pet  
     When User send the Put request with valid json body with content type <contentType>
     Then Server should return <status> from below table
     Then Schema should validate for pet response
Examples:
|status|contentType|
|200|JSON|