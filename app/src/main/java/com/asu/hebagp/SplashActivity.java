package com.asu.hebagp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    String welcomeEn;
    String welcomeAr;
    String welcomeGr;
    String welcomeFr;
    String welcomeRu;
    String welcomeSp;

    long delayTime = 1000;

    TextView welcomeTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);

        initilizeComponents();
        if (ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED ) //permision_accepted
        {

            changeText();
        } else {
            // Show rationale and request permission.
            ActivityCompat.requestPermissions(SplashActivity.this,
                    new String[]{Manifest.permission.CAMERA}, 2);
        }

    }

    private void changeText() {



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               welcomeTV.setText(welcomeAr);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        welcomeTV.setText(welcomeGr);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                welcomeTV.setText(welcomeFr);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        welcomeTV.setText(welcomeSp);
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                welcomeTV.setText(welcomeRu);
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                                                        startActivity(intent);
                                                        finish();
                                                    }
                                                },delayTime);
                                            }
                                        },delayTime);
                                    }
                                },delayTime);
                            }
                        },delayTime);
                    }
                },delayTime);
            }
        },delayTime);



    }

    private void initilizeComponents() {

        welcomeTV = (TextView) findViewById(R.id.welcomeTV);

        welcomeEn = getResources().getString(R.string.welcomeEn);
        welcomeAr = getResources().getString(R.string.welcomeAr);
        welcomeGr = getResources().getString(R.string.welcomeGerman);

        welcomeFr = getResources().getString(R.string.welcomeFr);
        welcomeRu = getResources().getString(R.string.welcomeRussian);
        welcomeSp = getResources().getString(R.string.welcomeSpain);

        welcomeTV.setText(welcomeEn);


    }

    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == 2) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                changeText();

            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }
}
