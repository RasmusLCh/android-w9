package com.example.updadtednotebook;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.updadtednotebook.Storage.FireStorage;

import java.util.ArrayList;

// import com.example.updadtednotebook.Storage.FileStorage;


public class NoteView extends AppCompatActivity {
    FireStorage fs = new FireStorage();
    private String headline;
    private String body;
    private EditText noteHeadline;
    private EditText noteBody;
    private Note note;
    private Note editNote;
    private ArrayList<String> notes = Data.getInstance().notes;
    private ArrayList<Note> noteObjects = Data.getInstance().noteObjects;
    private Boolean edit_state = false;

    private EditText editText_Headline;
    private EditText editText_Body;

    // private FileInputStream fis;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        // fs.startNoteListener();


        noteHeadline = findViewById(R.id.editText);
        noteBody = findViewById(R.id.editText2);
        editText_Headline = findViewById(R.id.editText);
        editText_Body = findViewById(R.id.editText2);

        Intent i = getIntent();
        Bundle bundle = i.getExtras();

        if(bundle != null){
            try{
                edit_state = true;
                editNote = (Note)bundle.getSerializable("noteKey");
                editText_Headline.setText(editNote.getHeadline());
                editText_Body.setText(editNote.getNote());
            }catch (Exception e){
                System.out.println(e);
            }
        }
    }

    public void saveClick(View view) {
        headline = noteHeadline.getText().toString();
        body = noteBody.getText().toString();
        note = new Note(headline,body);
        if(edit_state == true){
            noteObjects.set(editNote.getPosition(),note);
            notes.set(editNote.getPosition(),note.getHeadline());

          // Can only add for now
            fs.addNewNote(note.getHeadline(),note.getNote());
            //fs.editNote(note.getHeadline(),note.getNote(),note.getDoocumentID());
            edit_state = false;
        }else{
            noteObjects.add(note);
            notes.add(note.getHeadline());
            fs.addNewNote(note.getHeadline(),note.getNote());
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
