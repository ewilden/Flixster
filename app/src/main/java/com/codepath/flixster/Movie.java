package com.codepath.flixster;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by evanwild on 6/15/16.
 */
public class Movie implements Serializable {
    public String title;
    public String posterUrl;
    public String overview;
    public String backdropUrl;
    public double rating;

    public String getTitle() {
        return title;
    }

    public String getPosterUrl() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterUrl);
    }

    public String getBackdropUrl() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropUrl);
    }

    public String getOverview() {
        return overview;
    }



    public double getRating() {
        return rating;
    }

    public Movie(String title, String posterUrl, float rating) {
        this.title = title;
        this.posterUrl = posterUrl;
        this.rating = rating;
    }

    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterUrl = jsonObject.getString("poster_path");
        this.title = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backdropUrl = jsonObject.getString("backdrop_path");
        this.rating = jsonObject.getDouble("vote_average");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray jsonArray) {
        ArrayList<Movie> results = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                results.add(new Movie(jsonArray.getJSONObject(i)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

    public static ArrayList<Movie> getFakeMovies() {
        ArrayList<Movie> movies = new ArrayList<>();

        for (int i = 0; i < 60; i++) {
            movies.add(new Movie("The Social Network", "", 75));
            movies.add(new Movie("The Internship", "", 50));
            movies.add(new Movie("The Lion King", "", 100));
        }

        return movies;
    }

    @Override
    public String toString() {
        return "Movie [title=" + title + ", posterUrl="+ posterUrl+ ", overview="+ overview+
                ", backdropUrl="+backdropUrl+", rating="+rating+"]";
    }
}
