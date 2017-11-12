package com.paulinefeytel.model;

import java.util.Random;

public class QuizzTranslationWord extends Quizz {

    public QuizzTranslationWord(ListWord list) {
        this.listWord = list;
    }

    @Override
    public String displayWord() {
        Random randomWord = new Random();
        int index= randomWord.nextInt(listWord.numberOfWords());
        currentWord = listWord.getAWord(index);
        return currentWord.getTranslation();
    }

    @Override
    public String goodAnswer() {
        return currentWord.getWord();
    }

    @Override
    public Boolean correctAnswer(String input) {

        return currentWord.getWord().equals(input);
    }


}
