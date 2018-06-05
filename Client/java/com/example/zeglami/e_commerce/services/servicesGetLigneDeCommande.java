package com.example.zeglami.e_commerce;


import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.saadyousfi.e_commerce.entity.LigneCommande;
import com.example.saadyousfi.e_commerce.entity.Produit;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;


public class servicesGetLigneDeCommande {
    String url;
    Activity a;
    LinkedList<LigneCommande> listeLCMD = new LinkedList<>();
    JSONObject JSONLCmd = new JSONObject();

    public servicesGetLigneDeCommande(String url, Activity a){
        this.url=url;
        this.a=a;
        intLigneCMD(a);
    }

    public void intLigneCMD(Activity a){
        RequestQueue queue = Volley.newRequestQueue(a);
        JsonArrayRequest jsArrRequest = new JsonArrayRequest( Request.Method.GET,url,null,

                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse( JSONArray  response) {

                        try {
                            for(int i=0;i<response.length();i++) {

                                LigneCommande lcmd = new LigneCommande();

                                JSONObject object = response.getJSONObject(i);
                                JSONObject lignePK = object.getJSONObject("ligneDeCommandePK");

                                lcmd.setIdcommande(lignePK.getInt("idcommande"));
                                lcmd.setIdproduit(lignePK.getInt("idproduit"));
                                lcmd.setQtecommu(object.getInt("qtecomm"));

                                listeLCMD.add(lcmd);
                            }
                            System.out.println(listeLCMD);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        System.out.println("**************************Erreur: " + error);
                    }
                });
        queue.add(jsArrRequest);
    }

    public LinkedList<LigneCommande> getAllLCMD(){
        return listeLCMD;
    }

    public LinkedList<LigneCommande> getMyLCMD(int idCMD){
        LinkedList<LigneCommande> MyLCMD = new LinkedList<LigneCommande>();
        intLigneCMD(a);
        for(LigneCommande lcmd : listeLCMD){
            if(lcmd.getIdcommande()==idCMD){
                MyLCMD.add(lcmd);
            }
        }
        return MyLCMD;
    }

}
