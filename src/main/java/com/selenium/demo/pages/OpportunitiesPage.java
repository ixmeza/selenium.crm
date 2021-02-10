package com.selenium.demo.pages;

import com.selenium.demo.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class OpportunitiesPage extends Base {

    private final By opportunitiesSelector = By.cssSelector(".list-results-cell-summary a");
    private final By opportunitiesTable = By.cssSelector(".simple-table.simple-table--with-hover-effect.list-results__table");

    public OpportunitiesPage() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(opportunitiesTable));
    }

    public SalesPage deleteOpportunity(String summary)
    {
        getOpportunity(summary).click();
        return editHelper().deleteOpportunity();
    }

    public NewOpportunityPage editOpportunity(String summary) {
        getOpportunity(summary).click();
        OpportunityPage op = editHelper();
        return op.edit();
    }

    public OpportunityPage editHelper()
    {
        return new OpportunityPage();
    }

    public WebElement getOpportunity(String summary)
    {
        List<WebElement> opportunities = driver.findElements(opportunitiesSelector);
        for(WebElement op : opportunities){
            if(op.getText().contains(summary))
                return op;
        }
        return null;
    }
}
