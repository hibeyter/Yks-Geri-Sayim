package com.anemon.ykssayac.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class Soru {
    private int id;
    private String soru;
    private String cevap;
    private boolean cozulduMu;
    private Bitmap image;
    private byte[] imageByte;

    public Soru() {
    }

    public Soru(int id, String soru, String cevap, boolean cozulduMu) {
        this.id = id;
        this.soru = soru;
        this.cevap = cevap;
        this.cozulduMu = cozulduMu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoru() {
        return soru;
    }

    public void setSoru(String soru) {
        this.soru = soru;
    }

    public String getCevap() {
        return cevap;
    }

    public void setCevap(String cevap) {
        this.cevap = cevap;
    }

    public boolean isCozulduMu() {
        return cozulduMu;
    }

    public void setCozulduMu(boolean cozulduMu) {
        this.cozulduMu = cozulduMu;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        if (image!=null){
            this.image = image;
            this.imageByte=bitmapConvertByte(image);
        }else{
            System.out.println("nullImage");
        }
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
        this.image=byteConverBitmap(imageByte);
    }



    private byte[] bitmapConvertByte(Bitmap image) {
        if (image!=null){
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
            return outputStream.toByteArray();
        }else {
            System.out.println("beyter boş");
            return null;
        }

    }
    private Bitmap byteConverBitmap(byte[] imgByte){
        return  BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
    }

}
