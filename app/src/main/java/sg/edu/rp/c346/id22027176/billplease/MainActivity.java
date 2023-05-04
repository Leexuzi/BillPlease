package sg.edu.rp.c346.id22027176.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    EditText people;
    EditText discount;
    ToggleButton addSVS;
    ToggleButton addGST;
    RadioGroup rgPay;
    RadioButton rdbtnCash;
    RadioButton rdbtnPN;
    Button divide;
    Button clear;
    TextView allInAll;
    TextView share;
    ViewGroup rawStats;
    ViewGroup disc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount.findViewById(R.id.amt);
        people.findViewById(R.id.pax);
        discount.findViewById(R.id.dis);
        addSVS.findViewById(R.id.toggleSVS);
        addGST.findViewById(R.id.toggleGST);
        rgPay.findViewById(R.id.medium);
        rdbtnCash.findViewById(R.id.radioCash);
        rdbtnPN.findViewById(R.id.radioPN);
        divide.findViewById(R.id.go);
        clear.findViewById(R.id.back);
        allInAll.findViewById(R.id.monehGone);
        share.findViewById(R.id.divvy);
        rawStats.findViewById(R.id.rawNums);
        disc.findViewById(R.id.disInput);

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                }
                float modify = 0; //the SVS and GST
                float dis = 0; //discount
                int subTTL = Integer.parseInt(String.valueOf(amount.getText())); // \_(*_*)_/ trying to get the int value of the string entered into the bill
                if (addSVS.isChecked()) {
                    modify += 0.1;
                }
                if (addGST.isChecked()) {
                    modify += 0.07;
                }
                if (discount.getText().length() != 0) {
                    dis = 100 / Integer.parseInt(String.valueOf(amount.getText())); //Getting the decimal value of the percentage discount, but there must be a better way to get int values...
                    modify += dis;
                }
                float TTL = subTTL * (modify + 1); //doing the calculations to get the total
                allInAll.setText(String.format("$%s", String.valueOf(TTL))); //displaying the ttl
                String divided = String.valueOf(Integer.parseInt(String.valueOf(people)) / TTL); //dividing the cost
                if(rdbtnCash.isChecked()){
                    share.setText(String.format("Each Pays: $%s in cash", divided));//Adds the text to the divided amt
                }
                else{
                    share.setText(String.format("Each Pays: $%s via PayPal to 912345678", divided));
                }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear(rawStats);
                clear(disc);
            }
        }
    }
    private void clear(ViewGroup group){ //i looked online for this(https://stackoverflow.com/questions/5740708/android-clearing-all-edittext-fields-with-clear-button) just declaring my crime, so i may be charged with copying, rather than plagiarising
        for (int i = 0, count=group.getChildCount(); i < count; i++) {
            View view = group.getChildAt(i);
            if(view instanceof EditText){ //not really necessary for the app im making though
                ((EditText) view).setText(""); //i don't understand the brackets
            }
        }
    }
}
// i dunno if this works, because the emulator won't run. im tired of trying to get it to work