package com.example.updadtednotebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.updadtednotebook.Storage.FireStorage;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    FireStorage fs = new FireStorage();
    private List<String> notes = Data.getInstance().notes;
    private List<Note> noteObjects = Data.getInstance().noteObjects;
    private ListView listView;
    private ArrayAdapter adapterArray;
    private String selected_note = "";
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //fs.startNoteListener();
        //fs.getListItem();


        listView = (ListView) findViewById(R.id.listview);
        adapterArray = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,notes);
        listView.setAdapter(adapterArray);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected_note = notes.get(position);
                edit(view);
            }
        });
    }
    protected void onResume() {
        super.onResume();
        adapterArray.notifyDataSetChanged();
    }

    public void getNoteView(View view) {
        Intent intent = new Intent(this, NoteView.class);
        startActivity(intent);
    }
    public void edit(View view){
        Intent intent = new Intent(this, NoteView.class);
        if (notes.contains(selected_note)){
            for(int i = 0;i<noteObjects.size();i++){
                if(selected_note == noteObjects.get(i).getHeadline()){
                    noteObjects.get(i).setPosition(i);
                    bundle = new Bundle();
                    bundle.putSerializable("noteKey",noteObjects.get(i));

                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }
        }
        else{
            // Do nothing
        }
    }

}
