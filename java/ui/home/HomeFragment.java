package com.example.login.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.login.Appliance_Repair;
import com.example.login.Carpenter;
import com.example.login.Cleaning;
import com.example.login.Electrical;
import com.example.login.Plumbing;
import com.example.login.R;

public class HomeFragment extends Fragment {

    //private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //homeViewModel =
                //new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        RelativeLayout rl1 = root.findViewById(R.id.RL1);
        rl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), Electrical.class);
                startActivity(intent);
            }
        });
        RelativeLayout rl2 = root.findViewById(R.id.RL2);
        rl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), Plumbing.class);
                startActivity(intent);
            }
        });
        RelativeLayout rl3 = root.findViewById(R.id.RL3);
        rl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), Carpenter.class);
                startActivity(intent);
            }
        });
        RelativeLayout rl4 = root.findViewById(R.id.RL4);
        rl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), Appliance_Repair.class);
                startActivity(intent);
            }
        });
        RelativeLayout rl5 = root.findViewById(R.id.RL5);
        rl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(root.getContext(), Cleaning.class);
                startActivity(intent);
            }
        });
        return root;
    }
}