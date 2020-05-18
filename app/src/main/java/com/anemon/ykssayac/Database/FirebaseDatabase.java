package com.anemon.ykssayac.Database;

import com.anemon.ykssayac.Models.Ders;
import com.anemon.ykssayac.Models.Duyuru;
import com.anemon.ykssayac.Models.Soru;
import com.anemon.ykssayac.Models.Soz;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;


import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.NonNull;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FirebaseDatabase {
    private static final String TAG = "Beyter ";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT_WELCOME = "welcome";
    public static final String TEXT_DATE = "date";
    private FirebaseFirestore db;
    private CollectionReference duyurularReference;
    private CollectionReference sozlerReference;
    private CollectionReference derslerReference;

    private DocumentReference countReferenceDuyuru;
    private DocumentReference countReferenceSoz;
    private DocumentReference countReferenceEgzersiz;
    private DocumentReference tarihReference;

    private Context context;
    private LocalDatabase localDb;


    public FirebaseDatabase(Context context) {
        this.context = context;
        db = FirebaseFirestore.getInstance();
        localDb = new LocalDatabase(context);


        countReferenceDuyuru = db.collection("Counts").document("Duyurular");
        countReferenceSoz = db.collection("Counts").document("Sozler");
        countReferenceEgzersiz =db.collection("Counts").document("Egzersizler");
        tarihReference=db.collection("Counts").document("Tarih");

        duyurularReference = db.collection("Duyurular");
        derslerReference = db.collection("Egzersizler");
        sozlerReference = db.collection(("Sozler"));
    }



    public void duyuruControl(final int count) {
        countReferenceDuyuru.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                assert document != null;
                if (document.exists()) {
                    if (document.getData().get("count") != null) {
                        int duyuruCount = Integer.parseInt(String.valueOf(document.getData().get("count")));
                        if (duyuruCount != count) firebaseAddLocalDuyurular();
                        else {
                            for (Duyuru duyuru : localDb.getAllDuyuru()) {
                                if (duyuru.getImage() == null) {
                                    firebaseAddLocalDuyurular();
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        });
    }
    public void sozControl(final int count){
        countReferenceSoz.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DocumentSnapshot document = task.getResult();
                if (document.exists()) {
                    if (document.getData().get("count") != null) {
                        int duyuruCount = Integer.parseInt(String.valueOf(document.getData().get("count")));
                        if (duyuruCount != count) firebaseAddLocalSozler();
                    }
                }
            }
        });
    }
    public void dersControl(final int count){
        countReferenceEgzersiz.get().addOnCompleteListener(task -> {
                if (task.isSuccessful()){
                    DocumentSnapshot document= task.getResult();
                    if (document.exists()){
                        if (document.getData().get("dersler")!=null){
                            List<Map<String,Object>> data = (List<Map<String, Object>>) document.getData().get("dersler");
                            if (count!=data.size()) firebaseAddLocalDersler();
                            for (Map<String,Object> map : data){
                                int size = Integer.parseInt(String.valueOf(map.get("count")));
                                int id = Integer.parseInt(String.valueOf(map.get("id")));
                                int locaSize =localDb.getCountDersSoru(id);
                                if (size!= locaSize) firebaseAddLocalSorular(id,locaSize);
                            }
                        }
                    }
                }
        });
    }

    public void getDate(){
        SharedPreferences preferences = context.getSharedPreferences(SHARED_PREFS,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor  = preferences.edit();
        tarihReference.get().addOnCompleteListener(task -> {
            try {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    assert document != null;
                    if (document.exists()) if (document.getData().get("date") != null) {
                        Log.d(TAG,document.getData().get("date") + "");
                        editor.putString("date", document.getData().get("date") + "");
                        editor.apply();
                    }
                }
            }catch (Exception e){
                Log.d(TAG, Objects.requireNonNull(e.getMessage()));
            }

        });
    }

    private void firebaseAddLocalSorular(int id, final int localSize) {
        Query query = derslerReference.whereEqualTo("id", id);
        query.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult() != null) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        List<Map<String, Object>> mapList = (List<Map<String, Object>>) document.getData().get("sorular");
                        int id1 = Integer.parseInt(String.valueOf(document.getData().get("id")));
                        localDb.deleteSorular(id1);
                        assert mapList != null;
                        for (int i = 0; i < mapList.size(); i++) {
                            Soru data = new Soru();
                            data.setId(id1);
                            data.setCozulduMu(false);
                            data.setSoru((String) mapList.get(i).get("soru"));
                            data.setCevap((String) mapList.get(i).get("cevap"));
                            String imgUrl = String.valueOf(mapList.get(i).get("imageUrl"));
                            if (imgUrl.length() > 8) {
                                new doInBackgroundSoru(data, localDb).execute(String.valueOf(mapList.get(i).get("imageUrl")));
                            } else localDb.addSoru(data);
                        }
                    }
                }
            }
        });
    }
    private void firebaseAddLocalDersler() {
        derslerReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                if (task.getResult()!=null){
                    localDb.deleteDersler();
                    for(QueryDocumentSnapshot document: task.getResult()){
                        Ders data = new Ders();
                        data.setDersAdi((String) document.getData().get("dersAdi"));
                        data.setId(Integer.parseInt(String.valueOf(document.getData().get("id"))));
                        new doInBackgroundDers(data,localDb).execute((String) document.getData().get("imageUrl"));
                    }
                }
            }
        });
    }
    private void firebaseAddLocalSozler() {
        sozlerReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult() != null) {
                    localDb.deleteSozler();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Soz data = new Soz();
                        data.setId(Integer.parseInt(String.valueOf(document.getData().get("id"))));
                        data.setSoz((String) document.getData().get("soz"));
                        localDb.addSoz(data);
                    }
                }
            }
        }).addOnFailureListener(e -> Log.d(TAG, Objects.requireNonNull(e.getMessage())));
    }
    private void firebaseAddLocalDuyurular() {
        duyurularReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                if (task.getResult() != null) {
                    localDb.deleteDuyurular();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Duyuru data = new Duyuru();
                        data.setId(Integer.parseInt(String.valueOf(document.getData().get("id"))));
                        data.setContent((String) document.getData().get("content"));
                        data.setTitle((String) document.getData().get("title"));
                        data.setUrl((String) document.getData().get("url"));
                        new doInBackgroundDuyuru(data, localDb).execute((String) document.getData().get("imageUrl"));
                    }
                }
            }
        });
    }
}
