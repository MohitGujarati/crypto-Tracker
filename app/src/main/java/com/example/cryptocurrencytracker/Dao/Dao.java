package com.example.cryptocurrencytracker.Dao;


import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cryptocurrencytracker.Model.StockModel;

import java.util.List;

@androidx.room.Dao
public interface Dao {


    @Insert
    void insert(StockModel model);

    // below method is use to update
    // the data in our database.
    @Update
    void update(StockModel model);

    // below line is use to delete a
    // specific course in our database.
    @Delete
    void delete(StockModel model);

    // on below line we are making query to
    // delete all courses from our database.
    @Query("DELETE FROM stock_table")
    void deleteAllCourses();

    // below line is to read all the courses from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @Query("SELECT * FROM stock_table ORDER BY Stockname ASC")
    LiveData<List<StockModel>> getallStock();

}
