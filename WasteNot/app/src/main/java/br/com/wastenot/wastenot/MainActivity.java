package br.com.wastenot.wastenot;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Teste db

        BDWrapper db = new BDWrapper(this);

        db.addCards(new Cards("normal","Sen Triplets","{2}{W}{U}{B}","5","White,blue,black","Legendary Artifact Creature — Human Wizard","Legendary","Artifact,Creature","Human Wizard","Mythic Rare","At the beginning of your upkeep, choose target opponent This turn, that player can't cast spells or activate abilities and plays with his or her hand revealed You may play cards from that player's hand this turn.","They are the masters of your mind","Greg Staples","109","3","3","","",""));

        List<Cards> cardsList = db.getAllCard();

        for (Cards cd : cardsList){
            Toast.makeText(getApplicationContext(), cd.getName(), Toast.LENGTH_SHORT).show();
        }



    }


    public void search(View view){


        ListView list = (ListView) findViewById(R.id.list);

        List<ItemListView> itens = new ArrayList<ItemListView>();

        itens.add(new ItemListView("item 1",R.drawable.whish));
        itens.add(new ItemListView("item 2",R.drawable.havelist));
        AdapterListView adapter = new AdapterListView(this,itens);
        list.setAdapter(adapter);

        Toast.makeText(getApplicationContext(), "list", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_decks) {
            Intent intent = (new Intent(this,MyDeckActivity.class));
            startActivity(intent);

        } else if (id == R.id.nav_have_list) {
            Intent intent = (new Intent(this,HaveListActivity.class));
            startActivity(intent);

        } else if (id == R.id.nav_wanted_list) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
