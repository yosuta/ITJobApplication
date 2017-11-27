package com.example.lenovo.jobapp1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class JobDetailsActivity extends AppCompatActivity {
    TextView title,about,address,companyname,tApps;
    Button btnapply;
    DatabaseReference databaseReference,databaseReference1;
    List<String> apps;
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        databaseReference= FirebaseDatabase.getInstance().getReference().child("jobs");
        title=(TextView)findViewById(R.id.tTitle);
        about=(TextView)findViewById(R.id.textAbout);
        address=(TextView)findViewById(R.id.textAddress);
        companyname=(TextView)findViewById(R.id.textCompanyname);
        tApps=(TextView)findViewById(R.id.textApps);
        btnapply=(Button)findViewById(R.id.btnApply);
        apps=new ArrayList<>();

        String key=auth.getInstance().getCurrentUser().getUid();

        databaseReference1=FirebaseDatabase.getInstance().getReference().child("user").child(key).getParent().child(key).child("applies");

        final String cname=getIntent().getStringExtra("key");

        databaseReference.orderByChild("companyName").equalTo(cname).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Job value = child.getValue(Job.class);
                    title.setText(value.getTitle().toString().trim());
                    about.setText(value.getAbout().toString().trim());
                    address.setText(value.getAddress().toString().trim());
                    companyname.setText(value.getCompanyName().toString().trim());
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnapply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference1.child(cname).push().setValue(cname);
                Toast.makeText(JobDetailsActivity.this,cname+" applied.",Toast.LENGTH_LONG).show();
                }
        });
    }
    }


