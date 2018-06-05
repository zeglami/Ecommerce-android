package com.example.zeglami.e_commerce;


import android.app.Activity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.saadyousfi.e_commerce.entity.Commande;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hamidze on 01/05/2018.
 */

public class servicesPutCommande {
    String url;

    public servicesPutCommande(String url,Activity a){
        this.url = url;
    }

    public void putCommande(Commande cmd, Activity a) {

        RequestQueue queue = Volley.newRequestQueue(a);
        JSONObject data = new JSONObject();
        JSONObject data2 = new JSONObject();
        JSONObject data3 = new JSONObject();
        String thisurl = url+"/"+cmd.getIdcommande();
        try {

            data.put("idcommande",cmd.getIdcommande());
            data.put("adresselivraison",cmd.getAdresselivraison());
            data.put("nomlivraison", cmd.getNomlivraison());
            data.put("prenomlivraison", cmd.getPrenomlivraison());
            data.put("tellivraison", cmd.getTellivraison());
            Date date= new Date(cmd.getDatecommande().getTime());
            data.put("datecommande", date);

            data2.put("idlivreur",cmd.getIdlivreur());
            data3.put("idclient",cmd.getIdclient());

            data.put("idlivreur", data2);
            data.put("idclient", data3);
            data.put("etat",cmd.getEtat());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Formulation de la requette et traitement de la reponse
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.PUT,
                thisurl,
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
