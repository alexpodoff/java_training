package ru.auto.addressbook;

import org.testng.annotations.Test;

public class GroupDeletionTests extends BaseTest {

  @Test
  public void testDeleteGroup() {
    gotoGroupPage();
    selectGroup();
    deleteSelectedGroups();
    returnToGroupPage();
  }
}
