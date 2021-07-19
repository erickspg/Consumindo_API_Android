package com.example.consumindows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

class estadoRecyclerAdapter extends RecyclerView.Adapter<LinhaExibidaEstado> {
    private final List<estadoModel> list;
    private Context context;

    estadoRecyclerAdapter(List<estadoModel> valores) {
        list = new ArrayList<>();
        list.addAll(valores);
    }

    List<estadoModel> getList() {
        return list;
    }

    @NonNull
    @Override
    public LinhaExibidaEstado onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        final LinhaExibidaEstado holder = new LinhaExibidaEstado(LayoutInflater.from(context)
                .inflate(R.layout.linha_adaptador_estado, parent, false));

        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull LinhaExibidaEstado holder, int position) {
        // dado selecionado
        estadoModel conteudoLinha = list.get(position);
        // exibe dados
        holder.estado.setText(String.valueOf(conteudoLinha.getEstado()));
        holder.casos.setText(String.valueOf(conteudoLinha.getCasos()));
        holder.mortes.setText(conteudoLinha.getMortes());
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
}
