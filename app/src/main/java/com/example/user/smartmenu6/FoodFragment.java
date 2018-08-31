package com.example.user.smartmenu6;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;


public class FoodFragment extends ListFragment {
    String mContry;
    ArrayList<Food> allData=new ArrayList<>();
    ArrayList<Food> filterdata=new ArrayList<>();
    FoodListViewAdapter aa;

    public FoodFragment(){

    }

    @SuppressLint("ValidFragment")
    public FoodFragment(String contry){
        this.mContry = contry;
        this.allData = createFoodData();
        this.filterdata = getFilterFoodData(contry);
    }
    public ArrayList<Food> createFoodData(){
        allData.add(new Food("청국장", "3000", "kor"));
        allData.add(new Food("김치찌개", "6000", "kor"));
        allData.add(new Food("부대찌개", "6000", "kor"));
        allData.add(new Food("갈비탕", "6000", "kor"));
        allData.add(new Food("된장찌개", "6000", "kor"));
        allData.add(new Food("제육볶음", "6000", "kor"));
        allData.add(new Food("육개장", "6000", "kor"));

        allData.add(new Food("짬뽕", "6000", "cha"));
        allData.add(new Food("짜장면", "6000", "cha"));
        allData.add(new Food("탕수육", "6000", "cha"));
        allData.add(new Food("사천탕면", "6000", "cha"));
        allData.add(new Food("울면", "6000", "cha"));
        allData.add(new Food("잡채밥", "6000", "cha"));
        allData.add(new Food("짬뽕밥", "6000", "cha"));
        allData.add(new Food("짜장밥", "6000", "cha"));
        allData.add(new Food("새우볶음밥", "6000", "cha"));

        allData.add(new Food("연어초밥", "6000", "jap"));
        allData.add(new Food("우동", "6000", "jap"));
        allData.add(new Food("연어덮밥", "6000", "jap"));
        allData.add(new Food("돈꼬치라멘", "6000", "jap"));
        allData.add(new Food("해물라멘", "6000", "jap"));
        allData.add(new Food("돈까스", "6000", "jap"));
        allData.add(new Food("나가사끼라멘", "6000", "jap"));
        allData.add(new Food("장어덮밥", "6000", "jap"));
        allData.add(new Food("불고기초밥", "6000", "jap"));
        return allData;
    }
    public ArrayList<Food> getFilterFoodData(String contry){
        ArrayList<Food> filterdata=new ArrayList<>();
        for(int i=0;i<allData.size();i++){
            if(contry.equals(allData.get(i).getCountry())){
                filterdata.add(allData.get(i));
            }
        }
        return filterdata;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        // String[] datas = {"된장찌개 ", "김치찌개", "청국장", "부대찌개","콩나물국","순두부찌개","순대국","돼지국밥","참치찌개","돼지짜글이","해물된장찌개"};



        // ArrayAdapter<String> aa = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, datas);
        aa = new FoodListViewAdapter(filterdata);
        setListAdapter(aa);


    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
//        Toast toat = Toast.makeText(getActivity(), (String) l.getAdapter().getItem(position), Toast.LENGTH_SHORT);
//        toat.show();
        goDetail(l.getAdapter().getItem(position));

    }

    private void goDetail(Object item) {
        Gson gson = new Gson();
        HashMap paramMap = gson.fromJson(gson.toJson(item),HashMap.class);

        ((CustomerMenuOrderPage)getActivity()).switchFragment(new FoodDetailFragment(paramMap));

    }


    public void changeContry(String contry) {
        this.filterdata = getFilterFoodData(contry);
        aa.setFoodData(this.filterdata);
        aa.notifyDataSetChanged();
    }
}