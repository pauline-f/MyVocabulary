package com.paulinefeytel.model;

import java.util.Random;

public class QuizzTranslationWord extends Quizz {

    ListWord listWord;
    int lastIndex = -1;

    public QuizzTranslationWord(ListWord list) {

        this.listWord = list;
    }

    @Override
    public String displayWord() {
        Random randomWord = new Random();
        lastIndex= randomWord.nextInt(listWord.numberOfWords());
        return listWord.getTranslation(lastIndex);
    }

    @Override
    public String goodAnswer() {
        return listWord.getWord(lastIndex);
    }

    @Override
    public Boolean correctAnswer(String input) {
        return listWord.getWord(lastIndex).equals(input);
    }



}
