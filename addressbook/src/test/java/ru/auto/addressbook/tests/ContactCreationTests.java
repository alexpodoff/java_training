package ru.auto.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.auto.addressbook.model.ContactData;

import java.util.Set;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTO().homePage();
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData()
            .withFirstname("test1")
            .withLastname("test2")
            .withAddress("aaa bbb ccc")
            .withHomephone("12345678")
            .withEmail("omg@my.dom");
    app.contact().create(contact);
    app.goTO().homePage();
    Set<ContactData> after = app.contact().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    contact.withId(after.stream().mapToInt(ContactData::getId).max().getAsInt());
    before.add(contact);
    Assert.assertEquals(before, after);
  }

}
