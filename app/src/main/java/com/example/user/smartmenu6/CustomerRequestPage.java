package com.example.user.smartmenu6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class CustomerRequestPage extends Activity {


    private Button mWrite=null;
    private Button mCall=null;
    private Button mPlate=null;
    private Button mWater=null;



    public final static int CHOOSE_BUTTON_REQUEST = 0;


    public final static String BUTTONS =  "buttons";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_requestpage);

        mWrite = (Button) findViewById(R.id.write);

        mWrite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra(MainActivity.BUTTONS, "2");
                setResult(RESULT_OK, result);

            }
        });

        mWater = (Button) findViewById(R.id.water);

        mWater.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra(MainActivity.BUTTONS, "2");
                setResult(RESULT_OK, result);

            }
        });

        mPlate = (Button) findViewById(R.id.plate);

        mPlate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra(MainActivity.BUTTONS, "2");
                setResult(RESULT_OK, result);

            }
        });

        mCall = (Button) findViewById(R.id.call);

        mCall.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                result.putExtra(MainActivity.BUTTONS, "2");
                setResult(RESULT_OK, result);

            }
        });
    }


}


