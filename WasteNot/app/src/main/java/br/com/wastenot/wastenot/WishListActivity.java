package br.com.wastenot.wastenot;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class WishListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wish_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

       /* list = (ListView) findViewById(R.id.list);
        db = new BDWrapper(this);
        cardsList = db.getHaveCard();
        for (Cards cd : cardsList) {
            itens.add(new ItemListView(cd.getName(), R.drawable.whish));
        }

        adapter = new AdapterListView(this, itens);
        list.setAdapter(adapter);*/
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
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
            Intent intent = (new Intent(this,WishListActivity.class));
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
