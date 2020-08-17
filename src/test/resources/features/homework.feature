@homework
  Feature: Exercises

  @homework5_1
  Scenario: Positive or negative check
    And I print if "0" is positive

  @homework5_2
  Scenario: Days
    And I print "5" th day of week


  @homework6_1
  Scenario: Maps
    And I create map with keys "firstName" , "middleName" and values "John", "George" and swap values

  @homework6_2_1
  Scenario: Page details
    And I go to "https://www.google.com/"
    And I print page details

  @homework6_2_2
  Scenario: Navigation
    Given I go to "https://www.google.com/"
    And I go to "https://www.yahoo.com/"
    And I go back
    And I go forward
    And I refresh

  @homework6_2_3
  Scenario: Resolution
    Given I go to "https://www.google.com/"
    And I Change resolution to "desktop"

  @homework6_2_4
  Scenario: Submit the form
    Given I go to "https://skryabin.com/market/quote.html"
    And I fill out all required fields
    And I submit the page

  @homework6_2_5
  Scenario: Email field behavior
    Given I go to "https://skryabin.com/market/quote.html"
    And I verify email field behavior

  @homework6_2_6
  Scenario:  Validations of submitted form data
    Given I go to "https://skryabin.com/market/quote.html"
    And I fill out all required fields
    And I submit the page
    And I verify form data

  @homework6_2_7
  Scenario: Optional fields
    Given I go to "https://skryabin.com/market/quote.html"
    And I fill out all required fields
    And I fill out optional fields
    And I submit the page

  @homework6_2_8
  Scenario: Alerts
    Given I go to "https://skryabin.com/market/quote.html"
    And I accept agreement
    And I dismiss agreement
