package com.example.retrofitunittest;

import com.example.retrofitunittest.model.Movie;
import com.example.retrofitunittest.model.Reviews;
import com.example.retrofitunittest.model.Videos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieInterface {

    /**
     * Get top rated movies
     *
     * @return JSON Result
     */
    @GET("top_rated?")
    Call<Movie> getTopRatedMovies();

    /**
     * Get popular movies.
     *
     * @return JSON Result
     */
    @GET("popular?")
    Call<Movie> getPopularMovies();

    /**
     * Endpoint to get
     *
     * @param movieId Movie ID
     * @return JSON Result
     */
    @GET("{id}/videos")
    Call<Videos> getMovieVideos(@Path("id") int movieId);

    /**
     * Get Movie Reviews
     *
     * @param movieId Movie ID
     * @return JSON Result
     */
    @GET("{id}/reviews")
    Call<Reviews> getMovieReviews(@Path("id") int movieId);

}
