Feature: Swag Labs Inventory Scenarios assignment

  Scenario Outline: Login Swag Labs and select Inventory
    Given I am on Swag Labs Login Page
    When I enter '<userName>' and '<password>'
    And I click Login Button 
    Then I should be able to Login
    And I should be able to select random items

    Examples: 
      | userName                   |password     |
      | standard_user              | secret_sauce|
      | problem_user               | secret_sauce|
      | performance_glitch_user    | secret_sauce|
      
      
      Scenario: Login Swag Labs on Locked user
    Given I am on Swag Labs Login Page
    When I enter locked user and password
    And I click Login Button 
    Then I should not be able to Login
   
      
      
      