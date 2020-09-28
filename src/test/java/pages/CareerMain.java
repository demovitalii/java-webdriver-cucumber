package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CareerMain extends Page {

    @FindBy(xpath = "//button[contains(text(),'Login')]")
    private WebElement loginButton;

    @FindBy(xpath = "//button[contains(text(),'Recruit')]")
    private WebElement recruitButton;


    public void goToLogin() { loginButton.click(); }

    public void goToRecruiter(){
        recruitButton.click();
    }

    public WebElement returnRecruitButton(){
        return recruitButton;
    }

}