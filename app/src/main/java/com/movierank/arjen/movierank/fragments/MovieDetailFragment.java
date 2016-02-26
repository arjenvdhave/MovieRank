package com.movierank.arjen.movierank.fragments;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ConsoleMessage;
import android.widget.ImageView;
import android.widget.TextView;

import com.movierank.arjen.movierank.R;
import com.movierank.arjen.movierank.model.Movie;
import com.movierank.arjen.movierank.model.MovieDetail;
import com.movierank.arjen.movierank.repository.OMDbRest;
import com.movierank.arjen.movierank.utils.ImageDownloadTask;

import java.net.URL;

import retrofit2.Response;

public class MovieDetailFragment extends Fragment {
    private static final String SELECTED_MOVIE = "SELECTED_MOVIE";

    // TODO: Rename and change types of parameters
    private Movie selectedMovie;
    private MovieDetail movieDetail;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    public static MovieDetailFragment newInstance(Movie selectedMovie) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(SELECTED_MOVIE, selectedMovie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedMovie = (Movie)getArguments().getSerializable(SELECTED_MOVIE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        new OMDbRest(){
            @Override
            public void onSuccess(MovieDetail movieDetail, Response response) {
                MovieDetailFragment.this.movieDetail = movieDetail;
                onMovieChanged();
            }
        }.getMovieDetails(selectedMovie.imdb_id);


        return inflater.inflate(R.layout.fragment_movie_detail, container, false);
    }

    private void onMovieChanged(){
        if(movieDetail == null){
            return;
        }
        ((TextView) this.getView().findViewById(R.id.txtDetailTitle)).setText(movieDetail.Title);
        ((TextView) this.getView().findViewById(R.id.txtSubDetail)).setText(getSubDetailText());

        ((TextView) this.getView().findViewById(R.id.txtDetailReleased)).setText(movieDetail.Released);
        ((TextView) this.getView().findViewById(R.id.txtDetailRuntime)).setText(movieDetail.Runtime);
        ((TextView) this.getView().findViewById(R.id.txtDetailGenre)).setText(movieDetail.Genre);
        ((TextView) this.getView().findViewById(R.id.txtDetailDirector)).setText(movieDetail.Director);
        ((TextView) this.getView().findViewById(R.id.txtDetailWriter)).setText(movieDetail.Writer);
        ((TextView) this.getView().findViewById(R.id.txtDetailActors)).setText(movieDetail.Actors);
        ((TextView) this.getView().findViewById(R.id.txtDetailPlot)).setText(movieDetail.Plot);
        ((TextView) this.getView().findViewById(R.id.txtDetailLanguage)).setText(movieDetail.Language);
        ((TextView) this.getView().findViewById(R.id.txtDetailCountry)).setText(movieDetail.Country);
        ((TextView) this.getView().findViewById(R.id.txtDetailAwards)).setText(movieDetail.Awards);

        new ImageDownloadTask(new ImageDownloadTask.Callback(){
            @Override
            public void onBitmapLoaded(Bitmap bitmap) {
                ((ImageView) MovieDetailFragment.this.getView().findViewById(R.id.imgDetailPoster)).setImageBitmap(bitmap);
            }
        }).execute(movieDetail.Poster);
    }

    private String getSubDetailText(){
        return movieDetail.Year.toString() + " - Rating: "+selectedMovie.imdb_rating.toString();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
