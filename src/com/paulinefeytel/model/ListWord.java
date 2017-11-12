package com.paulinefeytel.model;

import java.util.ArrayList;

/**
 *  This class contains an ArrayList of Word and a name.
 */
public class ListWord {
    private ArrayList<Word> listWord;
    private String name;

    public ListWord(String name) {
        listWord = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addWord(Word word) {

        listWord.add(word);
    }

    public int numberOfWords() {
        return listWord.size();
    }

    public void displayNumberOfWords() {
        System.out.println("The list " + name + " has " + numberOfWords() + " words.");
    }

    public boolean removeWord(int index) {

        if (index >= 0 && index < listWord.size()) {
            listWord.remove(index);
            return true;
        }
        else {
            System.out.println("You must write the number of the word.");
            return false;
        }
    }

    public void displayAllWords() {
        for (Word word: listWord) {
            word.displayWord();
        }
    }

    public void displayAllWordsWithIndex() {
        int index = 1;
        for (int i = 0; i < listWord.size(); i++) {
            System.out.println(index + " - " + listWord.get(i).getWord() + " - " + listWord.get(i).getTranslation());
            index++;
        }
    }

    public void displayAllWordWithScore() {
        for (Word word: listWord) {
            word.displayWordWithScore();
        }
    }

    public Word getAWord(int index) {
        return listWord.get(index);
    }

    /**
     * @param index The index of the Word to be returned. It has to be a valid index, otherwise it will throw an exception.
     * @return the Word formatted for CSV
     */
    public String getWordAsCSV(int index) {
        Word word = listWord.get(index);
        return word.getWord() + ";" + word.getTranslation() + ";" + word.getScore() + ";" + word.getCount();
    }
}
