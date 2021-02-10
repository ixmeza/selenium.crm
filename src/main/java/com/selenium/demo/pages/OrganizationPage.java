package com.selenium.demo.pages;
import com.selenium.demo.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class OrganizationPage extends PersonPage{

    private final By orgName = By.cssSelector(".form-input-text.party-form-name");

    public OrganizationPage() {
        Log.info("Waiting for organization name textfield to be displayed");
        wait.until(ExpectedConditions.visibilityOfElementLocated(orgName));
    }

    public void setOrgName(String name) {
        Log.info("Setting organization name");
        driver.findElement(orgName).sendKeys(name);
    }

    public ContactDetail saveOrg() {
        getSaveBtn().click();
        return new ContactDetail();
    }
}
