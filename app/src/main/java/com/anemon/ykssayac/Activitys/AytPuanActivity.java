package com.anemon.ykssayac.Activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.text.Editable;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.anemon.ykssayac.Filters.MinMaxFilter;
import com.anemon.ykssayac.Filters.SizeOvered;
import com.anemon.ykssayac.Listeners.TextChangeListener;
import com.anemon.ykssayac.Models.AytModel;
import com.anemon.ykssayac.R;
import com.diegodobelo.expandingview.ExpandingItem;
import com.diegodobelo.expandingview.ExpandingList;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;


@SuppressLint("SetTextI18n")
public class AytPuanActivity extends AppCompatActivity {


    AytModel aytModel;
    ExpandingList expandingList;
    ExpandingItem sosbil1ExItem, sosbil2ExItem, matExItem, fenExItem, dilExItem;
    TextView sosbil1Name, sosbil2Name, matName, fenName, dilName,dipName;
    TextView sosbil1TN, sosbil2TN, matTN, fenTN, dilTN,dipTN;
    TextView tdeNtext, tar1Ntext, cog1Ntext, tar2Ntext, cog2Ntext, felNtext, dinNtext, matNtext, fizNtext, kimNtext, biyNtext, dilNtext;
    List<ExpandingItem> activeExpandItem;
    EditText tdeDedit, tdeYedit, tar1Dedit, tar1Yedit, cog1Dedit, cog1Yedit, tar2Dedit, tar2Yedit, cog2Dedit, cog2Yedit, felDedit, FelYedit,
            dinDedit, dinYedit, matDedit, matYedit, fizDedit, fizYedit, kimDedit, kimYedit, biyDedit, biyYedit, dilDedit, dilYedit,dipDedit;

    TextView eaText,sozelText,dilText,sayText,tytText;


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aytpuan);
        aytModel = new AytModel();
        Bundle gelenVeri = getIntent().getExtras();
        if (gelenVeri!=null) {
            double TYTham = gelenVeri.getDouble("TYTham", 0);
            double TYT = gelenVeri.getDouble("TYT", 0);
            double AYT = gelenVeri.getDouble("AYT", 0);
            float DIPLOMA = gelenVeri.getFloat("DIPLOMA", 0);
            if (TYTham < 150) {
                Toast.makeText(this, "TYT puanı baraj altında dikkat et.!!", Toast.LENGTH_LONG).show();
            }
            tytText=findViewById(R.id.tv_sonuctyt);
            if (TYTham<=100) tytText.setText("-----");
            else tytText.setText(String.format("%.2f", TYT));
            aytModel.setAYTpuan(AYT);
            aytModel.setDIPLOMAf(DIPLOMA);
        }

        init();


    }

    private void init() {
        expandingList = findViewById(R.id.expanding_list_ayt);

        sayText =findViewById(R.id.tv_sonucsayisal);
        eaText=findViewById(R.id.tv_sonucesit);
        sozelText=findViewById(R.id.tv_sonucsozel);
        dilText=findViewById(R.id.tv_sonucdil);
        activeExpandItem=new ArrayList<>();
        reklam();
        sosyalBilimler1();
        sosyalBilimler2();
        matematik();
        fenBilimleri();
        yabanciDil();
        diplamaPuani();
        filtre();
        addAllEditListener();

    }

    private void filtre() {
        tdeDedit.setFilters(new InputFilter[]{new SizeOvered(tdeDedit, tdeYedit, 24, this)});
        tdeYedit.setFilters(new InputFilter[]{new SizeOvered(tdeYedit, tdeDedit, 24, this)});
        tar1Dedit.setFilters(new InputFilter[]{new SizeOvered(tar1Dedit, tar1Yedit, 10, this)});
        tar1Yedit.setFilters(new InputFilter[]{new SizeOvered(tar1Yedit, tar1Dedit, 10, this)});
        cog1Dedit.setFilters(new InputFilter[]{new SizeOvered(cog1Dedit, cog1Yedit, 9, this)});
        cog1Yedit.setFilters(new InputFilter[]{new SizeOvered(cog1Yedit, cog1Dedit, 9, this)});
        tar2Dedit.setFilters(new InputFilter[]{new SizeOvered(tar2Dedit, tar2Yedit, 11, this)});
        tar2Yedit.setFilters(new InputFilter[]{new SizeOvered(tar2Yedit, tar2Dedit, 11, this)});
        cog2Dedit.setFilters(new InputFilter[]{new SizeOvered(cog2Dedit, cog2Yedit, 11, this)});
        cog2Yedit.setFilters(new InputFilter[]{new SizeOvered(cog2Yedit, cog2Dedit, 11, this)});
        felDedit.setFilters(new InputFilter[]{new SizeOvered(felDedit, FelYedit, 12, this)});
        FelYedit.setFilters(new InputFilter[]{new SizeOvered(FelYedit, tdeDedit, 12, this)});
        dinDedit.setFilters(new InputFilter[]{new SizeOvered(dinDedit, dinYedit, 6, this)});
        dinYedit.setFilters(new InputFilter[]{new SizeOvered(dinYedit, dinDedit, 6, this)});
        matDedit.setFilters(new InputFilter[]{new SizeOvered(matDedit, matYedit, 40, this)});
        matYedit.setFilters(new InputFilter[]{new SizeOvered(matYedit, matDedit, 40, this)});
        fizDedit.setFilters(new InputFilter[]{new SizeOvered(fizDedit, fizYedit, 14, this)});
        fizYedit.setFilters(new InputFilter[]{new SizeOvered(fizYedit, fizDedit, 14, this)});
        kimDedit.setFilters(new InputFilter[]{new SizeOvered(kimDedit, kimYedit, 13, this)});
        kimYedit.setFilters(new InputFilter[]{new SizeOvered(kimYedit, kimDedit, 13, this)});
        biyDedit.setFilters(new InputFilter[]{new SizeOvered(biyDedit, biyYedit, 13, this)});
        biyYedit.setFilters(new InputFilter[]{new SizeOvered(biyYedit, biyDedit, 13, this)});
        dilDedit.setFilters(new InputFilter[]{new SizeOvered(dilDedit, dilYedit, 80, this)});
        dilYedit.setFilters(new InputFilter[]{new SizeOvered(dilYedit, dilDedit, 80, this)});

    }

    private void controlToogle(ExpandingItem item) {
        if (activeExpandItem.size()>=1){
            activeExpandItem.get(0).toggleExpanded();
            activeExpandItem.clear();
        }
        activeExpandItem.add(item);
    }

    public void sosyalBilimler1() {
        sosbil1ExItem = expandingList.createNewItem(R.layout.ayt_expandig_layout);
        sosbil1ExItem.setStateChangedListener(expanded -> {
            if (expanded) {
               controlToogle(sosbil1ExItem);
               tdeDedit.requestFocus();
            }else activeExpandItem.remove(sosbil1ExItem);

        });


        sosbil1ExItem.setIndicatorColorRes(R.color.green); /// menu sol tarafı rengi
        sosbil1ExItem.setIndicatorIconRes(R.drawable.ayt_sosb_icon);  // menu sol taraf icon
        sosbil1Name = sosbil1ExItem.findViewById(R.id.bolum);
        sosbil1Name.setText("Sosyal Bilimler 1 ");
        sosbil1TN = sosbil1ExItem.findViewById(R.id.bolum_net);

        sosbil1ExItem.createSubItems(3);
        View TDEview = sosbil1ExItem.getSubItemView(0);
        ((TextView) TDEview.findViewById(R.id.ders)).setText("Türk Dili ve Edebiyatı");
        tdeDedit = TDEview.findViewById(R.id.dogru);
        tar1Yedit = TDEview.findViewById(R.id.yanlis);
        tdeNtext = TDEview.findViewById(R.id.net);

        View TAR1view = sosbil1ExItem.getSubItemView(1);
        ((TextView) TAR1view.findViewById(R.id.ders)).setText("Tarih 1");
        tar1Dedit = TAR1view.findViewById(R.id.dogru);
        tdeYedit = TAR1view.findViewById(R.id.yanlis);
        tar1Ntext = TAR1view.findViewById(R.id.net);

        View COG1view = sosbil1ExItem.getSubItemView(2);
        ((TextView) COG1view.findViewById(R.id.ders)).setText("Coğrafya 1");
        cog1Dedit = COG1view.findViewById(R.id.dogru);
        cog1Yedit = COG1view.findViewById(R.id.yanlis);
        cog1Ntext = COG1view.findViewById(R.id.net);
    }



    public void sosyalBilimler2() {
        sosbil2ExItem = expandingList.createNewItem(R.layout.ayt_expandig_layout);
        // sosbil2ExItem.setIndicatorColorRes(R.color.beyaz);
        sosbil2ExItem.setStateChangedListener(expanded -> {
            if (expanded) {
                controlToogle(sosbil2ExItem);
                tar2Dedit.requestFocus();
            }else activeExpandItem.remove(sosbil2ExItem);

        });

        sosbil2ExItem.setIndicatorColorRes(R.color.yellow); /// menu sol tarafı rengi
        sosbil2ExItem.setIndicatorIconRes(R.drawable.ayt_sosi_icon);  // menu sol taraf icon
        sosbil2Name = sosbil2ExItem.findViewById(R.id.bolum);
        sosbil2Name.setText("Sosyal Bilimler 2 ");
        sosbil2TN = sosbil2ExItem.findViewById(R.id.bolum_net);

        sosbil2ExItem.createSubItems(4);
        View TAR2view = sosbil2ExItem.getSubItemView(0);
        ((TextView) TAR2view.findViewById(R.id.ders)).setText("Tarih 2");
        tar2Dedit = TAR2view.findViewById(R.id.dogru);
        tar2Yedit = TAR2view.findViewById(R.id.yanlis);
        tar2Ntext = TAR2view.findViewById(R.id.net);

        View COG2view = sosbil2ExItem.getSubItemView(1);
        ((TextView) COG2view.findViewById(R.id.ders)).setText("Coğrafya 2");
        cog2Dedit = COG2view.findViewById(R.id.dogru);
        cog2Yedit = COG2view.findViewById(R.id.yanlis);
        cog2Ntext = COG2view.findViewById(R.id.net);

        View FELview = sosbil2ExItem.getSubItemView(2);
        ((TextView) FELview.findViewById(R.id.ders)).setText("Felsefe");
        felDedit = FELview.findViewById(R.id.dogru);
        FelYedit = FELview.findViewById(R.id.yanlis);
        felNtext = FELview.findViewById(R.id.net);

        View DINview = sosbil2ExItem.getSubItemView(3);
        ((TextView) DINview.findViewById(R.id.ders)).setText("Din Kültürü");
        dinDedit = DINview.findViewById(R.id.dogru);
        dinYedit = DINview.findViewById(R.id.yanlis);
        dinNtext = DINview.findViewById(R.id.net);

    }


    public void matematik() {
        matExItem = expandingList.createNewItem(R.layout.ayt_expandig_layout);
        matExItem.setStateChangedListener(expanded -> {
            if (expanded) {
                controlToogle(matExItem);
                matDedit.requestFocus();
            }else activeExpandItem.remove(matExItem);

        });
        matExItem.setIndicatorColorRes(R.color.purple);
        matExItem.setIndicatorIconRes(R.drawable.ayt_mat_icon);
        matName = matExItem.findViewById(R.id.bolum);
        matName.setText("Matematik ");
        matTN = matExItem.findViewById(R.id.bolum_net);

        matExItem.createSubItems(1);
        View matView = matExItem.getSubItemView(0);
        ((TextView) matView.findViewById(R.id.ders)).setText("Matematik");
        matDedit = matView.findViewById(R.id.dogru);
        matYedit = matView.findViewById(R.id.yanlis);
        matNtext = matView.findViewById(R.id.net);
    }


    public void fenBilimleri() {
        fenExItem = expandingList.createNewItem(R.layout.ayt_expandig_layout);
        fenExItem.setStateChangedListener(expanded -> {
            if (expanded) {
                controlToogle(fenExItem);
                fizDedit.requestFocus();
            }else activeExpandItem.remove(fenExItem);

        });
        fenExItem.setIndicatorColorRes(R.color.pink);
        fenExItem.setIndicatorIconRes(R.drawable.ayt_fen_icon);
        fenName = fenExItem.findViewById(R.id.bolum);
        fenName.setText("Fen Bilimleri");
        fenTN = fenExItem.findViewById(R.id.bolum_net);

        fenExItem.createSubItems(3);
        View FIZview = fenExItem.getSubItemView(0);
        ((TextView) FIZview.findViewById(R.id.ders)).setText("Fizik");
        fizDedit = FIZview.findViewById(R.id.dogru);
        fizYedit = FIZview.findViewById(R.id.yanlis);
        fizNtext = FIZview.findViewById(R.id.net);

        View KIMview = fenExItem.getSubItemView(1);
        ((TextView) KIMview.findViewById(R.id.ders)).setText("Kimya");
        kimDedit = KIMview.findViewById(R.id.dogru);
        kimYedit = KIMview.findViewById(R.id.yanlis);
        kimNtext = KIMview.findViewById(R.id.net);

        View BIYview = fenExItem.getSubItemView(2);
        ((TextView) BIYview.findViewById(R.id.ders)).setText("Biyoloji");
        biyDedit = BIYview.findViewById(R.id.dogru);
        biyYedit = BIYview.findViewById(R.id.yanlis);
        biyNtext = BIYview.findViewById(R.id.net);
    }


    public void yabanciDil() {
        dilExItem = expandingList.createNewItem(R.layout.ayt_expandig_layout);
        dilExItem.setStateChangedListener(expanded -> {
            if (expanded) {
                controlToogle(dilExItem);
                dilDedit.requestFocus();
            }else activeExpandItem.remove(dilExItem);

        });
        dilExItem.setIndicatorColorRes(R.color.orange);
        dilExItem.setIndicatorIconRes(R.drawable.ayt_dil_icon);
        dilName = dilExItem.findViewById(R.id.bolum);
        dilName.setText("Yabancı Dil ");
        dilTN = dilExItem.findViewById(R.id.bolum_net);

        dilExItem.createSubItems(1);
        View DILview = dilExItem.getSubItemView(0);
        ((TextView) DILview.findViewById(R.id.ders)).setText("Dil");
        dilDedit = DILview.findViewById(R.id.dogru);
        dilYedit = DILview.findViewById(R.id.yanlis);
        dilNtext = DILview.findViewById(R.id.net);

    }

    public void diplamaPuani() {
        ExpandingItem dipExItem = expandingList.createNewItem(R.layout.ayt_expandig_layout);
        dipExItem.setStateChangedListener(expanded -> {
            if (expanded) {
                controlToogle(dipExItem);
                dipDedit.requestFocus();
            } else activeExpandItem.remove(dipExItem);

        });
        dipExItem.setIndicatorColorRes(R.color.blue);
        dipExItem.setIndicatorIconRes(R.drawable.ic_diploma);
        dipName = dipExItem.findViewById(R.id.bolum);
        dipName.setText("Diploma Puanı ");
        dipTN = dipExItem.findViewById(R.id.bolum_net);
        dipTN.setText((int) aytModel.getDIPLOMAf() + " ");
        dipExItem.createSubItems(1);
        View DIPview = dipExItem.getSubItemView(0);
        ((TextView) DIPview.findViewById(R.id.ders)).setText("Diploma Notu");
        dipDedit = DIPview.findViewById(R.id.dogru);
        if (aytModel.getDIPLOMAf() > 0) dipDedit.setText((int) aytModel.getDIPLOMAf() + " ");

        dipDedit.setFilters(new InputFilter[]{new MinMaxFilter(0, 100, dipDedit)});
        DIPview.findViewById(R.id.yanlis).setVisibility(View.INVISIBLE);
        DIPview.findViewById(R.id.net).setVisibility(View.INVISIBLE);

    }
    public void addAllEditListener() {
        tdeDedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setTdeDf(Float.parseFloat(s.toString()));
                else aytModel.setTdeDf(0);
                puanhesapla();
            }
        });
        tdeYedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setTdeYf(Float.parseFloat(s.toString()));
                else aytModel.setTdeYf(0);
                puanhesapla();
            }
        });
        tar1Dedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setTar1Df(Float.parseFloat(s.toString()));
                else aytModel.setTar1Df(0);
                puanhesapla();
            }
        });
        tar1Yedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setTar1Yf(Float.parseFloat(s.toString()));
                else aytModel.setTar1Yf(0);
                puanhesapla();
            }
        });
        cog1Dedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setCog1Df(Float.parseFloat(s.toString()));
                else aytModel.setCog1Df(0);
                puanhesapla();
            }
        });
        cog1Yedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setCog1Yf(Float.parseFloat(s.toString()));
                else aytModel.setCog1Yf(0);
                puanhesapla();
            }
        });
        tar2Dedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setTar2Df(Float.parseFloat(s.toString()));
                else aytModel.setTar2Df(0);
                puanhesapla();
            }
        });
        tar2Yedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setTar2Yf(Float.parseFloat(s.toString()));
                else aytModel.setTar2Yf(0);
                puanhesapla();
            }
        });
        cog2Dedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setCog2Df(Float.parseFloat(s.toString()));
                else aytModel.setCog2Df(0);
                puanhesapla();
            }
        });
        cog2Yedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setCog2Yf(Float.parseFloat(s.toString()));
                else aytModel.setCog2Yf(0);
                puanhesapla();
            }
        });
        felDedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setFelDf(Float.parseFloat(s.toString()));
                else aytModel.setFelDf(0);
                puanhesapla();
            }
        });
        FelYedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                try {
                    if (s.length() > 0) aytModel.setFelYf(Float.parseFloat(s.toString()));
                    else aytModel.setFelYf(0);
                    puanhesapla();
                }catch (Exception e){
                    e.printStackTrace();
                    aytModel.setFelYf(0);
                }

            }
        });
        dinDedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setDinDf(Float.parseFloat(s.toString()));
                else aytModel.setDinDf(0);
                puanhesapla();
            }
        });
        dinYedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setDinYf(Float.parseFloat(s.toString()));
                else aytModel.setDinYf(0);
                puanhesapla();
            }
        });
        matDedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setMatDf(Float.parseFloat(s.toString()));
                else aytModel.setMatDf(0);
                puanhesapla();
            }
        });
        matYedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setMatYf(Float.parseFloat(s.toString()));
                else aytModel.setMatYf(0);
                puanhesapla();
            }
        });
        fizDedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setFizDf(Float.parseFloat(s.toString()));
                else aytModel.setFizDf(0);
                puanhesapla();
            }
        });
        fizYedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setFizYf(Float.parseFloat(s.toString()));
                else aytModel.setFizYf(0);
                puanhesapla();
            }
        });
        kimDedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setKimDf(Float.parseFloat(s.toString()));
                else aytModel.setKimDf(0);
                puanhesapla();
            }
        });
        kimYedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setKimYf(Float.parseFloat(s.toString()));
                else aytModel.setKimYf(0);
                puanhesapla();
            }
        });
        biyDedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setBiyDf(Float.parseFloat(s.toString()));
                else aytModel.setBiyDf(0);
                puanhesapla();
            }
        });
        biyYedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setBiyYf(Float.parseFloat(s.toString()));
                else aytModel.setBiyYf(0);
                puanhesapla();
            }
        });
        dilDedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setDilDf(Float.parseFloat(s.toString()));
                else aytModel.setDilDf(0);
                puanhesapla();
            }
        });
        dilYedit.addTextChangedListener(new TextChangeListener() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length() > 0) aytModel.setDilYf(Float.parseFloat(s.toString()));
                else aytModel.setTdeDf(0);
                puanhesapla();
            }
        });
        dipDedit.addTextChangedListener(new TextChangeListener(){
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                if (s.length()>0) {
                    aytModel.setDIPLOMAf(Float.parseFloat(s.toString()));
                    dipTN.setText(s+" ");
                }
                else aytModel.setDIPLOMAf(0);
                puanhesapla();
            }
        });

    }

    @SuppressLint("DefaultLocale")
    private void puanhesapla() {
        tdeNtext.setText(String.format("%.2f", aytModel.getTDEnet()));
        tar1Ntext.setText(String.format("%.2f", aytModel.getTAR1net()));
        cog1Ntext.setText(String.format("%.2f", aytModel.getCOG1net()));
        tar2Ntext.setText(String.format("%.2f", aytModel.getTAR2net()));
        cog2Ntext.setText(String.format("%.2f", aytModel.getCOG21net()));
        felNtext.setText(String.format("%.2f", aytModel.getFELnet()));
        dinNtext.setText(String.format("%.2f", aytModel.getDINnet()));
        matNtext.setText(String.format("%.2f", aytModel.getMATnet()));
        fizNtext.setText(String.format("%.2f", aytModel.getFIZnet()));
        kimNtext.setText(String.format("%.2f", aytModel.getKIMnet()));
        biyNtext.setText(String.format("%.2f", aytModel.getBIYnet()));
        dilNtext.setText(String.format("%.2f", aytModel.getDILnet()));

        sosbil1TN.setText("Net " + String.format("%3.2f", aytModel.getTAR1net() + aytModel.getCOG1net() + aytModel.getTDEnet()));
        sosbil2TN.setText("Net " + String.format("%3.2f", aytModel.getTAR2net() + aytModel.getCOG21net() + aytModel.getFELnet() + aytModel.getDINnet()));
        matTN.setText("Net " + String.format("%3.2f", aytModel.getMATnet()));
        fenTN.setText("Net " + String.format("%3.2f", aytModel.getFIZnet() + aytModel.getKIMnet() + aytModel.getBIYnet()));
        dilTN.setText("Net " + String.format("%3.2f", aytModel.getDILnet()));

        if (aytModel.getSAYpuan() > 100)
            sayText.setText(String.format("%.2f", aytModel.getSAYpuan()));
        else sayText.setText("-----");
        if (aytModel.getSOZpuan() > 100)
            sozelText.setText(String.format("%.2f", aytModel.getSOZpuan()));
        else sozelText.setText("-----");
        if (aytModel.getEApuan() > 100)
            eaText.setText(String.format("%.2f", aytModel.getEApuan()));
        else eaText.setText("-----");
        if (aytModel.getDILpuan() > 100)
            dilText.setText(String.format("%.2f", aytModel.getDILpuan()));
        else dilText.setText("-----");


    }
    public void reklam(){
        MobileAds.initialize(this, "ca-app-pub-5047443485754315~1658664749");
        AdView aytbanner = findViewById(R.id.banner_aytpuan);
        AdRequest adRequest = new AdRequest.Builder().build();
        aytbanner.loadAd(adRequest);
    }


}


