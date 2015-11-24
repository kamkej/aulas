package br.ufpr.tads.foodapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListCell extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] nome;
    private final Bitmap[] image;
    private final Float[] valor;


    public ListCell(Activity context, String[] nome, Bitmap[] image, Float[] valor) {
        super(context,R.layout.activity_list_cell,nome);
        this.context = context;
        this.nome = nome;
        this.image = image;
        this.valor = valor;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.activity_list_cell, null, true);
        TextView txtNome = (TextView)rowView.findViewById(R.id.nome);
        TextView txtValor =(TextView)rowView.findViewById(R.id.valor);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.img);
        txtNome.setText(nome[position]);
        txtValor.setText(String.valueOf(valor[position]));
        imageView.setImageBitmap(image[position]);
        //imageView.setb
        return rowView;
    }
}
