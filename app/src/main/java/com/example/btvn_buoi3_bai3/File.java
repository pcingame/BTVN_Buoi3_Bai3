package com.example.btvn_buoi3_bai3;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "File")
public class File implements Serializable {

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

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                '}';
    }
}
