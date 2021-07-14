package com.example.consumindows;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class LinhaExibida extends RecyclerView.ViewHolder {

    public TextView texto;

    public LinhaExibida(View itemLinha){

        super(itemLinha);
        texto = itemLinha.findViewById(R.id.textView);
    }
}
