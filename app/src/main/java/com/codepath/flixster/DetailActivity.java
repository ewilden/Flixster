package com.codepath.flixster;

import android.app.Activity;
import android.media.Image;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;

import com.squareup.picasso.Picasso;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        int pos = getIntent().getIntExtra("pos", -1);
        assert(pos >= 0);
        Bundle b = getIntent().getExtras();
        Movie movie = (Movie) getIntent().getSerializableExtra("movie");
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

        TextView tvTitle = (TextView) findViewById(R.id.tvTitleDetail);
        TextView tvOverview = (TextView) findViewById(R.id.tvOverviewDetail);
        ImageView ivBackdrop = (ImageView) findViewById(R.id.ivBackdropDetail);

        tvTitle.setText(title);
        tvOverview.setText(overview);
        String imageURI = backdropUrl;
        Picasso.with(this).load(imageURI).fit().centerCrop().
                placeholder(R.drawable.placeholder_thumbnail).into(ivBackdrop);
    }
}
