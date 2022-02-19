package com.example.cryptocurrencytracker.ViewModal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cryptocurrencytracker.Model.StockModel;
import com.example.cryptocurrencytracker.Repository.StockRepository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private StockRepository repository;

    // below line is to create a variable for live
    // data where all the courses are present.
    private LiveData<List<StockModel>> allStock;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new StockRepository(application);
        allStock = repository.getAllStocks();
    }

    // below method is use to insert the data to our repository.
    public void insert(StockModel model) {
        repository.insert(model);
    }

    // below line is to update data in our repository.
    public void update(StockModel model) {
        repository.update(model);
    }

    // below line is to delete the data in our repository.
    public void delete(StockModel model) {
        repository.delete(model);
    }

    // below method is to delete all the courses in our list.
    public void deleteAllStock() {
        repository.deleteAllCourses();
    }

    // below method is to get all the courses in our list.
    public LiveData<List<StockModel>> getAllStock() {
        return allStock;
    }


}
