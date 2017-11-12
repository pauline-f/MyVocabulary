package com.paulinefeytel.model;

/**
 *  This abstract class allows to have methods about quizz: display a word, check the answer, display the good answer and
 */
public abstract class Quizz {
    ListWord listWord;
    Word currentWord;

    public abstract String displayWord();
    public abstract Boolean correctAnswer(String input);
    public abstract String goodAnswer();
    public void setCount() {

        currentWord.incrementCount();
    }

    public void setScore() {

        currentWord.incrementScore();
    }
}
