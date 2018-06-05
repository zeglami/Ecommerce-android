package com.example.zeglami.e_commerce;


import android.app.Activity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.saadyousfi.e_commerce.entity.Client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mobiledev on 11/02/2018.
 */

public class servicesPostClient {
    String url;
    Client client = new Client();


    public servicesPostClient(String url, Activity a){
        this.url = url;
    }


    public void postClient(Client c, Activity a) {

        RequestQueue queue = Volley.newRequestQueue(a);
        JSONObject data = new JSONObject();
        try {
            data.put("nomclient",c.getNomclient());
            data.put("prenomclient", c.getPrenomclient());
            data.put("email", c.getEmail());
            Date date= new Date(c.getDatenaissance().getTime());
            System.out.println(date+"**************");
            data.put("datenaissance", date+"T00:00:00");
            data.put("telclient",c.getTelclient());
            data.put("adressecli",c.getAdressecli());
            data.put("loginclient", c.getLoginclient());
            data.put("mdpclient", c.getMdpclient());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Formulation de la requette et traitement de la reponse
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                data,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("**************************Response: " + response);
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("**************************Erreur: " + error);

                    }
                })
        {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                //headers.put("Content-Type", "application/json; charset=utf-8");
                return headers;
            }



        };
        queue.add(jsObjRequest);
    }

}
