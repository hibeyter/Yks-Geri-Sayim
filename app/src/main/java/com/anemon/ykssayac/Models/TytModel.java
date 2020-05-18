package com.anemon.ykssayac.Models;

public class TytModel  {
    private float turkDf =0, matDf =0, turkYf =0, matYf =0, fenDf =0, fenYf =0, sosDf =0, sosYf =0, dipFloat =0;
    private float Tnet=0,Mnet=0,Fnet=0,Snet=0;
    private boolean diplomaCheck=false;
    private double AYTpuan=0,TYTpuan=100;

    public double getTYTpuan() {
        float value = 0;
        if (diplomaCheck) value = dipFloat * 0.6f;
        TYTpuan = 100 + (getTnet() * 3.3) + (getMnet() * 3.3) +
                  (getFnet() * 3.4) + (getSnet() * 3.4) + value;
        return TYTpuan;
    }
    public double getHam() {
        return TYTpuan = 100 + (getTnet() * 3.3) + (getMnet() * 3.3) +
                (getFnet() * 3.4) + (getSnet() * 3.4) ;
    }

    public void setTYTpuan(double TYTpuan) {
        this.TYTpuan = TYTpuan;
    }

    public double getAYTpuan() {
        AYTpuan = (getTnet() * 1.32) + (getMnet() * 1.32) + (getFnet() * 1.36) + (getSnet() * 1.36);
        return AYTpuan;
    }

    public void setAYTpuan(double AYTpuan) {
        this.AYTpuan = AYTpuan;
    }

    public boolean isDiplomaCheck() {
        return diplomaCheck;
    }

    public void setDiplomaCheck(boolean diplomaCheck) {
        this.diplomaCheck = diplomaCheck;
    }

    public float getTurkDf() {
        return turkDf;
    }

    public void setTurkDf(float turkDf) {
        this.turkDf = turkDf;
    }

    public float getMatDf() {
        return matDf;
    }

    public void setMatDf(float matDf) {
        this.matDf = matDf;
    }

    public float getTurkYf() {
        return turkYf;
    }

    public void setTurkYf(float turkYf) {
        this.turkYf = turkYf;
    }

    public float getMatYf() {
        return matYf;
    }

    public void setMatYf(float matYf) {
        this.matYf = matYf;
    }

    public float getFenDf() {
        return fenDf;
    }

    public void setFenDf(float fenDf) {
        this.fenDf = fenDf;
    }

    public float getFenYf() {
        return fenYf;
    }

    public void setFenYf(float fenYf) {
        this.fenYf = fenYf;
    }

    public float getSosDf() {
        return sosDf;
    }

    public void setSosDf(float sosDf) {
        this.sosDf = sosDf;
    }

    public float getSosYf() {
        return sosYf;
    }

    public void setSosYf(float sosYf) {
        this.sosYf = sosYf;
    }

    public float getDipFloat() {
        return dipFloat;
    }

    public void setDipFloat(float dipFloat) {
        this.dipFloat = dipFloat;
    }

    public float getTnet() {
        return turkDf - (turkYf / 4);
    }

    public void setTnet(float tnet) {
        Tnet = tnet;
    }

    public float getMnet() {
        return matDf - (matYf / 4);
    }

    public void setMnet(float mnet) {
        Mnet = mnet;
    }

    public float getFnet() {
        return fenDf - (fenYf / 4);
    }

    public void setFnet(float fnet) {
        Fnet = fnet;
    }

    public float getSnet() {
        return sosDf - (sosYf / 4);
    }

    public void setSnet(float snet) {
        Snet = snet;
    }
}
