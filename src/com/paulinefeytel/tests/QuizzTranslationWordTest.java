package com.paulinefeytel.tests;

import com.paulinefeytel.model.ListWord;
import com.paulinefeytel.model.Quizz;
import com.paulinefeytel.model.QuizzTranslationWord;
import com.paulinefeytel.model.Word;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuizzTranslationWordTest {
    ListWord list;

    @Before
    public void setUp() {
        Word word1 = new Word("Blue", "Blå", 0, 0);
        list = new ListWord("Colors");
        list.addWord(word1);
    }

    @Test
    public void displayWord() {
        Quizz quizz = new QuizzTranslationWord(list);
        assertEquals("Blå", quizz.displayWord());
    }

    @Test
    public void getGoodAnswer() {
        Quizz quizz = new QuizzTranslationWord(list);
        quizz.displayWord();
        assertEquals("Blue", quizz.getGoodAnswer());
    }

    @Test
    public void isGoodAnswer() {
        Quizz quizz = new QuizzTranslationWord(list);
        quizz.displayWord();
        assertTrue(quizz.isGoodAnswer("Blue"));
        assertFalse(quizz.isGoodAnswer("Röd"));
    }

}