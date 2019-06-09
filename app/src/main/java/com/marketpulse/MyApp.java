package com.marketpulse;

import android.app.Application;

public class MyApp extends Application {
    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BuildConfig.base_url))
                .build();
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
