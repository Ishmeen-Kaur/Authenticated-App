package com.example.authenticatedapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    public static final String TAG = "Succes message";
    EditText f,e,ph,ps;
    Button register, login;
    FirebaseAuth fa;
    ProgressBar pb;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        f=(EditText)findViewById(R.id.fullname);
        e=(EditText)findViewById(R.id.email);
        ph=(EditText)findViewById(R.id.editText3);
        ps=(EditText)findViewById(R.id.editText4);
        register=(Button)findViewById(R.id.button);
        login=(Button)findViewById(R.id.button2);
        pb=(ProgressBar)findViewById(R.id.progressBar);


        fa= FirebaseAuth.getInstance();


        if(fa.getCurrentUser()!=null){
            Intent i= new Intent(getBaseContext(),MainActivity.class);
            startActivity(i);
            finish();
        }

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String em=e.getText().toString().trim();
                final String psd=ps.getText().toString().trim();

                if(TextUtils.isEmpty(em)){
                    e.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(psd)){
                    ps.setError("Password is required");
                    return;
                }
                if(psd.length()<6){
                    ps.setError("Password must have 6 characters or more");
                    return;
                }

                pb.setVisibility(View.VISIBLE);

                fa.createUserWithEmailAndPassword(em,psd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();


                            Intent i= new Intent(getBaseContext(),MainActivity.class);
                            startActivity(i);

                        }else{
                            Toast.makeText(Register.this, "Error ! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            pb.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),Login.class);
                startActivity(i);
            }
        });


    }
}
