package com.example.user.smartmenu6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.HashMap;

public class OrderCheck extends Activity {

    private Button btnOrderTable;
    public HashMap requestParamMap=new HashMap();
    private FireBaseModel fireBaseModel ;
    private ArrayList<HashMap> arrayList=new ArrayList<>();
    private OrderCheckedHashMapAdapter orderCheckedHashMapAdapter;

    private ListView listView;
    private TextView costSumText;
    private Button btnCalFinish;

    public final static int CHOOSE_BUTTON_REQUEST = 0;
    public final static String BUTTONS = "buttons";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_check);
        requestParamMap= (HashMap) getIntent().getExtras().get("paramMap");


        defineView();
        defineFireBase();


    }

    public void moveBack(){
        int CHOOSE_BUTTON_REQUEST = 0;

        Intent I = new Intent(OrderCheck.this, ManagerSecondPage.class);

        startActivityForResult(I, CHOOSE_BUTTON_REQUEST);

    }


    private void calFinish() {

        for(int i=0; i<arrayList.size(); i++) {
            String key = arrayList.get(i).get("key").toString();
            String name = arrayList.get(i).get("name").toString();
            String cost = arrayList.get(i).get("cost").toString();
            String tableNo = arrayList.get(i).get("tableNo").toString();
            String country = arrayList.get(i).get("country").toString();

             Food food = new Food(name, cost, country, tableNo);
            food.setOrderSts("F");
            fireBaseModel.updateData(food, key);
        }
    }


    private void defineView() {
        //btnOrderTable = (Button) findViewById(R.id.btn_order_table);
       // btnOrderTable.setText(requestParamMap.get("tableNo").toString()+"번 table 주문내역");
        listView=findViewById(R.id.table_ordered_list);
        costSumText=findViewById(R.id.cost_sum_text);

        btnCalFinish=findViewById(R.id.btn_cal_finish);
        btnCalFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                moveBack();
                calFinish();




                }
        });







        }



    protected void defineFireBase(){

        fireBaseModel=new FireBaseModel(this);
        fireBaseModel.firebaseAuthWithGoogle();
        fireBaseModel.firebaseNoneAuth();

        orderCheckedHashMapAdapter=new OrderCheckedHashMapAdapter(this,arrayList,costSumText);
        orderCheckedHashMapAdapter.setFireBaseModel(fireBaseModel);

        HashMap paramap=new HashMap();
        paramap.put("orderSts","Y");
        paramap.put("tableNo",requestParamMap.get("tableNo").toString());

        fireBaseModel.setListListener(orderCheckedHashMapAdapter,paramap);
        listView.setAdapter(orderCheckedHashMapAdapter);



    }



















}