import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YopMailPage {
    private final WebDriver driver;
    private final WebDriverWait webDriverWait;

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
        WebElement acceptButton = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("button[id=\"accept\"]")));
        acceptButton.click();
    }

    public void enterInbox(String email) {

        WebElement enterInbox = driver.findElement(By.cssSelector("#login"));
        enterInbox.click();
        enterInbox.sendKeys(email);
    }

    public void checkInbox() {
        WebElement checkInboxButton = driver.findElement(By.cssSelector("button[title=\"Check Inbox @yopmail.com\"]"));
        checkInboxButton.click();
    }

    public void refreshPage() {
        WebElement refreshPage = driver.findElement(By.cssSelector("button[id=\"refresh\"]"));
        refreshPage.click();
    }

    public float getTotalCostFromEmail() {
        WebElement iframe = driver.findElement(By.cssSelector("iframe[name=\"ifmail\"]"));
        driver.switchTo().frame(iframe);
        WebElement totalCostElement = driver.findElement(By.cssSelector("#mail > div > div > table > tbody > tr:nth-child(2) > td > table > tbody > tr:nth-child(2) > td:nth-child(2) > h3"));
        String totalCost = totalCostElement.getText().split("USD ")[1];
        return Float.parseFloat((totalCost));
    }
}
