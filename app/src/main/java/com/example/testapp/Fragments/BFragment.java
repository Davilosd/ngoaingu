package com.example.testapp.Fragments;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentManager;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.api.LessonsCallback;
import com.example.testapp.data.model.CompleteLessons;
import com.example.testapp.ui.Common.Common;
import com.example.testapp.CustomAdapter_Question;
import com.example.testapp.CustomAdapter_SV;
import com.example.testapp.JsonPlaceHolderApi;
import com.example.testapp.R;
import com.example.testapp.SinhVien;
import com.example.testapp.api.ApiCallback;
import com.example.testapp.api.ApiService;
import com.example.testapp.api.GetApi;
import com.example.testapp.api.QuestionCallback;
import com.example.testapp.data.model.Lessons;
import com.example.testapp.data.model.Question;
import com.example.testapp.data.model.Unit;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ListView lvDanhSach;

    ArrayList<SinhVien> data_SV = new ArrayList<>();
    ArrayList<Question> data_Questions = new ArrayList<>();
    ArrayList<Unit> units = new ArrayList<>();
    ArrayList<Lessons> lessons = new ArrayList<>();
    CustomAdapter_SV adapter_SV;
    CustomAdapter_Question adapter_Question;
    EditText edtQuestion, edtOption1, edtOption2, edtOption3, edtOption4, edtImgUrl;
    RadioButton radOption1, radOption2, radOption3, radOption4;
    Button btnThem, btnXoa, btnSua, btnThoat, btnNewUnit, btnNewLesson;
    Button btnChonHet, btnBoChon, btnXoaDS;
    Context context;
    SearchView searchView;
    SinhVien sinhVien=null;
    Question question = null;
    String UID = "";
    //int themXoaSua = 0;
    int currentAdapterIndex = 0;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Retrofit retrofit = ApiService.retrofit;
    JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
    public BFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BFragment newInstance(String param1, String param2) {
        BFragment fragment = new BFragment();
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
        View view = inflater.inflate(R.layout.fragment_b, container, false);
        // Inflate the layout for this fragment
        context = requireContext();
        GetApi getApi = new GetApi();
        searchView = view.findViewById(R.id.searchView);
        Spinner mySpinner = view.findViewById(R.id.spinnerLesson);
        Spinner unitSpinner =  view.findViewById(R.id.spinnerUnit) ;
        Retrofit retrofit = ApiService.retrofit;
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        getApi.getUnitsList(jsonPlaceHolderApi, new ApiCallback() {
            @Override
            public void onUnitsLoaded(ArrayList<Unit> data_unit) {
                // Do something with the questions ArrayList here
                units = data_unit;
                UID = units.get(0).getUid();
                ArrayAdapter<Unit> unit = new ArrayAdapter<Unit>(getActivity(), android.R.layout.simple_spinner_item,units);
                unit.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                unitSpinner.setAdapter(unit);
                unitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        onItemSelectedUnitHandler(adapterView, view, i, l);

                        //lmao
                        getApi.getLessonsList(jsonPlaceHolderApi, new LessonsCallback() {
                            @Override
                            public void onLessonsLoaded(ArrayList<Lessons> data_lessons) {
                                //lessons = data_lessons;
                                lessons.clear();


                                for (int i = 0; i < data_lessons.size(); i++) {
                                    System.out.println(Common.currentLid);

                                    if (data_lessons.get(i).getUid().equals(UID))
                                        lessons.add(data_lessons.get(i));
                                }

                                data_lessons.clear();
                                ArrayAdapter<Lessons> sv = new ArrayAdapter<Lessons>(getActivity(), android.R.layout.simple_spinner_item,lessons);
                                sv.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


                                mySpinner.setAdapter(sv);

                                mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                                        onItemSelectedHandler(adapterView, view, i, l);
                                        getApi.getQuestion(jsonPlaceHolderApi, Common.currentLid, new QuestionCallback() {
                                            @Override
                                            public void onQuestionsLoaded(ArrayList<Question> questions) {
                                                // Do something with the questions ArrayList here
                                                data_Questions = questions;
                                                System.out.println(Common.currentUid);
                                                setEvent(data_Questions);
                                            }
                                        });

                                        //System.out.println("lmao dataqs" + data_Questions);

                                    }
                                    @Override
                                    public void onNothingSelected(AdapterView<?> adapterView) {
                                        //System.out.println("not selected");

                                    }
                                });


                            }
                        });


                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        //System.out.println("not selected");

                    }
                });
            }

        });
        System.out.println("aaaaaaaaaaaaaaaaaa " + Common.currentUid);






        //data_Questions = getApi.getQuestion(jsonPlaceHolderApi, Common.currentLid);

        setControl(view);

        return view;
    }
    private void onItemSelectedHandler(AdapterView<?> adapterView, View view, int position, long id) {

        Adapter adapter = adapterView.getAdapter();
        Lessons employee = (Lessons) adapter.getItem(position);

        Common.currentLid = employee.getLid();

        //((ArrayAdapter<?>) adapter).notifyDataSetChanged();

        //.out.println("Data qs" + data_Questions + Common.currentLid);
        //Toast.makeText(getApplicationContext(), "Selected Employee: " + employee.getFullName() ,Toast.LENGTH_SHORT).show();
    }
    private void onItemSelectedUnitHandler(AdapterView<?> adapterView, View view, int position, long id) {
        Adapter adapter = adapterView.getAdapter();
        Unit unit = (Unit) adapter.getItem(position);

        Common.currentUid = unit.getUid();
        UID = unit.getUid();


        //System.out.println("Data unit" + Common.currentUid);
        //Toast.makeText(getApplicationContext(), "Selected Employee: " + employee.getFullName() ,Toast.LENGTH_SHORT).show();
    }
    private void KhoiTao() {
        data_SV.add(new SinhVien("Sv001", "Nguyen Van A", "12/2/2001", true));
        data_SV.add(new SinhVien("Sv002", "Nguyen Van b", "1/2/2001", false));
        data_SV.add(new SinhVien("Sv003", "Nguyen Van c", "1/2/2001", false));


    }
    private void setEvent( ArrayList<Question> data_Question){
        KhoiTao();

        adapter_Question = new CustomAdapter_Question(getActivity(), R.layout.layout_question_item, data_Question);
//        System.out.println("aaaaaaaaaaaaaaaaaaa");
//        System.out.println(adapter_Question);
        lvDanhSach.setAdapter(adapter_Question);
        lvDanhSach.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                question =data_Question.get(i);
                currentAdapterIndex = i;
                //Toast.makeText(MainActivity.this, "Chon" + sinhVien.getTenSV(), Toast.LENGTH_SHORT).show();
                ChonSV();
            }
        });
        lvDanhSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                data_SV.remove(i);
                adapter_SV.notifyDataSetChanged();
                return false;
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog(1);
                //ThemDL("", currentAdapterIndex);

            }
        });
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(2);

                //data_SV.remove(sinhVien);
                adapter_Question.notifyDataSetChanged();
            }
        });
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(3);
                //ThemDL(question.getId(), currentAdapterIndex);
                adapter_Question.notifyDataSetChanged();
            }
        });
        btnNewUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnNewLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnChonHet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter_Question.ChonHet();
            }
        });

        btnBoChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter_Question.BoChon();
            }
        });

        btnXoaDS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter_Question.XoaDS();
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String userInput = newText.toLowerCase();
                ArrayList<Question> filteredData = new ArrayList<>();

                for (Question question : data_Question) {
                    if (question.getQuestion().toLowerCase().contains(userInput)) {
                        filteredData.add(question);
                    }
                }

                adapter_Question = new CustomAdapter_Question(getActivity(), R.layout.layout_question_item, filteredData);
                lvDanhSach.setAdapter(adapter_Question);

                return true;
            }
        });
    }
    private void ChonSV(){
        edtQuestion.setText(question.getQuestion());
        //edtHoTen.setText(question.getAnswer());
        edtOption1.setText(question.getOptions()[0]);
        edtOption2.setText(question.getOptions()[1]);
        edtOption3.setText(question.getOptions()[2]);
        //edtOption4.setText(question.getId());
        edtOption4.setText(question.getOptions()[3]);

        if(question.getAnswer().equals(question.getOptions()[0]) )
            radOption1.setChecked(true);
        else if (question.getAnswer().equals(question.getOptions()[1]))
            radOption2.setChecked(true);
        else if (question.getAnswer().equals(question.getOptions()[2]))
            radOption3.setChecked(true);
        else
            radOption4.setChecked(true);
        edtImgUrl.setText(question.getImg());

    }

    public void showDialog(int themXoaSua){
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dialog);

        TextView tvDialog = dialog.findViewById(R.id.tvDialog);
        if(themXoaSua==1)
            tvDialog.setText("Ban co muon them cau hoi \"" + edtQuestion.getText().toString()+ "\" vao database?");
        else if (themXoaSua==2)
            tvDialog.setText("Ban co chac chan muon xoa cau hoi \"" + edtQuestion.getText().toString()+ "\" khoi database?");
        else if (themXoaSua==3)
            tvDialog.setText("Ban muon sua thong tin cau hoi \"" + edtQuestion.getText().toString()+ "\" ?");


        Button btnCo = dialog.findViewById(R.id.btnCo);
        Button btnKo = dialog.findViewById(R.id.btnKo);
        btnCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(themXoaSua==1) {
                    ThemDL("",currentAdapterIndex);
                    Toast.makeText(getActivity(), "Them thanh cong", Toast.LENGTH_SHORT).show();
                }
                else if (themXoaSua==2) {
                    data_Questions.remove(question);
                    deleteQuestion(question.getId());
                    System.out.println("asdasdasd" + question.getId());
                    Toast.makeText(getActivity(), "The", Toast.LENGTH_SHORT).show();
                }
                else if (themXoaSua==3)
                    ThemDL(question.getId(),currentAdapterIndex);
                adapter_Question.notifyDataSetChanged();

                dialog.dismiss();
            }
        });
        btnKo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void ThemDL(String id, int currentAdapterIndex){
        String options[] = new String[4];
        Editable answer;
        Array a;

        options[0] = edtOption1.getText().toString();
        options[1] = edtOption2.getText().toString();
        options[2] = edtOption3.getText().toString();
        options[3] = edtOption4.getText().toString();

        if(radOption1.isChecked())
            answer = edtOption1.getText();
        else if (radOption2.isChecked())
            answer = edtOption2.getText();
        else if (radOption3.isChecked())
            answer = edtOption3.getText();
        else
            answer = edtOption4.getText();

        Question q = new Question(edtQuestion.getText().toString(), answer.toString(), options, Common.currentLid, edtImgUrl.getText().toString(), id);
        if(q.getId().equals("")){
            addQuestion(q);
            data_Questions.add(q);
        }
        else {
            editQuestion(q, id);
            data_Questions.set(currentAdapterIndex, q);
        }

        adapter_Question.notifyDataSetChanged();

    }
    private void editQuestion(Question q, String questionId){
        Call<Question> call = jsonPlaceHolderApi.putQuestion(q, questionId, Common.currentAccount.getRole(), Common.token);
        call.enqueue(new Callback<Question>() {

            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                if (!response.isSuccessful()) {
                    System.out.println(response);
                    return;
                }

            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }
    private void addQuestion(Question q){
        Call<Question> call = jsonPlaceHolderApi.postQuestion(q,Common.currentAccount.getRole(), Common.token);
        call.enqueue(new Callback<Question>() {

            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                if (!response.isSuccessful()) {
                    //textViewResult.setText("code: " + response.code());
                    //Toast.makeText(getApplicationContext(), "complete lesson" + Common.currentLid, Toast.LENGTH_LONG).show();

                    System.out.println(response);
                    return;
                }

            }

            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                //Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                System.out.println(t.toString());
            }
        });
    }
    private void deleteQuestion(String id){
        Call<Question> call = jsonPlaceHolderApi.deleteQuestion(id,Common.currentAccount.getRole(), Common.token);
        call.enqueue(new Callback<Question>() {

            @Override
            public void onResponse(Call<Question> call, Response<Question> response) {
                if (!response.isSuccessful()) {
                    //textViewResult.setText("code: " + response.code());
                    //Toast.makeText(getApplicationContext(), "complete lesson" + Common.currentLid, Toast.LENGTH_LONG).show();

                    System.out.println(response);
                    return;
                }
            }
            @Override
            public void onFailure(Call<Question> call, Throwable t) {
                //Toast.makeText(getApplicationContext(), t.toString(), Toast.LENGTH_LONG).show();
                System.out.println(t.toString());
            }
        });
    }
    private void setControl(View view){
        lvDanhSach = view.findViewById(R.id.lvDanhSach);
        edtQuestion = view.findViewById(R.id.edtQuestion);
        edtOption1= view.findViewById(R.id.edtOption1);
        edtOption2= view.findViewById(R.id.edtOption2);
        edtOption3 = view.findViewById(R.id.edtOption3);
        edtOption4 = view.findViewById(R.id.edtOption4);
        edtImgUrl = view.findViewById(R.id.edtImgUrl);
        radOption1= view.findViewById(R.id.radOption1);
        radOption2= view.findViewById(R.id.radOption2);
        radOption3= view.findViewById(R.id.radOption3);
        radOption4= view.findViewById(R.id.radOption4);
        btnThem = view.findViewById(R.id.btnThem);
        btnXoa = view.findViewById(R.id.btnXoa);
        btnSua = view.findViewById(R.id.btnSua);
        btnNewLesson = view.findViewById(R.id.btnNewLesson);
        btnNewUnit = view.findViewById(R.id.btnNewUnit);

        btnBoChon = view.findViewById(R.id.btnBoChon);
        btnChonHet = view.findViewById(R.id.btnChonHet);
        btnXoaDS = view.findViewById(R.id.btnXoaDS);
    }
    public void refreshFragment() {
        FragmentManager parentFragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = parentFragmentManager.beginTransaction();
        fragmentTransaction.detach(this).attach(this).commit();
    }

}