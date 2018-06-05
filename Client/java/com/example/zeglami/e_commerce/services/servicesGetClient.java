package com.example.zeglami.e_commerce;


import android.app.Activity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.example.saadyousfi.e_commerce.entity.Client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.Locale;


/**
 * Created by mobiledev on 11/02/2018.
 */

public class servicesGetClient {
    String url;
    LinkedList<Client> listeClients=new LinkedList<Client>();
    JSONObject JSONClt = new JSONObject();


    public servicesGetClient(String url, Activity a){
        this.url=url;
        intClients(a);
        //intProduits(a);
        //intCommandes(a);
    }


    public void intClients(Activity a){
        RequestQueue queue = Volley.newRequestQueue(a);
        JsonArrayRequest jsArrRequest = new JsonArrayRequest( Request.Method.GET,url,null,

                new Response.Listener<JSONArray>(){
        @Override
        public void onResponse( JSONArray  response) {

            try {
                for(int i=0;i<response.length();i++) {

                    Client c=new Client();

                    JSONObject object = response.getJSONObject(i);

                    c.setAdressecli(object.getString("adressecli"));

                    DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

                    c.setDatenaissance(format.parse(object.getString("datenaissance")));
                    c.setEmail(object.getString("email"));
                    c.setIdclient(object.getInt("idclient"));
                    c.setLoginclient(object.getString("loginclient"));
                    c.setNomclient(object.getString("nomclient"));
                    c.setPrenomclient(object.getString("prenomclient"));
                    c.setMdpclient(object.getString("mdpclient"));
                    c.setTelclient(object.getString("telclient"));

                    //object.getJSONArray("evaluationCollection");
                    //object.getJSONObject("commandeCollection");

                    listeClients.add(c);

                }
                System.out.println(listeClients);

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


    public LinkedList<Client> getAllClients(){
        return listeClients;
    }

    public JSONObject getJSONClient(final int idC, Activity a) {
        RequestQueue queue = Volley.newRequestQueue(a);
        JsonArrayRequest jsArrRequest = new JsonArrayRequest( Request.Method.GET,url,null,

                new Response.Listener<JSONArray>(){
                    @Override
                    public void onResponse( JSONArray  response) {

                        try {
                            for(int i=0;i<response.length();i++) {
                                JSONObject object = response.getJSONObject(i);
                                if(object.getInt("idclient") == idC ){
                                    JSONClt = object;
                                }

                                //object.getJSONArray("evaluationCollection");
                                //object.getJSONObject("commandeCollection");
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
        return JSONClt;
    }

    public boolean isClient(String log, String pass){
        for(Client c : listeClients ){
            if (c.getLoginclient().equals(log) && c.getMdpclient().equals(pass)){
                return true;
            }
        }
        return false;
    }

    public Client thisClient(String username){
        for(Client c:listeClients){
            if(username.equals(c.getLoginclient())){
                return c;
            }
        }
        return null;
    }



}
