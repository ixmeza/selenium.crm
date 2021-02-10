package com.selenium.demo.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.MalformedURLException;

public class Driver {
    private static WebDriver driver;
    public Driver() throws MalformedURLException {
        System.out.println("Object created.");
        ReadConfig readConfig = new ReadConfig();
        if (readConfig.getPlatform().equalsIgnoreCase("web")) {
            System.setProperty("webdriver.chrome.driver", "./resources/chromedriver");
            // Set headless chrome
            ChromeOptions option = new ChromeOptions();
            // option.addArguments("headless");
            driver = new ChromeDriver(option);
        }
    }
    public static WebDriver getDriver()
    {
        return driver;
    }
}
