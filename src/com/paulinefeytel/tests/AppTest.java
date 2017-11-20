package com.paulinefeytel.tests;

import com.paulinefeytel.controller.App;
import com.paulinefeytel.model.AllLists;
import com.paulinefeytel.model.ListWord;
import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void createNewList() {
        App app = new App();
        ListWord list = app.createNewList("food");
        assertNotNull(list);
    }

    @Test
    public void createSameNewList() {
        App app = new App();
        ListWord list1 = app.createNewList("food");
        ListWord list2 = app.createNewList("food");
        assertNotNull(list1);
        assertNull(list2);
    }
}