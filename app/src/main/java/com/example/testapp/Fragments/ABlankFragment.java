package com.example.testapp.Fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.testapp.LessonActivity;
import com.example.testapp.api.LessonsCallback;
import com.example.testapp.ui.Common.Common;
import com.example.testapp.JsonPlaceHolderApi;
import com.example.testapp.PopActivity;
import com.example.testapp.R;
import com.example.testapp.api.ApiService;
import com.example.testapp.api.GetApi;
import com.example.testapp.data.model.CompleteLessons;
import com.example.testapp.data.model.Lessons;
import com.example.testapp.data.model.Unit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.testapp.Course;

import java.util.ArrayList;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ABlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ABlankFragment extends Fragment {
    private ArrayList<Course> mCourses = new ArrayList<>();
    private ArrayList<Lessons> alLessons = new ArrayList<>();
    private ArrayList<CompleteLessons> alCompleteLessons = new ArrayList<>();

    ListView lvDanhSach;
    private RecyclerView mRecyclerView;
    ArrayAdapter adapter_C;
    Course course= null;
    Lessons lesson = null;
    CompleteLessons CompleteLesson = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ABlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ABlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ABlankFragment newInstance(String param1, String param2) {
        ABlankFragment fragment = new ABlankFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_a_blank, container, false);
        TextView tv1 = view.findViewById(R.id.tv1);
        TextView titleTextView = view.findViewById(R.id.title_text_view);
        ListView listView = view.findViewById(R.id.lvDanhSach);
        //RecyclerView recyclerView = view.findViewById(R.id.course_recycler_view);
        lvDanhSach = view.findViewById(R.id.lvDanhSach);
        // Inflate the layout for this fragment

         //get api

        Retrofit retrofit = ApiService.retrofit;
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        GetApi getApi = new GetApi();

        getApi.getLessonsList(jsonPlaceHolderApi, new LessonsCallback() {
            @Override
            public void onLessonsLoaded(ArrayList<Lessons> lessons) {
                alLessons = lessons;
            }
        });
        alCompleteLessons = getApi.getCompleteLessonsList(jsonPlaceHolderApi);
        getUnitsList(jsonPlaceHolderApi);
        setEvent();
        loadMenu();
        learnApi();
        return view;

    }
    private void getUnitsList(JsonPlaceHolderApi jsonPlaceHolderApi){
        Call<List<Unit>> call = jsonPlaceHolderApi.getUnits();
        call.enqueue(new Callback<List<Unit>>() {
            @Override
            public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {
                if(!response.isSuccessful()){
                    //Toast.makeText(Learn.this, "failed1", Toast.LENGTH_SHORT).show();
                }
                List<Unit> units = response.body();

                for (Unit unit: units){
                    String content = "";
                    content += "Name: " + unit.getName() + "\n";
                    content += "Description: " + unit.getDescription();
                    //unitto.add(new Unit(unit.getName(), unit.getDescription()));
                    mCourses.add(new Course(unit.getName(), unit.getDescription()));
                }
                setEvent();
            }
            @Override
            public void onFailure(Call<List<Unit>> call, Throwable t) {
                //Toast.makeText(Learn.this, "failed", Toast.LENGTH_SHORT).show();
            }

        });
    }
    public ArrayList<Lessons> getLessonsList(JsonPlaceHolderApi jsonPlaceHolderApi){
        Call<List<Lessons>> call = jsonPlaceHolderApi.getLessons();
        call.enqueue(new Callback<List<Lessons>>() {
            @Override
            public void onResponse(Call<List<Lessons>> call, Response<List<Lessons>> response) {
                if(!response.isSuccessful()){
                    //Toast.makeText(Learn.this, "failed1", Toast.LENGTH_SHORT).show();
                }
                List<Lessons> lessons = response.body();

                for (Lessons lesson: lessons){
                    String content = "";
                    content += "Name: " + lesson.getName() + "\n";
                    content += "Description: " + lesson.getLid();
                    alLessons.add(new Lessons( lesson.getLid(), lesson.getName(), lesson.getUid(), lesson.getExp(), lesson.getHint(), lesson.getHintTitle()));

                }

                // cho nay nguy hiem, neu can nen bat lai serEEent
                //setEvent();
            }
            @Override
            public void onFailure(Call<List<Lessons>> call, Throwable t) {
                //Toast.makeText(Learn.this, "failed", Toast.LENGTH_SHORT).show();
            }

        });
        return alLessons;
    }

    private void getCompleteLessonsList(JsonPlaceHolderApi jsonPlaceHolderApi){
        Call<List<CompleteLessons>> call = jsonPlaceHolderApi.getCompleteLesson(Common.currentUser);
        call.enqueue(new Callback<List<CompleteLessons>>() {
            @Override
            public void onResponse(Call<List<CompleteLessons>> call, Response<List<CompleteLessons>> response) {
                if(!response.isSuccessful()){
                    //Toast.makeText(Learn.this, "failed1", Toast.LENGTH_SHORT).show();
                }
                List<CompleteLessons> cLessons = response.body();
                //System.out.println(response.body());
                for (CompleteLessons cLesson: cLessons){
                    String content = "";
                    content += "Name: " + cLesson.getEmail() + "\n";
                    content += "Description: " + cLesson.getLid();
                    alCompleteLessons.add(new CompleteLessons(cLesson.getEmail(), cLesson.getLid()));
                    //System.out.println(alCompleteLessons);
                }
                setEvent();
            }
            @Override
            public void onFailure(Call<List<CompleteLessons>> call, Throwable t) {
                //Toast.makeText(Learn.this, "failed", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void loadMenu(){


    }
    private void learnApi(){

    }

    //khoi tao gia tri doc tu api
    private void setEvent() {

        KhoiTao();
        if (alLessons != null && !mCourses.isEmpty()) {
            adapter_C = new ArrayAdapter<Lessons>(requireContext(), android.R.layout.simple_list_item_1, alLessons);
            lvDanhSach.setAdapter(adapter_C);

        }

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                lesson = alLessons.get(i);
                Common.currentLid = lesson.getLid();
                Common.currentLessonHint = lesson.getHint();
                Common.currentLessonHintTitle = lesson.getHintTitle();
                Common.currentLesson = lesson.getName();
                Common.isThisLessonCompleted = false;
                for(int j=0;j<alCompleteLessons.size();j++){

                    if(alCompleteLessons.get(j).getLid().equals(Common.currentLid) ) {
                        Common.isThisLessonCompleted = true;
                        break;
                    }
                }
                //Toast.makeText(Learn.this, "Chon" + course.getName(), Toast.LENGTH_SHORT).show();

                //ChooseCourse();
                showDialog();
            }
        });
    }

    private void ChooseCourse(){

        Intent i = new Intent(getActivity(), PopActivity.class);
        startActivity(i);

//        Intent lesson = new Intent(getActivity(), LessonActivity.class);
//        startActivity(lesson);

    }

    public void showDialog(){
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialog_lesson);

        TextView tvChosenLesson = dialog.findViewById(R.id.tvChosenLesson);
        TextView tvHint = dialog.findViewById(R.id.tvHint);
        TextView tvHintTitle = dialog.findViewById(R.id.tvHintTitle);
        TextView tvLesson = dialog.findViewById(R.id.tvLesson);
        Button btnLearn = dialog.findViewById(R.id.btnLearn);
        ImageView tvCancel = dialog.findViewById(R.id.tvCancel);

        if(Common.isThisLessonCompleted == false)
            tvChosenLesson.setText("Chưa hoàn thành" );
        else
            tvChosenLesson.setText("Đã hoàn thành" );
        tvHint.setText(Common.currentLessonHint);
        tvHintTitle.setText(Common.currentLessonHintTitle);
        tvLesson.setText(Common.currentLesson);
        btnLearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lesson = new Intent(getActivity(), LessonActivity.class);
                startActivity(lesson);
                dialog.dismiss();
            }
        });

        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
    public void KhoiTao(){


        //Toast.makeText(Learn.this, unitto., Toast.LENGTH_SHORT).show();
//        mCourses.add(new Course("French", "asd"));
//        mCourses.add(new Course("Spanish", "asd"));
//        mCourses.add(new Course("German", "asd"));

    }
//    private void setControl(){
//        lvDanhSach = view.findViewById(R.id.lvDanhSach);
//    }

}