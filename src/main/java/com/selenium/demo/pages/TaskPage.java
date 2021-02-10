package com.selenium.demo.pages;
import com.selenium.demo.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class TaskPage extends Base {
    // locators

    private final By dialogModalHeader = By.cssSelector(".chunky.form > .modal-dialog__header");
    private final By descriptionTxt = By.cssSelector(".ember-text-field.ember-view.form-input-text.task-modal__description");
    private final By addDetailLink = By.cssSelector(".form-label-link.hyperlink-button.task-modal__add-detail");
    private final By additionalDetailsTxt = By.cssSelector(".ember-text-area.ember-view.form-input-text.task-modal__details-text");
    private final By dueDateTxt = By.cssSelector(".date-picker__input");
    private final By timeHourSelect = By.cssSelector(".chunky.native-single-select.time-select__hour > .select-box");
    private final By timeMinutesSelect = By.cssSelector(".chunky.native-single-select.time-select__minutes > .select-box");
    private final By timeMeridiemSelect = By.cssSelector(".chunky.native-single-select.time-select__meridiem > .select-box");
    private final By repeatLink = By.cssSelector(".form-label-link.hyperlink-button.task-modal__show-repeat");
    private final By repeatTypeSelect = By.cssSelector(".chunky.native-single-select.repeat-select__type > .select-box");
    private final By repeatIntervalTxt = By.cssSelector(".form-input-text.repeat-select__interval");
    private final By repeatFrequencySelect = By.cssSelector(".chunky.native-single-select.repeat-select__frequency > .select-box");
    private final By addCategoryLink = By.cssSelector(".form-label-link.hyperlink-button.task-modal__show-add-category");
    private final By categorySelect = By.cssSelector("div.select-box.single-select.category-select.chunky");
    private final By linktoModel = By.cssSelector(".hyperlink-button.task-modal__show-link-to-model");
    private final By taskOwnerSelect = By.cssSelector(".chunky.native-single-select.task-owner > .select-box");
    private final By completeTaskBtn = By.cssSelector(".async-button.button.button--secondary");
    private final By deleteIcon = By.cssSelector("[class*='delete-button']");

    public TaskPage(WebDriver driver){
        wait.until(ExpectedConditions.visibilityOfElementLocated(dialogModalHeader));
    }

    public TaskPage(WebDriver driver, String description, String addDetail, String dueDate, String time, String repeatType, Integer repeatNo, String frequency, String category, String assignedTo) {
        setTask(description, addDetail, dueDate, time, repeatType, repeatNo, frequency, category, assignedTo);
    }
    public void setTask(String description, String addDetail, String dueDate, String time, String repeatType, Integer repeatNo, String frequency, String category, String assignedTo)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(dialogModalHeader));
        setDescription(description);

        if(addDetail != null){
            setDetail(addDetail);
        }
        setDueDate(dueDate);
        setTime(time);
        setCategory(category);
        if(repeatNo != null)
        {
            setRepetition(repeatType, repeatNo,frequency);
        }

        setOwner(assignedTo);
        getSaveBtn().click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(dialogModalHeader));

    }

    private void setOwner(String owner) {
        WebElement taskOwner = driver.findElement(taskOwnerSelect);
        taskOwner.click();
        taskOwner.findElement(By.xpath("//option[text()[contains(.,'"+owner+"')]]")).click();
    }

    private void setRepetition(String type, Integer repeatNo, String frequency) {
        driver.findElement(repeatLink).click();
        Select repeatType = new Select(driver.findElement(repeatTypeSelect));
        Select freq = new Select(driver.findElement(repeatFrequencySelect));
        repeatType.selectByVisibleText(type);
        driver.findElement(repeatIntervalTxt).clear();
        driver.findElement(repeatIntervalTxt).sendKeys(String.valueOf(repeatNo));
        freq.selectByValue(frequency);
    }

    private void setRepetition(String type, Integer repeatNo, String frequency, boolean hasRepetition) {
        if(!hasRepetition) {
            driver.findElement(repeatLink).click();
        }
        Select repeatType = new Select(driver.findElement(repeatTypeSelect));
        Select freq = new Select(driver.findElement(repeatFrequencySelect));
        repeatType.selectByVisibleText(type);
        driver.findElement(repeatIntervalTxt).clear();
        driver.findElement(repeatIntervalTxt).sendKeys(String.valueOf(repeatNo));
        freq.selectByValue(frequency);
    }

    private void setCategory(String category) {
        driver.findElement(categorySelect).click();
        driver.findElement(categorySelect).findElement(By.xpath("//div[@class='select-box__option'][text()='"+category+"']")).click();
    }

    private void setTime(String time) {
        Select hourSelect = new Select(driver.findElement(timeHourSelect));
        Select minutesSelect = new Select(driver.findElement(timeMinutesSelect));
        Select meridiemSelect = new Select(driver.findElement(timeMeridiemSelect));

        String [] timeValues = retrieveTimeValues(time);
        hourSelect.selectByVisibleText(timeValues[0]);
        minutesSelect.selectByVisibleText(timeValues[1]);
        meridiemSelect.selectByVisibleText(timeValues[2]);

    }

    private void setDueDate(String dueDate) {
        driver.findElement(dueDateTxt).clear();
        driver.findElement(dueDateTxt).sendKeys(dueDate);
        driver.findElement(dueDateTxt).sendKeys(Keys.TAB);
    }

    private void setDetail(String addDetail) {
        driver.findElement(addDetailLink).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(additionalDetailsTxt));
        driver.findElement(additionalDetailsTxt).clear();
        driver.findElement(additionalDetailsTxt).sendKeys(addDetail);
    }
    private void setDetail(String addDetail, String update)
    {
        wait.until(ExpectedConditions.visibilityOfElementLocated(additionalDetailsTxt));
        driver.findElement(additionalDetailsTxt).clear();
        driver.findElement(additionalDetailsTxt).sendKeys(addDetail);
    }

    private void setDescription(String description) {
        driver.findElement(descriptionTxt).clear();
        driver.findElement(descriptionTxt).sendKeys(description);
    }

    public void updateTask(String description, String addDetail, String dueDate, String time, String repeatType, Integer repeatNo, String frequency, String category, String assignedTo) {
        wait.until(ExpectedConditions.presenceOfElementLocated(descriptionTxt));
        wait.until(ExpectedConditions.visibilityOfElementLocated(dialogModalHeader));
        setDescription(description);

        if(addDetail != null){
            setDetail(addDetail, "update");
        }
        setDueDate(dueDate);
        setTime(time);
        setCategory(category);
        if(repeatNo != null)
        {
            setRepetition(repeatType, repeatNo,frequency,true);
        }

        setOwner(assignedTo);
        getSaveBtn().click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(dialogModalHeader));
    }

    public DashboardPage deleteTask() {
        driver.findElement(deleteIcon).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(dialogModalHeader));
        return new DashboardPage();
    }
}
