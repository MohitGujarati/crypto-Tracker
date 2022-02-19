package com.example.cryptocurrencytracker.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cryptocurrencytracker.R;

public class NewStockActivity extends AppCompatActivity {

    // creating a variables for our button and edittext.
    private EditText EdtStockName, StockDescEdt, StockDurEdt,Stockbuy,Stocksell;
    private Button StockBtn;

    // creating a constant string variable for our
    // course name, description and duration.
    public static final String EXTRA_ID = "ID";
    public static final String EXTRA_NAME = "NAME";
    public static final String EXTRA_DESCRIPTION = "Description";
    public static final String EXTRA_DURATION = "Duration";
    public static final String EXTRA_BUY = "Buy price";
    public static final String EXTRA_Sell = "Sell Price";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_stock);
        // initializing our variables for each view.
        EdtStockName = findViewById(R.id.Stock_Name_Edt);
        StockDescEdt = findViewById(R.id.Stock_Edt_Des);
        StockDurEdt = findViewById(R.id.Stock_Edt_Dur);
        StockBtn = findViewById(R.id.Stock_Save_Btn);
        Stockbuy = findViewById(R.id.Stock_Edt_Buy);
        Stocksell = findViewById(R.id.Stock_Edt_Sell);

        // below line is to get intent as we
        // are getting data via an intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            // if we get id for our data then we are
            // setting values to our edit text fields.
            EdtStockName.setText(intent.getStringExtra(EXTRA_NAME));
            StockDescEdt.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            StockDurEdt.setText(intent.getStringExtra(EXTRA_DURATION));
            Stockbuy.setText(intent.getStringExtra(EXTRA_BUY));
            Stocksell.setText(intent.getStringExtra(EXTRA_Sell));
        }
        // adding on click listener for our save button.
        StockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting text value from edittext and validating if
                // the text fields are empty or not.
                String StockName = EdtStockName.getText().toString();
                String StockDesc = StockDescEdt.getText().toString();
                String StockDuration = StockDurEdt.getText().toString();
                String Stock_buy = Stockbuy.getText().toString();
                String Stock_sell = Stocksell.getText().toString();
                if (StockName.isEmpty() || StockDesc.isEmpty() || StockDuration.isEmpty()|| Stock_buy.isEmpty()|| Stock_sell.isEmpty()) {
                    Toast.makeText(NewStockActivity.this, "Please enter the valid Stock details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to save our Stock.
                saveCourse(StockName, StockDesc, StockDuration,Stock_buy,Stock_sell);
            }
        });
    }

    private void saveCourse(String StockName, String StockDescription, String StockDuration,String Stock_buy,String Stock_sell) {
        // inside this method we are passing
        // all the data via an intent.
        Intent data = new Intent();

        // in below line we are passing all our course detail.
        data.putExtra(EXTRA_NAME, StockName);
        data.putExtra(EXTRA_DESCRIPTION, StockDescription);
        data.putExtra(EXTRA_DURATION, StockDuration);
        data.putExtra(EXTRA_BUY, Stock_buy);
        data.putExtra(EXTRA_Sell, Stock_sell);
        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            // in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }

        // at last we are setting result as data.
        setResult(RESULT_OK, data);

        // displaying a toast message after adding the data
        Toast.makeText(this, "Stock has been saved", Toast.LENGTH_SHORT).show();

    }
}
