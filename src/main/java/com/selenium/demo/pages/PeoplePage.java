package com.selenium.demo.pages;
import com.selenium.demo.base.Base;
import com.selenium.demo.utils.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class PeoplePage extends Base {
    // locators

    private final By header = By.cssSelector(".page__header-title");
    private final By addPersonBtn = By.cssSelector("[href*='person/new']");
    private final By addOrganisationBtn = By.cssSelector("[href*='organisation/new']");
    private final By tagFilter = By.xpath("//div/*[text()[contains(.,'Tag')]]");
    private final By nameFilter = By.xpath("//div/*[text()[contains(.,'Name')]]");
    private final By optionsSearchBox = By.cssSelector(".select-box__options input[role='searchbox']");
    private final By optionsSelectBox = By.cssSelector(".select-box__options select");
    private final By optionsApplyBtn = By.cssSelector(".select-box__options button[class*='apply']");
    private final By getOptionsRemoveBtn = By.cssSelector(".select-box__options button[class*='remove']");
    private final By getOptionsCancelBtn = By.cssSelector(".select-box__options button[class*='cancel']");
    private final By personsTable = By.cssSelector("table.simple-table.simple-table--with-hover-effect.list-results__table");
    private final By actionsDelete = By.cssSelector("div[class*='list-actions__delete']");
    private final By actionsAddNote = By.cssSelector("div[class*='list-actions__add-note']");
    private final By actionsSelectColumns = By.cssSelector("div[class*='list-columns list-select']");
    private final By actionsSortBy = By.cssSelector("div[class*='list-sort']");
    private final By deleteModal = By.cssSelector(".form.chunky");
    private final By deleteModalChkBox = By.cssSelector(".form-checkbox");
    private final By deleteModalDeleteBtn = By.cssSelector(".form.chunky button[type='submit']");
    private final By deleteModalCancelBtn = By.cssSelector(".form.chunky button:nth-child(2)");
    private final By deleteFormErrors = By.cssSelector(".form-errors__error");

    public PeoplePage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(addPersonBtn));
    }

    public By getPersonCheckBox(String email)
    {
        Log.info("Retrieving checkbox for: "+email);
        return By.xpath("//div/*[text()[contains(.,'"+email+"')]]/ancestor::tr//input");
    }

    public void delete(String email){
        Log.info("Deleting contact using email: "+email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(personsTable));
        Log.info("Selecting checkbox for: " +email);
        driver.findElement(this.getPersonCheckBox(email)).click();
        Log.info("Clicking on delete");
        driver.findElement(actionsDelete).click();
        Log.info("Waiting for delete modal");
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteModal));
        Log.info("Waiting for delete modal checkbox");
        wait.until(ExpectedConditions.visibilityOfElementLocated(deleteModalChkBox));
        Log.info("Selecting checkbox");
        driver.findElement(deleteModalChkBox).click();
        Log.info("Clicking delete button");
        driver.findElement(deleteModalDeleteBtn).click();
    }

    public PersonPage addPerson(){
        Log.info("Clicking add person button");
        driver.findElement(addPersonBtn).click();
        return new PersonPage();
    }

    public OrganizationPage addOrganization(){
        Log.info("Going to add organization page");
        driver.findElement(addOrganisationBtn).click();
        return new OrganizationPage();
    }

    public Boolean isOrganizationPresent(String name){
        Log.info("Verifying if organization [ "+ name +" ] name is present on list");
        boolean isPresent = false;
        wait.until(ExpectedConditions.visibilityOfElementLocated(personsTable));
        isPresent = driver.findElements(By.xpath("//a[text()[contains(.,'" + name + "')]]")).size() > 0;
        return isPresent;

    }
    public ContactDetail accessContact(String name){
        Log.info("Accessing contact: "+name);
        WebElement targetContact = null;
        Log.info("Retrieving all contacts in list");
        List<WebElement> contacts = driver.findElements(By.cssSelector("td a"));
        for (WebElement aContact : contacts) {
            if (aContact.getText().contains(name)) {
                Log.info("Contact name: "+ name +" found");
                targetContact = aContact;
                break;
            }
        }
        targetContact.click();
        Log.info("Clicking on the contact link");
        return new ContactDetail();
    }
}
