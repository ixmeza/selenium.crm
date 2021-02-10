package com.selenium.demo.base;
import com.selenium.demo.pages.*;
import org.junit.Assert;
import org.testng.annotations.Test;


public class E2E_Opportunity_Operations extends BaseTest {

    OpportunityPage opportunityPage;
    NewOpportunityPage newOpportunityPage;
    PipelinePage pipelinePage;
    OpportunitiesPage opportunitiesPage;
    SalesPage salesPage;

    @Test
    public void Should_be_able_to_create_opportunity()
    {
        String [] tags = { "tag1", "tag2"};
        String relatedTo = "ACME";
        String name = "Op"+faker.commerce().productName();
        String description = "my description";
        String [] currency = {"USD","GBP","EUR"};
        String expectedValue = String.valueOf(faker.number().numberBetween(1000,1000000));
        String [] paymentTerms = {"Fixed", "Hour", "Day", "Week", "Month", "Quarter"};
        Integer duration = faker.number().numberBetween(1,30);
        String milestone = "New";
        String owner = "Ix Test Demo";
        String closeDate = dashboardPage.getDummyDate();

        newOpportunityPage = dashboardPage.gotoPipeline().addOpportunity();
        pipelinePage = newOpportunityPage.addOpportunity(relatedTo, name, description, tags, currency[faker.number().numberBetween(0,2)], expectedValue, paymentTerms[faker.number().numberBetween(0,5)], duration, milestone, closeDate, null, owner).gotoPipeline().getPipeline();
        Assert.assertTrue(pipelinePage.isOpportunityPresent(1, relatedTo,name));
    }

    @Test
    public void Should_be_able_to_edit_opportunity(){
        String [] tags = { "tag1", "tag2"};
        String relatedTo = "ACME";
        String name = "Op"+faker.commerce().productName();
        String description = "my description";
        String [] currency = {"USD","GBP","EUR"};
        String expectedValue = String.valueOf(faker.number().numberBetween(1000,1000000));
        String [] paymentTerms = {"Fixed", "Hour", "Day", "Week", "Month", "Quarter"};
        Integer duration = faker.number().numberBetween(1,30);
        String milestone = "New";
        String owner = "Ix Test Demo";
        String closeDate = dashboardPage.getDummyDate();

        newOpportunityPage = dashboardPage.gotoPipeline().addOpportunity();
        opportunitiesPage = newOpportunityPage.addOpportunity(relatedTo, name, description, tags, currency[faker.number().numberBetween(0,2)], expectedValue, paymentTerms[faker.number().numberBetween(0,5)], duration, milestone, closeDate, null, owner).gotoPipeline().getOpportunities();
        newOpportunityPage = opportunitiesPage.editOpportunity(relatedTo+", "+name);

        newOpportunityPage.setOpportunityNameTxt("My updated name");
        newOpportunityPage.setDescriptionTxt("My updated description");
        opportunityPage = newOpportunityPage.save();
        Assert.assertTrue(opportunityPage.getDescription().contains("My updated description"));
        Assert.assertTrue(opportunityPage.getOpTitle().contains("My updated name"));
    }

    @Test
    public void Should_be_able_to_delete_opportunity(){
        String [] tags = { "tag1", "tag2"};
        String relatedTo = "ACME";
        String name = "Op"+faker.commerce().productName();
        String description = "my description";
        String [] currency = {"USD","GBP","EUR"};
        String expectedValue = String.valueOf(faker.number().numberBetween(1000,1000000));
        String [] paymentTerms = {"Fixed", "Hour", "Day", "Week", "Month", "Quarter"};
        Integer duration = faker.number().numberBetween(1,30);
        String milestone = "New";
        String owner = "Ix Test Demo";
        String closeDate = dashboardPage.getDummyDate();

        newOpportunityPage = dashboardPage.gotoPipeline().addOpportunity();
        opportunitiesPage = newOpportunityPage.addOpportunity(relatedTo, name, description, tags, currency[faker.number().numberBetween(0,2)], expectedValue, paymentTerms[faker.number().numberBetween(0,5)], duration, milestone, closeDate, null, owner).gotoPipeline().getOpportunities();

        salesPage = opportunitiesPage.deleteOpportunity(relatedTo+", "+name);
        Assert.assertTrue(salesPage.getTopMsg().contains("Opportunity deleted"));
    }


    @Test
    public void Should_be_able_to_move_opportunity_state()
    {
        String [] tags = { "tag1", "tag2"};
        String relatedTo = "ACME";
        String name = "Op"+faker.commerce().productName();
        String description = "my description";
        String [] currency = {"USD","GBP","EUR"};
        String expectedValue = String.valueOf(faker.number().numberBetween(1000,1000000));
        String [] paymentTerms = {"Fixed", "Hour", "Day", "Week", "Month", "Quarter"};
        Integer duration = faker.number().numberBetween(1,30);
        String milestone = "New";
        String owner = "Ix Test Demo";
        String closeDate = dashboardPage.getDummyDate();

        newOpportunityPage = dashboardPage.gotoPipeline().addOpportunity();
        opportunityPage = newOpportunityPage.addOpportunity(relatedTo, name, description, tags, currency[faker.number().numberBetween(0,2)], expectedValue, paymentTerms[faker.number().numberBetween(0,5)], duration, milestone, closeDate, null, owner);
        String partyId = opportunityPage.getPartyId();
        pipelinePage = opportunityPage.gotoPipeline().getPipeline();
        pipelinePage.moveOpportunity("opportunity"+partyId,"New");
        pipelinePage.moveOpportunity("opportunity"+partyId,"Qualified");
        pipelinePage.moveOpportunity("opportunity"+partyId,"Meeting");
        pipelinePage.moveOpportunity("opportunity"+partyId,"Proposal");
        pipelinePage.moveOpportunity("opportunity"+partyId,"Negotiation");
        pipelinePage.moveOpportunity("opportunity"+partyId,"Contract");
    }
}
