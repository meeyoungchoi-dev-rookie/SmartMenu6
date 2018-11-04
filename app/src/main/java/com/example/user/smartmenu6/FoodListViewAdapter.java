package com.example.user.smartmenu6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodListViewAdapter extends BaseAdapter {
    private ArrayList<Food> mData;
    private Map<String,Integer> mFoodImageMap;

    public void setFoodData(ArrayList<Food> data){
        this.mData = data;

    }



    public FoodListViewAdapter(ArrayList<Food> data) {

        mData = data;
        mFoodImageMap = new HashMap<>();
        mFoodImageMap.put("청국장", R.drawable.cheonggukjang);
        mFoodImageMap.put("김치찌개", R.drawable.kimchistew);
        mFoodImageMap.put("부대찌개", R.drawable.sausagestew);
        mFoodImageMap.put("갈비탕", R.drawable.shortribsoup);
        mFoodImageMap.put("된장찌개", R.drawable.soybeen);
        mFoodImageMap.put("제육볶음", R.drawable.spicypork);
        mFoodImageMap.put("육개장", R.drawable.yukgaejang);

        mFoodImageMap.put("연어초밥", R.drawable.yeonachobob);
        mFoodImageMap.put("우동", R.drawable.udong);
        mFoodImageMap.put("연어덮밥", R.drawable.yeonadubbob);
        mFoodImageMap.put("돈꼬치라멘", R.drawable.dongochiramyeon);
        mFoodImageMap.put("해물라멘", R.drawable.haemulramyeon);
        mFoodImageMap.put("돈까스", R.drawable.dongas);
        mFoodImageMap.put("나가사끼라멘", R.drawable.nagasakiramyeon);
        mFoodImageMap.put("장어덮밥", R.drawable.zangadubbob);
        mFoodImageMap.put("불고기초밥", R.drawable.bulgogichobob);

        mFoodImageMap.put("짬뽕", R.drawable.rednoodle);
        mFoodImageMap.put("짜장면", R.drawable.blacknoodle);
        mFoodImageMap.put("탕수육", R.drawable.tangsuyook);
        mFoodImageMap.put("짬짜면", R.drawable.zamzamaen);
        mFoodImageMap.put("사천탕면", R.drawable.sacheontangmyeon);
        mFoodImageMap.put("울면", R.drawable.oolmyeon);
        mFoodImageMap.put("잡채밥", R.drawable.zabchaebob);
        mFoodImageMap.put("짬뽕밥", R.drawable.zambongbob);
        mFoodImageMap.put("짜장밥", R.drawable.zazangbob);
        mFoodImageMap.put("새우볶음밥", R.drawable.saeoobokumbob);

    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_food, parent, false);

            ImageView koreanImage = (ImageView) convertView.findViewById(R.id.korean_image);
            TextView nameText = (TextView) convertView.findViewById(R.id.name_text);
            TextView costText = (TextView) convertView.findViewById(R.id.cost_text);

            holder.koreanImage = koreanImage;
            holder.nameText = nameText;
            holder.costText = costText;

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Food food=mData.get(position);

        holder.nameText.setText(food.getName());
        holder.costText.setText(food.getCost());
        //holder.koreanImage.setImageResource(mKoreanImageMap.get(korean.getKorean()));

        Picasso.with(parent.getContext())
                .load(mFoodImageMap.get(food.getName()))
                .into(holder.koreanImage);

        return  convertView;

    }
    static class ViewHolder {

        ImageView koreanImage;
        TextView nameText;
        TextView costText;

    }


}