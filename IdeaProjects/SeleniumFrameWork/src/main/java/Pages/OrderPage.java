package Pages;

import PageUtilities.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends Utilities
{
    WebDriver driver;
    public OrderPage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//tbody/tr/td[2]")
    List<WebElement>products;

    public List<WebElement> getHistoryProducts()
    {
        OrderPage orderPage=new OrderPage(driver);
             return products;
    }
}
