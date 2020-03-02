package com.example.updadtednotebook.Storage;

import android.util.Log;

import androidx.annotation.Nullable;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class FireStorage {

    private final static String note = "notes";
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public void startNoteListener() {
        db.collection(note).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot values, @Nullable FirebaseFirestoreException e) {
                if (e!=null){
                    Log.d("TAG","Error:"+e.getMessage());
                }
                else {
                    for(DocumentSnapshot snap :values.getDocuments()){
                        Log.i("all","read from FB"+ snap.get("head").toString() + " " + snap.get("body").toString());
                }
                }
            }
        });
    }

    public void addNewNote(String headline, String body) {
        DocumentReference docRef = db.collection(note).document();
        Map<String, String> map = new HashMap<>();
        map.put("head", headline);
        map.put("body", body);
        docRef.set(map);

    }

    public void editNote(String headline, String body, String path) {
        DocumentReference docRef = db.collection(note).document(path);
        Map<String, String> map = new HashMap<>();
        map.put("head", headline);
        map.put("body", body);
        docRef.set(map);
    }

    public void deleteNote(String path) {
        DocumentReference docRef = db.collection(note).document(path);
        docRef.delete();
    }

}
