package com.manager.lamtheoapp_ban_maytinhdt.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.manager.lamtheoapp_ban_maytinhdt.R;
import com.manager.lamtheoapp_ban_maytinhdt.retrofit.ApiBanhang;
import com.manager.lamtheoapp_ban_maytinhdt.retrofit.RetroficClient;
import com.manager.lamtheoapp_ban_maytinhdt.ultil.Utils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ThongKeActivity extends AppCompatActivity {

    Toolbar toolbar;
    PieChart pieChart;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanhang apiBanhang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        apiBanhang = RetroficClient.getInstance(Utils.BASE_URL).create(ApiBanhang.class);
        initView();
        ActionToolBar();
        getData();

    }

    private void getData() {
        List<PieEntry> list = new ArrayList<>();
        compositeDisposable.add(apiBanhang.thongKe()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                   thongKeModel -> {
                       if(thongKeModel.isSuccess()){
                           for(int i=0; i<thongKeModel.getResult().size(); i++){
                               String tensp = thongKeModel.getResult().get(i).getTensp();
                               int tong = thongKeModel.getResult().get(i).getTong();
                               list.add(new PieEntry(tong, tensp));
                           }
                           PieDataSet pieDataSet = new PieDataSet(list, "Thống kê");
                           PieData data = new PieData();
                           data.setDataSet(pieDataSet);
                           data.setValueTextSize(12f);
                           data.setValueFormatter(new PercentFormatter(pieChart));
                           pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

                           pieChart.setData(data);
                           pieChart.animateXY(2000, 2000);
                           pieChart.setUsePercentValues(true);
                           pieChart.getDescription().setEnabled(false);
                           pieChart.invalidate();

                       }

                   }   ,throwable -> {
                            Log.d("Logg", throwable.getMessage());
                        }
                ));
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar_thongke);
        pieChart = findViewById(R.id.piechart);
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

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}