package com.example.updadtednotebook;

// import com.example.updadtednotebook.Storage.FileStorage;

import java.util.ArrayList;


public class Data {
    public final ArrayList<String> notes = new ArrayList<>();
    public ArrayList<Note> noteObjects = new ArrayList<>();

    private Data(){}
    public static Data getInstance(){
        if(instance == null){
            instance = new Data();
        }
        return instance;
    }
    private static Data instance;
}
