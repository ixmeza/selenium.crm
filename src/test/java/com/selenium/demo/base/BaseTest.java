package com.selenium.demo.base;

import com.github.javafaker.Faker;
import com.selenium.demo.pages.DashboardPage;
import com.selenium.demo.pages.LoginPage;
import com.selenium.demo.utils.Log;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;

public class BaseTest extends Base {

    public Faker faker = new Faker();
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    public static Log slog = Log.getInstance();
    public static Logger log = slog.getLogger();
    ReadConfig readConfig = new ReadConfig();

    @BeforeMethod
    public void setupTest() throws MalformedURLException {
        Base.initialize();
        driver.manage().window().maximize();
        log.info("===== Initializing driver ===== ");
        log.info("Navigating to URL");
        driver.get(readConfig.getUrl());
        log.info("Authenticating with valid values");
        loginPage = new LoginPage();
        loginPage.setUsername(readConfig.getUserName());
        loginPage.setPassword(readConfig.getPassword());
        dashboardPage = loginPage.clickLoginBtn();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            Base.quit();
            driver = null; // we destroy the driver object after quit operation
            log.info("Quitting driver");
        }
    }

    public DashboardPage getDashboardPage() {
        log.info("Getting dashboard page");
        return dashboardPage;
    }
}
