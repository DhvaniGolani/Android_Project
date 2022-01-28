package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Register extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText mName,mEmail,mPassword,mCPassword,mPhone,mAdd;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseDatabase firebaseDatabase;
    Task<Void> mRef;
    FirebaseUser firebaseUser;
    HashMap<String,Object> map;
    String e="Email",a="Address", s="State",c="City", p="Phone", i="ID" , n="Name",selected,selectedCity;
    Integer selectedNo;
    ArrayAdapter<CharSequence> adapter2;
    Spinner spinner2;
    String email,password,cpassword,name,phone,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Spinner spinner= (Spinner) findViewById(R.id.State);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.States,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(adapter);

        spinner2= (Spinner) findViewById(R.id.City);

        mName = findViewById(R.id.Name);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
        mCPassword = findViewById(R.id.ConfirmPassword);
        mPhone = findViewById(R.id.Phone);
        mAdd = findViewById(R.id.address);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.createText);
        email = mEmail.getText().toString().trim();
        password = mPassword.getText().toString().trim();
        cpassword = mCPassword.getText().toString().trim();
        name = mName.getText().toString().trim();
        phone = mPhone.getText().toString().trim();
        address = mAdd.getText().toString();

        fAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        progressBar = findViewById(R.id.progressBar);


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                String name = mName.getText().toString().trim();
                String phone = mPhone.getText().toString().trim();
                String address = mAdd.getText().toString();

                if (validation()){
                progressBar.setVisibility(View.VISIBLE);
                //Register the user in firebase
                fAuth.createUserWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Register.this,
                                    "User Created.", Toast.LENGTH_SHORT).show();
                            firebaseUser=fAuth.getCurrentUser();
                            map=new HashMap<>();

                            map.put(n, name);
                            map.put(e, firebaseUser.getEmail());
                            map.put(p, phone);
                            map.put(s,selected);
                            map.put(c,selectedCity);
                            map.put(a,address);
                            mRef=firebaseDatabase.getReference().child("User").child(firebaseUser.getUid()).updateChildren(map);
                            startActivity(new Intent(getApplicationContext(),
                                    MainActivity.class));
                        }
                        else{
                            Toast.makeText(Register.this,
                                    "Error!" + task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
                }

            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

        selectedNo = i;
        switch (i)
        {
            case 0:
                selected = "Gujarat";
                adapter2 = ArrayAdapter.createFromResource(this,R.array.GujCity,android.R.layout.simple_spinner_item);
                adapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (position){
                            case 0: selectedCity = "Vadodara";break;
                            case 1: selectedCity = "Gandhinagar"; break;
                            case 2: selectedCity = "Ahmedabad"; break;
                            case 3: selectedCity = "Surat"; break;
                            case 4: selectedCity = "Rajkot"; break;
                            case 5: selectedCity = "Bharuch"; break;
                            case 6: selectedCity = "Vapi"; break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinner2.setAdapter(adapter2);
                break;
            case 1:
                selected = "Maharashtra";
                adapter2 = ArrayAdapter.createFromResource(this,R.array.MahaCity,android.R.layout.simple_spinner_item);
                adapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (position){
                            case 0: selectedCity = "Mumbai";break;
                            case 1: selectedCity = "Pune"; break;
                            case 2: selectedCity = "Nagpur"; break;
                            case 3: selectedCity = "Nashik"; break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinner2.setAdapter(adapter2);
                break;
            case 2:
                selected = "Rajasthan";
                adapter2 = ArrayAdapter.createFromResource(this,R.array.RajCity,android.R.layout.simple_spinner_item);
                adapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (position){
                            case 0: selectedCity = "Udaipur";break;
                            case 1: selectedCity = "Ajmer"; break;
                            case 2: selectedCity = "Jaipur"; break;
                            case 3: selectedCity = "Kota"; break;
                            case 4: selectedCity = "Jodhpur"; break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinner2.setAdapter(adapter2);
                break;
            case 3:
                selected = "Madhya Pradesh";
                adapter2 = ArrayAdapter.createFromResource(this,R.array.MPCity,android.R.layout.simple_spinner_item);
                adapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
                spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        switch (position){
                            case 0: selectedCity = "Indore";break;
                            case 1: selectedCity = "Bhopal"; break;
                            case 2: selectedCity = "Gwalior"; break;
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                spinner2.setAdapter(adapter2);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(this, "Select State!", Toast.LENGTH_SHORT).show();
    }

    public  boolean validation(){
        boolean valid = true;
        if(TextUtils.isEmpty(email)){
            mEmail.setError("Email is Required.");
            mEmail.requestFocus();
            valid = false;
        }
        if(! Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            mEmail.setError("Please Enter valid Email address");
            mEmail.requestFocus();
            valid = false;
        }
        if(TextUtils.isEmpty(password)){
            mPassword.setError("Password is Required.");
            mPassword.requestFocus();
            valid = false;
        }
        if (TextUtils.isEmpty(cpassword)){
            mCPassword.setError("Required.");
            mCPassword.requestFocus();
            valid = false;
        }
        if (cpassword != password){
            mCPassword.setError("Password Not Matching");
            mPassword.setError("Password Not Matching");
            mCPassword.requestFocus();
            mPassword.requestFocus();
            valid = false;
        }
        if (TextUtils.isEmpty(address)){
            mAdd.setError("Address Required.");
            mAdd.requestFocus();
            valid=false;
        }
        if (TextUtils.isEmpty(phone)){
            mPhone.setError("Phone Number Required.");
            mPhone.requestFocus();
            valid=false;
        }
        if (TextUtils.isEmpty(name)){
            mName.setError("Name Required.");
            mName.requestFocus();
            valid=false;
        }
        if(password.length() < 5){
            mPassword.setError("Password Must be >= 5 Characters.");
            valid = false;
        }
        return valid;
    }
}