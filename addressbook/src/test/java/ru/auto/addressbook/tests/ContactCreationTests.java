package ru.auto.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.auto.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() {
    app.goTO().homePage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData(
            "test1", "test2", "aaa bbb ccc", "12345678", "omg@my.dom");

    app.contact().create(contact);
    app.goTO().homePage();
    List<ContactData> after = app.contact().list();
    Assert.assertEquals(after.size(), before.size() + 1);

    before.add(contact);
    Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
    before.sort(byId);
    after.sort(byId);
    Assert.assertEquals(before, after);
  }

}
