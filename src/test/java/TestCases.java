import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.util.List;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;



/**
 * Created by haroonnaderi on 6/5/17.
 */
public class TestCases {

    WebDriver driver;
    WebDriverWait webDriverWait;
    public static final String USERNAME = "haroonnaderi1";
    public static final String AUTOMATE_KEY = "D2VVGCgUDXDjmmvT2z6X";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";


    @Before
    public void setUpTest(){

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "Firefox");
        caps.setCapability("platform", "Mac");
        caps.setCapability("browserstack.debug", "true");
        caps.setCapability("build", "v1");
        caps.setCapability("project", "tech onboarding 1");
        try {
            driver = new RemoteWebDriver(new URL(URL), caps);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        webDriverWait = new WebDriverWait(driver, 5);


    }

    @Test
    public void AmazonSearchTest(){

        driver.get("http://www.amazon.com");
        System.out.println("Running the actual tests rn");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("iphone 6S");
        WebElement goBox = driver.findElement(By.xpath("//*[@id=\"nav-search-submit-text\"]"));
        goBox.submit();

//        WebElement appleCheckBox = driver.findElement(By.xpath("//*[@id=\"ref_2528832011\"]/li[1]")).findElement(By.tagName("a"));
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(appleCheckBox));
//        appleCheckBox.click();
//
//        driver.navigate().refresh();
//        WebElement priceFilter = driver.findElement(By.xpath("//*[@id=\"ref_14674871011\"]/li[6]")).findElement(By.tagName("a"));
//        webDriverWait.until(ExpectedConditions.elementToBeClickable(priceFilter));
////        Actions newAction = new Actions(driver);
////        newAction.moveToElement(priceFilter).click().perform();
//        priceFilter.click();

        int attempts = 0;
        while (attempts < 3){
            try {
                webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"ref_2528832011\"]/li[1]")).findElement(By.tagName("a")))).click();
                break;
            }
            catch (Exception e){
            }
            attempts++;
        }
        driver.navigate().refresh();
        attempts = 0;
        while (attempts < 3){
            try {
                webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//*[@id=\"ref_14674871011\"]/li[6]")).findElement(By.tagName("a")))).click();
                break;
            }
            catch (Exception e){
            }
            attempts++;
        }

        List<WebElement> searchResults = driver.findElements(By.className("s-result-item"));
//        for (int i = 0; i < searchResults.size(); i++){
//            System.out.println(i);
//            System.out.println(driver.findElements(By.xpath("//div[contains(@class, 'a-fixed-left-grid-col a-col-right')]")).get(i).findElement(By.xpath("//a[contains(@class, 'a-link-normal s-access-detail-page  s-color-twister-title-link a-text-normal')]")).getAttribute("title"));
//        }
//        List<WebElement> listOfTitlesAndUrls = driver.findElements(By.xpath("//a[contains(@class, 'a-link-normal s-access-detail-page  s-color-twister-title-link a-text-normal')]"));
//      driver.findElements(By.className("s-result-item")).get(4).findElement(By.className("s-access-detail-page")).getAttribute("title")
        System.out.println(searchResults.size());
        for (WebElement result: searchResults){
            System.out.println(result.findElement(By.className("s-access-detail-page")).getAttribute("title"));
            System.out.println(result.findElement(By.className("s-access-detail-page")).getAttribute("href"));
            //String price = result.findElement(By.className("a-color-base")).getAttribute("aria-label");
            WebElement priceElement = result.findElement(By.className("a-color-base"));
            String price;
            if (priceElement.getAttribute("aria-label") == null) {
                price = priceElement.getAttribute("aria-label");
            }
            else{
                price = priceElement.getText();
            }
            System.out.println(price);

        }

    }

    @After
    public void TearDown(){
        System.out.println("Test is completed");
        driver.close();
        driver.quit();

    }
}
