package com.heri.firebaseandroidcodelab.activity;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.heri.firebaseandroidcodelab.R;
import com.heri.firebaseandroidcodelab.model.JournalEntry;
import com.heri.firebaseandroidcodelab.model.SampelData;
import com.heri.firebaseandroidcodelab.model.Tag;
import static android.R.attr.category;

import java.util.ArrayList;
import java.util.List;

public class JournalListActivity extends AppCompatActivity {

    private String LOG_TAG = "Ada error";
    private DatabaseReference dbreference;
    private DatabaseReference journalCloudEndPoint;
    private DatabaseReference tagCloudEndPoint;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal_list);
        dbreference = FirebaseDatabase.getInstance().getReference();
        journalCloudEndPoint = dbreference.child("journalentris");
        tagCloudEndPoint = dbreference.child("tags");
        journalCloudEndPoint.setValue("Halo ada").addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(LOG_TAG, e.getLocalizedMessage());
            }
        });
    }
    private void addInitialDataToFirebase(){
        List<JournalEntry> journalEntries = SampelData.getSampleJournalEntries();

        for (JournalEntry journalEntry: journalEntries){
            String key = journalCloudEndPoint.push().getKey();
            journalEntry.setJournalId(key);
            journalCloudEndPoint.child(key).setValue(journalEntry);
        }
        List<String> tagname = SampelData.getSampleTags();
        for (String nama: tagname){
            String tag = tagCloudEndPoint.push().getKey();

            Tag taginit = new Tag();
            taginit.setTagName(nama);
            taginit.setTagId(tag);
            tagCloudEndPoint.child(taginit.getTagId()).setValue(category);
        }
    }
}
