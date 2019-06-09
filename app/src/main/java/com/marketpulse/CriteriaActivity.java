package com.marketpulse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.List;
import java.util.Objects;

public class CriteriaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criteria);

        Intent intent = getIntent();
        Objects.requireNonNull(getSupportActionBar()).setTitle(intent.getStringExtra("name"));
        getSupportActionBar().setSubtitle(intent.getStringExtra("tag"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<MarketResponseModel.Criteria> criteriaList = intent.getParcelableArrayListExtra("criteria_list");

        RecyclerView marketDataRecyclerview = findViewById(R.id.criteria_list_rv);
        CriteriaListAdapter criteriaListAdapter = new CriteriaListAdapter(this, criteriaList);
        marketDataRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        marketDataRecyclerview.setAdapter(criteriaListAdapter);
        criteriaListAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            super.onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
