package com.anemon.ykssayac.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.anemon.ykssayac.Models.Ders;
import com.anemon.ykssayac.Models.Duyuru;
import com.anemon.ykssayac.Models.Soru;
import com.anemon.ykssayac.Models.Soz;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LocalDatabase extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "YksDB";
    private static final String TABLE_NAME_DUYURULAR = "Duyurular";
    private static final String TABLE_NAME_SOZLER = "Sozler";
    private static final String TABLE_NAME_DERSLER = "Dersler";
    private static final String TABLE_NAME_SORULAR = "Sorular";
    private  SQLiteDatabase db;


    private static final String id = "id";
    private static final String image = "image";

    //region duyurular  +id+image
    private static final String title = "title";
    private static final String content = "content";
    private static final String urlLink = "url";
    //endregion

    //region sözler +id
    private static final String sozContent = "soz";
    //enregion

    //region dersler +id + image
    private static final String dersAdi = "dersAdi";
    //enregion

    //region sorular +id+image
    private static final String soru = "soru";
    private static final String cevap = "cevap";
    private static final String cozulduMu = "cozulduMu";
    //enregion


    private static String CREATE_TABLE_DUYURULAR = "CREATE TABLE IF NOT EXISTS  " + TABLE_NAME_DUYURULAR + "("
            + id + " INTEGER PRIMARY KEY, "
            + image + " BLOB, "
            + title + " TEXT, "
            + urlLink + " TEXT, "
            + content + " TEXT "
            + ")";

    private static String CREATE_TABLE_SOZLER = "CREATE TABLE IF NOT EXISTS  " + TABLE_NAME_SOZLER + "("
            + id + " INTEGER PRIMARY KEY, "
            + sozContent + " TEXT "
            + ")";

    private static String CREATE_TABLE_DERSLER = "CREATE TABLE IF NOT EXISTS  " + TABLE_NAME_DERSLER + "("
            + id + " INTEGER PRIMARY KEY, "
            + image + " BLOB, "
            + dersAdi + " TEXT "
            + ")";

    private static String CREATE_TABLE_SORULAR = "CREATE TABLE IF NOT EXISTS  " + TABLE_NAME_SORULAR + "("
            + id + " INTEGER , "
            + image + " BLOB, "
            + cozulduMu + " INTEGER, "
            + soru + " TEXT, "
            + cevap + " TEXT "
            + ")";

    public LocalDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;
        db.execSQL(CREATE_TABLE_DUYURULAR);
        db.execSQL(CREATE_TABLE_SOZLER);
        db.execSQL(CREATE_TABLE_DERSLER);
        db.execSQL(CREATE_TABLE_SORULAR);
    }

    public void addDuyuru(Duyuru model) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id, model.getId());
        values.put(image, model.getImageByte());
        values.put(urlLink,model.getUrl());
        values.put(title, model.getTitle());
        values.put(content, model.getContent());
        db.insert(TABLE_NAME_DUYURULAR, null, values);
        db.close();
    }
    public List<Duyuru> getAllDuyuru(){
        db=this.getReadableDatabase();
        List<Duyuru> duyurular = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME_DUYURULAR+ " ORDER BY id DESC  " ,null);;
        while (cursor.moveToNext()){
            Duyuru duyuru = new Duyuru();
            duyuru.setId(cursor.getInt(cursor.getColumnIndex(id)));
            duyuru.setUrl(cursor.getString(cursor.getColumnIndex(urlLink)));
            duyuru.setImageByte(cursor.getBlob(cursor.getColumnIndex(image)));
            duyuru.setTitle(cursor.getString(cursor.getColumnIndex(title)));
            duyuru.setContent(cursor.getString(cursor.getColumnIndex(content)));
            duyurular.add(duyuru);
        }
        db.close();
        return duyurular;
    }
    public int getCountDuyuru() {
        db = this.getReadableDatabase();
        String countQuery = "SELECT  count(*) FROM " + TABLE_NAME_DUYURULAR;
        Cursor mCount = db.rawQuery(countQuery, null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        db.close();
        return count;

    }
    public void deleteDuyurular() {
        db = this.getWritableDatabase();
        db.execSQL("DELETE  FROM " + TABLE_NAME_DUYURULAR);
        db.close();
    }

    public void addSoz(Soz data){
        db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(id,data.getId());
        values.put(sozContent,data.getSoz());
        db.insert(TABLE_NAME_SOZLER,null,values);
        db.close();
    }
    public int getCountSoz(){
        db = this.getWritableDatabase();
        String countQuery = "SELECT  count(*) FROM " + TABLE_NAME_SOZLER;
        Cursor mCount = db.rawQuery(countQuery, null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        db.close();
        return count;
    }
    public Soz getRandomSoz() {
        int count = getCountSoz();
        if (count==0) return  new Soz(1,"Dün geçti, bugün senin yarın bugünün eseri olucaktır.");
        int random = new Random().nextInt(count)+1;
        Soz data = new Soz();
        db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_SOZLER, null);
        cursor.move(random);
        data.setId(cursor.getInt(cursor.getColumnIndex(id)));
        data.setSoz(cursor.getString(cursor.getColumnIndex(sozContent)));
        db.close();
        return data;
    }
    public void deleteSozler(){
        db = this.getWritableDatabase();
        db.execSQL("DELETE  FROM " + TABLE_NAME_SOZLER);
        db.close();
    }

    public void  addDers(Ders model) {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id, model.getId());
        values.put(dersAdi, model.getDersAdi());
        values.put(image,model.getImageByte());
        db.insert(TABLE_NAME_DERSLER, null, values);
        db.close();
    }
    public List<Ders>  getAllDers(){
        db = this.getReadableDatabase();
        List<Ders>  models = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME_DERSLER,null);;
        while (cursor.moveToNext()){
            Ders ders = new Ders();
            ders.setId(cursor.getInt(cursor.getColumnIndex(id)));
            ders.setDersAdi(cursor.getString(cursor.getColumnIndex(dersAdi)));
            ders.setImageByte(cursor.getBlob(cursor.getColumnIndex(image)));
            models.add(ders);
        }
        db.close();
        return models;
    }
    public Ders getDers(int dersid){
        Ders ders = new Ders();
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME_DERSLER +" WHERE id= "+dersid ,null);;
        cursor.moveToFirst();
        ders.setId(cursor.getInt(cursor.getColumnIndex(id)));
        ders.setDersAdi(cursor.getString(cursor.getColumnIndex(dersAdi)));
        ders.setImageByte(cursor.getBlob(cursor.getColumnIndex(image)));
        return ders;
    }
    public int  getCountDers(){
        db = this.getWritableDatabase();
        String countQuery = "SELECT  count(*) FROM " + TABLE_NAME_DERSLER;
        Cursor mCount = db.rawQuery(countQuery, null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        db.close();
        return count;
    }
    public void deleteDersler() {
        db = this.getWritableDatabase();
        db.execSQL("DELETE  FROM " + TABLE_NAME_DERSLER);
        db.close();
    }

    public void deleteSorular(int id) {
        db = this.getWritableDatabase();
        db.execSQL("DELETE  FROM " + TABLE_NAME_SORULAR+ " WHERE id ="+id);
        db.close();
    }



    public void addSoru(Soru  model){
        db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(id, model.getId());
        values.put(soru, model.getSoru());
        values.put(cozulduMu,model.isCozulduMu());
        values.put(cevap,model.getCevap());
        values.put(image,model.getImageByte());

        db.insert(TABLE_NAME_SORULAR, null, values);
        db.close();

    }
    public List<Soru> getSorular(int target){
        db=this.getWritableDatabase();
        List<Soru> models=new ArrayList<>();
        Cursor cursor=  db.rawQuery("select * from " + TABLE_NAME_DUYURULAR+" WHERE id="+target ,null);;
        while (cursor.moveToNext()){
            Soru model = new Soru();
            model.setId(cursor.getInt(cursor.getColumnIndex(id)));
            model.setImageByte(cursor.getBlob(cursor.getColumnIndex(image)));
            model.setSoru(cursor.getString(cursor.getColumnIndex(soru)));
            model.setCevap(cursor.getString(cursor.getColumnIndex(cevap)));
            model.setCozulduMu(cursor.getInt(cursor.getColumnIndex(cozulduMu))==1);
            models.add(model);
        }
        db.close();
        return models;
    }
    public int getCountCozulenSoru(int target){
        db = this.getWritableDatabase();
        String countQuery = "SELECT  count(*) FROM " + TABLE_NAME_SORULAR+" WHERE id="+target+" AND "+cozulduMu+" = 1";
        Cursor mCount = db.rawQuery(countQuery, null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        db.close();
        return count;
    }
    public Soru getRandomsoru(int dersİd) {
        db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME_SORULAR +
               " WHERE id="+dersİd+" AND cozulduMu = 0", null);
        int count = cursor.getCount();
        if (count == 0) {
            db.close();
            return new Soru(0, "Hmmm.. görünüşe göre soru kalmadı. Soru eklenir sen rahat ol", "tamam", false);
        }
        int random = new Random().nextInt(count)+1;

        Soru model = new Soru();
        cursor.move(random);
        if (cursor.getBlob(cursor.getColumnIndex(image)) != null)
        model.setImageByte(cursor.getBlob(cursor.getColumnIndex(image)));

        model.setSoru(cursor.getString(cursor.getColumnIndex(soru)));
        model.setCevap(cursor.getString(cursor.getColumnIndex(cevap)));
        model.setCozulduMu(cursor.getInt(cursor.getColumnIndex(cozulduMu)) == 1);
        model.setId(cursor.getInt(cursor.getColumnIndex(id)));
        return model;
    }
    public int getCountAllSorular(){
        db = this.getWritableDatabase();
        String countQuery = "SELECT  count(*) FROM " + TABLE_NAME_SORULAR;
        Cursor mCount = db.rawQuery(countQuery, null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        db.close();
        return count;
    }
    public int getCountActiveSoru(){
        db = this.getReadableDatabase();
        String countQuery = "SELECT  count(*) FROM  " + TABLE_NAME_SORULAR+" where '"+cozulduMu+"' =  0" ;
        Cursor mCount = db.rawQuery(countQuery, null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        db.close();

        return count;
    }
    public int getCountDersSoru(int target){
        db = this.getReadableDatabase();
        String countQuery = "SELECT  count(*) FROM  " + TABLE_NAME_SORULAR+" WHERE id="+target;
        Cursor mCount = db.rawQuery(countQuery, null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        db.close();
        return count;
    }
    public void updateSoru(Soru model){
        try{
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            if (model.isCozulduMu()) values.put(cozulduMu,1);
            else values.put(cozulduMu,0);
            db.update(TABLE_NAME_SORULAR,values," soru = '"+model.getSoru()+"' ",null);
            db.close();
        } catch (Exception e){
            e.printStackTrace();
        }


    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DUYURULAR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SOZLER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_DERSLER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SORULAR);

        onCreate(db);
    }


}
