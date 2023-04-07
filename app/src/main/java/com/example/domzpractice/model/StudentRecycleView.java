package com.example.domzpractice.model;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.domzpractice.R;
import com.example.domzpractice.fragment.StudentUpdate;

import java.util.List;

public class StudentRecycleView extends RecyclerView.Adapter<StudentRecycleView.MyViewHolder> {

    private Context context;
    private final List<Student> student;

    public StudentRecycleView(Context context, List<Student> students){
        this.context = context;
        this.student = students;
    }
    
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_row_student, parent, false);
        return new StudentRecycleView.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Student currentStudent = student.get(position);
        holder.tvStudentId.setText("Student ID: " + String.valueOf(currentStudent.getStudentId()));
        holder.tvName.setText("Name: "+ FullName(currentStudent.getFirstName(),
                currentStudent.getMiddleName(),
                currentStudent.getLastName()));
        holder.tvCourseYearTerm.setText(courseYearTerm(currentStudent.getCourse(),
                currentStudent.getYear(),
                currentStudent.getTerm()));
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), StudentUpdate.class);
                intent.putExtra("name", FullName(currentStudent.getFirstName(),
                        currentStudent.getMiddleName(),
                        currentStudent.getLastName()));
                Toast.makeText(view.getContext(), currentStudent.getFirstName(), Toast.LENGTH_SHORT).show();
                view.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return student.size();
    }

    private String FullName(String firstName, String middleName, String lastName){
        return firstName + " " + middleName.charAt(0) + ". " + lastName;
    }

    private String courseYearTerm(String course, String year, String term){
        return "Course: "+ course + " Year: "  + year + " Term: " + term;
    }

    public void filterItem(List<Student> filterData){
        student.clear();
        student.addAll(filterData);
        notifyDataSetChanged();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView tvName;
        TextView tvStudentId;
        TextView tvCourseYearTerm;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardHolder);
            tvStudentId = itemView.findViewById(R.id.tvStudentId);
            tvName = itemView.findViewById(R.id.tvName);
            tvCourseYearTerm = itemView.findViewById(R.id.tvCourseYearTerm);
        }
    }
}
