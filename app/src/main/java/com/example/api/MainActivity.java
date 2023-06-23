package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import WebService.Asynchtask;
import WebService.WebService;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                "https://dummyjson.com/users"
                ,datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");

    }

    @Override
    public void processFinish(String result) throws JSONException {
        TextView lis=(TextView) findViewById(R.id.Txt);
        String Usser="";
        JSONObject objecto = new JSONObject(result);
        JSONArray lista = objecto.getJSONArray("users");
        for (int i=0; i< lista.length(); i++){
        JSONObject user = lista.getJSONObject(i);
        Usser = Usser +"\n"+
                user.getString("firstName").toString()+", "+
                user.getString("age").toString()+", "+
                user.getString("email").toString();}
        lis.setText("Respuesta:\n"+Usser);


        }
    }
