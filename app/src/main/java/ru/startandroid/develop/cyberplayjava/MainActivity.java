package ru.startandroid.develop.cyberplayjava;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private InterstitialAd mInterstitialAd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomMenu = (BottomNavigationView) findViewById(R.id.bottom_menu_navigation);
        bottomMenu.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadFragment(FragmentNades.newInstance(),false);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        InterstitialAd.load(this,"ca-app-pub-3940256099942544/1033173712", adRequest, new InterstitialAdLoadCallback() {
            @Override
            public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                // The mInterstitialAd reference will be null until
                // an ad is loaded.
                mInterstitialAd = interstitialAd;
                // Log.i("AdMob", "onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                // Handle the error
                mInterstitialAd = null;
            }
        });
        }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_nades:
                    loadFragment(FragmentNades.newInstance(),false);
                    return true;
                case R.id.navigation_board:
                    loadFragment(FragmentBoard.newInstance(),false);
                    return true;
                case R.id.navigation_train:
                    loadFragment(FragmentTrain.newInstance(),false);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment,boolean BackStack) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (BackStack == true)
        {
            ft.addToBackStack(null);
        }
        ft.replace(R.id.fl_content, fragment);
        ft.commit();
        if (mInterstitialAd != null) {
            mInterstitialAd.show(MainActivity.this);
        } else {
            // Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu, menu);
        MenuItem item = menu.findItem(R.id.navigation_profile);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                SharedPreferences myPreferences
                        = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                String local_jwtKey = myPreferences.getString("jwt", "unknown");
                // Log.d("ret",local_jwtKey);
                if(local_jwtKey.equals("unknown"))
                {
                    loadFragment(FragmentProfileSignInUp.newInstance(),true);
                }
                else
                {
                    // loadFragment(FragmentProfileSignInUp.newInstance(),true);
                  /*  Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://94.228.115.44:80/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    MessagesApi messagesApi = retrofit.create(MessagesApi.class);
                    Call<ValidateResponse> call = messagesApi.validateUser(new ValidateUser(local_jwtKey));
                    call.enqueue(new Callback<ValidateResponse>() {
                        @Override
                        public void onResponse(Call<ValidateResponse> call, Response<ValidateResponse> response) {
                            if(response.isSuccessful()) {
                                if (response.body().getMessage().equals("Access allowed.")) {
                                    loadFragment(FragmentProfile.newInstance(),true);
                                } else {
                                    loadFragment(FragmentProfileSignInUp.newInstance(),true);
                                }
                            }

                        }

                        @Override
                        public void onFailure(Call<ValidateResponse> call, Throwable t) {

                        }
                    });
                    */
                    loadFragment(FragmentProfile.newInstance(),true);
                }

                return true;
            }
        });
        return true;
    }


}

