package com.example.nouraalsaawisproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList<Student> students;

    public MyAdapter(Context context, ArrayList<Student> students) {
        this.context = context;
        this.students = students;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.student_item, parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Student student=students.get(position);
        holder.tvID.setText(student.getId());
        holder.tvName.setText(student.getName());
        holder.tvFatherName.setText(student.getFatherName());
        holder.tvSurname.setText(student.getSurname());
        holder.tvNatID.setText(student.getNatId());
        holder.tvDoB.setText(student.getDob());
        holder.tvGender.setText(student.getGender());
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvID, tvName, tvFatherName, tvSurname, tvNatID, tvDoB, tvGender;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvID=itemView.findViewById(R.id.tvID);
            tvName=itemView.findViewById(R.id.tvSName);
            tvFatherName=itemView.findViewById(R.id.tvSFatherName);
            tvSurname=itemView.findViewById(R.id.tvSSurname);
            tvNatID=itemView.findViewById(R.id.tvSNatID);
            tvDoB=itemView.findViewById(R.id.tvSDoB);
            tvGender=itemView.findViewById(R.id.tvSGender);

        }
    }
}
