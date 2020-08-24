package definitions;

import cucumber.api.java.en.And;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


import static org.assertj.core.api.Assertions.assertThat;



import static support.TestContext.getDriver;

public class homeworkDefs {
    @And("I print if {string} is positive")
    public void iPrintIfIsPositive(String arg0) {
        int i=Integer.parseInt(arg0);

        if (i<0){
            System.out.println("negative");
        }
        else if(i>0){
            System.out.println("positive");
        }
        else {
            System.out.println("zerro");
        }

    }

    @And("I print {string} th day of week")
    public void iPrintThDayOfWeek(String arg0) {
        int i=Integer.parseInt(arg0)-1;
        String[]days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        System.out.println(days[i]);
    }





    @And("I create map with keys {string} , {string} and values {string}, {string} and swap values")
    public void iCreateMapWithKeysAndValuesAndSwagValues(String k1, String k2, String v1, String v2) {
        Map<String, String> info = new LinkedHashMap<>();
        info.put(k1, v1);
        info.put(k2, v2);
        System.out.println("Original Map:");
        for(String key : info.keySet()) {
            System.out.println(key + ": " + info.get(key));
        }
        String valueOriginal1 = info.get(k1);
        info.put(k1,info.get(k2));
        info.put(k2,valueOriginal1);
        System.out.println("Swapped Map:");
        for(String key : info.keySet()) {
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
        Dimension dPhone = new Dimension(400,768);
        Dimension dTablet = new Dimension(800,768);
        Dimension dDesktop = new Dimension(1024,768);

        if (device.equalsIgnoreCase("phone")) {
            getDriver().manage().window().setSize(dPhone);
        } else if (device.equalsIgnoreCase("desktop")) {
            getDriver().manage().window().setSize(dDesktop);
        } else if (device.equalsIgnoreCase("tablet")) {
            getDriver().manage().window().setSize(dTablet);
        } else {
            System.out.println("unsupported device: "+ device);
        }
        Thread.sleep(5000);

    }

    @And("I fill out all required fields")
    public void iFillOutAllRequiredFields(){
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
        for (int i = 0; i < 4; i++)
        {
            System.out.print(myArray[i]+", ");
        }
        System.out.println(myArray[4]+"]");

        int firstIndex = arg0 - 1;
        int secondIndex = arg1 - 1;

        int temp = myArray[secondIndex];
        myArray[secondIndex] = myArray[firstIndex];
        myArray[firstIndex] = temp;

        System.out.print("Swapped array: [");
        for (int i = 0; i < 4; i++)
        {
            System.out.print(myArray[i]+", ");
        }
        System.out.println(myArray[4]+"]");
    }

    @And("I check divisibility of {int} by {int} , {int} and both")
    public void iCheckDivisibilityOfByAndBoth(int n, int d1, int d2) {
        if(d1 == 0 || d2 == 0){
            System.out.println(n +" division by zero error");
        }
        else if(d1 == d2){
            System.out.println("Dividers are equal!!!");
            System.out.println("But we check anyway:");
            if(n%d1==0){
                System.out.println(n +" divisible by " + d1);
            }

            else{
                System.out.println("Not Divisible");
            }
        }
        else {
            if(n%d1==0 && n%d2==0){
                System.out.println(n +" divisible by " + d1 +" and "+d2);
            }
            else if(n%d1==0){
                System.out.println(n +" divisible by " + d1);
            }
            else if(n%d2==0){
                System.out.println(n +" divisible by " + d2);
            }
            else{
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
        String stateOptionXpath = "//option[@value='"+state+"']";
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
        while( i < n+1){
            System.out.println(i);
            i++;
        }
    }

    @And("I print all numbers from {int} up to {int}")
    public void iPrintAllNumbersFromUpTo(int i, int n) {
        while( i < n+1){
            System.out.println(i);
            i++;
        }
    }

    @And("I print integer array")
    public void iPrintIntegerArray() {
        int[] myArray = new int[]{1,2,3,4,5};
        for(int i: myArray){
            System.out.print(i+" ");
        }
    }

    @And("I print all even numbers from integer array")
    public void iPrintAllEvenNumbersFromIntegerArray() {
        int[] myArray = new int[]{1,2,3,4,5};
        for(int i: myArray){
            if(i%2==0){
               System.out.print(i+" ");
            }
        }
    }

    @And("I check if array is empty")
    public void iCheckIfArrayIsEmpty() {
        int[] myArray = new int[]{};
            if(myArray.length==0){
                System.out.println("Array is empty");
            }
            else {
                System.out.println("Array is not empty");
            };
    }

    @And("I check if array contains another element")
    public void iCheckIfArrayContainsAnotherElement() {
        int[] myArray = new int[]{1, 4, 3, 6, 7};
        int element = 5;
        String present = "Element is not present in array";
        for(int i: myArray){
            if(i==element){
                present = "element is present in array";
            }
        }
        System.out.println(present);

    }

    @And("I print all numbers up to {int} with conditions Fizz and Buzz")
    public void iPrintAllNumbersUpToWithConditionsFizzAndBuzz(int n) {
        int i = 1;
        while (i < n+1){
            if(i%3==0 && i%5==0){
                System.out.print("FizzBuzz ");
            }
            else if(i%3==0){
                System.out.print("Fizz ");
            }
            else if(i%5==0){
                System.out.print("Buzz ");
            }
            else {
                System.out.print(i+" ");
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
        if(Country.equals("Canada")){
            CountryId="10054";
        }
        if(Country.equals("El Salvador")){
            CountryId="10095";
        }
        if(Country.equals("Estonia")){
            CountryId="10104";
        }
        if(Country.equals("Fiji")){
            CountryId="10109";
        }

        Select stateSelect = new Select(getDriver().findElement(By.xpath("//select[@name='CountryID']")));
        stateSelect.selectByValue(CountryId);
        if(type.equals("Postcard")){
            getDriver().findElement(By.xpath("//input[@id='option_1']")).click();
        }
        else{
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
        if(getDriver().findElement(By.xpath("//div[@id='total']")).getText().equals(cost)){
            System.out.println("OK");
            System.out.println(getDriver().findElement(By.xpath("//div[@id='total']")).getText());
        }
        else {
            System.out.println("NOT OK");
            System.out.println(getDriver().findElement(By.xpath("//div[@id='total']")).getText());
        }
    }

    @When("I go to {string} tab")
    public void iGoToTab(String text) {
        String menuXpath = "//a[@class='menuitem'][contains(text(),'"+text+"')]";
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
        if(LocType.equals("Post Offices")){
            getDriver().findElement(By.xpath("//button[@id='post-offices-select']")).click();
            getDriver().findElement(By.xpath("//div[@class='col-md-4 col-sm-5 col-xs-12 location-type-container']//li[2]//a[1]")).click();

        }
        if(Serv.equals("Pickup Services")){
            getDriver().findElement(By.xpath("//button[@id='service-type-select']")).click();
            getDriver().findElement(By.xpath("//li[@id='pickupPo']//a[contains(text(),'Pickup Services')]")).click();
        }
        if(AvServ.equals("Accountable Mail")){
            getDriver().findElement(By.xpath("//button[@id='available-service-select']")).click();
            getDriver().findElement(By.xpath("//a[contains(text(),'Accountable Mail')]")).click();
        }
    }

    @And("I provide data as {string} street, {string} city, {string} state")
    public void iProvideDataAsStreetCityState(String street, String city, String state) {
        getDriver().findElement(By.xpath("//input[@id='search-input']")).click();
        getDriver().findElement(By.xpath("//input[@id='addressLineOne']")).sendKeys(street);
        getDriver().findElement(By.xpath("//input[@id='cityOrZipCode']")).sendKeys(city);
        String stateXpath = "//option[@value='"+state+"']";
        getDriver().findElement(By.xpath(stateXpath)).click();
        getDriver().findElement(By.xpath("//a[contains(text(),'Go to Results')]")).click();
    }

    @Then("I verify phone number is {string}")
    public void iVerifyPhoneNumberIs(String phone) {
        getDriver().findElement(By.xpath("//div[@id='resultBox']/div[1]")).click();
        assertThat(getDriver().findElement(By.xpath("//p[@class='ask-usps']")).getText()).contains(phone);

    }
}
