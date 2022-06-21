package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

public class BaseShopPage {
    protected WebDriver driver;

    By signIn = By.className("login");
    By signOut = By.className("logout");
    By account = By.cssSelector("a.account span");
    By contactUs = By.id("contact-link");
    By shoopingCart = By.className("shopping_cart");

    public BaseShopPage(WebDriver driver) {
        this.driver = driver;
        waitOnPage();
    }

    public AuthorizationPage clickSignIn() {
        driver.findElement(signIn).click();
        return new AuthorizationPage(driver);
    }

    public String getAuthorizedAccount() {
        return driver.findElement(account).getText();
    }
    public boolean isUnAuthorizedAccount() {
        List<WebElement> logIn = driver.findElements(signIn);
        return logIn.isEmpty();
    }
    public BaseShopPage clickOnCart(){
        driver.findElement(shoopingCart).click();
    return this;
    }

    public BaseShopPage waitOnPage() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.pollingEvery(Duration.ofSeconds(3));
        wait.ignoring(NoSuchElementException.class);

        wait.until(ExpectedConditions.elementToBeClickable(contactUs));
        return this;
    }
}
