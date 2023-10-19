import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class CalculatorPage {
    private final WebDriver driver;
    private final WebDriverWait webDriverWait;
    private final By numberOfInstancesBox = By.cssSelector("#input_99");
    private final By seriesCheckBox = By.cssSelector("md-select[placeholder=\"Series\"]>md-select-value[class=\"md-select-value\"]");
    private final By seriesValue = By.cssSelector("md-option[value=\"n1\"]");
    private final By machineTypeCheckBox = By.cssSelector("md-select[placeholder=\"Instance type\"]>md-select-value[class=\"md-select-value\"]");
    private final By machineTypeValue = By.cssSelector("md-option[value=\"CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-4\"] > div.md-text.ng-binding");
    private final By localSSD = By.cssSelector("md-select[placeholder=\"Local SSD\"]>md-select-value[class=\"md-select-value\"]");
    private final By GPUSelector = By.cssSelector("md-input-container>md-checkbox[ng-model=\"listingCtrl.computeServer.addGPUs\"]");
    private final By GPUType = By.cssSelector("md-select[aria-label=\"GPU type\"]");
    private final By GPUTypeSelector = By.cssSelector("#select_option_513 > div");
    private final By numberOfGPUSelector = By.cssSelector("#select_value_label_505 > span:nth-child(1)");
    private final By numberOfGPUOption = By.cssSelector("#select_option_516 > div");
    private final By numberOfSSD = By.cssSelector("#select_option_491 > div");
    private final By dataCenterSelector = By.cssSelector("#select_value_label_97 > span:nth-child(1) > div");
    private final By dataCenterOption = By.cssSelector("#select_option_264 > div");
    private final By committedUsageSelector= By.cssSelector("#select_value_label_98 > span:nth-child(1)");
    private final By committedUsageOption = By.cssSelector("#select_option_137 > div");
    private final By addToEstimateSelector = By.cssSelector("form[class=\"ng-scope ng-valid-min ng-valid-max ng-dirty ng-valid-number ng-valid ng-valid-required ng-valid-parse\"]>div>button[class=\"md-raised md-primary cpc-button md-button md-ink-ripple\"]");
    private final By totalCostSelector = By.cssSelector("h2[class=\"md-title\"]>b[class=\"ng-binding\"]");
    private final By emailEstimateButton = By.cssSelector("button[title=\"Email Estimate\"]");
    private final By emailInputSelector = By.cssSelector("#input_616");
    private final By sendEmailSelector = By.cssSelector("button[ng-disabled=\"emailForm.$invalid\"]");
    private final By iframe1Selector = By.cssSelector("[name^='goog_']");
    private final By iframe2Selector = By.id("myFrame");
    private final By notificationSelector = By.cssSelector(".devsite-snackbar-action");
    JavascriptExecutor js;

    public CalculatorPage(WebDriver driver, WebDriverWait webDriverWait) {
        this.driver = driver;
        this.webDriverWait = webDriverWait;
        js = (JavascriptExecutor) driver;
    }

    public void addNumberOfInstances() {
        WebElement iframe1 = driver.findElement(iframe1Selector);
        driver.switchTo().frame(iframe1);

        WebElement iframe2 = driver.findElement(iframe2Selector);
        driver.switchTo().frame(iframe2);

        WebElement numberOfInstances = driver.findElement(numberOfInstancesBox);
        numberOfInstances.click();
        numberOfInstances.sendKeys("4");
    }

    public void manageNotification() {
        WebElement notification = driver.findElement(notificationSelector);
        notification.click();
    }


    public void changeSeries() {
        WebElement seriesName = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(seriesCheckBox));
        seriesName.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(seriesValue)).click();
    }

    public void changeMachineType() {
        WebElement machineTypeSelector = driver.findElement(machineTypeCheckBox);
        machineTypeSelector.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(machineTypeValue)).click();
    }

    public void addGPU() throws InterruptedException {
        WebElement gpuSelector = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(GPUSelector));

        js.executeScript("arguments[0].scrollIntoView(true);", gpuSelector);
        js.executeScript("arguments[0].click();", gpuSelector);

        WebElement gType = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(GPUType));
        js.executeScript("arguments[0].scrollIntoView(true);", gType);
        Thread.sleep(500);
        gType.click();

        WebElement gpuType = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(GPUTypeSelector));
        gpuType.click();

        driver.findElement(numberOfGPUSelector).click();

        WebElement numberOfGPUs = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(numberOfGPUOption));
        numberOfGPUs.click();
    }

    public void localSSD() {
        WebElement localSSDSelector = driver.findElement(localSSD);
        localSSDSelector.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(numberOfSSD)).click();
    }

    public void addDataCenter() {
        WebElement dataCenter = driver.findElement(dataCenterSelector);
        dataCenter.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(dataCenterOption)).click();
    }

    public void addCommittedUsage() {
        WebElement committedUsage = driver.findElement(committedUsageSelector);
        committedUsage.click();
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(committedUsageOption)).click();
    }

    public void addToEstimate() {
        WebElement addToEstimate = driver.findElement(addToEstimateSelector);
        addToEstimate.click();
    }

    public String getTotalCost() {
        WebElement totalCostElement = driver.findElement(totalCostSelector);
        return totalCostElement.getText();
    }

    public void emailEstimate() {
        driver.findElement(emailEstimateButton).click();
    }

    public void switchToCalculatorPage(String window) {
        driver.switchTo().window(window);
    }

    public void sendEmailEstimate(String email) {
        driver.switchTo().defaultContent();
        WebElement iframe1 = driver.findElement(iframe1Selector);
        driver.switchTo().frame(iframe1);

        WebElement iframe2 = driver.findElement(iframe2Selector);
        driver.switchTo().frame(iframe2);

        WebElement emailInput = driver.findElement(emailInputSelector);
        emailInput.click();
        emailInput.sendKeys(email);
        driver.findElement(sendEmailSelector).click();
    }
}
