package com.manager.lamtheoapp_ban_maytinhdt.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.manager.lamtheoapp_ban_maytinhdt.R;
import com.manager.lamtheoapp_ban_maytinhdt.adapter.DoAnAdapter;
import com.manager.lamtheoapp_ban_maytinhdt.model.SanPhamMoi;
import com.manager.lamtheoapp_ban_maytinhdt.retrofit.ApiBanhang;
import com.manager.lamtheoapp_ban_maytinhdt.retrofit.RetroficClient;
import com.manager.lamtheoapp_ban_maytinhdt.ultil.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DoAnActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    ApiBanhang apiBanhang;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    int page =1;
    int loai;
    DoAnAdapter adapterDt;
    List<SanPhamMoi> sanPhamMoiList;
    LinearLayoutManager linearLayoutManager;
    Handler handler = new Handler();
    boolean isLoading = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_do_an);

        apiBanhang = RetroficClient.getInstance(Utils.BASE_URL).create(ApiBanhang.class);
        loai = getIntent().getIntExtra("loai", 1);

        
        Anhxa();
        ActionToolBar();
        GetData(page);
        addEventLoad();
        if(loai==2){
            toolbar.setTitle("Đồ Uống");
        }
    }

    private void addEventLoad() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(isLoading==false){
                    if(linearLayoutManager.findLastCompletelyVisibleItemPosition()==sanPhamMoiList.size()-1){
                        isLoading = true;
                        loadMore();
                    }

                }
            }
        });
    }

    private  void loadMore(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                sanPhamMoiList.add(null);
                adapterDt.notifyItemInserted(sanPhamMoiList.size()-1);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                 sanPhamMoiList.remove(sanPhamMoiList.size()-1);
                 adapterDt.notifyItemRemoved(sanPhamMoiList.size());
                 page = page+1;
                 GetData(page);
                 adapterDt.notifyDataSetChanged();
                 isLoading = false;
            }
        }, 2000);
    }

    private void GetData(int page) {
        compositeDisposable.add(apiBanhang.getSanPham(page, loai)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                   sanPhamMoiModel -> {
                       if(sanPhamMoiModel.isSucess()){

                           if(adapterDt == null){
                               sanPhamMoiList = sanPhamMoiModel.getResult();
                               adapterDt = new DoAnAdapter(getApplicationContext(), sanPhamMoiList);
                               recyclerView.setAdapter(adapterDt);
                           }else{
                               int vitri = sanPhamMoiList.size()-1;
                               int soluong = sanPhamMoiModel.getResult().size();
                               for (int i=0;i<soluong; i++){
                                   sanPhamMoiList.add(sanPhamMoiModel.getResult().get(i));
                               }
                               adapterDt.notifyItemRangeInserted(vitri, soluong);
                           }


                       }
                       else{
                           Toast.makeText(getApplicationContext(), "Hết dữ liệu rồi", Toast.LENGTH_SHORT).show();
                           isLoading = true;
                       }

                   },
                        throwable -> {
                            Toast.makeText(getApplicationContext(), "Không kết nối server", Toast.LENGTH_SHORT).show();
                        }
                ));
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycleview_dt);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        sanPhamMoiList = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}