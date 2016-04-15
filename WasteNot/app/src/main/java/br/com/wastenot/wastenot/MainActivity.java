package br.com.wastenot.wastenot;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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

import java.util.List;

import static android.view.Gravity.*;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    BDWrapper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.mainadd);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


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
        EditText edts = (EditText) findViewById(R.id.edtSearch);
        final String card = String.valueOf(edts.getText());
        final ListView list = (ListView) findViewById(R.id.list);

        List<ItemListView> itens = new ArrayList<ItemListView>();
        final List<Cards> cardsList = db.getCard(card);
        final List<Cards> cardsSelect = null;
        for (Cards cd : cardsList) {
            itens.add(new ItemListView(cd.getName(), R.drawable.whish));
        }


        final AdapterListView adapter = new AdapterListView(this, itens);
<<<<<<< HEAD
      //  list.requestFocusFromTouch();
        list.setChoiceMode(list.CHOICE_MODE_MULTIPLE);
=======

        list.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
>>>>>>> a712761429745dd8562f226aca6a5f84326c8ad9

               list.setAdapter(adapter);


        //Toast.makeText(getApplicationContext(), "list", Toast.LENGTH_SHORT).show();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (view.isActivated()) {
                       view.setSelected(false);
                       view.setBackgroundColor(0);
                    Toast.makeText(getApplicationContext(), "hi", Toast.LENGTH_SHORT).show();
                } else {
                    Cards card = cardsList.get(position);

                    Intent intent = (new Intent(getApplicationContext(), CardDetail.class));
                    intent.putExtra("cards", card);

                    startActivity(intent);
                }

               // Toast.makeText(getApplicationContext(), "out", Toast.LENGTH_SHORT).show();

            }
        });

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

<<<<<<< HEAD
                // view.setSelected(true);
                list.requestFocusFromTouch(); // IMPORTANT!
                list.setSelected(true);
                //      view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
=======
//                view.setFocusableInTouchMode(true);
                view.setSelected(true);
                view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.itemselect));

                Toast.makeText(getApplicationContext(),"hi", Toast.LENGTH_SHORT).show();
>>>>>>> a712761429745dd8562f226aca6a5f84326c8ad9


                //  Toast.makeText(getApplicationContext(), cardsList.get(position).getId(), Toast.LENGTH_SHORT).show();

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

