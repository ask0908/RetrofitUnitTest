package com.example.retrofitunittest;

import com.example.retrofitunittest.model.Movie;
import com.example.retrofitunittest.model.Reviews;
import com.example.retrofitunittest.model.Videos;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MovieApiTest extends BaseTestCase {

    @Test
    public void getTopRatedMovies() throws IOException {

        Call<Movie> topRatedList = getTmdbApiClient().movieInterface().getTopRatedMovies();
        Response<Movie> movieResponse = topRatedList.execute();

        assertEquals(movieResponse.code(), 200);
        assertTrue(movieResponse.isSuccessful());

    }

    @Test
    public void getPopularMovies() throws IOException {

        Call<Movie> popularMovies = getTmdbApiClient().movieInterface().getPopularMovies();
        Response<Movie> movieResponse = popularMovies.execute();

        assertEquals(movieResponse.code(), 200);
        assertTrue(movieResponse.isSuccessful());
    }

    @Test
    public void getMovieReviews() throws IOException {

        Call<Reviews> popularMovies = getTmdbApiClient().movieInterface().getMovieReviews(TestData.MOVIE_ID);
        Response<Reviews> reviewsResponse = popularMovies.execute();

        assertEquals(reviewsResponse.code(), 200);
        assertTrue(reviewsResponse.isSuccessful());
    }

    @Test
    public void getPopularVideos() throws IOException {

        Call<Videos> popularMovies = getTmdbApiClient().movieInterface().getMovieVideos(TestData.MOVIE_ID);
        Response<Videos> videosResponse = popularMovies.execute();

        assertEquals(videosResponse.code(), 200);
        assertTrue(videosResponse.isSuccessful());
    }

}
