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

    public void historicoEstado(View v){

        Log.e("TAG_MTD",
                "Entrei no Metodo!");

        //NukeSSLCerts.nuke();
        RequestQueue fila = Volley.newRequestQueue(this);
        String url = "https://api.covidtracking.com/v1/us/daily.json";
        JsonObjectRequest request = new JsonObjectRequest
        (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject json) {

                try {

                    int casos, morte; //Informação do JSON -> positive, death
                    String mortes, mortes2;

                    casos = json.getInt("positive");
                    //morte = json.getInt("death");
                    mortes = json.get("death").toString();
                    mortes2 = json.getString("death");

                    Log.e("TAG_try",
                            "Try");

                    //textTop.setText(casos);
                    textTop.setText(mortes2);
                    //textTop.setText(mortes);

                    System.out.println(casos);
                    //System.out.println(morte);

                } catch (Exception e) {
                    Log.e("TAG_error",
                            "catch");
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        // Add the request to the RequestQueue.
        fila.add(request);
        //getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new historicoEFragment()).commit();

    }


    public void atuaisEstado(View v){
        RequestQueue fila = Volley.newRequestQueue(this);
        String urlServidor = "https://api.covidtracking.com/v1/us/daily.json";
        //Campos do JSON: positive / death

        // cria a requisição de mensagem e tratamento de resposta
        StringRequest requisicao = new StringRequest(
                Request.Method.GET, // 1 - Método usado para enviar mensagem
                urlServidor,        // 2 - Endereço do servidor
                new Response.Listener<String>() { // 3 - Objeto para tratar resposta
                    @Override
                    public void onResponse(String response) {
                        int mortes = 0;


                        try {
                            JSONObject obj = new JSONObject(response);
                            JSONArray jsonArray = obj.getJSONArray("positive");

                            for (int i=0; i < jsonArray.length(); i++)
                            {
                                try {
                                    JSONObject oneObject = jsonArray.getJSONObject(i);
                                    // Pulling items from the array
                                    String casos = oneObject.getString("positive");
                                    String mortos = oneObject.getString("death");

                                    Log.e("TESTE",
                                            "positivo:" + casos);

                                } catch (JSONException e) {
                                    // Oops
                                }
                            }
                            mortes = obj.getInt("death");
                            textTop.setText(mortes);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                        Log.e("TAG_RESPONSE",
                                "Entrei no OnResponse!" + mortes);
                        //textTop.setText(resultado.substring(0, 800));
                    }
                },

                new Response.ErrorListener() { // 4 - Objeto para tratar erro
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        // envia a mensagem ao servidor
        fila.add(requisicao);
        //getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new atuaisEFragment()).commit();
    }


}
