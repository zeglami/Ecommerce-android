package com.example.zeglami.e_commerce;


import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.saadyousfi.e_commerce.entity.Commande;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Locale;



public class servicesGetCommandes {
    String url;
    LinkedList<Commande> listeCommandes = new LinkedList<Commande>();
    JSONObject JSONCmd = new JSONObject();
    public int id_lastcmd;


    public servicesGetCommandes(String url, Activity a){
        this.url=url;
        intCommandes(a);
    }


    public void intCommandes(Activity a){
        RequestQueue queue = Volley.newRequestQueue(a);
        JsonArrayRequest jsArrRequest = new JsonArrayRequest( Request.Method.GET,url,null,

                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse( JSONArray  response) {

                        try {
                            for(int i=0;i<response.length();i++) {

                                Commande cmd = new Commande();

                                JSONObject object = response.getJSONObject(i);
                                JSONObject client = object.getJSONObject("idclient");
                                JSONObject livreur = object.getJSONObject("idlivreur");

                                cmd.setIdcommande(object.getInt("idcommande"));
                                cmd.setIdlivreur(livreur.getInt("idlivreur"));
                                cmd.setIdclient(client.getInt("idclient"));
                                cmd.setEtat(object.getString("etat"));
                                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                                cmd.setDatecommande(format.parse(object.getString("datecommande")));
                                cmd.setTellivraison(object.getString("tellivraison"));
                                cmd.setPrenomlivraison(object.getString("prenomlivraison"));
                                cmd.setNomlivraison(object.getString("nomlivraison"));
                                cmd.setAdresselivraison(object.getString("adresselivraison"));

                                listeCommandes.add(cmd);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (ParseException e) {
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


    public LinkedList<Commande> getAllCommandes(){
        return listeCommandes;
    }

    public int idlastCommande(Activity a){
        intCommandes(a);
        if(getAllCommandes().isEmpty()) return 0;
        else return getAllCommandes().getLast().getIdcommande();
    }

    public LinkedList<Commande> getMyCommandes(int idCli,Activity a){
        LinkedList<Commande> MyCmds = new LinkedList<Commande>();
        intCommandes(a);
        for(Commande cmd : listeCommandes){
            if(cmd.getIdclient() == idCli && !cmd.getEtat().equals("Supprime")) MyCmds.add(cmd);
        }
        return MyCmds;
    }

    public LinkedList<Commande> getMyLivraisons(int idLiv,Activity a){
        LinkedList<Commande> MyCmds = new LinkedList<Commande>();
        intCommandes(a);
        for(Commande cmd : listeCommandes){
            if(cmd.getIdlivreur() == idLiv && !cmd.getEtat().equals("Supprime")) MyCmds.add(cmd);
        }
        return MyCmds;
    }

    public JSONObject getJSONCommande(final int idCmd,Activity a) {
        RequestQueue queue = Volley.newRequestQueue(a);
        JsonArrayRequest jsArrRequest = new JsonArrayRequest( Request.Method.GET,url,null,

                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse( JSONArray  response) {

                        try {
                            for(int i=0;i<response.length();i++) {
                                JSONObject object = response.getJSONObject(i);
                                if(object.getInt("idcommande") == idCmd ){
                                    JSONCmd = object;
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
        return JSONCmd;
    }

}
