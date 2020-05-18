package com.anemon.ykssayac.Database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.anemon.ykssayac.Models.Ders;

import java.io.IOException;
import java.io.InputStream;

public class doInBackgroundDers extends AsyncTask<String, Void, Bitmap> {

    Bitmap bitmap;
    Ders ders;
    LocalDatabase localDb;

    public doInBackgroundDers(Ders ders, LocalDatabase localDb) {
        this.ders = ders;
        this.localDb = localDb;
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
        ders.setImage(bitmap);
        localDb.addDers(ders);
    }
}
