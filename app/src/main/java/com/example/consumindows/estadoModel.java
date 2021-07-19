package com.example.consumindows;

class estadoModel {

    public String estado;
    public String casos;
    public String mortes;


    public estadoModel(String estado, String casos, String mortes) {
        this.estado = estado;
        this.casos = casos;
        this.mortes = mortes;
    }

    public String getEstado() {

        return estado;
    }

    public void setEstado(String estado) {

        this.estado = estado;
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

    public void setMortes(String mortes) {

        this.mortes = mortes;
    }

}
