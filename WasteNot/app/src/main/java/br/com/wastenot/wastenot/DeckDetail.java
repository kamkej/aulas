package br.com.wastenot.wastenot;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DeckDetail extends AppCompatActivity {

    BDWrapper db;
    ListView list;
    AdapterListView adapter;
    List<ItemListView> itens = new ArrayList<ItemListView>();
    List<Cards> cardsList;
    Deck deck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_detail);
        Intent intent = getIntent();
         deck = (Deck) intent.getSerializableExtra("deck");
        TextView title = (TextView) findViewById(R.id.txtDeckTitle);
        title.setText(deck.getDeckName());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddCart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = (new Intent(getApplicationContext(),addCartActivity.class));
                intent.putExtra("deckid", String.valueOf(deck.getId()));
                startActivity(intent);
            }
        });

        list = (ListView) findViewById(R.id.listDeck);
        db = new BDWrapper(this);
        getCards();

    }
    protected void getCards(){
        adapter = new AdapterListView(this, updateCardList());
        list.setAdapter(adapter);
    }
    protected List<ItemListView> updateCardList(){

        cardsList = db.getCardOfDeck(new String[]{String.valueOf(deck.getId())});
        int img =0;
        for (Cards cd : cardsList) {

            switch (cd.getColorIdentity())
            {
                case "U":
                    img = R.drawable.u;
                    break;
                case "R":
                    img = R.drawable.r;
                    break;
                case "G":
                    img = R.drawable.g;
                    break;
                case "B":
                    img = R.drawable.b;
                    break;
                case "W":
                    img = R.drawable.w;
                    break;
                case "U,B":
                    img = R.drawable.ub;
                    break;
                case "B,G":
                    img = R.drawable.bg;
                    break;
                case "R,G":
                    img = R.drawable.rg;
                    break;
                case "W,B":
                    img = R.drawable.wb;
                    break;
                case "U,R":
                    img = R.drawable.ur;
                    break;
                case "G,U":
                    img = R.drawable.gu;
                    break;
                case "W,U":
                    img = R.drawable.wu;
                    break;
                case "R,W":
                    img = R.drawable.rw;
                    break;
                case "W,R":
                    img = R.drawable.rw;
                    break;
                case "B,R":
                    img = R.drawable.br;
                    break;
                case "G,W":
                    img = R.drawable.gw;
                    break;
                default:
                    img = R.drawable.ic_home;
            }


            itens.add(new ItemListView(cd.getName(), img,0));
        }
        return itens;

    }
}
