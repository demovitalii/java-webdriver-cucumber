package definitions;



import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.QuoteForm;
import pages.QuoteResult;
import pages.Shipment;
import pages.UpsStartPage;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getData;



public class UpsStepDefs {

    UpsStartPage home = new UpsStartPage();
    Shipment ship = new Shipment();

    @Given("I open {string} page oop")
    public void iOpenPage(String page) throws InterruptedException {
        switch (page) {
            case "ups":
                home.open();
                break;

            default:
                throw new RuntimeException("Unknown page: " + page);
        }
    }

    @And("I open Shipping menu oop")
    public void iOpenShippingMenuOop() {
        home.shipmentMenuOpen();
    }

    @And("I go to Create a Shipment oop")
    public void iGoToCreateAShipmentOop() {
        home.createShipment();
    }


    @When("I fill out origin shipment fields for {string} oop")
    public void iFillOutOriginShipmentFieldsForOop(String client) {
        Map<String, String> user = getData(client);
        ship.waitToBeReady();
        ship.selectUS();
        ship.fillName(user.get("name"));
        ship.fillAddress(user.get("address"));
        ship.fillCity(user.get("city"));
        ship.fillPhone(user.get("phone"));
        ship.fillZip(user.get("zip"));
        ship.fillEmail(user.get("email"));
        ship.selectCalifornia();
    }

    @And("I submit the shipment form oop")
    public void iSubmitTheShipmentFormOop(){

        ship.submit();

    }


    @Then("I verify origin shipment fields submitted for {string} oop")
    public void iVerifyOriginShipmentFieldsSubmittedForOop(String client) {
        Map<String, String> user = getData(client);
        if(ship.verify().contains(user.get("name"))&&ship.verify().contains(user.get("zip"))&&ship.verify().contains(user.get("city"))){
            System.out.println("OK");
        }
        else {
            System.out.println("NOT OK");
        }
        
        
    }

    @And("I cancel the shipment form oop")
    public void iCancelTheShipmentFormOop() {
        ship.cancel();
    }


    @Then("I verify shipment form is reset oop")
    public void iVerifyShipmentFormIsResetForOop() {

        ship.noUPSection();
    }
}
