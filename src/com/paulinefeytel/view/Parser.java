package com.paulinefeytel.view;

import java.util.Scanner;

/**
 * Allow reading from the terminal window
 *
 */
public class Parser {
    private Scanner reader;
    private String[] words;

    public Parser() {
        reader = new Scanner(System.in);
    }

    public String write() {
        String inputLine;
        System.out.print("> ");
        inputLine = reader.nextLine();
        return inputLine.trim();
    }

    public String command(String input) {
        String inputCommand = input.trim();
        words = inputCommand.split(" ");
        return words[0].toUpperCase();
    }
}