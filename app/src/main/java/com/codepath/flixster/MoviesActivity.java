package com.codepath.flixster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        // Trying out a sample "Now Playing" API call
        String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJSONResults = null;
                try {
                    movieJSONResults = response.getJSONArray("results");
                    ArrayList<Movie> movies = Movie.fromJSONArray(movieJSONResults);
                    // Call the method to display these movies.
                    displayMovies(movies);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

        /*
        // 1. Get the actual movies
        ArrayList<Movie> movies = Movie.getFakeMovies();


        // 2. Get the ListView that we want to populate
        ListView lvMovies = (ListView) findViewById(R.id.lvMovies);

        // 3. Create an ArrayAdapter
        ArrayAdapter<Movie> adapterOld = new ArrayAdapter<Movie>(
                                            this, android.R.layout.simple_list_item_1, movies);

        // 3.2 Use our fancy new MoviesAdapter.
        MoviesAdapter adapter = new MoviesAdapter(this, movies);

        // 4. Associate the adapter with the ListView
        lvMovies.setAdapter(adapter);
        */
    }
    private void displayMovies(ArrayList<Movie> movies) {
        // 1. Get the ListView that we want to populate
        ListView lvMovies = (ListView) findViewById(R.id.lvMovies);

        // 2. Use our fancy new MoviesAdapter.
        MoviesAdapter adapter = new MoviesAdapter(this, movies);

        // 3. Associate the adapter with the ListView
        lvMovies.setAdapter(adapter);
    }
}
