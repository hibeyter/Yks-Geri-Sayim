package com.anemon.ykssayac.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.anemon.ykssayac.R;


public class WelcomeAdapter extends PagerAdapter {


    Context context;
    LayoutInflater inflater;
    Button goOn;
    ImageView devam;


    public int[] lst_images = {
            R.drawable.image_1,
            R.drawable.image_2,
            R.drawable.image_3,
            R.drawable.image_4

    };
    // Başlıklar
    public String[] lst_title = {
            "YKS Tarih Kontrolü",
            "Puan Hesaplama",
            "Duyurular Bölümü",
            "Egzersiz Bölümü"
    };
    // Bilgilendirmeler
    public String[] lst_description = {
            "YKS sınavınız için zaman kontrolü yaparak düzenli şekilde ders çalışmanıza yardımcı olacağız! Harika motive sözler de seni bekliyor:)",
            "TYT ve AYT puanları doğru bir şekilde hesaplayabilir netlerinizi ve yerleştirme puanınızı görebilirsiniz.",
            "YKS sınavınıza dair son dakika haberler, duyurular, üniversiteler ve bölümler hakkında bilgi edinebilirsiniz.",
            "YKS derslerinizden sizin için özenle seçtiğimiz sorular ile kelime oyunu oynayarak kendinizi gelişitirebilir ve biraz ders molası verebilirsiniz ;)"
    };
    // Arkaplan renkleri
    private int[]  lst_backgroundcolor = {
            Color.rgb(255,255,255),
           /* Color.rgb(255,255,255),
            Color.rgb(255,255,255),
            Color.rgb(255,255,255),
            Color.rgb(255,255,255)*/
    };



    public WelcomeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return lst_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.welcome_screen, container, false);
        LinearLayout layoutslide = view.findViewById(R.id.linear_welcome_screen);
        ImageView imgslide = view.findViewById(R.id.slide_img);
        TextView txttitle = view.findViewById(R.id.txt_title);
        TextView description = view.findViewById(R.id.txt_bilgi);
        goOn = view.findViewById(R.id.btn_welcome);
        devam = view.findViewById(R.id.img_diger);
        layoutslide.setBackgroundColor(lst_backgroundcolor[0]);
        imgslide.setImageResource(lst_images[position]);
        txttitle.setText(lst_title[position]);
        description.setText(lst_description[position]);
        if (position + 1 == lst_title.length) {
            goOn.setVisibility(View.VISIBLE);
            devam.setVisibility(View.GONE);
        } else {
            goOn.setVisibility(View.GONE);
            devam.setVisibility(View.VISIBLE);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
