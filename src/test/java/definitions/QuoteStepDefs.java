package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.QuoteForm;
import pages.QuoteResult;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getData;

public class QuoteStepDefs {

    QuoteForm form = new QuoteForm();
    QuoteResult result = new QuoteResult();

    @Given("I open {string} page")
    public void iOpenPage(String page) throws InterruptedException {
        switch (page) {
            case "quote":
                form.open();
                break;

            default:
                throw new RuntimeException("Unknown page: " + page);
        }
    }

    @When("I fill out required fields for {string} oop")
    public void iFillOutRequiredFieldsForOop(String role) {
        Map<String, String> user = getData(role);
        form.fillUsername(user.get("username"));
        form.fillEmail(user.get("email"));
        form.fillBothPasswords(user.get("password"));
        form.fillName(user.get("firstName"), user.get("lastName"));
        form.checkWithPrivacyPolicy();
    }

    @And("I submit the form oop")
    public void iSubmitTheFormOop() {
        form.submit();
    }

    @Then("I verify required fields for {string} oop")
    public void iVerifyRequiredFieldsForOop(String role) {
        Map<String, String> user = getData(role);
        String resultText = result.getResultText();
        assertThat(resultText).contains(user.get("username"));
        assertThat(resultText).contains(user.get("email"));
        assertThat(resultText).contains(user.get("firstName"));
        assertThat(resultText).contains(user.get("lastName"));

        assertThat(result.isAgreedToPrivacyPolicy()).isTrue();
        assertThat(result.getPasswordText()).isEqualTo("[entered]");

    }

    @When("I fill out optional fields for {string} oop")
    public void iFillOutOptionalFieldsForOop(String role) {
        Map<String, String> user = getData(role);
        form.fillContactInfo(user.get("contactPersonName"), user.get("contactPersonPhone"));
    }

    @Then("I verify optional fields for {string} oop")
    public void iVerifyOptionalFieldsForOop(String role) {
        Map<String, String> user = getData(role);

        assertThat(result.getResultText()).contains(user.get("contactPersonName"));
        assertThat(result.getResultText()).contains(user.get("contactPersonPhone"));
    }

    @Then("I see {string} error message {string}")
    public void iSeeErrorMessage(String errorField, String errorMessage) {
        String actualError = form.getErrorFieldText(errorField);
        assertThat(actualError).isEqualTo(errorMessage);

//        switch (errorField) {
//            case "username":
//                assertThat(form.getUsernameErrorText()).isEqualTo(errorMessage);
//                break;
//            case "email":
//                assertThat(form.getEmailErrorText()).isEqualTo(errorMessage);
//                break;
//            default:
//                throw new RuntimeException("Unrecognized field: " + errorField);
//        }

    }

    @Then("I don't see {string} error message")
    public void iDonTSeeErrorMessage(String errorField){
        assertThat(form.isErrorFieldDisplayed(errorField)).isFalse();

    }

    @When("I fill out {string} field with {string}")
    public void iFillOutFieldWith(String fieldName, String fieldValue) {
        switch(fieldName) {
            case "username":
                form.fillUsername(fieldValue);
                break;
            case "email":
                form.fillEmail(fieldValue);
                break;
            case "password":
               form.fillPassword(fieldValue);
               break;
            case "confirmPassword":
                form.fillConfirmPassword(fieldValue);
                break;

            default:
                System.out.println("input error");
        }
    }

    @When("I fill out name field with first name {string} and last name {string}")
    public void iFillOutNameFieldWithFirstNameAndLastName(String firstNameValue, String lastNameValue) {
        form.fillName(firstNameValue, lastNameValue);
    }

    @When("I fill out name field with first name {string}, middle name {string}, last name {string}")
    public void iFillOutNameFieldWithFirstNameMiddleNameLastName(String firstNameValue, String middleNameValue, String lastNameValue) {
        form.fillName(firstNameValue, middleNameValue, lastNameValue);
    }

    @Then("I verify {string} field value {string}")
    public void iVerifyFieldValue(String fieldName, String fieldValue) {
        switch(fieldName) {
            case "name":
                assertThat(form.getNameText()).isEqualTo(fieldValue);
                break;

            default:
                System.out.println("input error");
        }
    }
}