package ru.auto.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.auto.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTO().GroupPage();
        if (app.group().list().size() == 0) {
            app.group().create(new GroupData("test6", "test66", "test666"));
        }
    }

    @Test
    public void testModifyGroup() {
        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        app.group().selectByIndex(index);
        GroupData group = new GroupData(before.get(
                index).getId(), "test11", "test12", "test13");
        app.group().modify(group);
        List<GroupData> after = app.group().list();
        Assert.assertEquals(after.size(), before.size());

        before.remove(index);
        before.add(group);
        Comparator<? super GroupData> byId = Comparator.comparingInt(GroupData::getId);
        before.sort(byId);
        after.sort(byId);
        Assert.assertEquals(before, after);
    }
}
