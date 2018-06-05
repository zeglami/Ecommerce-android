package com.example.zeglami.e_commerce;


import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.saadyousfi.e_commerce.entity.Client;
import com.example.saadyousfi.e_commerce.entity.Commande;
import com.example.saadyousfi.e_commerce.entity.Livreur;
import com.example.saadyousfi.e_commerce.methods.session;
import com.example.saadyousfi.e_commerce.services.servicesGetLivreur;
import com.example.saadyousfi.e_commerce.services.servicesPutCommande;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class modifier_cmd extends Dialog implements android.view.View.OnClickListener {

    public Activity a;

    public ImageButton mod,del,back;
    public EditText adresse,tel;
    public Spinner spinner;

    ProgressDialog p1;
    public Client clt;
    public Commande cmd = new Commande();
    public LinkedList<Livreur> livreurs = new LinkedList<>();

    servicesGetLivreur slivreur;
    servicesPutCommande sPutcmd;
    com.example.saadyousfi.e_commerce.methods.session session ;

    Date currentTime = Calendar.getInstance().getTime();

    public modifier_cmd(Activity a) {
        super(a);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_modifier_cmd);

        adresse = (EditText) findViewById(R.id.adresse);
        tel = (EditText) findViewById(R.id.tel);
        spinner = (Spinner) findViewById(R.id.myspinner);

        mod = (ImageButton) findViewById(R.id.btn_mod);
        del = (ImageButton) findViewById(R.id.btn_del);
        back = (ImageButton) findViewById(R.id.btn_back);
        mod.setOnClickListener(this);
        del.setOnClickListener(this);
        back.setOnClickListener(this);

        session = new session(a);
        slivreur = new servicesGetLivreur("http://localhost:8180/ecommerce/webresources/entities.livreur",a);
        sPutcmd = new servicesPutCommande("http://localhost:8180/ecommerce/webresources/entities.commande",a);

        new AsyncTask() {
            protected void onPreExecute(){
                p1= ProgressDialog.show(a, "Veuillez patienter", "Chargement...", true);
            }
            protected void onProgressUpdate(Integer... progress) {

            }
            @Override
            protected Object doInBackground(Object[] objects) {

                return null;
            }
            protected void onPostExecute(Object result) {
                adresse.setText(cmd.getAdresselivraison());
                tel.setText(cmd.getTellivraison());

                livreurs = slivreur.getAllLivreurs();
                List<String> items = new ArrayList<>();
                int i=0;
                int pos=0;
                for(Livreur liv : livreurs){
                    items.add(liv.getNomlivreur()+" "+liv.getPrenomlivreur());
                    if(liv.getIdlivreur()==cmd.getIdlivreur()) pos = i;
                    i++;
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(a, android.R.layout.simple_spinner_dropdown_item, items);
                spinner.setAdapter(adapter);
                spinner.setSelection(pos);
                p1.dismiss();
            }
        }.execute();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_mod:
                new AsyncTask() {
                    protected void onPreExecute(){
                        p1= ProgressDialog.show(a, "Veuillez patienter", "Mise à jour de la commande...", true);
                    }
                    protected void onProgressUpdate(Integer... progress) {

                    }
                    @Override
                    protected Object doInBackground(Object[] objects) {

                        return null;
                    }
                    protected void onPostExecute(Object result) {
                        String adlivreur = adresse.getText().toString();
                        String tellivreur = tel.getText().toString();
                        String liv = spinner.getSelectedItem().toString();
                        //int position = spinner.getSelectedItemPosition();
                        int idlivreur = slivreur.idLivreur(liv);

                        cmd.setAdresselivraison(adlivreur);
                        cmd.setTellivraison(tellivreur);
                        cmd.setIdlivreur(idlivreur);
                        sPutcmd.putCommande(cmd,a);
                        p1.dismiss();
                    }
                }.execute();
                toast("Modification avec succée !");
                dismiss();
                break;
            case R.id.btn_del:
                new AsyncTask() {
                    protected void onPreExecute(){
                        p1= ProgressDialog.show(a, "Veuillez patienter", "Suppression...", true);
                    }
                    protected void onProgressUpdate(Integer... progress) {

                    }
                    @Override
                    protected Object doInBackground(Object[] objects) {

                        return null;
                    }
                    protected void onPostExecute(Object result) {
                        cmd.setEtat("Supprime");
                        sPutcmd.putCommande(cmd,a);
                        p1.dismiss();
                    }
                }.execute();
                toast("Suppression avec succée !");
                dismiss();
                break;
            case R.id.btn_back:
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
