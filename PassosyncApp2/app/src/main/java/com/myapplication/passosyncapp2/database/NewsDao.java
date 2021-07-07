package com.myapplication.passosyncapp2.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface NewsDao {

  @Query("SELECT * FROM NEWS_TABLE")
  List<News> getAll();

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  void insert(News news);

}
