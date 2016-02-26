package com.movierank.arjen.movierank.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import java.io.Serializable;

/**
 * Created by arjen on 2/22/16.
 */
@Root(name = "film", strict = false)
public class Movie implements Serializable{
    public Long id;

    @Element(name = "titel", required = false)
    public String titel;

    @Element(name = "jaar", required = false)
    public String jaar;

    @Element(name = "imdb_id", required = false)
    public String imdb_id;

    @Element(name = "imdb_rating", required = false)
    public Double imdb_rating;

    public Movie() {
    }
}
