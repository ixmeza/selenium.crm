package com.selenium.demo.base;
import com.selenium.demo.pages.CaseDetailPage;
import com.selenium.demo.pages.CasesPage;
import com.selenium.demo.pages.NewCasePage;
import com.selenium.demo.utils.Log;
import org.junit.Assert;
import org.testng.annotations.Test;

public class E2E_Case_Operations extends BaseTest{

    protected CasesPage casesPage;
    protected NewCasePage newCasePage;
    protected CaseDetailPage caseDetailPage;

    @Test
    public void Should_Be_Able_To_Create_Case()
    {
        String [] tags = { "tag1", "tag2"};
        String caseName = "Case" + faker.number().randomNumber();
        String caseDescription = faker.book().title();
        String relatedTo = "ACME";
        String track = null;
        String owner = "Ix Test Demo";

        Log.startTestCase("===== Should_Be_Able_To_Create_Case ===== ");
        newCasePage = dashboardPage.goToCases().getAddCase();
        casesPage = newCasePage.addCase(relatedTo,caseName,caseDescription, tags, track, owner ).goToCases();
        Assert.assertTrue(casesPage.isCasePresent(caseName));
        Log.endTestCase("Should_Be_Able_To_Create_Case");
    }

    @Test
    public void Should_Be_Able_To_Edit_Case()
    {
        String [] tags = { "tag1", "tag2"};
        String caseName = "Case" + faker.number().randomNumber();
        String caseDescription = faker.book().title();
        String relatedTo = "ACME";
        String track = null;
        String owner = "Ix Test Demo";
        String closedOn = dashboardPage.getDummyDate();

        Log.startTestCase("===== Should_Be_Able_To_Edit_Case ===== ");
        newCasePage = dashboardPage.goToCases().getAddCase();
        casesPage = newCasePage.addCase(relatedTo,caseName,caseDescription, tags, track, owner ).goToCases();
        casesPage.accessCase(caseName).editValues(relatedTo,caseName,caseDescription, closedOn, tags, track, owner );
        Log.endTestCase("Should_Be_Able_To_Edit_Case");
    }

    @Test
    public void Should_Be_Able_To_Delete_Case()
    {
        String [] tags = { "tag1", "tag2"};
        String caseName = "Case" + faker.number().randomNumber();
        String caseDescription = faker.book().title();
        String relatedTo = "ACME";
        String track = null;
        String owner = "Ix Test Demo";
        Log.startTestCase("===== Should_Be_Able_To_Delete_Case ===== ");
        newCasePage = dashboardPage.goToCases().getAddCase();
        casesPage = newCasePage.addCase(relatedTo,caseName,caseDescription, tags, track, owner ).goToCases();
        Assert.assertTrue(casesPage.accessCase(caseName).delete().getTopMsg().contains("Case deleted"));
        Log.endTestCase("Should_Be_Able_To_Delete_Case ");
    }

    @Test
    public void Should_Be_Able_To_Close_Case()
    {
        String [] tags = { "tag1", "tag2"};
        String caseName = "Case" + faker.number().randomNumber();
        String caseDescription = faker.book().title();
        String relatedTo = "ACME";
        String track = null;
        String owner = "Ix Test Demo";
        Log.startTestCase("===== Should_Be_Able_To_Close_Case ===== ");
        newCasePage = dashboardPage.goToCases().getAddCase();
        casesPage = newCasePage.addCase(relatedTo,caseName,caseDescription, tags, track, owner ).goToCases();
        caseDetailPage = casesPage.accessCase(caseName).close();
        Assert.assertTrue(caseDetailPage.getCaseStatus().contains("Closed"));
        Log.endTestCase("Should_Be_Able_To_Close_Case ");
    }

    @Test
    public void Should_Be_Able_To_ReOpen_Case()
    {
        String [] tags = { "tag1", "tag2"};
        String caseName = "Case" + faker.number().randomNumber();
        String caseDescription = faker.book().title();
        String relatedTo = "ACME";
        String track = null;
        String owner = "Ix Test Demo";
        Log.startTestCase("===== Should_Be_Able_To_ReOpen_Case ===== ");
        newCasePage = dashboardPage.goToCases().getAddCase();
        casesPage = newCasePage.addCase(relatedTo,caseName,caseDescription, tags, track, owner ).goToCases();
        caseDetailPage = casesPage.accessCase(caseName).close();
        Assert.assertTrue(caseDetailPage.getCaseStatus().contains("Closed"));
        Assert.assertTrue(caseDetailPage.reOpen().getCaseStatus().contains("Open"));
        Log.endTestCase("Should_Be_Able_To_ReOpen_Case");
    }
}
