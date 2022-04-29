package automationpractice;

import io.qameta.allure.Allure;
import io.restassured.http.Cookies;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.AuthorizationPage;
import pages.HeaderWidget;
import pages.HomePage;
import pages.MyAccountPage;
import utils.JSONUtils;
import utils.PropertyReader;
import utils.WebDriverUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static io.restassured.RestAssured.when;

public class AuthorizationTest extends BaseTest {

    Logger log = LoggerFactory.getLogger(AuthorizationTest.class);

    @AfterEach
    public void allureAttachScreenshot() {
        File screenShot = WebDriverUtils.getScreenshot(driver);
        try {
            Allure.addAttachment("authorize test screenshot", Files.newInputStream(screenShot.toPath()));
        } catch (IOException e) {
            System.err.println("could not attach screenshot");
        }
    }

    @Test
    public void authorizeTest() {
        log.info("start AuthorizeTest");
        driver.get(PropertyReader.BASEURL);
        HomePage homePage = new HomePage(driver).waitOnPage();
        AuthorizationPage authorizationPage = homePage.headerWidget.clickSignIn();

        MyAccountPage myAccountPage = authorizationPage.doAuthorize("skillupdemo@gmail.com", "12345");
        String account = myAccountPage.getAuthorizedAccount();
        Assertions.assertEquals("name lasr", account);
    }

    @Test
    public void sneakyAuthorize() throws IOException {
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
        String username = homePage.headerWidget.getAuthorizedAccount();

        Assertions.assertEquals( "name lasr", username,"user wa not logged in");
    }
}
