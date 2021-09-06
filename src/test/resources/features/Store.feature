@Store
@Regression
Feature: Store functionality

Scenario: To Verify user is able to get inventory from the store
  
     Given User set valid endpoint to get inventory  
     When User send the get request with content type "JSON"
     Then Server should return "200"
     Then Schema should validate for store response