package com.anemon.ykssayac.Activitys;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.anemon.ykssayac.Filters.MinMaxFilter;
import com.anemon.ykssayac.Filters.SizeOvered;
import com.anemon.ykssayac.Models.Puantutucu;
import com.anemon.ykssayac.Models.TytModel;
import com.anemon.ykssayac.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnTextChanged;


@SuppressLint({"DefaultLocale", "SetTextI18n"})
public class TytPuanActivity extends AppCompatActivity {

    TytModel tytModel;
    @BindView(R.id.tdogru) EditText turkcedogru;
    @BindView(R.id.tyanlis)  EditText turYanlis;
    @OnTextChanged(value = {R.id.tdogru,R.id.tyanlis}, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void turkceChanged() {
        String TDdata = turkcedogru.getText().toString();
        String TYdata = turYanlis.getText().toString();
        if (TDdata.length() > 0)  tytModel.setTurkDf(Float.parseFloat(TDdata));  else tytModel.setTurkDf(0);
        if (TYdata.length() > 0)  tytModel.setTurkYf(Float.parseFloat(TYdata));  else tytModel.setTurkYf(0);
        netHesapla();
    }
    @BindView(R.id.mtdogru)  EditText matdogru;
    @BindView(R.id.mtyanlis)  EditText matyanlis;
    @OnTextChanged(value = {R.id.mtdogru,R.id.mtyanlis}, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void matematikChanged() {
        String MDdata = matdogru.getText().toString();
        String MYdata = matyanlis.getText().toString();
        if (MDdata.length() > 0)  tytModel.setMatDf(Float.parseFloat(MDdata));  else tytModel.setMatDf(0);
        if (MYdata.length() > 0)  tytModel.setMatYf(Float.parseFloat(MYdata));  else tytModel.setMatYf(0);
        netHesapla();
    }
    @BindView(R.id.fendogru) EditText fendogru;
    @BindView(R.id.fenyanlis)  EditText fenyanlis;
    @OnTextChanged(value = {R.id.fendogru,R.id.fenyanlis}, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void fenChanged() {
        String FDdata = fendogru.getText().toString();
        String FYdata = fenyanlis.getText().toString();
        if (FDdata.length() > 0) tytModel.setFenDf(Float.parseFloat(FDdata));  else tytModel.setFenDf(0);
        if (FYdata.length() > 0) tytModel.setFenYf(Float.parseFloat(FYdata));  else tytModel.setFenYf(0);
        netHesapla();
    }
    @BindView(R.id.sosyaldogru) EditText sosyaldogru;
    @BindView(R.id.sosyalyanlis)  EditText sosyalyanlis;
    @OnTextChanged(value = {R.id.sosyaldogru,R.id.sosyalyanlis}, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void sosyalChanged() {
        String SDdata = sosyaldogru.getText().toString();
        String SYdata = sosyalyanlis.getText().toString();
        if (SDdata.length() > 0) tytModel.setSosDf(Float.parseFloat(SDdata));  else tytModel.setSosDf(0);
        if (SYdata.length() > 0) tytModel.setSosYf(Float.parseFloat(SYdata));  else tytModel.setSosYf(0);
        netHesapla();
    }

    @BindView(R.id.diploma)  EditText diploma;
    @OnTextChanged(value = R.id.diploma, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void diplomaChanged(CharSequence text) {
        if (text.length()>0) tytModel.setDipFloat(Float.parseFloat(text.toString()));else tytModel.setDipFloat(0);
        puanHesapla();
    }

    @BindView(R.id.kont)  CheckBox kontrol;
    @OnCheckedChanged(R.id.kont)
    public void checkedChanged() {
        tytModel.setDiplomaCheck(!tytModel.isDiplomaCheck());
        puanHesapla();
    }

    @BindView(R.id.aythesapla)  LinearLayout aythesap;

    @BindView(R.id.fennet)  TextView fennet;
    @BindView(R.id.matnet)  TextView matnet;
    @BindView(R.id.trnet)  TextView trnet;
    @BindView(R.id.sosyalnet)  TextView sosyalnet;
    @BindView(R.id.sonuc)  TextView sonuc;

    public void netHesapla(){
        trnet.setText(String.valueOf(tytModel.getTnet()));
        matnet.setText(String.valueOf(tytModel.getMnet()));
        fennet.setText(String.valueOf(tytModel.getFnet()));
        sosyalnet.setText(String.valueOf(tytModel.getSnet()));
        puanHesapla();
    }

    public void puanHesapla(){
        sonuc.setText(String.format("%.3f",tytModel.getTYTpuan())+"");
        aytp = tytModel.getAYTpuan();
    }

    public void  filter() {
        turkcedogru.setFilters(new InputFilter[]{new SizeOvered(turkcedogru, turYanlis, 40, this)});
        turYanlis.setFilters(new InputFilter[]{new SizeOvered(turYanlis, turkcedogru, 40, this)});
        matdogru.setFilters(new InputFilter[]{new SizeOvered(matdogru, matyanlis, 40, this)});
        matyanlis.setFilters(new InputFilter[]{new SizeOvered(matyanlis, turkcedogru, 40, this)});
        fendogru.setFilters(new InputFilter[]{new SizeOvered(fendogru, fenyanlis, 40, this)});
        fenyanlis.setFilters(new InputFilter[]{new SizeOvered(fenyanlis, turkcedogru, 40, this)});
        sosyaldogru.setFilters(new InputFilter[]{new SizeOvered(sosyaldogru, sosyalyanlis, 40, this)});
        sosyalyanlis.setFilters(new InputFilter[]{new SizeOvered(sosyalyanlis, sosyaldogru, 40, this)});
        diploma.setFilters(new InputFilter[]{new MinMaxFilter(0, 100, diploma)});
    }


    //private InterstitialAd insterstitialAd;
    double aytp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puan);
        ButterKnife.bind(this);
        filter();
        tytModel=new TytModel();

        MobileAds.initialize(this, "ca-app-pub-5047443485754315~1658664749");
        AdView tytbanner = findViewById(R.id.tytbanner);
        AdRequest adRequest = new AdRequest.Builder().build();
        tytbanner.loadAd(adRequest);





     /*  insterstitialAd = new InterstitialAd(this);
        insterstitialAd.setAdUnitId("ca-app-pub-5047443485754315/7450047478");
        insterstitialAd.loadAd(new AdRequest.Builder().build());*/

        aythesap.setOnClickListener(v -> {
            /*if (insterstitialAd.isLoaded()) {
                insterstitialAd.show();
            }*/
            Intent intent = new Intent(TytPuanActivity.this, AytPuanActivity.class);
            intent.putExtra("TYTham",tytModel.getHam());
            intent.putExtra("TYT",tytModel.getTYTpuan());
            intent.putExtra("AYT",tytModel.getAYTpuan());
            intent.putExtra("DIPLOMA",tytModel.getDipFloat());
            startActivity(intent);
            overridePendingTransition(R.anim.fleft, R.anim.fhelper);
        });
    }


}
