package com.example.user.smartmenu6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

public class CustomerSecondPage extends Activity {


    private Button mOrder=null;
    private Button mRequest=null;


    public final static int CHOOSE_BUTTON_REQUEST = 0;


    public final static String BUTTONS =  "buttons";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_secondpage);

        mOrder = (Button) findViewById(R.id.order);
        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(CustomerSecondPage.this, CustomerMenuOrderPage.class);

                startActivityForResult(I, CHOOSE_BUTTON_REQUEST);

            }
        });

        mRequest = (Button) findViewById(R.id.request);
        mRequest.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent I = new Intent(CustomerSecondPage.this,CustomerRequestPage.class);

                startActivityForResult(I, CHOOSE_BUTTON_REQUEST);

            }
        });
    }

}
