package com.example.vsevolod.myapplication;


import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BorrBookActivity extends AppCompatActivity {

    TextView txtView;
    EditText studName;
    EditText studNo;
    TextView respText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borr_book);
        Button borrButt=(Button)findViewById(R.id.BorrButt);
        respText=(TextView)findViewById(R.id.Response);
        txtView=(TextView) findViewById(R.id.ReturnDateEdit);
        txtView.setText("yyyy-MM-dd");
        studName=(EditText)findViewById(R.id.StudNameEdit);
        studNo=(EditText)findViewById(R.id.StudNoEdit);
        borrButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((!txtView.getText().equals("yyyy-MM-dd"))&&(!studName.getText().equals(""))&&(studName.getText()!=null)&&(!studNo.getText().equals(""))
                        &&(studNo.getText()!=null)){
                    try{
                        int StudNoInt=Integer.valueOf(studNo.getText().toString());
                        String RetDate=txtView.getText().toString();
                        String StudName=studName.getText().toString();
                        int BookId=getIntent().getIntExtra("ID",0);
                        BORRBOOK borrbook=new BORRBOOK();
                        borrbook.setBookid(BookId);
                        borrbook.setStudname(StudName);
                        borrbook.setStudno(StudNoInt);
                        borrbook.setReturndate(RetDate);
                        AddBorrBook addBorrBook=new AddBorrBook();
                        addBorrBook.execute(borrbook);
                        Toast toast=Toast.makeText(getApplicationContext(),addBorrBook.get(),Toast.LENGTH_SHORT);
                        toast.show();
                        respText.setText("");
                    }catch (Exception e){
                        respText.setText(e.toString());
                        e.printStackTrace();
                    }
                }
            }
        });



        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLabel();
            }
        });


    }

    private void updateLabel(){
        DialogFragment dlg=new DatePickerFragment();
        dlg.show(getFragmentManager(),"Date Picker");
    }
}
