package com.anemon.ykssayac.Database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import com.anemon.ykssayac.Models.Duyuru;
import java.io.IOException;
import java.io.InputStream;

public class doInBackgroundDuyuru extends AsyncTask<String, Void, Bitmap> {

    Duyuru duyuru;
    Bitmap bitmap;
    LocalDatabase localDb;

    public doInBackgroundDuyuru(Duyuru duyuru, LocalDatabase localDb) {
        this.duyuru = duyuru;
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
        duyuru.setImage(bitmap);
        localDb.addDuyuru(duyuru);
    }


}

