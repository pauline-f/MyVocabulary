package com.paulinefeytel.model;

import java.util.Random;

public class QuizzWordTranslation extends Quizz {

    public QuizzWordTranslation(ListWord list) {
        this.listWord = list;
    }

    @Override
    public String displayWord() {
        Random randomWord = new Random();
        int index= randomWord.nextInt(listWord.numberOfWords());
        currentWord = listWord.getAWord(index);
        return currentWord.getWord();
    }

    @Override
    public String goodAnswer() {

        return currentWord.getTranslation();
    }

    @Override
    public Boolean correctAnswer(String input) {

        return currentWord.getTranslation().equals(input);
    }
    
}
