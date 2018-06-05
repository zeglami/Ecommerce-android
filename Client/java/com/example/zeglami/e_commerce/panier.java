package com.example.zeglami.e_commerce;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saadyousfi.e_commerce.entity.Commande;
import com.example.saadyousfi.e_commerce.entity.LigneCommande;
import com.example.saadyousfi.e_commerce.entity.Produit;
import com.example.saadyousfi.e_commerce.methods.session;

import java.util.LinkedList;

/**
 * Created by hamidze on 09/04/2018.
 */

public class panier extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    String border = "#c2c2d6";
    String header = "#ff6600";
    String body = "#ffcc66";
    String app_bg = "#EEEEEE";
    String qte = "#0066ff";

    private session session;
    public ImageButton reset,confirmer;
    public ScrollView scrollView;
    public TableLayout tablePanier;

    public LinkedList<Produit> produits;

    Commande cmd = new Commande();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        confirmer = (ImageButton) findViewById(R.id.confirmer);
        confirmer.setOnClickListener(this);
        reset = (ImageButton) findViewById(R.id.reset);
        reset.setOnClickListener(this);

        scrollView = (ScrollView) findViewById(R.id.myscrollview);
        tablePanier = (TableLayout) findViewById(R.id.tablePanier);

        session = new session(this);
        if(!session.loggedin()){
            if(session.loggedinLiv()){
                session.logout(this,info_livraison.class);
            }else {
                session.logout(this, authentification.class);
            }
        }

        getPanier();

    }

    View.OnClickListener delP = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for(Produit p : produits) {
                if(view.getId()==p.getIdproduit()){
                    session.delProd(p);
                    getPanier();
                    toast("Produit Supprimé !");
                }
            }
        }
    };

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.confirmer:
                if(session.getProds()==null){
                    toast("Aucun produit dans le panier !");
                }else {
                    LinkedList<LigneCommande> lignescmd = new LinkedList<>();
                    int i=0;
                    for (Produit p : session.getProds()) {
                        EditText Qte = (EditText) findViewById(p.getIdproduit());
                        if (Qte.getText().toString().equals("") || Qte.getText().toString().equals("0") || Qte.getText().toString().equals(null)) {
                            i=-1;
                        }else{
                            LigneCommande lcmd = new LigneCommande();
                            lcmd.setIdproduit(p.getIdproduit());
                            lcmd.setQtecommu(Integer.parseInt(Qte.getText().toString()));
                            lignescmd.add(lcmd);
                        }
                    }
                    if(i!=-1){
                        confirmer_cmd confirmation = new confirmer_cmd(this);
                        confirmation.lignescmd = lignescmd;
                        confirmation.clt = session.getClient();
                        confirmation.a = this;
                        confirmation.show();

                    }else{
                        toast("Veuillez saisir toutes les quantités !");
                    }
                    //ser.postCommande(serviceClt.getJSONClient(20,this),serviceLiv.getJSONLivreur(1,this),this);
                }
                break;
            case R.id.reset:
                if(session.getProds()==null){
                    toast("Panier déja vide !");
                }else {
                    session.resetP();
                    getPanier();
                    toast("Reset Panier !");
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
                toast("Vous étes déja sur le Panier !");
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

    public void getPanier(){
        produits = session.getProds();

        tablePanier.getChildCount();
        tablePanier.getChildAt(0);
        tablePanier.removeAllViews();

        TableRow hRow = new TableRow(panier.this);

        TextView hNom = new TextView(panier.this);
        hNom.setText("Produit");
        hNom.setWidth(300);
        hNom.setTextColor(Color.parseColor("#3d3d5c"));
        hNom.setBackgroundColor(Color.parseColor(header));

        TextView hPrix = new TextView(panier.this);
        hPrix.setText("Prix");
        hPrix.setWidth(300);
        hPrix.setTextColor(Color.parseColor("#3d3d5c"));
        hPrix.setBackgroundColor(Color.parseColor(header));

        TextView hQte = new TextView(panier.this);
        hQte.setWidth(150);
        hQte.setText("Qté");
        hQte.setTextColor(Color.parseColor("#b30000"));
        hQte.setBackgroundColor(Color.parseColor(header));

        TextView blanc = new TextView(panier.this);
        blanc.setBackgroundColor(Color.parseColor(app_bg));

        hRow.addView(hNom);
        hRow.addView(hPrix);
        hRow.addView(hQte);
        hRow.addView(blanc);

        tablePanier.addView(hRow);

        TableRow ligneR = new TableRow(panier.this);

        TextView ligneV1 = new TextView(panier.this);
        ligneV1.setHeight(2);
        ligneV1.setBackgroundColor(Color.parseColor(border));

        TextView ligneV2 = new TextView(panier.this);
        ligneV2.setHeight(2);
        ligneV2.setBackgroundColor(Color.parseColor(border));

        TextView ligneV3 = new TextView(panier.this);
        ligneV3.setHeight(2);
        ligneV3.setBackgroundColor(Color.parseColor(border));

        TextView ligneV4 = new TextView(panier.this);
        ligneV4.setHeight(2);
        ligneV4.setBackgroundColor(Color.parseColor(app_bg));

        ligneR.addView(ligneV1);
        ligneR.addView(ligneV2);
        ligneR.addView(ligneV3);
        ligneR.addView(ligneV4);


        tablePanier.addView(ligneR);

        if(produits != null){
            for(Produit p : produits){

                TableRow tableRow=new TableRow(panier.this);

                TextView textNom=new TextView(panier.this);
                textNom.setBackgroundColor(Color.parseColor(body));
                textNom.setText(p.getNomproduit());
                textNom.setGravity(Gravity.CENTER_VERTICAL);
                textNom.setTextSize(14);
                textNom.setHeight(200);

                TextView textPrix=new TextView(panier.this);
                textPrix.setText(String.valueOf(p.getPrix())+"DH");
                textPrix.setBackgroundColor(Color.parseColor(body));
                textPrix.setGravity(Gravity.CENTER_VERTICAL);
                textPrix.setTextSize(16);
                textPrix.setTextColor(Color.parseColor(qte));
                textPrix.setHeight(200);

                EditText inputQte = new EditText(panier.this);
                inputQte.setId(p.getIdproduit());
                inputQte.setBackgroundColor(Color.parseColor(body));
                inputQte.setInputType(InputType.TYPE_CLASS_NUMBER);
                inputQte.setGravity(Gravity.CENTER_VERTICAL);
                inputQte.setHeight(200);
                inputQte.setHint("0");


                ImageButton delB = new ImageButton(panier.this);
                delB.setId(p.getIdproduit());
                delB.setImageResource(R.mipmap.del_round);
                delB.setBackgroundResource(0);
                delB.setBackgroundColor(Color.parseColor(app_bg));
                delB.setOnClickListener(delP);

                tableRow.setGravity(Gravity.CENTER_VERTICAL);

                tableRow.addView(textNom);
                tableRow.addView(textPrix);
                tableRow.addView(inputQte);
                tableRow.addView(delB);

                tablePanier.addView(tableRow);
            }
        }
    }

    public void toast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
