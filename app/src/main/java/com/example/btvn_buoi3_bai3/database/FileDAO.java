package com.example.btvn_buoi3_bai3.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.btvn_buoi3_bai3.File;

import java.util.List;

@Dao
public interface FileDAO {
    @Insert
    void insertFile(File file);

    @Query("SELECT * FROM File")
    List<File> getListFile();

    @Delete
    void deleteFile(File file);

    @Query("Update File set name = '' where name = :Name ")
    void updateFileName(String Name);

    /*@Update
    void updateFileName(File file);*/
}
