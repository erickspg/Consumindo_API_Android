package com.example.consumindows;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class historicoEFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private List<estadoModel> dados = new ArrayList<>();
    private estadoRecyclerAdapter adaptadorEstado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_historico_e, container, false);
        recyclerView = view.findViewById(R.id.listaEstado);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        adaptadorEstado = new estadoRecyclerAdapter(dados);
        recyclerView.setAdapter(adaptadorEstado);
        Bundle bundle = getArguments();
        String response = bundle.getString("response");
        adaptadorEstado.getList().addAll(transformaJson(response));
        return view;
    }

    private List<estadoModel> transformaJson(String response) {
        try {
            List<estadoModel> dados = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String estado = jsonObject.getString("state");
                String casos = jsonObject.getString("positive");
                String mortes = jsonObject.getString("death");
                estadoModel estadoModel = new estadoModel(estado, casos, mortes);
                dados.add(estadoModel);
            }
            return dados;
        }catch (JSONException e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
