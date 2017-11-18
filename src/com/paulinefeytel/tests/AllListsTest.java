package com.paulinefeytel.tests;

import com.paulinefeytel.model.AllLists;
import com.paulinefeytel.model.ListWord;
import org.junit.Test;

import static org.junit.Assert.*;

public class AllListsTest {

    @Test
    public void findAList() {
        AllLists allLists = new AllLists();
        ListWord list1 = new ListWord("colors");
        ListWord list2 = new ListWord("animals");
        ListWord list3 = new ListWord("food");
        allLists.addList(list1);
        allLists.addList(list2);
        allLists.addList(list3);

        assertEquals(list2, allLists.findAList("animals"));
    }
}