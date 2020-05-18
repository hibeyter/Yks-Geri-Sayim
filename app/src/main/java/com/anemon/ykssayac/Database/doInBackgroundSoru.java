package com.anemon.ykssayac.Database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.anemon.ykssayac.Models.Soru;

import java.io.IOException;
import java.io.InputStream;

public class doInBackgroundSoru  extends AsyncTask<String, Void, Bitmap>{

    LocalDatabase localDb;
    Soru soru;
    Bitmap bitmap;
    public doInBackgroundSoru( Soru soru, LocalDatabase localDb) {
        this.localDb = localDb;
        this.soru = soru;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {
        String stringUrl = strings[0];
        bitmap = null;
        InputStream inputStream;
        try {
            inputStream = new java.net.URL(stringUrl).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        soru.setImage(bitmap);
        localDb.addSoru(soru);
    }
}
