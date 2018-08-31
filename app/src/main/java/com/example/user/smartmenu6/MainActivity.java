package com.example.user.smartmenu6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mCustomer = null;
    private Button mManager = null;
    private Button mKitchen = null;


    public final static int CHOOSE_BUTTON_REQUEST = 0;


    public final static String BUTTONS = "buttons";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mCustomer = (Button) findViewById(R.id.customer);
        mManager = (Button) findViewById(R.id.manager);
        mKitchen = (Button) findViewById(R.id.kitchen);


        mCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(MainActivity.this, CustomerSecondPage.class);

                startActivityForResult(I, CHOOSE_BUTTON_REQUEST);
            }
        });


        mManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(MainActivity.this, ManagerSecondPage.class);

                startActivityForResult(I, CHOOSE_BUTTON_REQUEST);
            }
        });

        mKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(MainActivity.this, KitchenMenuCheck.class);

                startActivityForResult(I, CHOOSE_BUTTON_REQUEST);
            }
        });


    }





}
