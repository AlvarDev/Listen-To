package com.alvardev.listento.rest;

import android.content.Context;
import android.util.Log;

import com.alvardev.listento.R;
import com.alvardev.listento.models.response.ErrorResponse;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
                .client(getHttpClient())
                .build();
    }

    private static OkHttpClient getHttpClient() {
        OkHttpClient httpClient = new OkHttpClient();
        OkHttpClient.Builder httpClientBuilder = httpClient.newBuilder();
        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer BQDcmZ1s1aa3vgUy2emu_lQ9czTBGUx0PhPCozWNf0RKGkoseUGyUkLmxvl1elaO-CfnpQDGHA_ubfhhmQ-bLWjAlh8tmDROTKFFTCwmbXF2Oqc4Jxko3ZvB5sFgh0ism0dnkgoquaDeYtUv")
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            }
        });

        httpClient = httpClientBuilder.build();
        return httpClient;
    }

    public static String manageResponseErrors(retrofit2.Response response, Context context) {
        try {
            if(response != null && response.errorBody() != null){
                String errorString = response.errorBody().string();
                ErrorResponse error = new Gson().fromJson(errorString, ErrorResponse.class);

                return error != null && error.getMessages() != null && error.getMessages().size() > 0 ?
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
