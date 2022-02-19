package com.example.cryptocurrencytracker.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.cryptocurrencytracker.Dao.Dao;
import com.example.cryptocurrencytracker.Database.StockDatabase;
import com.example.cryptocurrencytracker.Model.StockModel;

import java.util.List;

public class StockRepository {
    // below line is the create a variable
    // for dao and list for all courses.
    private Dao dao;
    private LiveData<List<StockModel>> allStocks;
    // creating a constructor for our variables
    // and passing the variables to it.


    // creating a constructor for our variables
    // and passing the variables to it.
    public StockRepository (Application application) {
        StockDatabase database = StockDatabase.getInstance(application);
        dao = database.Dao();
        allStocks = dao.getallStock();
    }
    // creating a method to insert the data to our database.
    public void insert(StockModel model) {
        new InsertStockAsyncTask(dao).execute(model);
    }

    // creating a method to update data in database.
    public void update(StockModel model) {
        new UpdateStockAsyncTask(dao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(StockModel model) {
        new DeleteStockAsyncTask(dao).execute(model);
    }

    // below is the method to delete all the courses.
    public void deleteAllCourses() {
        new DeleteAllStockAsyncTask(dao).execute();
    }

    // below method is to read all the courses.
    public LiveData<List<StockModel>> getAllStocks() {
        return allStocks;
    }

    // we are creating a async task method to insert new course.
    private static class InsertStockAsyncTask extends AsyncTask<StockModel, Void, Void> {
        private Dao dao;

        private InsertStockAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(StockModel... model) {
            // below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our course.
    private static class UpdateStockAsyncTask extends AsyncTask<StockModel, Void, Void> {
        private Dao dao;

        private UpdateStockAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(StockModel... models) {
            // below line is use to update
            // our modal in dao.
            dao.update(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete course.
    private static class DeleteStockAsyncTask extends AsyncTask<StockModel, Void, Void> {
        private Dao dao;

        private DeleteStockAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(StockModel... models) {
            // below line is use to delete
            // our course modal in dao.
            dao.delete(models[0]);
            return null;
        }
    }

    // we are creating a async task method to delete all courses.
    private static class DeleteAllStockAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;
        private DeleteAllStockAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            // on below line calling method
            // to delete all courses.
            dao.deleteAllCourses();
            return null;
        }
    }


}
