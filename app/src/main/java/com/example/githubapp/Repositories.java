package com.example.githubapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.githubapp.model.GitHubRepo;
import com.example.githubapp.rest.ApiClient;

import java.util.ArrayList;
import java.util.List;

import com.example.githubapp.adapter.ReposAdapter;
import com.example.githubapp.model.GitHubUser;
import com.example.githubapp.rest.ApiClient;
import com.example.githubapp.rest.GitHubRepoEndPoint;
import com.example.githubapp.rest.GitHubUserEndPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repositories extends AppCompatActivity {

    String receivedUserName;
    TextView userNameTV;
    RecyclerView mRecyclerView;
    List<GitHubRepo> myDataSource = new ArrayList<>();
    RecyclerView.Adapter myAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repositories);
        myDataSource = new ArrayList<>();
        receivedUserName = getIntent().getStringExtra("username");

//        Bundle extras = getIntent().getExtras();
//        receivedUserName = extras.getString("username");
//
//        userNameTV =  findViewById(R.id.userNameTV);
//
//        userNameTV.setText("User: " + receivedUserName);
//
//        mRecyclerView=  findViewById(R.id.repos_recycler_view);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        myAdapter = new ReposAdapter(myDataSource, R.layout.list_item_repo,
//                getApplicationContext());
//
//        mRecyclerView.setAdapter(myAdapter);
//
//        loadRepositories();
        userNameTV = findViewById(R.id.userNameTV);
        mRecyclerView = findViewById(R.id.repos_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new ReposAdapter(myDataSource, R.layout.list_item_repo, getApplicationContext());
        mRecyclerView.setAdapter(myAdapter);

        loadRepositories();

    }

//    public void loadRepositories(){
//
//        String defaultUsername = "octocat";
//
//        GitHubRepoEndPoint apiService =
//                ApiClient.getClient().create(GitHubRepoEndPoint.class);
//
//        Call<List<GitHubRepo>> call = apiService.getRepo(receivedUserName);
//        call.enqueue(new Callback<List<GitHubRepo>>() {
//            @SuppressLint("NotifyDataSetChanged")
//            @Override
//            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
//                if (response.isSuccessful()) {
//                    myDataSource.clear();
//                    assert response.body() != null;
//                    myDataSource.addAll(response.body());
//                    myAdapter.notifyDataSetChanged();
//                } else {
//                    // Log an error or handle the unsuccessful response
//                    Log.e("Repos", "Unsuccessful response: " + response.message());
//                }
////                myDataSource.clear();
////                assert response.body() != null;
////                myDataSource.addAll(response.body());
////                myAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
//                // Log error here since request failed
//                Log.e("Repos", t.toString());
//            }
//
//        });
//    }
public void loadRepositories() {
    Log.d("Repositories", "Loading repositories for username: " + receivedUserName);

    if (receivedUserName != null && !receivedUserName.isEmpty()) {
        GitHubRepoEndPoint apiService = ApiClient.getClient().create(GitHubRepoEndPoint.class);

        Call<List<GitHubRepo>> call = apiService.getRepo(receivedUserName);
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                Log.d("Repositories", "API Response: " + response.code());

                if (response.isSuccessful()) {
                    myDataSource.clear();
                    assert response.body() != null;
                    myDataSource.addAll(response.body());
                    myAdapter.notifyDataSetChanged();
                } else {
                    // Handle unsuccessful response, e.g., display an error message
                    Log.e("Repos", "Unsuccessful response: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                // Log error here since request failed
                Log.e("Repos", "API request failed: " + t.toString());
            }
        });
    } else {
        // Handle the case where receivedUserName is null or empty, e.g., display an error message
        Log.e("Repos", "Received username is null or empty");
    }
}

}