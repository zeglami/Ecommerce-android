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

/**
 * Created by mobiledev on 11/02/2018.
 */

public class servicesGetProduit {
    String url;
    LinkedList<Produit> listeProduits=new LinkedList<Produit>();

    public servicesGetProduit(String url, Activity a){
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

                                Produit p=new Produit();
                                JSONObject object = response.getJSONObject(i);
                                p.setIdproduit(object.getInt("idproduit"));
                                p.setPrix(object.getInt("prix"));
                                p.setIdcatalogue(object.getInt("idcatalogue"));
                                p.setNomproduit(object.getString("nomproduit"));
                                p.setTypeproduit(object.getString("typeproduit"));
                                listeProduits.add(p);
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

    public LinkedList<Produit> getAllProducts(){
        return listeProduits;
    }

    public LinkedList<Produit> getProductsbyCatalogue(int id){
        LinkedList<Produit> liste=new LinkedList<Produit>();
        for (Produit p : listeProduits) {
            if (p.getIdcatalogue()==id){
                liste.add(p);
            }
        }
        return liste;
    }

    public Produit getProdbyId(int id){
        for(Produit p : listeProduits){
            if(p.getIdproduit()==id) return p;
        }
        return null;
    }

}
