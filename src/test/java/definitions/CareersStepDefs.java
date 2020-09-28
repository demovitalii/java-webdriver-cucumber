package definitions;

import cucumber.api.java.en.And;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;


import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.*;

import static support.TestContext.getData;

public class CareersStepDefs {

    CareerLogin CareerLog = new CareerLogin();
    CareerMain CareerMain = new CareerMain();
    CareerRecruiter CareerRec = new CareerRecruiter();

    @And("I login as {string}")
    public void iLoginAs(String who) {
            CareerMain.goToLogin();
            Map<String, String> user = getData(who);
            CareerLog.enterLogin(user.get("email"));
            CareerLog.enterPassword(user.get("password"));
            CareerLog.submit();

    }

    @Then("I verify {string} login")
    public void iVerifyLogin(String role) {
        if (role.equals("recruiter")){
           assertThat(CareerMain.returnRecruitButton()).isNotNull();
        }
    }

    @When("I remove {string} position")
    public void iRemovePosition(String position){
        CareerMain.goToRecruiter();
        CareerRec.deleteCandidate(position);
    }

    @And("I verify {string} position is removed")
    public void iVerifyPositionIsRemoved(String position) throws InterruptedException {
        Thread.sleep(1000);
        assertThat(CareerRec.body().getText()).doesNotContainPattern(position); //why .doesNotContain(position) don't work?
    }
}
