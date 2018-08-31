package com.example.user.smartmenu6;
import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

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
import com.google.gson.Gson;

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

    public void setmListItems(ArrayList<String> mListItems) {
        this.mListItems = mListItems;
    }

    private ArrayList<String> mListItems = new ArrayList<String>();

    public FireBaseModel(Context context){
        mContext = context;
    }

    public void firebaseAuthWithGoogle() {
        mAuth = FirebaseAuth.getInstance();
    }

    public void firebaseNoneAuth() {

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

    }


    public void addData(Food food){
        databaseReference.child("orders").push().setValue(food);
    }

    public void setListListener(final MyHashMapAdapter arrayAdapter,final HashMap paramMap){

        this.myHashMapAdapter=arrayAdapter;

        databaseReference.child("orders").orderByChild("orderSts").equalTo(paramMap.get("orderSts").toString()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                boolean addAble=true;
                if(paramMap.get("tableNo")!=null){

                    if (paramMap.get("tableNo").equals(dataSnapshot.child("tableNo").getValue())) {
                        addAble = true;
                    }else {
                        addAble=false;
                    }
                }

                if(addAble){

                    Log.d("onChildAdded 1",GSON.toJson(dataSnapshot.toString()));
                                       HashMap hashMap = new HashMap();
                    hashMap.put("key",dataSnapshot.getKey());





                    hashMap.put("name",dataSnapshot.child("name").getValue());
                    hashMap.put("cost",dataSnapshot.child("cost").getValue());
                    hashMap.put("tableNo",dataSnapshot.child("tableNo").getValue());
                    hashMap.put("country",dataSnapshot.child("country").getValue());


                    arrayAdapter.add(hashMap);

                    arrayAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.d("onChildChanged",dataSnapshot.toString());
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.d("onChildRemoved",dataSnapshot.toString());

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
        Log.d("removeItem",s);
        databaseReference.child("message").child(s).removeValue();
    }

    public void updateData(Food food, String key) {
        Map<String,Object> childUpdates=new HashMap<>();
        childUpdates.put("/orders/"+key,food);
        databaseReference.updateChildren(childUpdates);

        myHashMapAdapter.notifyDataSetChanged();





    }
}