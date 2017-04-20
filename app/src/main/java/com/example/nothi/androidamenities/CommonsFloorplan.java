package com.example.nothi.androidamenities;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommonsFloorplan extends AppCompatActivity {
    ListView floorListView;
    ArrayList<String> floors = new ArrayList< >();
    ArrayAdapter<String> adapter;
    int decider=0;
    private android.app.FragmentManager fmanager;//this interacts with fragment
    //NOT import android.support.v4.app.FragmentManager; RATHER the android
    private FragmentTransaction transaction;

    private Moredetails mdetails;
    private ImageView floorimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commons_floorplan);

        Intent listIn=getIntent();
        String messagePassed=listIn.getStringExtra("floorkey");
        Toast.makeText(getApplicationContext(), "You chose picture: "+ messagePassed, Toast.LENGTH_LONG).show();

        floorListView = (ListView) findViewById(R.id.printercommonsfloorList);
        decider=Integer.parseInt(messagePassed);

        fmanager=this.getFragmentManager();
        mdetails= new Moredetails();
        transaction=fmanager.beginTransaction();
        transaction.add(R.id.printerpicture, mdetails, "messagedetailsfrag");
        //commit
        transaction.commit();
        floorimage=(ImageView)findViewById(R.id.imageofFloor);


        final Map<String, String > printerValueMap=new HashMap<>();
        printerValueMap.put("C110", "Black and White only 500 pages queue: 20");
        printerValueMap.put("C112", "Black and White only 255 pages queue: 0");
        printerValueMap.put("C138", "Colour 58 pages, queue: 10");
        printerValueMap.put("C148", "Colour 670 pages, queue: 6");
        /////////////////
        printerValueMap.put("C222", "Colour 70 pages, queue: 7");
        printerValueMap.put("C215", "Colour 6 pages, queue: 0");
        printerValueMap.put("C201", "Colour 0 pages, queue: 14");
        /////
        printerValueMap.put("C314", "Black and White only 570 pages, queue: 0");
        printerValueMap.put("C322", "Colour 1246 pages, queue: 0");
        printerValueMap.put("C308", "Colour 518 pages, queue: 4");

        switch (decider){
            case 1:

                floors =new ArrayList<>( Arrays.asList("C110 Black and White only 500 pages queue: 20",
                "C112 Black and White only 255 pages queue: 0","C138 Colour 58 pages, queue: 10","C148 Colour 670 pages, queue: 6"));
                break;
            case 2:

                floors =new ArrayList<>( Arrays.asList("C222 Colour 70 pages, queue: 7",
                        "C215 Colour 6 pages, queue: 0","C138 Colour 58 pages, queue: 10","C201 Colour 0 pages, queue: 14"));
                floorimage.setImageResource(R.drawable.commonsfloor2);
                break;
            case 3:

                floors =new ArrayList<>( Arrays.asList("C314 Black and White only 570 pages, queue: 0",
                        "C322 Colour 1246 pages, queue: 0","Colour 518 pages, queue: 4"));
                floorimage.setImageResource(R.drawable.commonsfloor3);
                break;
        }

        adapter =new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, floors);
        floorListView.setAdapter(adapter);

    }

    public void destroyFragment(){
        transaction=fmanager.beginTransaction();
        //pies.remove(positionlatch);
       // messageAdapter.notifyDataSetChanged();
        Moredetails des=(Moredetails)fmanager.findFragmentByTag("messagedetailsfrag");
        if(des!=null){
            transaction.remove(des);
            transaction.commit();
            //fragmentdead=true;//fragment is dead
        }
    }


}
