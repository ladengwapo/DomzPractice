package com.example.domzpractice.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.domzpractice.R;
import com.example.domzpractice.database.Instance;
import com.example.domzpractice.model.Student;
import com.example.domzpractice.model.StudentRecycleView;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private Instance instance;
    private Context context;
    private EditText etSearch;
    private List<Student> students;
    private List<Student> filterData;
    private List<Student> copyStudents;
    private StudentRecycleView studentRecycleView;

    public HomeFragment(Context context) {
        this.context = context;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddStudentFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddStudentFragment newInstance(String param1, String param2) {
        AddStudentFragment fragment = new AddStudentFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        instance = new Instance(context);
        students = instance.getStudentData(context);

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.studentRecycleView);

        studentRecycleView = new StudentRecycleView(context, students);
        recyclerView.setAdapter(studentRecycleView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(layoutManager);

        filterData = new ArrayList<Student>();
        copyStudents = new ArrayList<>(students);
        etSearch = view.findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new SearchStudent());

        return view;
    }

    private class SearchStudent implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            filterData.clear();
            String strSearch = charSequence.toString().toLowerCase();

            if(strSearch.length() == 0){
                filterData.addAll(copyStudents);
            }else{
                for(Student e: copyStudents){
                    String fullName = e.getFirstName() + " " + e.getMiddleName() + " " + e.getLastName();
                    String middleInitial = e.getFirstName() + " " + e.getMiddleName().charAt(0) + ". " + e.getLastName();
                    String studentId = String.valueOf(e.getStudentId());
                    String course = e.getCourse().toLowerCase();

                    if(fullName.toLowerCase().contains(strSearch) || middleInitial.toLowerCase().contains(strSearch) || studentId.contains(strSearch) || course.contains(strSearch)){
                        filterData.add(e);
                    }
                }

                if(filterData.size() == 0){
                    Toast.makeText(context, "Student not found", Toast.LENGTH_SHORT).show();
                }
            }
            studentRecycleView.filterItem(filterData);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }
}