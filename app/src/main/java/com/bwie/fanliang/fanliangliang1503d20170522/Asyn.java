package com.bwie.fanliang.fanliangliang1503d20170522;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.google.gson.Gson;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by fanliang on 2017/4/28.
 */

public class Asyn extends AsyncTask<Object,Integer,String> {

    private Handler handler;
    List<Bean.DataBean> list = new ArrayList<Bean.DataBean>();
    Map params;
    public Asyn(Handler handler) {
        this.handler = handler;
    }

    @Override
    protected String doInBackground(Object... params) {
        Map map = (Map) params[0];
        this.params = map;
        String path = "http://www.93.gov.cn/93app/data.do";

        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(20000);
            connection.setReadTimeout(20000);

            StringBuffer sb = new StringBuffer();
            Set<String> set = map.keySet();
            for(String s : set){
                sb.append(s);
                sb.append("=");
                sb.append(map.get(s));
                sb.append("&");
            }
            sb = sb.deleteCharAt(sb.length()-1);
            OutputStream os = connection.getOutputStream();
            os.write(sb.toString().getBytes());
            os.flush();
            System.out.println("result" + connection.getResponseCode());
            if(connection.getResponseCode() == 200){
                InputStream is = connection.getInputStream();
                String result = StringUtils.inputStreamToString(is);
                System.out.println("result" + result);
                return result;

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Log.i("bean",s);
        Gson gson = new Gson();
        Bean bean = gson.fromJson(s,Bean.class);

        List<Bean.DataBean> listBean = bean.getData();
        Log.i("bean",listBean.toString());
        list.addAll(listBean);
        int page = (int)params.get("page");
        Message msg = new Message();
        msg.what = page ==1? 1: 2;
        msg.obj = list;
        handler.sendMessage(msg);

    }
}
