package com.marketpulse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class CriteriaActivity extends AppCompatActivity {

    private List<MarketResponseModel.Criteria> criteriaList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criteria);

        Intent intent = getIntent();
        getSupportActionBar().setTitle(intent.getStringExtra("name"));
        getSupportActionBar().setSubtitle(intent.getStringExtra("tag"));
        criteriaList = intent.getParcelableArrayListExtra("criteria_list");

        RecyclerView marketDataRecyclerview = findViewById(R.id.criteria_list_rv);
        CriteriaListAdapter criteriaListAdapter = new CriteriaListAdapter(this, criteriaList);
        marketDataRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        marketDataRecyclerview.setAdapter(criteriaListAdapter);
        criteriaListAdapter.notifyDataSetChanged();
    }
}
