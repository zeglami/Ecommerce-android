package com.example.zeglami.e_commerce;

import android.app.Activity;
import android.util.Base64;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.saadyousfi.e_commerce.entity.Image;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.LinkedList;



public class servicesGetImage {
    String url;
    LinkedList<Image> listeImages=new LinkedList<Image>();

    public servicesGetImage(String url, Activity a){
        this.url=url;
        RequestQueue queue = Volley.newRequestQueue(a);
        JsonArrayRequest jsArrRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse( JSONArray  response) {
                        try {
                            for(int i=0;i<response.length();i++) {

                                Image img = new Image();
                                JSONObject object = response.getJSONObject(i);
                                JSONObject object2 = object.getJSONObject("idproduit");
                                img.setIdimage(object.getInt("idimage"));
                                img.setIdproduit(object2.getInt("idproduit"));
                                img.setPhoto(Base64.decode(object.getString("photo"),Base64.DEFAULT));
                                listeImages.add(img);
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

    }

    public LinkedList<Image> getAllImages(){
        return listeImages;
    }

    public Image getFirstImagebyProduct(int id){
        for (Image img : listeImages) {
            if (img.getIdproduit()==id){
                return img;
            }
        }
        return null;
    }

}
