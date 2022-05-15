package com.example.cryptocurrencytracker.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptocurrencytracker.Adapter.StockRVAdapter;
import com.example.cryptocurrencytracker.Model.StockModel;
import com.example.cryptocurrencytracker.R;
import com.example.cryptocurrencytracker.ViewModal.ViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class Note_Act extends AppCompatActivity {
    // creating a variables for our recycler view.
    private RecyclerView StockRV;
    private static final int ADD_Stock_REQUEST = 1;
    private static final int EDIT_Stock_REQUEST = 2;
    private ViewModel viewModel;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_);

        // initializing our variable for our recycler view and fab.
        StockRV = findViewById(R.id.StockRV);
        fab = findViewById(R.id.idFABAdd);

        // adding on click listener for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starting a new activity for adding a new course
                // and passing a constant value in it.
                Intent intent = new Intent(Note_Act.this, NewStockActivity.class);
                startActivityForResult(intent, ADD_Stock_REQUEST);
            }
        });

        // setting layout manager to our adapter class.
        StockRV.setLayoutManager(new LinearLayoutManager(this));
        StockRV.setHasFixedSize(true);

        // initializing adapter for recycler view.
        final StockRVAdapter adapter = new StockRVAdapter();

        // setting adapter class for recycler view.
        StockRV.setAdapter(adapter);

        // passing a data from view modal.
        viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        // below line is use to get all the courses from view modal.
        viewModel.getAllStock().observe(this, new Observer<List<StockModel>>() {
            @Override
            public void onChanged(List<StockModel> models) {
                // when the data is changed in our models we are
                // adding that list to our adapter class.
                adapter.submitList(models);
            }
        });
        // below method is use to add swipe to delete method for item of recycler view.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // on recycler view item swiped then we are deleting the item of our recycler view.
                viewModel.delete(adapter.getStockAt(viewHolder.getAdapterPosition()));
                Toast.makeText(Note_Act.this, "data deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                // below line is use to attach this to recycler view.
                        attachToRecyclerView(StockRV);
        // below line is use to set item click listener for our item of recycler view.

        adapter.setOnItemClickListener(new StockRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(StockModel model) {
                Intent intent = new Intent(Note_Act.this, NewStockActivity.class);
                intent.putExtra(NewStockActivity.EXTRA_ID, model.getId());
                intent.putExtra(NewStockActivity.EXTRA_NAME, model.getStockname());
                intent.putExtra(NewStockActivity.EXTRA_DESCRIPTION, model.getStockDescription());
                intent.putExtra(NewStockActivity.EXTRA_DURATION, model.getStockDuration());
                intent.putExtra(NewStockActivity.EXTRA_BUY, model.getStockBuy());
                intent.putExtra(NewStockActivity.EXTRA_Sell, model.getStockSell());

                // below line is to start a new activity and
                // adding a edit course constant.
                startActivityForResult(intent, EDIT_Stock_REQUEST);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_Stock_REQUEST && resultCode == RESULT_OK) {
            String StockName = data.getStringExtra(NewStockActivity.EXTRA_NAME);
            String StockDescription = data.getStringExtra(NewStockActivity.EXTRA_DESCRIPTION);
            String StockDuration = data.getStringExtra(NewStockActivity.EXTRA_DURATION);
            String StockBuy = data.getStringExtra(NewStockActivity.EXTRA_BUY);
            String StockSell = data.getStringExtra(NewStockActivity.EXTRA_Sell);
            StockModel model = new StockModel(StockName, StockDescription, StockDuration, StockBuy, StockSell);
            viewModel.insert(model);
            Toast.makeText(this, "Stock saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_Stock_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewStockActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Stock data can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }
            String StockName = data.getStringExtra(NewStockActivity.EXTRA_NAME);
            String StockDesc = data.getStringExtra(NewStockActivity.EXTRA_DESCRIPTION);
            String StockDuration = data.getStringExtra(NewStockActivity.EXTRA_DURATION);
            String StockBuy = data.getStringExtra(NewStockActivity.EXTRA_BUY);
            String StockSell = data.getStringExtra(NewStockActivity.EXTRA_Sell);

            StockModel model = new StockModel(StockName, StockDesc, StockDuration, StockBuy, StockSell);
            model.setId(id);

            viewModel.update(model);

            Toast.makeText(this, "Stock updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Stock not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
