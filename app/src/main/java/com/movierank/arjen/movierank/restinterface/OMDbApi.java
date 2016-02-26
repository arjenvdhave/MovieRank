package com.movierank.arjen.movierank.restinterface;

import com.movierank.arjen.movierank.model.MovieDetail;
import com.movierank.arjen.movierank.model.MovieList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by arjen on 2/26/16.
 */
public interface OMDbApi {

    @GET("?")
    Call<MovieDetail> movieDetails(@QueryMap Map<String, String> options);
}
