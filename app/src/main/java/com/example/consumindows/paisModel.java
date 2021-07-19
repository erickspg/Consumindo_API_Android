package com.example.consumindows;


import android.view.View;

class paisModel {

    public String casos;
    public String mortes;


    /*public paisModel(String casos, String mortes) {
        this.casos = casos;
        this.mortes = mortes;
    }*/

    public paisModel(String casos, String mortes) {
        this.casos = casos;
        this.mortes = mortes;
    }


    public String getCasos() {

        return casos;
    }

    public void setCasos(String casos) {

        this.casos = casos;
    }

    public String getMortes() {

        return mortes;
    }

    public void setMortes(String mortes)
    {
        this.mortes = mortes;
    }
}
