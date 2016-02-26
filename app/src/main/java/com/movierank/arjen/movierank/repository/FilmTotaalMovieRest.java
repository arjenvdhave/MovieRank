package com.movierank.arjen.movierank.repository;

/**
 * Created by arjen on 2/22/16.
 */


import android.util.Log;

import com.movierank.arjen.movierank.utils.NetworkCall;
import com.movierank.arjen.movierank.model.MovieList;
import com.movierank.arjen.movierank.restinterface.FilmTotaalApi;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class FilmTotaalMovieRest<T> {

    private final String API_KEY = "6spel9dxsyk6slpp3tedgl5454d33f38";

    private Retrofit retrofit;
    private FilmTotaalApi api;

    public FilmTotaalMovieRest() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.filmtotaal.nl/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        api = retrofit.create(FilmTotaalApi.class);
    }

    public void getMoviesToday(){
        new NetworkCall<MovieList>(this).execute( api.moviesToday(getParams()));
    }

    public void onSuccess(T movies, Response response){

    }
    public void onError(Response response){
        if( response == null){
            Log.d("MR", "Error zonder response");
        }else{
            Log.d("MR","Error: "+response.message());
        }

    }

    private Map<String,String> getParams(){
        Map<String,String> params = new HashMap<>();
        params.put("apikey", API_KEY);
        params.put("dag", getDay());
        params.put("sorteer", "0");
        return params;
    }

    private String getDay(){
        Calendar date = Calendar.getInstance();
        String dag = "";
        dag += formatWithLeadingZero(date.get(Calendar.DATE));
        dag += "-";
        dag += formatWithLeadingZero(date.get(Calendar.MONTH) + 1);
        dag += "-";
        dag += date.get(Calendar.YEAR);

        return dag;
    }

    private String formatWithLeadingZero( Integer value){
        if( value < 9)
            return "0"+value;
        return value.toString();
    }
}
