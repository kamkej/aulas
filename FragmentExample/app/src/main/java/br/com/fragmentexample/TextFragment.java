package br.com.fragmentexample;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by julio on 10/21/15.
 */
public class TextFragment extends Fragment {

    private static TextView textView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.text_fragment,container,false);

        textView = (TextView)view.findViewById(R.id.textView1);
        return view;
    }

    public  void changeTextProperties(int fontsize,String text){
        textView.setTextSize(fontsize);
        textView.setText(text);
    }
}
