package com.example.zeglami.e_commerce;


import android.app.Activity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.VolleyError;
import com.example.saadyousfi.e_commerce.entity.Client;
import com.example.saadyousfi.e_commerce.entity.Commande;
import com.example.saadyousfi.e_commerce.entity.LigneCommande;
import com.example.saadyousfi.e_commerce.entity.Livreur;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by hamidez 19/03/2018.
 */

public class servicesPostCommande {
    String url_cmd;
    String url_lcmd;
    public int id_lastcmd = 0;

    Commande cmd = new Commande();

    LinkedList<LigneCommande> lignescmd = new LinkedList<>();

    public servicesPostCommande(String url_cmd,String url_lcmd,Activity a){
        this.url_cmd = url_cmd;
        this.url_lcmd = url_lcmd;
    }

    public void postCommande(Commande cmd, Activity a) {

        RequestQueue queue = Volley.newRequestQueue(a);
        JSONObject data = new JSONObject();
        try {
            data.put("idcommande",cmd.getIdcommande());
            data.put("adresselivraison",cmd.getAdresselivraison());
            data.put("nomlivraison", cmd.getNomlivraison());
            data.put("prenomlivraison", cmd.getPrenomlivraison());
            data.put("tellivraison", cmd.getTellivraison());
            Date date= new Date(cmd.getDatecommande().getTime());
            data.put("datecommande", date);
            data.put("idlivreur", cmd.getIdlivreur());
            data.put("idclient", cmd.getIdclient());
            data.put("etat","En Attente");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Formulation de la requette et traitement de la reponse
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.POST,
                url_cmd,
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


    public void postLignesCommande(int last,LinkedList<LigneCommande> lc, Activity a) {

        for(LigneCommande lcmd : lc) {

            RequestQueue queue = Volley.newRequestQueue(a);
            JSONObject data = new JSONObject();
            JSONObject data2 = new JSONObject();
            try {

                data2.put("idcommande", last);
                data2.put("idproduit", lcmd.getIdproduit());

                data.put("ligneDeCommandePK", data2);
                data.put("qtecomm", lcmd.getQtecommu());


            } catch (JSONException e) {
                e.printStackTrace();
            }

            // Formulation de la requette et traitement de la reponse
            JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    url_lcmd,
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
                    }) {
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

}


