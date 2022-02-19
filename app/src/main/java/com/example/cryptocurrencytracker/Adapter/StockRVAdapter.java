package com.example.cryptocurrencytracker.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptocurrencytracker.Model.StockModel;
import com.example.cryptocurrencytracker.R;

public class StockRVAdapter extends ListAdapter<StockModel,StockRVAdapter.ViewHolder> {

    // creating a variable for on item click listener.
    private OnItemClickListener listener;

    // creating a constructor class for our adapter class.
    public StockRVAdapter(){  super(DIFF_CALLBACK);}

    // creating a call back for item of recycler view.
    private static final DiffUtil.ItemCallback<StockModel> DIFF_CALLBACK = new DiffUtil.ItemCallback<StockModel>() {
        @Override
        public boolean areItemsTheSame(StockModel oldItem, StockModel newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(StockModel oldItem, StockModel newItem) {
            // below line is to check the Stock name, description and Stock duration,BUY SELL.
            return oldItem.getStockname().equals(newItem.getStockname())
                    &&
                    oldItem.getStockDescription().equals(newItem.getStockDescription())
                    &&
                    oldItem.getStockDuration().equals(newItem.getStockDuration())
                    &&
                    oldItem.getStockBuy().equals(newItem.getStockBuy())
                    &&
                    oldItem.getStockSell().equals(newItem.getStockSell());
        }
    };



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item= LayoutInflater.from(parent.getContext()).inflate(R.layout.stock_itemview,parent,false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // below line of code is use to set data to
        // each item of our recycler view.
        StockModel model = getStockAt(position);
        holder.StockNameTV.setText(model.getStockname());
        holder.StockDescTV.setText(model.getStockDescription());
        holder.StockDurationTV.setText(model.getStockDuration());
        holder.StockBuy.setText(model.getStockBuy());
        holder.StockSell.setText(model.getStockSell());

    }

    // creating a method to get course modal for a specific position.
    public StockModel getStockAt(int position) {
        return getItem(position);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        // view holder class to create a variable for each view.
        TextView StockNameTV, StockDescTV, StockDurationTV, StockBuy, StockSell;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            StockNameTV = itemView.findViewById(R.id.TVStockName);
            StockDescTV = itemView.findViewById(R.id.TVStockDesc);
            StockDurationTV = itemView.findViewById(R.id.TVStockDur);
            StockBuy =itemView.findViewById(R.id.TVStockBuy);
            StockSell =itemView.findViewById(R.id.TVStockSell);

            // adding on click listener for each item of recycler view.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // inside on click listener we are passing
                    // position to our item of recycler view.
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(StockModel model);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}

