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

  @homework7_1_1
  Scenario: Swap elements
    And I swap 3 and 5 element in 5 2 9 7 3 array

  @homework7_1_2
  Scenario: Divisibility
    And I check divisibility of 1 by 2 , 1 and both

  @homework7_2
  Scenario: Validate ZIP code for Portnov Computer School
    Given I go to usps page
    When I go to Lookup ZIP page by address
    And I fill out "4970 El Camino Real" street, "Los Altos" city, "CA" state
    Then I validate "94022" zip code exists in the result

  @homework8_1_1
  Scenario: Function that prints all numbers from 0 up to n
    And I print all numbers from zerro up to 10

  @homework8_1_2
  Scenario: Function that supports also negative numbers
    And I print all numbers from -8 up to 10

  @homework8_1_3
  Scenario: Function that prints all integer array
    And I print integer array

  @homework8_1_4
  Scenario: Function that prints all even numbers from integer array
    And I print all even numbers from integer array

  @homework8_1_5
  Scenario: Function that checks if array is empty
    And I check if array is empty

  @homework8_1_6
  Scenario: Function that checks if array contains another element
    And I check if array contains another element

  @homework8_1_7
  Scenario: Print all the numbers up to the argument with conditions
    And I print all numbers up to 20 with conditions Fizz and Buzz

  @homework8_2_1
  Scenario: Calculate price
    Given I go to usps page
    When I go to Calculate Price Page
    And I select "Canada" with "Postcard" shape
    And I define "2" quantity
    Then I calculate the price and validate cost is "$2.40"

  #@homework8_2_2
  #Scenario: Verify location
   # Given I go to usps page
   # When I perform "Free Boxes" search
    #And I set "Mail & Ship" in filters
    #Then I verify that "6" results found
    #When I select "Priority Mail | USPS" in results
    #And I click "Ship Now" button
    #Then I validate that Sign In is required

  @homework8_2_3
  Scenario: Quadcopters delivery
    Given I go to usps page
    When I go to "Help" tab
    And I perform "Quadcopters delivery" help search
    Then I verify that no results of "Quadcopters delivery" available in help search

  @homework8_2_4
  Scenario: Phone number of the nearest Mail Pickup
    Given I go to usps page
    When I navigate to Find a Location page
    And I filter by "Post Offices" location types, "Pickup Services" services, "Accountable Mail" available services
    And I provide data as "4970 El Camino Real 110" street, "Los Altos" city, "CA" state
    Then I verify phone number is "800-275-8777"


  @homework9_1
  Scenario: Converter page
    Given I go to converter page
    And I click on "length"
    And I select from "Mile" and to "Kilometer"
    And I set from "50" value and verify result is "79.4"


  @homework9_2
  Scenario: Verify calculator result
    Given I go to calculator page
    When I navigate to "Auto Loan Calculator"
    And I clear all calculator fields
    And I calculate
    Then I verify "Please provide a positive auto price." calculator error
    Then I verify "Please provide a positive interest value." calculator error
    And I enter "25000" price, "60" months, "4.5" interest, "5000" downpayment, "0" trade-in, "California" state, "7" percent tax, "300" fees
    And I calculate
    Then I verify monthly pay is "$372.86"

  @homework9_3
  Scenario: Every door direct mail
    Given I go to usps page
    When I go to Every Door Direct Mail under Business
    And I search for "4970 El Camino Real, Los Altos, CA 94022"
    And I click Show Table on the map
    When I click Select All on the table
    And I close modal window
    Then I verify that summary of all rows of Cost column is equal Approximate Cost in Order Summary

    @homework10_1_1
    Scenario:  User inputs two numbers which are divided by 5
     And user input 20 and 100 which are divided by 5

    @homework10_1_2
    Scenario: Addition
      And I add 5 to 6 , print result and verify it

    @homework10_1_3
    Scenario: Reverse every third character of a string
      And I reverse every third character of a string "WebDriver"

    @homework10_1_4
    Scenario: Speak like Yoda
      And I translate "I Will DO" to YODA language

    @homework10_2_1
    Scenario: UPS end to end first
      Given I go to ups page
      And I open Shipping menu
      And I go to Create a Shipment
      When I fill out origin shipment fields
      And I submit the shipment form
      Then I verify origin shipment fields submitted
      And I cancel the shipment form
      Then I verify shipment form is reset

    @homework10_2_2
    Scenario: UPS end to end full
      Given I go to ups page
      And I open Shipping menu
      And I go to Create a Shipment
      When I fill out origin shipment fields
      And I submit the shipment form
      Then I verify origin shipment fields submitted
      When I fill out destination shipment fields
      When I submit the shipment form
      And I set packaging type and weight
      When I submit the shipment form
      Then I verify total charges appear
      And I select cheapest delivery option
      And I submit the shipment form
      And I set description and check Saturday Delivery type
      Then I verify total charges changed
      When I submit the shipment form
      And I select Paypal payment type
     # And I submit the shipment form
      Then I review all recorded details on the review page
      And I cancel the shipment form
      Then I verify shipment form is reset


    @homework11_coding
    Scenario: Coding chalanges11
      And I Write a function that find two max numbers in an array
      And  I Write a function that finds if array contains duplicates
      And I Write a function that finds if "avvna" is palindrome
      And I Write a function that counts number of each character in a "123154654"


    @homework11_2_1
    Scenario Outline: Verify calculator result with examples
      Given I go to calculator page
      When I navigate to "Auto Loan Calculator"
      And I clear all calculator fields
      And I calculate
      Then I verify "Please provide a positive auto price." calculator error
      Then I verify "Please provide a positive interest value." calculator error
      And I enter "<price>" price, "<month>" months, "<interest>" interest, "<downpayment>" downpayment, "<tradein>" trade-in, "<state>" state, "<tax>" percent tax, "<fees>" fees
      And I calculate
      Then I verify monthly pay is "<pay>"
      Examples:
        |price|month|interest|downpayment|tradein|state     |tax|fees|pay   |
        |25000|60   |4.5     |5000       |0      |California|7  |300 |372.86|

    @homework11_2_2
    Scenario Outline: Converter page with examples
      Given I go to converter page
      And I click on "<type>"
      And I select from "<from>" and to "<to>"
      And I set from "<fromValue>" value and verify result is "<toValue>"
      Examples:
        |type  |from    |to         |fromValue|toValue |
        |length|Mile    |Kilometer  |50       |79.35   |
        |length|Mile    |Kilometer  |50       |79.41   |

    @homework11_2_3
    Scenario: UPS end to end full2
      Given I go to ups page
      And I open Shipping menu
      And I go to Create a Shipment
      When I fill out origin shipment fields
      And I submit the shipment form
      Then I verify origin shipment fields submitted
      When I fill out destination shipment fields
      When I submit the shipment form
      And I set packaging type and weight
      When I submit the shipment form
      Then I verify total charges appear
      And I select cheapest delivery option
      And I submit the shipment form
      And I set description and check Saturday Delivery type
      Then I verify total charges changed
      When I submit the shipment form
      And I select Paypal payment type
     # And I submit the shipment form
      Then I review all recorded details on the review page
      And I cancel the shipment form
      Then I verify shipment form is reset


    @homework12_1
    Scenario: Wrong store id does not match
      Given I go to usps page
      When I go to Postal Store tab
      And I enter "12345" into store search
      Then I search and validate no products found

    @homework12_2
    Scenario: One item found
      Given I go to usps page
      When I go to Stamps under Postal Store
      And choose mail service Priority Mail
      Then I Verify 1 items found

    @homework12_3
    Scenario: Verify color
      Given I go to usps page
      When I go to Stamps under Postal Store
      When I unselect Stamps checkbox
      And select Vertical stamp Shape
      And I click Blue color
      Then I verify "Blue" and "Vertical" filters
      Then I Verify 12 items found
      And I verify that items below 12 dollars exists

    @homework12_4
    Scenario: Verify location
      Given I go to usps page
      When I perform Passports search
      And I select "Passport Application" in results
      And I click "Schedule an Appointment" button
      And verify "Passport Renewal" service exists

    @homework12_5
    Scenario: PO Box
      Given I go to usps page
      When I go to PO Boxes under Track & Manage
      And I reserve new PO box for "94022"
      Then I verify that "Los Altos — Post Office™" present
      And I verify that "Size 5-XL" PO Box is available in Los Altos — Post Office™




