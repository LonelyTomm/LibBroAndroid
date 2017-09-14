package com.example.vsevolod.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import com.thoughtworks.xstream.XStream;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class GetBooksFromWS extends AsyncTask<Void,Void,List<BOOK>>{
    private static final String URI_BOOK="http://192.168.1.106:8080/LibBro/webservice/BookManage/getBooks";

    protected List<BOOK> doInBackground(Void... urls){
        try{
            List<BOOK> books=null;

            OkHttpClient httpClient=new OkHttpClient();
            Request request=new Request.Builder().url(URI_BOOK).get().build();
            try(Response response=httpClient.newCall(request).execute()){
                XStream xStream=new XStream();
                xStream.alias("bOOKs",List.class);
                xStream.alias("book",BOOK.class);
                xStream.autodetectAnnotations(true);
                books=(List<BOOK>) xStream.fromXML(response.body().string(),new BOOK());
                Log.d("BOOKS","SUCCESSFUL");
            }
            return books;
        }catch (Exception e) {
            Log.e("MESSAGE",e.toString());
            return null;
        }
    }

    protected void onPostExecute(List<BOOK> result){
        super.onPostExecute(result);
    }

}
