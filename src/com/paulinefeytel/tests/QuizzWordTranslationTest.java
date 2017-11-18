package com.paulinefeytel.tests;

import com.paulinefeytel.model.ListWord;
import com.paulinefeytel.model.Quizz;
import com.paulinefeytel.model.QuizzWordTranslation;
import com.paulinefeytel.model.Word;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuizzWordTranslationTest {
    ListWord list;

    @Before
    public void setUp() {
        Word word1 = new Word("Blue", "Blå", 0, 0);
        list = new ListWord("Colors");
        list.addWord(word1);
    }

    @Test
    public void displayWord() {
        Quizz quizz = new QuizzWordTranslation(list);
        assertEquals("Blue", quizz.displayWord());
    }

    @Test
    public void getGoodAnswer() {
        Quizz quizz = new QuizzWordTranslation(list);
        quizz.displayWord();
        assertEquals("Blå", quizz.getGoodAnswer());
    }

    @Test
    public void isGoodAnswer() {
        Quizz quizz = new QuizzWordTranslation(list);
        quizz.displayWord();
        assertTrue(quizz.isGoodAnswer("Blå"));
        assertFalse(quizz.isGoodAnswer("Gul"));
    }
}