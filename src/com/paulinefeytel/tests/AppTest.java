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
        AllLists allLists = new AllLists();
        ListWord list = new ListWord("colors");
        allLists.addList(list);
        assertEquals(list, app.createNewList("food"));
    }


}