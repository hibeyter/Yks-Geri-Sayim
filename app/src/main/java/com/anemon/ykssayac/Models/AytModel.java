package com.anemon.ykssayac.Models;

public class AytModel {
    private double AYTpuan=0,SAYpuan=0,SOZpuan=0,EApuan=0,DILpuan;
    private float tdeDf=0,tdeYf=0,tar1Df=0,tar1Yf=0,cog1Df=0,cog1Yf=0,tar2Df=0,tar2Yf=0,cog2Df=0,cog2Yf=0,felDf=0,felYf=0,
    dinDf=0,dinYf=0,matDf=0,matYf=0,fizDf=0,fizYf=0,kimDf=0,kimYf=0,biyDf=0,biyYf=0,dilDf=0,dilYf=0, DIPLOMAf =0;

    public double getAYTpuan() {
        return AYTpuan;
    }

    public void setAYTpuan(double AYTpuan) {
        this.AYTpuan = AYTpuan;
    }

    public double getSAYpuan() {
        SAYpuan=100 + AYTpuan + (getMATnet() * 3) + (getFIZnet() * 2.85) + (getKIMnet() * 3.07) + (getBIYnet() * 3.07);
        return SAYpuan+0.6*DIPLOMAf;
    }

    public void setSAYpuan(double SAYpuan) {
        this.SAYpuan = SAYpuan;
    }

    public double getSOZpuan() {
        SOZpuan=100 + AYTpuan + (getTDEnet() * 3) + (getTAR1net() * 2.8) + (getCOG1net() * 3.33) + (getTAR2net() * 2.91) +
                (getCOG21net() * 2.91) + (getFELnet() * 3.3) + (getDINnet() * 3.33);
        return  SOZpuan+0.6*DIPLOMAf;
    }



    public void setSOZpuan(double SOZpuan) {
        this.SOZpuan = SOZpuan;
    }

    public double getEApuan() {
        EApuan=100 + AYTpuan + (getMATnet() * 3) + (getTDEnet() * 3) + (getTAR1net() * 2.8) + (getCOG1net() * 3.33);
        return EApuan+0.6*DIPLOMAf;
    }

    public double getDILpuan() {
        DILpuan = 100 + AYTpuan + (getDILnet() * 3);
        return DILpuan+0.6*DIPLOMAf;
    }

    public void setDILpuan(double DILpuan) {

        this.DILpuan = DILpuan;
    }

    public void setEApuan(double EApuan) {
        this.EApuan = EApuan;
    }
    public float getTDEnet(){
        return tdeDf-(tdeYf/4);
    }
    public float getTAR1net(){
        return tar1Df-(tar1Yf/4);
    }
    public float getCOG1net(){
        return cog1Df-(cog1Yf/4);
    }
    public float getTAR2net(){
        return tar2Df-(tar2Yf/4);
    }
    public float getCOG21net(){
        return cog2Df-(cog2Yf/4);
    }
    public float getFELnet(){
        return felDf-(felYf/4);
    }
    public float getDINnet(){
        return dinDf-(dinYf/4);
    }
    public float getMATnet(){
        return matDf-(matYf/4);
    }

    public float getFIZnet(){
        return fizDf-(fizYf/4);
    }
    public float getKIMnet(){
        return kimDf-(kimYf/4);
    }
    public float getBIYnet(){
        return biyDf-(biyYf/4);
    }
    public float getDILnet(){
        return dilDf-(dilYf/4);
    }




    public float getTdeDf() {
        return tdeDf;
    }

    public void setTdeDf(float tdeDf) {
        this.tdeDf = tdeDf;
    }

    public float getTdeYf() {
        return tdeYf;
    }

    public void setTdeYf(float tdeYf) {
        this.tdeYf = tdeYf;
    }

    public float getTar1Df() {
        return tar1Df;
    }

    public void setTar1Df(float tar1Df) {
        this.tar1Df = tar1Df;
    }

    public float getTar1Yf() {
        return tar1Yf;
    }

    public void setTar1Yf(float tar1Yf) {
        this.tar1Yf = tar1Yf;
    }

    public float getCog1Df() {
        return cog1Df;
    }

    public void setCog1Df(float cog1Df) {
        this.cog1Df = cog1Df;
    }

    public float getCog1Yf() {
        return cog1Yf;
    }

    public void setCog1Yf(float cog1Yf) {
        this.cog1Yf = cog1Yf;
    }

    public float getTar2Df() {
        return tar2Df;
    }

    public void setTar2Df(float tar2Df) {
        this.tar2Df = tar2Df;
    }

    public float getTar2Yf() {
        return tar2Yf;
    }

    public void setTar2Yf(float tar2Yf) {
        this.tar2Yf = tar2Yf;
    }

    public float getCog2Df() {
        return cog2Df;
    }

    public void setCog2Df(float cog2Df) {
        this.cog2Df = cog2Df;
    }

    public float getCog2Yf() {
        return cog2Yf;
    }

    public void setCog2Yf(float cog2Yf) {
        this.cog2Yf = cog2Yf;
    }

    public float getFelDf() {
        return felDf;
    }

    public void setFelDf(float felDf) {
        this.felDf = felDf;
    }

    public float getFelYf() {
        return felYf;
    }

    public void setFelYf(float felYf) {
        this.felYf = felYf;
    }

    public float getDinDf() {
        return dinDf;
    }

    public void setDinDf(float dinDf) {
        this.dinDf = dinDf;
    }

    public float getDinYf() {
        return dinYf;
    }

    public void setDinYf(float dinYf) {
        this.dinYf = dinYf;
    }

    public float getMatDf() {
        return matDf;
    }

    public void setMatDf(float matDf) {
        this.matDf = matDf;
    }

    public float getMatYf() {
        return matYf;
    }

    public void setMatYf(float matYf) {
        this.matYf = matYf;
    }

    public float getFizDf() {
        return fizDf;
    }

    public void setFizDf(float fizDf) {
        this.fizDf = fizDf;
    }

    public float getFizYf() {
        return fizYf;
    }

    public void setFizYf(float fizYf) {
        this.fizYf = fizYf;
    }

    public float getKimDf() {
        return kimDf;
    }

    public void setKimDf(float kimDf) {
        this.kimDf = kimDf;
    }

    public float getKimYf() {
        return kimYf;
    }

    public void setKimYf(float kimYf) {
        this.kimYf = kimYf;
    }

    public float getBiyDf() {
        return biyDf;
    }

    public void setBiyDf(float biyDf) {
        this.biyDf = biyDf;
    }

    public float getBiyYf() {
        return biyYf;
    }

    public void setBiyYf(float biyYf) {
        this.biyYf = biyYf;
    }

    public float getDilDf() {
        return dilDf;
    }

    public void setDilDf(float dilDf) {
        this.dilDf = dilDf;
    }

    public float getDilYf() {
        return dilYf;
    }

    public void setDilYf(float dilYf) {
        this.dilYf = dilYf;
    }

    public float getDIPLOMAf() {
        return DIPLOMAf;
    }

    public void setDIPLOMAf(float DIPLOMAf) {
        this.DIPLOMAf = DIPLOMAf;
    }
}
