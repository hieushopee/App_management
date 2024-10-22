package com.manager.lamtheoapp_ban_maytinhdt.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.manager.lamtheoapp_ban_maytinhdt.R;
import com.manager.lamtheoapp_ban_maytinhdt.adapter.DoAnAdapter;
import com.manager.lamtheoapp_ban_maytinhdt.model.SanPhamMoi;
import com.manager.lamtheoapp_ban_maytinhdt.retrofit.ApiBanhang;
import com.manager.lamtheoapp_ban_maytinhdt.retrofit.RetroficClient;
import com.manager.lamtheoapp_ban_maytinhdt.ultil.Utils;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    DoAnAdapter adapterDt;
    List<SanPhamMoi> sanPhamMoiList;
    ApiBanhang apiBanhang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    EditText edtsearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
//        initToolbar();
        initView();

    }

    private void initView() {
        apiBanhang = RetroficClient.getInstance(Utils.BASE_URL).create(ApiBanhang.class);
        toolbar = findViewById(R.id.toolbar1);
        recyclerView = findViewById(R.id.recycleview_search);
        LinearLayoutManager  layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        edtsearch = findViewById(R.id.edtsearch);
        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                getDataSearch();
            }
        });
    }

    private void getDataSearch() {
        String str_search = edtsearch.getText().toString().trim();
        compositeDisposable.add(apiBanhang.search(str_search)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        sanPhamMoiModel -> {
                            if(sanPhamMoiModel.isSucess()){
                                adapterDt = new DoAnAdapter(getApplicationContext(), sanPhamMoiModel.getResult());
                                recyclerView.setAdapter(adapterDt);
                            }
                        }, throwable -> {
                            Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                ));
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}