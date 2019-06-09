package com.marketpulse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Inject
    NetworkService networkService;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MyApp) getApplication()).getNetComponent().inject(this);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.text_view);
        getPlaylistItems();
    }

    private void getPlaylistItems() {
        Observable<List<MarketResponseModel>> observable = networkService.getMarketData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<MarketResponseModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        textView.setText(getString(R.string.error));
                        Log.d(TAG, e.getMessage());
                    }

                    @Override
                    public void onNext(List<MarketResponseModel> model) {
                        textView.setText(getString(R.string.success));
                    }
                });
    }
}
