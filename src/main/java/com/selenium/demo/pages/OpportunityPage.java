package com.selenium.demo.pages;
import com.selenium.demo.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.ArrayList;
import java.util.List;

public class OpportunityPage extends Base {

    private final By editBtn = By.cssSelector("[href*='edit']");
    private final By editSecondaryBtn = By.cssSelector(".select-box__selected-option.multi-button__secondary");
    private final By opTitle = By.cssSelector(".entity-details__title");
    private final By relatedTo = By.cssSelector(".tooltipper.party-tooltipper a");
    private final By description = By.cssSelector(".ugc.entity-details__description");
    private final By tags = By.cssSelector(".item-select__item.chip");
    private final By addContactsBtn = By.cssSelector(".panel.contacts-panel button.contacts-panel__add");
    private final By contactsInPanel = By.cssSelector("ul.contacts-panel__items li.contacts-panel__item");
    private final By expectedAmount = By.cssSelector(".opportunity-summary__expected-value-amount");
    private final By expectedCloseDate = By.cssSelector(".opportunity-summary__close-date");
    private final By currentMilestone = By.cssSelector(".opportunity-summary__current-milestone");
    private final By opDuration = By.cssSelector(".opportunity-summary__duration");
    private final By milestoneMenuBtn = By.cssSelector(".milestone-menu [role]");
    private final By addTaskBtn = By.cssSelector("[data-pendo='add-task-button']");
    private final By deleteModalBtn = By.cssSelector(".async-button.button.button--danger");

    public OpportunityPage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(opTitle));
    }

    public String getOpTitle() {
        return driver.findElement(opTitle).getText();
    }

    public String getDescription(){
        return driver.findElement(description).getText();
    }

    public String getRelatedTo()
    {
        return driver.findElement(relatedTo).getText();
    }

    public List<String> getContacts()
    {
        List<String> allContacts = new ArrayList<String>();
        List<WebElement> contacts = driver.findElements(contactsInPanel);
        contacts.forEach(webElement -> allContacts.add(webElement.getText()));
        return allContacts;
    }
    public NewOpportunityPage edit(){
        driver.findElement(editBtn).click();
        return new NewOpportunityPage();
    }

    public SalesPage deleteOpportunity()
    {
        driver.findElement(editSecondaryBtn).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@class='menu-select__option'][text()[contains(.,'Delete')]]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteModalBtn)).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='menu-select__option'][text()[contains(.,'Delete')]]")));
        return new SalesPage();
    }
    public String getCurrentMilestone(){
        return driver.findElement(currentMilestone).getText();
    }

    public String getExpectedCloseDate(){
        return driver.findElement(expectedCloseDate).getText();
    }

    public String getExpectedValue(){
        return driver.findElement(expectedAmount).getText();
    }

    public String getDuration(){
        return driver.findElement(opDuration).getText();
    }

    public TaskPage addTask(String description, String addDetail, String dueDate, String time, String repeatType, Integer repeatNo, String frequency, String category, String assignedTo) {
        driver.findElement(addTaskBtn).click();
        return new TaskPage(driver, description, addDetail, dueDate, time, repeatType, repeatNo, frequency, category, assignedTo);
    }
}
