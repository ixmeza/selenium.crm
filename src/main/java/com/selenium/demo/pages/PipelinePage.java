package com.selenium.demo.pages;
import com.selenium.demo.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class PipelinePage extends Base {

    Actions action;
    Actions builder;

    private final By header = By.cssSelector(".page__header-title");
    private final By addOpportunity = By.cssSelector("[href*='add']");
    private final By pipeLineValueLbl = By.cssSelector(".tooltipper.simple-tooltipper.kanban-header__pipeline-value");
    private final By totalValueLbl = By.cssSelector("tooltipper.simple-tooltipper.kanban-header__total-value");
    private final By closedLinkCounter = By.cssSelector("[href*='closed']");
    private final By opportunities = By.cssSelector("[id*='opportunity']");
    private final By newColumn = By.cssSelector(".kanban-column:nth-child(1)");
    private final By qualifiedColumn = By.cssSelector(".kanban-column:nth-child(2)");
    private final By meetingColumn = By.cssSelector(".kanban-column:nth-child(3)");
    private final By proposalColumn = By.cssSelector(".kanban-column:nth-child(4)");
    private final By negotiationColumn = By.cssSelector(".kanban-column:nth-child(5)");
    private final By contractColumn = By.cssSelector(".kanban-column:nth-child(6)");
    private final By columnTitle = By.xpath("//*[@class='kanban-column']//*[@class='kanban-column-title']");

    public PipelinePage(WebDriver driver) {
        log.info("Waiting for header" );
        wait.until(ExpectedConditions.visibilityOfElementLocated(header));
        action = new Actions(driver);
        builder = new Actions(driver);
    }

   public void moveOpportunity(String opId, String toColumn)
   {
       log.info("Getting destination column" );
       WebElement to = getColumnElement(toColumn);
       String columnNumber = String.valueOf(getColumnNumber(toColumn));
       log.info("Getting source column" );
       WebElement from = driver.findElement(By.id(opId));
       log.info("Drag and drop the card" );
       builder.dragAndDrop(from,to).build().perform();
       wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@class='kanban-column'])["+columnNumber+"]//*[@class='kanban-card '][@id='"+opId+"']")));
   }

   public WebElement getColumnElement(String colName)
   {
       WebElement element = null;
       if(colName.contains("New"))
       {
           element = driver.findElement(newColumn);
       }
       if(colName.contains("Qualified")){
           element = driver.findElement(qualifiedColumn);
       }

       if(colName.contains("Meeting")){
           element = driver.findElement(meetingColumn);
       }

       if(colName.contains("Proposal")) {
           element = driver.findElement(proposalColumn);
       }

       if(colName.contains("Negotiation")) {
           element = driver.findElement(negotiationColumn);
       }

       if(colName.contains("Contract")) {
           element = driver.findElement(contractColumn);
       }
       return element;
   }

   public Integer getColumnNumber(String colName)
   {
       List<WebElement> titles = driver.findElements(columnTitle);
       for(int i = 0 ; i < titles.size(); i++)
       {
           if(titles.get(i).getText().contains(colName))
               return i + 1;
       }
       return 0;
   }

    public Boolean isOpportunityPresent(Integer row, String relatedTo, String name)
    {
        String id = getId(relatedTo, name);
        By selector = By.cssSelector(".kanban-column:nth-child("+row+") div[id*='opportunity']");
        List<WebElement> ops = driver.findElements(selector);
        for( WebElement op : ops){
            if(op.getAttribute("id").contains(id))
                return true;
        }
        return false;
    }

    public String getId(String relatedTo, String name)
    {
        log.info("Getting id" );
        WebElement op = driver.findElement(By.xpath("//a[text()[contains(.,'"+name+"')]]/ancestor::div[@class='kanban-card ']//a[@class='ember-view kanban-card__title-text'][text()[contains(.,'"+relatedTo+"')]]/ancestor::div[@class='kanban-card ']"));
        return op.getAttribute("id");
    }

}
