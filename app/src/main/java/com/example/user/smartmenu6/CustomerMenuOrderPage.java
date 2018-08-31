package com.example.user.smartmenu6;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class CustomerMenuOrderPage extends AppCompatActivity implements View.OnClickListener   {

    Button btn1;
    Button btn2;
    Button btn3;
    String test;
    String test2;
    String test3;

    FragmentManager manager;
    FoodFragment foodFragment;
    FragmentTransaction tf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_menuorderpage);

        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        manager=getSupportFragmentManager();
        foodFragment=new FoodFragment("kor");

        tf = manager.beginTransaction();
        tf.addToBackStack(null);
        tf.add(R.id.main_container, foodFragment);
        tf.commit();

    }

    @Override
    public void onClick(View v) {
        String contry = "";
        if(v==btn1){
            contry ="kor";
        }else if(v==btn2){
            contry ="jap";
        }else if(v==btn3){
            contry ="cha";
        }
        if(!"".equals(contry)){
            if(foodFragment.isVisible()){
                foodFragment.changeContry(contry);
            }else{
                switchFragment(new FoodFragment(contry));
            }

        }
    }

    public void switchFragment(Fragment frg) {
        manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.replace( R.id.main_container, frg );
        fragmentTransaction.commit();
    }













}