package Pages;

import PageUtilities.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends Utilities
{
    WebDriver driver;
    public CartPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(xpath="//div[@class='cartSection']/h3")
    List<WebElement> cartItems;

    @FindBy(css = ".totalRow .btn.btn-primary")
    WebElement checkoutBtn;

    public List<WebElement> getCartItems()
    {
        return cartItems;
    }

    public CheckoutPage ClickOnCheckout()
    {
        waitForElementToBeClickable(checkoutBtn);
        JSClick(checkoutBtn);
        CheckoutPage checkoutPage=new CheckoutPage(driver);
        return checkoutPage;
    }




}
