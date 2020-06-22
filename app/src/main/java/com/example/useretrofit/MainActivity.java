package com.example.useretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.useretrofit.interfaces.Api;
import com.example.useretrofit.models.PostModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.datoId);
        getPost();

    }

    private void getPost() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<List<PostModel>> call = api.getPost();

        call.enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {


                if(!response.isSuccessful()) {
                    text.setText(response.code());
                    return;
                }

                List<PostModel> postList = response.body();
                String content ="";
                for(PostModel post: postList) {

                    content+= "userId:"+post.getTitle() + "\n";

                }
                text.setText(content);
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                    text.setText((t.getMessage()));
            }
        });
    }
}
