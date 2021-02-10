package com.selenium.demo.pages;
import com.selenium.demo.base.Base;
import com.selenium.demo.utils.Log;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class PersonPage extends Base {

    // locators
    private final By titleSelect = By.xpath("//*[@class='select-box single-select chunky party-form-title']");
    private final By firstNameTxt = By.cssSelector("input.form-input-text.party-form-first-name");
    private final By lastNameTxt = By.cssSelector("input.form-input-text.party-form-last-name");
    private final By jobTitleTxt = By.cssSelector(".form-input-text.party-form-job-title");
    private final By organizationSelect = By.cssSelector(".form.chunky input[placeholder='Find an organization']");
    private final By tagsSelect = By.cssSelector(".item-select.party-form-tag-select input");
    private final By phoneNumbersTxt = By.cssSelector(".form-input-text.party-form-phone-number");
    private final String phoneNumberXpath = "//input[@class='form-input-text party-form-phone-number']";
    private final By emailTxt = By.cssSelector("input.form-input-text.party-form-email-address");
    private final String emailXpath = "//input[@class='form-input-text party-form-email-address']";
    private final By webSitesTxt = By.cssSelector("input.form-input-text.party-form-website");
    private final String webSiteXpath = "//input[@class='form-input-text party-form-website']";
    private final By addPhoneNumberBtn = By.xpath("(//div[@class='contact-mechanisms']/div[@class='repeater '])[1]/button");
    private final By addEmailBtn = By.xpath("(//div[@class='contact-mechanisms']/div[@class='repeater '])[2]/button");
    private final By addWebSiteBtn = By.xpath("(//div[@class='contact-mechanisms']/div[@class='repeater '])[3]/button");
    private final By addAddressBtn = By.xpath("//div[@class='repeater  party-form-address-repeater']/button");

    private final By addressTxt = By.cssSelector(".form-input-text.party-form-address-street");
    private final String addressTypeXpath = "//*[@class='form-row form-row-multiple-fields contact-mechanisms__address-street-row']//*[@class='select-box single-select chunky']";
    private final By collapseAddress = By.cssSelector(".collapsable-fieldset__legend");
    private final By cityTxt = By.cssSelector(".form-input-text.party-form-address-city");
    private final By stateTxt = By.cssSelector(".form-input-text.party-form-address-state");
    private final By zipCodeTxt = By.cssSelector(".form-input-text.party-form-address-zip");
    private final By countryTxt = By.cssSelector(".select-box.single-search-select.country-select.chunky.party-form-address-country");

    public PersonPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(tagsSelect));
    }

    public void setTitle(String title){
        Log.info("Setting title: "+title);
        Log.info("Clicking on title dropdown");
        driver.findElement(titleSelect).click();
        Log.info("Waiting for option to be present");
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@class='select-box single-select chunky party-form-title']//*[@class='select-box__options']/div")));
        Log.info("Selecting option "+title);
        driver.findElement(By.xpath("//*[@class='select-box single-select chunky party-form-title']//*[@class='select-box__options']/div[text()='"+title+"']")).click();
    }

    public void setFirstName(String firstName){
        Log.info("Entering First name "+firstName);
        driver.findElement(firstNameTxt).sendKeys(firstName);
    }

    public void setLastName(String lastName){
        Log.info("Entering Last name "+lastName);
        driver.findElement(lastNameTxt).sendKeys(lastName);
    }

    public  void setJobTitle(String jobTitle){
        Log.info("Entering job title "+jobTitle);
        driver.findElement(jobTitleTxt).sendKeys(jobTitle);
    }

    public void setOrganizationSelect(String orgName ) {
        Log.info("Entering organization name "+orgName);
        driver.findElement(organizationSelect).sendKeys(orgName);
        Log.info("Waiting for organization name to be displayed ");
        WebElement org = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='search-select__option-text']/span[text()='"+orgName+"']")));
        Log.info("Clicking on organization name "+orgName);
        org.click();
    }

    public void setPhoneNumber(int rowIdx, String phoneNumber){
        List<WebElement> phoneNumbers = driver.findElements(phoneNumbersTxt);
        Log.info("Entering phone numbers "+ phoneNumber +"in row"+ rowIdx);
        phoneNumbers.get(rowIdx - 1).sendKeys(phoneNumber);
        phoneNumbers.get(rowIdx - 1).sendKeys(Keys.TAB);
    }
    public void setPhoneNumberType(int rowIdx, String typeName){
        Log.info("Selecting phone number type "+ typeName +"in row"+ rowIdx);
        String typeSelector = getType(rowIdx, phoneNumberXpath);
        clickListOption(typeSelector, typeName);
    }

    public void setEmail(int rowIdx, String email){
        List<WebElement> emails = driver.findElements(emailTxt);
        emails.get(rowIdx - 1 ).sendKeys(email);
        emails.get(rowIdx - 1 ).sendKeys(Keys.TAB);
    }

    public void setEmailType(int rowIdx, String typeName){
        String typeSelector = getType(rowIdx, emailXpath);
        clickListOption(typeSelector, typeName);
    }

    public void setWebsite(int rowIdx, String website){
        List<WebElement> websites = driver.findElements(webSitesTxt);
        websites.get(rowIdx - 1).sendKeys(website);
        websites.get(rowIdx - 1).sendKeys(Keys.TAB);
    }

    public void setWebSitesType(int rowIdx, String typeName){
        String webtype = typeName.contains("Home") || typeName.contains("Work") ? "regular":"social";
        String typeSelector = getType(rowIdx, webSiteXpath, webtype);
        clickListOption(typeSelector,typeName);
    }

    public void clickListOption(String typeSelector, String typeName){
        By selectBox = By.xpath(typeSelector);
        wait.until(ExpectedConditions.elementToBeClickable(selectBox));
        driver.findElement(selectBox).click();
        String optionsSelector = typeSelector + "/div[@class='select-box__options']";
        By options = By.xpath(optionsSelector);
        wait.until(ExpectedConditions.visibilityOfElementLocated(options));
        driver.findElement(By.xpath(optionsSelector + "/div[text()='"+typeName+"']")).click();
    }

    public WebElement getAddPhoneNumberBtn() {
        return driver.findElement(addPhoneNumberBtn);
    }

    public WebElement getAddEmailBtn() {
        return driver.findElement(addEmailBtn);
    }

    public WebElement getAddWebSiteBtn() {
        return driver.findElement(addWebSiteBtn);
    }

    public WebElement getAddAddressBtn(){
        return driver.findElement(addAddressBtn);
    }

    public void setAddressTxt(int addNo, String address){
        collapseAll(false);
        List<WebElement> addresses = driver.findElements(addressTxt);
        addresses.get(addNo - 1).sendKeys(address);
    }

    public void setCityTxt(int addNo, String city)
    {
        collapseAll(false);
        List<WebElement> cities = driver.findElements(cityTxt);
        cities.get(addNo - 1).sendKeys(city);
    }

    public void setZipCodeTxt(int addNo, String zip)
    {
        collapseAll(false);
        List<WebElement> zipcodes = driver.findElements(zipCodeTxt);
        zipcodes.get(addNo - 1).sendKeys(zip);
    }

    public void setCountryTxt(int addNo, String country)
    {
        collapseAll(false);
        List<WebElement> countries = driver.findElements(countryTxt);
        countries.get(addNo - 1).click();
        countries.get(addNo - 1).findElement(By.tagName("input")).click();
        countries.get(addNo - 1).findElement(By.tagName("input")).sendKeys(country);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        WebElement option = countries.get(addNo - 1 ).findElement(By.xpath("//*[@class='select-box__options']//*[text()='"+country+"']"));
        jse.executeScript("arguments[0].click()", option);
    }

    public  void setStateTxt(int addNo, String state)
    {
        collapseAll(false);
        List<WebElement> states = driver.findElements(stateTxt);
        states.get(addNo - 1).sendKeys(state);
    }

    public  void setAddressType(int addNo, String type)
    {
        collapseAll(false);
        List<WebElement> types = driver.findElements(By.xpath(addressTypeXpath));
        wait.until(ExpectedConditions.elementToBeClickable( types.get(addNo - 1)));
        types.get(addNo - 1).click();
        WebElement option = driver.findElement(By.xpath("("+ addressTypeXpath + ")["+addNo+"]//*[text()='"+type+"']"));
        wait.until(ExpectedConditions.elementToBeClickable(option));
        option.click();
    }

    public String getType(int rowIdx, String selector)
    {
        String list = null;
        if(selector.contains("email-address") || selector.contains("phone-number" )){
            // finding row with referenceValue
            list = "(" + selector + "/ancestor::div[@class='form-row form-row-flush-fields']//div[@role='listbox'])[" + rowIdx + "]";
        }
        return list;
    }

    public String getType(int rowIdx, String selector, String webtype)
    {
        String list = null;
        int webIdx = webtype.contains("social") ? 1 : 2;
        if(selector.contains("website")){
            list = "((" + selector + "/ancestor::div[@class='form-row form-row-flush-fields'])[" + rowIdx + "]//div[@role='listbox'])[" + webIdx + "]";
        }
        return list;
    }

    public void collapseAll(boolean b) {
        List<WebElement> addresses = driver.findElements(collapseAddress);
        if(!b){
            for( int i = 0 ; i < addresses.size(); i++) {
                if (addresses.get(i).getAttribute("tabindex").contains("0")){ //collapsed
                    addresses.get(i).click();
                    wait.until(ExpectedConditions.attributeContains(addresses.get(i), "tabindex", "-1"));
                }
            }
        }
    }
}
