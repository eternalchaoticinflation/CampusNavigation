package com.example.nothi.androidamenities;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.nothi.androidamenities.MainActivity.ACTIVITY_NAME;

public class NearestinCommons extends AppCompatActivity {
    private ListView printerListViewwc;

    ArrayList<String> printers =new ArrayList< >();

    ArrayAdapter<String> adapter;
    private Button wcfloorone;
    private Button wcfloortwo;
    private Button wcfloorthree;
    private Button wcfloorplan;
    private TextView status;
    private Button addprinter;
    private Button removeprinter;
    private int floortracker;

    private int positiontracker=0;//tracks postion to remove from list.
    private int deleteid;

    private EditText printerin;

    //store and edit information

    //to set picture
    SharedPreferences sharedInfo;//to set picture
    //first para, name of preference's file
    SharedPreferences.Editor editallInfo;

    public SQLiteDatabase fcDB;
    //private android.app.FragmentManager fmanager;
    //private FragmentTransaction transaction;
    private NearestinCommons.ChatAdapter messageAdapter;

    private ProgressBar progressBr;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearestin_commons);
        status=(TextView) findViewById(R.id.status);
        printerListViewwc= (ListView) findViewById(R.id.printerlistview);
        progressBr=(ProgressBar) findViewById(R.id.dataprogressBar);
        progressBr.setVisibility(View.VISIBLE);

        wcfloorone=(Button)findViewById(R.id.wc1);
        wcfloortwo=(Button)findViewById(R.id.wc2);
        wcfloorthree=(Button)findViewById(R.id.wc3);
        wcfloorplan=(Button)findViewById(R.id.wcfloor);
        floortracker=1;

        addprinter=(Button)findViewById(R.id.addPrbt);
        removeprinter=(Button)findViewById(R.id.removePrbt);


        //to set picture
        sharedInfo= getSharedPreferences("setPictureKey1", Context.MODE_PRIVATE);//to set picture
        //to set picture
        editallInfo= sharedInfo.edit();//edit into Info all the info

        /////////////////////////////
        //Database
        messageAdapter=new ChatAdapter(this);
        final AmenitiesDatabaseHelper dbToChat = new AmenitiesDatabaseHelper(this);
        //gets querys to help us       //now we can query     //cDB.rawQuery();
        //no  you need      //pies.add(cDB.rawQuery());   //it comes in a cusor
        fcDB = dbToChat.getWritableDatabase();



               // printers.add("C102, pages 66 black and white");



        GetprinterQuery letsrock = new  GetprinterQuery ();
        letsrock.execute();




        ////////////////////////////



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





        //printers =new ArrayList<>( Arrays.asList("C110","C112","C138","C148"));

       // adapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, printers);

        //printerListViewwc.setAdapter(adapter);//we do this to be able to notify change.

        printerListViewwc.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id){

                status.setText(printerValueMap.get(printers.get(position)));
                positiontracker=position;
                deleteid=(int)messageAdapter.getItemID(position);
                messageAdapter.getItemID(positiontracker);


                //Toast.makeText(getApplicationContext(), printers.get(position), Toast.LENGTH_SHORT).show();
            }

        });
        wcfloorone.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        //printers.clear();
                        floortracker=1;//floortracker assigned 1
                      //  printers.add("C110");
                       // printers.add("C112");
                       // printers.add("C138");
                      //  printers.add("C148");



                      //  adapter.notifyDataSetChanged();

                        Snackbar.make(findViewById(R.id.addPrbt), "Selected floor 1", Snackbar.LENGTH_LONG).setAction("Action", null).show();


                    }
                }

        );
        wcfloortwo.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        //printers.clear();
                        floortracker=2;//floortracker assigned 2

                        ///printers.add("C222");
                       // printers.add("C215");
                       // printers.add("C201");

                       // adapter.notifyDataSetChanged();
                        Snackbar.make(findViewById(R.id.addPrbt), "Selected floor 2", Snackbar.LENGTH_LONG).setAction("Action", null).show();



                    }
                }

        );
        wcfloorthree.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                       // printers.clear();
                        floortracker=3;//floortracker assigned 1
                       // printers.add("C322");
                       // printers.add("C314");
                       // printers.add("C308");
                        Snackbar.make(findViewById(R.id.addPrbt), "Selected floor 3", Snackbar.LENGTH_LONG).setAction("Action", null).show();



                        // adapter.notifyDataSetChanged();



                    }
                }

        );

        wcfloorplan.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        saveInfo(v);

                        String picturenumber=Integer.toString(floortracker);
                        Intent printeronfloor= new Intent("com.example.nothi.androidamenities.CommonsFloorplan");
                        printeronfloor.putExtra("floorkey", picturenumber);
                        startActivity(printeronfloor);//done




                    }
                }

        );

        addprinter.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {printerin=(EditText)findViewById(R.id.newprinterinput);
                        String messagePassed=printerin.getText().toString();

                        List<String> items = Arrays.asList(messagePassed.split("\\s*,\\s*"));

                        printers.add(messagePassed);
                      ///  printerValueMap.put(items.get(0), items.get(1));

                        ContentValues newvalues = new ContentValues();// you need a contentValues
                        newvalues.put(dbToChat.KEY_MESSAGE, messagePassed); //then put it in column name(key), then message(value);
                        fcDB.insertWithOnConflict(dbToChat.TABLE_CHAT, null, newvalues, SQLiteDatabase.CONFLICT_IGNORE);
                        printerin.setText("");

                        messageAdapter.notifyDataSetChanged();
                        Snackbar.make(findViewById(R.id.addPrbt), "Added PRINTER :"+messagePassed , Snackbar.LENGTH_LONG).setAction("Action", null).show();





                    }
                }

        );

        removeprinter.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {printerin=(EditText)findViewById(R.id.newprinterinput);



                        AlertDialog.Builder builder = new AlertDialog.Builder(NearestinCommons.this);
                        // 2. Chain together various setter methods to set the dialog characteristics
                        LayoutInflater dinflater = getLayoutInflater();
                        //final View vinflator=dinflater.inflate(R.layout.assistant_view, null);
                       // EditText assistantmessage=(EditText)vinflator.findViewById(R.id.asticon_edittext);


                        builder.setMessage(R.string.deleteprintermessage) //Add a dialog message to strings.xml
                                .setTitle(R.string.deleteprintermessage)
                                .setView(dinflater.inflate(R.layout.assistant_view, null))


                                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        fcDB.delete("tablechat","_id=? and chatcol=?",new String[]{Integer.toString(deleteid), printers.get(positiontracker) });

                                        //^above command is like
                                        //db.delete("tablename","id=? and name=?",new String[]{"1","jack"});
                                        //delete from tablename where id='1' and name ='jack'
                                        //DO NOT USE cDB.rawQuery("DELETE FROM tablechat WHERE chatcol = ?", new String[]{ pies.get(positionlatch)});

                                        printers.remove(positiontracker);
                                        messageAdapter.notifyDataSetChanged();

                                        onResume(); // User clicked OK button
                                    }
                                })
                                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // User cancelled the dialog
                                        onResume();
                                    }
                                })
                                .show();






                    }
                }

        );





    }//OnCreate

    public void saveInfo(View view){
        //takes view to get infoz


        editallInfo.putString("pictureKey", Integer.toString(floortracker) );//from coordinatkey
        //passes same coordinate to all activities

        editallInfo.commit();//this edits all info
       // Toast.makeText(this, "commit successful, finding nearest amenity!", Toast.LENGTH_LONG).show();

    }


    class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        @Override
        public int getCount() {
            return printers.size();
        }
        @Override
        public String getItem(int position) {
            return printers.get(position);
        }//alreadydone

        public long getItemID(int position) {
            //pies.get(position) is a STRING and it is the message on display that is clicked AND it is
            //the message in the database. cDB is the database.
            //change query for custom query
            Cursor cursorgetId=fcDB.rawQuery("SELECT _id, chatcol FROM tablechat WHERE chatcol = ?", new String[]{ printers.get(position)});
            int theid= 0;
            //while is good to not get -1 null point exception
            while (!cursorgetId.isAfterLast()) {
                if (cursorgetId.moveToFirst()) {
                    Log.i(ACTIVITY_NAME, "CursorIdIDDDDD’s  total count is = " + cursorgetId.getCount());
                    //Cursor’s  total count is = 4
                    do {
                        theid=Integer.valueOf(cursorgetId.getString(cursorgetId.getColumnIndex("_id")));
                        Log.i(ACTIVITY_NAME, "in getItemID SQL COLUMN ID is:" +
                                cursorgetId.getString(cursorgetId.getColumnIndex("_id") )

                                //it expects a table [_id, chatcol], which is [0, 1]

                         );

                        Log.i(ACTIVITY_NAME, "SQL MESSAGE now : " +
                                cursorgetId.getString(cursorgetId.getColumnIndex("chatcol")
                                        //it expects a table [_id, chatcol], which is [0, 1]

                                ));
                        //Toast.makeText(getApplicationContext(), "rrr is RRRRR "+cursor.getString(cursor.getColumnIndex(dbToChat.KEY_ID)), Toast.LENGTH_LONG).show();
                        //SQL MESSAGE:rddhjjjj and SQL MESSAGE:dfhhhClick to Chat, SQL MESSAGE:ddffgghh
                        //String td=cursorgetId.getString(cursorgetId.getColumnIndex("_id"));
                        Log.i(ACTIVITY_NAME, " getItemID Cursor’s table's column count = " + cursorgetId.getColumnCount());
                        // Toast.makeText(getApplicationContext(), td, Toast.LENGTH_LONG).show();

                    } while (cursorgetId.moveToNext());
                }
            }



            //cursorgetId.getString(cursorgetId.getColumnIndex("chatcol"));
            return theid;

        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = NearestinCommons.this.getLayoutInflater();
            View result = null;

            result = inflater.inflate(R.layout.printer_row_incoming, null);


            TextView message = (TextView) result.findViewById(R.id.message_text);
            message.setText(getItem(position)); // get the string at position
            return result;


        }

    }///ChatAdapter


    class GetprinterQuery extends AsyncTask<String, Integer, String> {



        @Override
        protected void onPreExecute() {//on UI
            //could be a progress Bar, to indicate works is loading or being done
            //SHOW the progressbar
            progressBr.setVisibility(View.VISIBLE);

        }


        @Override
        protected void onPostExecute(String result) {//on UI
            //both pre and run have access to view in activity.
            // but actually progressbar is hidden on postExecute.

            progressBr.setVisibility(View.INVISIBLE);
            printerListViewwc.setAdapter(messageAdapter);//we do this to be able to notify change.

        }

        @Override
        protected void onProgressUpdate(Integer... value) {
            progressBr.setVisibility(View.VISIBLE);//is now Vis

            //researt to 0
            progressBr.setProgress(value[0]);

            Log.i(ACTIVITY_NAME, "We have updated");

        }


        @Override
        protected String doInBackground(String... params) {



            Cursor cursor = fcDB.rawQuery("SELECT _id, chatcol FROM tablechat ", null);
            publishProgress(25);
            SystemClock.sleep(1000);
            while (!cursor.isAfterLast()) {
                if (cursor.moveToFirst()) {

                    do {
                        Log.i(ACTIVITY_NAME, "SQL MESSAGE:" +
                                cursor.getString(cursor.getColumnIndex("chatcol")
                                        //it expects a table [_id, chatcol], which is [0, 1]

                                ));
                        //Toast.makeText(getApplicationContext(), "rrr is RRRRR "+cursor.getString(cursor.getColumnIndex(dbToChat.KEY_ID)), Toast.LENGTH_LONG).show();
                        //SQL MESSAGE:rddhjjjj and SQL MESSAGE:dfhhhClick to Chat, SQL MESSAGE:ddffgghh
                        printers.add( cursor.getString(cursor.getColumnIndex("chatcol")));
                        Log.i(ACTIVITY_NAME, "Cursor’s table's column count = " + cursor.getColumnCount());

                    } while (cursor.moveToNext());
                }
            }
            publishProgress(75);
            SystemClock.sleep(500);
            publishProgress(100);



            return null;}
    }


}
