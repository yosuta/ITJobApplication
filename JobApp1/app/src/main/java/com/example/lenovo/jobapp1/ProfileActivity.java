package com.example.lenovo.jobapp1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    Button bLogout;
    TextView tEmail;
    private  DatabaseReference databaseReference;
    private EditText editname,editBio,editTitle,editLanguage;
    private Button btnSaveinfo,btnAddphoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        FirebaseUser user1=firebaseAuth.getCurrentUser();

        databaseReference=FirebaseDatabase.getInstance().getReference("user");

        editname=(EditText)findViewById(R.id.eName);
        editBio=(EditText)findViewById(R.id.eBio);
        editTitle=(EditText)findViewById(R.id.eTitle);
        editLanguage=(EditText)findViewById(R.id.eLan);
        btnSaveinfo=(Button)findViewById(R.id.btnSave);
        btnAddphoto=(Button)findViewById(R.id.btnPhoto);
        bLogout=(Button)findViewById(R.id.btnLogout);
        tEmail=(TextView)findViewById(R.id.txtEmail);
        tEmail.setText("Welcome "+user1.getEmail());
        bLogout.setOnClickListener(this);
        btnSaveinfo.setOnClickListener(this);
        btnAddphoto.setOnClickListener(this);
    }
        private void saveUserInfo(){
                String name=editname.getText().toString().trim();
                String bio=editBio.getText().toString().trim();
                String title=editTitle.getText().toString().trim();
                String language=editLanguage.getText().toString().trim();
                User user=new User(name,bio,title,language);
                FirebaseUser user1=firebaseAuth.getCurrentUser();
                databaseReference.child(user1.getUid()).setValue(user);
                Toast.makeText(this,"Information saved",Toast.LENGTH_LONG).show();
        }
        private void addNewPhoto(){
            Intent intent=new Intent(ProfileActivity.this,PhotoActivity.class);
            startActivity(intent);
        }

    @Override
    public void onClick(View v) {
        if(v==bLogout){
            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this,LoginActivity.class));
        }
        if(v==btnSaveinfo){
            saveUserInfo();
        }
        if(v==btnAddphoto){
            addNewPhoto();
        }
    }
}
