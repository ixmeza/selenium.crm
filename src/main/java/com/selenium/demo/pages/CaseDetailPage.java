package com.selenium.demo.pages;
import com.selenium.demo.base.Base;
import com.selenium.demo.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CaseDetailPage extends Base {

    private final By editBtn = By.cssSelector("[href*='edit']");
    private final By caseTitle = By.cssSelector(".entity-details__title");
    private final By partyTxt = By.cssSelector(".tooltipper.party-tooltipper");
    private final By tagsList = By.cssSelector(".item-select__item.chip");
    private final By caseStatus = By.cssSelector(".kase-summary__status-blob.kase-summary__status-blob");
    private final By reOpenCloseBtn = By.cssSelector(".button.button--secondary.button--small.float-right");
    private final By deleteCaseMenuItem = By.cssSelector("[class*='menu-select__option']:nth-child(7)");
    private final By warningRedBtn = By.cssSelector(".async-button.button.button--danger");
    private final By editSecondaryBtn = By.cssSelector(".select-box__selected-option.multi-button__secondary");

    public CaseDetailPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(caseTitle));
    }

    public void editValues(String relatedTo,String caseName,String caseDescription, String closedOn, String [] tags, String track, String owner ){
        Log.info("Clicking edit button");
        driver.findElement(editBtn).click();
        new NewCasePage().editCase(relatedTo, caseName, caseDescription, closedOn, tags, track, owner );
    }


    public CasesPage delete() {
        Log.info("Deleting case");
        driver.findElement(editSecondaryBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteCaseMenuItem)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(warningRedBtn)).click();
        return new CasesPage();
    }

    public CaseDetailPage close() {
        Log.info("Reopening case");
        driver.findElement(reOpenCloseBtn).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(warningRedBtn)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(warningRedBtn));
        return new CaseDetailPage();
    }

    public String getCaseStatus(){
        Log.info("Retrieving case status");
        return driver.findElement(caseStatus).getText();
    }

    public CaseDetailPage reOpen() {
        Log.info("Re-opening case");
        driver.findElement(reOpenCloseBtn).click();
        return new CaseDetailPage();
    }


}
