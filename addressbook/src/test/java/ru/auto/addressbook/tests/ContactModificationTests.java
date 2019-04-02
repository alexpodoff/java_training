package ru.auto.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.auto.addressbook.model.ContactData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTO().homePage();
        if (app.contact().list().size() == 0) {
            app.contact().create(new ContactData()
                            .withFirstname("test6")
                            .withLastname("test66")
                            .withAddress("6 6 6")
                            .withHomephone("66666666")
                            .withEmail("hell@my.dom"));
        }
    }

    @Test
    public void testModificationContcact() throws Exception {
        app.goTO().homePage();
        List<ContactData> before = app.contact().list();
        int index = before.size() - 1;
        ContactData contact = new ContactData()
                .withFirstname("test1")
                .withLastname("test2")
                .withAddress("aaa bbb ccc")
                .withHomephone("12345678")
                .withEmail("omg@my.dom");
        app.contact().modifyByIndex(contact, index);
        app.goTO().homePage();
        List<ContactData> after = app.contact().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(contact);
        Comparator<? super ContactData> byId = Comparator.comparingInt(ContactData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}