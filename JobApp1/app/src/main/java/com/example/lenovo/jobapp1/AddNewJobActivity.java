package com.example.lenovo.jobapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNewJobActivity extends AppCompatActivity {
    EditText eAbout,eAddress,eCompanyname,eTitle;
    Button btnAddJ;

    DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_job);
        eAbout=(EditText)findViewById(R.id.editA);
        eAddress=(EditText)findViewById(R.id.editAd);
        eCompanyname=(EditText)findViewById(R.id.editC);
        eTitle=(EditText)findViewById(R.id.editT);
        btnAddJ=(Button)findViewById(R.id.btnAddJob);
        databaseReference= FirebaseDatabase.getInstance().getReference("jobs");
        btnAddJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addJob();
            }
        });
    }

    private void addJob(){
        String companyname=eCompanyname.getText().toString().trim();
        String about=eAbout.getText().toString().trim();
        String address=eAddress.getText().toString().trim();
        String title=eTitle.getText().toString().trim();
        if(!TextUtils.isEmpty(companyname) && !TextUtils.isEmpty(title)){
            String id= databaseReference.push().getKey();
            Job job=new Job(id,companyname,about,title,address);
            databaseReference.child(id).setValue(job);
            Toast.makeText(this,"Job added",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Enter company name and title",Toast.LENGTH_SHORT).show();
        }
    }
}
