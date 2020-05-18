package com.anemon.ykssayac.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class Ders {
    private  int id;
    private  String dersAdi;
    private Bitmap image;
    private byte[] imageByte;

    public Ders() {
    }

    public Ders(int id, String dersAdi) {
        this.id = id;
        this.dersAdi = dersAdi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDersAdi() {
        return dersAdi;
    }

    public void setDersAdi(String dersAdi) {
        this.dersAdi = dersAdi;
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
        if (imageByte!=null){
            return  BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        }return null;

    }

}
