package com.paulinefeytel;

import java.util.ArrayList;

/**
 *  This class contains all the lists of words
 */
public class AllLists {

    public ArrayList<ListWord> lists;

    public AllLists() {
        this.lists = new ArrayList<>();
    }

    public void addList(ListWord list) {
        this.lists.add(list);
    }

    public int countLists() {
        return lists.size();
    }

    public void displayAllLists() {
        for (ListWord listW: lists) {
            System.out.println(listW.getName());
        }
    }

    public ListWord findAList(String name) {
        for (ListWord list: lists) {
            if (list.getName().equals(name)) {
                return list;
            }
        }
        return null;
    }
}
