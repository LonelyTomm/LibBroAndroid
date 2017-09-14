package com.example.vsevolod.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView=(GridView)findViewById(R.id.GridView);
        gridView.setAdapter(new ImageTextAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id=(int)adapterView.getAdapter().getItemId(i);
                Intent intent=new Intent(getApplicationContext(),BookDescription.class);
                intent.putExtra("ID",id);
                startActivity(intent);
            }
        });
    }
}
