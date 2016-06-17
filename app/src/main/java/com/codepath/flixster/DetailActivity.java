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

import com.squareup.picasso.Picasso;

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

    public void onVideoClick() {
        // TODO get movie ID, use to make API call and launch WatchYtActivity.

    }
}
