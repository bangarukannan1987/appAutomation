package com.styli.runCukes;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.styli.driverhandler.Driver;
import com.styli.driverhandler.DriverManager;
import com.styli.screens.LoginScreen;
import com.styli.screens.ProductScreen;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.*;

public class TestCases {
    ExtentReports extentReports;

    @BeforeSuite
    public void setUpReport() {
        extentReports = new ExtentReports();
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("extent_report.html");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("APP AUTOMATION REPORT");
        sparkReporter.config().setReportName("LOGIN AND PRODUCT SEARCH TEST");
        extentReports.attachReporter(sparkReporter);
    }

    @BeforeMethod
    @Parameters({"deviceName", "platformVersion"})
    public void setUp(String deviceName, String platformVersion) throws Exception{
        System.setProperty("deviceName",deviceName);
        Driver.initDriver(deviceName, platformVersion);
    }

    @AfterMethod
    public void tearDown(){
        Driver.quitDriver();
    }

    @Test(priority = 0)
    public void validLogin(){
        ExtentTest extentTest = extentReports.createTest("VALID LOGIN TEST").assignCategory("REGRESSION").assignDevice(System.getProperty("platformName").toUpperCase());
        if(System.getProperty("deviceName")!=null)
            extentTest.assignDevice("DEVICE NAME -"+System.getProperty("deviceName").toUpperCase());
        new LoginScreen().sendUserName("standard_user").sendPassword("secret_sauce").clickSuccuessLogin().verifyCartButton();
        extentTest.pass("LOGIN SUCCESSFUL!");
        extentTest.addScreenCaptureFromBase64String(((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64));
    }

    @Test(priority = 1)
    public void invalidLogin(){
        ExtentTest extentTest = extentReports.createTest("INVALID LOGIN TEST").assignCategory("Regression").assignDevice(System.getProperty("platformName"));
        if(System.getProperty("deviceName")!=null)
            extentTest.assignDevice("DEVICE NAME -"+System.getProperty("deviceName").toUpperCase());
        new LoginScreen().sendUserName("standard").sendPassword("secret").clickSuccuessLogin();
        extentTest.pass("INVALID LOGIN CASE SUCCESSFULL!");
        extentTest.addScreenCaptureFromBase64String(((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64));
    }

    @Test(priority = 2)
    public void validateProduct(){
        ExtentTest extentTest = extentReports.createTest("PRODUCT SEARCH TEST").assignCategory("Regression").assignDevice(System.getProperty("platformName"));
        if(System.getProperty("deviceName")!=null)
            extentTest.assignDevice("DEVICE NAME -"+System.getProperty("deviceName").toUpperCase());
        ProductScreen productScreen = new LoginScreen().sendUserName("standard_user").sendPassword("secret_sauce").clickSuccuessLogin();
        productScreen.selectProduct("Sauce Labs Backpack");
        productScreen.searchProduct("Sauce Labs Backpack");
        productScreen.verifyProduct();
        extentTest.pass("PRODUCT DETAILS SEARCH COMPLETED AND VERIFIED!");
        extentTest.addScreenCaptureFromBase64String(((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64));
    }

    @AfterSuite
    public void flushReport() {
        extentReports.flush();
    }
}
