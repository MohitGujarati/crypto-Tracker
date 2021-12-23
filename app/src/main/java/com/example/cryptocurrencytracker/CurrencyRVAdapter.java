package com.example.cryptocurrencytracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CurrencyRVAdapter extends RecyclerView.Adapter<CurrencyRVAdapter.ViewHolder> {

    private ArrayList<CurrencyModel> currencyModelArrayList;
    private Context context;
    private static DecimalFormat df4 = new DecimalFormat("#.####");


    //creating constructor
    public CurrencyRVAdapter(ArrayList<CurrencyModel> currencyModelArrayList, Context context) {
        this.currencyModelArrayList = currencyModelArrayList;
        this.context = context;
    }

    //New method for Currency filter
    public void Filtermethod(ArrayList<CurrencyModel> filteredList) {
        currencyModelArrayList = filteredList;
        notifyDataSetChanged();
    }


    //Creating a ViewHolder methods
    @NonNull
    @Override
    public CurrencyRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Getting the data from Context and Inflating(Showing) to our XML item(NAME,symbol,price)
        View view = LayoutInflater.from(context).inflate(R.layout.currencies_rv_item, parent, false);
        //returning that adapter
        return new CurrencyRVAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyRVAdapter.ViewHolder holder, int position) {
        //Binding data from Viewholder and connecting with the actual items in XML
        // object of model class
        CurrencyModel currencyModel = currencyModelArrayList.get(position);
        // call holder and set the data by call the get item before that we findview in view holder class
        holder.currencyNametv.setText(currencyModel.getName());
        holder.symbolTV.setText(currencyModel.getSymbol());
        holder.rateTv.setText("$ " + df4.format(currencyModel.getPrice()));
    }

    @Override
    public int getItemCount() {
        return currencyModelArrayList.size();
    }

    // we create an inner class for View Holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView currencyNametv, symbolTV, rateTv;

        //we create a constructor of View Holder
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            currencyNametv = itemView.findViewById(R.id.idTvname);
            symbolTV = itemView.findViewById(R.id.idTvSymbol);
            rateTv = itemView.findViewById(R.id.idTvCurrencyRate);

        }
    }
}
