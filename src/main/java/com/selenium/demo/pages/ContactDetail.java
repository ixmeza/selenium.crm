package com.selenium.demo.pages;
import com.selenium.demo.base.Base;
import com.selenium.demo.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ContactDetail extends Base{

    private final By titleTxt = By.cssSelector(".party-details__title");
    private final By numbers = By.cssSelector(".simple-list__item .phone-number.ember-view");
    private final By emails = By.cssSelector(".simple-list__item .email-address");
    private final By urls =  By.cssSelector(".simple-list__item .website-address");
    private final By postalAddresses = By.cssSelector(".simple-list__item .postal-address");
    private final By editBtn = By.cssSelector(".ember-view.multi-button__primary");
    private final By editSecondaryBtn = By.cssSelector(".select-box__selected-option.multi-button__secondary");
    private final By deleteCaseMenuItem = By.xpath("//button[@class='menu-select__option'][text()[contains(.,'Delete')]]");
    private final By warningRedBtn = By.cssSelector(".async-button.button.button--danger");

    public ContactDetail() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(titleTxt));
    }

    public String getTitle(){
        return driver.findElement(titleTxt).getText();
    }

    public String getWorkNumber(){
        return driver.findElement(By.xpath("//*[@class='phone-number__type'][text()[contains(.,'Work')]]/parent::div//span[@class='phone-number__digits']")).getText();
    }

    public String getFaxNumber()
    {
        return driver.findElement(By.xpath("//*[@class='phone-number__type'][text()[contains(.,'Fax')]]/parent::div//span[@class='phone-number__digits']")).getText();
    }
    public String getDirectNumber(){
        return driver.findElement(By.xpath("//*[@class='phone-number__type'][text()[contains(.,'Direct')]]/parent::div//span[@class='phone-number__digits']")).getText();
    }

    public String getWorkEmail(){
        return driver.findElement(By.xpath("//*[@class='email-address']//*[text()[contains(.,'Work')]]/parent::div//a")).getText();
    }

    public String getHomeEmail(){
        return driver.findElement(By.xpath("//*[@class='email-address']//*[text()[contains(.,'Home')]]/parent::div//a")).getText();
    }

    public String getWorkUrl(){
        return driver.findElement(By.xpath("//*[@class='website-address']//*[text()[contains(.,'Work')]]/parent::div//a")).getText();
    }

    public String getBillingAddress(){
        return driver.findElement(By.xpath("//*[@class='postal-address']//*[text()[contains(.,'Billing')]]/parent::div")).getText();
    }

    public String getOfficeAddress(){
        return driver.findElement(By.xpath("//*[@class='postal-address']//*[text()[contains(.,'Office')]]/parent::div")).getText();
    }

    public OrganizationPage edit(){
        Log.info("Clicking edit button");
        driver.findElement(editBtn).click();
        return new OrganizationPage();
    }


    public PeoplePage delete(){
        Log.info("Deleting case");
        driver.findElement(editSecondaryBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteCaseMenuItem)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(warningRedBtn)).click();
        return new PeoplePage();
    }

}
