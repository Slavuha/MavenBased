package automationpractice;

import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.internal.KeysRelatedAction;
import org.openqa.selenium.support.ui.Wait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.HomePage;
import pages.MyStorePage;
import pages.ProductDetailsPage;

import java.awt.*;
import java.util.function.Function;

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

// MyStorePage ostalnie = ostalnieDela();
 //ili так

//        BaseTest ostalnie2 = new BaseTest();
//        ostalnie2.ostalnieDela2();

        // в таком варианте метод не работает и выдает ошибку что драйвер нулевой. Работает вообщем только вариант с созданием в класе Бейс Тест метода с возвратным значением МуСторПадже, а просто не выходит( пока еще!

    }

}
