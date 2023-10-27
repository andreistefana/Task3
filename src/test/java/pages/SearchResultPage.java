package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchResultPage {
    private final WebDriver driver;
    private final WebDriverWait webDriverWait;
    private final By calculatorLinkSelector = By.xpath("//a[@class='gs-title' and @target='_self' and @dir='ltr']/b[text()='Google Cloud Pricing Calculator']");

    public SearchResultPage(WebDriver driver, WebDriverWait webDriverWait) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
    }

    public void goToCalculator() {
        WebElement calculatorLink = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(calculatorLinkSelector));
        calculatorLink.click();
    }
}
