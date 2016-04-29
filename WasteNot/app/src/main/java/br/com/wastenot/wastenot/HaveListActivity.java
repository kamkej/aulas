package br.com.wastenot.wastenot;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class HaveListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    BDWrapper db;
    ListView list;
    List<Cards> cardsList;
    MenuItem have,wanted;
    List<String> cardsSelect = new ArrayList<String>();
    List<ItemListView> itens = new ArrayList<ItemListView>();
    AdapterListView adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_have_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        list = (ListView) findViewById(R.id.list);
        db = new BDWrapper(this);
        cardsList = db.getHaveCard();
        for (Cards cd : cardsList) {
            itens.add(new ItemListView(cd.getName(), R.drawable.whish));
        }

        adapter = new AdapterListView(this, itens);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (cardsSelect.contains(cardsList.get(position).getId())) {
                    cardsSelect.remove(cardsSelect.indexOf(cardsList.get(position).getId()));
                    view.setBackgroundColor(0);
                    if (cardsSelect.isEmpty()) {
                        have.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                        have.setVisible(false);
                        wanted.setShowAsAction(MenuItem.SHOW_AS_ACTION_NEVER);
                        wanted.setVisible(false);

                    }
                } else {
                    Cards card = cardsList.get(position);
                    Intent intent = (new Intent(getApplicationContext(), CardDetail.class));
                    intent.putExtra("cards", card);
                    startActivity(intent);
                }
            }
        });

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
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
