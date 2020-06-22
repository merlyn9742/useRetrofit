package com.example.useretrofit.interfaces;

import com.example.useretrofit.models.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("posts")
    Call<List<PostModel>> getPost();
}
