package automationpractice;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HomePage;
import pages.MyStorePage;
import pages.ProductDetailsPage;
import utils.JSONUtils;
import utils.PropertyReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ReviewTest extends BaseTest {

    String authUrl = "http://automationpractice.com/index.php?controller=authentication&email=testmail_skup@gmail.com&passwd=12345&back=my-account&SubmitLogin=";

    Logger log = LoggerFactory.getLogger(ReviewTest.class);

    @Test
    @DisplayName("write item review")
    void leaveAComment() {
        log.info("start leave_a_comment_test");
        driver.get(authUrl);
        HomePage.open(driver);

        log.info("navigate to Summer dresses shop page");
        MyStorePage myStorePage = navigateToSummerDresses();
        myStorePage.enableGirlyCheckbox();

        log.info("try to click on first shop item");
        ProductDetailsPage detailsPage = myStorePage.viewDressDetails(1);
//        myStorePage.viewDressDetails("Chiffon");

        detailsPage.createReview(5, "my review", "pretty good dress");
        String confirmationText = detailsPage.getConfirmReviewHeader();

        Assertions.assertEquals("New comment", confirmationText, "dress review was not created");
        log.info("end leave_a_comment_test");
    }
    @Test
    @DisplayName("Try use switch")
    void useSwitch(){

        driver.get(authUrl);

   // String oldTab = driver.getWindowHandle();
    driver.findElement(By.xpath("//i [@class=\"icon-list-ol\"]")).click();
    driver.findElement(By.xpath("//a [@title=\"Contact Us\"]")).click();
    ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

 driver.switchTo().window(tabs.get(1));
 driver.close();
 driver.switchTo().window(tabs.get(2));
//java.lang.IndexOutOfBoundsException: Index 1 out of bounds for length 1
 //   driver.switchTo().window(oldTab);
   // driver.close();
    }
@Test
@DisplayName("HomeTask19 Try use cookies")
void addCookies() throws IOException {
        driver.get(PropertyReader.BASEURL);
        HomePage homePage = new HomePage(driver).waitOnPage();

        driver.manage().deleteAllCookies();

        JSONObject jsonCookies = JSONUtils.getFileContentsAsJsonObject("src/test/resources/Cookies.json");
    Cookie cookie = new Cookie(
            jsonCookies.getString("name"),
            jsonCookies.getString("value")
            );
    driver.manage().addCookie(cookie);

    driver.navigate().refresh();
    driver.findElement(By.xpath("//a [@title=\"View my shopping cart\"]")).click();
    BaseTest baseTest = new BaseTest();
   // baseTest.clickOnCart(); // driver is null

   String totalPrice = "$45.51";
   WebElement totalPrice1 = driver.findElement(By.cssSelector("span#total_price"));
   String totalText = totalPrice1.getText();
    System.out.println(totalText);
    log.info("Print price" +totalText);


   Assertions.assertEquals(totalPrice, totalText); //how to assert on webelement

    }

    @Test
    @DisplayName("Use implisit wait")
    void implisitWait(){
        driver.manage().timeouts().implicitlyWait(999, TimeUnit.DAYS); //no years))
        driver.get(authUrl);
        driver.findElement(By.xpath("//a [@title=\"View my shopping cart\"]")).click();
    }
    @Test
    @DisplayName("Use explisit wait")
    void explisitWait(){
        driver.get(authUrl);
        WebElement explisitWait = (new WebDriverWait(driver, 111)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a [@title=\"View my shopping cart\"]")));
    explisitWait.click();
    }





    @Test
    @DisplayName("HomeTaskBro")
    void homeTask(){
        log.info("start homtask in 8 time)");
        driver.get(authUrl);
        HomePage.open(driver);

        log.info("navigate to whoman button");
        MyStorePage myStorePage = navigateToSummerDresses();
        MyStorePage baseTest = navigateToFirsDress();

        driver.findElement(By.xpath("//div [@class=\"header-container\"]")).click();
        driver.findElement(By.cssSelector("a.account")).click();
        driver.findElement(By.xpath("//i [@class=\"icon-heart\"]")).click();
        driver.findElement(By.xpath("//a [@class=\"icon\"]")).click();

        Alert alert = driver.switchTo().alert();
        alert.accept();
// MyStorePage ostalnie = ostalnieDela();
 //ili так

//        BaseTest ostalnie2 = new BaseTest();
//        ostalnie2.ostalnieDela2();

        // в таком варианте метод не работает и выдает ошибку что драйвер нулевой. Работает вообщем только вариант с созданием в класе Бейс Тест метода с возвратным значением МуСторПадже, а просто не выходит( пока еще!

    }

}
