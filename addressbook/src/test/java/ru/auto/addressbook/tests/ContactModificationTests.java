package ru.auto.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.auto.addressbook.model.ContactData;

import java.util.Set;

public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTO().homePage();
        if (app.contact().all().size() == 0) {
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
        Set<ContactData> before = app.contact().all();
        ContactData modifiedContact = before.iterator().next();
        ContactData contact = new ContactData()
                .withFirstname("test33331")
                .withLastname("test2")
                .withAddress("aaa bbb ccc")
                .withHomephone("12345678")
                .withEmail("omg@my.dom");
        app.contact().modifyById(modifiedContact);
        app.goTO().homePage();
        Set<ContactData> after = app.contact().all();
        Assert.assertEquals(after.size(), before.size());

        before.remove(contact);
        before.add(modifiedContact);
        Assert.assertEquals(before, after);
    }
}