package com.alvardev.listento.rest;

import android.content.Context;
import android.util.Log;

import com.alvardev.listento.R;
import com.alvardev.listento.models.response.ErrorResponse;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alvardev on 21/05/17.
 * Request to server
 * https://developer.spotify.com/web-api/console/get-search-item/
 */

public class ApiClient {

    private static final String BASE_URL = "https://api.spotify.com/v1/";
    private static final String TAG = "ApiClient";


    public static Retrofit getClient() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static String manageResponseErrors(retrofit2.Response response, Context context) {
        try {
            if(response != null && response.errorBody() != null){
                String errorString = response.errorBody().string();
                ErrorResponse error = new Gson().fromJson(errorString, ErrorResponse.class);

                return error.getMessages().size() > 0 ?
                        error.getMessages().get(0) :
                        context.getString(R.string.s_general_error);
            }else{
                Log.i(TAG, "[Error] response.error.body is null");
                return context.getString(R.string.s_general_error);
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.i(TAG, "[IOException] -> "+e.getMessage());
            return context.getString(R.string.s_general_error);
        } catch (JsonParseException e) {
            Log.e(TAG, "[Error] can't get error message, possible HTML as response");
            return context.getString(R.string.s_general_error);
        }
    }

}
