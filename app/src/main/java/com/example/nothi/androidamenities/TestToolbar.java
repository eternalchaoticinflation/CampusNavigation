package com.example.nothi.androidamenities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class TestToolbar extends AppCompatActivity {
    FloatingActionButton fab;

    String iconthreemess;
    EditText assistantmessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        assistantmessage= (EditText)findViewById(R.id.asticon_edittext);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "<no escape\"&gt;\">", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu (Menu m){

        //Kind of like
        // View customfragview=inflater.inflate(R.layout.activity_message_details, (ViewGroup)containerfromVG, false);
        this.getMenuInflater().inflate(R.menu.toolbar_menu, m );
        //this is my toolbar

        return true;



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem mioption){
        //some menu item is clicked or selected
        //assign it to any id
        int menuoptionid= mioption.getItemId();


        switch( menuoptionid ){
            case R.id.action_commons:

                Intent goingtoWC= new Intent("com.example.nothi.androidamenities.NearestinCommons");

                startActivity(goingtoWC);//done

                break;
            case R.id.action_techbuilding:
                Intent goingtoWB= new Intent("com.example.nothi.androidamenities.NearestinBusiness");

                startActivity(goingtoWB);//done


                break;
            case R.id.action_assistant:

                AlertDialog.Builder builder = new AlertDialog.Builder(TestToolbar.this);
                // 2. Chain together various setter methods to set the dialog characteristics
                LayoutInflater dinflater = getLayoutInflater();
                final View vinflator=dinflater.inflate(R.layout.assistant_view, null);
                //TextView amessage=(TextView) vinflator.findViewById(R.id.a_edittext);



                builder.setMessage(R.string.helpprintermessage) //Add a dialog message to strings.xml
                        .setTitle(R.string.astprintermessage)
                        .setView(dinflater.inflate(R.layout.help_view, null))


                        .setPositiveButton(R.string.okhelp, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent goingtoWC= new Intent("com.example.nothi.androidamenities.NearestinCommons");

                                startActivity(goingtoWC);//done

                            }
                        })
                        .setNegativeButton(R.string.gotit, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                                onResume();
                            }
                        })
                        .show();



                break;





        }

    return false;
    }


}
