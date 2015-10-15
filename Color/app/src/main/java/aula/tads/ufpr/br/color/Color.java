package aula.tads.ufpr.br.color;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;



public class Color extends Activity {

    private SeekBar redSeekBar;
    private SeekBar greenSeekBar;
    private SeekBar blueSeekBar;

    private TextView selectedColor;
    private TextView color;
    private String[] hexColor = {"00","00","00"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color);

        redSeekBar = (SeekBar)findViewById(R.id.SBRed);
        greenSeekBar = (SeekBar)findViewById(R.id.SBGreen);
        blueSeekBar = (SeekBar)findViewById(R.id.SBBlue);

        redSeekBar.setOnSeekBarChangeListener(new EventSeek((byte)0));
        greenSeekBar.setOnSeekBarChangeListener(new EventSeek((byte)1));
        blueSeekBar.setOnSeekBarChangeListener(new EventSeek((byte)2));

        selectedColor = (TextView)findViewById(R.id.TVSelectColor);
        color = (TextView)findViewById(R.id.color);

        setColor("#"+hexColor[0]+hexColor[1]+hexColor[2]);

    }

    private void setColor(String str){
        selectedColor.setText(str);
        color.setBackgroundColor(android.graphics.Color.parseColor(str));
    }
    private void setHexNumber(int progress, byte color) {
        String c = Integer.toHexString(progress);
        hexColor[color] = (c.length() == 2 ? "" : "0")+c;
        setColor("#"+hexColor[0]+hexColor[1]+hexColor[2]);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_color, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public class EventSeek implements SeekBar.OnSeekBarChangeListener {
        private byte color;


        public EventSeek(byte color) {
            this.color = color;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            setHexNumber(progress, color);
        }



        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }


    };
}
