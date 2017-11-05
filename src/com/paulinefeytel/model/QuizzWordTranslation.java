package com.paulinefeytel.model;

import java.util.Random;

public class QuizzWordTranslation extends Quizz {

    ListWord listWord;
    int lastIndex = -1;

    public QuizzWordTranslation(ListWord list) {
        this.listWord = list;
    }

    @Override
    public String displayWord() {
        Random randomWord = new Random();
        lastIndex= randomWord.nextInt(listWord.numberOfWords());
        return listWord.getWord(lastIndex);
    }

    @Override
    public String goodAnswer() {
        return listWord.getTranslation(lastIndex);
    }

    @Override
    public Boolean correctAnswer(String input) {
        return listWord.getTranslation(lastIndex).equals(input);
    }

}
