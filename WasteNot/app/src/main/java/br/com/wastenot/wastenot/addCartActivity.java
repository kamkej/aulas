package br.com.wastenot.wastenot;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class addCartActivity extends AppCompatActivity {
    BDWrapper db;
    ListView list;
    EditText edts;
    String card,deckId;
    List<Cards> cardsList;
    List<String> cardsSelect = new ArrayList<String>();
    List<ItemDeckView> itens = new ArrayList<ItemDeckView>();
    AdapterDeckCarts adapter = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cart);

        db = new BDWrapper(this);
        list = (ListView) findViewById(R.id.list);
        edts = (EditText) findViewById(R.id.edtSearch);

        Intent intent = getIntent();
        deckId =  intent.getStringExtra("deckid");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAddCart);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), cardsSelect.toString() + "," + deckId, Toast.LENGTH_LONG).show();
            }
        });
        list.setChoiceMode(list.CHOICE_MODE_MULTIPLE);

    }

    public void search(final View view) {
        if(adapter!=null){
            adapter.updateList();
        }
        getCards();
       /* list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (cardsSelect.contains(String.valueOf(cardsList.get(position).getId()))) {

                    cardsSelect.remove(cardsSelect.indexOf(String.valueOf(cardsList.get(position).getId())));

                    if (cardsSelect.size()>= 1) {
                        Toast.makeText(getApplicationContext(), "Qtd"+cardsSelect.size(), Toast.LENGTH_LONG).show();
                    }
                    if (!cardsSelect.contains(String.valueOf(cardsList.get(position).getId()))) {
                        view.setBackgroundColor(0);

                    }
                } else if (!cardsSelect.isEmpty()) {

                    cardsSelect.add(String.valueOf(cardsList.get(position).getId()));
                    view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.itemselect));
                } else {
                    Cards Cards = cardsList.get(position);
                    Intent intent = (new Intent(getApplicationContext(), CardDetail.class));
                    intent.putExtra("deck", Cards);
                    startActivity(intent);
                }


            }
        });*/

        list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                cardsSelect.add(String.valueOf(cardsList.get(position).getId()));
                //   view.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.itemselect));
             //  Toast.makeText(getApplicationContext(),String.valueOf(adapter.getItemId(position)), Toast.LENGTH_LONG).show();
                adapter.updateList(getApplicationContext(),itens,id);


                return true;
            }
        });

    }
    protected   List<ItemDeckView> updateCardList(){
        int img =0;
        int icon = 0;
        card = String.valueOf(edts.getText());
        cardsList = db.getCard(card);
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
           /* if(cd.getWhishlist().equalsIgnoreCase("1")){
                icon = R.drawable.ic_wanted_list_black;
            } else if(cd.getHavelist().equalsIgnoreCase("1")){
                icon = R.drawable.ic_have_black;

            }else{
                icon = 0;
            }*/


            itens.add(new ItemDeckView(cd.getName(), img,""));
        }
        return itens;

    }

    protected void getCards(){
        adapter = new AdapterDeckCarts(this, updateCardList());

        list.setAdapter(adapter);
    }
}
