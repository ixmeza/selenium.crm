package com.selenium.demo.pages;

import com.selenium.demo.base.Base;
import com.selenium.demo.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewCasePage extends Base {

    private final By relatedToTxt = By.cssSelector("input[placeholder='Find a person or organization']");
    private final By searchForOwner = By.cssSelector("input[placeholder = 'Search for owner']");
    private final By caseNameTxt = By.cssSelector("div:nth-child(3) input");
    private final By descriptionTxt = By.cssSelector("div:nth-child(4) textarea");
    private final By tagsTxt = By.cssSelector("div:nth-child(5) input");
    private final By trackSelect = By.cssSelector(".form-field > div[role='listbox'] > div[role='button']");
    private final By ownerSelect = By.cssSelector(".assignment-controls > div[role='listbox'] > div[role='button']");
    private final By addTrackLink = By.cssSelector("span a[href*='cases']");
    private final By assignToMeBtn = By.xpath("//button[@class='menu-select__option'][text()[contains(.,'Assign to me')]]");
    private final By assignToOwner = By.xpath("//button[@class='menu-select__option'][text()[contains(.,'Assign to an owner')]]");
    private final By assignOwnerHeader = By.cssSelector(".modal-dialog__header");
    private final By changeRelatesToBtn = By.cssSelector(".bar__text-button");
    private final By tagList = By.cssSelector(".item-select__item.chip");
    private final By closedOnTxt = By.cssSelector(".date-picker__input");

    public NewCasePage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(caseNameTxt));
    }

    public void setRelatesToTxt(String relatesTo)
    {
        Log.info("Setting relates to: "+ relatesTo);
        String mode = driver.findElements(changeRelatesToBtn).size() > 0 ? "edit" : "new";
        if(mode.contains("edit")){
                driver.findElement(changeRelatesToBtn).click();
        }
        wait.until(ExpectedConditions.visibilityOfElementLocated(relatedToTxt));
        driver.findElement(relatedToTxt).sendKeys(relatesTo);
        By selectOption = By.xpath("//div[@class='select-box__options']//span[@class='search-select__option-main-text'][text()[contains(.,'"+relatesTo+"')]]");
        wait.until(ExpectedConditions.elementToBeClickable(selectOption));
        driver.findElement(selectOption).click();
    }

    public void setDescriptionTxt(String description) {
        Log.info("Setting description: "+ description);

        driver.findElement(descriptionTxt).clear();
        driver.findElement(descriptionTxt).sendKeys(description);
    }

    public void setCaseNameTxt(String name){
        Log.info("Setting case name: "+ name);
        driver.findElement(caseNameTxt).clear();
        driver.findElement(caseNameTxt).sendKeys(name);
    }

    public void setTagsTxt(String [] tags)
    {
        Log.info("Setting tags");
        String mode = driver.findElements(tagList).size() > 0 ? "edit" : "new";
        if(mode.contains("edit")){
            Log.info("Deleting all existing tags");
            // delete all tasks first
            for( WebElement tag : driver.findElements(By.cssSelector(".item-select__item.chip button"))){
                tag.click();
            }
        }
        // add new tags
        Log.info("Adding tags");
        for(int i = 0 ; i < tags.length; i++)
        {
            if(mode.contains("edit")) {
                driver.findElement(By.cssSelector("div:nth-child(6) input")).sendKeys(tags[i]);
                driver.findElement(By.cssSelector("div:nth-child(6) input")).sendKeys(Keys.ENTER);
            }
            else {
                driver.findElement(tagsTxt).sendKeys(tags[i]);
                driver.findElement(tagsTxt).sendKeys(Keys.ENTER);
            }
        }
    }

    public void setOwner(String owner)
    {
        Log.info("Clicking on owner listbox");
        wait.until(ExpectedConditions.visibilityOfElementLocated(ownerSelect)).click();
        if(owner.contains("me")){
            Log.info("Assigning to me");
            driver.findElement(assignToMeBtn).click();
        }
        else{
            Log.info("Assigning to "+ owner);
            driver.findElement(assignToOwner).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(assignOwnerHeader));
            driver.findElement(searchForOwner).sendKeys(owner);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='search-select__option-main-text'][text()[contains(.,'"+owner+"')]]")));
            driver.findElement(By.xpath("//span[@class='search-select__option-main-text'][text()[contains(.,'"+owner+"')]]")).click();
        }
    }

    public CaseDetailPage addCase(String relatedTo, String caseName, String caseDescription, String [] tags, String track, String owner)
    {
        setRelatesToTxt(relatedTo);
        setCaseNameTxt(caseName);
        setDescriptionTxt(caseDescription);
        setTagsTxt(tags);
        setOwner(owner);
        super.getSaveBtn().click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(changeRelatesToBtn));
        return new CaseDetailPage();
    }

    public CaseDetailPage editCase(String relatedTo, String caseName, String caseDescription, String closedOn,  String [] tags, String track, String owner){
        Log.info("Editing case values");
        setRelatesToTxt(relatedTo);
        setCaseNameTxt(caseName);
        setDescriptionTxt(caseDescription);
        setClosedOn(closedOn);
        setTagsTxt(tags);
        setOwner(owner);
        Log.info("Clicking save button");
        super.getSaveBtn().click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(changeRelatesToBtn));
        return new CaseDetailPage();
    }

    private void setClosedOn(String closedOn) {
        Log.info("Setting closed on date "+ closedOn);
        driver.findElement(closedOnTxt).clear();
        driver.findElement(closedOnTxt).sendKeys(closedOn);
        driver.findElement(closedOnTxt).sendKeys(Keys.TAB);
    }
}
