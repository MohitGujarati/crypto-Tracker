package com.example.cryptocurrencytracker.View;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.cryptocurrencytracker.Adapter.CurrencyRVAdapter;
import com.example.cryptocurrencytracker.Model.CurrencyModel;
import com.example.cryptocurrencytracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ProgressBar loadingbar;
    private EditText search;
    private RecyclerView currenciesRV;

    Button addnote,nightmode,Viewnews,settingbtn;
    private ArrayList<CurrencyModel> currencyModelArrayList;
    private CurrencyRVAdapter currencyRVAdapter;



    public String logourl="";
    public static final String TAG = "internetCall";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        // using toolbar as ActionBar
     //   setSupportActionBar(toolbar);
        search = findViewById(R.id.idSearch);
        currenciesRV = findViewById(R.id.idcurrencies);
        loadingbar = findViewById(R.id.idLoader);
        addnote = findViewById(R.id.Addnote);
        Viewnews = findViewById(R.id.idnews);
        settingbtn = findViewById(R.id.settingbtn);


        //creating arraylist
        currencyModelArrayList = new ArrayList<>();
        currencyRVAdapter = new CurrencyRVAdapter(currencyModelArrayList, this);


        //Setting this adapter to recycle view
        currenciesRV.setLayoutManager(new GridLayoutManager(this,2));
        currenciesRV.setAdapter(currencyRVAdapter);

        getCurrencyData();

        settingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Setting", Toast.LENGTH_SHORT).show();
            }
        });




        addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Note_Act.class);
                startActivity(i);
            }
        });

        Viewnews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, News_Act.class);
                startActivity(i);
            }
        });
        //Searching Function
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                filterCurrenciews(s.toString());

            }
        });

    }




    //Filter for searchbar
    private void filterCurrenciews(String currency) {

        ArrayList<CurrencyModel> filterList = new ArrayList<>();
        for (CurrencyModel item : currencyModelArrayList) {
            if (item.getName().toLowerCase().contains(currency.toLowerCase())) {
                filterList.add(item);
            }

        }

        if (filterList.isEmpty()) {
         //   Toast.makeText(this, "", Toast.LENGTH_LONG).show();
        } else {
            currencyRVAdapter.Filtermethod(filterList);
        }
    }


    //method to fetch data from Api using Volley
    private void getCurrencyData() {
        loadingbar.setVisibility(View.VISIBLE);

        String url = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/listings/latest";
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                loadingbar.setVisibility(View.GONE);

                try {

                    JSONArray dataArray = response.getJSONArray("data");
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject dataObj = dataArray.getJSONObject(i);
                        String name = dataObj.getString("name");
                        String symbol = dataObj.getString("symbol");

                        JSONArray datatag = dataObj.getJSONArray("tags");
                        String minabale = (String) datatag.get(0);

                        // creating object for quote  so we get access to USD-and then-->price
                        JSONObject quote = dataObj.getJSONObject("quote");
                        JSONObject USD = quote.getJSONObject("USD");
                        double price = USD.getDouble("price");

                        currencyModelArrayList.add(new CurrencyModel(name, symbol, minabale, price));

                    }
                    //notify Adapter that the data is changed;
                    currencyRVAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "Data not received ");
                  //  Toast.makeText(MainActivity.this, "Failed to retrieve data ", Toast.LENGTH_SHORT).show();
                }

                //ADDING Search Function //WE need to create filter method to filter the data

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                loadingbar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Failed to load Data", Toast.LENGTH_LONG).show();
                //here we extract the data FromPostMan

            }


        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> header = new HashMap<>();
                //we pass key and value from postman
                header.put("X-CMC_PRO_API_KEY", "686bbefe-b5e8-4c3c-945b-f3ada338a84c");
                return header;

            }
        };
        requestQueue.add(jsonObjectRequest);
    }

}
