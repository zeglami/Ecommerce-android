package com.example.zeglami.e_commerce;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.saadyousfi.e_commerce.entity.Client;
import com.example.saadyousfi.e_commerce.methods.parametres;
import com.example.saadyousfi.e_commerce.services.servicesPostClient;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class creer_compte extends AppCompatActivity  implements View.OnClickListener {

    Button ok;
    Button cancel;
    EditText nom;
    EditText prenom;
    EditText adresse;
    EditText email;
    EditText login;
    EditText pass;
    EditText gsm;
    EditText date;
    servicesPostClient s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_compte);

        s=new servicesPostClient("http://192.168.43.211:8180/ecommerce/webresources/entities.client", this);

        ok=(Button) findViewById(R.id.ok);
        ok.setOnClickListener(this);

        cancel=(Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(this);

        nom=(EditText) findViewById(R.id.nom);
        prenom=(EditText) findViewById(R.id.prenom);
        adresse=(EditText) findViewById(R.id.adresse);
        email=(EditText) findViewById(R.id.email);
        login=(EditText) findViewById(R.id.login);
        pass=(EditText) findViewById(R.id.passCreerCpt);
        gsm=(EditText) findViewById(R.id.gsm);
        date=(EditText) findViewById(R.id.date_naiss);

    }

    public void onClick(View v){
        if(v.getId()==R.id.ok) {
            Client c = new Client();
            c.setNomclient(nom.getText().toString());
            c.setPrenomclient(prenom.getText().toString());
            c.setAdressecli(adresse.getText().toString());
            c.setEmail(email.getText().toString());
            c.setLoginclient(login.getText().toString());
            c.setMdpclient(pass.getText().toString());
            c.setTelclient(gsm.getText().toString());
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            try {
                System.out.println("*************"+format.parse(date.getText().toString()));
                c.setDatenaissance(format.parse(date.getText().toString()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            s.postClient(c,this);

            Intent email = new Intent();
            email.setData(Uri.parse("mailto:"));
            email.setAction(Intent.ACTION_SEND);
            String[] recipients={"shahin5@hotmail.fr"};
            email.putExtra(Intent.EXTRA_EMAIL, recipients);
            email.putExtra(Intent.EXTRA_SUBJECT, "Creation du compte");
            email.putExtra(Intent.EXTRA_TEXT,"Votre compte est crée avec succes");
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, "Veuillez choisir une application..."));

        }

        else if(v.getId()==R.id.cancel) {
            this.finish();
        }
        // verifier les autres evenements
    }
}
