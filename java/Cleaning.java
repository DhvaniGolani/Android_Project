package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.snackbar.Snackbar;

public class Cleaning extends AppCompatActivity {
    Button add,add1,add2;
    String selectedAct="Cleaning";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cleaning);
        add=findViewById(R.id.addButt);
        add1=findViewById(R.id.addButt1);
        add2=findViewById(R.id.addButt2);
        add.setOnClickListener(onClickListener);
        add1.setOnClickListener(onClickListener);
        add2.setOnClickListener(onClickListener);
    }
    private  View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.addButt:
                    add.setText("Added");
                    Drawable img = add.getContext().getResources().getDrawable(R.drawable.ic_baseline_check_24);
                    add.setCompoundDrawablesWithIntrinsicBounds(null,null,img,null);
                    showSnackbar(v);
                    break;
                case  R.id.addButt1:
                    add1.setText("Added");
                    Drawable img1 = add1.getContext().getResources().getDrawable(R.drawable.ic_baseline_check_24);
                    add1.setCompoundDrawablesWithIntrinsicBounds(null,null,img1,null);
                    showSnackbar(v);
                    break;
                case R.id.addButt2:
                    add2.setText("Added");
                    Drawable img2 = add2.getContext().getResources().getDrawable(R.drawable.ic_baseline_check_24);
                    add2.setCompoundDrawablesWithIntrinsicBounds(null,null,img2,null);
                    showSnackbar(v);
                    break;
            }
        }
    };

    public void showSnackbar(View view){
        Snackbar snackbar = Snackbar.make(view , "Service Added",Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Continue", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(view.getContext() , Booking.class);
                intent.putExtra("Act",selectedAct);
                startActivity(intent);
                finish();
            }
        });
        snackbar.setBackgroundTint(getResources().getColor(R.color.colorPrimaryDark))
                .setActionTextColor(getResources().getColor(R.color.white));
        snackbar.show();
    }
}