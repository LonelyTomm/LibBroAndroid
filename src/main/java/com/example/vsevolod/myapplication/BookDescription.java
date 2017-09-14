package com.example.vsevolod.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class BookDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_description);
        final int id=getIntent().getIntExtra("ID",0);
        ImageView imageView=(ImageView)findViewById(R.id.ImageBookDesc);
        TextView titleText=(TextView)findViewById(R.id.TitleBookDesc);
        TextView RetailText=(TextView)findViewById(R.id.RetBookDesc);
        TextView publisherText=(TextView)findViewById(R.id.PubBookDesc);
        TextView quantityText=(TextView)findViewById(R.id.QuantityBookDesc);
        TextView descriptionText=(TextView)findViewById(R.id.DescriptionBookDesc);
        TextView authorText=(TextView)findViewById(R.id.AuthorBookDesc);
        TextView genreText=(TextView)findViewById(R.id.GenreBookDesc);
        Button borrBook=(Button)findViewById(R.id.BorrowButt);
        BOOK book=new BOOK();
        try{
            GetBookDesc getBookDesc=new GetBookDesc();
            getBookDesc.execute(String.valueOf(id));
            book=getBookDesc.get();
        }catch (Exception e){
            e.printStackTrace();
        }
        new DownloadImage(imageView).execute("http://192.168.1.106:8080/LibBro/imageHandle?img="+book.getImgpath());
        if(book.getQuantity()<=0){
            borrBook.setClickable(false);
        }else {
            borrBook.setClickable(true);
        }
        titleText.setText(titleText.getText()+book.getTitle());
        RetailText.setText(RetailText.getText()+String.valueOf(book.getRetprice()));
        publisherText.setText(publisherText.getText()+book.getPublisher());
        quantityText.setText(quantityText.getText()+String.valueOf(book.getQuantity()));
        descriptionText.setText(descriptionText.getText()+book.getDescription());
        authorText.setText(authorText.getText()+book.getAuthor());
        genreText.setText(genreText.getText()+book.getGenre());
        borrBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),BorrBookActivity.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
    }
}
