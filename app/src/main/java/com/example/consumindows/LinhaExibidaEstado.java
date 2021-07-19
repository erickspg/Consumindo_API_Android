package com.example.consumindows;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

class LinhaExibidaEstado extends RecyclerView.ViewHolder {

    public TextView estado;
    public TextView casos;
    public TextView mortes;


    public LinhaExibidaEstado(View itemLinha){

        super(itemLinha);
        estado = itemView.findViewById(R.id.estado);
        casos =  itemView.findViewById(R.id.casos);
        mortes = itemView.findViewById(R.id.mortes);

    }
}
