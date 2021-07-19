package com.example.consumindows;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class historicoPFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private List<paisModel> dados = new ArrayList<>();
    private paisRecyclerAdapter adaptadorPais;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_historico_p, container, false);
        recyclerView = view.findViewById(R.id.listaPais);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        adaptadorPais = new paisRecyclerAdapter(dados);
        recyclerView.setAdapter(adaptadorPais);
        Bundle bundle = getArguments();
        String response = bundle.getString("response");
        adaptadorPais.getList().addAll(transformaJson(response));
        return view;
    }

    private List<paisModel> transformaJson(String response) {
        try {
            List<paisModel> dados = new ArrayList<>();
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String casos = jsonObject.getString("positive");
                String mortes = jsonObject.getString("death");
                paisModel USModel = new paisModel(casos, mortes);
                dados.add(USModel);
            }
            return dados;
        }catch (JSONException e){
            throw new RuntimeException(e.getMessage());
        }

    }
}
