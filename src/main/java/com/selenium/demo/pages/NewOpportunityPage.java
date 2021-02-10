package com.selenium.demo.pages;
import com.selenium.demo.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewOpportunityPage extends Base {

    private final By relatedToTxt = By.cssSelector("input[placeholder='Find a person or organization']");
    private final By opportunityNameTxt = By.cssSelector("div:nth-child(3) input");
    private final By descriptionTxt = By.cssSelector("div:nth-child(4) textarea");
    private final By tagsTxt = By.cssSelector("div:nth-child(5) input");
    private final By currencySelect = By.cssSelector(".select-box.single-search-select.currency-select.chunky");
    private final By expectedValueTxt = By.cssSelector("div:nth-of-type(2) > .form-input-text");
    private final By paymentTermsSelect = By.cssSelector("form div.form-row:nth-child(7) div.form-field:nth-child(3) .select-box");
    private final By milestoneSelect = By.cssSelector(".select-box.single-select.milestone-select.single-select--has-extra-options.chunky");
    private final By milestoneWon = By.cssSelector(".milestone-select__won.button.button--success");
    private final By milestoneLost = By.cssSelector(".milestone-select__lost.button.button--danger");
    private final By expectedClosePicker = By.className("date-picker__input");
    private final By trackSelect = By.cssSelector(".opportunity-form__track-row [role='button']");
    private final By ownerSelect = By.cssSelector(".assignment-controls > div[role='listbox'] > div[role='button']");
    private final By assignToMeBtn = By.xpath("//button[@class='menu-select__option'][text()[contains(.,'Assign to me')]]");
    private final By assignToOwner = By.xpath("//button[@class='menu-select__option'][text()[contains(.,'Assign to an owner')]]");
    private final By assignOwnerHeader = By.cssSelector(".modal-dialog__header");
    private final By searchForOwner = By.cssSelector("input[placeholder = 'Search for owner']");
    private final By paymentDurationTxt = By.cssSelector(".form-input-affix > .form-input-text");
    private final By changeRelatesToBtn = By.cssSelector(".bar__text-button");


    public NewOpportunityPage() {
        log.info("Waiting for opportunity text name box");
        wait.until(ExpectedConditions.visibilityOfElementLocated(opportunityNameTxt));
    }

    public void setOpportunityNameTxt(String name)
    {
        log.info("Setting opportunity name ");
        driver.findElement(opportunityNameTxt).clear();
        driver.findElement(opportunityNameTxt).sendKeys(name);
    }


    public void setRelatesToTxt(String relatesTo)
    {
        log.info("Setting relates to");
        String mode = driver.findElements(changeRelatesToBtn).size() > 0 ? "edit" : "new";
        if(!mode.contains("edit")){
            wait.until(ExpectedConditions.visibilityOfElementLocated(relatedToTxt));
            driver.findElement(relatedToTxt).sendKeys(relatesTo);
            By selectOption = By.xpath("//div[@class='select-box__options']//span[@class='search-select__option-main-text'][text()[contains(.,'"+relatesTo+"')]]");
            wait.until(ExpectedConditions.elementToBeClickable(selectOption));
            driver.findElement(selectOption).click();
        }
    }

    public void setDescriptionTxt(String description) {
        log.info("Setting Description ");
        driver.findElement(descriptionTxt).clear();
        driver.findElement(descriptionTxt).sendKeys(description);
    }

    public void setTagsTxt(String [] tags)
    {
        log.info("Setting tags");
        for(int i = 0 ; i < tags.length; i++)
        {
            driver.findElement(tagsTxt).sendKeys(tags[i]);
            driver.findElement(tagsTxt).sendKeys(Keys.ENTER);
        }
    }

    public void setCurrency(String currency)
    {
        log.info("Setting currency ");
        driver.findElement(currencySelect).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(currencySelect).findElement(By.cssSelector("input")))).sendKeys(currency);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()[contains(.,'"+currency+"')]]"))).click();
    }

    public void setExpectedValueTxt(String expectedValue)
    {
        log.info("Setting expected value ");
        driver.findElement(expectedValueTxt).clear();
        driver.findElement(expectedValueTxt).sendKeys(expectedValue);
    }

    public void setPaymentTerms(String terms, Integer duration){
        log.info("Setting payment terms ");
        driver.findElement(paymentTermsSelect).click();
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[text()[contains(.,'"+terms+"')]]")))).click();
        if(!terms.contains("Fixed") && duration != null){
                    wait.until(ExpectedConditions.visibilityOfElementLocated(paymentDurationTxt));
                    setPaymentDuration(duration);
        }
    }
    public void setPaymentDuration(Integer duration)
    {
        log.info("Setting opportunity name: ");
        driver.findElement(paymentDurationTxt).clear();
        driver.findElement(paymentDurationTxt).sendKeys(String.valueOf(duration));
    }

    public void setMilestone(String milestone)
    {
        log.info("Setting milestone: ");
        driver.findElement(milestoneSelect).click();
        if(!milestone.contains("Won") && !milestone.contains("Lost"))
        {
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[@class='single-select__main']/div[text()[contains(.,'"+milestone+"')]]")))).click();
        }
        else
        {
            if(milestone.contains("Won")) {
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(milestoneWon))).click();
            }
            else{
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(milestoneLost))).click();
            }
        }
    }

    public void setExpectedCloseDate(String date)
    {
        log.info("Setting expected close date" );
        driver.findElement(expectedClosePicker).click();
        driver.findElement(expectedClosePicker).clear();
        driver.findElement(expectedClosePicker).sendKeys(date);
    }

    public void setOwner(String owner)
    {
        log.info("Setting expected close date" );
        driver.findElement(ownerSelect).click();
        if(owner.contains("me")){
            driver.findElement(assignToMeBtn).click();
        }
        else{
            driver.findElement(assignToOwner).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(searchForOwner));
            driver.findElement(searchForOwner).sendKeys(owner);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='search-select__option-main-text'][text()[contains(.,'"+owner+"')]]")));
            driver.findElement(By.xpath("//span[@class='search-select__option-main-text'][text()[contains(.,'"+owner+"')]]")).click();
        }
    }

    public OpportunityPage addOpportunity(String relatedTo, String opportunityName, String opDescription, String [] tags, String currency, String expectedValue, String paymentTerms, String milestone, String closeDate,  String track, String owner)
    {
        setOpportunityNameTxt(opportunityName);
        setDescriptionTxt(opDescription);
        setTagsTxt(tags);
        setRelatesToTxt(relatedTo);
        setCurrency(currency);
        setExpectedValueTxt(expectedValue);
        setPaymentTerms(paymentTerms,null);
        setMilestone(milestone);
        setExpectedCloseDate(closeDate);
        setOwner(owner);

        log.info("Clicking save button" );
        getSaveBtn().click();
        return new OpportunityPage();
    }

    public OpportunityPage addOpportunity(String relatedTo, String opportunityName, String opDescription, String [] tags, String currency, String expectedValue, String paymentTerms, Integer duration, String milestone, String closeDate,  String track, String owner)
    {
        setRelatesToTxt(relatedTo);
        setOpportunityNameTxt(opportunityName);
        setDescriptionTxt(opDescription);
        setTagsTxt(tags);
        setCurrency(currency);
        setExpectedValueTxt(expectedValue);
        setPaymentTerms(paymentTerms, duration);
        setMilestone(milestone);
        setExpectedCloseDate(closeDate);
        setOwner(owner);
        log.info("Clicking save button" );
        getSaveBtn().click();
        return new OpportunityPage();
    }


    public OpportunityPage save() {
        log.info("Clicking save button" );
        getSaveBtn().click();
        return new OpportunityPage();
    }
}
