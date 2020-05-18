package com.anemon.ykssayac.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;

import com.anemon.ykssayac.Adapters.DersAdapter;
import com.anemon.ykssayac.Database.LocalDatabase;
import com.anemon.ykssayac.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class DerslerActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LocalDatabase localDb;
    DersAdapter dersAdapter;
    private AlphaAnimation clickAnimation = new AlphaAnimation(1F, 0.4F);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dersler);
        clickAnimation.setDuration(200);
        init();


        dersAdapter = new DersAdapter(localDb,this.getApplicationContext());
        recyclerView.setAdapter(dersAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected void onResume() {
        super.onResume();
        dersAdapter.notifyDataSetChanged();
    }

    private void init() {
        recyclerView = findViewById(R.id.rcv_dersler);
        localDb = new LocalDatabase(this.getApplicationContext());
        MobileAds.initialize(this.getApplicationContext(), getResources().getString(R.string.addMobKey));
        AdView banner = findViewById(R.id.banner_egzersizDersler);
        AdRequest adRequest = new AdRequest.Builder().build();
        banner.loadAd(adRequest);
    }
    public void backImg(View view){
        view.startAnimation(clickAnimation);
        Intent intent = new Intent(DerslerActivity.this,MainActivity.class);
        startActivity(intent);
    }
}
