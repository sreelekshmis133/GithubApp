package com.example.githubapp;
import android.view.Gravity;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Handle incoming FCM messages here
        String notificationText = remoteMessage.getNotification().getBody();

        // Show a custom Toast message at the top of the screen
        showToastAtTop(notificationText);


    }
    private void showToastAtTop(String message) {
        Toast toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG);

        // Position the Toast at the top of the screen
        toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, 0);

        // Show the Toast
        toast.show();
    }
}