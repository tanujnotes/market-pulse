package com.marketpulse;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

public interface NetworkService {

    @GET("data/")
    Observable<List<MarketResponseModel>> getMarketData();

}
