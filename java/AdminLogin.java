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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AdminLogin extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText memail,mpass;
    Button login;
    String email,pass;
    ProgressBar pg;
    TextView fpass;
    String Username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        setTitle("Admin Login");
        mAuth=FirebaseAuth.getInstance();
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
                                Toast.makeText(AdminLogin.this, "Email Sent!!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
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
        email=memail.getText().toString();
        pass=mpass.getText().toString();
        if (validation()) {
            pg.setVisibility(View.VISIBLE);
            mAuth.signInWithEmailAndPassword(email, pass).addOnSuccessListener(this, new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    pg.setVisibility(View.GONE);
                    DatabaseReference nm= FirebaseDatabase.getInstance().getReference();
                    nm.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (DataSnapshot dataSnapshot : snapshot.child("Admin").getChildren()) {
                                Username = dataSnapshot.child("Email").getValue().toString();
                                if (email.equals(Username)) {
                                    Toast.makeText(AdminLogin.this, "Admin Logged In!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(AdminLogin.this, AdminAppointsSP.class);
                                    startActivity(intent);
                                }
                                if (!email.equals(Username)){
                                    Snackbar snackbar = Snackbar.make(view , "Not A Admin!!",Snackbar.LENGTH_SHORT);
                                    snackbar.setBackgroundTint(getResources().getColor(R.color.colorPrimaryDark))
                                            .setActionTextColor(getResources().getColor(R.color.white));
                                    snackbar.show();
                                    //Toast.makeText(MainActivity.this, "User Logged In!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
            }).addOnFailureListener(this, e -> {
                pg.setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(view , "Error: " + e.getMessage(),Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(getResources().getColor(R.color.colorPrimaryDark))
                        .setActionTextColor(getResources().getColor(R.color.white));
                snackbar.show();
            });
        }
    }
}