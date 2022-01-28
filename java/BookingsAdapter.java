package com.example.login;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.ui.dashboard.List_Data1;
import com.example.login.ui.dashboard.MyAdapter1;

import java.util.List;

public class BookingsAdapter extends RecyclerView.Adapter<BookingsAdapter.ViewHolder>{
    private List<list_bookings> list_bookings;

    public BookingsAdapter(List<com.example.login.list_bookings> list_bookings) {
        this.list_bookings = list_bookings;
    }

    @NonNull
    @Override
    public BookingsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_bookings,parent,false);
        return new BookingsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingsAdapter.ViewHolder holder, int position) {
        list_bookings lb = list_bookings.get(position);
        holder.txtservtype.setText(lb.getServiceType());
        holder.txtfname.setText(lb.getUserName().substring(0,1));
        holder.txtuname.setText(lb.getUserName());
        holder.txttime.setText(lb.gettime());
        holder.txtdate.setText(lb.getDate());
    }

    @Override
    public int getItemCount() {
        return list_bookings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtservtype, txtuname, txtfname,txttime,txtdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtservtype = (TextView) itemView.findViewById(R.id.serviceType);
            txtuname = (TextView) itemView.findViewById(R.id.Uname);
            txtfname = (TextView) itemView.findViewById(R.id.fname);
            txtdate = (TextView) itemView.findViewById(R.id.bookdate);
            txttime = (TextView) itemView.findViewById(R.id.booktime);
        }
    }
}
