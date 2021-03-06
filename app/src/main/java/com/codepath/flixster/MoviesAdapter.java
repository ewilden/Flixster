package com.codepath.flixster;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by evanwild on 6/15/16.
 */
public class MoviesAdapter extends ArrayAdapter<Movie> {

    // View lookup cache
    private static class ViewHolder {
        TextView  tvTitle;
        TextView  tvOverview;
        ImageView ivPoster;
        ImageView ivBackdrop;
    }

    public MoviesAdapter(Context context, ArrayList<Movie> movies) {
        super(context, R.layout.item_movie, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for position
        Movie movie = getItem(position);

        ViewHolder viewHolder; // view lookup cache stored in tag

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView =
                    LayoutInflater.from(getContext()).inflate(R.layout.item_movie, parent, false);
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.ivPoster = (ImageView) convertView.findViewById(R.id.ivPoster);
            viewHolder.ivBackdrop = (ImageView) convertView.findViewById(R.id.ivBackdrop);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Lookup view for data population

        TextView tvTitle = viewHolder.tvTitle;
        ImageView ivPoster = viewHolder.ivPoster;
        TextView tvOverview = viewHolder.tvOverview;
        ImageView ivBackdrop = viewHolder.ivBackdrop;

        // clear out image from convertView
        if (ivPoster != null) {
            ivPoster.setImageResource(0);
        }
        if (ivBackdrop != null)
            ivBackdrop.setImageResource(0);

        // Populate the data into the template view using the data object
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        //String imageURI = "https://i.imgur.com/tGbaZCY.jpg";

        if (ivPoster != null) {
            String imageURI = movie.getPosterUrl();
            Picasso.with(getContext()).load(imageURI).fit().centerCrop().
                    placeholder(R.drawable.placeholder_thumbnail).into(ivPoster);
        }
        if (ivBackdrop != null) {
            String imageURI = movie.getBackdropUrl();
            Picasso.with(getContext()).load(imageURI).fit().centerCrop().
                    placeholder(R.drawable.placeholder_thumbnail).into(ivBackdrop);
        }

        // Log.d("MoviesAdapter", "Position: "+ position);

        //ivPoster.set... TODO

        // Return the completed view to render on screen
        return convertView;
    }
}
