package com.example.updadtednotebook;

import java.io.Serializable;

public class Note implements Serializable {
    private String headline;
    private String note;
    private int position;
    private String doocumentID;

    public Note(String headline, String note) {
        this.headline = headline;
        this.note = note;
    }

    public String getHeadline() {
        return headline;
    }

    public String getNote() {
        return note;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getDoocumentID() {
        return doocumentID;
    }

    public void setDoocumentID(String doocumentID) {
        this.doocumentID = doocumentID;
    }


}
