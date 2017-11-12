package com.paulinefeytel.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileManager {
    public static final String FILENAME = "app.csv";


    public void saveCommand(AllLists lists) {
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

                    if (newList == false) {
                        //System.out.println("New list!");
                        ListWord list = new ListWord(wordFile[0]);
                        list.addWord(word);
                        lists.addList(list);
                    } else {
                        lists.lists.get(i).addWord(word);
                        //System.out.println("List exists!");
                    }
                } else {
                    //System.out.println("List!");
                    ListWord list = new ListWord(wordFile[0]);
                    list.addWord(word);

                    lists.addList(list);
                }

                //System.out.println(currentLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lists;
    }

}
