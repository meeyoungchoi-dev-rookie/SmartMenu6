package com.example.user.smartmenu6;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.smartmenu6.service.FireBaseHttpRequestConnector;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class FoodDetailFragment extends Fragment {

    TextView nameText;
    TextView costText;
    Button cancelBtn;
    View.OnClickListener cancelBtnClickListener;
    Button addOrderBtn;
    View.OnClickListener addOrderBtnClickListener;
    ImageView imageView;
    HashMap mWeatherImageMap=new HashMap<>();
    HashMap paramMap;
    View rootView;
    Spinner tableSpinner;
    FireBaseModel fireBaseModel;
    ArrayList<String> arrayList;



    private OnFragmentInteractionListener mListener;

    public FoodDetailFragment() {
        // Required empty public constructor

    }

    @SuppressLint("ValidFragment")
    public FoodDetailFragment(HashMap paramMap) {
        this.paramMap = paramMap;
    }

    // TODO: Rename and change types and number of parameters
    public static FoodDetailFragment newInstance(String param1, String param2) {
        FoodDetailFragment fragment = new FoodDetailFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_food_detail_fragment, container, false);
        init(rootView);
        return rootView;

    }

    protected void init(View rootView){
        defineView(rootView);

        mWeatherImageMap.put("청국장", R.drawable.cheonggukjang);
        mWeatherImageMap.put("김치찌개", R.drawable.kimchistew);
        mWeatherImageMap.put("부대찌개", R.drawable.sausagestew);
        mWeatherImageMap.put("갈비탕", R.drawable.shortribsoup);
        mWeatherImageMap.put("된장찌개", R.drawable.soybeen);
        mWeatherImageMap.put("제육볶음", R.drawable.spicypork);
        mWeatherImageMap.put("육개장", R.drawable.yukgaejang);

        mWeatherImageMap.put("연어초밥", R.drawable.yeonachobob);
        mWeatherImageMap.put("우동", R.drawable.udong);
        mWeatherImageMap.put("연어덮밥", R.drawable.yeonadubbob);
        mWeatherImageMap.put("돈꼬치라멘", R.drawable.dongochiramyeon);
        mWeatherImageMap.put("해물라멘", R.drawable.haemulramyeon);
        mWeatherImageMap.put("돈까스", R.drawable.dongas);
        mWeatherImageMap.put("나가사끼라멘", R.drawable.nagasakiramyeon);
        mWeatherImageMap.put("장어덮밥", R.drawable.zangadubbob);
        mWeatherImageMap.put("불고기초밥", R.drawable.bulgogichobob);

        mWeatherImageMap.put("짬뽕", R.drawable.rednoodle);
        mWeatherImageMap.put("짜장면", R.drawable.blacknoodle);
        mWeatherImageMap.put("탕수육", R.drawable.tangsuyook);
        mWeatherImageMap.put("짬짜면", R.drawable.zamzamaen);
        mWeatherImageMap.put("사천탕면", R.drawable.sacheontangmyeon);
        mWeatherImageMap.put("울면", R.drawable.oolmyeon);
        mWeatherImageMap.put("잡채밥", R.drawable.zabchaebob);
        mWeatherImageMap.put("짬뽕밥", R.drawable.zambongbob);
        mWeatherImageMap.put("짜장밥", R.drawable.zazangbob);
        mWeatherImageMap.put("새우볶음밥", R.drawable.saeoobokumbob);

        setDataView(paramMap);

        fireBaseModel = new FireBaseModel(rootView.getContext());
        fireBaseModel .firebaseAuthWithGoogle();
        fireBaseModel.firebaseNoneAuth();
        // arrayAdapter = new MyHashMapAdapter(this,arrayList);
        //fireBaseModel.setListListener(arrayAdapter);
        //myList.setAdapter(arrayAdapter);
    }

    protected void defineListener(){
        cancelBtnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goList();
            }
        };


        addOrderBtnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOrder();
            }
        };

        cancelBtn.setOnClickListener(cancelBtnClickListener);
        addOrderBtn.setOnClickListener(addOrderBtnClickListener);
    }

    Food food;
    protected void setDataView(HashMap paramMap) {

        Gson gson = new Gson();
        food = gson.fromJson(gson.toJson(paramMap), Food.class);
        nameText.setText(food.getName());
        costText.setText(food.getCost());


        Picasso.with(getContext())
                .load((Integer) mWeatherImageMap.get(food.getName()))
                .into(imageView);
    }
    protected void defineView(View rootView){
        tableSpinner=rootView.findViewById(R.id.spinner_table);

        nameText  = rootView.findViewById(R.id.name_text);
        costText = rootView.findViewById(R.id.cost_text);
        imageView = rootView.findViewById(R.id.food_image);

        cancelBtn = rootView.findViewById(R.id.cancel_btn);
        addOrderBtn = rootView.findViewById(R.id.addOrder_btn);


       arrayList = new ArrayList<String>();

        arrayList.add(("1"));
        arrayList.add(("2"));
        arrayList.add(("3"));
        arrayList.add(("4"));


        ArrayAdapter<String > adapter=new ArrayAdapter<>(rootView.getContext(),android.R.layout.simple_spinner_item,arrayList);

        tableSpinner.setAdapter(adapter);
        tableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                food.setTableNo(arrayList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        defineListener();







    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
//        if (context instanceof OnFragmentInteractionListener) {
//            mListener = (OnFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnFragmentInteractionListener");
//        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private void addOrder() {
        //define 주문에 대한 데이터 처리
        // FireBase 권장.
        Log.d("test", food.toString());
        food.setTableNo(tableSpinner.getSelectedItem().toString());
        food.setOrderSts("N");


        fireBaseModel.addData(food);
        Toast toat = Toast.makeText(rootView.getContext(), "주문완료", Toast.LENGTH_SHORT);
        toat.show();



        Map<String, Object> firebaseMap =createfirebaseMap(food);


        FireBaseHttpRequestConnector fireBaseHttpRequestConnector = new FireBaseHttpRequestConnector();
        fireBaseHttpRequestConnector.execute(firebaseMap);
    }

    private Map<String,Object> createfirebaseMap(Food food) {
        Map<String, String> foodMap = new HashMap<>();
        foodMap.put("title", food.getName());
        foodMap.put("body", food.getTableNo());

        Map<String, Object> firebaseMap=new HashMap<>();
        firebaseMap.put("to", FirebaseInstanceId.getInstance().getToken());
        firebaseMap.put("notification", foodMap);
        firebaseMap.put("data", foodMap);
        return firebaseMap;
    }


    private void goList() {
        ((CustomerMenuOrderPage)getActivity()).switchFragment(new FoodFragment(food.getCountry()));
    }


}