package com.paulinefeytel.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;


/**
 *  This class allows to write and read the file "app.csv".
 *  When it writes, it writes the class data in the file.
 *  When it reads, it reads the file and put the data in the classes.
 */
public class FileManager {
    private static final String FILENAME = "app.csv";

    /**
     * Write the data in the file. CSV format
     * @param lists
     */
    public void saveFile(AllLists lists) {
        try (PrintWriter writer = new PrintWriter(FILENAME)) {
            for (int i = 0; i < lists.lists.size(); i++) {
                for (int j = 0; j < lists.lists.get(i).numberOfWords(); j++) {
                    writer.write(lists.lists.get(i).getName() + ";");
                    writer.write(lists.lists.get(i).getWordAsCSV(j));
                    writer.println();
                }
            }
        } catch (IOException e) {
            System.err.println("There was a problem writing to " + FILENAME);
        }
    }

    /**
     * Reads the file and put the data in the classes
     * @return AllLists
     */
    public AllLists readFile() {
        AllLists lists = new AllLists();
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String currentLine;

            while ((currentLine = br.readLine()) != null) {
                String[] wordFile = currentLine.split(";");

                Word word = new Word(wordFile[1], wordFile[2], Integer.parseInt(wordFile[3]), Integer.parseInt(wordFile[4]));

                if (!lists.lists.isEmpty()) {
                    boolean newList = false;
                    int i = 0;

                    while (i < lists.lists.size() && !newList) {
                        if (lists.lists.get(i).getName().equals(wordFile[0])) {
                            newList = true;
                        } else {
                            i++;
                        }
                    }

                    if (!newList) {
                        addListAndWord(lists, wordFile[0], word);
                    } else {
                        lists.lists.get(i).addWord(word);
                    }
                } else {
                    addListAndWord(lists, wordFile[0], word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lists;
    }

    private void addListAndWord(AllLists lists, String name, Word word) {
        ListWord list = new ListWord(name);
        list.addWord(word);
        lists.addList(list);
    }

}
