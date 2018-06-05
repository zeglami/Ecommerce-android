package com.example.zeglami.e_commerce;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saadyousfi.e_commerce.entity.Client;
import com.example.saadyousfi.e_commerce.entity.Commande;
import com.example.saadyousfi.e_commerce.entity.Image;
import com.example.saadyousfi.e_commerce.entity.LigneCommande;
import com.example.saadyousfi.e_commerce.entity.Livreur;
import com.example.saadyousfi.e_commerce.entity.Produit;
import com.example.saadyousfi.e_commerce.methods.session;
import com.example.saadyousfi.e_commerce.services.servicesGetImage;
import com.example.saadyousfi.e_commerce.services.servicesGetLigneDeCommande;
import com.example.saadyousfi.e_commerce.services.servicesGetLivreur;
import com.example.saadyousfi.e_commerce.services.servicesGetProduit;
import com.example.saadyousfi.e_commerce.services.servicesPutCommande;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;



public class livrer_cmd extends Dialog implements android.view.View.OnClickListener {
    String qte_c = "#0066ff";

    public Activity a;

    public ImageButton annuler,livrer,back;
    public TextView adr,dest,date_cmd,etat,teleph;
    public TableLayout tableProduits;
    Image image;

    ProgressDialog p1;
    ProgressDialog p2;
    ProgressDialog p3;

    public Client clt;
    public Commande cmd = new Commande();
    public Livreur livreur = new Livreur();
    public LinkedList<Produit> prods = new LinkedList<>();
    public LinkedList<LigneCommande> lcmds = new LinkedList<>();

    servicesGetLigneDeCommande slcmds;
    servicesGetLivreur slivreur;
    servicesGetProduit sproduit;
    servicesGetImage simage;
    servicesPutCommande sPutcmd;
    com.example.saadyousfi.e_commerce.methods.session session ;

    public livrer_cmd(Activity a) {
        super(a);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_livrer_cmd);

        adr = (TextView) findViewById(R.id.adresse);
        dest = (TextView) findViewById(R.id.destination);
        date_cmd = (TextView) findViewById(R.id.date);
        etat = (TextView) findViewById(R.id.etat);
        teleph = (TextView) findViewById(R.id.teleph);

        annuler = (ImageButton) findViewById(R.id.btn_annuler);
        livrer = (ImageButton) findViewById(R.id.btn_livrer);
        back = (ImageButton) findViewById(R.id.btn_back);

        annuler.setOnClickListener(this);
        livrer.setOnClickListener(this);
        back.setOnClickListener(this);

        tableProduits = (TableLayout) findViewById(R.id.tableProduits);

        session = new session(a);
        slcmds = new servicesGetLigneDeCommande("http://localhost:8180/ecommerce/webresources/entities.lignedecommande",a);
        slivreur = new servicesGetLivreur("http://localhost:8180/ecommerce/webresources/entities.livreur",a);
        sproduit = new servicesGetProduit("http://localhost:8180/ecommerce/webresources/entities.produit",a);
        simage = new servicesGetImage("http://localhost:8180/ecommerce/webresources/entities.image", a);
        sPutcmd = new servicesPutCommande("http://localhost:8180/ecommerce/webresources/entities.commande",a);

        Content();

    }


    public void Content(){

        tableProduits.removeAllViews();
        new AsyncTask() {
            protected void onPreExecute() {
                p1 = ProgressDialog.show(a, "Veuillez patienter", "Chargement...", true);
            }

            protected void onProgressUpdate(Integer... progress) {

            }

            @Override
            protected Object doInBackground(Object[] objects) {

                return null;
            }

            protected void onPostExecute(Object result) {
                for (LigneCommande lcmd : slcmds.getMyLCMD(cmd.getIdcommande())) {
                    prods.add(sproduit.getProdbyId(lcmd.getIdproduit()));
                    lcmds.add(lcmd);
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

                adr.setText(cmd.getAdresselivraison());
                dest.setText(cmd.getNomlivraison() + " " + cmd.getPrenomlivraison());
                date_cmd.setText(dateFormat.format(cmd.getDatecommande()));
                teleph.setText(cmd.getTellivraison());
                etat.setText(cmd.getEtat());
                if (cmd.getEtat().equals("En Attente"))
                    etat.setTextColor(Color.parseColor("#ffcc00"));
                else if (cmd.getEtat().equals("Livre"))
                    etat.setTextColor(Color.parseColor("#006600"));
                else if (cmd.getEtat().equals("Annule"))
                    etat.setTextColor(Color.parseColor("#3399ff"));
                else if (cmd.getEtat().equals("Supprime"))
                    etat.setTextColor(Color.parseColor("#cc0000"));
                else etat.setTextColor(Color.parseColor("#737373"));
                p1.dismiss();
            }
        }.execute();


        new AsyncTask() {
            protected void onPreExecute() {
                p2 = ProgressDialog.show(a, "Veuillez patienter", "Chargement des lignes de commande !", true);
            }

            protected void onProgressUpdate(Integer... progress) {

            }

            @Override
            protected Object doInBackground(Object[] objects) {

                return null;
            }

            protected void onPostExecute(Object result) {
                getContent();
                p2.dismiss();
            }
        }.execute();
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_livrer:
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
                        cmd.setEtat("Livre");
                        sPutcmd.putCommande(cmd,a);
                        p1.dismiss();
                    }
                }.execute();
                toast("Commande Validée !");
                dismiss();
                break;
            case R.id.btn_annuler:
                new AsyncTask() {
                    protected void onPreExecute(){
                        p1= ProgressDialog.show(a, "Veuillez patienter", "Annulation de la commande...", true);
                    }
                    protected void onProgressUpdate(Integer... progress) {

                    }
                    @Override
                    protected Object doInBackground(Object[] objects) {

                        return null;
                    }
                    protected void onPostExecute(Object result) {
                        cmd.setEtat("Annule");
                        sPutcmd.putCommande(cmd,a);
                        p1.dismiss();
                    }
                }.execute();
                toast("Commande Annulée !");
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


    public void getContent(){
        new AsyncTask() {

            protected void onPreExecute(){
                p3= ProgressDialog.show(a, "Veuillez patienter", "Charger les Images !", true);
            }
            protected void onProgressUpdate(Integer... progress) {

            }
            @Override
            protected Object doInBackground(Object[] objects) {

                return null;
            }
            protected void onPostExecute(Object result) {
                tableProduits.getChildCount();
                tableProduits.getChildAt(0);
                tableProduits.removeAllViews();

                for(Produit p: prods){
                    image = simage.getFirstImagebyProduct(p.getIdproduit());
                    Bitmap bmp;

                    if(image != null) {
                        bmp = BitmapFactory.decodeByteArray(image.getPhoto(), 0, image.getPhoto().length);
                    }else{
                        bmp = null;
                    }

                    TableRow tableRow=new TableRow(a);

                    ImageView imageView = new ImageView(a);
                    if(image == null){
                        imageView.setImageResource(R.mipmap.noimage_round);
                    }else{
                        imageView.setImageBitmap(bmp);
                    }
                    imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    imageView.setAdjustViewBounds(true);
                    imageView.setMaxHeight(250);
                    imageView.setMaxWidth(300);
                    imageView.setMinimumHeight(250);
                    imageView.setMaxWidth(300);

                    TextView textNom=new TextView(a);
                    textNom.setTextSize(16);
                    textNom.setText(p.getNomproduit());
                    textNom.setGravity(Gravity.CENTER_VERTICAL);
                    textNom.setHeight(245);
                    textNom.setWidth(350);

                    TextView textQte = new TextView(a);
                    for(LigneCommande lcmd : lcmds) {
                        if(lcmd.getIdproduit() == p.getIdproduit()) {
                            textQte.setTextSize(20);
                            textQte.setTextColor(Color.parseColor(qte_c));
                            textQte.setText(String.valueOf(lcmd.getQtecommu())+" Unité");
                            textQte.setGravity(Gravity.CENTER_VERTICAL);
                            textQte.setHeight(245);
                            textQte.setWidth(220);
                        }
                    }
                    tableRow.setGravity(Gravity.CENTER_VERTICAL);

                    tableRow.addView(imageView);
                    tableRow.addView(textNom);
                    tableRow.addView(textQte);

                    tableProduits.addView(tableRow);
                }
                p3.dismiss();
            }
        }.execute();
    }

    public void toast(String message){
        Toast.makeText(a, message, Toast.LENGTH_SHORT).show();
    }

}