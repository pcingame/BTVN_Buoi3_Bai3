package com.example.btvn_buoi3_bai3.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.btvn_buoi3_bai3.File;

@Database(entities = {File.class}, version = 1)
public abstract class FileDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "file.db";
    private static FileDatabase instance;

    public static synchronized FileDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), FileDatabase.class, DATABASE_NAME).
                    allowMainThreadQueries()
                    .build();

        }
        return instance;
    }

    public abstract FileDAO fileDAO();
}
