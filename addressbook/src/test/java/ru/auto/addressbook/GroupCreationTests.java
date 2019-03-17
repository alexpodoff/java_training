package ru.auto.addressbook;

import org.testng.annotations.Test;

public class GroupCreationTests extends BaseTest {

  @Test
  public void testGroupCreation() {
    gotoGroupPage();
    initGroupCreation();
    fillGroupForm(new GroupData("test1", "test2", "test3"));
    submitGroupCreation();
    returnToGroupPage();
  }

}
