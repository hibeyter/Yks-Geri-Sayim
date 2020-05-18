package com.anemon.ykssayac.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.anemon.ykssayac.Database.LocalDatabase;
import com.anemon.ykssayac.Listeners.AnimationListener;
import com.anemon.ykssayac.Models.Soru;
import com.anemon.ykssayac.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EgzersizActivity extends AppCompatActivity {

    private static final String TAG ="Beyter " ;
    private InterstitialAd interstitialAd;
    private AdView banner;
    LocalDatabase localDb;
    Soru nowQuestion;
    TextView txtDersAdi, txtSoru, txtCevap;
    LinearLayout linearLayout, btnVideo, linearLayout2;
    Animation smallbigforth;
    ImageView imgSoru;
    int size = 0, maxSize = 0, dersId;
    private String ALFABE = "abcçdefgğhıijklmnoöprsştuüvyz";
    private AlphaAnimation clickAnimation = new AlphaAnimation(1F, 0.4F);
    LottieAnimationView succsesAnimation;
    Random random;
    boolean flagLayout=true;
    List<TextView> tiklanilanHarfler;
    List<TextView> allCharTextV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egzersiz);
        init();
        clickAnimation.setDuration(200);
        btnVideo.setOnClickListener(view -> {
            view.startAnimation(clickAnimation);
            if (interstitialAd.isLoaded()) {
                interstitialAd.show();
                reklamTakip();
            }else{
                Toast.makeText(this, "Reklam yok yardım edemem O_o", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        localDb = new LocalDatabase(this.getApplicationContext());
        txtDersAdi = findViewById(R.id.tv_dersadi);
        txtSoru = findViewById(R.id.tv_soru);
        linearLayout = findViewById(R.id.ln_harflik);
        linearLayout2 = findViewById(R.id.ln_harflik2);
        txtCevap = findViewById(R.id.tv_cevap);
        imgSoru = findViewById(R.id.ders_mini_img);
        btnVideo = findViewById(R.id.btn_ly_izle);
        banner = findViewById(R.id.banner_egzersiz);
        succsesAnimation=findViewById(R.id.succses_animation);
        succsesAnimation.addAnimatorListener(new AnimationListener(){
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                succsesAnimation.setVisibility(View.GONE);
                refreshNewScreen();
            }
        });

        random = new Random();
        MobileAds.initialize(this, "ca-app-pub-5047443485754315~1658664749");
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-5047443485754315/2720464970");
        interstitialAd.loadAd(new AdRequest.Builder().build());
        AdRequest adRequest = new AdRequest.Builder().build();
        banner.loadAd(adRequest);

        tiklanilanHarfler = new ArrayList<>();
        allCharTextV=new ArrayList<>();
        smallbigforth = AnimationUtils.loadAnimation(this, R.anim.smalltobig);
        Bundle gelenVeri = getIntent().getExtras();
        assert gelenVeri != null;  dersId = gelenVeri.getInt("id", 0);
        txtDersAdi.setText(gelenVeri.getString("dersAdi", "Genel :)"));
        txtCevap.setText("");
        nowQuestion = localDb.getRandomsoru(dersId);
        txtSoru.setText(nowQuestion.getSoru());
        maxSize = nowQuestion.getCevap().length();
        imgSoru.setImageBitmap(localDb.getDers(dersId).getImage());
        createScreen();
    }

    public void reklamTakip() {
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                interstitialAd.loadAd(new AdRequest.Builder().build());
                helperAlgorithm();
            }
        });
    }

    private void helperAlgorithm() {
        if (allCharTextV.size() > 0) {
            String cevap = nowQuestion.getCevap().toLowerCase();
            for (int i = 0; i < cevap.length(); i++) {
                String harf = cevap.charAt(i) + "";
                for (int j = 0; j < allCharTextV.size(); j++) {
                    String controlHarf = allCharTextV.get(j).getText() + "";
                    if (harf.equals(controlHarf)) {
                        allCharTextV.remove(j);
                        break;
                    }
                }
            }
            for (TextView t : allCharTextV) {
                t.animate().alpha(0).setDuration(400);
                t.setVisibility(View.INVISIBLE);
            }
        }else {
            Toast.makeText(getApplicationContext(),"Daha önce yardım aldın",Toast.LENGTH_SHORT).show();
        }
    }


    private void createScreen() {
        linearLayout.removeAllViews();
        linearLayout2.removeAllViews();
        txtCevap.setText("");
        size = 0;
        tiklanilanHarfler=new ArrayList<>();
        allCharTextV=new ArrayList<>();
        String word = nowQuestion.getCevap();
        while (word.length() < 14) {
            word += getRandomChar();
        }
        word = shuffle(word).toLowerCase();
        for (int i = 0; i < word.length(); i++) {
            createText(word.charAt(i));
        }
    }

    private String getRandomChar() {
        return ALFABE.charAt(random.nextInt(ALFABE.length())) + "";
    }


    @SuppressLint("SetTextI18n")
    private void createText(final char character) {
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        linearLayoutParams.rightMargin = 18;
        final TextView textView = new TextView(this.getApplicationContext());
        textView.setLayoutParams(linearLayoutParams);
        textView.setBackground(this.getResources().getDrawable(R.drawable.bgpink));
        textView.setTextColor(this.getResources().getColor(R.color.purple));
        textView.setGravity(Gravity.CENTER);
        textView.setText(character + "");
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTextSize(16 * getResources().getDisplayMetrics().density);
        textView.setPadding(15, 0, 15, 10);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/titan_one.ttf");
        textView.setTypeface(typeface);
        allCharTextV.add(textView);
        textView.setOnClickListener(v -> {
            if (size < maxSize) {
                tiklanilanHarfler.add(textView);
                txtCevap.setText(txtCevap.getText() + String.valueOf(character));
                textView.startAnimation(smallbigforth);
                textView.animate().alpha(0).setDuration(400);
                textView.setVisibility(View.INVISIBLE);
                size++;
                if (size == maxSize) control();
            }
        });
        if (flagLayout) linearLayout.addView(textView);
        else linearLayout2.addView(textView);
        flagLayout = !flagLayout;

    }

    private void control() {
        if (nowQuestion.getCevap().toLowerCase().equals(txtCevap.getText().toString())) {
            Toast.makeText(this, "Doğru Cevap :)", Toast.LENGTH_SHORT).show();
            nowQuestion.setCozulduMu(true);
            localDb.updateSoru(nowQuestion);
            succsesAnimation.setVisibility(View.VISIBLE);
            succsesAnimation.playAnimation();
        } else {
            Toast.makeText(this, ":( Yardım..lazım mı?", Toast.LENGTH_SHORT).show();
            refreshScreen();
        }

    }

    private void refreshScreen() {
        createScreen();
    }

    private void refreshNewScreen() {
        nowQuestion = localDb.getRandomsoru(dersId);
        txtSoru.setText(nowQuestion.getSoru());
        maxSize = nowQuestion.getCevap().length();
        imgSoru.setImageBitmap(localDb.getDers(dersId).getImage());
        createScreen();

    }

    private String shuffle(String harfler) {
        char[] temp = harfler.toCharArray();
        Random random = new Random();
        String data = "";
        List<Character> characterList = new ArrayList<>();
        for (char c : temp) characterList.add(c);
        int size = characterList.size();
        for (int i = 0; i < size; i++) {
            int k = random.nextInt(characterList.size());
            data += characterList.get(k);
            characterList.remove(k);
        }
        return data;
    }

    public void refresh(View view) {
        view.startAnimation(clickAnimation);
        refreshNewScreen();
    }

    public void delete(View view) {
        String word = txtCevap.getText().toString();
        if (word.length() > 0) {
            view.startAnimation(clickAnimation);
            txtCevap.setText(word.substring(0, word.length() - 1));
            size--;
            tiklanilanHarfler.get(tiklanilanHarfler.size()-1).setVisibility(View.VISIBLE);
            tiklanilanHarfler.get(tiklanilanHarfler.size()-1).animate().alpha(1).setDuration(400);
            tiklanilanHarfler.remove(tiklanilanHarfler.size()-1);
        }else{
            Toast.makeText(this, "Hatanı düzeltmek için hata yapmalısın (+_+)", Toast.LENGTH_SHORT).show();
        }
    }

}
