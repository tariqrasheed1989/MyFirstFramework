package Pages;

import PageUtilities.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CheckoutPage extends Utilities
{
    WebDriver driver;
    public CheckoutPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath = "//input[@placeholder='Select Country']")
    WebElement selectCountry;

    @FindBy(xpath = "//span[@class='ng-star-inserted']")
    List<WebElement>CountrySuggestions;

    @FindBy(xpath = "//div[@class='actions']/a")
    WebElement PlaceOrderBtn;

    public void select_Country(String countryName)
    {
        selectCountry.sendKeys(countryName);
        for (WebElement dd:CountrySuggestions)
        {
            if (dd.getText().equals(countryName))
            {
                dd.click();
            }
        }
    }

    public ConfirmationPage SubmitOrder()
    {
        waitForElementToBeClickable(PlaceOrderBtn);
        JSClick(PlaceOrderBtn);
        ConfirmationPage confirmationPage=new ConfirmationPage(driver);
        return confirmationPage;
    }
}
