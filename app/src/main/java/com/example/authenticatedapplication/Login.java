package com.example.authenticatedapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    EditText e,p;
    Button l;
    TextView c;
    ProgressBar pb;
    FirebaseAuth fa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        e=(EditText)findViewById(R.id.email);
        p=(EditText)findViewById(R.id.editText3);
        l=(Button)findViewById(R.id.button);
        pb=(ProgressBar)findViewById(R.id.progressBar2);
        c=(TextView)findViewById(R.id.textView4);

        fa= FirebaseAuth.getInstance();

        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em=e.getText().toString().trim();
                String psd=p.getText().toString().trim();
                if(TextUtils.isEmpty(em)){
                    e.setError("Email is Required");
                    return;
                }
                if(TextUtils.isEmpty(psd)){
                    p.setError("Password is required");
                    return;
                }
                if(psd.length()<6){
                    p.setError("Password must have 6 characters or more");
                    return;
                }

                pb.setVisibility(View.VISIBLE);



                fa.signInWithEmailAndPassword(em,psd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            Intent i= new Intent(getBaseContext(),MainActivity.class);
                            startActivity(i);
                        }else{
                            Toast.makeText(Login .this, "Error ! "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            pb.setVisibility(View.GONE);
                        }

                    }
                });

            }
        });

        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getBaseContext(),Register.class);
                startActivity(i);
            }
        });






    }
}
