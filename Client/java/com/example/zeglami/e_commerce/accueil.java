package com.example.zeglami.e_commerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.saadyousfi.e_commerce.methods.session;

/**
 * Created by hamidze on 06/02/2018.
 */

public class accueil extends AppCompatActivity implements View.OnClickListener {

    Button commander;
    Button consulter;
    String username;

    private com.example.saadyousfi.e_commerce.methods.session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil);

        commander=(Button)findViewById(R.id.commander);
        consulter=(Button)findViewById(R.id.consulter);

        commander.setOnClickListener(this);
        consulter.setOnClickListener(this);

        session = new session(this);
        if(!session.loggedin()){
            if(session.loggedinLiv()){
               // session.logout(this,info_livraison.class);
            }else {
                session.logout(this, authentification.class);
            }
        }

        username = session.getUsername();
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.commander){
            Intent MyIntent = new Intent(this,composer_cmd.class);
            startActivity(MyIntent);
        }
        else if (view.getId()==R.id.consulter){
            Intent MyIntent = new Intent(this,etat_cmd.class);
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
            default:
                return false;
        }
    }
}
