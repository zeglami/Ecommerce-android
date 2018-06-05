package com.example.zeglami.e_commerce;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saadyousfi.e_commerce.entity.Catalogue;
import com.example.saadyousfi.e_commerce.entity.Commande;
import com.example.saadyousfi.e_commerce.entity.Image;
import com.example.saadyousfi.e_commerce.entity.Produit;
import com.example.saadyousfi.e_commerce.methods.session;
import com.example.saadyousfi.e_commerce.services.servicesGetCatalogue;
import com.example.saadyousfi.e_commerce.services.servicesGetImage;
import com.example.saadyousfi.e_commerce.services.servicesGetProduit;


import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;


public class composer_cmd extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    String border = "#c2c2d6";
    String header = "#ff6600";
    String body = "#ffcc66";
    String app_bg = "#EEEEEE";
    String qte = "#0066ff";

    public String username;

    static Spinner spinner;
    ScrollView scrollView;

    ImageButton panier;

    ProgressDialog p1;
    ProgressDialog p2;
    ProgressDialog p3;

    TableLayout tableProduit;

    LinkedList<Catalogue> catalogues;
    LinkedList<Produit> produits;
    Image image;

    Commande commande;

    ArrayList<String> liste;

    servicesGetCatalogue service1;
    servicesGetProduit service2;
    servicesGetImage service3;

    LinkedList<Produit> listP = new LinkedList<>();
    private session session;
    public int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composer_cmd);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinner=(Spinner) findViewById(R.id.myspinner);
        spinner.setOnItemSelectedListener(this);

        panier = (ImageButton) findViewById(R.id.panier);
        panier.setOnClickListener(this);

        scrollView=(ScrollView) findViewById(R.id.myscrollview);
        tableProduit=(TableLayout) findViewById(R.id.tableProduit);

        service1=new servicesGetCatalogue("http://192.168.43.211:8180/ecommerce/webresources/entities.catalogue", composer_cmd.this);
        service2=new servicesGetProduit("http://192.168.43.211:8180/ecommerce/webresources/entities.produit", composer_cmd.this);
        service3=new servicesGetImage("http://192.168.43.211:8180/ecommerce/webresources/entities.image", composer_cmd.this);

        session = new session(this);
        if(!session.loggedin()){
            if(session.loggedinLiv()){
                session.logout(this,info_livraison.class);
            }else {
                session.logout(this, authentification.class);
            }
        }

        Content();


    }

    public void Content(){
        new AsyncTask() {

            LinkedList<Produit> prods = service2.getAllProducts();

            protected void onPreExecute(){
                p1= ProgressDialog.show(composer_cmd.this, "Veuillez patienter", "Catalogues en cours de chargement", true);
            }
            protected void onProgressUpdate(Integer... progress) {

            }
            @Override
            protected Object doInBackground(Object[] objects) {

                return service1.getOnlyNames(prods);
            }
            protected void onPostExecute(Object result) {

                liste=service1.getOnlyNames(prods);
                ArrayAdapter<String> adapter =
                        new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, liste);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                p1.dismiss();
            }
        }.execute();
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        String catalogue=adapterView.getItemAtPosition(i).toString();
        final Catalogue c = service1.getCataloguebyName(catalogue);

        new AsyncTask() {
            protected void onPreExecute(){
                p2= ProgressDialog.show(composer_cmd.this, "Veuillez patienter", "Produits en cours de chargement", true);
            }
            protected void onProgressUpdate(Integer... progress) {

            }
            @Override
            protected Object doInBackground(Object[] objects) {
                int i=0;
                while (service2.getProductsbyCatalogue(c.getIdcatalogue()).size()==0) {
                    i=i+1;
                    if(i%100==0)
                        System.out.print(".");
                }
                System.out.println("bye bye");
                return service2.getProductsbyCatalogue(c.getIdcatalogue());
            }

            protected void onPostExecute(Object result) {
                getContent(c);
                p2.dismiss();
            }
        }.execute();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    View.OnClickListener ajouterP = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for(Produit p : listP) {
                if(view.getId()==p.getIdproduit()){
                    session.addProd(p);
                    Toast.makeText(getApplicationContext(), "Produit Ajouté !", Toast.LENGTH_SHORT).show();
                }
            }

        }
    };

        @Override
    public void onClick(View v) {

        if(v.getId()==R.id.panier){
            Intent MyIntent = new Intent(this, panier.class);
            startActivity(MyIntent);
        }



    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mon_menu,menu);
        return true;
    }

    public boolean  onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.MonCompte:
                return true;
            case R.id.Panier:
                Intent MyIntent = new Intent(this, panier.class);
                startActivity(MyIntent);
                return true;
            case R.id.Help:
                return true;
            case R.id.Deconnexion:
                session.logout(this,authentification.class);
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return false;
        }
    }

    public void getContent(Catalogue c){
        produits = service2.getProductsbyCatalogue(c.getIdcatalogue());

        //Recuperer les produits dans tableProduit
        tableProduit.getChildCount();
        tableProduit.getChildAt(0);
        tableProduit.removeAllViews();

        LinearLayout myTable = new LinearLayout(composer_cmd.this);

        /*TableRow hRow = new TableRow(composer_cmd.this);

        TextView hNom = new TextView(composer_cmd.this);
        hNom.setText("Produit");
        hNom.setWidth(300);
        hNom.setTextColor(Color.parseColor("#3d3d5c"));
        hNom.setBackgroundColor(Color.parseColor(header));

        TextView hPrix = new TextView(composer_cmd.this);
        hPrix.setText("Prix");
        hPrix.setWidth(300);
        hPrix.setTextColor(Color.parseColor("#3d3d5c"));
        hPrix.setBackgroundColor(Color.parseColor(header));

        TextView blanc1 = new TextView(composer_cmd.this);
        blanc1.setBackgroundColor(Color.parseColor(app_bg));

        TextView blanc2 = new TextView(composer_cmd.this);
        blanc2.setBackgroundColor(Color.parseColor(app_bg));

        hRow.addView(blanc1);
        hRow.addView(hNom);
        hRow.addView(hPrix);
        hRow.addView(blanc2);

        tableProduit.addView(hRow);

        TableRow ligneR = new TableRow(composer_cmd.this);

        TextView ligneV1 = new TextView(composer_cmd.this);
        ligneV1.setHeight(2);
        ligneV1.setBackgroundColor(Color.parseColor(border));

        TextView ligneV2 = new TextView(composer_cmd.this);
        ligneV2.setHeight(2);
        ligneV2.setBackgroundColor(Color.parseColor(border));

        TextView ligneV3 = new TextView(composer_cmd.this);
        ligneV3.setHeight(2);
        ligneV3.setBackgroundColor(Color.parseColor(app_bg));

        TextView ligneV4 = new TextView(composer_cmd.this);
        ligneV4.setHeight(2);
        ligneV4.setBackgroundColor(Color.parseColor(app_bg));

        ligneR.addView(ligneV4);
        ligneR.addView(ligneV1);
        ligneR.addView(ligneV2);
        ligneR.addView(ligneV3);


        tableProduit.addView(ligneR);

        */


        new AsyncTask() {

            protected void onPreExecute(){
                p3= ProgressDialog.show(composer_cmd.this, "Veuillez patienter", "Charger les Images !", true);
            }
            protected void onProgressUpdate(Integer... progress) {

            }
            @Override
            protected Object doInBackground(Object[] objects) {

                return null;
            }
            protected void onPostExecute(Object result) {
                listP.clear();
                for(Produit p: produits){
                    image = service3.getFirstImagebyProduct(p.getIdproduit());
                    Bitmap bmp;

                    if(image != null) {
                        bmp = BitmapFactory.decodeByteArray(image.getPhoto(), 0, image.getPhoto().length);
                    }else{
                        bmp = null;
                    }

                    listP.add(p);
                    TableRow tableRow=new TableRow(composer_cmd.this);

                    ImageView imageView = new ImageView(composer_cmd.this);
                    if(image == null){
                        imageView.setImageResource(R.mipmap.noimage_round);
                    }else{
                        imageView.setImageBitmap(bmp);
                    }
                    imageView.setBackgroundColor(Color.parseColor(app_bg));
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    imageView.setAdjustViewBounds(true);
                    imageView.setMaxHeight(250);
                    imageView.setMaxWidth(300);
                    imageView.setMinimumHeight(250);
                    imageView.setMaxWidth(300);

                    TextView textNom=new TextView(composer_cmd.this);
                    textNom.setBackgroundColor(Color.parseColor(body));
                    textNom.setTextSize(15);
                    textNom.setText(p.getNomproduit());
                    textNom.setGravity(Gravity.CENTER_VERTICAL);
                    textNom.setHeight(245);
                    textNom.setWidth(350);

                    TextView textPrix=new TextView(composer_cmd.this);
                    textPrix.setBackgroundColor(Color.parseColor(body));
                    textPrix.setTextSize(15);
                    textPrix.setTextColor(Color.parseColor(qte));
                    textPrix.setText(String.valueOf(p.getPrix()));
                    textPrix.setGravity(Gravity.CENTER_VERTICAL);
                    textPrix.setHeight(245);
                    textPrix.setWidth(270);

                    ImageButton ajpan=new ImageButton(composer_cmd.this);
                    ajpan.setId(p.getIdproduit());
                    ajpan.setImageResource(R.mipmap.aj_panier_round);
                    ajpan.setBackgroundColor(Color.parseColor(app_bg));
                    ajpan.setOnClickListener(ajouterP);

                    tableRow.setGravity(Gravity.CENTER_VERTICAL);

                    tableRow.addView(imageView);
                    tableRow.addView(textNom);
                    tableRow.addView(textPrix);
                    tableRow.addView(ajpan);

                    tableProduit.addView(tableRow);


                }
                p3.dismiss();
            }
        }.execute();

    }

}