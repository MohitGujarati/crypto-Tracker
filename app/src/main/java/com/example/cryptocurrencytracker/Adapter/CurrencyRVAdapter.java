package com.example.cryptocurrencytracker.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptocurrencytracker.Model.CurrencyModel;
import com.example.cryptocurrencytracker.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CurrencyRVAdapter extends RecyclerView.Adapter<CurrencyRVAdapter.ViewHolder> {

    private ArrayList<CurrencyModel> currencyModelArrayList;
    private Context context;
    private static DecimalFormat df4 = new DecimalFormat("#.##");


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
        holder.tagstv.setText(currencyModel.getTags());
        holder.rateTv.setText("$ " + df4.format(currencyModel.getPrice()));
        //Glide.with(context).load(currencyModel.getLogo()).into(holder.imgeview);

        String value = holder.tagstv.getText().toString();
        switch (value) {
            case "mineable":
                holder.tagstv.setBackgroundResource(R.drawable.bacground_minable);
                break;

            case "platform":
                holder.tagstv.setBackgroundResource(R.drawable.bacground_platfrom);
                break;


            case "defi":
                holder.tagstv.setBackgroundResource(R.drawable.background_defi);
                break;
            case "memes":
                holder.tagstv.setBackgroundResource(R.drawable.backround_meme);
                break;

            default:
                holder.tagstv.setBackgroundResource(R.drawable.bg);

        }


    }

    @Override
    public int getItemCount() {
        return currencyModelArrayList.size();
    }


    // we create an inner class for View Holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView currencyNametv, symbolTV, rateTv, tagstv;
        private ImageView imgeview;

        //  private ImageView imglogo;
        //we create a constructor of View Holder
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgeview=itemView.findViewById(R.id.imgeview);
            currencyNametv = itemView.findViewById(R.id.idTvname);
            symbolTV = itemView.findViewById(R.id.idTvSymbol);
            rateTv = itemView.findViewById(R.id.idTvCurrencyRate);
            tagstv = itemView.findViewById(R.id.idTvCurrencyTags);


        }
    }
}

