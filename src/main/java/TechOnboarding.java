import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
import java.util.List;

/**
 * Created by haroonnaderi on 6/8/17.
 */
public class TechOnboarding {


    public static final String USERNAME = "haroonnaderi1";
    public static final String AUTOMATE_KEY = "D2VVGCgUDXDjmmvT2z6X";
    public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";

    public static void main(String[] args) throws Exception {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browser", "chrome");
        caps.setCapability("platform", "mac");
        caps.setCapability("browserstack.debug", "true");
        caps.setCapability("build", "v1");
        caps.setCapability("project", "tech onboarding 1");
        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);

        driver.get("http://www.amazon.com");
        System.out.println("Running the actual tests rn");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("iphone 6S");
        WebElement goBox = driver.findElement(By.xpath("//*[@id=\"nav-search-submit-text\"]"));
        goBox.submit();

        WebElement appleCheckBox = driver.findElement(By.cssSelector("#ref_2528832011 > li:nth-child(1) > a > img"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(appleCheckBox));
        appleCheckBox.click();

        driver.navigate().refresh();
        WebElement priceFilter = driver.findElement(By.cssSelector("#ref_14674871011 > li:nth-child(6) > a > span"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(priceFilter));
        Actions newAction = new Actions(driver);
        newAction.moveToElement(priceFilter).click().perform();

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
            String price = result.findElement(By.className("a-color-base")).getAttribute("aria-label");
            if (price == null)
            {
                price = result.findElement(By.className("a-color-base")).getText();
            }
            System.out.println(price);

        }

        System.out.println("Test is completed");
        driver.close();
        driver.quit();


    }

}
