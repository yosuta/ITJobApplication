package com.example.lenovo.jobapp1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    EditText eEmail,ePassword,eName;
    TextView tSignin,tEnterprise;
    Button bRegister;

    private ProgressDialog progressDialog;
    DatabaseReference databaseUsers;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        databaseUsers=FirebaseDatabase.getInstance().getReference("user");
        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }
        progressDialog=new ProgressDialog(this);
        bRegister=(Button)findViewById(R.id.btnRegister);
        eEmail=(EditText)findViewById(R.id.editEmail);
        ePassword=(EditText)findViewById(R.id.editPassword);
        eName=(EditText)findViewById(R.id.editName);
        tSignin=(TextView)findViewById(R.id.txtSignin);
        tEnterprise=(TextView)findViewById(R.id.txtEnterprise);

        bRegister.setOnClickListener(this);
        tSignin.setOnClickListener(this);
        tEnterprise.setOnClickListener(this);
    }
    private void registerUser(){
        String email=eEmail.getText().toString().trim();
        String password=ePassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("Registering User...");
        progressDialog.show();
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    FirebaseUser user = auth.getCurrentUser();
                    finish();
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                    Toast.makeText(RegisterActivity.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(RegisterActivity.this,"Could not register.",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        if(v==bRegister){
            registerUser();
        }
        if(v==tSignin){
            startActivity(new Intent(this,LoginActivity.class));
        }
        if(v==tEnterprise){
            User user=new User();
            user.setUserType("1");
            Intent intent=new Intent(RegisterActivity.this,AddNewJobActivity.class);
            startActivity(intent);
        }
    }
}
