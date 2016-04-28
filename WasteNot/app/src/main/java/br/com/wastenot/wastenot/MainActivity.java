package br.com.wastenot.wastenot;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static android.view.Gravity.*;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    BDWrapper db;
    MenuItem dtos;
    ListView list;
    List<String> cardsSelect = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        list = (ListView) findViewById(R.id.list);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        db = new BDWrapper(this);


    }


    public void search(final View view) {
        dtos.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
        dtos.setVisible(false);
        EditText edts = (EditText) findViewById(R.id.edtSearch);
        final String card = String.valueOf(edts.getText());

        List<ItemListView> itens = new ArrayList<ItemListView>();
        final List<Cards> cardsList = db.getCard(card);
        for (Cards cd : cardsList) {
            itens.add(new ItemListView(cd.getName(), R.drawable.whish));
        }


        final AdapterListView adapter = new AdapterListView(this, itens);
            list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                   if (cardsSelect.contains(cardsList.get(position).getId())) {
                       cardsSelect.remove(cardsSelect.indexOf(cardsList.get(position).getId()));
                        view.setBackgroundColor(0);
                       if(cardsSelect.isEmpty()){
                           dtos.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                           dtos.setVisible(false);

                       }
                    } else {
                        Cards card = cardsList.get(position);
                        Intent intent = (new Intent(getApplicationContext(), CardDetail.class));
                        intent.putExtra("cards", card);
                        startActivity(intent);
                    }
                }
        });
        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dtos.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
                dtos.setVisible(true);

                cardsSelect.add(cardsList.get(position).getId());

                view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.itemselect));

                return true;
            }
        });



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
      dtos = menu.findItem(R.id.action_favorite);
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
            Intent intent = new Intent(this,SettingActivity.class);
            startActivity(intent);
        } else if(id == R.id.action_favorite){
            for (String idc  : cardsSelect) {
                Log.d("id", idc);
            }
            cardsSelect.removeAll(cardsSelect);


            Toast.makeText(this,"Item add com sucesso",Toast.LENGTH_LONG).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
     //       menu.findItem(R.id.action_favorite).setVisible(true);
        return super.onPrepareOptionsMenu(menu);

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

