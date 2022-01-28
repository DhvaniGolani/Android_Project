package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SPLogin extends AppCompatActivity {
    String email,pass;
    FirebaseAuth mAuth;
    ProgressBar pg;
    TextView fpass,reg;
    EditText memail,mpass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_p_login);
        setTitle("Login As Service Provider");
        mAuth= FirebaseAuth.getInstance();
        memail=(EditText)findViewById(R.id.email);
        mpass=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        email=memail.getText().toString();
        pass=mpass.getText().toString();
        pg = findViewById(R.id.progressBar);
        fpass=findViewById(R.id.ForgotPass);
        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //firebaseUser=mAuth.getCurrentUser();
                email=memail.getText().toString();
                if (email.isEmpty()){
                    memail.setError("Enter Email!!");
                    memail.requestFocus();
                }
                else {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SPLogin.this, "Email Sent!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        reg = findViewById(R.id.registerBtn);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(SPLogin.this, SPRegister.class);
                startActivity(intent1);
            }
        });
    }
    public boolean validation()
    {
        //Toast.makeText(MainActivity.this , "Inside Validation" , Toast.LENGTH_SHORT).show();
        boolean valid = true;
        if(email.isEmpty())
        {
            memail.setError("Email is required");
            memail.requestFocus();
            valid = false;
        }
        if(! Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            memail.setError("Please Enter valid Email address");
            memail.requestFocus();
            valid = false;
        }
        if(pass.isEmpty())
        {
            mpass.setError("Please Enter the password");
            mpass.requestFocus();
            valid = false;
        }
        pg.setVisibility(View.GONE);
        return valid;
    }

    public void login(View view) {
    }
}