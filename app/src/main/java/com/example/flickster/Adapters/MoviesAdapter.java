package com.example.flickster.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.flickster.DetailActivity;
import com.example.flickster.models.Movie;
import com.example.flickster.R;

import org.parceler.Parcels;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>
{
    Context context;
    List <Movie> movies;

    public MoviesAdapter(Context context, List<Movie> movies) //used generate constructor
    {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("smile", "onCreateViewHolder");
        View view= LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {
        Log.d("smile", "onCreateViewHolder" +position);
        Movie movie = movies.get(position);
        //Bind movie data into the view holder
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPosters;
        RelativeLayout container;
        public ViewHolder(View itemview) {
            super(itemview);
            tvTitle= itemView.findViewById(R.id.tvTitle);
            tvOverview=itemView.findViewById(R.id.tvOverview);
            ivPosters=itemView.findViewById(R.id.ivPoster);
            container=itemView.findViewById(R.id.container);
        }
        public void bind(final Movie movie)
        {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText((movie.getOverview()));
            String imageUrl= movie.getPosterPath();
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) // reference the backdrop path if phone is in landscape
            {
                imageUrl=movie.getBackdropPath();
            }
            Glide.with(context).load(imageUrl).into(ivPosters);
            //add click listern on the whole row
            container.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View view)
                {
                    //Naviagete to detail activity on tap
                    Intent i= new Intent(context, DetailActivity.class);

                    i.putExtra("movie", Parcels.wrap(movie));
                    context.startActivity(i);
                }
            });
        }
    }
}