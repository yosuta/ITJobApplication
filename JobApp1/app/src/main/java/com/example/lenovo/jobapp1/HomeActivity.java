package com.example.lenovo.jobapp1;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.PropertyName;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{
    ListView listView;
    List<Job> jobList;
    //Button btnAdd;
    TextView tProfile,tJobs;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        databaseReference= FirebaseDatabase.getInstance().getReference("jobs");
        listView=(ListView)findViewById(R.id.listviewjobs);
        jobList=new ArrayList<>();
        tProfile=(TextView)findViewById(R.id.txtProfile);
        tJobs=(TextView)findViewById(R.id.txtJobs);
        //btnAdd=(Button)findViewById(R.id.btnAddNewJob);
        tProfile.setOnClickListener(this);
       // btnAdd.setOnClickListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getApplicationContext(),JobDetailsActivity.class);
                Job job= (Job) listView.getAdapter().getItem(position);
                String cname=job.getCompanyName();
                i.putExtra("key",cname);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                jobList.clear();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    Job job=dataSnapshot1.getValue(Job.class);
                    jobList.add(job);
                }
                JobList adapter=new JobList(HomeActivity.this,jobList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v==tProfile){
            Intent in=new Intent(HomeActivity.this, ProfileActivity.class);
            startActivity(in);
        }
       /* if(v==btnAdd){
            Intent intent=new Intent(HomeActivity.this,AddNewJobActivity.class);
            startActivity(intent);
        }*/
    }
}
