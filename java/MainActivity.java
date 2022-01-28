package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity {
    FirebaseAuth mAuth;
    EditText memail,mpass;
    Button login;
    String email,pass;
    ProgressBar pg;
    TextView fpass,reg;
    FirebaseUser firebaseUser;
    String Username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("HomeBuddy");
        setTitleColor(R.color.whiteTextColor);
        /*Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setData(Uri.parse("9601935777"));
        smsIntent.putExtra("sms_body","Hiiii");
        if (smsIntent.resolveActivity(getPackageManager())!=null){
            startActivity(smsIntent);
        }
        else {
            Toast.makeText(this, "jhj", Toast.LENGTH_SHORT).show();
        }*/
        //PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),0,intent,0);
        //SmsManager smsManager=SmsManager.getDefault();
        //smsManager.sendTextMessage("9601935777","9427538182","Service Provider Appointed",null,null);

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
                                Toast.makeText(MainActivity.this, "Email Sent!!", Toast.LENGTH_SHORT).show();
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
                Intent intent1 = new Intent(MainActivity.this, Register.class);
                startActivity(intent1);
            }
        });
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
                            for (DataSnapshot dataSnapshot : snapshot.child("User").getChildren()) {
                                Username = dataSnapshot.child("Email").getValue().toString();
                                if (email.equals(Username)) {
                                    //Toast.makeText(MainActivity.this, "User Logged In!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, BottomNav.class);
                                    startActivity(intent);
                                }
                                if (!email.equals(Username)){
                                    Snackbar snackbar = Snackbar.make(view , "Not A User!!",Snackbar.LENGTH_SHORT);
                                    snackbar.setBackgroundTint(getResources().getColor(R.color.colorPrimaryDark))
                                            .setActionTextColor(getResources().getColor(R.color.white));
                                    snackbar.show();
                                    //Toast.makeText(MainActivity.this, "User Logged In!", Toast.LENGTH_SHORT).show();
                                }
                            }
                            /*for (DataSnapshot dataSnapshot : snapshot.child("ServiceProvider").getChildren()) {
                                Username = dataSnapshot.child("Email").getValue().toString();
                                if (email.equals(Username)) {
                                    Toast.makeText(MainActivity.this, "SP Logged In!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(MainActivity.this, BottomNav.class);
                                    startActivity(intent);
                                }
                            }*/
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });

                    //Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(MainActivity.this, BottomNav.class);
                    //startActivity(intent);
                }
            }).addOnFailureListener(this, e -> {
                pg.setVisibility(View.GONE);
                Snackbar snackbar = Snackbar.make(view , "Error: " + e.getMessage(),Snackbar.LENGTH_SHORT);
                snackbar.setBackgroundTint(getResources().getColor(R.color.colorPrimaryDark))
                        .setActionTextColor(getResources().getColor(R.color.white));
                snackbar.show();
                //Toast.makeText(MainActivity.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                //return;
            });
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.SPRegister:
                Intent intent = new Intent(this,SPRegister.class);
                startActivity(intent);
                //Toast.makeText(this, "SPREGISTER", Toast.LENGTH_SHORT).show();
                break;
            case R.id.SPLogin:
                Intent intent1 = new Intent(this,SPLogin.class);
                startActivity(intent1);
                //Toast.makeText(this, "SPLogin", Toast.LENGTH_SHORT).show();
                break;
            case R.id.AdminLogin:
                Intent intent2 = new Intent(this,AdminLogin.class);
                startActivity(intent2);
                //Toast.makeText(this, "Admin", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}