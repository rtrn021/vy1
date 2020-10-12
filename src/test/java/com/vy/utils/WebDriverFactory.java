package com.vy.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class WebDriverFactory {

    // write a static method that takes a string parameter name: browserType
    // based on the value of parameter
    // it will set up the browser and the method will return chrome driver object
    // name of the method: getDriver
    public static WebDriver getDriver(String browsertype) {
        WebDriver driver= null;
        switch (browsertype.toLowerCase()){
            case"chrome":
                WebDriverManager.chromedriver().setup();
                driver= new ChromeDriver();
                break;

            case"firefox"  :
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver= new EdgeDriver();
                break;


        }
            return driver;

    }}

