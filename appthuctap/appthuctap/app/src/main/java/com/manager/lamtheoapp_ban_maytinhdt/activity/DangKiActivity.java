package com.manager.lamtheoapp_ban_maytinhdt.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.manager.lamtheoapp_ban_maytinhdt.R;
import com.manager.lamtheoapp_ban_maytinhdt.retrofit.ApiBanhang;
import com.manager.lamtheoapp_ban_maytinhdt.retrofit.RetroficClient;
import com.manager.lamtheoapp_ban_maytinhdt.ultil.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DangKiActivity extends AppCompatActivity {

    EditText email, pass, repass, mobile, username;
    Button button;
    ApiBanhang apiBanhang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);

        initView();
        initControll();
    }

    private void initControll() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangki();
            }
        });
    }

    private void dangki() {
        String str_email = email.getText().toString().trim();
        String str_pass = pass.getText().toString().trim();
        String str_repass = repass.getText().toString().trim();
        String str_mobile = mobile.getText().toString().trim();
        String str_username = username.getText().toString().trim();

        if(TextUtils.isEmpty(str_email)){
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(str_pass)){
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập pass", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(str_repass)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập Repass", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(str_mobile)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập mobile", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(str_username)) {
            Toast.makeText(getApplicationContext(), "Bạn chưa nhập tên", Toast.LENGTH_SHORT).show();
        }

        else{
            if(str_pass.equals(str_repass)){
                //postdata
                compositeDisposable.add(apiBanhang.dangki(str_email, str_pass, str_username,str_mobile)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                userModel -> {
                                    if(userModel.isSuccess()){
                                        Utils.user_current.setEmail(str_email);
                                        Utils.user_current.setPass(str_pass);
                                        Intent intent = new Intent(getApplicationContext(), DangNhapActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }else{
                                        Toast.makeText(getApplicationContext(), userModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                },
                                throwable -> {
                                    Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        ));
            }
            else{
                Toast.makeText(getApplicationContext(), "pass chưa trùng khớp", Toast.LENGTH_SHORT).show();
            }
        }


    }

    private void initView() {

        apiBanhang = RetroficClient.getInstance(Utils.BASE_URL).create(ApiBanhang.class);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        repass = findViewById(R.id.repass);
        button = findViewById(R.id.btndangki);
        mobile = findViewById(R.id.mobile);
        username = findViewById(R.id.username);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}