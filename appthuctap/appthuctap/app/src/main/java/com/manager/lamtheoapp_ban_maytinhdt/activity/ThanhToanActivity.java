package com.manager.lamtheoapp_ban_maytinhdt.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.manager.lamtheoapp_ban_maytinhdt.R;
import com.manager.lamtheoapp_ban_maytinhdt.retrofit.ApiBanhang;
import com.manager.lamtheoapp_ban_maytinhdt.retrofit.RetroficClient;
import com.manager.lamtheoapp_ban_maytinhdt.ultil.Utils;
import com.google.gson.Gson;

import java.text.DecimalFormat;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ThanhToanActivity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txttongtien, txtsodt, txtemail;
    EditText edtdiachi;

    ApiBanhang apiBanhang;
    Button btndathang;
    long tongtien;
    int totalItem;

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        initView();
        initControll();
        coutItem();
    }

    private void coutItem() {
        totalItem = 0;
        for(int i =0; i<Utils.manggiohang.size(); i++){
            totalItem = totalItem + Utils.manggiohang.get(i).getSoluong();
        }
    }

    private void initControll() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongtien = getIntent().getLongExtra("tongtien", 0);
        txttongtien.setText(decimalFormat.format(tongtien));
        txtsodt.setText(Utils.user_current.getMobile());
        txtemail.setText(Utils.user_current.getEmail());

        btndathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_diachi = edtdiachi.getText().toString().trim();
                if(TextUtils.isEmpty(str_diachi)){
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
                }
                else{
                    //post data
                    String str_email = Utils.user_current.getEmail();
                    String str_sodt = Utils.user_current.getMobile();
                    int id = Utils.user_current.getId();
                    Log.d("test", new Gson().toJson(Utils.manggiohang));
//                    compositeDisposable.add(apiBanhang.createOder(str_email, str_sodt, String.valueOf(tongtien), id, str_diachi,totalItem, new Gson().toJson(Utils.manggiohang))
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(
//                                    userModel -> {
//                                        Toast.makeText(getApplicationContext(), "thanh cong", Toast.LENGTH_SHORT).show();
//                                        Intent in = new Intent(getApplicationContext(), MainActivity.class);
//                                        startActivity(in);
//                                        finish();
//                                    },throwable -> {
//                                        Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
//                                    }
//                            ));



                    compositeDisposable.add(apiBanhang.createOder(str_email, str_sodt,String.valueOf(tongtien), id, str_diachi, totalItem, new Gson().toJson(Utils.manggiohang))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                               userModel -> {
                                   Toast.makeText(getApplicationContext(), "Thanh cong", Toast.LENGTH_SHORT).show();
                                   Utils.manggiohang.clear();
                                   Intent in = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(in);
                                        finish();
                               }
                                    ,throwable -> {
                                        Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                            ));
                }
            }
        });
    }

    private void initView() {
        apiBanhang = RetroficClient.getInstance(Utils.BASE_URL).create(ApiBanhang.class);
        toolbar = findViewById(R.id.toolbar11);
        txtemail = findViewById(R.id.txtemail);
        txtsodt = findViewById(R.id.txtsodienthoai);
        txttongtien = findViewById(R.id.txttongtien);
        edtdiachi = findViewById(R.id.edtdiachi);
        btndathang = findViewById(R.id.btndathang);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}