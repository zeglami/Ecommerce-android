package com.example.zeglami.e_commerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saadyousfi.e_commerce.methods.session;
import com.example.saadyousfi.e_commerce.services.servicesGetClient;
import com.example.saadyousfi.e_commerce.services.servicesGetLivreur;


public class authentification extends AppCompatActivity implements View.OnClickListener {

    private Button valider,creer,reinitialiser;

    private EditText utilisateur,passe;
    private servicesGetClient s;
    private servicesGetLivreur sliv;

    private com.example.saadyousfi.e_commerce.methods.session session;
    servicesGetClient service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentification);

        s=new servicesGetClient("http://192.168.43.211:8180/ecommerce/webresources/entities.client", this);
        sliv=new servicesGetLivreur("http://192.168.43.211:8180/ecommerce/webresources/entities.livreur", this);

        valider=(Button) findViewById(R.id.valider);
        valider.setOnClickListener(this);

        creer=(Button) findViewById(R.id.creer);
        creer.setOnClickListener(this);

        reinitialiser=(Button) findViewById(R.id.reinitialiser);
        reinitialiser.setOnClickListener(this);

        utilisateur=(EditText) findViewById(R.id.utilisateur);
        passe=(EditText) findViewById(R.id.mdp);

        session = new session(this);

        service=new servicesGetClient("http://192.168.43.211:8180/ecommerce/webresources/entities.client", authentification.this);

        if(session.loggedin()){
            startActivity(new Intent(this,accueil.class));
            finish();
        }else if(session.loggedinLiv()){
            startActivity(new Intent(this,info_livraison.class));
            finish();
        }

    }

    public void onClick(View v){
        switch(v.getId()) {
            case R.id.valider:
                login();
                break;
            case R.id.creer:
                Intent intent1 = new Intent(this, creer_compte.class);
                startActivity(intent1);
                break;
            case R.id.reinitialiser:
                Intent intent2 = new Intent(this, initialiser.class);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }

    public void login(){
        String username = utilisateur.getText().toString();
        String mdp = passe.getText().toString();
        if(s.isClient(username,mdp)){
            toast("maintenat vous avez connecté");
            session.setLoggedin(true);
            Intent MyIntent = new Intent(this, accueil.class);
            session.setClient(service.thisClient(username));
            startActivity(MyIntent);
            finish();
        }else if(sliv.isLivreur(username,mdp)) {
            toast("maintenat vous avez connecté");
            session.setLoggedinLiv(true);
            Intent MyIntent = new Intent(this, info_livraison.class);
            session.setLivreur(sliv.thisLivreur(username));
            startActivity(MyIntent);
            finish();
        }else{
            toast("Usernameou Password Erroné");
        }
    }

    public void toast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


}
