import Pages.*;
import Tests.BaseTest;
import Tests.Retry;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class StandaloneTest extends BaseTest
{
    String[] BuyProduct = {"IPHONE 13 PRO", "ADIDAS ORIGINAL"};
    List<String> buyProduct = Arrays.asList(BuyProduct);
    @Test(dataProvider = "getData", groups = "Purchase")
    public void submitOrder(HashMap<String, String>input) throws IOException {

        ProductCataloguePage productCataloguePage=landingPage.LoginApp(input.get("username"), input.get("password"));
        productCataloguePage.AddToCart(buyProduct);
        CartPage cartPage=productCataloguePage.goToCartPage();
        for(WebElement dd:cartPage.getCartItems())
        {
            Assert.assertTrue(buyProduct.contains(dd.getText()));
        }
        CheckoutPage checkoutPage=cartPage.ClickOnCheckout();
        checkoutPage.select_Country("India");
        ConfirmationPage confirmationPage =checkoutPage.SubmitOrder();
        Assert.assertTrue(confirmationPage.VerifyConfirmationMsg().equals("THANKYOU FOR THE ORDER."));

    }
    @Test(enabled = true)
    public void LoginErrorValidation() throws IOException
    {
        landingPage.LoginApp("tariqrasheed1989@gmail.com", "India@2015____");
        //wrong password
        String ErrorMessage=landingPage.getErrorMsg();
        Assert.assertEquals(ErrorMessage,"Incorrect email or password.");
    }
    @Test(dependsOnMethods = {"submitOrder"}, retryAnalyzer = Retry.class, enabled = true)
    public void OrderHistoryTest()
    {

        ProductCataloguePage productCataloguePage=landingPage.LoginApp("tariqrasheed1989@gmail.com", "India@2015");
        OrderPage orderPage=productCataloguePage.goToOrdersPage();
        List<WebElement>historyProds= orderPage.getHistoryProducts();
        for (int i=0;i<historyProds.size();i++)
        {
                Assert.assertTrue(buyProduct.contains(historyProds.get(i).getText()));
        }
    }


    @DataProvider
    public Object[][] getData() throws IOException {
//        HashMap<String, String>map=new HashMap<String, String>();
//        map.put("username","aman1512@gmail.com");
//        map.put("password","Abcd@1234");
//
//        HashMap<String, String>map1=new HashMap<String, String>();
//        map1.put("username","tariqrasheed1989@gmail.com");
//        map1.put("password","India@2015");
        List<HashMap<String, String>>data=getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//Data//PurchaseOrder.json");
        return new Object [][] {{data.get(0)},{data.get(1)}};
    }
}
