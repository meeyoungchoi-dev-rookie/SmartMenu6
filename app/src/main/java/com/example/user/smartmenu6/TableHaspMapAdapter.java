package com.example.user.smartmenu6;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class TableHaspMapAdapter extends BaseAdapter {


    private ArrayList<HashMap> mListItems;
    private LayoutInflater mLayoutInflator;
    private Gson gson = new Gson();
    FireBaseModel fireBaseModel;
    private Context rootContext;


    public TableHaspMapAdapter(Context context, ArrayList<HashMap> arrayList) {
        rootContext = context;
        mListItems = arrayList;
        mLayoutInflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mListItems.size();
    }

    @Override
    public Object getItem(int i) {
        return mListItems.get(i);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    public void add(HashMap hashMap) {

        mListItems.add(hashMap);
    }

    public void removeIndex(int position) {
        mListItems.remove(position);
    }

    static class ViewHolder {
        TextView tableText;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        TableHaspMapAdapter.ViewHolder holder;

        if (convertView == null) {
            holder = new TableHaspMapAdapter.ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.table_item_cal, parent, false);

            TextView tableText = (TextView) convertView.findViewById(R.id.table_no);

            holder.tableText = tableText;
            convertView.setTag(holder);

        } else {
            holder = (TableHaspMapAdapter.ViewHolder) convertView.getTag();

        }

        Button btnCookFinish = (Button) convertView.findViewById(R.id.btn_do_cal);
        btnCookFinish.setOnClickListener(mOnClickListener);
        btnCookFinish.setTag(position);

        HashMap food = mListItems.get(position);
        holder.tableText.setText(food.get("tableNo").toString() + "번테이블");
        return convertView;
    }


    public void setFireBaseModel(FireBaseModel fireBaseModel) {
        this.fireBaseModel = fireBaseModel;
    }

    Button.OnClickListener mOnClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {


            int position = Integer.parseInt((v.getTag().toString()));
            String tableNo = mListItems.get(position).get("tableNo").toString();

            Intent I = new Intent(rootContext, OrderCheck.class);
            HashMap paramMap = new HashMap();
            paramMap.put("tableNo", tableNo);
            I.putExtra("paramMap", paramMap);

            rootContext.startActivity(I);





        }
    };

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}


























