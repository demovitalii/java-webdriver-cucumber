package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static support.TestContext.*;
import org.openqa.selenium.interactions.Actions;

public class CareerRecruiter extends Page {

    public void deleteCandidate(String position) {
        String candidateDeleteButtonXpath = "//h4[contains(text(),'" + position + "')]/../../..//i/..";
        String candidateDivXpath = "//h4[contains(text(),'" + position + "')]/../../..";
        WebElement candidateDeleteButton = getDriver().findElement(By.xpath(candidateDeleteButtonXpath));
        WebElement candidateDiv = getDriver().findElement(By.xpath(candidateDivXpath));
        new Actions(getDriver()).moveToElement(candidateDiv).perform();
        candidateDeleteButton.click();
    }
    public WebElement body(){
        String bodyXpath = "//body";
        return getDriver().findElement(By.xpath(bodyXpath));

    }






}