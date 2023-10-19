import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YopMailPage {
    private final WebDriver driver;
    private final WebDriverWait webDriverWait;
    private final By acceptCookiesSelector = By.cssSelector("button[id=\"accept\"]");
    private final By loginSelector = By.cssSelector("#login");
    private final By checkInboxSelector = By.cssSelector("button[title=\"Check Inbox @yopmail.com\"]");
    private final By refreshButton = By.cssSelector("button[id=\"refresh\"]");
    private final By iframeSelector = By.cssSelector("iframe[name=\"ifmail\"]");
    private final By totalCostSelector = By.cssSelector("#mail > div > div > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(2) > td:nth-child(2) > h3");

    JavascriptExecutor js;

    public YopMailPage(WebDriver driver, WebDriverWait webDriverWait) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
        js = (JavascriptExecutor) driver;
    }

    public void openYopMailPage() {
        js.executeScript("window.open('https://yopmail.com/')");
    }

    public void switchToYopMailPage(String window) {
        driver.switchTo().window(window);
    }

    public void acceptCookiesAndContinue() {
        WebElement acceptButton = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(acceptCookiesSelector));
        acceptButton.click();
    }

    public void enterInbox(String email) {

        WebElement enterInbox = driver.findElement(loginSelector);
        enterInbox.click();
        enterInbox.sendKeys(email);
    }

    public void checkInbox() {
        WebElement checkInboxButton = driver.findElement(checkInboxSelector);
        checkInboxButton.click();
    }

    public void refreshPage() {
        WebElement refreshPage = driver.findElement(refreshButton);
        refreshPage.click();
    }

    public float getTotalCostFromEmail() {
        WebElement iframe = driver.findElement(iframeSelector);
        driver.switchTo().frame(iframe);
        WebElement totalCostElement = driver.findElement(totalCostSelector);
        String totalCost = totalCostElement.getText().split("USD ")[1];
        return Float.parseFloat((totalCost));
    }
}
