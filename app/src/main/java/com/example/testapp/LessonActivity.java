package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.data.model.Heplers;
import com.example.testapp.ui.Common.Common;
import com.example.testapp.api.ApiService;
import com.example.testapp.data.model.CompleteLessons;
import com.example.testapp.data.model.Question;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LessonActivity extends AppCompatActivity implements View.OnClickListener {
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    TextView totalQuestionsTextView;
    TextView q1,q2, q3, q4 ,q5, q6, q7, q8, q9, q10, q11, q12, q13, q14;
    TextView questionTextView;
    ImageView imgView;
    Button ansA, ansB, ansC, ansD;
    Button submitBtn;
    int score=0;
    private  ArrayList<String> question = new ArrayList<>();

    private ArrayList<String> img = new ArrayList<>();
    private  ArrayList<String> correctAnswers = new ArrayList<>();
    private  ArrayList<ArrayList<String>> choices = new ArrayList<ArrayList<String>>();
    private ArrayList<Question> cauHoi = new ArrayList<>();
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    Heplers heplers;
    String hint = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson);
        totalQuestionsTextView = findViewById(R.id.total_question);

        imgView = findViewById(R.id.image_view);
        questionTextView = findViewById(R.id.question);

        //get api
        Retrofit retrofit = ApiService.retrofit;
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Question>> call = jsonPlaceHolderApi.getQuestion(Common.currentLid, "user", Common.token);

        call.enqueue(new Callback<List<Question>>() {

            @Override
            public void onResponse(Call<List<Question>> call, Response<List<Question>> response) {
                if(!response.isSuccessful()){
                    question.add("success");
                    Toast.makeText(LessonActivity.this, "success", Toast.LENGTH_SHORT).show();

                    //Toast.makeText(Learn.this, "failed1", Toast.LENGTH_SHORT).show();
                }
                List<Question> questions = response.body();
                //question = new ArrayList<>();
                for (Question q: questions){
                    String content = "";
                    //choices.add(q.getOptions());
                    question.add(q.getQuestion());
                    cauHoi.add(q);
                    correctAnswers.add(q.getAnswer());

                    img.add(q.getImg());
                }
                loadNewQuestion();
            }

            @Override
            public void onFailure(Call<List<Question>> call, Throwable t) {
                //Toast.makeText(Learn.this, "failed", Toast.LENGTH_SHORT).show();
                question.add("failed");
                Toast.makeText(LessonActivity.this, "failed", Toast.LENGTH_SHORT).show();
            }

        });
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        submitBtn = findViewById(R.id.btn_submitt);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        //totalQuestionsTextView.setText("Total question: " + totalQuestion);


    }

    @Override
    public void onClick(View view) {
        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if(clickedButton.getId()==R.id.btn_submitt){
            if(selectedAnswer.equals((correctAnswers.get(currentQuestionIndex)))){
                score++;
            }
            else{
                question.add(question.get(currentQuestionIndex));
                img.add(img.get(currentQuestionIndex));
                //choices.add(choices.get(currentQuestionIndex));
                cauHoi.add(cauHoi.get(currentQuestionIndex));
                correctAnswers.add(correctAnswers.get(currentQuestionIndex));

            }
            currentQuestionIndex++;
            loadNewQuestion();


        }else {
            //choices button clicked
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);
        }
    }

    void loadNewQuestion(){

        totalQuestionsTextView.setText("Total question: " + question.size());
        if(currentQuestionIndex == question.size()){
            finishQuiz();
            return;
        }
        questionTextView.setText((question.get(currentQuestionIndex)));


        String[] questionWords = question.get(currentQuestionIndex).split(" ");
        ArrayList<String> list = new ArrayList<String>(Arrays.asList(questionWords));
        for(int i = questionWords.length; i<14;i++){
            list.add("");
        }
        ArrayList<TextView> q = new ArrayList<>();


        khoiTao();
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.q1:
                        //System.out.println(q1.getText().toString().replace(" ", ""));
                        getHint(q1.getText().toString());

                        break;
                    case R.id.q2:
                        getHint(q2.getText().toString());
                        break;
                    case R.id.q3:
                        getHint(q3.getText().toString());
                        break;
                    case R.id.q4:
                        getHint(q4.getText().toString());
                        break;
                    case R.id.q5:
                        getHint(q5.getText().toString());                        break;
                    case R.id.q6:
                        getHint(q6.getText().toString());                        break;
                    case R.id.q7:
                        getHint(q7.getText().toString());                        break;
                    case R.id.q8:
                        getHint(q8.getText().toString());                        break;
                    case R.id.q9:
                        getHint(q9.getText().toString());                        break;
                    case R.id.q10:
                        getHint(q10.getText().toString());                        break;
                    case R.id.q11:
                        getHint(q11.getText().toString());                        break;
                    case R.id.q12:
                        getHint(q12.getText().toString());                        break;
                    case R.id.q13:
                        getHint(q13.getText().toString());                        break;
                    case R.id.q14:
                        getHint(q14.getText().toString());                        break;
                }
            }
        };

        q1.setOnClickListener(listener);
        q2.setOnClickListener(listener);
        q3.setOnClickListener(listener);
        q4.setOnClickListener(listener);
        q5.setOnClickListener(listener);
        q6.setOnClickListener(listener);
        q7.setOnClickListener(listener);
        q8.setOnClickListener(listener);
        q9.setOnClickListener(listener);
        q10.setOnClickListener(listener);
        q11.setOnClickListener(listener);
        q12.setOnClickListener(listener);
        q13.setOnClickListener(listener);
        q14.setOnClickListener(listener);

        nhanTV(list);

        if(!img.get(currentQuestionIndex).isEmpty())
            Picasso.get().load(img.get(currentQuestionIndex)).into(imgView);


        ansA.setText((cauHoi.get(currentQuestionIndex).getOptions()[0]));
        ansB.setText((cauHoi.get(currentQuestionIndex).getOptions()[1]));
        ansC.setText((cauHoi.get(currentQuestionIndex).getOptions()[2]));
        ansD.setText((cauHoi.get(currentQuestionIndex).getOptions()[3]));


    }

    void finishQuiz(){

        String passStatus = "";

        if(score > question.size()*0.60){
            passStatus = "Passed";
        }else {
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is " + score+ " out of" + question.size())
                .setPositiveButton("End", (dialogInterface, i) -> endQuiz())
                .setCancelable(false)
                .show();

        Retrofit retrofit = ApiService.retrofit;

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        CompleteLessons cLessons = new CompleteLessons(Common.currentUser, Common.currentLid);

        Call<List<CompleteLessons>> call = jsonPlaceHolderApi.lessonComplete(cLessons);
        call.enqueue(new Callback<List<CompleteLessons>>() {

            @Override
            public void onResponse(Call<List<CompleteLessons>> call, Response<List<CompleteLessons>> response) {
                if (!response.isSuccessful()) {
                    //textViewResult.setText("code: " + response.code());
                    Toast.makeText(getApplicationContext(), "complete lesson" + Common.currentLid, Toast.LENGTH_LONG).show();
                    return;
                }
            }
            @Override
            public void onFailure(Call<List<CompleteLessons>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                System.out.println(t.toString());
            }
        });
    }

    void restartQuiz(){
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }
    void endQuiz(){
        Intent learn = new Intent(getApplicationContext(), Learn.class);
        startActivity(learn);
    }
    void khoiTao(){

        q1 = findViewById(R.id.q1);
        q2 = findViewById(R.id.q2);
        q3 = findViewById(R.id.q3);
        q4 = findViewById(R.id.q4);
        q5 = findViewById(R.id.q5);
        q6 = findViewById(R.id.q6);
        q7 = findViewById(R.id.q7);
        q8 = findViewById(R.id.q8);
        q9 = findViewById(R.id.q9);
        q10 = findViewById(R.id.q10);
        q11 = findViewById(R.id.q11);
        q12 = findViewById(R.id.q12);
        q13 = findViewById(R.id.q13);
        q14 = findViewById(R.id.q14);

    }
    void nhanTV(ArrayList<String> list){
//        q1 = q.get(0);
//        q2 = q.get(1);
//        q3 = q.get(2);
//        q4 = q.get(3);
//        q5 = q.get(4);
//        q6 = q.get(5);
//        q7 = q.get(6);
//        q8 = q.get(7);
//        q9 = q.get(8);
//        q10 = q.get(9);
//        q11 = q.get(10);
//        q12 = q.get(11);
//        q13 = q.get(12);
//        q14 = q.get(13);
        if(!list.get(0).isEmpty())
            q1.setText(list.get(0) + " ");
        //if(!questionWords[1].isEmpty())
            q2.setText(list.get(1)+ " ");
        //if(!questionWords[2].isEmpty())
            q3.setText(list.get(2)+ " ");
        //if(!questionWords[3].isEmpty())
            q4.setText(list.get(3)+ " ");
        //if(!questionWords[4].isEmpty())
            q5.setText(list.get(4)+ " ");
        //if(!questionWords[5].isEmpty())
            q6.setText(list.get(5)+ " ");
        //if(!questionWords[6].isEmpty())
            q7.setText(list.get(6)+ " ");
        //if(!questionWords[7].isEmpty())
            q8.setText(list.get(7)+ " ");
        //if(!questionWords[8].isEmpty())
            q9.setText(list.get(8)+ " ");
//        if(!questionWords[9].isEmpty())
            q10.setText(list.get(9)+ " ");
//        if(!questionWords[10].isEmpty())
            q11.setText(list.get(10)+ " ");
//        if(!questionWords[11].isEmpty())
            q12.setText(list.get(11)+ " ");

            q13.setText(list.get(12)+ " ");

            q14.setText(list.get(13)+ " ");
    }
    void getHint(String hintWord){
        Retrofit retrofit = ApiService.retrofit;

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);


        Call<List<Heplers>> call = jsonPlaceHolderApi.getHelpers(hintWord);
        call.enqueue(new Callback<List<Heplers>>() {

            @Override
            public void onResponse(Call<List<Heplers>> call, Response<List<Heplers>> response) {
                if (!response.isSuccessful()) {
                    //textViewResult.setText("code: " + response.code());
                    return;
                }
                  List<Heplers> heplers = response.body();
//                System.out.println("helpers" + heplers);
//                asd();
                //hint = heplers.getMeaning();
                for (Heplers h: heplers){
                    hint = h.getMeaning();
                    showHelper(hintWord,hint);
                    System.out.println(hint);
                    System.out.println( h.getMeaning());
                }

            }
            @Override
            public void onFailure(Call<List<Heplers>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"error"+ t.toString(), Toast.LENGTH_LONG).show();
                System.out.println(t.toString());
            }
        });
    }
    void asd(){
        Toast.makeText(getApplicationContext(), "complete lesson", Toast.LENGTH_LONG).show();
    }
    void showHelper(String aWord,String hint) {
        Dialog dialog = new Dialog(LessonActivity.this);
        dialog.setContentView(R.layout.dialog_helper);


        TextView tvHelper = dialog.findViewById(R.id.tvHelper);
        TextView tvHelperTitle = dialog.findViewById(R.id.tvHelperTitle);
        ImageView ivCancel = dialog.findViewById(R.id.ivCancel);

        tvHelper.setText(hint);
        tvHelperTitle.setText(aWord);
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}