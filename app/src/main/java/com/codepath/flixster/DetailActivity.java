package com.codepath.flixster;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.content.Context;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class DetailActivity extends Activity {
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        int pos = getIntent().getIntExtra("pos", -1);

        Bundle b = getIntent().getExtras();
        movie = (Movie) getIntent().getSerializableExtra("movie");
        /*
        String title = b.getString("title");
        String overview = b.getString("overview");
        String posterUrl = b.getString("posterUrl");
        String backdropUrl = b.getString("backdropUrl");

        double rating = Double.parseDouble(b.getString("rating"));
        */
        String title = movie.getTitle();
        String overview = movie.getOverview();
        String posterUrl = movie.getPosterUrl();
        String backdropUrl = movie.getBackdropUrl();
        double rating = movie.getRating() / 2.0;

        TextView tvTitle = (TextView) findViewById(R.id.tvTitleDetail);
        TextView tvOverview = (TextView) findViewById(R.id.tvOverviewDetail);
        ImageView ivBackdrop = (ImageView) findViewById(R.id.ivBackdropDetail);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        tvTitle.setText(title);
        tvOverview.setText(overview);
        String imageURI = backdropUrl;
        ratingBar.setRating((float) rating);
        Picasso.with(this).load(imageURI).fit().centerCrop().
                placeholder(R.drawable.placeholder_thumbnail).into(ivBackdrop);
    }

    private final int REQUEST_CODE = 23;

    public void onVideoClick(View view) {
        // TODO get movie ID, use to make API call and launch WatchYtActivity.
        int id = movie.getId();
        String url = "https://api.themoviedb.org/3/movie/"+id+"/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJSONResults = null;
                try {
                    movieJSONResults = response.getJSONArray("results");
                    JSONObject res = movieJSONResults.getJSONObject(0);
                    String key = res.getString("key");

                    Intent i = new Intent(DetailActivity.this, WatchYtActivity.class);

                    i.putExtra("key", key);
                    startActivity(i);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });

    }
}
