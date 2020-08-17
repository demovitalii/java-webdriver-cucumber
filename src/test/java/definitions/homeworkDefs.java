package definitions;

import cucumber.api.java.en.And;

import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;

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
}
