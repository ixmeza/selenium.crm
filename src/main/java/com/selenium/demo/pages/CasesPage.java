package com.selenium.demo.pages;
import com.selenium.demo.base.Base;
import com.selenium.demo.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class CasesPage extends Base {

    private final By addCase = By.cssSelector(".ember-view.button.button--secondary.button--small");
    private final By topMsg = By.cssSelector(".message__body");


    public CasesPage() {
        Log.info("Waiting for Add case button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(addCase));
    }

    public NewCasePage getAddCase(){
        Log.info("Waiting for Add case button");
        wait.until(ExpectedConditions.visibilityOfElementLocated(addCase));
        Log.info("Clicking on add case");
        driver.findElement(addCase).click();
        return new NewCasePage();
    }

    public Boolean isCasePresent(String caseName)
    {
        Log.info("Verifying if case: "+ caseName +" is present in list");
        Boolean isPresent = false;
        List<WebElement> cases = driver.findElements(By.cssSelector("td a"));
        for (WebElement aCase : cases) {
            if(aCase.getText().contains(caseName)){
                Log.info("Case:"+ caseName+" found");
                isPresent = true;
                break;
            }
        }
        return isPresent;
    }

    public CaseDetailPage accessCase(String caseName) {
        Log.info("Retrieving cases found");
        WebElement targetCase = null;
        List<WebElement> cases = driver.findElements(By.cssSelector("td a"));
        for (WebElement aCase : cases) {
            if (aCase.getText().contains(caseName)) {
                Log.info("Accessing: "+ caseName );
                targetCase = aCase;
                break;
            }
        }
        targetCase.click();
        return new CaseDetailPage();
    }

    public String getTopMsg(){
        Log.info("Getting top message");
        return driver.findElement(topMsg).getText();
    }
}
