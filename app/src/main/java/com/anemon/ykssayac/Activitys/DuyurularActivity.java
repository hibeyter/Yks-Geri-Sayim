package com.anemon.ykssayac.Activitys;


import android.os.Bundle;

import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.anemon.ykssayac.Adapters.DuyuruAdapter;
import com.anemon.ykssayac.Database.LocalDatabase;

import com.anemon.ykssayac.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;



public class DuyurularActivity extends AppCompatActivity {

    private AdView bannerCalisma;
    private RewardedVideoAd rewardedVideoAd;
    LocalDatabase localDb;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calisma);
        init();

        DuyuruAdapter duyuruAdapter = new DuyuruAdapter(this.getApplicationContext() , localDb.getAllDuyuru()) ;
        recyclerView.setAdapter(duyuruAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        //region odul reklam

      /*  rewardedVideoAd=MobileAds.getRewardedVideoAdInstance(this);
        rewardedVideoAd.loadAd("ca-app-pub-6103319033249647/3763033651",new AdRequest.Builder().build());



        rewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                rewardedVideoAd.show();
            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {

            }

            @Override
            public void onRewarded(RewardItem rewardItem) {

            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }

            @Override
            public void onRewardedVideoCompleted() {

            }
        });*/
//endregion

}

    private void init() {
        localDb = new LocalDatabase(this.getApplicationContext());
        recyclerView = findViewById(R.id.duyuru_recycler);
        bannerCalisma=findViewById(R.id.banner_calisma);
        MobileAds.initialize(this,"ca-app-pub-5047443485754315~1658664749");
        AdRequest adRequest=new AdRequest.Builder().build();
        bannerCalisma.loadAd(adRequest);



    }
}
