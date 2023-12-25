package Pages;

import PageUtilities.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductCataloguePage extends Utilities
{
    WebDriver driver;
    public ProductCataloguePage(WebDriver driver)
    {
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

//    List<WebElement> products = driver.findElements(By.xpath("//div[@class='card-body']/h5/b"));
    @FindBy(xpath="//div[@class='card-body']/h5/b")
    List<WebElement> products;
    //by the time we mention "List" @FindBy gets to know that it is "findElements"

    @FindBy(xpath = "//button[@class='btn w-10 rounded']")
    List<WebElement> AddToCart;

    @FindBy(xpath = "//div[@id='toast-container']")
    WebElement ToastMessage;

    By products1=By.xpath("//div[@class='card-body']/h5/b");
    By addTocart1=By.xpath("//button[@class='btn w-10 rounded']");
    By toastMessage= By.xpath("//div[@id='toast-container']");
    public List<WebElement> getProductList()
    {
        waitForElementToAppear(products1);
        return products;
    }
    public void AddToCart(List<String> buyProduct)
    {

        for (int i = 0; i < products.size(); i++) {
            String getName = products.get(i).getText();

            if (buyProduct.contains(getName))
            {
                AddToCart.get(i).click();
                waitForElementToAppear(toastMessage);
                waitForElementToDisAppear(ToastMessage);
            }


        }
    }

}
