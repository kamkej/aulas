package br.com.wastenot.wastenot;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MyDeckActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    BDWrapper db;
    ListView list;
    AdapterDeckView adapter;
    List<Deck> deckList;
    List<ItemDeckView> itens = new ArrayList<ItemDeckView>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_deck);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

      FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab01);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLangDialog();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        list = (ListView) findViewById(R.id.list);
        db = new BDWrapper(this);
        getCards();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_decks) {
            Intent intent = (new Intent(this,MyDeckActivity.class));
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_have_list) {
            Intent intent = (new Intent(this,HaveListActivity.class));
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_wanted_list) {
            Intent intent = (new Intent(this,wantedList.class));
            startActivity(intent);
            finish();
        }else if (id == R.id.nav_home){
            Intent intent = (new Intent(this,MainActivity.class));
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void showChangeLangDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText edt = (EditText) dialogView.findViewById(R.id.edt_deck);

        dialogBuilder.setTitle("New Deck");
        dialogBuilder.setMessage("Enter Deck Name  below");
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                db.addDecks(edt.getText().toString());
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }
    protected void getCards(){
        adapter = new AdapterDeckView(this, updateDeckList());
        list.setAdapter(adapter);
    }
    protected List<ItemDeckView> updateDeckList(){
        deckList = db.getAllDecks();
        int img = img = R.drawable.ic_home;;
        for (Deck dc : deckList) {


            itens.add(new ItemDeckView(dc.getDeckName(), img,String.valueOf(dc.getQtd())));
        }
        return itens;

    }
}
