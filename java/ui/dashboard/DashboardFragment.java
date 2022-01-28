package com.example.login.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    private List<List_Data1> list_data1List;
    private RecyclerView rv;
    private MyAdapter1 adapter;
    FirebaseUser firebaseUser;
    FirebaseAuth mAuth;
    String UID,BID;
    DatabaseReference mRef;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        rv=root.findViewById(R.id.bookings);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(inflater.getContext()));
        list_data1List =new ArrayList<>();
        mAuth= FirebaseAuth.getInstance();
        firebaseUser=mAuth.getCurrentUser();
        UID = firebaseUser.getUid();
        DatabaseReference nm= FirebaseDatabase.getInstance().getReference().child("User").child(UID).child("Booking");
        nm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot npsnapshot : snapshot.getChildren()) {
                    BID = npsnapshot.child("BookingID").getValue().toString();
                    mRef = FirebaseDatabase.getInstance().getReference().child("Booking").child(BID);
                    mRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                List_Data1 l = snapshot.getValue(List_Data1.class);
                                list_data1List.add(l);
                                adapter = new MyAdapter1(list_data1List);
                                rv.setAdapter(adapter);
                            }

                            else {
                            Toast.makeText(getContext(), "No Data Available", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                        }
                    });
                }
                    //adapter = new MyAdapter1(list_data1List);
                    //rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return root;
    }
}