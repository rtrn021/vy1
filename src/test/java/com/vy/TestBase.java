package com.vy;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.vy.utils.BrowserUtils;
import com.vy.utils.ConfigurationReader;
import com.vy.utils.Driver;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;


public class TestBase {

  protected WebDriver driver;
  protected Actions actions;
  protected WebDriverWait wait;

  //this class is just for starting and building reports

  static protected ExtentReports report;

  //this class is used to create HTML report file

  static protected ExtentHtmlReporter htmlReporter;

  //this will define a test, enable adding logs, authors, test steps

  static protected ExtentTest extentLogger;

  @BeforeTest
  public void setUpTest() {
    System.out.println("Before test is running get ready!!!");
    //initialize the class
    report = new ExtentReports();
    System.out.println("hi");

    //create a report path
    String projectPath = System.getProperty("user.dir");
    String path = projectPath + "/test-output/report.html";

    //initialize the html reporter with the report path
    htmlReporter = new ExtentHtmlReporter(path);

    //attach the html report to report object
    report.attachReporter(htmlReporter);

    //title in report
    htmlReporter.config().setReportName("Vytrack2 Smoke Test");

    //set environment information
    report.setSystemInfo("Environment", "QA");
    report.setSystemInfo("Browser", ConfigurationReader.get("browser"));
    report.setSystemInfo("OS", System.getProperty("os.name"));

  }


  @BeforeMethod
  public void setUp() {
    System.out.println("Before method is running get ready!!!");
    driver = Driver.get();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    actions = new Actions(driver);
    wait = new WebDriverWait(driver, 10);

    driver.get(ConfigurationReader.get("url"));
    Pages.initialise();
  }

  @AfterTest
  public void teardown() {
    System.out.println("After Test is running!!!");
    //this is when the report is actually created
    report.flush();

  }

  //ITestResult class describes the result of a tests in TESTNG
  @AfterMethod
  public void tearDown(ITestResult result) throws InterruptedException, IOException {
    System.out.println("After method is running!!!");
    //if test fails
    if (result.getStatus() == ITestResult.FAILURE) {
// record the name of failed testcase
      extentLogger = report.createTest("failed");
      extentLogger.fail(result.getName());

      //take the screen shot and return location of screenshot
      String screenShotPath = BrowserUtils.getScreenshot(result.getName());
//add your screenshot to your report

      extentLogger.addScreenCaptureFromPath(screenShotPath);

      //capture the exception and put inside report

      extentLogger.fail(result.getThrowable());


    }

    Thread.sleep(2000);
//    Driver.closeDriver();
  }

}
