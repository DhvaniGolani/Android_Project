package com.example.login.ui.dashboard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;

import java.util.List;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.ViewHolder>{
    private List<List_Data1> list_data1List;

    public MyAdapter1(List<List_Data1> list_data1List) {
        this.list_data1List =list_data1List;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_data1,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List_Data1 ld= list_data1List.get(position);
        holder.txtservtype.setText(ld.getServiceType());
        holder.txtstype.setText(ld.getServiceType().substring(0,1));
        holder.txtstatus.setText(ld.getStatus());
        holder.txttime.setText(ld.gettime());
        holder.txtdate.setText(ld.getDate());
    }

    @Override
    public int getItemCount() {
        return list_data1List.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtservtype, txtstatus, txtstype,txttime,txtdate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtservtype = (TextView) itemView.findViewById(R.id.servtype);
            txtstatus = (TextView) itemView.findViewById(R.id.status);
            txtstype = (TextView) itemView.findViewById(R.id.fname);
            txtdate = (TextView) itemView.findViewById(R.id.date);
            txttime = (TextView) itemView.findViewById(R.id.showtime);
        }
    }
}
