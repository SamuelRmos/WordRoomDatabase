package com.example.roomwordsample.Room

import androidx.room.RoomDatabase

public abstract class WordRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    companion object{
        /**
         * Singleton prevents multiple instances of database opening at the
         * same time.
         */

    }
}