import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class MainTest {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));

        // Step 1: Open Google Cloud Home Page
        driver.get("https://cloud.google.com/");
        driver.manage().window().maximize();
        GoogleCloudHomePage homePage = new GoogleCloudHomePage(driver);

        // Step 2-4: Search and go to calculator page
        homePage.searchForCalculator("Google Cloud Platform Pricing Calculator");
        SearchResultPage searchResultPage = new SearchResultPage(driver);
        searchResultPage.goToCalculator();

        // Step 5-8: Fill out the form and check the price
        CalculatorPage calculatorPage = new CalculatorPage(driver, webDriverWait);
        calculatorPage.manageNotification();
        calculatorPage.addNumberOfInstances();
        calculatorPage.changeSeries();
        calculatorPage.changeMachineType();
        calculatorPage.addGPU();
        calculatorPage.localSSD();
        calculatorPage.addDataCenter();
        calculatorPage.addCommittedUsage();
        calculatorPage.addToEstimate();
        String totalCost = calculatorPage.getTotalCost();

        float cost = Float.parseFloat(totalCost.split("USD ", 2)[1].split(" per")[0]);
        Assert.assertTrue(cost > 0);
        System.out.println("Total Estimated Cost: " + cost);

        // Step 9-15: Email estimate and validate
        calculatorPage.emailEstimate();


        YopMailPage yopMailPage = new YopMailPage(driver, webDriverWait);

        String firstWindow = driver.getWindowHandles().stream().findFirst().get();

        yopMailPage.openYopMailPage();
        String secondWindow = "";
        for (String window : driver.getWindowHandles()) {
            if (window != firstWindow)
                secondWindow = window;
        }
        yopMailPage.switchToYopMailPage(secondWindow);
        yopMailPage.acceptCookiesAndContinue();
        String emailInbox = "epamTask2_email";
        yopMailPage.enterInbox(emailInbox);
        yopMailPage.checkInbox();

        calculatorPage.switchToCalculatorPage(firstWindow);
        calculatorPage.sendEmailEstimate(emailInbox + "@yopmail.com");
        yopMailPage.switchToYopMailPage(secondWindow);
        //Wait 30 seconds to receive the email
        Thread.sleep(10000);
        yopMailPage.refreshPage();
        float totalCostFromEmail = yopMailPage.getTotalCostFromEmail();
        Assert.assertEquals(cost, totalCostFromEmail);
        // Close the browser
        driver.quit();
    }
}
