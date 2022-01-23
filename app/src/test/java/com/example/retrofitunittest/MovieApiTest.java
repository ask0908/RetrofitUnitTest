package com.example.retrofitunittest;

import com.example.retrofitunittest.model.Movie;
import com.example.retrofitunittest.model.Reviews;
import com.example.retrofitunittest.model.Videos;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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

    /* https://answer-id.com/ko/63308629 */
    // Set a response for retrofit to handle. You can copy a sample response from your server to simulate a correct result or an error.
    // 처리할 레트로핏에 대한 응답을 설정합니다. 서버에서 샘플 응답을 복사하여 올바른 결과 또는 오류를 시뮬레이션할 수 있습니다.
    // MockResponse can also be customized with different parameters to match your test needs
    // MockResponse는 테스트 요구 사항에 맞게 다양한 매개변수로 사용자 정의할 수도 있습니다.
    @Test
    public void test() throws Exception {
        MockWebServer server = new MockWebServer();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server.url("http://api.themoviedb.org/3/movie/").toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//        server.enqueue(new MockResponse().setBody());

        MovieInterface movieInterface = retrofit.create(MovieInterface.class);

        // With your service created you can now call its method that should  consume the MockResponse above
        // 서비스가 생성되면 이제 위의 MockResponse를 사용해야 하는 메서드를 호출할 수 있습니다.
        // You can then use the desired assertion to check if the result is as expected. For example:
        // 그런 다음 원하는 어설션을 사용하여 결과가 예상대로인지 확인할 수 있습니다. 예를 들어:
        Call<Movie> call = movieInterface.getPopularMovies();
//        assertTrue(call.execute() != null);

        Response<Movie> response = call.execute();
        assertEquals(true, response.body().getResults() != null);
//        assertEquals(response.code(), 401);
//        assertTrue(response.isSuccessful());
//        assertEquals(true, call.execute());
        server.shutdown();
    }

}
