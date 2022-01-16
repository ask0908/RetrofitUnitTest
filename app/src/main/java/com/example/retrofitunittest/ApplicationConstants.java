package com.example.retrofitunittest;

public class ApplicationConstants {
    /**
     * Set to true to Enable Debugging in the API false to disable. This should be false when releasing the app
     * API에서 디버깅을 활성화하려면 true로 설정하고 비활성화하려면 false로 설정합니다. 앱을 출시할 때 false여야 합니다.
     */
    public static final boolean DEBUG = true;
    /**
     * API Endpoint
     */
    public static final String END_POINT = "http://api.themoviedb.org/3/movie/";
    /**
     * Connection timeout duration
     * 연결 시간 초과 기간
     */
    public static final int CONNECT_TIMEOUT = 60 * 1000;
    /**
     * Connection Read timeout duration
     * 연결 읽기 시간 초과 기간
     */
    public static final int READ_TIMEOUT = 60 * 1000;
    /**
     * Connection write timeout duration
     * 연결 쓰기 시간 초과 기간
     */
    public static final int WRITE_TIMEOUT = 60 * 1000;

}
