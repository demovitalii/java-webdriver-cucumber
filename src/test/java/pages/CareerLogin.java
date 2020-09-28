package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CareerLogin extends Page {

    public CareerLogin() {
        url = "https://skryabin-careers.herokuapp.com/";
    }

    @FindBy(xpath = "//input[@type='text']")
    private WebElement loginInput;
    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;
    @FindBy(xpath = "//button[@id='loginButton']")
    private WebElement loginButton;

    public void enterLogin(String login) {
        loginInput.sendKeys(login);
    }

    public void enterPassword(String password){
        passwordInput.sendKeys(password);
    }

    public void submit(){
        loginButton.click();
    }

}