package com.selenium.demo.pages;
import com.selenium.demo.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class SalesPage extends Base {

    private final By addOportunityBtn = By.cssSelector("[href*='add']");
    private final By pipelineIcon = By.cssSelector("[aria-label='Kanban view']");
    private final By opportunitiesIcon = By.cssSelector("[aria-label='List view']");
    private final By dashboardIcon = By.cssSelector("[aria-label='Dashboard']");
    private final By topMsg = By.cssSelector(".message.message--success.message--revealed.flash-message .message__body");

    public SalesPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addOportunityBtn));
    }

    public NewOpportunityPage addOpportunity()
    {
        log.info("Clicking on add opportunity button");
        driver.findElement(addOportunityBtn).click();
        return new NewOpportunityPage();
    }

    public PipelinePage getPipeline()
    {
        log.info("Clicking on Pipeline icon");
        driver.findElement(pipelineIcon).click();
        return new PipelinePage(driver);
    }

    public OpportunitiesPage getOpportunities()
    {
        log.info("Clicking on Opportunities icon");
        driver.findElement(opportunitiesIcon).click();
        return new OpportunitiesPage();
    }

    public void getDashboard()
    {
        log.info("Clicking on Dashboard icon");
        driver.findElement(dashboardIcon).click();
    }

    public String getTopMsg()
    {
        log.info("Getting Top message text");
        return driver.findElement(topMsg).getText();
    }

}
