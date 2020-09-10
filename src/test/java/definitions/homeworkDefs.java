package definitions;

import cucumber.api.java.en.And;

import java.util.*;
import java.util.concurrent.TimeUnit;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.assertj.core.api.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.assertj.core.api.Assertions.assertThat;
import java.util.Arrays;



import static support.TestContext.getDriver;

public class homeworkDefs {
    @And("I print if {string} is positive")
    public void iPrintIfIsPositive(String arg0) {
        int i = Integer.parseInt(arg0);

        if (i < 0) {
            System.out.println("negative");
        } else if (i > 0) {
            System.out.println("positive");
        } else {
            System.out.println("zerro");
        }

    }

    @And("I print {string} th day of week")
    public void iPrintThDayOfWeek(String arg0) {
        int i = Integer.parseInt(arg0) - 1;
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        System.out.println(days[i]);
    }


    @And("I create map with keys {string} , {string} and values {string}, {string} and swap values")
    public void iCreateMapWithKeysAndValuesAndSwagValues(String k1, String k2, String v1, String v2) {
        Map<String, String> info = new LinkedHashMap<>();
        info.put(k1, v1);
        info.put(k2, v2);
        System.out.println("Original Map:");
        for (String key : info.keySet()) {
            System.out.println(key + ": " + info.get(key));
        }
        String valueOriginal1 = info.get(k1);
        info.put(k1, info.get(k2));
        info.put(k2, valueOriginal1);
        System.out.println("Swapped Map:");
        for (String key : info.keySet()) {
            System.out.println(key + ": " + info.get(key));
        }
    }

    @And("I go to {string}")
    public void iGoToAndPrintDetails(String pageUrl) {
        getDriver().get(pageUrl);
    }

    @And("I print page details")
    public void iPrintPageDetails() {
        System.out.println(getDriver().getTitle());
        System.out.println(getDriver().getCurrentUrl());
        System.out.println(getDriver().getWindowHandle());
        System.out.println(getDriver().getPageSource());
    }

    @And("I go back")
    public void iGoBack() {
        getDriver().navigate().back();
    }

    @And("I go forward")
    public void iGoForward() {
        getDriver().navigate().forward();
    }

    @And("I refresh")
    public void iRefresh() {
        getDriver().navigate().refresh();

    }

    @And("I Change resolution to {string}")
    public void iChangeResolutionTo(String device) throws InterruptedException {
        Dimension dPhone = new Dimension(400, 768);
        Dimension dTablet = new Dimension(800, 768);
        Dimension dDesktop = new Dimension(1024, 768);

        if (device.equalsIgnoreCase("phone")) {
            getDriver().manage().window().setSize(dPhone);
        } else if (device.equalsIgnoreCase("desktop")) {
            getDriver().manage().window().setSize(dDesktop);
        } else if (device.equalsIgnoreCase("tablet")) {
            getDriver().manage().window().setSize(dTablet);
        } else {
            System.out.println("unsupported device: " + device);
        }
        Thread.sleep(5000);

    }

    @And("I fill out all required fields")
    public void iFillOutAllRequiredFields() {
        getDriver().findElement(By.xpath("//input[@id='name']")).click();
        getDriver().findElement(By.xpath("//input[@id='firstName']")).sendKeys("First");
        getDriver().findElement(By.xpath("//input[@id='middleName']")).sendKeys("Middle");
        getDriver().findElement(By.xpath("//input[@id='lastName']")).sendKeys("Last");
        getDriver().findElement(By.xpath("//span[contains(text(),'Save')]")).click();

        getDriver().findElement(By.xpath("//input[@name='username']")).sendKeys("Username");
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("Email@example.com");
        getDriver().findElement(By.xpath("//input[@id='password']")).sendKeys("159159159");
        getDriver().findElement(By.xpath("//input[@id='confirmPassword']")).sendKeys("159159159");
        getDriver().findElement(By.xpath("//input[@name='agreedToPrivacyPolicy']")).click();


    }

    @And("I submit the page")
    public void iSubmitThePage() throws InterruptedException {
        getDriver().findElement(By.xpath("//button[@id='formSubmit']")).click();
        Thread.sleep(5000);
    }

    @And("I verify email field behavior")
    public void iVerifyEmailFieldBehavior() throws InterruptedException {
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("123456");
        Thread.sleep(2000);
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys(Keys.BACK_SPACE);
        Thread.sleep(2000);
        getDriver().findElement(By.xpath("//input[@name='email']")).clear();
        Thread.sleep(2000);
        getDriver().findElement(By.xpath("//input[@name='email']")).sendKeys("123@example.com");
        Thread.sleep(2000);

    }

    @And("I verify form data")
    public void iVerifyFormData() {

        assertThat(getDriver().findElement(By.xpath("//b[@name='firstName']")).getText()).isEqualTo("First");
        assertThat(getDriver().findElement(By.xpath("//b[@name='email']")).getText()).contains("@example.com");
        assertThat(getDriver().findElement(By.xpath("//button[@id='return']")).isDisplayed()).isTrue();
        //assertThat(getDriver().findElement(By.xpath("//*[@id='']")).isDisplayed()).isFalse();
    }

    @And("I fill out optional fields")
    public void iFillOutOptionalFields() {
        getDriver().findElement(By.xpath("//input[@name='phone']")).sendKeys("123456789");
        getDriver().findElement(By.xpath("//option[contains(text(),'Japan')]")).click();
        getDriver().findElement(By.xpath("//input[@value='male']")).click();
        getDriver().findElement(By.xpath("//input[@name='allowedToContact']")).click();
        getDriver().findElement(By.xpath("//textarea[@id='address']")).sendKeys("Earth");
        getDriver().findElement(By.xpath("//option[contains(text(),'Toyota')]")).click();
        getDriver().switchTo().frame("additionalInfo");
        getDriver().findElement(By.xpath("//input[@id='contactPersonName']")).sendKeys("conName");
        getDriver().findElement(By.xpath("//input[@id='contactPersonPhone']")).sendKeys("conPhone");
        getDriver().switchTo().defaultContent();


    }


    @And("I accept agreement")
    public void iAcceptAgreement() {
        getDriver().findElement(By.xpath("//button[@id='thirdPartyButton']")).click();
        getDriver().switchTo().alert().accept();
    }

    @And("I dismiss agreement")
    public void iDismissAgreement() {
        getDriver().findElement(By.xpath("//button[@id='thirdPartyButton']")).click();
        getDriver().switchTo().alert().dismiss();
    }


    @And("I swap {int} and {int} element in {int} {int} {int} {int} {int} array")
    public void iSwapAndElementInArray(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5, int arg6) {
        int myArray[] = new int[]{arg2, arg3, arg4, arg5, arg6};
        System.out.print("Original array: [");
        for (int i = 0; i < 4; i++) {
            System.out.print(myArray[i] + ", ");
        }
        System.out.println(myArray[4] + "]");

        int firstIndex = arg0 - 1;
        int secondIndex = arg1 - 1;

        int temp = myArray[secondIndex];
        myArray[secondIndex] = myArray[firstIndex];
        myArray[firstIndex] = temp;

        System.out.print("Swapped array: [");
        for (int i = 0; i < 4; i++) {
            System.out.print(myArray[i] + ", ");
        }
        System.out.println(myArray[4] + "]");
    }

    @And("I check divisibility of {int} by {int} , {int} and both")
    public void iCheckDivisibilityOfByAndBoth(int n, int d1, int d2) {
        if (d1 == 0 || d2 == 0) {
            System.out.println(n + " division by zero error");
        } else if (d1 == d2) {
            System.out.println("Dividers are equal!!!");
            System.out.println("But we check anyway:");
            if (n % d1 == 0) {
                System.out.println(n + " divisible by " + d1);
            } else {
                System.out.println("Not Divisible");
            }
        } else {
            if (n % d1 == 0 && n % d2 == 0) {
                System.out.println(n + " divisible by " + d1 + " and " + d2);
            } else if (n % d1 == 0) {
                System.out.println(n + " divisible by " + d1);
            } else if (n % d2 == 0) {
                System.out.println(n + " divisible by " + d2);
            } else {
                System.out.println("Not Divisible");
            }
        }

    }

    @Given("I go to usps page")
    public void iGoToUspsPage() {
        getDriver().get("https://www.usps.com");
    }

    @When("I go to Lookup ZIP page by address")
    public void iGoToLookupZIPPageByAddress() {
        getDriver().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        getDriver().findElement(By.xpath("//a[contains(@class,'nav-first-element')]")).click();
        getDriver().findElement(By.xpath("//img[contains(@alt,'Zip Code')]/..")).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'Find by Address')]")).click();

    }

    @And("I fill out {string} street, {string} city, {string} state")
    public void iFillOutStreetCityState(String street, String city, String state) throws InterruptedException {
        getDriver().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        getDriver().findElement(By.xpath("//input[@id='tAddress']")).sendKeys(street);
        getDriver().findElement(By.xpath("//input[@id='tCity']")).sendKeys(city);
        String stateOptionXpath = "//option[@value='" + state + "']";
        getDriver().findElement(By.xpath(stateOptionXpath)).click();
        getDriver().findElement(By.xpath("//a[@id='zip-by-address']")).click();
        Thread.sleep(5000);

    }

    @Then("I validate {string} zip code exists in the result")
    public void iValidateZipCodeExistsInTheResult(String zip) {
        assertThat(getDriver().findElement(By.xpath("//div[@id='zipByAddressDiv']")).getText()).contains(zip);
    }

    @And("I print all numbers from zerro up to {int}")
    public void iPrintAllNumbersFromZerroUpTo(int n) {
        int i = 0;
        while (i < n + 1) {
            System.out.println(i);
            i++;
        }
    }

    @And("I print all numbers from {int} up to {int}")
    public void iPrintAllNumbersFromUpTo(int i, int n) {
        while (i < n + 1) {
            System.out.println(i);
            i++;
        }
    }

    @And("I print integer array")
    public void iPrintIntegerArray() {
        int[] myArray = new int[]{1, 2, 3, 4, 5};
        for (int i : myArray) {
            System.out.print(i + " ");
        }
    }

    @And("I print all even numbers from integer array")
    public void iPrintAllEvenNumbersFromIntegerArray() {
        int[] myArray = new int[]{1, 2, 3, 4, 5};
        for (int i : myArray) {
            if (i % 2 == 0) {
                System.out.print(i + " ");
            }
        }
    }

    @And("I check if array is empty")
    public void iCheckIfArrayIsEmpty() {
        int[] myArray = new int[]{};
        if (myArray.length == 0) {
            System.out.println("Array is empty");
        } else {
            System.out.println("Array is not empty");
        }
        ;
    }

    @And("I check if array contains another element")
    public void iCheckIfArrayContainsAnotherElement() {
        int[] myArray = new int[]{1, 4, 3, 6, 7};
        int element = 5;
        String present = "Element is not present in array";
        for (int i : myArray) {
            if (i == element) {
                present = "element is present in array";
            }
        }
        System.out.println(present);

    }

    @And("I print all numbers up to {int} with conditions Fizz and Buzz")
    public void iPrintAllNumbersUpToWithConditionsFizzAndBuzz(int n) {
        int i = 1;
        while (i < n + 1) {
            if (i % 3 == 0 && i % 5 == 0) {
                System.out.print("FizzBuzz ");
            } else if (i % 3 == 0) {
                System.out.print("Fizz ");
            } else if (i % 5 == 0) {
                System.out.print("Buzz ");
            } else {
                System.out.print(i + " ");
            }
            i++;
        }
    }

    @When("I go to Calculate Price Page")
    public void iGoToCalculatePricePage() {
        WebElement mailAndShip = getDriver().findElement(By.xpath("//a[@id='mail-ship-width']"));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(mailAndShip).perform();
        getDriver().findElement(By.xpath("//li[@class='tool-calc']/a[contains(@href,'postcalc')]")).click();

    }

    @And("I select {string} with {string} shape")
    public void iSelectWithShape(String Country, String type) {
        getDriver().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        String CountryId = "000000";
        if (Country.equals("Canada")) {
            CountryId = "10054";
        }
        if (Country.equals("El Salvador")) {
            CountryId = "10095";
        }
        if (Country.equals("Estonia")) {
            CountryId = "10104";
        }
        if (Country.equals("Fiji")) {
            CountryId = "10109";
        }

        Select stateSelect = new Select(getDriver().findElement(By.xpath("//select[@name='CountryID']")));
        stateSelect.selectByValue(CountryId);
        if (type.equals("Postcard")) {
            getDriver().findElement(By.xpath("//input[@id='option_1']")).click();
        } else {
            System.out.println("error");
        }
    }

    @And("I define {string} quantity")
    public void iDefineQuantity(String n) {
        getDriver().findElement(By.xpath("//input[@placeholder='Quantity']")).sendKeys(n);
    }

    @Then("I calculate the price and validate cost is {string}")
    public void iCalculateThePriceAndValidateCostIs(String cost) {
        getDriver().findElement(By.xpath("//input[@value='Calculate']")).click();
        if (getDriver().findElement(By.xpath("//div[@id='total']")).getText().equals(cost)) {
            System.out.println("OK");
            System.out.println(getDriver().findElement(By.xpath("//div[@id='total']")).getText());
        } else {
            System.out.println("NOT OK");
            System.out.println(getDriver().findElement(By.xpath("//div[@id='total']")).getText());
        }
    }

    @When("I go to {string} tab")
    public void iGoToTab(String text) {
        String menuXpath = "//a[@class='menuitem'][contains(text(),'" + text + "')]";
        getDriver().findElement(By.xpath(menuXpath)).click();

    }

    @And("I perform {string} help search")
    public void iPerformHelpSearch(String searchText) {
        getDriver().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        getDriver().findElement(By.xpath("//span[@class='search-field-group']//input")).sendKeys(searchText);
        getDriver().findElement(By.xpath("//button[@title='Search'][1]")).click();
    }

    @Then("I verify that no results of {string} available in help search")
    public void iVerifyThatNoResultsOfAvailableInHelpSearch(String searchText) {
        getDriver().findElement(By.xpath("//span[@class='search-field-group']//input")).clear();

        assertThat(getDriver().findElement(By.xpath("//body")).getText()).doesNotContain(searchText);


    }

    @When("I navigate to Find a Location page")
    public void iNavigateToFindALocationPage() {
        getDriver().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        getDriver().findElement(By.xpath("//a[contains(@class,'nav-first-element')]")).click();
        getDriver().findElement(By.xpath("//p[contains(text(),'Find USPS Locations')]/..")).click();
    }

    @And("I filter by {string} location types, {string} services, {string} available services")
    public void iFilterByLocationTypesServicesAvailableServices(String LocType, String Serv, String AvServ) {
        getDriver().manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
        if (LocType.equals("Post Offices")) {
            getDriver().findElement(By.xpath("//button[@id='post-offices-select']")).click();
            getDriver().findElement(By.xpath("//div[@class='col-md-4 col-sm-5 col-xs-12 location-type-container']//li[2]//a[1]")).click();

        }
        if (Serv.equals("Pickup Services")) {
            getDriver().findElement(By.xpath("//button[@id='service-type-select']")).click();
            getDriver().findElement(By.xpath("//li[@id='pickupPo']//a[contains(text(),'Pickup Services')]")).click();
        }
        if (AvServ.equals("Accountable Mail")) {
            getDriver().findElement(By.xpath("//button[@id='available-service-select']")).click();
            getDriver().findElement(By.xpath("//a[contains(text(),'Accountable Mail')]")).click();
        }
    }

    @And("I provide data as {string} street, {string} city, {string} state")
    public void iProvideDataAsStreetCityState(String street, String city, String state) {
        getDriver().findElement(By.xpath("//input[@id='search-input']")).click();
        getDriver().findElement(By.xpath("//input[@id='addressLineOne']")).sendKeys(street);
        getDriver().findElement(By.xpath("//input[@id='cityOrZipCode']")).sendKeys(city);
        String stateXpath = "//option[@value='" + state + "']";
        getDriver().findElement(By.xpath(stateXpath)).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'Go to Results')]")).click();
    }

    @Then("I verify phone number is {string}")
    public void iVerifyPhoneNumberIs(String phone) {
        getDriver().findElement(By.xpath("//div[@id='resultBox']/div[1]")).click();
        assertThat(getDriver().findElement(By.xpath("//p[@class='ask-usps']")).getText()).contains(phone);

    }

    @Given("I go to converter page")
    public void iGoToConverterPage() {
        getDriver().get("https://www.unitconverters.net/");
    }

    @And("I click on {string}")
    public void iClickOn(String page) {
        if (page.equalsIgnoreCase("temperature")) {
            getDriver().findElement(By.xpath("//div[@id='menu']//a[contains(text(),'Temperature')]")).click();
        } else if (page.equalsIgnoreCase("length")) {
            System.out.println("");
        } else if (page.equalsIgnoreCase("area")) {
            getDriver().findElement(By.xpath("//div[@id='menu']//a[contains(text(),'Area')]")).click();
        } else if (page.equalsIgnoreCase("volume")) {
            getDriver().findElement(By.xpath("//div[@id='menu']//a[contains(text(),'Volume')]")).click();
        } else if (page.equalsIgnoreCase("weigth")) {
            getDriver().findElement(By.xpath("//div[@id='menu']//a[contains(text(),'Weight')]")).click();
        } else if (page.equalsIgnoreCase("time")) {
            getDriver().findElement(By.xpath("//div[@id='menu']//a[contains(text(),'Time')]")).click();
        } else {
            System.out.println("input error");
        }
    }

    @And("I select from {string} and to {string}")
    public void iSelectFromAndTo(String from, String to) {

        Select fromSelect = new Select(getDriver().findElement(By.xpath("//select[@name='calFrom']")));
        fromSelect.selectByVisibleText(from);
        Select toSelect = new Select(getDriver().findElement(By.xpath("//select[@name='calTo']")));
        toSelect.selectByVisibleText(to);
    }

    @And("I set from {string} value and verify result is {string}")
    public void iSetFromValueAndVerifyResultIs(String from, String expResult) {
        getDriver().findElement(By.xpath("//input[@name='fromVal']")).sendKeys(from);
        String actResult = getDriver().findElement(By.xpath("//input[@name='toVal']")).getAttribute("value");
        float intActResul = Float.parseFloat(actResult);
        float intExpResul = Float.parseFloat(expResult);
        double accuracy = 1.015;
        if (intActResul < intExpResul * accuracy && intActResul > intExpResul / accuracy) {
            System.out.println("ok");
        } else {
            System.out.println("not ok");
        }
    }


    @When("I go to Every Door Direct Mail under Business")
    public void iGoToEveryDoorDirectMailUnderBusiness() {
        WebElement everyDoorDirectMail = getDriver().findElement(By.xpath("//a[@id='navbusiness']/.."));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(everyDoorDirectMail).perform();
        getDriver().findElement(By.xpath("//a[contains(text(),'Every Door Direct Mail')]")).click();
    }

    @And("I search for {string}")
    public void iSearchFor(String address) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        WebElement input = getDriver().findElement(By.xpath("//input[@id='address']"));
        wait.until(ExpectedConditions.elementToBeClickable(input));
        getDriver().findElement(By.xpath("//input[@id='address']")).sendKeys(address);
        getDriver().findElement(By.xpath("//span[contains(text(),'Search')]/../..//button[@type='submit']")).click();
    }


    @And("I click Show Table on the map")
    public void iClickShowTableOnTheMap() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        WebElement element = getDriver().findElement(By.xpath("//a[@class='route-table-toggle']"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor ex = (JavascriptExecutor) getDriver();
        ex.executeScript("arguments[0].click()", element);


    }

    @When("I click Select All on the table")
    public void iClickSelectAllOnTheTable() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);


        WebElement element = getDriver().findElement(By.xpath("//a[@class='totalsArea']"));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        JavascriptExecutor ex = (JavascriptExecutor) getDriver();
        ex.executeScript("arguments[0].click()", element);
    }

    @And("I close modal window")
    public void iCloseModalWindow() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        WebElement close = getDriver().findElement(By.xpath("//div[@id='modal-box-closeModal']"));
        wait.until(ExpectedConditions.elementToBeClickable(close)).click();
    }

    @Then("I verify that summary of all rows of Cost column is equal Approximate Cost in Order Summary")
    public void iVerifyThatSummaryOfAllRowsOfCostColumnIsEqualApproximateCostInOrderSummary() throws InterruptedException {
        WebElement problematicElement = getDriver().findElement(By.xpath("//div[@class='dojoxGridScrollbox']"));
        JavascriptExecutor ex = (JavascriptExecutor) getDriver();
        ex.executeScript("arguments[0].scrollBy(0,1000)", problematicElement);
        Thread.sleep(1000);


        List<WebElement> all = getDriver().findElements(By.xpath("//td[contains(text(),'$')][not(contains(text(),'k'))]"));
        List<Double> listValue;
        listValue = new ArrayList<>();

        for (WebElement i : all) {
            listValue.add(Double.parseDouble(i.getText().substring(1)));
        }

        double sum = 0.00;
        for (double i : listValue) {
            sum += i;
        }
        double approxCostDouble = Double.parseDouble(getDriver().findElement(By.xpath("//span[@class='approx-cost']")).getText());
        int intSum = (int) sum;
        int intApproxCost = (int) approxCostDouble;
        if (intApproxCost == intSum) {
            System.out.println("OK");
        } else {
            System.out.println("NOT OK");
        }
        System.out.println(intApproxCost);
        System.out.println(intSum);


    }

    @Given("I go to calculator page")
    public void iGoToCalculatorPage() {
        getDriver().get("https://www.calculator.net/");
    }

    @When("I navigate to {string}")
    public void iNavigateTo(String arg0) {
        getDriver().findElement(By.xpath("//a[contains(text(),'Auto Loan Calculator')]")).click();
    }

    @And("I clear all calculator fields")
    public void iClearAllCalculatorFields() {
        getDriver().findElement(By.xpath("//input[@id='cloanamount']")).clear();
        getDriver().findElement(By.xpath("//input[@id='cloanterm']")).clear();
        getDriver().findElement(By.xpath("//input[@id='cinterestrate']")).clear();
        getDriver().findElement(By.xpath("//input[@id='cdownpayment']")).clear();
        getDriver().findElement(By.xpath("//input[@id='csaletax']")).clear();
        getDriver().findElement(By.xpath("//input[@id='ctitlereg']")).clear();
    }

    @And("I calculate")
    public void iCalculate() {
        getDriver().findElement(By.xpath("//input[@value='Calculate']")).click();
    }

    @Then("I verify {string} calculator error")
    public void iVerifyCalculatorError(String arg0) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        WebElement error = getDriver().findElement(By.xpath("//font[contains(text(),'Please provide a positive auto price.')]"));
        wait.until(ExpectedConditions.elementToBeClickable(error));

    }

    @And("I enter {string} price, {string} months, {string} interest, {string} downpayment, {string} trade-in, {string} state, {string} percent tax, {string} fees")
    public void iEnterPriceMonthsInterestDownpaymentTradeInStatePercentTaxFees(String price, String month, String interest, String downpayment, String tradeIn, String state, String tax, String fees) {
        getDriver().findElement(By.xpath("//input[@id='cloanamount']")).sendKeys(price);
        getDriver().findElement(By.xpath("//input[@id='cloanterm']")).sendKeys(month);
        getDriver().findElement(By.xpath("//input[@id='cinterestrate']")).sendKeys(interest);
        getDriver().findElement(By.xpath("//input[@id='cdownpayment']")).sendKeys(downpayment);
        getDriver().findElement(By.xpath("//input[@id='ctradeinvalue']")).sendKeys(tradeIn);
        Select stateSelect = new Select(getDriver().findElement(By.xpath("//select[@name='cstate']")));
        stateSelect.selectByVisibleText(state);
        getDriver().findElement(By.xpath("//input[@id='csaletax']")).sendKeys(tax);
        getDriver().findElement(By.xpath("//input[@id='ctitlereg']")).sendKeys(fees);

    }

    @Then("I verify monthly pay is {string}")
    public void iVerifyMonthlyPayIs(String pay) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        WebElement payAm = getDriver().findElement(By.xpath("//h2[@class='h2result']"));
        wait.until(ExpectedConditions.textToBePresentInElement(payAm, pay));
    }

    @And("user input {int} and {int} which are divided by {int}")
    public void userInputAnd(int first, int second, int dvsr) {
        int div1 = first / dvsr;
        int div2 = second / dvsr;
        System.out.println(first + "/" + dvsr + "=" + div1);
        if (div1 >= 1 && div1 <= 10) {
            System.out.println("First result in range of 1..10");
        } else if (div1 >= 10 && div1 <= 20) {
            System.out.println("First result in range of 10..20");
        }
        System.out.println(second + "/" + dvsr + "=" + div2);
        if (div2 >= 1 && div2 <= 10) {
            System.out.println("Second result in range of 1..10");
        } else if (div2 >= 10 && div2 <= 20) {
            System.out.println("Second result in range of 10..20");
        }


    }

    @And("I add {int} to {int} , print result and verify it")
    public void iAddToPrintResultAndVerifyIt(int first, int second) throws InterruptedException {
        int add = first + second;
        System.out.println("Addition is " + add);

        System.out.println("TESTING...");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(".");
        TimeUnit.SECONDS.sleep(1);
        ;
        System.out.println(".");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(".");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("Please wait we doing some extremely complicated calculations");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(".");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(".");
        TimeUnit.SECONDS.sleep(1);
        System.out.println(".");
        TimeUnit.SECONDS.sleep(1);

        if (add - first != second || add - second != first) {
            System.out.println("TEST FAILED");
        } else {
            System.out.println("TEST PASSED");
        }
        TimeUnit.SECONDS.sleep(5);
    }

    @And("I reverse every third character of a string {string}")
    public void iReverseEveryThirdCharacterOfAString(String str) {
        StringBuilder newString = new StringBuilder();
        for (int i = str.length() - 3; i >= 0; i -= 3) {
            newString.append(str.charAt(i));
        }
        System.out.println(newString);

    }

    @And("I translate {string} to YODA language")
    public void iTranslateToYODALanguage(String humanText) {
        String[] humanTextArray = humanText.split(" ");
        System.out.println("Human text: " + humanText);
        StringBuilder yodaText = new StringBuilder();
        for (int i = humanTextArray.length - 1; i >= 0; i -= 1) {
            yodaText.append(humanTextArray[i] + " ");
        }
        yodaText.setLength(yodaText.length() - 1);
        System.out.println("Yoda text: " + yodaText);

    }

    @Given("I go to ups page")
    public void iGoToUpsPage() {
        getDriver().get("https://www.ups.com/us/en/Home.page?");
    }

    @And("I open Shipping menu")
    public void iOpenShippingMenu() {
        getDriver().findElement(By.xpath("//a[@id='ups-menuLinks2']")).click();
    }

    @And("I go to Create a Shipment")
    public void iGoToCreateAShipment() {
        getDriver().findElement(By.xpath("//a[contains(text(),'Create a Shipment:')]")).click();
    }

    @When("I fill out origin shipment fields")
    public void iFillOutOriginShipmentFields() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        WebElement check = getDriver().findElement(By.xpath("//select[@id='origincountry']"));
        wait.until(ExpectedConditions.elementToBeClickable(check));
        getDriver().findElement(By.xpath("//option[contains(text(),'United States')]")).click();
        getDriver().findElement(By.xpath("//input[@id='originname']")).sendKeys("Vitalii");
        getDriver().findElement(By.xpath("//input[@id='originaddress1']")).sendKeys("111 Ruan Court");
        getDriver().findElement(By.xpath("//input[@id='originpostal']")).sendKeys("95630");
        getDriver().findElement(By.xpath("//input[@id='origincity']")).sendKeys("Folsom");
        getDriver().findElement(By.xpath("//option[contains(text(),'California')]")).click();
        getDriver().findElement(By.xpath("//input[@id='originemail']")).sendKeys("123@example.com");
        getDriver().findElement(By.xpath("//input[@id='originphone']")).sendKeys("1591595151");


    }

    @And("I submit the shipment form")
    public void iSubmitTheShipmentForm() {
        WebElement submit = getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationContinueButton']"));
        JavascriptExecutor ex = (JavascriptExecutor) getDriver();
        ex.executeScript("arguments[0].click()", submit);
    }

    @Then("I verify origin shipment fields submitted")
    public void iVerifyOriginShipmentFieldsSubmitted() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        WebElement ship = getDriver().findElement(By.xpath("//div[@class='ups-section']"));
        wait.until(ExpectedConditions.textToBePresentInElement(ship, "Vitalii"));
    }

    @And("I cancel the shipment form")
    public void iCancelTheShipmentForm() {
        WebElement cancel = getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationCancelShipmentButton']"));
        JavascriptExecutor ex = (JavascriptExecutor) getDriver();
        ex.executeScript("arguments[0].click()", cancel);
        WebElement yes = getDriver().findElement(By.xpath("//button[@id='nbsCancelShipmentWarningYes']"));

        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.elementToBeClickable(yes));

        ex.executeScript("arguments[0].click()", yes);

    }

    @Then("I verify shipment form is reset")
    public void iVerifyShipmentFormIsReset() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        WebElement wrap = getDriver().findElement(By.xpath("//div[@class='ups-wrap_inner']//div[@class='ups-wrap']"));
        wait.until(ExpectedConditions.textToBePresentInElement(wrap, "Hello. Where are you shipping from?"));
    }

    @When("I fill out destination shipment fields")
    public void iFillOutDestinationShipmentFields() {
        getDriver().findElement(By.xpath("//option[contains(text(),'United States')]")).click();
        getDriver().findElement(By.xpath("//input[@id='destinationname']")).sendKeys("Ivan");
        getDriver().findElement(By.xpath("//input[@id='destinationaddress1']")).sendKeys("155 TTT");
        getDriver().findElement(By.xpath("//input[@id='destinationpostal']")).sendKeys("95030");
        getDriver().findElement(By.xpath("//input[@id='destinationcity']")).sendKeys("Los Gatos");
        getDriver().findElement(By.xpath("//option[contains(text(),'California')]")).click();
    }

    @And("I set packaging type and weight")
    public void iSetPackagingTypeAndWeight() {
        getDriver().findElement(By.xpath("//option[contains(text(),'My Packaging')]")).click();
        getDriver().findElement(By.xpath("//input[@id='nbsPackagePackageWeightField0']")).sendKeys("5");
    }

    @Then("I verify total charges appear")
    public void iVerifyTotalChargesAppear() {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        WebElement body = getDriver().findElement(By.xpath("//body"));
        wait.until(ExpectedConditions.textToBePresentInElement(body, "$"));

    }

    @And("I select cheapest delivery option")
    public void iSelectCheapestDeliveryOption() {
        List<WebElement> myList = getDriver().findElements(By.xpath("//strong[contains(text(),'$')]"));
        int i = myList.toArray().length - 1;
        JavascriptExecutor ex = (JavascriptExecutor) getDriver();
        ex.executeScript("arguments[0].click()", myList.get(i));

    }

    @And("I set description and check Saturday Delivery type")
    public void iSetDescriptionAndCheckSaturdayDeliveryType() {
        getDriver().findElement(By.xpath("//input[@id='nbsShipmentDescription']")).sendKeys("hello");
        getDriver().findElement(By.xpath("//saturday-delivery-option[@class='ng-star-inserted']//label[@class='ups-lever ups-checkbox-custom-label section-checkbox-label']")).click();


    }

    @Then("I verify total charges changed")
    public void iVerifyTotalChargesChanged() throws InterruptedException {
        getDriver().findElement(By.xpath("//saturday-delivery-option[@class='ng-star-inserted']//label[@class='ups-lever ups-checkbox-custom-label section-checkbox-label']")).click();
        Thread.sleep(500);
        String anyDay = getDriver().findElement(By.xpath("//span[@id='total-charges-spinner']")).getText();
        getDriver().findElement(By.xpath("//saturday-delivery-option[@class='ng-star-inserted']//label[@class='ups-lever ups-checkbox-custom-label section-checkbox-label']")).click();
        Thread.sleep(500);
        String saturday = getDriver().findElement(By.xpath("//span[@id='total-charges-spinner']")).getText();
        if (anyDay.equals(saturday)) {
            System.out.println("not ok");
        } else {
            System.out.println("ok");
        }
        System.out.println(anyDay);
        System.out.println(saturday);

    }

    @And("I select Paypal payment type")
    public void iSelectPaypalPaymentType() {
        getDriver().findElement(By.xpath("//label[@for='other-ways-to-pay-tile'][@class='ups-tile_button_content']")).click();
    }

    @Then("I review all recorded details on the review page")
    public void iReviewAllRecordedDetailsOnTheReviewPage() throws InterruptedException {

        getDriver().findElement(By.xpath("//button[@id='nbsBackForwardNavigationReviewPrimaryButton']")).click();
        Thread.sleep(1000);

    }


    @And("I Write a function that find two max numbers in an array")
    public void iWriteAFunctionThatFindMaxNumbersInAnArray() {
        int[] intArray = {1, 2, 31, 4, 31, 8, 10, 4};
        int firstOfTwoMax = intArray[0] - intArray[1];
        int secondOfTwoMax = intArray[0] - intArray[1];
        for (int i : intArray) {
            if (i > firstOfTwoMax) {
                firstOfTwoMax = i;
            }
            int countMax = 0;
            for (int j : intArray) {
                if (j == firstOfTwoMax) {
                    countMax++;
                }
            }
            if (countMax > 1) {
                secondOfTwoMax = firstOfTwoMax;
            } else {
                for (int y : intArray) {
                    if (y > secondOfTwoMax && y < firstOfTwoMax) {
                        secondOfTwoMax = y;
                    }
                }
            }

        }
        System.out.println("First max: " + firstOfTwoMax);
        System.out.println("Second max: " + secondOfTwoMax);
    }

    @And("I Write a function that finds if array contains duplicates")
    public void iWriteAFunctionThatFindsIfArrayContainsDuplicates() {
        Object[] myArray = {0.56, 4, "fdfd", 5 ,"dsds"};
        int length = myArray.length;
        int duplicates = 0;
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                if(myArray[i].equals(myArray[j]) && i != j){
                    duplicates++;
                }
            }

        }
        if(duplicates>1) {
            System.out.println("Array contains duplicates");
        }
        else {
            System.out.println("All elements are unique");
        }
    }

    @And("I Write a function that finds if {string} is palindrome")
    public void iWriteAFunctionThatFindsIfIsPalindrome(String given) {
        StringBuilder rev = new StringBuilder();
        int length = given.length();
        for (int i = length-1; i >= 0; i--) {
            rev.append(given.charAt(i));
        }

        if(given.equals(rev.toString())){
            System.out.println("Palindrome");
        }
        else {
            System.out.println("not a palindrome");
        }
    }

    @And("I Write a function that counts number of each character in a {string}")
    public void iWriteAFunctionThatCountsNumberOfEachCharacterInA(String str) {
        for(int i = 0; i<str.length();i++){
            int count = 0;
            char charr = str.charAt(i);
            for (int j = 0; j <str.length();j++){
                if(str.charAt(j)==str.charAt(i)){
                    count++;
                }
            }
            System.out.println(charr + ": " + count);
        }
    }

    @When("I go to Postal Store tab")
    public void iGoToPostalStoreTab() {
        getDriver().findElement(By.xpath("//a[@name='navpostalstore']/..//a[@aria-expanded='false']")).click();
    }

    @And("I enter {string} into store search")
    public void iEnterIntoStoreSearch(String search) {
        getDriver().findElement(By.xpath("//input[@name='Ntt']")).sendKeys(search);
    }

    @Then("I search and validate no products found")
    public void iSearchAndValidateNoProductsFound() {
        getDriver().findElement(By.xpath("//input[@name='search']")).click();
        WebElement errorP = getDriver().findElement(By.xpath("//p[contains(text(),'Your search did not match any products.')]"));

        assertThat(errorP.isDisplayed());
    }

    @When("I go to Stamps under Postal Store")
    public void iGoToStampsUnderPostalStore() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getDriver().findElement(By.xpath("//a[@name='navpostalstore']/..//a[@aria-expanded='false']"))).perform();
        getDriver().findElement(By.xpath("//a[text()='Stamps']")).click();
    }

    @And("choose mail service Priority Mail")
    public void chooseMailServicePriorityMail() {
        JavascriptExecutor js = ((JavascriptExecutor) getDriver());
        WebElement label = getDriver().findElement(By.xpath("//label[@for='checkbox-type-Mail Service-Priority Mail-1']"));
        js.executeScript("arguments[0].click();", label);
    }


    @Then("I Verify {int} items found")
    public void iVerifyItemsFound(int count) {
        List<WebElement>  results = getDriver().findElements(By.xpath("//div[@class='result-product-img']"));
        if (results.size() == count){
            System.out.println("OK");
        }
        else {
            System.out.println("not OK");
        }
    }

    @When("I unselect Stamps checkbox")
    public void iUnselectStampsCheckbox() {
        JavascriptExecutor js = ((JavascriptExecutor) getDriver());
        WebElement label = getDriver().findElement(By.xpath("//label[@for='checkbox-type-Category-Stamps']"));
        js.executeScript("arguments[0].click();", label);
    }

    @And("select Vertical stamp Shape")
    public void selectVerticalStampShape() {
        JavascriptExecutor js = ((JavascriptExecutor) getDriver());
        WebElement label = getDriver().findElement(By.xpath("//label[contains(@for,'checkbox-type-Stamp Shape-Vertical')]"));
        js.executeScript("arguments[0].click();", label);
    }

    @And("I click Blue color")
    public void iClickBlueColor() {
        JavascriptExecutor js = ((JavascriptExecutor) getDriver());
        WebElement label = getDriver().findElement(By.xpath("//div[@style='background-color:#033366;']"));
        js.executeScript("arguments[0].click();", label);
    }

    @Then("I verify {string} and {string} filters")
    public void iVerifyAndFilters(String filter1, String filter2) {
        WebElement  filtersSpan1 = getDriver().findElement(By.xpath("//div[@class='container-fluid']//div[@class='cartridge-viewport']//span[1]"));
        WebElement  filtersSpan2 = getDriver().findElement(By.xpath("//div[@class='container-fluid']//div[@class='cartridge-viewport']//span[2]"));
        assertThat(filtersSpan1.getText().equals(filter1+" "));
        assertThat(filtersSpan2.getText().equals(filter2+" "));
    }


    @And("I verify that items below {int} dollars exists")
    public void iVerifyThatItemsBelowDollarsExists(int minPrice) {
        List<WebElement>  results = getDriver().findElements(By.xpath("//p[contains(text(),'$')]"));
        ArrayList<String> ar = new ArrayList<>();
        for(WebElement i:results){

            ar.add(i.getText());
        }
        ArrayList<String> lower = new ArrayList<>();
        int i = 0;
        while(i<minPrice)
            {lower.add(Integer.toString(i) +".");
            i++;
        }
        String result = "no results less then "+ Integer.toString(minPrice);
        for(String z: ar){
            for(String y: lower){
                if(z.contains(y)){
                    result = "Got result under " + Integer.toString(minPrice);
                }
            }
        }
        System.out.println(result);



    }

    @When("I perform Passports search")
    public void iPerformPassportsSearch() {
        WebElement search = getDriver().findElement(By.xpath("//a[contains(text(),'Search USPS.com')]"));
        Actions actions = new Actions(getDriver());
        actions.moveToElement(search).perform();
        getDriver().findElement(By.xpath("//div[@class='repos']//a[contains(text(),'PASSPORTS')]")).click();
    }

    @And("I select {string} in results")
    public void iSelectInResults(String arg0) {
        JavascriptExecutor js = ((JavascriptExecutor) getDriver());

        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        WebElement results = getDriver().findElement(By.xpath("//div[@id='main_res']"));
        wait.until(ExpectedConditions.textToBePresentInElement(results, "results found for 'Passports'"));
        String searchArg = "//span[contains(text(),'"+arg0+"')]";
        WebElement ourLink = getDriver().findElement(By.xpath(searchArg));
        js.executeScript("arguments[0].click();", ourLink);

    }

    @And("I click {string} button")
    public void iClickButton(String arg0) {
        String button = "//a[@class='button--primary'][contains(text(),'"+arg0+"')]";
        getDriver().findElement(By.xpath(button)).click();
    }

    @And("verify {string} service exists")
    public void verifyServiceExists(String arg0) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        String option = "//option[contains(text(),'"+arg0+"')]";
        WebElement optionEl = getDriver().findElement(By.xpath(option));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//select[@id='passportappointmentType']")));
        wait.until(ExpectedConditions.elementToBeClickable(optionEl));
    }

    @When("I go to PO Boxes under Track & Manage")
    public void iGoToPOBoxesUnderTrackManage() {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(getDriver().findElement(By.xpath("//a[@id='navtrackmanage']/..//a[@aria-expanded='false']"))).perform();
        getDriver().findElement(By.xpath("//a[text()='PO Boxes'][@role='menuitem']")).click();
    }

    @And("I reserve new PO box for {string}")
    public void iReserveNewPOBoxFor(String zip) {
        getDriver().findElement(By.xpath("//input[@id='searchInput']")).sendKeys(zip);
        getDriver().findElement(By.xpath("//span[@class='icon-search']/..")).click();
    }

    @Then("I verify that {string} present")
    public void iVerifyThatPresent(String location) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        WebElement locationResults = getDriver().findElement(By.xpath("//div[@class='row poLocationResults']"));
        wait.until(ExpectedConditions.textToBePresentInElement(locationResults, location));
    }


    @And("I verify that {string} PO Box is available in Los Altos — Post Office™")
    public void iVerifyThatPOBoxIsAvailableInLosAltosPostOffice(String size){
        JavascriptExecutor js = ((JavascriptExecutor) getDriver());


        js.executeScript("arguments[0].click();", getDriver().findElement(By.xpath("//span[contains(text(),'Los Altos')]")));
        String sizeXpath = "";
        if(size.equals("Size 5-XL")){
            sizeXpath = "//img[@src='images/box_size_xlarge.png']";
        }
        else if(size.equals("Size 4-L")){
            sizeXpath = "//img[@src='images/box_size_large.png']";
        }
        else if(size.equals("Size 3-M")){
            sizeXpath = "//img[@src='images/box_size_medium.png']";
        }
        else if(size.equals("Size 2-S")){
            sizeXpath = "//img[@src='images/box_size_small.png']";
        }
        else if(size.equals("Size 1-XS")){
            sizeXpath = "//img[@src='images/box_size_xsmall.png']";
        }
        else {
            System.out.println("input error");
        }


        js.executeScript("arguments[0].click();", getDriver().findElement(By.xpath(sizeXpath)));

        if(getDriver().findElement(By.xpath("//span[@class='bold unavailable']")).isDisplayed()){
            System.out.println("Unavailable");
        }
        else {
            System.out.println("Available");
        }




    }
}