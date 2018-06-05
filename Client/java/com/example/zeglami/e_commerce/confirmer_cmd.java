package com.example.zeglami.e_commerce;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saadyousfi.e_commerce.entity.Client;
import com.example.saadyousfi.e_commerce.entity.Commande;
import com.example.saadyousfi.e_commerce.entity.LigneCommande;
import com.example.saadyousfi.e_commerce.entity.Livreur;
import com.example.saadyousfi.e_commerce.services.servicesGetCommandes;
import com.example.saadyousfi.e_commerce.services.servicesGetLivreur;
import com.example.saadyousfi.e_commerce.services.servicesPostCommande;
import com.example.saadyousfi.e_commerce.methods.session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class confirmer_cmd extends Dialog implements android.view.View.OnClickListener {

    public Activity a;
    public Button yes, no;
    public EditText adresse;
    public Spinner spinner;

    ProgressDialog p1;
    public LinkedList<LigneCommande> lignescmd = new LinkedList<LigneCommande>();
    public Client clt;
    public Commande cmd = new Commande();
    public LinkedList<Livreur> livreurs = new LinkedList<>();

    session session ;

    Date currentTime = Calendar.getInstance().getTime();

    servicesPostCommande service;
    servicesGetCommandes scmd;
    servicesGetLivreur slivreur;

    public confirmer_cmd(Activity a) {
        super(a);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_confirmer_cmd);

        adresse = (EditText) findViewById(R.id.adresse);
        spinner = (Spinner) findViewById(R.id.myspinner);

        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);
        yes.setOnClickListener(this);
        no.setOnClickListener(this);

        session = new session(a);
        service = new servicesPostCommande("http://192.168.43.211:8180/ecommerce/webresources/entities.commande","http://localhost:8180/ecommerce/webresources/entities.lignedecommande",a);
        scmd = new servicesGetCommandes("http://192.168.43.211:8180/ecommerce/webresources/entities.commande",a);
        slivreur = new servicesGetLivreur("http://192.168.43.211:8180/ecommerce/webresources/entities.livreur",a);

        new AsyncTask() {
            protected void onPreExecute(){
                p1= ProgressDialog.show(a, "Veuillez patienter", "Livreurs en cours de chargement", true);
            }
            protected void onProgressUpdate(Integer... progress) {

            }
            @Override
            protected Object doInBackground(Object[] objects) {

                return null;
            }
            protected void onPostExecute(Object result) {
                livreurs = slivreur.getAllLivreurs();
                List<String> items = new ArrayList<>();
                for(Livreur liv : livreurs){
                    items.add(liv.getNomlivreur()+" "+liv.getPrenomlivreur());
                }
                if(items == null){
                    items.add("Aucun Livreur");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(a, android.R.layout.simple_spinner_dropdown_item, items);
                spinner.setAdapter(adapter);
                p1.dismiss();
            }
        }.execute();

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_yes:
                new AsyncTask() {
                    protected void onPreExecute(){
                        p1= ProgressDialog.show(a, "Veuillez patienter", "Commande ne cours...", true);
                    }
                    protected void onProgressUpdate(Integer... progress) {

                    }
                    @Override
                    protected Object doInBackground(Object[] objects) {

                        return null;
                    }
                    protected void onPostExecute(Object result) {
                        int idcmd = scmd.idlastCommande(a)+1;
                        String adlivreur = adresse.getText().toString();
                        String thislivreur = spinner.getSelectedItem().toString();
                        int position = spinner.getSelectedItemPosition();
                        int idlivreur = livreurs.get(position).getIdlivreur();

                        cmd.setIdcommande(idcmd);
                        cmd.setAdresselivraison(adlivreur);
                        cmd.setIdclient(clt.getIdclient());
                        cmd.setIdlivreur(idlivreur);
                        cmd.setNomlivraison(clt.getNomclient());
                        cmd.setPrenomlivraison(clt.getPrenomclient());
                        cmd.setTellivraison(clt.getTelclient());
                        cmd.setDatecommande(currentTime);

                        service.postCommande(cmd,a);

                        p1.dismiss();
                    }
                }.execute();

                new AsyncTask() {
                    protected void onPreExecute(){
                        p1= ProgressDialog.show(a, "Veuillez patienter", "Commande ne cours...", true);
                    }
                    protected void onProgressUpdate(Integer... progress) {

                    }
                    @Override
                    protected Object doInBackground(Object[] objects) {

                        return null;
                    }
                    protected void onPostExecute(Object result) {
                        int idcmd = scmd.idlastCommande(a)+1;
                        service.postLignesCommande(idcmd,lignescmd,a);

                        session.resetP();
                        a.finish();
                        p1.dismiss();
                    }
                }.execute();
                toast("Commande Envoyé !");
                dismiss();
                break;
            case R.id.btn_no:
                dismiss();
                break;
            default:
                break;
        }
        dismiss();
    }

    public void toast(String message){
        Toast.makeText(a, message, Toast.LENGTH_SHORT).show();
    }
}