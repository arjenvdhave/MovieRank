package com.movierank.arjen.movierank.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.io.Serializable;
import java.util.List;

/**
 * Created by arjen on 2/26/16.
 */
@Root(name = "filmsoptv", strict = false)
public class MovieList implements Serializable {

    @ElementList(inline = true)
    public List<Movie> movies;
}
