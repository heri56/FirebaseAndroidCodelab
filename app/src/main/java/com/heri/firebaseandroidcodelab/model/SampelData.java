package com.heri.firebaseandroidcodelab.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by heri on 12/21/17.
 */

public class SampelData {
    public  static List<String> getSampleTags(){
        List<String> tagnama = new ArrayList<>();

        tagnama.add("Family");
        tagnama.add("World");
        tagnama.add("Productivity");
        tagnama.add("Personal");

        return  tagnama;
    }

    public static List<JournalEntry> getSampleJournalEntries() {
        List<JournalEntry> journalEntries = new ArrayList<>();

        JournalEntry journalEntry = new JournalEntry();

        journalEntry.setTitle("DisneyLand Trip");
        journalEntry.setContent("We want to Disneyland and the kids had lots of fun!");

        Calendar calendar = GregorianCalendar.getInstance();
        journalEntry.setDateModified(calendar.getTimeInMillis());
        journalEntries.add(journalEntry);

        return journalEntries;
    }
}
