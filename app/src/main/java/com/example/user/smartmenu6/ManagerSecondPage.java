package com.example.user.smartmenu6;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class ManagerSecondPage extends Activity {


  public ListView tableListView;
  public TableHaspMapAdapter tableHashMapAdapter;
  public final static String Buttons="buttons";
  public ArrayList<HashMap> arrayList=new ArrayList<HashMap>();





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_secondpage);
        defineView();



        }
      private void defineView() {

        tableListView=findViewById(R.id.table_list_view);
        int maxTableCnt=10;

        for(int i=1; i<maxTableCnt; i++){

            HashMap table=new HashMap<>();
            table.put("tableNo",i);
            arrayList.add(table);

        }



        tableHashMapAdapter=new TableHaspMapAdapter(this,arrayList);
        tableListView.setAdapter(tableHashMapAdapter);



      }

}
