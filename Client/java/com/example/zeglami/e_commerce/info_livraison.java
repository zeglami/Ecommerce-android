package com.example.zeglami.e_commerce;


import android.app.ProgressDialog;
import android.content.Intent;
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
import com.example.saadyousfi.e_commerce.services.servicesGetCommandes;
import com.example.saadyousfi.e_commerce.services.servicesGetLigneDeCommande;
import com.example.saadyousfi.e_commerce.services.servicesGetProduit;

import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class info_livraison extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    String border = "#c2c2d6";
    String header = "#400080";
    String body = "#d1b3ff";
    String app_bg = "#EEEEEE";
    String htext = "#EEEEEE";
    String qte_c = "#800000";

    ProgressDialog p1;
    private com.example.saadyousfi.e_commerce.methods.session session;
    public ScrollView scrollView;
    public TableLayout tableCommandes;
    public LinkedList<Commande> commandes = new LinkedList<Commande>();

    servicesGetCommandes sCMD;
    servicesGetLigneDeCommande sLCMD;
    servicesGetProduit sProd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_livraison);

        scrollView = (ScrollView) findViewById(R.id.myscrollview);
        tableCommandes = (TableLayout) findViewById(R.id.tableCommande);


        session = new session(this);
        if(!session.loggedinLiv()){
            session.logoutLiv(this, authentification.class);
        }

        sCMD = new servicesGetCommandes("http://192.168.43.211:8180/ecommerce/webresources/entities.commande",info_livraison.this);
        sLCMD = new servicesGetLigneDeCommande("http://192.168.43.211:8180/ecommerce/webresources/entities.lignedecommande",info_livraison.this);
        sProd = new servicesGetProduit("http://192.168.43.211:8180/ecommerce/webresources/entities.produit",info_livraison.this);

        Content();


    }

    public void Content(){

        new AsyncTask() {
            protected void onPreExecute(){
                p1= ProgressDialog.show(info_livraison.this, "Veuillez patienter", "Commandes en cours de chargement", true);
            }
            protected void onProgressUpdate(Integer... progress) {

            }
            @Override
            protected Object doInBackground(Object[] objects) {
                return null;
            }
            protected void onPostExecute(Object result) {
                getCommandes();
                p1.dismiss();
            }
        }.execute();

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.liv_menu,menu);
        return true;
    }

    public boolean  onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case R.id.Actualiser:
                finish();
                startActivity(getIntent());
                return true;
            case R.id.Help:
                return true;
            case R.id.Deconnexion:
                session.logoutLiv(this,authentification.class);
                return true;
            default:
                return false;
        }
    }

    public void getCommandes(){

        int idLiv = session.getLivreur().getIdlivreur();
        sCMD.intCommandes(this);
        commandes = sCMD.getMyLivraisons(idLiv,this);

        tableCommandes.getChildCount();
        tableCommandes.getChildAt(0);
        tableCommandes.removeAllViewsInLayout();


        TableRow hRow = new TableRow(info_livraison.this);

        TextView hAdresse = new TextView(info_livraison.this);
        hAdresse.setText("Adresse");
        hAdresse.setTextColor(Color.parseColor(htext));
        hAdresse.setBackgroundColor(Color.parseColor(header));

        TextView hTel = new TextView(info_livraison.this);
        hTel.setText("Tel");
        hTel.setTextColor(Color.parseColor(htext));
        hTel.setBackgroundColor(Color.parseColor(header));

        TextView hDate = new TextView(info_livraison.this);
        hDate.setText("Date");
        hDate.setTextColor(Color.parseColor(htext));
        hDate.setBackgroundColor(Color.parseColor(header));

        TextView blanc2 = new TextView(info_livraison.this);
        blanc2.setBackgroundColor(Color.parseColor(app_bg));

        hRow.addView(hAdresse);
        hRow.addView(hTel);
        hRow.addView(hDate);
        hRow.addView(blanc2);

        tableCommandes.addView(hRow);

        TableRow ligneR = new TableRow(info_livraison.this);

        TextView ligneV1 = new TextView(info_livraison.this);
        ligneV1.setHeight(2);
        ligneV1.setBackgroundColor(Color.parseColor(border));

        TextView ligneV3 = new TextView(info_livraison.this);
        ligneV3.setHeight(2);
        ligneV3.setBackgroundColor(Color.parseColor(border));

        TextView ligneV4 = new TextView(info_livraison.this);
        ligneV4.setHeight(2);
        ligneV4.setBackgroundColor(Color.parseColor(border));

        TextView ligneV7 = new TextView(info_livraison.this);
        ligneV7.setHeight(2);
        ligneV7.setBackgroundColor(Color.parseColor(app_bg));

        ligneR.addView(ligneV1);
        ligneR.addView(ligneV3);
        ligneR.addView(ligneV4);
        ligneR.addView(ligneV7);


        tableCommandes.addView(ligneR);

        if(commandes != null){
            for(Commande cmd : commandes){

                LinkedList<LigneCommande> lcmd = new LinkedList<>();
                LinkedList<LigneCommande> mylcmd = new LinkedList<>();
                LinkedList<Produit> prod = new LinkedList<>();
                int idcmd = cmd.getIdcommande();

                lcmd = sLCMD.getAllLCMD();
                prod = sProd.getAllProducts();

                mylcmd = getMyLCMD(idcmd,lcmd);

                TableRow tableRow = new TableRow(info_livraison.this);

                TextView textAdresse = new TextView(info_livraison.this);
                textAdresse.setBackgroundColor(Color.parseColor(body));
                textAdresse.setText(cmd.getAdresselivraison());
                textAdresse.setGravity(Gravity.CENTER_VERTICAL);
                textAdresse.setWidth(420);
                textAdresse.setHeight(160);

                TextView textTel = new TextView(info_livraison.this);
                textTel.setBackgroundColor(Color.parseColor(body));
                textTel.setText(cmd.getTellivraison());
                textTel.setGravity(Gravity.CENTER_VERTICAL);
                textTel.setWidth(250);
                textTel.setHeight(160);

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                TextView textDate = new TextView(info_livraison.this);
                textDate.setBackgroundColor(Color.parseColor(body));
                textDate.setText(dateFormat.format(cmd.getDatecommande()));
                textDate.setGravity(Gravity.CENTER_VERTICAL);
                textDate.setWidth(250);
                textDate.setHeight(160);


                ImageButton affB = new ImageButton(info_livraison.this);
                affB.setId(cmd.getIdcommande());
                affB.setImageResource(R.drawable.detail);
                affB.setBackgroundResource(0);
                affB.setBackgroundColor(Color.parseColor(app_bg));
                affB.setMaxHeight(160);
                affB.setMaxWidth(120);
                affB.setMinimumWidth(120);
                affB.setMinimumHeight(160);
                affB.setOnClickListener(actionAff);

                tableRow.setGravity(Gravity.CENTER_VERTICAL);

                tableRow.addView(textAdresse);
                tableRow.addView(textTel);
                tableRow.addView(textDate);
                tableRow.addView(affB);

                tableCommandes.addView(tableRow);
            }
        }
    }


    View.OnClickListener actionAff = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for(Commande cmd : commandes) {
                if(view.getId()==cmd.getIdcommande()){
                    livrer_cmd livrer_cmd = new livrer_cmd(info_livraison.this);
                    livrer_cmd.clt = session.getClient();
                    livrer_cmd.cmd = cmd;
                    livrer_cmd.a = info_livraison.this;
                    livrer_cmd.show();
                    toast("Info Commande !");
                }
            }
        }
    };

    public LinkedList<LigneCommande> getMyLCMD(int idCMD,LinkedList<LigneCommande> lcmds){
        LinkedList<LigneCommande> MyLCMD = new LinkedList<>();
        for(LigneCommande lcmd : lcmds){
            if(lcmd.getIdcommande()==idCMD){
                MyLCMD.add(lcmd);
            }
        }
        return MyLCMD;
    }

    public void toast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}