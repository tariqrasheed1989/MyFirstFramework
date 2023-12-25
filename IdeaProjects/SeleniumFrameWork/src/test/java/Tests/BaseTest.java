package Tests;

import Pages.LandingPage;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;


public class BaseTest
{
    public WebDriver driver;
    public LandingPage landingPage;
    public WebDriver initializeDriver() throws IOException
    {
        FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//src//main//java//Resources//GlobalData.properties");
        Properties prop=new Properties();
        prop.load(fis);
      String BrowserName=System.getProperty("browser")!=null ?System.getProperty("browser"):prop.getProperty("browser");
        //String BrowserName=prop.getProperty("browser");

        if (BrowserName.equalsIgnoreCase("Chrome")) {
             driver = new ChromeDriver();

        }
        else if (BrowserName.equalsIgnoreCase("Edge"))
        {
             driver=new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        return driver;
    }
    public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
        //read Json to string
        String JsonContent= FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
        //convert to HashMap
        ObjectMapper mapper=new ObjectMapper();
        List<HashMap<String, String>> data =mapper.readValue(JsonContent, new TypeReference<List<HashMap<String,String>>>() {});
        return data;
    }

//    public String getScreenshot(String TestCaseName, WebDriver driver) throws IOException {
//        TakesScreenshot ts=(TakesScreenshot) driver;
//        File source=ts.getScreenshotAs(OutputType.FILE);//source
//        File file=new File(System.getProperty("user.dir")+"/reports/"+TestCaseName+".jpg");//destination
//        FileUtils.copyFile(source,file);
//        return System.getProperty("user.dir")+"/reports/"+TestCaseName+".jpg";
//    }
        public String getScreenshot(String TestCaseName, WebDriver driver) throws IOException {

        String screenshotPath = null;
        try{
        //take screenshot and save it in a file
        File sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        //copy the file to the required path
        File destinationFile = new File(System.getProperty("user.dir")+"/reports/"+TestCaseName+"_image_" + System.currentTimeMillis()+ ".png");
        FileHandler.copy(sourceFile, destinationFile);
        String[] relatvePath = destinationFile.toString().split("reports");
        screenshotPath = ".//" + relatvePath[1];
    }
    catch(Exception e){
        System.out.println("Failure to take screenshot "+e);
    }
    return screenshotPath;
}


    @BeforeMethod (alwaysRun = true)
    public LandingPage launchApplication() throws IOException
    {
        WebDriver driver= initializeDriver();
        landingPage=new LandingPage(driver);
        landingPage.goTo();
        return landingPage;
    }
    @AfterMethod(alwaysRun = true)
    public void tearDown()
    {
        driver.close();
    }
}
