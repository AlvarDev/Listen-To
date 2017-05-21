package com.alvardev.listento.rest;

import com.alvardev.listento.models.response.TracksResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alvardev on 21/05/17.
 * Interface for request to API server
 */

public interface ApiClientInterface {

    @GET("search")
    Call<TracksResponse> getSongsFromSpotify(@Query("q") String q,
                                    @Query("type") String type);

}
