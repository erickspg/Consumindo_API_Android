package com.example.consumindows;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class paisRecyclerAdapter extends RecyclerView.Adapter<LinhaExibidaPais> {

    private final List<paisModel> list;
    private Context context;

    paisRecyclerAdapter(List<paisModel> valores) {
        list = new ArrayList<>();
        list.addAll(valores);
    }

    List<paisModel> getList() {
        return list;
    }

    /**
     * Método executado sempre que a tela é executada
     * @return
     */
    @NonNull
    @Override
    public LinhaExibidaPais onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        final LinhaExibidaPais holder = new LinhaExibidaPais(LayoutInflater.from(context)
                .inflate(R.layout.linha_simples_adaptador, parent, false));

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LinhaExibidaPais holder, int position) {
        // dado selecionado
        paisModel conteudoLinha = list.get(position);
        // exibe dados
        holder.casos.setText(String.valueOf(conteudoLinha.getCasos()));
        holder.mortes.setText(conteudoLinha.getMortes());
    }


    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }
}
