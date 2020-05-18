package com.anemon.ykssayac.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import java.io.ByteArrayOutputStream;

public class Duyuru {
    private int id;
    private Bitmap image;
    private byte[] imageByte;
    private String title;
    private String content;
    private String url;
    public Duyuru() {
    }

    public byte[] getImageByte() {
        return imageByte;
    }

    public void setImageByte(byte[] imageByte) {
        this.imageByte = imageByte;
        this.image=byteConverBitmap(imageByte);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        if (imgByte!=null){
            return  BitmapFactory.decodeByteArray(imgByte, 0, imgByte.length);
        }return null;

    }



}
