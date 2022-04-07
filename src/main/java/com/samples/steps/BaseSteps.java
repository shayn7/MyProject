package com.samples.steps;

import com.samples.factories.DriverFactory;
import com.samples.enums.Environments;
import com.samples.enums.Pages;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import com.samples.pages.BasePage;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public abstract class BaseSteps {

    private static final Logger log = LoggerFactory.getLogger(BaseSteps.class);
    private final Environments environment;
    private WebDriver driver;
    private BasePage expectedPage;

    protected BaseSteps(Environments environment) {
        this.environment = environment;
    }

    public void startSession() {
        MutableCapabilities options = initCapabilities();
        if (environment == Environments.REMOTE) {
            String hub = System.getProperty("hub");
            driver = DriverFactory.getRemoteDriver(hub, options);
        } else {
            driver = DriverFactory.getDriver(environment, options);
        }
    }

    public void iShouldBeOnPage(Pages page){
        expectedPage = page.getPage(this);
        logToConsoleAndReporter(String.format("page URL is: %s", driver.getCurrentUrl()));
        boolean isExpectedPage = expectedPage.verifyPage();
        Assert.assertTrue(isExpectedPage, "actual page is not " + page);
        logToConsoleAndReporter(String.format("we are on page: %s as expected", page));
    }

    public <T extends BasePage> T getExpectedPage(){
        return (T) expectedPage;
    }

    public void pressEnterButton() {
        Actions builder = new Actions(driver);
        builder.sendKeys(Keys.RETURN).perform();
    }

    public void hoverOverAnElement(WebElement element){
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public void doubleClickOnElementAndSetNewText(WebElement element, String value) {
        Actions action = new Actions(driver);
        action.moveToElement(element).doubleClick().doubleClick().sendKeys(value).build().perform();
    }

    @Step("getting URL: {0}")
    public void getUrl(String url){
        String currentUrl = driver.getCurrentUrl();
        String newUrl = currentUrl.replace(currentUrl, url);
        driver.get(newUrl);
        logToConsoleAndReporter(String.format("after getting the URL the page title is %s", getPageTitle()));
    }

    public void setText(WebElement element, String text){
        boolean isDisplayed = isElementDisplayed(element);
        if(isDisplayed){
            element.clear();
            element.sendKeys(text);
            logToConsoleAndReporter(String.format("entered the text: %s in the text box", text));
        }
    }

    public String getText(WebElement element){
        boolean isDisplayed = isElementDisplayed(element);
        return isDisplayed ? element.getText() : " ";
    }

    @Step("checking if element is displayed. element is: {0}")
    public boolean isElementDisplayed(WebElement element){
        try{
            waitFor(ExpectedConditions.visibilityOf(element));
            return true;
        } catch (ElementNotVisibleException e){
            return false;
        }
    }

    @Step("clicked on element: {0}")
    public void clickOnElement(WebElement element){
        boolean isClickable = isElementDisplayed(element);
        if(isClickable){
            element.click();
            logToConsoleAndReporter("clicked on element " + element);
        } else {
            logToConsoleAndReporter("wasn't able to click on element " + element);
        }
    }

    public boolean isElementDisabled(WebElement element){
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].hasAttribute('disabled)';", element);
    }

    public void tearDown(){
        if(driver != null) driver.quit();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public WebDriver getDriver() {
        return driver;
    }

    public ChromeDriver getChromeDriver(){
        return (ChromeDriver) driver;
    }

    private void logToConsoleAndReporter(String text) {
        log.info(text);
        saveTextLog(text);
    }

    private void waitFor(ExpectedCondition<WebElement> webElementExpectedCondition){
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(webElementExpectedCondition);
    }

    @Attachment(value = "{0}", type = "text/plain")
    private String saveTextLog(String text) {
        return text;
    }


    protected abstract MutableCapabilities initCapabilities();
}
