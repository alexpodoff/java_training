package ru.auto.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.auto.addressbook.model.ContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends BaseHelper {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void submitContactCreation() {
        click(By.name("submit"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.name("firstname"), contactData.getFirstname());
        type(By.name("lastname"), contactData.getLastname());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomephone());
        type(By.name("email"), contactData.getEmail());

    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void selectContactById(int id) {
        click(By.cssSelector("input[value='" + id + "']"));
        }

    public void selectContactByIndex(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void selectFirstContact() {
        click(By.name("selected[]"));
    }

    public void deleteContact() {
        click(By.xpath("//input[@value='Delete']"));
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void initContactModificationByIndex(int index) {
        wd.findElement(By.xpath("//tr[" + (index + 2) + "]/td[8]/a/img")).click();
    }

    public void confirmContactModification() {
        click(By.name("update"));
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactForm(contact);
        submitContactCreation();
    }

    public void modifyFirstContact(ContactData contact) {
        selectFirstContact();
        initContactModification();
        fillContactForm(contact);
        confirmContactModification();
    }

    public void modifyByIndex(ContactData contact, int index) {
        selectContactByIndex(index);
        initContactModificationByIndex(index);
        fillContactForm(contact);
        confirmContactModification();
    }
    
    public void deleteFirstContact() {
        selectFirstContact();
        deleteContact();
    }

    public void deleteByIndex(int index) {
        selectContactByIndex(index);
        deleteContact();
    }
    public boolean isThereAContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public List<ContactData> list() {
        List<ContactData> contacts = new ArrayList<>();
        List<WebElement> rows = wd.findElements(By.name("entry"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String name = cells.get(2).getText();
            String lastname = cells.get(1).getText();
            ContactData contact = new ContactData(name, lastname);
            contacts.add(contact);
        }
        return contacts;
    }
}
