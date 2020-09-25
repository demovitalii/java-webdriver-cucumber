package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static support.TestContext.getWait;

public class UspsCalculator extends UspsHeader {

    @FindBy(id="CountryID")
    private WebElement contryId;

    @FindBy(id = "option_1")
    private WebElement postcardOption;

    @FindBy(id = "quantity-0")
    private WebElement quantity;

    @FindBy(xpath = "//input[@value='Calculate']")
    private WebElement calculateButton;

    @FindBy(xpath = "//div[@id='total']")
    private WebElement actualTotal;

    public void selectCountry(String country) {
        Select stateSelect = new Select(contryId);
        stateSelect.selectByVisibleText(country);
    }

    public void selectPostcard() {
        postcardOption.click();
    }

    public void selectQuantity(String qnt) {
        quantity.sendKeys(qnt);
    }

    public void calculate() {
        calculateButton.click();
    }

    public String getActualCost() {
        return actualTotal.getText();
    }



}