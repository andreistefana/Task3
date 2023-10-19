import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GoogleCloudHomePage {
    private final WebDriver driver;
    private final By searchBox = By.name("q");

    public GoogleCloudHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void searchForCalculator(String query) {
        WebElement searchInput = driver.findElement(searchBox);
        searchInput.sendKeys(query);
        searchInput.submit();
    }
}
