package com.selenium.demo.pages;
import com.selenium.demo.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class DashboardPage extends Base {
    // locators
    private final By addTasksBtn = By.cssSelector("button.button--secondary.button--small");

    public boolean getAddTask(){
       return driver.findElement(addTasksBtn).isDisplayed();
    }

    public TaskPage addTask(String description, String addDetail, String dueDate, String time, String repeatType, Integer repeatNo, String frequency, String category, String assignedTo){
        log.info("Clicking on add task button");
        driver.findElement(addTasksBtn).click();
        log.info("Creating a new task with values sent as parameters");
        return new TaskPage(driver, description, addDetail, dueDate, time, repeatType, repeatNo, frequency, category, assignedTo);
    }

    public TaskPage openTask(String taskName){
        log.info("Opening task");
        driver.findElement(By.xpath("//button[text()[contains(.,'"+taskName+"')]]")).click();
        return new TaskPage(driver);
    }
    public Boolean isTaskPresent(String taskName){
        log.info("Verifying task presence");
        WebElement task = null;
        try{
             task = driver.findElement(By.xpath("//button[text()[contains(.,'"+taskName+"')]]"));
        }catch (Exception e){

        }
        return task != null;
    }

    public void markAsComplete(String description) {
        log.info("marking task as complete");
        driver.findElement(By.xpath("//button[text()[contains(.,'"+description+"')]]/ancestor::div[@class='general-task-item']//input")).click();
    }

    public boolean isTaskCompleted(String description){
        log.info("Verifying task completion");
        WebElement element = null;
        try {
            element = driver.findElement(By.xpath("//button[text()[contains(.,'"+description+"')]]/ancestor::div[@class='general-task-item is-completed']"));
        }
        catch (Exception e){

        }
        return element != null;
    }
}
