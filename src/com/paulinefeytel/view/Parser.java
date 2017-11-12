package com.paulinefeytel.view;

import java.util.Scanner;


public class Parser {
    private Scanner reader;

    /**
     * Create a parser to read from the terminal window.
     */
    public Parser() {
        reader = new Scanner(System.in);
    }

    public String write() {
        String inputLine;
        System.out.print("> ");
        inputLine = reader.nextLine();
        return inputLine.trim();
    }

    public static class Command {
        private String input;
        private String[] words;

        public Command(String input) {
            this.input = input.trim();
            words = input.split(" ");
        }

        public String getInput() {
            return input;
        }

        public void setInput(String input) {
            this.input = input;
        }


        public String firstWordCommand() {
            String first = words[0].toUpperCase();
            return first;
        }
    }
}