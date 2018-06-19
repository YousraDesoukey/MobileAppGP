package com.asu.hebagp;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Locale;

import Fragments.LanguageSettingsFragment;
import Fragments.MyItemsFragment;
import Fragments.ScannerFragment;

public class MainActivity extends AppCompatActivity {

    Fragment fragment;
    FragmentManager fragmentManager;

    public static int selectPicCode = 10;
    public static Uri outputFileUri;

    RelativeLayout scannerBtn , languageSettingsBtn , myItemsBtn;
    public static ImageView scannerIC , languageIC , myItemsIC;
    public static TextView scannerTV, languageTV , myItemsTV;

    public static Drawable deActiveScanner , deActiveItems , deActiveLanguage;
    public static Drawable activeScanner , activeItems , activeLanguage;

    public static String languageFragmentTag = "LANGUAGESETTINGSFRAGMENT";
    public static String scannerFragmentTag = "SCANNERFRAGMENT";
    public static String myItemsFragmentTag = "MYITEMSFRAGMENT";
    public static String itemPreviewFragmentTag = "ITEMPREVIEWFRAGMENT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initilizeLanguage();

        setContentView(R.layout.activity_main);

        initilizeComponents();
        deActiveAll();
        setFirstFragment();
    }

    private void initilizeLanguage() {

        switch (StartApplication.language) {

            case StartApplication.en_lang:
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                Configuration configuration = getResources().getConfiguration();
                configuration.setLayoutDirection(new Locale("en"));
                configuration.setLocale(new Locale("en"));
                getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
            }
            break;

            case StartApplication.ar_lang:
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                Configuration configuration = getResources().getConfiguration();
                configuration.setLayoutDirection(new Locale("ar"));
                configuration.setLocale(new Locale("ar"));
                getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
            }
            break;

            case StartApplication.de_lang:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    Configuration configuration = getResources().getConfiguration();
                    configuration.setLayoutDirection(new Locale("de"));
                    configuration.setLocale(new Locale("de"));
                    getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
                }
            break;

            case StartApplication.es_lang:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    Configuration configuration = getResources().getConfiguration();
                    configuration.setLayoutDirection(new Locale("es"));
                    configuration.setLocale(new Locale("es"));
                    getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
                }
                break;

            case StartApplication.fr_lang:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    Configuration configuration = getResources().getConfiguration();
                    configuration.setLayoutDirection(new Locale("fr"));
                    configuration.setLocale(new Locale("fr"));
                    getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
                }
                break;

            case StartApplication.ru_lang:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    Configuration configuration = getResources().getConfiguration();
                    configuration.setLayoutDirection(new Locale("ru"));
                    configuration.setLocale(new Locale("ru"));
                    getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
                }
                break;
        }
    }

    private void setFirstFragment() {

        fragment = fragmentManager.findFragmentById(R.id.FragmentProvider);

        if(fragment == null)
        {
            fragment = new ScannerFragment();
            fragmentManager.beginTransaction().add(R.id.FragmentProvider, fragment, scannerFragmentTag).commit();
        }
    }

    private void initilizeComponents() {
        fragmentManager = getSupportFragmentManager();

        scannerBtn = (RelativeLayout) findViewById(R.id.scannerBtn);
        languageSettingsBtn = (RelativeLayout) findViewById(R.id.languageBtn);
        myItemsBtn = (RelativeLayout) findViewById(R.id.myItemsBtn);

        scannerIC = (ImageView) findViewById(R.id.scannerIC);
        languageIC = (ImageView) findViewById(R.id.langIC);
        myItemsIC = (ImageView) findViewById(R.id.myItemsIC);

        scannerTV = (TextView) findViewById(R.id.scannerTV);
        languageTV = (TextView) findViewById(R.id.languageTV);
        myItemsTV = (TextView) findViewById(R.id.myItemsTV);

        deActiveScanner = getResources().getDrawable(R.drawable.scanner_ic);
        activeScanner = getResources().getDrawable(R.drawable.scanner_ic_activated);

        deActiveLanguage = getResources().getDrawable(R.drawable.language_ic);
        activeLanguage = getResources().getDrawable(R.drawable.language_ic_activated);

        deActiveItems = getResources().getDrawable(R.drawable.my_items_ic);
        activeItems = getResources().getDrawable(R.drawable.my_items_ic_activated);

        scannerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = fragmentManager.findFragmentById(R.id.FragmentProvider);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                String tag = fragment.getTag();

                if(!tag.equals(scannerFragmentTag))
                {
                    fragment = new ScannerFragment();
                    fragmentManager.popBackStack(MainActivity.scannerFragmentTag, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    fragmentTransaction.replace(R.id.FragmentProvider,fragment,MainActivity.scannerFragmentTag)
                            .setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(MainActivity.scannerFragmentTag).commit();
                }
            }
        });

        myItemsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fragment = fragmentManager.findFragmentById(R.id.FragmentProvider);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                String tag = fragment.getTag();

                if(!tag.equals(myItemsFragmentTag))
                {
                    fragment = new MyItemsFragment();
                    fragmentManager.popBackStack(MainActivity.myItemsFragmentTag, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    fragmentTransaction.replace(R.id.FragmentProvider,fragment,MainActivity.myItemsFragmentTag)
                            .setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(MainActivity.myItemsFragmentTag).commit();
                }
            }
        });

        languageSettingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment = fragmentManager.findFragmentById(R.id.FragmentProvider);
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                String tag = fragment.getTag();

                if(!tag.equals(languageFragmentTag))
                {
                    fragment = new LanguageSettingsFragment();
                    fragmentManager.popBackStack(MainActivity.languageFragmentTag, android.app.FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    fragmentTransaction.replace(R.id.FragmentProvider,fragment,MainActivity.languageFragmentTag)
                            .setTransition(android.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .addToBackStack(MainActivity.languageFragmentTag).commit();
                }
            }
        });
    }

    public static void deActiveAll()
    {

        scannerIC.setImageDrawable(deActiveScanner);
        languageIC.setImageDrawable(deActiveLanguage);
        myItemsIC.setImageDrawable(deActiveItems);

        myItemsTV.setTextColor(Color.parseColor("#666666"));
        languageTV.setTextColor(Color.parseColor("#666666"));
        scannerTV.setTextColor(Color.parseColor("#666666"));

    }

    public static void activeScanner()
    {
        deActiveAll();
        scannerIC.setImageDrawable(activeScanner);
        scannerTV.setTextColor(Color.parseColor("#a8642a"));
    }

    public static void activeLanguage()
    {
        deActiveAll();
        languageIC.setImageDrawable(activeLanguage);
        languageTV.setTextColor(Color.parseColor("#a8642a"));
    }

    public static void activeMyITems()
    {
        deActiveAll();
        myItemsIC.setImageDrawable(activeItems);
        myItemsTV.setTextColor(Color.parseColor("#a8642a"));
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode == RESULT_OK) {
//            if (requestCode == selectPicCode) {
////                final boolean isCamera;
////                if (data == null) {
////                    isCamera = true;
////                } else {
////                    final String action = data.getAction();
////                    if (action == null) {
////                        isCamera = false;
////                    } else {
////                        isCamera = MediaStore.ACTION_IMAGE_CAPTURE.equals(data.getAction());
////                    }
////                }
////
////                Uri selectedImageUri;
////                if (isCamera) {
////                    selectedImageUri = outputFileUri;
////                } else {
////                    selectedImageUri = data == null ? null : data.getData();
////                }
//                Uri uri;
//                if(data == null)
//                {
//                    uri = outputFileUri;
//
//                }else {
//                    if(data.getData() == null)
//                    {
//                        uri = outputFileUri;
//                    }else {
//                        uri = data.getData();
//                    }
//                }
//
//                ScannerFragment.imgURL = uri;
//
//
//                try {
//                    // HomeFragment.myPlaceBitMap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//
//                    Picasso.with(MainActivity.this).load(uri.toString()).into(ScannerFragment.addIV);
//
//                    // HomeFragment.addImageBtn.setImageBitmap(HomeFragment.myPlaceBitMap);
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    Toast.makeText(MainActivity.this,"Error in Storage",Toast.LENGTH_SHORT).show();
//                }
//            }
//        }
//    }

}
