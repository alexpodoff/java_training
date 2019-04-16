package ru.auto.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.auto.addressbook.model.ContactData;
import ru.auto.addressbook.model.GroupData;
import ru.auto.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


public class GroupContactRelationTests extends TestBase {

    @BeforeMethod
    public void ensureContactPreconditions() {
        app.db().contacts();
        if (app.db().contacts().size() == 0) {
            app.goTO().homePage();
            app.contact().create(new ContactData()
                    .withFirstname("test6")
                    .withLastname("test66")
                    .withAddress("6 6 6")
                    .withHomephone("66666666")
                    .withEmail("hell@my.dom"));
            app.goTO().homePage();
        }
    }

    @BeforeMethod
    public void ensureGroupPreconditions() {
        if (app.db().groups().size() == 0) {
            app.goTO().GroupPage();
            app.group().create(new GroupData().withName("test6").withHeader("test66").withFooter("test666"));
        }
    }

    @Test
    public void testContactToGroup() {
        ContactData contact = app.db().contacts().iterator().next();
        GroupData group = app.db().groups().iterator().next();
        if (contact.getGroups().contains(group)) {
            app.contact().delFromGroup(contact, group);
        }
        app.goTO().homePage();
        Groups groupList = contact.getGroups();
        app.contact().addToGroup(contact, group);
        app.goTO().homePage();
        Groups newGroupList = app.db().contacts().stream().filter((c) -> c.getId() == contact.getId())
                .collect(Collectors.toSet()).iterator().next().getGroups();
        assertThat(newGroupList, equalTo(groupList.withAdded(group)));
        verifyContactListInUI();
    }

    @Test
    public void testContactFromGroup() {
        ContactData contact = app.db().contacts().iterator().next();
        GroupData group = app.db().groups().iterator().next();
        if (!contact.getGroups().contains(group)) {
            app.contact().addToGroup(contact, group);
        }
        Groups groupList = contact.getGroups();
        app.goTO().homePage();
        app.contact().delFromGroup(contact, group);
        app.goTO().homePage();
        Groups newGroupList = app.db().contacts().stream().filter((c) -> c.getId() == contact.getId())
                .collect(Collectors.toSet()).iterator().next().getGroups();
        assertThat(newGroupList, equalTo(groupList.without(group)));
        verifyContactListInUI();
    }
}
