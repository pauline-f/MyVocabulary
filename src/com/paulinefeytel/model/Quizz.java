package com.paulinefeytel.model;

/**
 *  This abstract class allows to have methods about quizz: display a word, check the answer, display the good answer and
 */
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
