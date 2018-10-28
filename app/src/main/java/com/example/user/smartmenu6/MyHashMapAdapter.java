package com.example.user.smartmenu6;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class MyHashMapAdapter extends BaseAdapter  {
    private ArrayList<HashMap> mListItems;
    private LayoutInflater mLayoutInflater;
    private Gson gson = new Gson();
    FireBaseModel fireBaseModel;


    public MyHashMapAdapter(Context context, ArrayList<HashMap> arrayList){
        mListItems = arrayList;
        //get the layout inflater
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        //getCount() represents how many items are in the list
        return mListItems.size();
    }
    @Override
    //get the data of an item from a specific position
    //i represents the position of the item in the list
    public Object getItem(int i) {

        return mListItems.get(i);
    }
    @Override
    //get the position id of the item from the list
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyHashMapAdapter.ViewHolder holder;



        if (convertView == null) {
            holder = new MyHashMapAdapter.ViewHolder();
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.kitchen_item_food, parent, false);

            ImageView koreanImage = (ImageView) convertView.findViewById(R.id.korean_image);
            TextView nameText = (TextView) convertView.findViewById(R.id.name_text);
            // TextView costText = (TextView) convertView.findViewById(R.id.cost_text);
            TextView tableText = (TextView) convertView.findViewById(R.id.table_text);


            TextView orderNo = (TextView) convertView.findViewById(R.id.order_no);

            holder.koreanImage = koreanImage;
            holder.nameText = nameText;
            // holder.costText = costText;
            holder.tableText = tableText;

            //주문번호
            holder.orderNo=orderNo;

            convertView.setTag(holder);
        } else {
            holder = (MyHashMapAdapter.ViewHolder) convertView.getTag();
        }

        Button btnCookFinish = (Button) convertView.findViewById(R.id.btn_cook_finish);
        btnCookFinish.setOnClickListener(mOnClickListener);
        btnCookFinish.setTag(position);


        HashMap food=mListItems.get(position);



        holder.nameText.setText(food.get("name").toString());
        // holder.costText.setText(food.get("cost").toString());
        holder.tableText.setText(food.get("tableNo").toString());
        //주문번호
        holder.orderNo.setText(food.get("orderNo").toString());




        return  convertView;

    }

    public void setFireBaseModel(FireBaseModel fireBaseModel) {
        this.fireBaseModel = fireBaseModel;
    }

    static class ViewHolder {
        ImageView koreanImage;
        TextView nameText;
        //TextView costText;
        TextView tableText;
        TextView orderNo;

    }

    public void add(HashMap hashMap) {
        mListItems.add(hashMap);

    }

    public void removeIndex(int position) {
        mListItems.remove(position);
    }


    /*리스너를 외부에 만들어 놓고 해당 포지션을 가져오기 위헤서 겟뷰에서 버튼에 셋테그로 버튼의 번재수를 지정하고 뽑아 쓴다 */
    Button.OnClickListener mOnClickListener = new  Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = Integer.parseInt( (v.getTag().toString()) );
            System.out.println("contactList.get(position).getPhoneNumber()  :   " + mListItems.get(position).get("name"));

            String key = mListItems.get(position).get("key").toString();
            String name = mListItems.get(position).get("name").toString();
            String cost  = mListItems.get(position).get("cost").toString();
            String country=mListItems.get(position).get("country").toString();
            String tableNo  = mListItems.get(position).get("tableNo").toString();

            Food food = new Food(name,cost,country,tableNo);
            food.setOrderSts("Y");
            fireBaseModel.updateData(food,key);
            mListItems.remove(position);
        }
    };

    @Override
    public void notifyDataSetChanged(){

        super.notifyDataSetChanged();
    }

}