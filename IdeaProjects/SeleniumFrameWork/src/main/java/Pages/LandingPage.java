package Pages;

import PageUtilities.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage extends Utilities
{
    WebDriver driver;
    public LandingPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="userEmail")
    WebElement userEmail;

    @FindBy(css = "#userPassword")
    WebElement userPassword;

    @FindBy(css = "#login")
    WebElement loginButton;

    @FindBy(css = "div[class*='toast-message']")
    WebElement errorMsg;
    public ProductCataloguePage LoginApp(String username, String password)
    {
        userEmail.sendKeys(username);
        userPassword.sendKeys(password);
        loginButton.click();
        ProductCataloguePage productCataloguePage=new ProductCataloguePage(driver);
        return productCataloguePage;
    }
    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client/");
    }
    public String getErrorMsg()
    {
        waitForElementToAppear(errorMsg);
        return errorMsg.getText();
    }
//aman1512@gmail.com
    //Abcd@1234
}
