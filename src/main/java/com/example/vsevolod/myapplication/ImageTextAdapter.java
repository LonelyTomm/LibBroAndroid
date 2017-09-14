package com.example.vsevolod.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;



public class ImageTextAdapter extends BaseAdapter{
    private Context mContext;
    private List<BOOK> bookList;

    public ImageTextAdapter(Context c)
    {
        mContext=c;
        try{
            GetBooksFromWS getBooksFromWS=new GetBooksFromWS();
            getBooksFromWS.execute();
            bookList=getBooksFromWS.get();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Object getItem(int position){
        return bookList.get(position);
    }

    public long getItemId(int position){
        return bookList.get(position).getBid();
    }

    public int getCount(){
        if(bookList!=null){
            return bookList.size();
        }else {
            return 0;
        }

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View grid;

        if(convertView==null){
            grid=new View(mContext);
            LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            grid=inflater.inflate(R.layout.cellgrid,parent,false);
        }else{
            grid=(View)convertView;
        }

        ImageView imagePart=(ImageView)grid.findViewById(R.id.BookImage);
        new DownloadImage(imagePart).execute("http://192.168.1.106:8080/LibBro/imageHandle?img="+bookList.get(position).getImgpath());
        TextView bookTitle=(TextView)grid.findViewById(R.id.BookTitle);
        bookTitle.setText(bookList.get(position).getTitle());
        TextView bookDesc=(TextView)grid.findViewById(R.id.BookDescription);
        bookDesc.setText(bookList.get(position).getDescription());

        return grid;
    }
}
