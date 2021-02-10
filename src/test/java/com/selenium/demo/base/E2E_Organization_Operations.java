package com.selenium.demo.base;

import com.github.javafaker.Faker;
import com.selenium.demo.pages.ContactDetail;
import com.selenium.demo.pages.OrganizationPage;
import com.selenium.demo.pages.PeoplePage;
import com.selenium.demo.utils.Log;
import org.junit.Assert;
import org.testng.annotations.Test;

public class E2E_Organization_Operations extends BaseTest {
    private final Faker faker = new Faker();
    private OrganizationPage organizationPage;
    private PeoplePage peoplePage;
    private ContactDetail contactDetail;

    @Test
    public void Should_Be_Able_To_Create_An_Organization()
    {
        Log.startTestCase("===== Should_Be_Able_To_Create_An_Organization ===== ");

        String orgName = faker.commerce().department();
        String [] phoneNumber = { faker.phoneNumber().phoneNumber(), faker.phoneNumber().cellPhone(), faker.phoneNumber().subscriberNumber() };
        String [] phoneType = { "Work", "Fax", "Direct"};
        String [] email = { faker.name().firstName() + "@mail.com", faker.name().firstName() + "@mail.com" };
        String [] emailType = { "Work", "Home"};
        String [] companyUrl = { faker.company().url() , faker.company().url() };
        String [] websiteType = { "Twitter", "Facebook", "Home", "Work"};

        peoplePage = getDashboardPage().goToPeople();
        organizationPage = peoplePage.addOrganization();

        organizationPage.setOrgName(orgName);
        organizationPage.setPhoneNumber(1, phoneNumber[0]);
        organizationPage.getAddPhoneNumberBtn().click();
        organizationPage.setPhoneNumber(2, phoneNumber[1]);
        organizationPage.getAddPhoneNumberBtn().click();
        organizationPage.setPhoneNumber(3, phoneNumber[2]);
        organizationPage.setPhoneNumberType(1,phoneType[0]);
        organizationPage.setPhoneNumberType(2,phoneType[1]);
        organizationPage.setPhoneNumberType(3,phoneType[2]);
        organizationPage.getAddEmailBtn().click();
        organizationPage.getAddEmailBtn().click();
        organizationPage.setEmail(1, email[0]);
        organizationPage.setEmail(2,email[1]);
        organizationPage.setEmailType(1, emailType[0]);
        organizationPage.setEmailType(2, emailType[1]);

        organizationPage.getAddWebSiteBtn().click();
        organizationPage.getAddWebSiteBtn().click();
        organizationPage.setWebsite(1, companyUrl[0] );
        organizationPage.setWebsite(2, companyUrl[1] );
        organizationPage.setWebSitesType(1,websiteType[0]);
        organizationPage.setWebSitesType(1,websiteType[1]);
        organizationPage.setWebSitesType(2,websiteType[2]);
        organizationPage.setWebSitesType(2,websiteType[3]);

        organizationPage.getAddAddressBtn().click();
        organizationPage.getAddAddressBtn().click();

        organizationPage.setAddressType(1,"Office");
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

        contactDetail = organizationPage.saveOrg();
        Assert.assertTrue(contactDetail.getTitle().contains(orgName));
        contactDetail.goToPeople();
        Assert.assertTrue(peoplePage.isOrganizationPresent(orgName));
        Log.endTestCase("Should_Be_Able_To_Create_An_Organization");
    }

    @Test
    public void Should_Be_Able_To_Update_An_Organization()
    {
        Log.startTestCase("===== Should_Be_Able_To_Update_An_Organization ===== ");
        String orgName = faker.commerce().department();
        String [] phoneNumber = { faker.phoneNumber().phoneNumber() };
        String [] phoneType = { "Work"};
        String [] email = { faker.name().firstName() + "@mail.com" };
        String [] emailType = { "Work"};


        peoplePage = getDashboardPage().goToPeople();
        organizationPage = peoplePage.addOrganization();

        organizationPage.setOrgName(orgName);
        contactDetail = organizationPage.saveOrg();
        organizationPage = contactDetail.edit();
        organizationPage.getAddPhoneNumberBtn().click();
        organizationPage.setPhoneNumber(1, phoneNumber[0]);
        organizationPage.setPhoneNumberType(1,phoneType[0]);
        organizationPage.getAddEmailBtn().click();
        organizationPage.setEmail(1, email[0]);
        organizationPage.setEmailType(1, emailType[0]);
        contactDetail = organizationPage.saveOrg();


        Log.endTestCase("Should_Be_Able_To_Create_An_Organization");
    }

    @Test
    public void Should_Be_Able_To_Delete_An_Organization()
    {
        Log.startTestCase("===== Should_Be_Able_To_Delete_An_Organization ===== ");

        String orgName = faker.commerce().department();
        String [] phoneNumber = { faker.phoneNumber().phoneNumber() };
        String [] phoneType = { "Work"};
        String [] email = { faker.name().firstName() + "@mail.com" };
        String [] emailType = { "Work"};
        String [] companyUrl = { faker.company().url() };
        String [] websiteType = { "Work"};

        peoplePage = getDashboardPage().goToPeople();
        organizationPage = peoplePage.addOrganization();

        organizationPage.setOrgName(orgName);
        organizationPage.setPhoneNumber(1, phoneNumber[0]);
        organizationPage.setPhoneNumberType(1,phoneType[0]);
        organizationPage.getAddEmailBtn().click();
        organizationPage.setEmail(1, email[0]);
        organizationPage.setEmailType(1, emailType[0]);
        organizationPage.getAddWebSiteBtn().click();
        organizationPage.setWebsite(1, companyUrl[0] );
        organizationPage.setWebSitesType(1,websiteType[0]);
        organizationPage.getAddAddressBtn().click();
        organizationPage.setAddressType(1,"Office");
        organizationPage.setAddressTxt(1,faker.address().streetAddress());
        organizationPage.setCityTxt(1,faker.address().cityName());
        organizationPage.setStateTxt(1,faker.address().state());
        organizationPage.setZipCodeTxt(1, faker.address().zipCode());
        organizationPage.setCountryTxt(1,faker.country().name());

        contactDetail = organizationPage.saveOrg();
        peoplePage = contactDetail.delete();
        Assert.assertFalse(peoplePage.isOrganizationPresent(orgName));

        Log.endTestCase("Should_Be_Able_To_Delete_An_Organization");
    }

}
