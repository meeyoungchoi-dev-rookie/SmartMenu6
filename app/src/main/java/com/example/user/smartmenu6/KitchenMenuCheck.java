package com.example.user.smartmenu6;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KitchenMenuCheck extends Activity {



    private Button mOrder0=null;


    public final static int CHOOSE_BUTTON_REQUEST = 0;


    public final static String BUTTONS =  "buttons";
    private FireBaseModel fireBaseModel;
    private ArrayList<HashMap> arrayList =new ArrayList<HashMap>();
    private MyHashMapAdapter myHashMapAdapter;

    private ListView listView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitchen_check);

        definedFireBase();
        definedView();


    }

    protected void definedView() {
        listView = findViewById(R.id.ordered_list);
        listView.setAdapter(myHashMapAdapter);
    }

    protected void definedFireBase(){
        fireBaseModel=new FireBaseModel(this);

        fireBaseModel.firebaseAuthWithGoogle();
        fireBaseModel.firebaseNoneAuth();

        myHashMapAdapter=new MyHashMapAdapter(this,arrayList);
        myHashMapAdapter.setFireBaseModel(fireBaseModel);

        HashMap paramMap=new HashMap();
        paramMap.put("orderSts","N");







        fireBaseModel.setListListener(myHashMapAdapter,paramMap);




    }





}
