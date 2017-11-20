package com.paulinefeytel.model;

import java.util.ArrayList;

/**
 *  This class contains all the lists of words
 */
public class AllLists {

    public ArrayList<ListWord> list;

    public AllLists() {
        this.list = new ArrayList<>();
    }

    public void addList(ListWord list) {
        this.list.add(list);
    }

    public int countLists() {
        return list.size();
    }

    public void displayAllLists() {
        for (ListWord listW: list) {
            System.out.println(listW.getName());
        }
    }

    /**
     * Allow to find a list with the name
     * @param name Name of the list searched
     * @return the list of word found
     */
    public ListWord findAList(String name) {
        for (ListWord list: list) {
            if (list.getName().equals(name)) {
                return list;
            }
        }
        return null;
    }

    public boolean findList(String name) {
        if (findAList(name) == null) {
            return false;
        } else {
            return true;
        }
    }
}
