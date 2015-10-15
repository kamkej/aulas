package br.ufpr.tads.tipcalc;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class TipCalc extends Activity {

    private double currentBillTotal;
    private int currentCustomPercent;
    private EditText tip10EditText;
    private EditText tip15EditText;
    private EditText tip20EditText;
    private EditText total10EditText;
    private EditText total15EditText;
    private EditText total20EditText;
    private TextView customTipTexView;
    private EditText billEditTest;
    private EditText tipCustomEditText;
    private EditText totalCustomEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tip_calc);
        tip10EditText = (EditText)findViewById(R.id.tip10EditText);
        tip15EditText = (EditText)findViewById(R.id.tip15EditText);
        tip20EditText = (EditText)findViewById(R.id.tip20EditText);
        total10EditText = (EditText)findViewById(R.id.total10EditText);
        total15EditText = (EditText)findViewById(R.id.total15EditText);
        total20EditText = (EditText)findViewById(R.id.total20EditText);
        customTipTexView = (TextView)findViewById(R.id.customTipTextView);
        tipCustomEditText =(EditText)findViewById(R.id.tipCustomEditText);
        totalCustomEditText = (EditText)findViewById(R.id.totalCustomEditText);
        billEditTest = (EditText)findViewById(R.id.billEditText);
        SeekBar customSeekBar = (SeekBar)findViewById(R.id.customseekBar);

        currentCustomPercent = customSeekBar.getProgress();

        billEditTest.addTextChangedListener(billTextWatcher);

        customSeekBar.setOnSeekBarChangeListener(customSeekBarListener);


    }

    private void updateStandard(){
        double tenPercentTip = currentBillTotal*10;
        double tenPercentTotal = currentBillTotal+tenPercentTip;
        double fifteenPercentTip = currentBillTotal*15;
        double fifteenPercentTotal = currentBillTotal+fifteenPercentTip;
        double twentyPercentTip = currentBillTotal*20;
        double twentyPercentTotal = currentBillTotal+twentyPercentTip;

        tip10EditText.setText(String.format("%.02f",tenPercentTip));
        total10EditText.setText(String.format("%.02f",tenPercentTotal));
        tip15EditText.setText(String.format("%.02f",fifteenPercentTip));
        total15EditText.setText(String.format("%.02f",fifteenPercentTotal));
        tip20EditText.setText(String.format("%.02f",twentyPercentTip));
        total20EditText.setText(String.format("%.02f", twentyPercentTotal));


    }
    private void updateCustom(){
        customTipTexView.setText(currentCustomPercent+"%");

        double customTipAmount = currentBillTotal * currentCustomPercent * .01;
        double customTotalAmount = currentBillTotal + customTipAmount;

        tipCustomEditText.setText(String.format("%.02f",customTipAmount));
        totalCustomEditText.setText(String.format("%.02f",customTipAmount));
    }
    private SeekBar.OnSeekBarChangeListener customSeekBarListener = new SeekBar.OnSeekBarChangeListener(){

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            currentCustomPercent = progress;
            updateCustom();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };
    private TextWatcher billTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                currentBillTotal = Double.parseDouble(s.toString());
            }catch (NumberFormatException e){
                currentBillTotal = 0.0;
            }
            updateCustom();
            updateStandard();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tip_calc, menu);
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
}
