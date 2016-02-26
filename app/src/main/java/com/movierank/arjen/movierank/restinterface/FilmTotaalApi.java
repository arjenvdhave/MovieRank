package com.movierank.arjen.movierank.restinterface;

import com.movierank.arjen.movierank.model.Movie;
import com.movierank.arjen.movierank.model.MovieList;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by arjen on 2/22/16.
 */
public interface FilmTotaalApi {

    @GET("filmsoptv.xml")
    Call<MovieList> moviesToday(@QueryMap Map<String, String> options);
}
