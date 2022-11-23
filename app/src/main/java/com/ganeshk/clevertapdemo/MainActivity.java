package com.ganeshk.clevertapdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.clevertap.android.sdk.CleverTapAPI;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    CleverTapAPI clevertapDefaultInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clevertapDefaultInstance = CleverTapAPI.getDefaultInstance(getApplicationContext());

        CleverTapAPI cleverTapAPI = CleverTapAPI.getDefaultInstance(getApplicationContext());
        cleverTapAPI.createNotificationChannel(getApplicationContext(),"1","CleverTapDemo","CleverTap Demo Channel", NotificationManager.IMPORTANCE_MAX,true);
        cleverTapAPI.pushEvent("Test Event");
    }

    public void viewProduct(View view) {

        try {
            //Push Product Viewed Event
            String product_image_url = "https://d35fo82fjcw0y8.cloudfront.net/2018/07/26020307/customer-success-clevertap.jpg";
            HashMap<String, Object> prodViewedAction = new HashMap<String, Object>();
            prodViewedAction.put("Product ID", "1");
            prodViewedAction.put("Product Image", product_image_url);
            prodViewedAction.put("Product Name", "CleverTap");
            prodViewedAction.put("Date", new java.util.Date());
            clevertapDefaultInstance.pushEvent("Product viewed", prodViewedAction);

            //Push an Email Id to the profile
            String email = "jidesh+kamble.ganesh004@clevertap.com";
            HashMap<String, Object> profileUpdate = new HashMap<String, Object>();
            profileUpdate.put("Email", email);
            clevertapDefaultInstance.pushProfile(profileUpdate);

            Toast.makeText(this, "Product Viewed Event Pushed \n Push an Email Id to the profile", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this, "Product view Failed \nTry Again Later", Toast.LENGTH_SHORT).show();
        }
    }
}