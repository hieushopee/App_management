package com.manager.lamtheoapp_ban_maytinhdt.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.manager.lamtheoapp_ban_maytinhdt.R;
import com.manager.lamtheoapp_ban_maytinhdt.retrofit.ApiBanhang;
import com.manager.lamtheoapp_ban_maytinhdt.retrofit.RetroficClient;
import com.manager.lamtheoapp_ban_maytinhdt.ultil.Utils;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DangNhapActivity extends AppCompatActivity {

    TextView txtdangki;
    EditText email, pass;
    Button dangnhap;
    ApiBanhang apiBanhang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    boolean isLogin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        initView();
        initControl();
    }

    private void initControl() {
        txtdangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DangKiActivity.class);
                startActivity(intent);
            }
        });

        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_email = email.getText().toString().trim();
                String str_pass = pass.getText().toString().trim();
                if(TextUtils.isEmpty(str_email)){
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập email", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(str_pass)){
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập pass", Toast.LENGTH_SHORT).show();
                }
                else if(str_email.equals("admin@gmail.com")){
                        Intent intent1 = new Intent(getApplicationContext(), AdminActivity.class);
                        startActivity(intent1);
                }
                else{
                    //save data
                    Paper.book().write("email", str_email);
                    Paper.book().write("pass", str_pass);
                   dangnhap(str_email, str_pass);
                }
            }
        });
    }

    private void initView() {
        Paper.init(this);
        apiBanhang = RetroficClient.getInstance(Utils.BASE_URL).create(ApiBanhang.class);
        txtdangki = findViewById(R.id.txtdangki);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        dangnhap = findViewById(R.id.btndangnhap);

        //read data
        if(Paper.book().read("email") != null && Paper.book().read("pass") != null){
            email.setText(Paper.book().read("email"));
            pass.setText( Paper.book().read("pass"));
            if(Paper.book().read("isLogin") != null){
                boolean flag = Paper.book().read("isLogin");
                if(flag){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            dangnhap(Paper.book().read("email"), Paper.book().read("pass"));
                        }
                    }, 10000);
                }
            }
        }
    }

    private void dangnhap(String email, String pass) {
        compositeDisposable.add(apiBanhang.dangNhap(email, pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userModel -> {
                            if(userModel.isSuccess()){
                                isLogin = true;
                                Paper.book().write("isLogin", isLogin);
                                Utils.user_current = userModel.getResult().get(0);

                                //lluu lai thong tin ngươi dung
                                Paper.book().write("user", userModel.getResult().get(0));
//                                Paper.book().write("user3", userModel.getResult().get(3));
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
                                finish();
                            }

                        },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(Utils.user_current.getEmail() != null && Utils.user_current.getPass()!=null){
            email.setText(Utils.user_current.getEmail());
            pass.setText(Utils.user_current.getPass());
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}