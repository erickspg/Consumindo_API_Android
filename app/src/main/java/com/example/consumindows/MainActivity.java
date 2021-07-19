package com.example.consumindows;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public String historicoPais, historicoEstado, atuaisEstado;
    public Button btnHistoricoPais, btnHistoricoEstado, btnAtualEstado;
    public TextView textTop;
    public Bundle param = new Bundle();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHistoricoPais = findViewById(R.id.historicoPais);
        btnHistoricoEstado = findViewById(R.id.historicoEstado);
        btnAtualEstado = findViewById(R.id.atuaisPorEstado);
        textTop = findViewById(R.id.textTop);
        historicoPais();
        historicoEstado();
        atuaisEstado();
    }

    public void HistoricoP(View view) {
        //instancio fragmento
        historicoPFragment fragment = new historicoPFragment();
        param.putString("response", historicoPais);  //envio os parametros
        fragment.setArguments(param);

        //faço a transação do fragmento
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment); // amarro ao meu frame layout
        fragmentTransaction.commit();
    }

    public void HistoricoE(View view) {
        //instancio fragmento
        historicoEFragment fragment = new historicoEFragment();
        param.putString("response", historicoEstado);  //envio os parametros
        fragment.setArguments(param);

        //faço a transação do fragmento
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment); // amarro ao meu frame layout
        fragmentTransaction.commit();
    }

    public void atuaisE(View view) {
        //instancio fragmento
        atuaisEFragment fragment = new atuaisEFragment();
        param.putString("response", atuaisEstado);  //envio os parametros
        fragment.setArguments(param);

        //faço a transação do fragmento
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_container, fragment); // amarro ao meu frame layout
        fragmentTransaction.commit();
    }

    public void historicoPais(){


        //NukeSSLCerts.nuke();
        RequestQueue fila = Volley.newRequestQueue(this);
        String urlServidor = "https://api.covidtracking.com/v1/us/daily.json";    //Campos do JSON: positive / death


        // cria a requisição de mensagem e tratamento de resposta
        StringRequest  requisicao = new StringRequest (Request.Method.GET,urlServidor, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {

                historicoPais = result;
                //textTop.setText(response);
                Log.e("TAG_RESPONSE",
                        "Entrei no OnResponse!");

            }
        },

        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Exibe o erro na tela
                Toast.makeText(
                        getBaseContext(),
                        "Erro: "+error,
                        Toast.LENGTH_LONG).show();
                // log de erro
                Log.e("TAG_EXEMPLO",
                        "Erro encontrado ao tentar enviar mensagem",error);
            }
        });

        // envia a mensagem ao servidor
        fila.add(requisicao);

    }

    public void historicoEstado(){

        RequestQueue fila = Volley.newRequestQueue(this);
        String urlServidor = "https://api.covidtracking.com/v1/states/daily.json";    //Campos do JSON: state / positive / death


        // cria a requisição de mensagem e tratamento de resposta
        StringRequest  requisicao = new StringRequest (Request.Method.GET,urlServidor, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {

                historicoEstado = result;

            }
        },

        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // envia a mensagem ao servidor
        fila.add(requisicao);

    }


    public void atuaisEstado(){
        RequestQueue fila = Volley.newRequestQueue(this);
        String urlServidor = "https://api.covidtracking.com/v1/states/current.json";    //Campos do JSON: state / positive / death


        // cria a requisição de mensagem e tratamento de resposta
        StringRequest  requisicao = new StringRequest (Request.Method.GET,urlServidor, new Response.Listener<String>() {
            @Override
            public void onResponse(String result) {

                atuaisEstado = result;

            }
        },

        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // envia a mensagem ao servidor
        fila.add(requisicao);
    }


}
