package com.movierank.arjen.movierank.repository;

import android.util.Log;

import com.movierank.arjen.movierank.model.MovieDetail;
import com.movierank.arjen.movierank.restinterface.OMDbApi;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by arjen on 2/26/16.
 */
public class OMDbRest {
    private Retrofit retrofit;
    private OMDbApi api;

    public OMDbRest() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://www.omdbapi.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(OMDbApi.class);
    }

    public void getMovieDetails(String imdbId){
       api.movieDetails(getParams(imdbId)).enqueue(new Callback<MovieDetail>() {
           @Override
           public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                onSuccess(response.body(), response);
           }

           @Override
           public void onFailure(Call<MovieDetail> call, Throwable t) {
               onError(null);
           }
       });
    }

    public void onSuccess(MovieDetail movieDetail, Response response){

    }
    public void onError(Response response){
        if( response == null){
            Log.d("MR", "Error zonder response");
        }else{
            Log.d("MR","Error: "+response.message());
        }

    }

    private Map<String,String> getParams(String imdbId){
        Map<String,String> params = new HashMap<>();
        params.put("i", "tt"+imdbId);
        params.put("plot", "full");
        params.put("r", "json");
        return params;
    }
}
