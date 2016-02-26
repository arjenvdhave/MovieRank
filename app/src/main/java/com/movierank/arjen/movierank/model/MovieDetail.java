package com.movierank.arjen.movierank.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by arjen on 2/26/16.
 */
public class MovieDetail implements Serializable{

    public String Title;
    public Integer Year;
    public String Released;
    public String Runtime;
    public String Genre;
    public String Director;
    public String Writer;
    public String Actors;
    public String Plot;
    public String Language;
    public String Country;
    public String Awards;
    public String Poster;
}
