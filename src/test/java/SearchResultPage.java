import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultPage {
    private final WebDriver driver;
    private final By calculatorLinkSelector = By.xpath("//a[@class='gs-title' and @target='_self' and @dir='ltr']/b[text()='Google Cloud Pricing Calculator']");

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public void goToCalculator() {
        WebElement calculatorLink = driver.findElement(calculatorLinkSelector);
        calculatorLink.click();
    }
}
