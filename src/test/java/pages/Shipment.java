package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;

public class Shipment extends Page {



    @FindBy(xpath = "//select[@id='origincountry']")
    private WebElement originCountry;

    @FindBy(xpath = "//option[contains(text(),'United States')]")
    private WebElement unitedStatesOption;

    @FindBy(xpath = "//input[@id='originname']")
    private WebElement name;

    @FindBy(xpath = "//input[@id='originaddress1']")
    private WebElement address;

    @FindBy(xpath = "//input[@id='originpostal']")
    private WebElement zip;

    @FindBy(xpath = "//input[@id='origincity']")
    private WebElement city;

    @FindBy(xpath = "//option[contains(text(),'California')]")
    private WebElement stateCalifornia;

    @FindBy(xpath = "//input[@id='originemail']")
    private WebElement email;

    @FindBy(xpath = "//input[@id='originphone']")
    private WebElement phone;

    @FindBy(xpath = "//button[@id='nbsBackForwardNavigationContinueButton']")
    private WebElement submitButton;

    @FindBy(xpath = "//button[@id='nbsBackForwardNavigationCancelShipmentButton']")
    private WebElement cancelButton;

    @FindBy(xpath = "//div[@class='ups-section']")
    private WebElement upssection;

    @FindBy(xpath = "//button[@id='nbsCancelShipmentWarningYes']")
    private WebElement yes;

    @FindBy(xpath = "//div[@class='ups-wrap_inner']//div[@class='ups-wrap']")
    private WebElement hello;


    //methods

    //public void open() {        getDriver().get(url);    }


    public void waitToBeReady() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(originCountry));

    }

    public void selectUS() {
        unitedStatesOption.click();

    }
    public void selectCalifornia() {
        stateCalifornia.click();

    }
    public void fillName(String value) {
        name.sendKeys(value);

    }
    public void fillAddress(String value) {
        address.sendKeys(value);

    }
    public void fillZip(String value) {
        zip.sendKeys(value);

    }
    public void fillCity(String value) {
        city.sendKeys(value);

    }
    public void fillEmail(String value) {
        email.sendKeys(value);

    }
    public void fillPhone(String value) {
        phone.sendKeys(value);

    }

    public void submit(){
        JavascriptExecutor ex = (JavascriptExecutor) getDriver();
        ex.executeScript("arguments[0].click()", submitButton);


    }

    public void cancel() {
        JavascriptExecutor ex = (JavascriptExecutor) getDriver();
        ex.executeScript("arguments[0].click()", cancelButton);
        yes.click();

    }

    public String verify(){
       return upssection.getText();

    }
    public void noUPSection() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);

        wait.until(ExpectedConditions.textToBePresentInElement(hello, "Hello. Where are you shipping from?"));

    }

}