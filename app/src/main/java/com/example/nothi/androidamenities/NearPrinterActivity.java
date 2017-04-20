package com.example.nothi.androidamenities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearPrinterActivity extends AppCompatActivity {
    private EditText coordReceived;
    //SharedPreferences sharedInfo;

    //maybe use a treemap.
    Map<String, List<Double>  > printerValueMap=new HashMap<>();

    private Button selectWC;
    private Button selectWB;
    private Button selectWT;
    private Button selectHP;

    private  List<Double> printerCordFloorOneX = Arrays.asList(0.0, 6.0, 12.0,18.0, 24.0);
    private  List<Double> printerCordFloorOneY = Arrays.asList(0.0, 10.0, 20.0, 30.0, 40.0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_near_printer);
        coordReceived= (EditText) findViewById(R.id.receivedCoord);
        //coordReceived.setText(sharedInfo.getString("Coordinatekey", "default" ));
        Intent listIn=getIntent();


        String messagePassed=listIn.getStringExtra("Coordkey");
        coordReceived.setText(messagePassed);
//        List<String> items = Arrays.asList(messagePassed.split("\\s*,\\s*"));
      //  coordReceived.setText("Raw is:"+messagePassed+" Parsed is: "+" "+items.get(0)+" "+items.get(1)+" "+items.get(2));

      //  List<Double> currnetLoc=Arrays.asList(Double.parseDouble(items.get(1)), Double.parseDouble(items.get(2)));


        printerValueMap.put("Printer one", Arrays.asList(0.0, 0.0));
        printerValueMap.put("Printer two", Arrays.asList(6.0, 10.0));
        printerValueMap.put("Printer three", Arrays.asList(12.0, 20.0));
        printerValueMap.put("Printer four", Arrays.asList(18.0, 30.0));
        printerValueMap.put("Printer five", Arrays.asList(24.0, 40.0));

        List<Double> distanceOrder=Arrays.asList();

        //////////////////////////////////////////////////////////////////////

        selectWC=(Button)findViewById(R.id.wc);
        selectWB=(Button)findViewById(R.id.wb);
        selectWT=(Button)findViewById(R.id.wt);
        selectHP=(Button)findViewById(R.id.helpBt);


        selectWC.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {



                        Intent goingtoWC= new Intent("com.example.nothi.androidamenities.NearestinCommons");

                        startActivity(goingtoWC);//done



                    }
                }

        );


        selectWB.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {



                        Intent goingtoWB= new Intent("com.example.nothi.androidamenities.NearestinBusiness");

                        startActivity(goingtoWB);//done



                    }
                }

        );


        selectWT.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {



                        Intent goingtoWT= new Intent("com.example.nothi.androidamenities.NearestinTech");

                        startActivity(goingtoWT);//done



                    }
                }

        );


        selectHP.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {



                        AlertDialog.Builder builder = new AlertDialog.Builder(NearPrinterActivity.this);
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



                    }
                }

        );
    }


    }

