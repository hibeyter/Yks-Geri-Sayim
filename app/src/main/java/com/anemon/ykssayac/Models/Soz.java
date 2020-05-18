package com.anemon.ykssayac.Models;

public class Soz {
    private int id;
    private String soz;

    public Soz() {
    }

    public Soz(int id, String soz) {
        this.id = id;
        this.soz = soz;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoz() {
        return soz;
    }

    public void setSoz(String soz) {
        this.soz = soz;
    }
}
