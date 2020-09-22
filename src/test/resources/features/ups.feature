@ups
  Feature: UPS
    @ups3
    Scenario: UPS end to end first OOP
      Given I open "ups" page oop
      And I open Shipping menu oop
      And I go to Create a Shipment oop
      When I fill out origin shipment fields for "ship" oop
      And I submit the shipment form oop
      Then I verify origin shipment fields submitted for "ship" oop
      And I cancel the shipment form oop
      Then I verify shipment form is reset oop