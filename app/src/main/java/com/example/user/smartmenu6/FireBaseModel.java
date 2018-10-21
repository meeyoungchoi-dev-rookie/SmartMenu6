package com.example.user.smartmenu6;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.user.smartmenu6.dto.ToToken;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jun on 2017-11-25.
 */

public class FireBaseModel {
    private String TAG = "테그";

    private FirebaseAuth mAuth;
    private Context mContext;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private Gson GSON = new Gson();
    private MyHashMapAdapter myHashMapAdapter;

    public ArrayList<String> getmListItems() {
        return mListItems;
    }

    public ArrayList<ToToken> clientTokenList;
    //모든 기기에 갖고있는 토큰을 가지고 있다.


    public void setmListItems(ArrayList<String> mListItems) {
        this.mListItems = mListItems;
    }

    private ArrayList<String> mListItems = new ArrayList<String>();

    public FireBaseModel(Context context) {
        mContext = context;
    }

    public void firebaseAuthWithGoogle() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void firebaseNoneAuth() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }


    public void addData(Food food) {
        databaseReference.child("orders").push().setValue(food);
    }

    public void addToToken(ToToken toToken) {
        databaseReference.child("toTokens").push().setValue(toToken);
    }

    public void setListListener(final MyHashMapAdapter arrayAdapter, final HashMap paramMap) {

        this.myHashMapAdapter = arrayAdapter;

        databaseReference.child("orders").orderByChild("orderSts").equalTo(paramMap.get("orderSts").toString()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                boolean addAble = true;
                if (paramMap.get("tableNo") != null) {

                    if (paramMap.get("tableNo").equals(dataSnapshot.child("tableNo").getValue())) {
                        addAble = true;
                    } else {
                        addAble = false;
                    }
                }

                if (addAble) {

                    Log.d("onChildAdded 1", GSON.toJson(dataSnapshot.toString()));
                    HashMap hashMap = new HashMap();
                    hashMap.put("key", dataSnapshot.getKey());

                    hashMap.put("name", dataSnapshot.child("name").getValue());
                    hashMap.put("cost", dataSnapshot.child("cost").getValue());
                    hashMap.put("tableNo", dataSnapshot.child("tableNo").getValue());
                    hashMap.put("country", dataSnapshot.child("country").getValue());
                    hashMap.put("number", dataSnapshot.child("number").getValue());


                    arrayAdapter.add(hashMap);

                    arrayAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d("onChildChanged", dataSnapshot.toString());
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("onChildRemoved", dataSnapshot.toString());

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void removeItem(String s) {
        Log.d("removeItem", s);
        databaseReference.child("message").child(s).removeValue();
    }

    public void updateData(Food food, String key) {
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/orders/" + key, food);
        databaseReference.updateChildren(childUpdates);

        myHashMapAdapter.notifyDataSetChanged();


    }

    public void refreshKitchenTokenList(ArrayList<ToToken> tokenList) {

        databaseReference.child("toTokens").addValueEventListener(tokenListEventListener);

    }

    public ArrayList<ToToken> getClientTokenList() {
        return this.clientTokenList;
    }


    ValueEventListener tokenListEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            clientTokenList = new ArrayList<ToToken>();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ToToken toToken = snapshot.getValue(ToToken.class);
                    clientTokenList.add(toToken);
                }
            }

        }


        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
        // 새로고친다 고객토큰리스트를
    public void refreshClientTokenList() {
        databaseReference.child("toTokens").addValueEventListener(valueEventListener);
        //데이터베이스 레퍼런스의 자식인 투토큰에 가치있는 사건을 듣는거를 더한다.
        //가치있는 사건을 듣는거는
        //데이터가 변경시점
        //취소 시점
        //투토큰리스트를 바꼈을때랑 취소되었을때를 감지하는 애가 있다
        //앱에 파이어베이스가 바꼇는지 감지한다.
        //차일드라는 함수에 투토큰을 찾아온다.      //가치있는 사건을 듣는거
        //데이터베이스레퍼런스에 있는 투토큰 자식
        //add 더하다
        //value 가치
        //event 사건
        //

    }


        //가치있는 사건을 듣는거
    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            //저장된 토큰리스트를 새로고침한다.
            //바꼈을때 마다 새로 갱신을 하기위해서 초기화 한다.
            //파이어베이스에서 내려준 모든 데이터토큰리스를 다시 처음부터 넣는다.
            clientTokenList=new ArrayList<ToToken>();

            if (dataSnapshot.exists()){
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ToToken toToken=snapshot.getValue(ToToken.class);
                    Log.d("token:",toToken.toString());
                    toToken.setKey(snapshot.getKey());
                    //토큰에서 어디의 키인지 알수 있다.

                    //실제 키이다.
                    clientTokenList.add(toToken);
                    //투토큰은 데이터만 잇다.
                }
            }

        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    public void updateToToken(String key, ToToken toToken) {

        Map<String ,Object> childUpdates=new HashMap<>();
        childUpdates.put("/toTokens/" + key,toToken);
        databaseReference.updateChildren(childUpdates);


    }
}





