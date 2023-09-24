package com.example.githubapp.rest;

import java.util.List;

import com.example.githubapp.model.GitHubRepo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubRepoEndPoint {
    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> getRepo(@Path("user") String name);

}
