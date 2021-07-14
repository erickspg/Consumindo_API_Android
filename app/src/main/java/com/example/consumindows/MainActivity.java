package com.example.consumindows;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;

public class MainActivity extends AppCompatActivity {

    public Button btnHistoricoPais, btnHistoricoEstado, btnAtualEstado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHistoricoPais = findViewById(R.id.historicoPais);
        btnHistoricoEstado = findViewById(R.id.historicoEstado);
        btnAtualEstado = findViewById(R.id.atuaisPorEstado);

    }

    public void historicoPais(){


        NukeSSLCerts.nuke();
        RequestQueue filaEnviadoraDeMensagens = Volley.newRequestQueue(this);
        String urlServidor = "https://api.covidtracking.com/v1/us/daily.json";
        //Campos do JSON: positive / death

        // cria a requisição de mensagem e tratamento de resposta
        StringRequest requisicao = new StringRequest(
                DownloadManager.Request.Method.GET, // 1 - Método usado para enviar mensagem
                urlServidor,        // 2 - Endereço do servidor
                new Response.Listener<JSONArray>() { // 3 - Objeto para tratar resposta
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONArray resultado = response;

                    }
                },

                new Response.ErrorListener() { // 4 - Objeto para tratar erro
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // exibe o erro na tela e nos Logs
                        Toast.makeText(
                                getBaseContext(),
                                "Erro: "+error,
                                Toast.LENGTH_LONG).show();
                        // log de erro no LogCat
                        Log.e("TAG_EXEMPLO",
                                "Erro encontrado ao tentar enviar mensagem",error);
                    }
                });

        // envia a mensagem ao servidor
        filaEnviadoraDeMensagens.add(requisicao);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new historicoPFragment()).commit();

    }

    public void historicoEstado(){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new historicoEFragment()).commit();
    }

    public void atuaisEstado(){
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new atuaisEFragment()).commit();
    }
}
