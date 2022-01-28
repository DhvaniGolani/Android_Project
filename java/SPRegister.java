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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class SPRegister extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    Integer selectedNo;
    ArrayAdapter<CharSequence> adapter2;
    Spinner spinner2;
    EditText name,uname,pass,OffAdd,phone,cpass;
    String selected,selectedCity,selected1,SPUserName,SPName,SPPass,SPPhone,SPAdd,SPCPass;
    Task<Void> mRef;
    FirebaseUser firebaseUser;
    HashMap<String,Object> map;
    FirebaseAuth fAuth;
    FirebaseDatabase firebaseDatabase;
    String e="Email", a="Address", p="Phone", i="ID",n="Name",s="State",c="City";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_p_register);
        name = findViewById(R.id.SPName);
        uname = findViewById(R.id.SPUserName);
        pass = findViewById(R.id.SPPassword);
        cpass = findViewById(R.id.SPConfirmPassword);
        phone = findViewById(R.id.SPPhone);
        OffAdd = findViewById(R.id.SPAdd);
        fAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        SPName = name.getText().toString();
        SPUserName = uname.getText().toString();
        SPPass = pass.getText().toString();
        SPPhone = phone.getText().toString();
        SPAdd = OffAdd.getText().toString();
        SPCPass = cpass.getText().toString() ;

        Spinner spinner= (Spinner) findViewById(R.id.ServiceTypeSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.Services,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setOnItemSelectedListener(this);
        spinner.setAdapter(adapter);

        spinner2= (Spinner) findViewById(R.id.City);

        Spinner spinner1= (Spinner) findViewById(R.id.State);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,R.array.States,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                selectedNo = i;
                switch (i)
                {
                    case 0:
                        selected1 = "Gujarat";
                        adapter2 = ArrayAdapter.createFromResource(getApplicationContext(),R.array.GujCity,android.R.layout.simple_spinner_item);
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
                        selected1 = "Maharashtra";
                        adapter2 = ArrayAdapter.createFromResource(getApplicationContext(),R.array.MahaCity,android.R.layout.simple_spinner_item);
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
                        selected1 = "Rajasthan";
                        adapter2 = ArrayAdapter.createFromResource(getApplicationContext(),R.array.RajCity,android.R.layout.simple_spinner_item);
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
                        selected1 = "Madhya Pradesh";
                        adapter2 = ArrayAdapter.createFromResource(getApplicationContext(),R.array.MPCity,android.R.layout.simple_spinner_item);
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

            }
        });
        spinner1.setAdapter(adapter1);



        Button submit = (Button) findViewById(R.id.Submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String SPName = name.getText().toString();
                String SPUserName = uname.getText().toString();
                String SPPass = pass.getText().toString();
                String SPPhone = phone.getText().toString();
                String SPAdd = OffAdd.getText().toString();

                if (validation()){
                fAuth.createUserWithEmailAndPassword(SPUserName,SPPass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(SPRegister.this,
                                            "User Created.", Toast.LENGTH_SHORT).show();
                                    firebaseUser=fAuth.getCurrentUser();
                                    map=new HashMap<>();
                                    //map.put("Id " , firebaseUser.getUid());
                                    map.put(n, SPName);
                                    map.put(e, firebaseUser.getEmail());
                                    map.put(a, SPAdd);
                                    map.put(s,selected1);
                                    map.put(c,selectedCity);
                                    map.put(p, SPPhone);
                                    mRef=firebaseDatabase.getReference().child("ServiceProvider").child(selected).child(firebaseUser.getUid()).updateChildren(map);
                                    startActivity(new Intent(getApplicationContext(),
                                            MainActivity.class));
                                }
                                else{
                                    Toast.makeText(SPRegister.this,
                                            "Error!" + task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                    //progressBar.setVisibility(View.GONE);
                                }
                            }
                        });
                }
            }
        });
    }

    public  boolean validation(){
        boolean valid = true;
        if(TextUtils.isEmpty(SPUserName)){
            uname.setError("Email is Required.");
            uname.requestFocus();
            valid = false;
        }
        if(! Patterns.EMAIL_ADDRESS.matcher(SPUserName).matches())
        {
            uname.setError("Please Enter valid Email address");
            uname.requestFocus();
            valid = false;
        }
        if(TextUtils.isEmpty(SPPass)){
            pass.setError("Password is Required.");
            pass.requestFocus();
            valid = false;
        }
        if (TextUtils.isEmpty(SPCPass)){
            cpass.setError("Required Field.");
            cpass.requestFocus();
            valid = false;
        }
        if (SPCPass != SPPass){
            pass.setError("Password Not Matching");
            cpass.setError("Password Not Matching");
            pass.requestFocus();
            cpass.requestFocus();
            valid = false;
        }
        if (TextUtils.isEmpty(SPAdd)){
            OffAdd.setError("Address Required.");
            OffAdd.requestFocus();
            valid=false;
        }
        if (TextUtils.isEmpty(SPPhone)){
            phone.setError("Phone Number Required.");
            phone.requestFocus();
            valid=false;
        }
        if (TextUtils.isEmpty(SPName)){
            name.setError("Name Required.");
            name.requestFocus();
            valid=false;
        }
        if(SPPass.length() < 5){
            pass.setError("Password Must be >= 5 Characters.");
            valid = false;
        }
        return valid;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {

        selectedNo = i;
        switch (i)
        {
            case 0:
                selected = "Electrical";
                break;
            case 1:
                selected = "Plumbing";
                break;
            case 2:
                selected = "Carpenter";
                break;
            case 3:
                selected = "Appliance Repair";
                break;
            case 4:
                selected = "Cleaning";
                break;

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}