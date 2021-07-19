package com.example.consumindows;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class LinhaExibidaPais extends RecyclerView.ViewHolder {

    public TextView casos;
    public TextView mortes;


    public LinhaExibidaPais(View itemLinha){

        super(itemLinha);
        casos = itemView.findViewById(R.id.casos);
        mortes = itemView.findViewById(R.id.mortes);

    }
}
