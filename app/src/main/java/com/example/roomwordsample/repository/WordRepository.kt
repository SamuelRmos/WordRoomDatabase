package com.example.roomwordsample.repository

import androidx.lifecycle.LiveData
import com.example.roomwordsample.room.Word
import com.example.roomwordsample.room.WordDao

class WordRepository(private val wordDao: WordDao) {

    /**
     * Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     */
    val allWords: LiveData<List<Word>> = wordDao.getAlphabetizedWords()

    suspend fun insert (word: Word){
        wordDao.insert(word)
    }
}