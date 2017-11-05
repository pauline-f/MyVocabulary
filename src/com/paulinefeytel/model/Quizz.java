package com.paulinefeytel.model;

public abstract class Quizz {

    ListWord listWord;
    int lastIndex = -1;

    public abstract String displayWord();
    public abstract Boolean correctAnswer(String input);
    public abstract String goodAnswer();

    public void setCount() {
        listWord.getAWord(lastIndex).incrementCount();
    }

    public void setScore() {
        listWord.getAWord(lastIndex).incrementScore();
    }
}
