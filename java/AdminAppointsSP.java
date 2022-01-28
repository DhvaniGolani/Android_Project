package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.login.ui.dashboard.List_Data1;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AdminAppointsSP extends AppCompatActivity {
    private List<list_bookings> list_bookingsList;
    private BookingsAdapter bookingsAdapter;
    FirebaseAuth mAuth;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_appoints_s_p);
        RecyclerView rv = (RecyclerView) findViewById(R.id.recyclerBookings);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        list_bookingsList = new ArrayList<>();

        DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("Booking");
        nm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    list_bookings list_bookings = snapshot.getValue(list_bookings.class);
                    list_bookingsList.add(list_bookings);
                    bookingsAdapter = new BookingsAdapter(list_bookingsList);
                    rv.setAdapter(bookingsAdapter);
                }
                else {
                    Toast.makeText(AdminAppointsSP.this, "No Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}