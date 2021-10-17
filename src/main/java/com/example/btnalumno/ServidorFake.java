package com.example.btnalumno;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;


public class ServidorFake  {
    
    android.app.Activity activity;

    RequestQueue queue;
    CallbackMedidas callbackMedidas;


    String URL = "http://localhost:8080";


    //constructor
    public ServidorFake(Activity activity){

        this.activity = activity;

        queue = Volley.newRequestQueue(activity);

        queue.start();

    }

    public void GetMedidas(CallbackMedidas callback){
        String url = URL + "/medida";



        JsonArrayRequest json = new JsonArrayRequest(Request.Method.GET,url,new JSONArray(),
                    new Response.Listener<JSONArray>(){
                    @Override
                        public void onResponse(JSONArray response) {

                        if(response == null){
                            Log.d("prueba" , "no recibe nada");
                        }else{

                            callback.callbackMedidas(response);
                        }
                    }
                    },

                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error){
                            NetworkResponse networkResponse = error.networkResponse;
                        }
                    }

                );
        queue.add(json);
    }

}
