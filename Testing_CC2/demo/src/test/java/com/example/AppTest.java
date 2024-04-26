package com.example;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class AppTest {
    WebDriver driver;
    ExtentReports extent;
    ExtentSparkReporter spark;
    public Actions action;
    public JavascriptExecutor js;

    @BeforeTest
    public void beforeTestMethod() throws InterruptedException {
        driver = new ChromeDriver();
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(
                "C:\\Users\\prave\\OneDrive\\Desktop\\Testing_CC2\\demo\\src\\extentreport\\report.html");
        extent.attachReporter(spark);

        spark.config().setDocumentTitle("CC2 Report");
        spark.config().setTheme(Theme.DARK);
    }

    @Test
    public void testcase1() throws InterruptedException {
        driver.get("https://www.barnesandnoble.com");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[1]/a")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[1]/div/a[2]")).click();
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/div[2]/div/input[1]"))
                .sendKeys("Chetan Bhagat");
        driver.findElement(By.xpath("//*[@id='rhf_header_element']/nav/div/div[3]/form/div/span/button")).click();
        String wb = driver
                .findElement(By.xpath("//*[@id='searchGrid']/div/section[1]/section[1]/div/div[1]/div[1]/h1/span"))
                .getText();
        if (wb.equals("Chetan Bhagat")) {
            System.out.print("It Contains keyword Chetan Bhagat in it");
        } else {
            System.out.print("It Not Contains keyword Chetan Bhagat in it");
        }
        ExtentTest test1 = extent.createTest("test case 1");
        test1.log(Status.PASS, "Search Success");
    }

    @Test
    public void testcase2() throws InterruptedException {
        driver.get("https://www.barnesandnoble.com");
        WebElement audioBook = driver.findElement(By.xpath("//*[@id='rhfCategoryFlyout_Audiobooks']"));

        Actions action = new Actions(driver);
        action.moveToElement(audioBook).perform();

        Thread.sleep(5000);

        driver.findElement(
                By.xpath("//*[@id='navbarSupportedContent']/div/ul/li[5]/div/div/div[1]/div/div[2]/div[1]/dd/a[1]"))
                .click();
        Thread.sleep(5000);

        ExtentTest test2 = extent.createTest("test case 2");
        test2.log(Status.PASS, "Search Success");

    }

    @Test
    public void testcase3() throws InterruptedException, IOException {
        driver.get("https://www.barnesandnoble.com");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1900)");
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id='onetrust-accept-btn-handler']")).click();
        Thread.sleep(5000);
        driver.findElement(By.linkText("B&N MEMBERSHIP")).click();
        js.executeScript("window.scrollBy(0, 1600)");
        Thread.sleep(5000);

        driver.findElement(By.linkText("JOIN REWARDS")).click();

        File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "C:\\Users\\prave\\OneDrive\\Desktop\\Testing_CC2\\demo\\src\\extentreport\\image.png";
        FileUtils.copyFile(screen, new File(path));

        ExtentTest test3 = extent.createTest("test case 3");
        test3.log(Status.PASS, "Search Success");
    }

    @AfterTest
    public void aftertest() {
        driver.close();
        extent.flush();
    }
}
