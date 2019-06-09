package com.marketpulse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

public class CriteriaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criteria);

        List<MarketResponseModel.Criteria> criteriaList = getIntent().getParcelableArrayListExtra("criteria_list");
    }
}
