package com.example.zeglami.e_commerce;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.example.saadyousfi.e_commerce.services.servicesGetCommandes;
import com.example.saadyousfi.e_commerce.services.servicesGetLigneDeCommande;
import com.example.saadyousfi.e_commerce.services.servicesGetProduit;

import java.text.SimpleDateFormat;
import java.util.LinkedList;


public class etat_cmd extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

        String border = "#c2c2d6";
        String header = "#400080";
        String body = "#d1b3ff";
        String app_bg = "#EEEEEE";
        String htext = "#EEEEEE";
        String qte_c = "#800000";

        ProgressDialog p1;
        private session session;
        public ScrollView scrollView;
        public TableLayout tableCommandes;
        public LinkedList<Commande> commandes = new LinkedList<Commande>();

        servicesGetCommandes sCMD;
        servicesGetLigneDeCommande sLCMD;
        servicesGetProduit sProd;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etat_cmd);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        scrollView = (ScrollView) findViewById(R.id.myscrollview);
        tableCommandes = (TableLayout) findViewById(R.id.tableCommande);

        session = new session(this);
        if(!session.loggedin()){
            if(session.loggedinLiv()){
                session.logout(this,info_livraison.class);
            }else {
                session.logout(this, authentification.class);
            }
        }

        sCMD = new servicesGetCommandes("http://192.168.43.211:8180/ecommerce/webresources/entities.commande",etat_cmd.this);
        sLCMD = new servicesGetLigneDeCommande("http://192.168.43.211:8180/ecommerce/webresources/entities.lignedecommande",etat_cmd.this);
        sProd = new servicesGetProduit("http://192.168.43.211:8180/ecommerce/webresources/entities.produit",etat_cmd.this);

        new AsyncTask() {
            protected void onPreExecute(){
                p1= ProgressDialog.show(etat_cmd.this, "Veuillez patienter", "Commandes en cours de chargement", true);
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


    public void getCommandes(){
        int idCli = session.getClient().getIdclient();
        sCMD.intCommandes(this);
        commandes = sCMD.getMyCommandes(idCli,this);

        tableCommandes.getChildCount();
        tableCommandes.getChildAt(0);
        tableCommandes.removeAllViews();

        TableRow hRow = new TableRow(etat_cmd.this);

        TextView hAdresse = new TextView(etat_cmd.this);
        hAdresse.setText("Adresse");
        hAdresse.setTextColor(Color.parseColor(htext));
        hAdresse.setBackgroundColor(Color.parseColor(header));

        /*TextView hLivreur = new TextView(etat_cmd.this);
        hLivreur.setText("Livreur");
        hLivreur.setTextColor(Color.parseColor("#3d3d5c"));
        hLivreur.setBackgroundColor(Color.parseColor(header));*/

        /*TextView hTel = new TextView(etat_cmd.this);
        hTel.setText("Tel");
        hTel.setTextColor(Color.parseColor("#3d3d5c"));
        hTel.setBackgroundColor(Color.parseColor(header));*/

        TextView hDate = new TextView(etat_cmd.this);
        hDate.setText("Date");
        hDate.setTextColor(Color.parseColor(htext));
        hDate.setBackgroundColor(Color.parseColor(header));

        TextView hTotal = new TextView(etat_cmd.this);
        hTotal.setText("Total");
        hTotal.setTextColor(Color.parseColor(htext));
        hTotal.setBackgroundColor(Color.parseColor(header));

        TextView blanc1 = new TextView(etat_cmd.this);
        blanc1.setBackgroundColor(Color.parseColor(app_bg));
        TextView blanc2 = new TextView(etat_cmd.this);
        blanc2.setBackgroundColor(Color.parseColor(app_bg));

        hRow.addView(hAdresse);

        hRow.addView(hDate);
        hRow.addView(hTotal);
        hRow.addView(blanc1);
        hRow.addView(blanc2);

        tableCommandes.addView(hRow);

        TableRow ligneR = new TableRow(etat_cmd.this);

        TextView ligneV1 = new TextView(etat_cmd.this);
        ligneV1.setHeight(2);
        ligneV1.setBackgroundColor(Color.parseColor(border));



        TextView ligneV4 = new TextView(etat_cmd.this);
        ligneV4.setHeight(2);
        ligneV4.setBackgroundColor(Color.parseColor(border));

        TextView ligneV5 = new TextView(etat_cmd.this);
        ligneV5.setHeight(2);
        ligneV5.setBackgroundColor(Color.parseColor(border));

        TextView ligneV6 = new TextView(etat_cmd.this);
        ligneV6.setHeight(2);
        ligneV6.setBackgroundColor(Color.parseColor(app_bg));

        TextView ligneV7 = new TextView(etat_cmd.this);
        ligneV7.setHeight(2);
        ligneV7.setBackgroundColor(Color.parseColor(app_bg));

        ligneR.addView(ligneV1);

        ligneR.addView(ligneV4);
        ligneR.addView(ligneV5);
        ligneR.addView(ligneV6);
        ligneR.addView(ligneV7);


        tableCommandes.addView(ligneR);

        if(commandes != null){
            for(Commande cmd : commandes){

                LinkedList<LigneCommande> lcmd = new LinkedList<>();
                LinkedList<LigneCommande> mylcmd = new LinkedList<>();
                LinkedList<Produit> prod = new LinkedList<>();
                int qte;
                int idcmd = cmd.getIdcommande();

                lcmd = sLCMD.getAllLCMD();
                prod = sProd.getAllProducts();

                mylcmd = getMyLCMD(idcmd,lcmd);
                qte = getTotal(mylcmd,prod);

                TableRow tableRow = new TableRow(etat_cmd.this);

                TextView textAdresse = new TextView(etat_cmd.this);
                textAdresse.setBackgroundColor(Color.parseColor(body));
                textAdresse.setText(cmd.getAdresselivraison());
                textAdresse.setGravity(Gravity.CENTER_VERTICAL);
                textAdresse.setWidth(420);
                textAdresse.setHeight(160);



                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                TextView textDate = new TextView(etat_cmd.this);
                textDate.setBackgroundColor(Color.parseColor(body));
                textDate.setText(dateFormat.format(cmd.getDatecommande()));
                textDate.setGravity(Gravity.CENTER_VERTICAL);
                textDate.setWidth(250);
                textDate.setHeight(160);


                TextView textTotal = new TextView(etat_cmd.this);
                textTotal.setBackgroundColor(Color.parseColor(body));
                textTotal.setTextSize(20);
                textTotal.setTextColor(Color.parseColor(qte_c));
                textTotal.setText(Integer.toString(qte)+"DH");
                textTotal.setGravity(Gravity.CENTER_VERTICAL);
                textTotal.setHeight(160);


                ImageButton affB = new ImageButton(etat_cmd.this);
                affB.setId(cmd.getIdcommande());
                affB.setImageResource(R.drawable.detail);
                affB.setBackgroundResource(0);
                affB.setBackgroundColor(Color.parseColor(app_bg));
                affB.setMaxHeight(160);
                affB.setMaxWidth(100);
                affB.setMinimumHeight(160);
                affB.setOnClickListener(actionAff);

                ImageButton editB = new ImageButton(etat_cmd.this);
                editB.setId(cmd.getIdcommande());
                editB.setImageResource(R.drawable.editer);
                editB.setBackgroundResource(0);
                editB.setBackgroundColor(Color.parseColor(app_bg));
                editB.setMaxWidth(100);
                editB.setMaxHeight(160);
                editB.setMinimumHeight(160);
                editB.setOnClickListener(actionEdit);

                tableRow.setGravity(Gravity.CENTER_VERTICAL);

                tableRow.addView(textAdresse);
                //tableRow.addView(textLivreur);
                //tableRow.addView(textTel);
                tableRow.addView(textDate);
                tableRow.addView(textTotal);
                tableRow.addView(affB);
                tableRow.addView(editB);

                tableCommandes.addView(tableRow);
            }
        }
    }

    View.OnClickListener actionAff = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for(Commande cmd : commandes) {
                if(view.getId()==cmd.getIdcommande()){
                    detail_cmd detail_cmd = new detail_cmd(etat_cmd.this);
                    detail_cmd.clt = session.getClient();
                    detail_cmd.cmd = cmd;
                    detail_cmd.a = etat_cmd.this;
                    detail_cmd.show();
                    toast("Info Commande !");
                }
            }
        }
    };

    View.OnClickListener actionEdit = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for(Commande cmd : commandes) {
                if(view.getId()==cmd.getIdcommande()){
                    modifier_cmd modifier_cmd = new modifier_cmd(etat_cmd.this);
                    modifier_cmd.clt = session.getClient();
                    modifier_cmd.cmd = cmd;
                    modifier_cmd.a = etat_cmd.this;
                    modifier_cmd.show();
                    toast("Editer Commande !");
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

    public int getTotal(LinkedList<LigneCommande> LCMD, LinkedList<Produit> prod){
        int total = 0;
        for(LigneCommande lcmd : LCMD){
            for(Produit p :prod){
                if(lcmd.getIdproduit() == p.getIdproduit()){
                    total = total + lcmd.getQtecommu()*p.getPrix();
                    break;
                }
            }
        }
        return total;
    }

    public void toast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}