package com.example.zeglami.e_commerce;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.example.saadyousfi.e_commerce.entity.Client;
import com.example.saadyousfi.e_commerce.entity.Commande;
import com.example.saadyousfi.e_commerce.entity.LigneCommande;
import com.example.saadyousfi.e_commerce.entity.Livreur;
import com.example.saadyousfi.e_commerce.entity.Produit;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.LinkedList;
import com.example.saadyousfi.e_commerce.services.servicesGetClient;



public class session {
    SharedPreferences prefs;
    SharedPreferences.Editor editor;
    Context ctx;
    public String username;


    public session(Context ctx){
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedin(boolean loggedin){
        editor.putBoolean("loggedInmode",loggedin);
        editor.commit();
    }

    public boolean loggedin(){
        return prefs.getBoolean("loggedInmode", false);
    }

    public void setLoggedinLiv(boolean loggedin){
        editor.putBoolean("loggedInmodeLiv",loggedin);
        editor.commit();
    }

    public boolean loggedinLiv(){
        return prefs.getBoolean("loggedInmodeLiv", false);
    }

    public void setClient(Client c){
        Gson gson = new Gson();
        String json = gson.toJson(c);
        editor.putString("client",json);
        editor.commit();
    }

    public void setLivreur(Livreur liv){
        Gson gson = new Gson();
        String json = gson.toJson(liv);
        editor.putString("livreur",json);
        editor.commit();
    }

    public String getUsername(){
        return prefs.getString("username",username);
    }

    public void addProd(Produit prod){
        LinkedList<Produit> all_prods = new LinkedList<>() ;
        if(getProds() == null){
            all_prods.add(prod);
        }else{
            int i=0;
            for(Produit p : getProds()){
                all_prods.add(p);
                if(prod.getIdproduit() == p.getIdproduit()) i=-1;
            }
            if(i == 0) all_prods.add(prod);
        }
        Gson gson = new Gson();
        String json = gson.toJson(all_prods);
        editor.remove("prods");
        editor.putString("prods",json);
        editor.commit();

    }

    public void delProd(Produit prod){
        LinkedList<Produit> all_prods = new LinkedList<>() ;
        for(Produit p : getProds()){
            if(prod.getIdproduit() != p.getIdproduit()) all_prods.add(p);
        }
        Gson gson = new Gson();
        String json = gson.toJson(all_prods);
        editor.remove("prods");
        editor.putString("prods",json);
        editor.commit();
    }

    public LinkedList<Produit> getProds(){
        Gson gson = new Gson();
        String json = prefs.getString("prods","null" );
        if(json == null) return null;
        else {
            Type type = new TypeToken<LinkedList<Produit>>() {}.getType();
            return gson.fromJson(json, type);
        }
    }

    public Client getClient(){
        Gson gson = new Gson();
        String json = prefs.getString("client","null" );
        if(json == null) return null;
        else {
            Type type = new TypeToken<Client>() {}.getType();
            return gson.fromJson(json, type);
        }
    }

    public Livreur getLivreur(){
        Gson gson = new Gson();
        String json = prefs.getString("livreur","null" );
        if(json == null) return null;
        else {
            Type type = new TypeToken<Livreur>() {}.getType();
            return gson.fromJson(json, type);
        }
    }

    public void resetP(){
        editor.remove("prods");
        editor.commit();
    }

    public void logout(AppCompatActivity src,Class des){
        this.setLoggedin(false);
        this.setClient(null);
        src.finish();
        src.startActivity(new Intent(src,des));
    }

    public void logoutLiv(AppCompatActivity src,Class des){
        this.setLoggedinLiv(false);
        this.setLivreur(null);
        src.finish();
        src.startActivity(new Intent(src,des));
    }
}
