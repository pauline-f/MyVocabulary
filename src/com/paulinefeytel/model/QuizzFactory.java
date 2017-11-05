package com.paulinefeytel.model;

public class QuizzFactory {
    public static Quizz getQuizz(String input, ListWord list) {
        switch (input) {
            case "1":
                return new QuizzWordTranslation(list);
            case "2":
                return new QuizzTranslationWord(list);
        }
        throw new IllegalArgumentException("This quizz doesn't exist!");
    }
}