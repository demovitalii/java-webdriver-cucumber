package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;

public class UpsStartPage extends Page {

    // constructor
    public UpsStartPage() {
        url = "https://www.ups.com/us/en/Home.page?";
        title = "Global Shipping & Logistics Services | UPS - United States";
    }

    @FindBy(xpath = "//a[@id='ups-menuLinks2']")
    private WebElement shippingMenu;

    @FindBy(xpath = "//a[contains(text(),'Create a Shipment:')]")
    private WebElement createShipmentLink;


    //methods

    public void open() {
        getDriver().get(url);
    }


    public void shipmentMenuOpen() {
        shippingMenu.click();

    }

    public void createShipment() {
        createShipmentLink.click();

    }

}