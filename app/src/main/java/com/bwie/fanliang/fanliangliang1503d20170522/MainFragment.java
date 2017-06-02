package com.bwie.fanliang.fanliangliang1503d20170522;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.bwie.fanliang.fanliangliang1503d20170522.XlistView.XListView;
import com.google.gson.Gson;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by haozi on 2017/5/12.
 */

public class MainFragment extends Fragment implements XListView.IXListViewListener{
    private int page = 1;
    List<Bean.DataBean> list = new ArrayList<Bean.DataBean>();
    private Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case 1:
                    List<Bean.DataBean> list = (List<Bean.DataBean>) msg.obj;
                    updata(list,1);
                    break;

                case 2:
                    List<Bean.DataBean> list1 = (List<Bean.DataBean>) msg.obj;
                    updata(list1,2);
                    break;

            }
        }
    };

    private MyAdapter adapter;
    private XListView xListView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_fragment, container, false);

        xListView = (XListView) view.findViewById(R.id.xlv);

        xListView.setXListViewListener(this);
        xListView.setPullLoadEnable(true);
        xListView.setPullRefreshEnable(true);


        new Thread(new Runnable() {
            @Override
            public void run() {
                iniData(page);
            }
        }).start();

        adapter = new MyAdapter(getActivity(),list);
        xListView.setAdapter(adapter);

        return view;



    }



    private void iniData(int page) {
        Map map = new HashMap();
        map.put("channelId",0);
        map.put("startNum",0);
        map.put("page",page);

        new Asyn(handler).execute(map);

    }
    public void updata(List<Bean.DataBean> listBean,int type){
        if(type == 1){
            list.clear();
            list.addAll(listBean);
            adapter.notifyDataSetChanged();
            xListView.stopRefresh();
            xListView.setRefreshTime("刚刚");
        }else{
            list.addAll(listBean);
            adapter.notifyDataSetChanged();
            xListView.stopRefresh();

        }

    }

    @Override
    public void onRefresh() {

        page = 1;
        iniData(page);
    }

    @Override
    public void onLoadMore() {
        page = page + 1;
        iniData(page);
    }
}
