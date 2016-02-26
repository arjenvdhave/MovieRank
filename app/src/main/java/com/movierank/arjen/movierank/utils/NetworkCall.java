package com.movierank.arjen.movierank.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.movierank.arjen.movierank.repository.FilmTotaalMovieRest;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by arjen on 2/22/16.
 */
public class  NetworkCall<T> extends AsyncTask<Call<T>,Void,Response<T>> {

    FilmTotaalMovieRest filmTotaalMovieRest;

    public NetworkCall(FilmTotaalMovieRest filmTotaalMovieRest) {
        this.filmTotaalMovieRest = filmTotaalMovieRest;
    }

    @Override
    protected Response<T> doInBackground(Call... params) {
        try {
            Call<T> call = params[0];
            Response<T> response = call.execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Response<T> tResponse) {
        if( tResponse == null || !tResponse.isSuccess())
            filmTotaalMovieRest.onError(tResponse);
        else
            filmTotaalMovieRest.onSuccess(tResponse.body(), tResponse);
    }
}
