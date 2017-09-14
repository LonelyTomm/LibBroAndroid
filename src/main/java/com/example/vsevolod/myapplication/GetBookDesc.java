package com.example.vsevolod.myapplication;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.thoughtworks.xstream.XStream;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetBookDesc extends AsyncTask<String,Void,BOOK>{

    protected BOOK doInBackground(String... ids){
        String url="http://192.168.1.106:8080/LibBro/webservice/BookManage/getBook/"+ids[0];
        try{
            BOOK book=new BOOK();

            OkHttpClient okHttpClient=new OkHttpClient();
            Request request=new Request.Builder().url(url).get().build();
            try(Response response=okHttpClient.newCall(request).execute()){
                XStream xStream=new XStream();
                xStream.alias("book",BOOK.class);
                book=(BOOK)xStream.fromXML(response.body().string());
                Log.d("DescMessage","Successful");
            }
            return book;
        }catch (Exception e){
            return null;
        }
    }

    protected void onPostExecute(BOOK result){
        super.onPostExecute(result);
    }

}
