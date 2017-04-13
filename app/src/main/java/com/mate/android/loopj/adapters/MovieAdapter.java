package com.mate.android.loopj.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mate.android.loopj.R;

import java.util.ArrayList;

/**
 * Created by anujgupta on 13/04/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.Movie> {

    Context context;
    ArrayList<String> titles;

    public MovieAdapter(Context context, ArrayList<String> titles) {
        this.context = context;
        this.titles = titles;
    }

    @Override
    public Movie onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_movie,parent,false);
        Movie m = new Movie(v);
        return m;

    }

    @Override
    public void onBindViewHolder(Movie holder, int position) {

        holder.tvMovie.setText(titles.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class Movie extends RecyclerView.ViewHolder{

        TextView tvMovie;
        public Movie(View v) {
            super(v);

            tvMovie = (TextView) v.findViewById(R.id.tvMovieTitle);

        }
    }
}
