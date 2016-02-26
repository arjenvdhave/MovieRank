package com.movierank.arjen.movierank.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.movierank.arjen.movierank.R;
import com.movierank.arjen.movierank.fragments.MovieDetailFragment;
import com.movierank.arjen.movierank.fragments.MovieListFragment;
import com.movierank.arjen.movierank.model.Movie;

public class MoviesToday extends AppCompatActivity implements MovieListFragment.OnMovieListFragmentMovieClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_today);

        MovieListFragment movieListFragment = MovieListFragment.newInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.movies_master_container, movieListFragment).commit();
    }


    @Override
    public void onMovieListFragmentMovieClickedListener(Movie item) {
        MovieDetailFragment detail = MovieDetailFragment.newInstance(item);
        getSupportFragmentManager().beginTransaction().replace(R.id.movies_master_container, detail).addToBackStack("BACK_TO_MOVIE_LIST").commit();
    }
}
