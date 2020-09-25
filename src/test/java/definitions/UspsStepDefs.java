package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.Th;
import org.assertj.core.data.Percentage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.html5.WebStorage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;


import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static support.TestContext.*;

public class UspsStepDefs {

    UspsHome uspsHome = new UspsHome();
    UspsLookupByZip uspsLookupByZip = new UspsLookupByZip();
    UspsByAddressForm uspsByAddressForm = new UspsByAddressForm();
    UspsByAddressResult uspsByAddressResult = new UspsByAddressResult();
    UspsCalculator uspsCalculator = new UspsCalculator();

    @When("I go to Lookup ZIP page by address")
    public void iGoToLookupZIPPageByAddress() throws InterruptedException {
        WebElement mailAndShip = getDriver().findElement(By.xpath("//a[@id='mail-ship-width']"));
        getActions().moveToElement(mailAndShip).perform();
        getDriver().findElement(By.xpath("//li[@class='tool-zip']//a[contains(@href,'Zip')]")).click();
        getDriver().findElement(By.xpath("//div[@id='zip-lookup-welcome']//a[contains(@href,'byaddress')][contains(@class,'primary')]")).click();
    }

    @And("I fill out {string} street, {string} city, {string} state")
    public void iFillOutStreetCityState(String street, String city, String state) throws InterruptedException {

        getDriver().findElement(By.xpath("//input[@id='tAddress']")).sendKeys(street);
        getDriver().findElement(By.xpath("//input[@id='tCity']")).sendKeys(city);

        Select stateSelect = new Select(getDriver().findElement(By.xpath("//select[@id='tState']")));
        stateSelect.selectByValue(state);
//        getDriver().findElement(By.xpath("//select[@id='tState']//option[@value='"  + state +  "']")).click();

        getDriver().findElement(By.xpath("//a[@id='zip-by-address']")).click();

    }

    @Then("I validate {string} zip code exists in the result")
    public void iValidateZipCodeExistsInTheResult(String zip) throws InterruptedException {
        // explicit wait
        WebElement result = getDriver().findElement(By.xpath("//div[@id='zipByAddressDiv']"));

        getWait().until(ExpectedConditions.textToBePresentInElement(result, zip));
//        wait.until(driver -> result.getText().length() > 0);
//        assertThat(result.getText()).contains(zip);
    }

    @When("I perform {string} search")
    public void iPerformSearch(String text) {
        WebElement searchMenu = getDriver().findElement(By.xpath("//li[contains(@class, 'nav-search')]"));
        WebElement searchInput = getDriver().findElement(By.xpath("//input[@id='global-header--search-track-search']"));
        getActions()
                .moveToElement(searchMenu)
                .sendKeys(searchInput, text)
                .sendKeys(Keys.ENTER)
                .perform();
    }

    @And("I set {string} in filters")
    public void iSetInFilters(String filter) throws InterruptedException {
        WebElement spinner = getDriver().findElement(By.xpath("//div[@class='white-spinner-container']"));
//        wait.until(ExpectedConditions.invisibilityOf(spinner));
//        getDriver().findElement(By.xpath("//a[@class='dn-attr-a'][text()='" + filter + "']")).click();
        WebElement filterElement = getDriver().findElement(By.xpath("//a[@class='dn-attr-a'][text()='" + filter + "']"));
        getExecutor().executeScript("arguments[0].click();", filterElement);

        getWait().until(invisibilityOf(spinner));
    }

    @Then("I verify that {string} results found")
    public void iVerifyThatResultsFound(String expectedCount) {
        int expectedSize = Integer.parseInt(expectedCount);

        String heading = getDriver().findElement(By.xpath("//span[@id='searchResultsHeading']")).getText();
        String headingCount = heading.replaceAll("\\D*", "");
        int parsedHeadingCount = Integer.parseInt(headingCount);

        List<WebElement> results = getDriver().findElements(By.xpath("//ul[@id='records']/li"));
        int actualSize = results.size();

        assertThat(actualSize).isEqualTo(expectedSize);
        assertThat(actualSize).isEqualTo(parsedHeadingCount);
    }

    @When("I select {string} in results")
    public void iSelectInResults(String result) {
        getDriver().findElement(By.xpath("//span[text()='Priority Mail | USPS']")).click();
    }

    @And("I click {string} button")
    public void iClickButton(String buttonTitle) throws InterruptedException {
        int numOfWin = getDriver().getWindowHandles().size();
        while (getDriver().getWindowHandles().size() < numOfWin + 1) {
            getDriver().findElement(By.xpath("//a[contains(text(),'" + buttonTitle + "')]")).click();
            Thread.sleep(100);
        }
    }

    @Then("I validate that Sign In is required")
    public void iValidateThatSignInIsRequired() {
        String originalWindow = getDriver().getWindowHandle();
        // switch to new window
        for (String handle : getDriver().getWindowHandles()) {
            getDriver().switchTo().window(handle);
        }

        getWait(10).until(ExpectedConditions.titleContains("Sign In"));

        getDriver().findElement(By.xpath("//button[@id='btn-submit']")).click();
        getDriver().findElement(By.id("btn-submit")).click();
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='error-username']")));
        getWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='error-password']")));

        // switch back
        getDriver().switchTo().window(originalWindow);
    }

    @When("I go to {string} under {string}")
    public void iGoToUnder(String subMenu, String menu) throws InterruptedException {
        WebElement menuElement = getDriver().findElement(By.xpath("//a[@role='menuitem'][text()='" + menu +"']"));
        getActions().moveToElement(menuElement).perform();
        getDriver().findElement(By.xpath("//li/a[text()='" + subMenu +"']")).click();
    }

    @And("I search for {string}")
    public void iSearchFor(String address) {
        getDriver().findElement(By.xpath("//input[@id='address']")).sendKeys(address);
        getDriver().findElement(By.xpath("//button[contains(@class,'search-form-field-icon-search')]")).click();
    }

    @And("I click {string} on the map")
    public void iClickOnTheMap(String text) throws InterruptedException {
        WebElement overlay = getDriver().findElement(By.xpath("//div[@id='eddm_overlay-progress']"));
        getWait().until(visibilityOf(overlay));
        getWait(10).until(invisibilityOf(overlay));
        getDriver().findElement(By.xpath("//a[contains(text(),'" + text + "')]")).click();
    }

    @When("I click {string} on the table")
    public void iClickOnTheTable(String text) {
        getDriver().findElement(By.xpath("//div[@id='route-table']//a[contains(text(),'" + text + "')]")).click();
    }

    @And("I close modal window")
    public void iCloseModalWindow() throws InterruptedException {
        getDriver().findElement(By.xpath("//div[@id='modal-box-closeModal']")).click();
    }

    @Then("I verify that summary of all rows of Cost column is equal Approximate Cost in Order Summary")
    public void iVerifyThatSummaryOfAllRowsOfCostColumnIsEqualApproximateCostInOrderSummary() throws ParseException {
        String totalCountString = getDriver().findElement(By.xpath("//a[contains(@class, 'totalsArea')]")).getText();
        int totalCount = Integer.parseInt(totalCountString.replaceAll("\\D*", ""));

        By costListSelector = By.xpath("//td[@idx='7']");
        List<WebElement> costList = getDriver().findElements(costListSelector);
        System.out.println("Expected elements size: " + totalCount);

        // dealing with infinite scroll
        while (costList.size() < totalCount) {
            System.out.println("Actual elements size: " + costList.size());
            int lastIndex = costList.size() - 1;
            getActions().moveToElement(costList.get(lastIndex)).perform();
            costList = getDriver().findElements(costListSelector);
        }
        System.out.println("Actual elements size: " + costList.size());

        Locale locale = new Locale("en", "US");
        NumberFormat formatter = NumberFormat.getCurrencyInstance(locale);
        double actualTotal = 0;
        for (WebElement cost : costList) {
            double costTotal = formatter.parse(cost.getText()).doubleValue();
            actualTotal += costTotal;
        }
        System.out.println("Actual total " + actualTotal);

        String expectedTotalString = getDriver().findElement(By.xpath("//span[@class='approx-cost']")).getText();
        double expectedTotal = Double.parseDouble(expectedTotalString);
        System.out.println("Expected total " + expectedTotal);

        assertThat(actualTotal).isCloseTo(expectedTotal, Percentage.withPercentage(1));

    }


    public void upsTest() {
        getDriver().findElement(By.xpath("//*[@name='postal']")).sendKeys("94022");
        WebElement city = getDriver().findElement(By.xpath("//input[@name='city']"));
        WebElement state = getDriver().findElement(By.xpath("//select[@id='originstate']/option[text()='California']"));

        getWait().until(textToBePresentInElementValue(city, "LOS ALTOS"));
        getWait().until(elementToBeSelected(state));

    }


    @When("I go to Lookup ZIP page by address oop")
    public void iGoToLookupZIPPageByAddressOop() {
        uspsHome.goToLookupByZip();
        uspsLookupByZip.clickFindByAddress();
    }

    @And("I fill out {string} street, {string} city, {string} state oop")
    public void iFillOutStreetCityStateOop(String street, String city, String state) {
        uspsByAddressForm.fillStreet(street);
        uspsByAddressForm.fillCity(city);
        uspsByAddressForm.selectState(state);
        uspsByAddressForm.clickFind();
    }

    @Then("I validate {string} zip code exists in the result oop")
    public void iValidateZipCodeExistsInTheResultOop(String zip) {
        String actualTotalResult = uspsByAddressResult.getSearchResult();
        assertThat(actualTotalResult).contains(zip);

        boolean areAllItemsContainZip = uspsByAddressResult.areAllResultsContainZip(zip);
        assertThat(areAllItemsContainZip).isTrue();
    }

    @Given("I go to {string} page")
    public void iGoToPage(String url) {
        if(url.equals("usps")){
            getDriver().get("https://www.usps.com/");
        }
        else {
            System.out.println("incorrect input");
        }

    }

    @When("I go to Calculate Price Page oop")
    public void iGoToCalculatePricePage() throws InterruptedException {
        uspsHome.goToCalculatePrice();
    }

    @And("I select {string} with {string} shape oop")
    public void iSelectWithShape(String country, String shape) {
        uspsCalculator.selectCountry(country);
        uspsCalculator.selectPostcard();
    }

    @And("I define {string} quantity oop")
    public void iDefineQuantity(String quantity) {
        uspsCalculator.selectQuantity(quantity);
    }

    @Then("I calculate the price and validate cost is {string} oop")
    public void iCalculateThePriceAndValidateCostIs(String cost) {
        uspsCalculator.calculate();
        assertThat(uspsCalculator.getActualCost()).isEqualTo(cost);
    }









}