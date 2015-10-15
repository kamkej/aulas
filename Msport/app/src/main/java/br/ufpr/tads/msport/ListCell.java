package br.ufpr.tads.msport;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by julio on 9/30/15.
 */
public class ListCell extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] itens;
    private Integer[] imageId;
    private String[]valor;

    public ListCell(Activity context,  String[] itens, Integer[] imageId,String[] valor) {
        super(context, R.layout.list_cell,itens);
        this.itens = itens;
        this.context = context;
        this.imageId = imageId;
        this.valor = valor;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_cell, null, true);
        TextView txtTitle =(TextView) rowView.findViewById(R.id.txt);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        TextView txtValor = (TextView)rowView.findViewById(R.id.valor);
        txtTitle.setText(itens[position]);
        imageView.setImageResource(imageId[position]);
        txtValor.setText(valor[position]);

        return rowView;
    }
}


