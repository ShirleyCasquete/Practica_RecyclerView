package com.example.practicarecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.practicarecyclerview.Adaptadores.UsuarioAdaptador;
import com.example.practicarecyclerview.Modelos.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import WebServices.Asynchtask;
import WebServices.WebService;

public class MainActivity extends AppCompatActivity  implements Asynchtask {
    public ListView LstOpciones;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LstOpciones = (ListView)findViewById(R.id.LstUsuarios);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://reqres.in/api/users",
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");
    }

    @Override
    public void processFinish(String result) throws JSONException {
        ArrayList LstUsuarios;
        JSONObject JSONLista =  new JSONObject(result);
        JSONArray JSONListaUsuarios=  JSONLista.getJSONArray("data");

        LstUsuarios = Usuario.JsonObjectsBuild(JSONListaUsuarios);

        UsuarioAdaptador adapatorUsuario = new UsuarioAdaptador(this, LstUsuarios);

        LstOpciones.setAdapter(adapatorUsuario);
    }
}