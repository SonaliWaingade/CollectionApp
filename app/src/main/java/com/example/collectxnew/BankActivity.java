package com.example.collectxnew;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class BankActivity extends AppCompatActivity {

    EditText Bank_name, bank_account, bank_balance, bank_enterammount;
    TextView bank_Totalamount;
    Switch bank_switch;
    ToggleButton bank_toggle;
    Button bank_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank);

        Bank_name = findViewById(R.id.Bank_name);
        bank_account = findViewById(R.id.bank_account);
        bank_balance = findViewById(R.id.bank_balance);
        bank_enterammount = findViewById(R.id.bank_enterammount);

        bank_Totalamount = findViewById(R.id.bank_Totalamount);

        bank_switch = findViewById(R.id.bank_switch);

        bank_toggle = findViewById(R.id.bank_toggle);

        bank_button = findViewById(R.id.bank_button);
    }

    public void DoHandle(View view) {

        try {
            if (!bank_switch.isChecked()) {
                setTitle("Bank Closed");

            }
            int acc = Integer.parseInt(""+bank_account.getText());
            Integer bal = Integer.parseInt(""+bank_balance.getText());
            Integer amm = Integer.parseInt(""+bank_enterammount.getText());

            if (bank_switch.isChecked() && bank_toggle.isChecked()){
                setTitle("Amount Deposit");
                bal = bal+amm-5;
                bank_Totalamount.setText("Updated Balance=" +bal);
            }
            else {
                if (bank_switch.isChecked() && !bank_toggle.isChecked() && bal<=amm+5){
                    setTitle("Amount Withdraw");
                    bank_Totalamount.setText("Insufficient Balance");
                }
            }
        }catch (Exception ex){
            System.out.println(ex);
            bank_Totalamount.setText(ex.getMessage());
        }

    }
}
