
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created by haroonnaderi on 6/8/17.
 */
public class AmazonTestCase extends BrowserStackJUnitTest{


    @Test
    public void AmazonSearchTest(){

        driver.get("http://www.amazon.com");
        System.out.println("Running the actual tests rn");
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.sendKeys("iphone 6S");
        WebElement goBox = driver.findElement(By.xpath("//*[@id=\"nav-search-submit-text\"]"));
        goBox.submit();

        WebElement appleCheckBox = driver.findElement(By.xpath("//*[@id=\"ref_2528832011\"]/li[1]")).findElement(By.tagName("a"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(appleCheckBox));
        appleCheckBox.click();

        driver.navigate().refresh();
        WebElement priceFilter = driver.findElement(By.xpath("//*[@id=\"ref_14674871011\"]/li[6]")).findElement(By.tagName("a"));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(priceFilter));
//        Actions newAction = new Actions(driver);
//        newAction.moveToElement(priceFilter).click().perform();
        priceFilter.click();

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
}
