package com.example.consumindows;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorRecyclerView extends RecyclerView.Adapter<LinhaExibida>{

    private ArrayList<String> valores;
    private Context context;

    public AdaptadorRecyclerView(Context context, ArrayList<String> valores){

        this.context= context;
        this.valores = valores;

    }

    @NonNull
    @Override
    public LinhaExibida onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View linha = LayoutInflater.from(context).inflate(R.layout.linha_simples_adaptador,parent, false);
        return new LinhaExibida(linha);
    }

    @Override
    public void onBindViewHolder(@NonNull LinhaExibida linhaExibida, int i) {

        String valorSelecionado = valores.get(i);
        linhaExibida.texto.setText(valorSelecionado);

        /*linhaExibida.texto.setOnClickListener(new View.OnClickListener()) {

            public void onClick(View v){
                Toast.makeText(context, "Clicado: " + valorSelecionado, Toast.LENGTH_SHORT).show();
            }
        }*/
    }

    @Override
    public int getItemCount() {
        return valores.size();
    }
}
