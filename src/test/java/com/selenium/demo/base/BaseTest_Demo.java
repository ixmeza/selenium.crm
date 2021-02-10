package com.selenium.demo.base;

import com.github.javafaker.Faker;
import com.selenium.demo.pages.*;
import com.selenium.demo.utils.Log;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.util.Date;


public class BaseTest_Demo extends Base{


    public Faker faker = new Faker();
    protected LoginPage loginPage;
    protected DashboardPage dashboardPage;
    public static Log slog = Log.getInstance();
    public static Logger log = slog.getLogger();
    ReadConfig readConfig = new ReadConfig();
    protected PeoplePage peoplePage;
    protected PersonPage personPage;
    protected OrganizationPage organizationPage;
    protected NewCasePage newCasePage;
    protected NewOpportunityPage newOpportunityPage;
    protected OpportunityPage opportunityPage;
    protected PipelinePage pipelinePage;

    public void setUp() throws MalformedURLException {

        Base.initialize();
        driver.manage().window().maximize();
        log.info("===== Initializing driver ===== ");
        log.info("Navigating to URL");
        driver.get(readConfig.getUrl());
        log.info("Authenticating with valid values");
        loginPage = new LoginPage();
        loginPage.setUsername(readConfig.getUserName());
        loginPage.setPassword(readConfig.getPassword());
        dashboardPage = loginPage.clickLoginBtn();
        String [] tags = { "tag1", "tag2"};

        newOpportunityPage = dashboardPage.gotoPipeline().addOpportunity();
        opportunityPage = newOpportunityPage.addOpportunity("ACME", "ACME Opportunity", "my description", tags, "MXN", "1000000", "Hour",5,"New", "10/01/2022", "track", "Ix Test Demo");
        String partyId = opportunityPage.getPartyId();

        String myDateObj = getDummyDate();
        opportunityPage.addTask(faker.company().catchPhrase(), "hello world", myDateObj, "01:00 AM", "Repeat every", 5, "MONTHLY", "Call", "Me");

        dashboardPage = opportunityPage.goToDashboard();
        newCasePage = dashboardPage.goToCases().getAddCase();
        pipelinePage = newCasePage.addCase("ACME","Case name","my description", tags, "track", "Ix Test Demo" ).gotoPipeline().getPipeline();

        pipelinePage.moveOpportunity("opportunity"+partyId,"New");
        pipelinePage.moveOpportunity("opportunity"+partyId,"Qualified");
        pipelinePage.moveOpportunity("opportunity"+partyId,"Meeting");
        pipelinePage.moveOpportunity("opportunity"+partyId,"Proposal");
        pipelinePage.moveOpportunity("opportunity"+partyId,"Negotiation");
        pipelinePage.moveOpportunity("opportunity"+partyId,"Contract");

        pipelinePage = dashboardPage.gotoPipeline().getPipeline();
        peoplePage = pipelinePage.goToPeople();

        organizationPage = peoplePage.addOrganization();
        organizationPage.setOrgName(faker.commerce().department());
        organizationPage.setPhoneNumber(1, faker.phoneNumber().phoneNumber());
        organizationPage.getAddPhoneNumberBtn().click();
        organizationPage.setPhoneNumber(2, faker.phoneNumber().cellPhone());
        organizationPage.getAddPhoneNumberBtn().click();
        organizationPage.setPhoneNumber(3, faker.phoneNumber().subscriberNumber());
        organizationPage.setPhoneNumberType(1,"Work");
        organizationPage.setPhoneNumberType(2,"Fax");
        organizationPage.setPhoneNumberType(3,"Direct");
        organizationPage.getAddEmailBtn().click();
        organizationPage.getAddEmailBtn().click();
        organizationPage.setEmail(1,faker.name().firstName() + "@mail.com");
        organizationPage.setEmail(2,faker.name().firstName() + "@mail.com");
        organizationPage.setEmailType(1, "Work");
        organizationPage.setEmailType(2, "Home");
        organizationPage.getAddWebSiteBtn().click();
        organizationPage.getAddWebSiteBtn().click();
        organizationPage.setWebsite(1, faker.company().url());
        organizationPage.setWebsite(2, faker.company().url());
        organizationPage.setWebSitesType(1,"Facebook");
        organizationPage.setWebSitesType(1,"Home");
        organizationPage.setWebSitesType(2,"Work");
        organizationPage.setWebSitesType(2,"Twitter");

        organizationPage.getAddAddressBtn().click();
        organizationPage.getAddAddressBtn().click();

        organizationPage.setAddressType(1,"Billing");
        organizationPage.setAddressTxt(1,faker.address().streetAddress());
        organizationPage.setCityTxt(1,faker.address().cityName());
        organizationPage.setStateTxt(1,faker.address().state());
        organizationPage.setZipCodeTxt(1, faker.address().zipCode());
        organizationPage.setCountryTxt(1,faker.country().name());

        organizationPage.setAddressTxt(2,faker.address().streetAddress());
        organizationPage.setAddressType(2,"Billing");
        organizationPage.setCityTxt(2,faker.address().cityName());
        organizationPage.setStateTxt(2,faker.address().state());
        organizationPage.setZipCodeTxt(2, faker.address().zipCode());
        organizationPage.setCountryTxt(2,faker.country().name());

        personPage = organizationPage.saveOrg().goToPeople().addPerson();
        // peoplePage.delete("ixtestdemo@outlook.com");
        personPage.setTitle("Dr");
        personPage.setFirstName(faker.name().firstName());
        personPage.setLastName(faker.name().lastName());
        personPage.setJobTitle(faker.job().title());
        personPage.setOrganizationSelect("ACME");

        personPage.setPhoneNumber(1,faker.phoneNumber().phoneNumber());
        personPage.setEmail(1,faker.name()+String.valueOf(faker.number().numberBetween(0,100))+"@mail.com");
        personPage.setWebsite(1,"youtube.com");
        personPage.getAddPhoneNumberBtn().click();
        personPage.setPhoneNumber(2,faker.phoneNumber().phoneNumber());
        personPage.getAddPhoneNumberBtn().click();
        personPage.setPhoneNumber(3,faker.phoneNumber().phoneNumber());
        personPage.setPhoneNumberType(1,"Work");
        personPage.setPhoneNumberType(2,"Mobile");
        personPage.setPhoneNumberType(3,"Mobile");
        personPage.setEmailType(1, "Home");
        personPage.getAddEmailBtn().click();
        personPage.setEmail(2,faker.name()+String.valueOf(faker.number().numberBetween(0,100))+"@mail.com");
        personPage.getAddEmailBtn().click();
        personPage.setEmail(3,faker.name()+String.valueOf(faker.number().numberBetween(0,100))+"@mail.com");
        personPage.setEmailType(2, "Work");
        personPage.setEmailType(3, "Home");
        personPage.getAddWebSiteBtn().click();
        personPage.setWebSitesType(1,"Facebook");
        personPage.setWebSitesType(1,"Home");
        personPage.setWebSitesType(2,"Work");
        personPage.setWebSitesType(2,"Twitter");

        personPage.getAddAddressBtn().click();
        personPage.getAddAddressBtn().click();

        personPage.setAddressType(1,"Billing");
        personPage.setAddressTxt(1,faker.address().streetAddress());
        personPage.setCityTxt(1,faker.address().cityName());
        personPage.setStateTxt(1,faker.address().state());
        personPage.setZipCodeTxt(1, faker.address().zipCode());
        personPage.setCountryTxt(1,"France");

        personPage.setAddressType(2,"Office");
        personPage.setAddressTxt(2,faker.address().streetAddress());
        personPage.setCityTxt(2,faker.address().cityName());
        personPage.setStateTxt(2,faker.address().state());
        personPage.setZipCodeTxt(2, faker.address().zipCode());
        personPage.setCountryTxt(2,"United Kingdom");

        personPage.getSaveBtn().click();

        driver.quit();
    }
    public static void main(String[] args) throws MalformedURLException {
        BaseTest_Demo test = new BaseTest_Demo();
        test.setUp();
    }
}
