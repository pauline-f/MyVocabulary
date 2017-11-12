package com.paulinefeytel.controller;

import com.paulinefeytel.model.*;
import com.paulinefeytel.model.FileManager;
import com.paulinefeytel.view.Parser;

public class App {
    AllLists allLists;
    Quizz quizz;
    private Parser parser;
    public FileManager fileManager;

    public App() {
        allLists = new AllLists();
        parser = new Parser();
        fileManager = new FileManager();
    }

    public void displayAllLists() {
        allLists.displayAllLists();
    }

    public ListWord createNewList(String name) {
        ListWord list = new ListWord(name);
        allLists.addList(list);
        return list;
    }

    /**
     * Messages for command line and to manage the commands.
     */
    public void msgWelcome() {
        allLists = fileManager.readFile();
        System.out.println("Welcome! With this app, you can create allLists for learn new vocabulary in a new language and try to remember them with quizzs.");
        System.out.println("For the moment, you have " + allLists.lists.size() + " list(s).");
        System.out.println("Write 'HELP' if you need more informations.");
        command(parser.write());
    }


    public void command(String input) {
        Parser.Command command = new Parser.Command(input);
        String first = command.firstWordCommand();

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
                fileManager.saveCommand(allLists);
                break;
            case "QUIZZ":
                quizzCommand();
                break;
            default:
                System.out.println("This command doesn't work!");
                helpCommand();
                break;
        }
    }

    private void quizzCommand() {
        System.out.println("Choose your quizz:");
        System.out.println("Write '1' if you want to write the translation of a word");
        System.out.println("Write '2' if you want to write the word of a translation");
        String input = (parser.write());

        if (input.equals("1") || input.equals("2")) {

            ListWord list = selectList();
            if (list != null) {
                stopCommand();
                Quizz quizz = QuizzFactory.getQuizz(input, list);
                quizz(quizz);
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

    public void quizz(Quizz quizz) {

        String wordQuizz = quizz.displayWord();
        System.out.println(wordQuizz);

        String input = parser.write();

        if (input.toUpperCase().equals("STOP")) {
            helpCommand();
        }
        else if (quizz.correctAnswer(input)) {
            System.out.println("Good Answer!");
            quizz.setCount();
            quizz.setScore();
            quizz(quizz);
        }
        else {
            System.out.println("The answer was: " + quizz.goodAnswer());
            quizz.setCount();
            quizz(quizz);
        }


    }

    public void scoreCommand() {
        ListWord list = selectList();

        list.displayAllWordWithScore();
    }

    public ListWord selectList() {
        System.out.println("Choose a list:");
        displayAllLists();

        String inputList = parser.write();
        return allLists.findAList(inputList);

    }

    public void helpCommand() {
        System.out.println("Write 'CREATE' if you want create a list.");
        System.out.println("Write 'LISTS' if you want display all the allLists.");
        System.out.println("Write 'ADD' if you want add words in a list.");
        System.out.println("Write 'REMOVE' if you want remove a word in a list.");
        System.out.println("Write 'WORDS' if you want display all words in a list.");
        System.out.println("Write 'QUIZZ' if you want display a quizz.");
        System.out.println("Write 'SCORE' if you want display your score." );
        System.out.println("Write 'SAVE' if you want to save the data in a fileManager.");
        System.out.println("Write 'QUIT' if you want to quit the application.");
        command(parser.write());
    }

    public void stopCommand() {

        System.out.println("Write 'STOP' if you are finish.");
    }

    public void createCommand() {
        System.out.println("Write the name of your list: ");
        createNewList(parser.write());
        helpCommand();
    }

    public void listsCommand() {
        System.out.println("You have " + allLists.countLists() + " list(s):");
        displayAllLists();
        command(parser.write());
    }


    public void addCommand() {
        ListWord list = selectList();
        if (list != null) {
            addWord(list.getName());
        }
        else {
            System.out.println("This list doesn't exist!");
            addCommand();
        }
    }

    public void addWord(String list) {
        System.out.println("Write your word:");
        String word = parser.write();

        if (word.trim().toUpperCase().equals("STOP")) {
            helpCommand();
        }
        else {
            System.out.println("Write your translation:");
            String translation = parser.write();

            Word completeWord = new Word(word, translation, 0, 0);

            ListWord listWord = allLists.findAList(list);
            listWord.addWord(completeWord);

            System.out.println("You added this word: ");
            completeWord.displayWord();
            stopCommand();
            addWord(list);
        }
    }

    public void wordsCommand() {

        ListWord list = selectList();

        list.displayNumberOfWords();
        list.displayAllWords();

        helpCommand();
    }

    public void removeCommand() {
        ListWord list = selectList();

        list.displayAllWordsWithIndex();

        System.out.println("Choose a word: ");

        int index = Integer.parseInt(parser.write()) -1;


        if (list.removeWord(index)) {
            helpCommand();
        }
        else {
            removeCommand();
        }
    }

}
