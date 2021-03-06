/*
 * Filename: MicroActivity.java
 * Author: Scott McClare
 * Course: CST2335 - Graphical Programming
 * Term Project
 * Date: April 20, 2017
 * Professor: Eric Torunski
 * Purpose: This Activity presents a list of microwave ovens on campus, with the options to add
 * new items to the list. Clicking on a list item will launch a MicroDetails activity with further
 * information about the microwave. If the user clicks a "Delete This Microwave" oven button in that
 * activity, it is removed from the list.
 */


package com.example.nothi.androidamenities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * This class launches an Activity presenting a list of microwave ovens on campus, with the options
 * to add new items to the list. Clicking on a list item will launch a MicroDetails activity with
 * further  * information about the microwave. If the user clicks a "Delete This Microwave" oven
 * button in that  * activity, it is removed from the list.
 * @author    Scott McClare
 * @version   1.0.0 April 20, 2017
 * @since 1.8.0_112
 */
public class NearMicrowave extends AppCompatActivity {

    /**
     * The name of this activity
     */
    public static final String ACTIVITY_NAME = "NearMicrowave";
    /**
     * Button to add new microwave items to the list
     */
    Button addButton;
    /**
     * This Listview container contains the ArrayList microwave
     */
    ListView list;
    /**
     * List of microwave ovens available. Currently hard-coded but in future iterations will be
     * populated from a list downloaded from the Internet.
     */
    ArrayList<String> microwaves;
    /**
     * The ArrayAdapter to put the ArrayList into the ListView
     */
    ArrayAdapter<String> a;
    /**
     * Text field allowing the user to add items to the list
     */
    EditText addMicro;
    /**
     * The outgoing request code for startActivityForResult calls
     */
    int requestCode = 100;

    /**
     * Creates the activity NearMicrowave.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_microwave);

        list = (ListView) findViewById(R.id.microwaveList); // the ListView resource on the layout
        addMicro = (EditText) findViewById(R.id.addMicrowave); // the EditText resource on the layout

        addButton = (Button) findViewById(R.id.addButton); // Add button
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // on click, add the contents of the EditText to the ArrayList and display it.
                microwaves.add(addMicro.getText().toString());
                a.notifyDataSetChanged();
                addMicro.setText("");
                // Notify the user that the add was successful
                Toast clickAdd = Toast.makeText(getBaseContext(), "Item added", Toast.LENGTH_SHORT);
                clickAdd.show();
            }
        });

        // Populate the list of microwave; currently called from a dummy method but will be
        // downloadable content in the future.
        microwaves = new ArrayList<>();
        populateList();
        a = new ArrayAdapter(this, android.R.layout.simple_list_item_1, microwaves);
        list.setAdapter(a);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // Create an Intent and add the clicked message and its ID to its Bundle
                // then launch the MicroDetails activity
                Intent intent = new Intent(NearMicrowave.this, MicroDetails.class);
                intent.putExtra("microId", l);
                intent.putExtra("microText", (String) adapterView.getItemAtPosition(i));
                startActivityForResult(intent, requestCode);

            }
        });
    }

    /**
     * When the user clicks the delete button in MicroDetails activity, delete the corresponding
     * microwave entry.
     * @param requestCode The sending activity
     * @param resultCode Result code
     * @param data the Intent containing the ID of the entry to remove
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK ) {
            int microId = (int) data.getExtras().getLong("microId");
            microwaves.remove(microId);
            a.notifyDataSetChanged();
        }
    }

    /**
     * Placeholder method to simulate a downloadable list of microwave. To be replaced by an actual
     * download and AsyncTask.
     */
    private void populateList() {
        microwaves.add("B Building - near B182");
        microwaves.add("D Building - in main cafeteria");
        microwaves.add("T Building - near T119");
        microwaves.add("E Building - vending machine room on 2nd floor");
    }

}
