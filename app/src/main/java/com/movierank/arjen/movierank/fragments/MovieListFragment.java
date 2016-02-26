package com.movierank.arjen.movierank.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movierank.arjen.movierank.R;
import com.movierank.arjen.movierank.adapters.MyMovieRecyclerViewAdapter;
import com.movierank.arjen.movierank.model.Movie;
import com.movierank.arjen.movierank.model.MovieList;
import com.movierank.arjen.movierank.repository.FilmTotaalMovieRest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Response;

public class MovieListFragment extends Fragment {

    private OnMovieListFragmentMovieClickedListener mListener;
    private MyMovieRecyclerViewAdapter myMovieRecyclerViewAdapter;
    private List<Movie> allMovies;

    public MovieListFragment() {
    }

    public static MovieListFragment newInstance() {
        MovieListFragment fragment = new MovieListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));

            allMovies = new ArrayList<>();
            new FilmTotaalMovieRest<MovieList>(){
                @Override
                public void onSuccess(MovieList movieList, Response response) {
                    Log.d("MR", Integer.toString(movieList.movies.size()));
                    allMovies.addAll(movieList.movies);

                    Collections.sort(allMovies, new Comparator<Movie>() {
                        @Override
                        public int compare(Movie lhs, Movie rhs) {
                            if (lhs == null && rhs != null)
                                return -1;
                            if (lhs != null && rhs == null)
                                return 1;
                            if (lhs == null && rhs == null)
                                return 0;
                            return lhs.imdb_rating.compareTo(rhs.imdb_rating);
                        }
                    });
                    Collections.reverse(allMovies);
                    myMovieRecyclerViewAdapter.notifyDataSetChanged();
                }
            }.getMoviesToday();

            myMovieRecyclerViewAdapter = new MyMovieRecyclerViewAdapter(allMovies, mListener);
            recyclerView.setAdapter(myMovieRecyclerViewAdapter);
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnMovieListFragmentMovieClickedListener) {
            mListener = (OnMovieListFragmentMovieClickedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnMovieListFragmentMovieClickedListener {
        void onMovieListFragmentMovieClickedListener(Movie item);
    }
}
