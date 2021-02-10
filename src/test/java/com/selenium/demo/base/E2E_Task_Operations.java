package com.selenium.demo.base;

import com.github.javafaker.Faker;
import com.selenium.demo.pages.TaskPage;
import com.selenium.demo.utils.Log;
import org.testng.Assert;
import org.testng.annotations.Test;

public class E2E_Task_Operations extends BaseTest{

    private final Faker faker = new Faker();
    protected TaskPage taskPage;

    @Test
    public void Should_Be_Able_To_Create_Task()
    {
        Log.startTestCase("===== Should_Be_Able_To_Create_Task ===== ");

        String description = faker.space().constellation();
        String additionalDetails = faker.lorem().paragraph();
        String dummyDate = getDashboardPage().getDummyDate();
        String dummyTime = "01:00 AM";
        String repeatType = "Repeat every";
        Integer repeatNo = faker.number().numberBetween(1,10);
        String repeatFrequency = "MONTHLY";
        String category = "Call";
        String assignedTo = "Me";

        getDashboardPage().addTask(description, additionalDetails, dummyDate, dummyTime, repeatType, repeatNo, repeatFrequency, category, assignedTo);
        Log.endTestCase("Should_Be_Able_To_Create_Task");
    }

    @Test
    public void Should_Be_Able_To_Update_Task()
    {
        Log.startTestCase("===== Should_Be_Able_To_Update_Task ===== ");

        String beforeDescription = "ToBeUpdatedBefore";
        String additionalDetails = faker.lorem().paragraph();
        String dummyDate = getDashboardPage().getDummyDate();
        String dummyTime = "01:00 AM";
        String repeatType = "Repeat every";
        Integer repeatNo = faker.number().numberBetween(1,10);
        String repeatFrequency = "MONTHLY";
        String category = "Call";
        String assignedTo = "Me";
        getDashboardPage().addTask(beforeDescription, additionalDetails, dummyDate, dummyTime, repeatType, repeatNo, repeatFrequency, category, assignedTo);

        String description = "ToBeUpdatedAfter";
        taskPage = getDashboardPage().openTask(beforeDescription);
        taskPage.updateTask(description, additionalDetails, dummyDate, dummyTime, repeatType, repeatNo, repeatFrequency, category, assignedTo);
        Boolean isTaskPresent = getDashboardPage().isTaskPresent(description);
        Assert.assertTrue(isTaskPresent);

    }

    @Test
    public void Should_Be_Able_To_Complete_Task(){
        Log.startTestCase("===== Should_Be_Able_To_Complete_Task ===== ");

        String description = "ToBeCompleted";
        String additionalDetails = faker.lorem().paragraph();
        String dummyDate = getDashboardPage().getDummyDate();
        String dummyTime = "01:00 AM";
        String repeatType = "Repeat every";
        Integer repeatNo = faker.number().numberBetween(1,10);
        String repeatFrequency = "MONTHLY";
        String category = "Call";
        String assignedTo = "Me";

        getDashboardPage().addTask(description, additionalDetails, dummyDate, dummyTime, repeatType, repeatNo, repeatFrequency, category, assignedTo);
        Boolean isTaskPresent = getDashboardPage().isTaskPresent(description);
        Assert.assertTrue(isTaskPresent);

        dashboardPage.markAsComplete(description);
        Boolean isTaskCompleted = getDashboardPage().isTaskCompleted(description);
        Assert.assertTrue(isTaskCompleted);
    }

    @Test
    public void Should_Be_Able_To_Delete_Task()
    {
        Log.startTestCase("===== Should_Be_Able_To_Delete_Task ===== ");

        String description = "ToBeDeleted";
        String additionalDetails = faker.lorem().paragraph();
        String dummyDate = getDashboardPage().getDummyDate();
        String dummyTime = "01:00 AM";
        String repeatType = "Repeat every";
        Integer repeatNo = faker.number().numberBetween(1,10);
        String repeatFrequency = "MONTHLY";
        String category = "Call";
        String assignedTo = "Me";

        getDashboardPage().addTask(description, additionalDetails, dummyDate, dummyTime, repeatType, repeatNo, repeatFrequency, category, assignedTo);
        Boolean isTaskPresent = getDashboardPage().isTaskPresent(description);
        Assert.assertTrue(isTaskPresent);

        taskPage = getDashboardPage().openTask(description);
        taskPage.deleteTask();
        isTaskPresent = getDashboardPage().isTaskPresent(description);
        Assert.assertFalse(isTaskPresent);
    }

}
