package com.marketpulse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private View loadingView;
    private MarketDataAdapter marketDataAdapter;
    private List<MarketResponseModel> marketResponseList = new ArrayList<>();

    @Inject
    NetworkService networkService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApp) getApplication()).getNetComponent().inject(this);
        setContentView(R.layout.activity_main);

        loadingView = findViewById(R.id.loading_view);
        marketDataAdapter = new MarketDataAdapter(this, marketResponseList);
        RecyclerView marketDataRecyclerview = findViewById(R.id.market_data_rv);
        marketDataRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        marketDataRecyclerview.setAdapter(marketDataAdapter);

        loadingView.findViewById(R.id.tap_to_retry).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingView.findViewById(R.id.tap_to_retry).setVisibility(View.GONE);
                loadingView.findViewById(R.id.progress_bar).setVisibility(View.VISIBLE);
                getMarketDataList();
            }
        });

        getMarketDataList();
    }

    private void getMarketDataList() {
        Observable<List<MarketResponseModel>> observable = networkService.getMarketData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<MarketResponseModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, e.getMessage());
                        loadingView.findViewById(R.id.progress_bar).setVisibility(View.GONE);
                        loadingView.findViewById(R.id.tap_to_retry).setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onNext(List<MarketResponseModel> model) {
                        loadingView.setVisibility(View.GONE);
                        marketResponseList.clear();
                        marketResponseList.addAll(model);
                        marketDataAdapter.notifyDataSetChanged();
                    }
                });
    }
}
