package ru.auto.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.auto.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() {
    app.goTO().GroupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("test111");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    Assert.assertEquals(after.size(), before.size() + 1);

    group.withId(after.stream().mapToInt(GroupData::getId).max().getAsInt());
    before.add(group);
    Assert.assertEquals(before, after);
  }

}
