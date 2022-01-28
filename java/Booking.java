package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.HashMap;

public class Booking extends AppCompatActivity {
    TextView tv;
    Integer selectedNo;
    FirebaseUser firebaseUser;
    FirebaseAuth mAuth;
    Task<Void> mRef,mRef1;
    String UserId,UserName,UserAdd,UserState,UCity,UState,UserCity,selected,selectedCity,selectedTime,selectedDate;
    EditText address;
    TextView state;
    TextView city;
    EditText txtDate;
    ArrayAdapter<CharSequence> adapter2;
    ArrayAdapter<CharSequence> adaptert;
    Spinner spinner2,time;
    String add;
    ImageButton changeCity,changeState;
    Button btnDatePicker,btnConfirm,btnCancel;
    private int mYear, mMonth, mDay;
    HashMap<String,Object> map,map1;
    String b="BookingID",bid;
    String n="UserName",uid="UserId",serType= "ServiceType",a="Address",c="City",s="State",t="time",st="Status",status="Pending",d="Date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        Intent intent=getIntent();
        String Service = intent.getStringExtra("Act");
        //tv.setText(Service);
        spinner2= (Spinner) findViewById(R.id.City);

        btnDatePicker = findViewById(R.id.btn_date);
        txtDate = findViewById(R.id.txt_date);
        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Booking.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
                datePickerDialog.show();
            }
        });

        Spinner spinner= (Spinner) findViewById(R.id.State);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.States,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id) {
                selectedNo = i;
                switch (i)
                {
                    case 0:
                        selected = "Gujarat";
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
                        selected = "Maharashtra";
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
                        selected = "Rajasthan";
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
                        selected = "Madhya Pradesh";
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
        spinner.setAdapter(adapter);

        time = (Spinner) findViewById(R.id.timeSeletor);
        adaptert = ArrayAdapter.createFromResource(this,R.array.timeRange,android.R.layout.simple_spinner_item);
        adaptert.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0 :
                        selectedTime="09:00 AM - 10:00 AM";
                        break;
                    case 1 :
                        selectedTime="10:00 AM - 11:00 AM";
                        break;
                    case 2 :
                        selectedTime="11:00 AM - 12:00 PM";
                        break;
                    case 3 :
                        selectedTime="13:00 PM - 14:00 PM";
                        break;
                    case 4 :
                        selectedTime="14:00 PM - 15:00 PM";
                        break;
                    case 5 :
                        selectedTime="15:00 PM - 16:00 PM";
                        break;
                    case 6 :
                        selectedTime="16:00 PM - 17:00 PM";
                        break;
                    case 7 :
                        selectedTime="17:00 PM - 18:00 PM";
                        break;
                    case 8 :
                        selectedTime="18:00 PM - 19:00 PM";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        time.setAdapter(adaptert);

        //tv = findViewById(R.id.textView);
        address=findViewById(R.id.address);
        city=findViewById(R.id.txtCity);
        state = findViewById(R.id.txtState);

        changeCity = findViewById(R.id.changeCity);
        changeState = findViewById(R.id.changeState);

        changeState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner.setVisibility(View.VISIBLE);
                //state.setText(selected);
                state.setVisibility(View.GONE);
                spinner2.setVisibility(View.VISIBLE);
                city.setVisibility(View.GONE);
            }
        });
        changeCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner2.setVisibility(View.VISIBLE);
                city.setVisibility(View.GONE);
                String getState = state.getText().toString();
                switch (getState){
                    case "Gujarat":
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
                    case "Maharashtra":
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
                    case "Rajasthan":
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
                    case "Madhya Pradesh":
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
        });

        mAuth= FirebaseAuth.getInstance();
        firebaseUser=mAuth.getCurrentUser();
        UserId = firebaseUser.getUid();
        DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("User").child(UserId);
        nm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserAdd = snapshot.child("Address").getValue().toString();
                address.setText(UserAdd);
                UserState = snapshot.child("State").getValue().toString();
                UserCity = snapshot.child("City").getValue().toString();
                state.setText(UserState);
                city.setText(UserCity);
                UserName = snapshot.child("Name").getValue().toString();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btnConfirm = findViewById(R.id.btn_confirm);
        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add = address.getText().toString().trim();
                selectedDate = txtDate.getText().toString();

                if (validation()){
                    map=new HashMap<>();
                    map.put(n,UserName);
                    map.put(uid,UserId);
                    map.put(a,add);
                    map.put(c,selectedCity);
                    map.put(s,selected);
                    map.put(serType,Service);
                    map.put(st,status);
                    map.put(t,selectedTime);
                    map.put(d,selectedDate);
                    FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
                    DatabaseReference db= firebaseDatabase.getReference().child("Booking");
                    String getbid = db.push().getKey();
                    mRef = FirebaseDatabase.getInstance().getReference().child("Booking").child(getbid).updateChildren(map);
                    //String getbid = FirebaseDatabase.getInstance().getReference().child("Booking").push().getKey();
                    map1=new HashMap<>();
                    map1.put(b,getbid);
                    mRef1 = FirebaseDatabase.getInstance().getReference().child("User").child(UserId).child("Booking").push().updateChildren(map1);
                    Intent intent1 = new Intent(Booking.this,BottomNav.class);
                    startActivity(intent1);
                }
            }
        });

        btnCancel = (Button) findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(getApplicationContext(),BottomNav.class);
                startActivity(intent3);
            }
        });

    }

    public  boolean validation() {
        boolean valid = true;
        add = address.getText().toString().trim();
        selectedDate = txtDate.getText().toString();
        if (TextUtils.isEmpty(add)){
            address.setError("Address is Required.");
            address.requestFocus();
        }
        /*if (!changeState.callOnClick()){
            selected = state.getText().toString();
        }
        if (!changeCity.callOnClick()){
            selectedCity = city.getText().toString();
        }*/
        if (TextUtils.isEmpty(selectedDate)){
            txtDate.setError("Date Required!!!");
            txtDate.requestFocus();
            valid= false;
        }
        if (TextUtils.isEmpty(selectedTime)){
            time.requestFocus();
            valid= false;
        }
        return valid;
    }
}