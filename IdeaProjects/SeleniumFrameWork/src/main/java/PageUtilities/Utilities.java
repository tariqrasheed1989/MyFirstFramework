package PageUtilities;

import Pages.CartPage;
import Pages.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.xml.xpath.XPath;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

public class Utilities
{
    WebDriver driver;
    public Utilities(WebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
    WebElement CartHeader;

    @FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
    WebElement OrderHeader;
    public void waitForElementToAppear(By findBy)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitForElementToAppear(WebElement element)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public void waitForElementToDisAppear(WebElement webElement)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOf(webElement));
    }
    public void waitForElementToBeClickable(WebElement webElement)
    {
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public CartPage goToCartPage()
    {
        CartHeader.click();
        CartPage cartPage=new CartPage(driver);
        return cartPage;
    }

    public OrderPage goToOrdersPage()
    {
        OrderHeader.click();
        OrderPage orderPage=new OrderPage(driver);
        return orderPage;
    }

    public void JSClick(WebElement webElement)
    {
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();",webElement);
    }
}
