package com.anemon.ykssayac.Activitys;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.view.animation.AlphaAnimation;

import android.widget.TextView;


import com.anemon.ykssayac.Adapters.WelcomeAdapter;
import com.anemon.ykssayac.Database.FirebaseDatabase;
import com.anemon.ykssayac.Database.LocalDatabase;
import com.anemon.ykssayac.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;


import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "Beyter ";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT_WELCOME = "welcome";
    public static final String TEXT_DATE = "date";
    SharedPreferences sharedPreferences ;
    SharedPreferences.Editor editor ;
    private Handler handler;
    private LocalDatabase localDb;
    private FirebaseDatabase firebaseDb;
    private ViewPager viewPager;
    private WelcomeAdapter welcomeAdapter;
    private AlphaAnimation clickAnimation = new AlphaAnimation(1F, 0.4F);

    @BindView(R.id.gun)  TextView gun;
    @BindView(R.id.saat)  TextView saat;
    @BindView(R.id.dakika)  TextView dakika;
    @BindView(R.id.saniye)  TextView saniye;
    @BindView(R.id.sozler)  TextView sozler;

    @OnClick(R.id.btduyurular)
    void goDuyurular(View view){
        view.startAnimation(clickAnimation);
        Intent ax = new Intent(MainActivity.this, DuyurularActivity.class);
        startActivity(ax);
    }

    @OnClick(R.id.btpuan)
    void goTYTpuan(View view){
        view.startAnimation(clickAnimation);
        Intent ax = new Intent(MainActivity.this, TytPuanActivity.class);
        startActivity(ax);

    }

    @OnClick(R.id.bthakkinda)
    void goDersler(View view){
        view.startAnimation(clickAnimation);
        Intent ax = new Intent(MainActivity.this, DerslerActivity.class);
        startActivity(ax);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        clickAnimation.setDuration(100);
        init();
        countDownStart();
    }

    @SuppressLint("CommitPrefEdits")
    private void init() {
        localDb = new LocalDatabase(this.getApplicationContext());
        firebaseDb = new FirebaseDatabase(this.getApplicationContext());
        databaseControl();
        MobileAds.initialize(this.getApplicationContext(), getResources().getString(R.string.addMobKey));
        AdView banner = findViewById(R.id.banner_main);
        AdRequest adRequest = new AdRequest.Builder().build();
        banner.loadAd(adRequest);
        sharedPreferences= getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        viewPager=findViewById(R.id.welcome_pager);
        welcomeAdapter=new WelcomeAdapter(this);
        sozler.setText(localDb.getRandomSoz().getSoz());
        welcomeScreenControl();
    }

    private void welcomeScreenControl() {
        boolean slideOpen= sharedPreferences.getBoolean(TEXT_WELCOME,true);
        if (slideOpen){
            viewPager.setVisibility(View.VISIBLE);
            viewPager.setAdapter(welcomeAdapter);
        }
    }

    public void welcomeBtn(View v){
        editor.putBoolean(TEXT_WELCOME,false);
        editor.apply();
        viewPager.setVisibility(View.INVISIBLE);
    }

    public  void nextSlide(View v){
        v.startAnimation(clickAnimation);
        viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
    }
    private void databaseControl() {
        firebaseDb.duyuruControl(localDb.getCountDuyuru());
        firebaseDb.sozControl(localDb.getCountSoz());
        firebaseDb.dersControl(localDb.getCountDers());
        firebaseDb.getDate();
    }

    @SuppressLint("SimpleDateFormat")
    public void countDownStart() {
        final String sinavGunu  = sharedPreferences.getString (TEXT_DATE,"2020-06-27 10:00");
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        handler = new Handler();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                handler.postDelayed(this, 1000);
                try {
                    Date futureDate = dateFormat.parse(sinavGunu);
                    Date currentDate = new Date();
                    if (!currentDate.after(futureDate)) {
                        long diff = futureDate.getTime()
                                - currentDate.getTime();
                        long days = diff / (24 * 60 * 60 * 1000);
                        diff -= days * (24 * 60 * 60 * 1000);
                        long hours = diff / (60 * 60 * 1000);
                        diff -= hours * (60 * 60 * 1000);
                        long minutes = diff / (60 * 1000);
                        diff -= minutes * (60 * 1000);
                        long seconds = diff / 1000;
                        gun.setText("" + String.format("%02d", days));
                        saat.setText("" + String.format("%02d", hours));
                        dakika.setText("" + String.format("%02d", minutes));
                        saniye.setText("" + String.format("%02d", seconds));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        handler.postDelayed(runnable, 1000);
    }


}

