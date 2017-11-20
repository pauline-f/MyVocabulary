package com.paulinefeytel.controller;

import com.paulinefeytel.model.*;
import com.paulinefeytel.model.FileManager;
import com.paulinefeytel.view.Parser;

public class App {
    private AllLists allLists;
    private Parser parser;
    private FileManager fileManager;
    private Quizz quizz;

    public App() {
        parser = new Parser();
        fileManager = new FileManager();
        allLists = new AllLists();
    }

    public void loadLists() {
        allLists = fileManager.readFile();
    }

    private void displayAllLists() {
        allLists.displayAllLists();
    }

    public ListWord createNewList(String name) {
        if (allLists.findList(name)) {
            System.out.println("This list already exists!");
            return null;
        } else {
            ListWord list = new ListWord(name);
            allLists.addList(list);
            return list;
        }
    }

    public void showWelcomeMessage() {
        System.out.println("Welcome! With this app, you can create lists for learn new vocabulary in a new language and try to remember them with quizzs.");
        System.out.println("For the moment, you have " + allLists.list.size() + " list(s).");
        System.out.println("Write 'HELP' if you need more informations.");
        command(parser.write());
    }

    /**
     * Manage the command input
     * @param input This is user input
     */
    public void command(String input) {
        String first = parser.command(input);

        while (!first.equals("QUIT")) {
            switch (first) {
                case "HELP":
                    helpCommand();
                    break;
                case "CREATE":
                    createCommand();
                    break;
                case "LISTS":
                    listsCommand();
                    break;
                case "ADD":
                    addCommand();
                    break;
                case "REMOVE":
                    removeCommand();
                    break;
                case "WORDS":
                    wordsCommand();
                    break;
                case "SCORE":
                    scoreCommand();
                    break;
                case "SAVE":
                    fileManager.saveFile(allLists);
                    helpCommand();
                    break;
                case "QUIZZ":
                    quizzCommand();
                    break;
                default:
                    System.out.println("Unknown command!");
                    helpCommand();
                    break;
            }
            command(parser.write());
        }
    }

    /**
     * Manage the command for the quizzes
     */
    private void quizzCommand() {
        System.out.println("Choose your quizz:");
        System.out.println("Write '1' if you want to write the translation of a word");
        System.out.println("Write '2' if you want to write the word of a translation");
        String input = (parser.write());

        if (input.equals("1") || input.equals("2")) {
            ListWord list = selectList();
            if (list != null) {
                stopCommand();
                quizz = QuizzFactory.getQuizz(input, list);
                playQuizz();
            } else {
                System.out.println("This list doesn't exist!");
                quizzCommand();
            }
        }
        else {
            System.out.println("This command isn't valid!");
            quizzCommand();
        }
    }

    /**
     * Manage the quizz: display the word or the translation, check the answer written by the user and
     * display the good answer if the user is wrong
     */
    private void playQuizz() {
        String wordQuizz = quizz.displayWord();
        System.out.println(wordQuizz);

        String input = parser.write();

        if (input.toUpperCase().equals("STOP")) {
            helpCommand();
        } else if (quizz.isGoodAnswer(input)) {
            System.out.println("Good Answer!");
            quizz.incrementCount();
            quizz.incrementScore();
            playQuizz();
        } else {
            System.out.println("The answer was: " + quizz.getGoodAnswer());
            quizz.incrementCount();
            playQuizz();
        }
    }

    /**
     * Display all the words of a list with the score
     */
    private void scoreCommand() {
        ListWord list = selectList();
        if (list != null) {
            System.out.println(list.displayAllWordWithScore());
            helpCommand();
        } else {
            System.out.println("This list doesn't exist!");
            scoreCommand();
        }
    }

    /**
     * Lets the user input a list name, and tries to return it.
     * @return list if found, otherwise null
     */
    private ListWord selectList() {
        System.out.println("Choose a list:");
        displayAllLists();

        String inputList = parser.write();
        return allLists.findAList(inputList);
    }

    /**
     * Displays all the commands that the user can write
     */
    private void helpCommand() {
        System.out.println("Write 'CREATE' if you want create a list.");
        System.out.println("Write 'LISTS' if you want display all the allLists.");
        System.out.println("Write 'ADD' if you want add words in a list.");
        System.out.println("Write 'REMOVE' if you want remove a word in a list.");
        System.out.println("Write 'WORDS' if you want display all words in a list.");
        System.out.println("Write 'QUIZZ' if you want display a quizz.");
        System.out.println("Write 'SCORE' if you want display your score." );
        System.out.println("Write 'SAVE' if you want to save the data in a fileManager.");
        System.out.println("Write 'QUIT' if you want to quit the application.");
    }

    /**
     * Allows the user to stop writing words or stopping a quizz
     */
    private void stopCommand() {
        System.out.println("Write 'STOP' if you are done.");
    }

    /**
     * Allows the user to create a new list
     */
    private void createCommand() {
        System.out.println("Write the name of your list: ");
        createNewList(parser.write());
        helpCommand();
    }

    /**
     * Displays all the lists
     */
    private void listsCommand() {
        System.out.println("You have " + allLists.countLists() + " list(s):");
        displayAllLists();
        helpCommand();
    }

    /**
     * Allows adding words in a list. The user can create the list before adding words
     */
    private void addCommand() {
        ListWord list = selectList();
        if (list != null) {
            addWord(list);
        }
        else {
            System.out.println("This list doesn't exist!");
            addCommand();
        }
    }

    /**
     * Allows adding word and translation in a list
     * @param listWord
     */
    private void addWord(ListWord listWord) {
        System.out.println("Write your word:");
        String word = parser.write();

        if (word.trim().toUpperCase().equals("STOP")) {
            helpCommand();
        } else {
            System.out.println("Write your translation:");
            String translation = parser.write();

            Word completeWord = new Word(word, translation);

            if (listWord.findWord(word)) {
                System.out.println("This word already exists");
                addWord(listWord);
            } else {
                listWord.addWord(completeWord);
                System.out.println("You added this word: " + word);
                completeWord.getWordAsString();
                stopCommand();
                addWord(listWord);
            }
        }
    }

    /**
     * Displays all the words of a list. The user must write the name of the list before
     */
    private void wordsCommand() {
        ListWord list = selectList();

        if (list != null) {
            System.out.println(list.displayNumberOfWords());
            System.out.println(list.displayAllWords());
            helpCommand();
        } else {
            System.out.println("This list doesn't exist!");
            wordsCommand();
        }

    }

    /**
     * Removes a word. The user must write the name of the list before
     */
    private void removeCommand() {
        ListWord list = selectList();

        if (list != null) {
            System.out.println(list.displayAllWordsWithIndex());
            System.out.println("Choose a word: ");

            int index = -1;
            try {
                index = Integer.parseInt(parser.write()) -1;
            }
            catch (NumberFormatException e) {
                System.out.println("You must write a number!");
                removeCommand();
            }

            if (list.removeWord(index)) {
                System.out.println("The word was removed");
                helpCommand();
            } else {
                System.out.println("You must write the number of the word.");
                removeCommand();
            }
        } else {
            System.out.println("This list doesn't exist");
            removeCommand();
        }
    }
}
