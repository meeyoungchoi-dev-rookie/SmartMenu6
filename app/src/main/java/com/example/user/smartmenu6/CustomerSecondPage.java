package com.example.user.smartmenu6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.user.smartmenu6.dto.ToToken;
import com.example.user.smartmenu6.service.FireBaseHttpRequestConnector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerSecondPage extends Activity {


    private Button mOrder=null;
    private Button mRequest=null;


    public final static int CHOOSE_BUTTON_REQUEST = 0;


    public final static String BUTTONS =  "buttons";
    FireBaseModel fireBaseModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_secondpage);
        defineFirBase();
        defineView();
    }
    protected void defineView(){
        mOrder = (Button) findViewById(R.id.order);
        mOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(CustomerSecondPage.this, CustomerMenuOrderPage.class);
                startActivityForResult(I, CHOOSE_BUTTON_REQUEST);
            }
        });

        Button mCallManager = (Button) findViewById(R.id.callManger);
        mCallManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tableNo = "3"; // 3번만 우선 할 수 있도록 함 화면에서선택한 테이블 번호로 바꿔야 됨
                sendCallManagerMessage(tableNo,fireBaseModel.getClientTokenList());

            }
        });
    }

    protected  void defineFirBase(){

        fireBaseModel = new FireBaseModel(this);
        fireBaseModel .firebaseAuthWithGoogle();
        fireBaseModel.firebaseNoneAuth();
        fireBaseModel.refreshClientTokenList();
    }

    //토큰받기->전송 저장 주방목록 가져오기 주방목록 만큼 메시지 전송
    private void sendCallManagerMessage(String tableNo,ArrayList<ToToken> clientTokenList) {

        //주방목록 가져오기 주방목록 만큼 메시지 전송

        if(clientTokenList!=null){
            for (int i = 0; i < clientTokenList.size(); i++) {
                if (clientTokenList.get(i).getRole().equals("1")) {
                    if (clientTokenList.get(i).getToken() != null) {

                        Map<String, Object> firebaseMap = createFirebaseMap(tableNo, clientTokenList.get(i).getToken());
                        FireBaseHttpRequestConnector fireBaseHttpRequestConnector = new FireBaseHttpRequestConnector();
                        fireBaseHttpRequestConnector.execute(firebaseMap);
                    }

                }
            }
        }

    }

    private Map<String,Object> createFirebaseMap(String tableNo,String to) {
        Map<String, String> foodMap = new HashMap<>();
        foodMap.put("title", tableNo+"번 테이블 호출");
        foodMap.put("body",  tableNo+"번 테이블 호출");

        Map<String, Object> firebaseMap=new HashMap<>();


        Log.d("to :", to);
        firebaseMap.put("to", to);
        firebaseMap.put("notification", foodMap);
        firebaseMap.put("data", foodMap);
        return firebaseMap;
    }

}
