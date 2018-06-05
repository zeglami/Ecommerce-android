package com.example.zeglami.e_commerce;

import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.example.saadyousfi.e_commerce.entity.Livreur;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;



public class servicesGetLivreur {
    String url;
    LinkedList<Livreur> listeLivreurs = new LinkedList<Livreur>();
    JSONObject JSONLiv = new JSONObject();


    public servicesGetLivreur(String url, Activity a){
        this.url=url;
        intLivreurs(a);
    }


    public void intLivreurs(Activity a){
        RequestQueue queue = Volley.newRequestQueue(a);
        JsonArrayRequest jsArrRequest = new JsonArrayRequest( Request.Method.GET,url,null,

                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse( JSONArray  response) {

                        try {
                            for(int i=0;i<response.length();i++) {

                                Livreur l = new Livreur();

                                JSONObject object = response.getJSONObject(i);

                                l.setIdlivreur(object.getInt("idlivreur"));
                                l.setLoginlivreur(object.getString("loginlivreur"));
                                l.setNomlivreur(object.getString("nomlivreur"));
                                l.setPrenomlivreur(object.getString("prenomlivreur"));
                                l.setMdplivreur(object.getString("mdplivreur"));
                                l.setTellivreur(object.getString("tellivreur"));

                                listeLivreurs.add(l);

                            }
                            System.out.println(listeLivreurs);

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

    public LinkedList<Livreur> getAllLivreurs(){
        return listeLivreurs;
    }

    public JSONObject getJSONLivreur(final int idL, Activity a) {
        RequestQueue queue = Volley.newRequestQueue(a);
        JsonArrayRequest jsArrRequest = new JsonArrayRequest( Request.Method.GET,url,null,

                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse( JSONArray  response) {

                        try {
                            for(int i=0;i<response.length();i++) {
                                JSONObject object = response.getJSONObject(i);
                                if(object.getInt("idlivreur") == idL ){
                                    JSONLiv = object;
                                }

                            }

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
        return JSONLiv;
    }

    public boolean isLivreur(String log, String pass){
        for(Livreur liv : listeLivreurs ){
            if (liv.getLoginlivreur().equals(log) && liv.getMdplivreur().equals(pass)){
                return true;
            }
        }
        return false;
    }

    public Livreur thisLivreur(String username){
        for(Livreur liv:listeLivreurs){
            if(username.equals(liv.getLoginlivreur())){
                return liv;
            }
        }
        return null;
    }

    public int idLivreur(String livreur){
        for(Livreur liv : listeLivreurs){
            if((liv.getNomlivreur()+" "+liv.getPrenomlivreur()).equals(livreur)) return liv.getIdlivreur();
        }
        return 0;
    }

    public String thisLivreur(int id){
        for(Livreur liv : listeLivreurs){
            if(liv.getIdlivreur() == id){
                return liv.getNomlivreur()+" "+liv.getPrenomlivreur();
            }
        }
        return null;
    }

}
