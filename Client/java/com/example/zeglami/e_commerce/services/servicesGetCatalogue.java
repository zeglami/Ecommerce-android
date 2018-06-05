package com.example.zeglami.e_commerce;


        import android.app.Activity;

        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.JsonArrayRequest;
        import com.android.volley.toolbox.Volley;
        import com.example.saadyousfi.e_commerce.entity.Catalogue;
        import com.example.saadyousfi.e_commerce.entity.Produit;
        import com.example.saadyousfi.e_commerce.entity.Client;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.text.DateFormat;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.LinkedList;
        import java.util.Locale;

/**
 * Created by mobiledev on 11/02/2018.
 */

public class servicesGetCatalogue {
    String url;
    LinkedList<Catalogue> listeCatalogues=new LinkedList<Catalogue>();

    public servicesGetCatalogue(String url, Activity a){
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
                                Catalogue c=new Catalogue();
                                JSONObject object = response.getJSONObject(i);
                                c.setIdcatalogue(object.getInt("idcatalogue"));
                                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                                c.setDatecatalogue(format.parse(object.getString("datecatalogue")));
                                c.setNomcatalogue(object.getString("nomcatalogue"));
                                listeCatalogues.add(c);
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

    public LinkedList<Catalogue> getAllCatalogues(){
        return listeCatalogues;
    }

    public Catalogue getCataloguebyId(int id){
        for(Catalogue c:listeCatalogues){
            if(c.getIdcatalogue()==id){
                return c;
            }
        }
        return null;
    }

    public Catalogue getCataloguebyName(String name){
        for(Catalogue c:listeCatalogues){
            if(c.getNomcatalogue()==name){
                return c;
            }
        }
        return null;
    }


    public ArrayList<String> getAllNames(){
        ArrayList<String> liste= new ArrayList<String>();
        for (Catalogue c : listeCatalogues) {
            liste.add(c.getNomcatalogue());
        }
        return liste;
    }

    public ArrayList<String> getOnlyNames(LinkedList<Produit> prods){
        ArrayList<String> liste= new ArrayList<String>();
        for (Catalogue c : listeCatalogues) {
            for(Produit p : prods){
                if(p.getIdcatalogue() == c.getIdcatalogue()){
                    liste.add(c.getNomcatalogue());
                    break;
                }
            }
        }
        return liste;
    }

    public boolean isFull(){
        return listeCatalogues.size()!=0;
    }


}
