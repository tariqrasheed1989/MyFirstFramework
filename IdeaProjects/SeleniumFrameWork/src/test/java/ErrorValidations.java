import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.ConfirmationPage;
import Pages.ProductCataloguePage;
import Tests.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ErrorValidations extends BaseTest
{
    @Test(groups = {"ErrorHandling"}, enabled = false)
    public void submitOrder() throws IOException {
        landingPage.LoginApp("tariqrasheed1989@gmail.com", "India@2015____");
        //wrong password
        String ErrorMessage=landingPage.getErrorMsg();
        Assert.assertEquals(ErrorMessage,"Incorrect email or password.");
    }
}
