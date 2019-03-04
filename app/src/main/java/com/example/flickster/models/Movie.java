package com.example.flickster.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
public class Movie
{
    int movieId;
    double voteAverage;
    String posterPath, title, overview, backdropPath;

    public Movie(JSONObject jsonObject) throws JSONException //who ever calls the method has to deal with the catch
    {
        posterPath = jsonObject.getString("poster_path");
        title=jsonObject.getString("title");
        overview=jsonObject.getString("overview");
        backdropPath=jsonObject.getString("backdrop_path");
        voteAverage= jsonObject.getDouble("vote_average");
        movieId =jsonObject.getInt("id");

    }
    //Empty constructor needed by Parcel
    public Movie()
    {

    }
    public static List<Movie> fromJSONArray(JSONArray movieJsonArray) throws JSONException
    {
        List<Movie> movies = new ArrayList<>();
        for(int i =0; i< movieJsonArray.length(); i++) //for loop to add the movies
        {
            movies.add(new Movie(movieJsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterPath() { //used the generator for these  getters
        return String.format("https://image.tmdb.org/t/p/w342%s", posterPath);
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342%s",backdropPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public int getMovieId() {
        return movieId;
    }
}
