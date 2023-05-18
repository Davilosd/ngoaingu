package com.example.testapp.Fragments;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.testapp.JsonPlaceHolderApi;
import com.example.testapp.Learn;
import com.example.testapp.R;
import com.example.testapp.SignUp;
import com.example.testapp.api.ApiService;
import com.example.testapp.data.model.Accounts;
import com.example.testapp.data.model.Users;
import com.example.testapp.ui.Common.Common;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button btnEdit, btnChange, btnChangePW;
    TextView tvEmail, tvFirstName, tvLastName, tvPhoneNumber, tvChangePW, tvError;
    EditText editFirstName, editLastName, editPN, editOldPWD, editNewPWD, editRePWD;
    JsonPlaceHolderApi jsonPlaceHolderApi;

    public CFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CFragment newInstance(String param1, String param2) {
        CFragment fragment = new CFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_c, container, false);

        findView(view);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showEditInfo();
            }
        });

        tvChangePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });

        return view;
    }
    public void showEditInfo() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.change_info);
        editFirstName = dialog.findViewById(R.id.editFirstName);
        editLastName = dialog.findViewById(R.id.editLastName);
        editPN = dialog.findViewById(R.id.editPN);

        editFirstName.setText(Common.currentAccount.getfName());
        editLastName.setText(Common.currentAccount.getlName());
        editPN.setText(Common.currentAccount.getPhoneNumber());
        btnChange = dialog.findViewById(R.id.buttonChange);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(1);
            }
        });

        dialog.show();
    }
    public void changePassword() {
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.change_password);
        editNewPWD = dialog.findViewById(R.id.editNewPWD);
        editOldPWD = dialog.findViewById(R.id.editOldPWD);
        editRePWD = dialog.findViewById(R.id.editRePWD);
        btnChangePW = dialog.findViewById(R.id.btnChangePW);
        tvError = dialog.findViewById(R.id.tvError);

        btnChangePW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Common.currentAccount.getPassword().equals(editOldPWD.getText().toString()) ){
                    saveEditAccount(2);
                    Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else{
                    tvError.setText("Mật khẩu cũ không chính xác");
                    Toast.makeText(getActivity(), "Mật khẩu cũ không chính xác", Toast.LENGTH_SHORT).show();
                }


            }
        });
        dialog.show();
    }

    public void showDialog(int edit_change){
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.custom_dialog);

        TextView tvDialog = dialog.findViewById(R.id.tvDialog);
        if(edit_change ==1)
            tvDialog.setText("Lưu thay đổi?");
        else if (edit_change == 2)
            tvDialog.setText("Lưu thay đổi?");
        Button btnCo = dialog.findViewById(R.id.btnCo);
        Button btnKo = dialog.findViewById(R.id.btnKo);
        btnCo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                saveEditAccount(1); // 1 la thay doi thong tin tai khoan
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

    public void saveEditAccount(int edit_Change){
        Retrofit retrofit = ApiService.retrofit;
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Accounts account;
        if(edit_Change == 2){
            account = new Accounts(Common.currentAccount.getEmail(), editNewPWD.getText().toString(),Common.currentAccount.getRole(), Common.currentAccount.getfName(), Common.currentAccount.getlName(), Common.currentAccount.getPhoneNumber());
        }
        else
            account = new Accounts(Common.currentAccount.getEmail(), Common.currentAccount.getPassword(), Common.currentAccount.getRole(), editFirstName.getText().toString(), editLastName.getText().toString(), editPN.getText().toString());


        Call<Accounts> call = jsonPlaceHolderApi.editAccount(account);
        call.enqueue(new Callback<Accounts>() {

            @Override
            public void onResponse(Call<Accounts> call, Response<Accounts> response) {
                if (!response.isSuccessful()) {
                    //textViewResult.setText("code: " + response.code());
                    Toast.makeText(getActivity(), "Thay đổi thành công", Toast.LENGTH_LONG).show();
                    return;
                }
                Common.currentAccount = account;
                tvFirstName.setText("Tên:   " + Common.currentAccount.getfName());
                tvLastName.setText("Họ:   " + Common.currentAccount.getlName());
                tvPhoneNumber.setText("SĐT:   " + Common.currentAccount.getPhoneNumber());
            }
            @Override
            public void onFailure(Call<Accounts> call, Throwable t) {
                Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_LONG).show();
                System.out.println(t.toString());
            }
        });
    }
    public void findView(View view){
        btnEdit = view.findViewById(R.id.btnEdit);
        tvEmail = view.findViewById(R.id.tvEmail);
        tvPhoneNumber = view.findViewById(R.id.tvPhoneNumber);
        tvFirstName = view.findViewById(R.id.tvFirstName);
        tvLastName = view.findViewById(R.id.tvLastName);
        tvChangePW = view.findViewById(R.id.tvChangePW);

        tvEmail.setText("Email:   " + Common.currentAccount.getEmail());
        tvLastName.setText("Họ:   " + Common.currentAccount.getlName());
        tvFirstName.setText("Tên:   " + Common.currentAccount.getfName());
        tvPhoneNumber.setText("SĐT:   " + Common.currentAccount.getPhoneNumber());

    }

}