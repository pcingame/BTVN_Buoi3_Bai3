package com.example.btvn_buoi3_bai3;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "File")
public class File {

    @PrimaryKey (autoGenerate = true)
    private int id;

    private String Name;

    public File(String name) {
        Name = name;
    }

    public File() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
