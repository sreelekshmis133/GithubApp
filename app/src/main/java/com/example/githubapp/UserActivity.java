package com.example.githubapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.githubapp.model.GitHubUser;
import com.example.githubapp.rest.ApiClient;
import com.example.githubapp.rest.GitHubUserEndPoint;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    ImageView avatarImg;
    TextView userNameTV;
    TextView followersTV;
    TextView followingTV;
    TextView logIn;
    TextView email;
    Button ownedrepos;

    String username;

    Bitmap myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        avatarImg = findViewById(R.id.avatar);
        userNameTV = findViewById(R.id.username);
        followersTV = findViewById(R.id.followers);
        followingTV = findViewById(R.id.following);
        logIn = findViewById(R.id.logIn);
        email = findViewById(R.id.email);
        ownedrepos = findViewById(R.id.ownedReposBtn);

        username = getIntent().getStringExtra("username");

        System.out.println("Received username: " + username);

        loadData();


    }

    public void loadOwnRepos(View view) {
        Intent intent = new Intent(UserActivity.this, Repositories.class);
        intent.putExtra("username", username);
        startActivity(intent);
    }

    public class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... urls) {
            try {
                URL url = new URL(urls[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                Bitmap myBitmap = BitmapFactory.decodeStream(inputStream);
                return myBitmap;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void loadData() {
        final GitHubUserEndPoint apiService =
                ApiClient.getClient().create(GitHubUserEndPoint.class);

        Call<GitHubUser> call = apiService.getUser(username);
        call.enqueue(new Callback<GitHubUser>() {
            @Override
            public void onResponse(Call<GitHubUser> call, Response<GitHubUser> response) {
                ImageDownloader task = new ImageDownloader();
                try {
                    myImage = task.execute(response.body().getAvatar()).get();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                avatarImg.setImageBitmap(myImage);
                avatarImg.getLayoutParams().height=220;
                avatarImg.getLayoutParams().width=220;

                if (response.body().getName() == null) {
                    userNameTV.setText("No name provided");
                } else {
                    userNameTV.setText("Username: " + response.body().getName());
                }

                followersTV.setText("Followers: " + response.body().getFollowers());
                followingTV.setText("Following: " + response.body().getFollowing());
                logIn.setText("LogIn: " + response.body().getLogin());

                if (response.body().getEmail() == null) {
                    email.setText("No email provided");
                } else {
                    email.setText("Email: " + response.body().getEmail());
                }
            }

            @Override
            public void onFailure(Call<GitHubUser> call, Throwable t) {
                System.out.println("Failed!" + t.toString());
            }
        });
    }
}
