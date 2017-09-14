package com.example.vsevolod.myapplication;

import android.os.AsyncTask;
import android.util.Log;

import com.thoughtworks.xstream.XStream;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class AddBorrBook extends AsyncTask<BORRBOOK,Void,String> {

    protected String doInBackground(BORRBOOK... borrbook){
        String UrL="http://192.168.1.106:8080/LibBro/webservice/BookManage/addBbook";

        final MediaType MEDIA_TYPE=MediaType.parse("application/xml");

        try{
            OkHttpClient okHttpClient=new OkHttpClient();
            XStream xStream=new XStream();
            String respText="";
            xStream.alias("borrbook",BORRBOOK.class);
            Log.e("XMLSTRING",xStream.toXML(borrbook[0]));
            RequestBody body=RequestBody.create(MEDIA_TYPE, xStream.toXML(borrbook[0]));
            Request request=new Request.Builder().url(UrL).post(body).build();
            Log.e("REQUESTBODY",request.headers().toString());
            try(Response response=okHttpClient.newCall(request).execute()){
                if(!response.isSuccessful())
                    respText=response.toString();
                else
                    respText=response.body().string();
            }
            if(respText.equals("")){
                return "Connection Problems";
            }else {
                return respText;
            }
        }catch (Exception e){
            e.printStackTrace();
            return e.toString();
        }
    }

    protected void onPostExecute(String result){
        super.onPostExecute(result);
    }

}
