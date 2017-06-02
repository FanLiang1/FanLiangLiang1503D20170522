package com.bwie.fanliang.fanliangliang1503d20170522;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by fanliang on 2017/4/28.
 */

public class MyAdapter extends BaseAdapter {
    Context context;
    List<Bean.DataBean> list;
    public MyAdapter(Context context, List<Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {

        if(list.get(position).getIMAGEURL()==null){
            return 0;
        }else{
            return 1;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Vh1 vh1 = null;
        Vh2 vh2 = null;

        int type = getItemViewType(position);
        if(convertView == null){
            if(type==0){
                vh1 = new Vh1();
                convertView = convertView.inflate(context, R.layout.layout1,null);
                vh1.tv1 = (TextView) convertView.findViewById(R.id.layout1_title);
                vh1.tv2 = (TextView) convertView.findViewById(R.id.layout1_foot);
                vh1.tv3 = (TextView) convertView.findViewById(R.id.layout1_title1);
                vh1.tv4 = (TextView) convertView.findViewById(R.id.layout1_foot1);
                convertView.setTag(vh1);
            }else{
                vh2 = new Vh2();
                convertView = convertView.inflate(context,R.layout.layout2,null);
                vh2.iv1 = (ImageView) convertView.findViewById(R.id.layout2_iv1);
                vh2.tv1 = (TextView) convertView.findViewById(R.id.layout2_title);
                vh2.tv2 = (TextView) convertView.findViewById(R.id.layout2_title1);
                vh2.tv3 = (TextView) convertView.findViewById(R.id.layout2_title3);
                vh2.tv4 = (TextView) convertView.findViewById(R.id.layout2_title4);
                convertView.setTag(vh2);
            }

        }else{

            if(type == 0){
                vh1 = (Vh1) convertView.getTag();
            }else{
                vh2 = (Vh2) convertView.getTag();
            }

        }
        if(type == 0){
            vh1.tv1.setText(list.get(position).getTITLE());
            vh1.tv2.setText(list.get(position).getFROMNAME());
            vh1.tv3.setText(list.get(position).getSUBTITLE());
            vh1.tv4.setText(list.get(position).getSHOWTIME());
        }else{
            ImageLoader.getInstance().displayImage(list.get(position).getIMAGEURL(),vh2.iv1,Ipplication.getOption());
            vh2.tv1.setText(list.get(position).getTITLE());
            vh2.tv2.setText(list.get(position).getSUBTITLE());
            vh2.tv3.setText(list.get(position).getFROMNAME());
            vh2.tv4.setText(list.get(position).getSHOWTIME());
        }

        // vh1.tv1.setText(list.get(position));
        return convertView;
    }
    class Vh1{
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;

    }

    class Vh2{
        ImageView iv1;
        TextView tv1;
        TextView tv2;
        TextView tv3;
        TextView tv4;

    }

}
