package com.example.internship_ex1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private RecyclerView rcvData;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ItemAdapter itemAdapter;

    private Button btnLoadmore;

    public int QUANTITY = 10;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rcvData = findViewById(R.id.rcv_data);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        btnLoadmore = findViewById(R.id.btnLoadmore);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcvData.setLayoutManager(linearLayoutManager);

        itemAdapter = new ItemAdapter();
        itemAdapter.setData(getListItems());
        rcvData.setAdapter(itemAdapter);

        btnLoadmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMoreItems();
                itemAdapter.setData(getListItems());
            }
        });

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    private List<ItemObject> getListItems() {
        List<ItemObject> list = new ArrayList<>();
        for (int i=0;i<QUANTITY;i++){
            list.add(new ItemObject("Item " + i));
        }
        return list;
    }

    @Override
    public void onRefresh() {
        itemAdapter.setData(getListItems());
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        },3000);
    }

    public void showMoreItems() {
        QUANTITY += 10;
    }
}