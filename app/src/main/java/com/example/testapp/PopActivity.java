package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.testapp.ui.Common.Common;

public class PopActivity extends AppCompatActivity {
    Button btnStartLesson;
    TextView tvLid;
    String text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop);
        btnStartLesson = (Button) findViewById(R.id.btnStartLesson);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8), (int)(height*.7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);

        tvLid = (TextView) findViewById(R.id.tvLid);
        if(Common.isThisLessonCompleted == false)
            text="chua hoan thanh";
        else
            text="Da hoan thanh";

        tvLid.setText("Bai hoc:"+Common.currentLid + text );
        //System.out.println(Common.currentLid);
        btnStartLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lesson = new Intent(getApplicationContext(), LessonActivity.class);
                startActivity(lesson);
            }
        });
    }
}