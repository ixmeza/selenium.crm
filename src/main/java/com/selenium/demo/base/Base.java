package com.selenium.demo.base;
import com.github.javafaker.Faker;
import com.selenium.demo.pages.*;
import com.selenium.demo.utils.Log;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Base {

    public static WebDriver driver;
    public static Driver driverInstance;
    public static WebDriverWait wait;
    private static final Faker faker = new Faker();
    public Log slog = Log.getInstance();
    public Logger log = slog.getLogger();

    private final By dashboardIcon = By.cssSelector("a[aria-label='Dashboard']");
    private final By peopleIcon = By.cssSelector("a[aria-label='People & Organizations']");
    private final By casesIcon = By.cssSelector("a[aria-label='Cases']");
    private final By calendarIcon = By.cssSelector("a[aria-label='Calendar & Tasks']");
    private final By salesIcon = By.cssSelector("a[aria-label='Sales Pipeline']");
    private final By reportsIcon = By.cssSelector("a[aria-label='Reports']");
    private final By searchBox = By.cssSelector("[role='searchbox']");
    private final By searchAll = By.cssSelector(".elastic-select__filters button:nth-child(1)");
    private final By searchContacts = By.cssSelector(".elastic-select__filters button:nth-child(2)");
    private final By searchOpportunity = By.cssSelector(".elastic-select__filters button:nth-child(3)");
    private final By searchCases = By.cssSelector(".elastic-select__filters button:nth-child(4)");
    private final By searchActivity = By.cssSelector(".elastic-select__filters button:nth-child(5)");

    private final By saveBtn = By.cssSelector(".form-actions [type='submit']");
    private final By cancelBtn = By.cssSelector(".form-actions button:nth-child(2)");

    public WebDriver getDriver()
    {
        return driver;
    }

    public static void initialize() throws MalformedURLException {
        driverInstance = new Driver();
        driver = Driver.getDriver();
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver,10);
        Log.info("Initializing driver");
    }

    public static void quit() {
        driver.quit();
        driver = null; // we destroy the driver object after quit operation
        Log.info("Quitting driver");
    }

    public static void close_driver() {
        driver.close();
        driver = null;  // we destroy the driver object after quit operation
        Log.info("Closing driver");
    }

    public DashboardPage goToDashboard(){
        Log.info("Go to Dashboard page");
        driver.findElement(dashboardIcon).click();
        return new DashboardPage();
    }

    public PeoplePage goToPeople(){
        Log.info("Go to People page");
        driver.findElement(peopleIcon).click();
        return new PeoplePage();
    }

    public CasesPage goToCases(){
        Log.info("Go to Cases page");
        driver.findElement(casesIcon).click();
        return new CasesPage();
    }

    public SalesPage gotoPipeline(){
        Log.info("Go to Pipeline page");
        driver.findElement(salesIcon).click();
        return new SalesPage();
    }

    public boolean isNotPresent(By locator) {
        Boolean notPresent = ExpectedConditions.not(ExpectedConditions.presenceOfElementLocated(locator)).apply(driver);
        Log.info("Verifying if locator "+locator.toString()+" is not present: " + notPresent);
        return notPresent;
    }

    public WebElement getSaveBtn() {
        Log.info("Getting save button");
        return driver.findElement(saveBtn);
    }

    public WebElement getCancelBtn() {
        Log.info("Getting cancel button");
        return driver.findElement(cancelBtn);
    }

    public String convertToLocalDate(Date myDate)
    {
        Log.info("Converting date object to local date format dd/MM/YYYY");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY");
        return formatter.format(myDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    public String getDummyDate()
    {
        Log.info("Getting a dummy date within 6 days");
        Date now = new Date();
        String myDateObj = convertToLocalDate(faker.date().future(6, TimeUnit.DAYS, now));
        String[] aux = myDateObj.split("/");
        return aux[1]+ "/"+ aux[0] + "/" + aux[2];
    }

    public String[] retrieveTimeValues(String time){
        Log.info("Retrieving time values");
        String[] result = new String[time.length() + 1];
        String hour = null;
        String minutes = null;
        String meridiem = null;
        for (String val: time.split(" "))
        {
            if(val.contains(":")){
                String [] array = val.split(":");
                hour = array[0];
                minutes= array[1];
            }
            else{
                meridiem = val;
            }
        }
        result[0] = hour;
        result[1] = minutes;
        result[2] = meridiem;
        return  result;
    }

    public OpportunityPage search(String query)
    {
        Log.info("Searching for opportunity:"+query+"");
        driver.findElement(searchBox).sendKeys(query);
        wait.until(ExpectedConditions.visibilityOfElementLocated(searchOpportunity)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='elastic-select__details']/span[@class='elastic-select__title'][text()[contains(.,'"+query+"')]]"))).click();
        return new OpportunityPage();
    }

    public void scrollToElement(WebElement element) {
        Log.info("Scrolling to element");
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", element);
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public String getPartyId() {
        String url = getUrl();
        String pattern = "(\\d+)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);
        // Now create matcher object.
        Matcher m = r.matcher(url);
        if (m.find( )) {
            return m.group(0);
        }
        else
        {
            return null;
        }
    }
}
