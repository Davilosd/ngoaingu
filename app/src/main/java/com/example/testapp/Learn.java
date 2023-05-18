package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.testapp.Course;
import com.example.testapp.Fragments.ABlankFragment;
import com.example.testapp.Fragments.BFragment;
import com.example.testapp.Fragments.CFragment;
import com.example.testapp.LessonActivity;
import com.example.testapp.api.ApiService;
import com.example.testapp.data.model.Unit;
import com.example.testapp.ui.Common.Common;
import com.example.testapp.ui.login.LoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Learn extends AppCompatActivity {

    ListView lvDanhSach;
    BottomNavigationView bnView;

    ArrayAdapter adapter_C;
    CustomAdapter_Course adapter_Co;
    private ArrayList<Course> mCourses = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ArrayList<Unit> mUnit = new ArrayList<>();
    Course course= null;
    Unit unit = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        loadFrag(new ABlankFragment());
        bnView = findViewById(R.id.nav_view_bottom);
        bnView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id==R.id.navigation_home){
                    loadFrag(new ABlankFragment());
                } else if (id==R.id.navigation_dashboard) {
                    if(!Common.currentUser.equals("guess"))
                        if(Common.currentAccount.getRole().equals("admin"))
                            loadFrag(new BFragment());
                } else if (id==R.id.navigation_notifications) {
                    if(!Common.currentUser.equals("guess"))
                        loadFrag(new CFragment());
                } else {

                }
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
        adapter_C = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mCourses);
        lvDanhSach.setAdapter(adapter_C);

        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                course = mCourses.get(i);
                Toast.makeText(Learn.this, "Chon" + course.getName(), Toast.LENGTH_SHORT).show();
                ChooseCourse();
            }
        });
    }

    private void ChooseCourse(){
        Intent lesson = new Intent(Learn.this, LessonActivity.class);
        startActivity(lesson);
    }
    public void KhoiTao(){
    }
    private void setControl(){
        lvDanhSach = findViewById(R.id.lvDanhSach);
    }
    public void loadFrag(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        //ft.add(R.id.container, fragment);
        ft.replace(R.id.container, fragment);
        ft.commit();
    }
}