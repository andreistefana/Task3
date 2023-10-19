import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CalculatorPage {
    private final WebDriver driver;
    private final WebDriverWait webDriverWait;
    private final By numberOfInstancesBox = By.cssSelector("#input_99");
    private final By series = By.cssSelector("md-select[placeholder=\"Series\"]>md-select-value[class=\"md-select-value\"]");
    private final By machineType = By.cssSelector("md-select[placeholder=\"Instance type\"]>md-select-value[class=\"md-select-value\"]");
    private final By localSSD = By.cssSelector("md-select[placeholder=\"Local SSD\"]>md-select-value[class=\"md-select-value\"]");
    JavascriptExecutor js;

    public CalculatorPage(WebDriver driver, WebDriverWait webDriverWait) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
        js = (JavascriptExecutor) driver;
    }

    public void addNumberOfInstances() {
        WebElement iframe1 = driver.findElement(By.cssSelector("[name^=\"goog_\"]"));
        driver.switchTo().frame(iframe1);

        WebElement iframe2 = driver.findElement(By.id("myFrame"));
        driver.switchTo().frame(iframe2);

        WebElement numberOfInstances = driver.findElement(numberOfInstancesBox);
        numberOfInstances.click();
        numberOfInstances.sendKeys("4");
    }

    public void manageNotification() {
        WebElement notification = driver.findElement(By.cssSelector(".devsite-snackbar-action"));
        notification.click();
    }


    public void changeSeries() {
        WebElement seriesName = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(series));
        seriesName.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("md-option[value=\"n1\"]"))).click();
    }

    public void changeMachineType() {
        WebElement machineTypeSelector = driver.findElement(machineType);
        machineTypeSelector.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("md-option[value=\"CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-4\"] > div.md-text.ng-binding"))).click();
    }

    public void addGPU() throws InterruptedException {
        WebElement gpuSelector = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("md-input-container>md-checkbox[ng-model=\"listingCtrl.computeServer.addGPUs\"]")));

        js.executeScript("arguments[0].scrollIntoView(true);", gpuSelector);
        js.executeScript("arguments[0].click();", gpuSelector);

        WebElement gType = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("md-select[aria-label=\"GPU type\"]")));
        js.executeScript("arguments[0].scrollIntoView(true);", gType);
        Thread.sleep(500);
        gType.click();

        WebElement gpuType = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#select_option_513 > div")));
        gpuType.click();

        driver.findElement(By.cssSelector("#select_value_label_505 > span:nth-child(1)")).click();

        WebElement numberOfGPUs = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#select_option_516 > div")));
        numberOfGPUs.click();
    }

    public void localSSD() {
        WebElement localSSDSelector = driver.findElement(localSSD);
        localSSDSelector.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#select_option_491 > div"))).click();
    }

    public void addDataCenter() {
        WebElement dataCenter = driver.findElement(By.cssSelector("#select_value_label_97 > span:nth-child(1) > div"));
        dataCenter.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#select_option_264 > div"))).click();
    }

    public void addCommittedUsage() {
        WebElement commitedUsage = driver.findElement(By.cssSelector("#select_value_label_98 > span:nth-child(1)"));
        commitedUsage.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#select_option_137 > div"))).click();
    }

    public void addToEstimate() {
        WebElement addToEstimate = driver.findElement(By.cssSelector("form[class=\"ng-scope ng-valid-min ng-valid-max ng-dirty ng-valid-number ng-valid ng-valid-required ng-valid-parse\"]>div>button[class=\"md-raised md-primary cpc-button md-button md-ink-ripple\"]"));
        addToEstimate.click();
    }

    public String getTotalCost() {
        WebElement totalCostElement = driver.findElement(By.cssSelector("h2[class=\"md-title\"]>b[class=\"ng-binding\"]"));
        return totalCostElement.getText();
    }

    public void emailEstimate() {
        By emailEstimateButton = By.cssSelector("button[title=\"Email Estimate\"]");
        driver.findElement(emailEstimateButton).click();
    }

    public void switchToCalculatorPage(String window) {
        driver.switchTo().window(window);
    }

    public void sendEmailEstimate(String email) {
        driver.switchTo().defaultContent();
        WebElement iframe1 = driver.findElement(By.cssSelector("[name^=\"goog_\"]"));
        driver.switchTo().frame(iframe1);

        WebElement iframe2 = driver.findElement(By.id("myFrame"));
        driver.switchTo().frame(iframe2);

        WebElement emailInput = driver.findElement(By.cssSelector("#input_616"));
        emailInput.click();
        emailInput.sendKeys(email);
        driver.findElement(By.cssSelector("button[ng-disabled=\"emailForm.$invalid\"]")).click();
    }
}
