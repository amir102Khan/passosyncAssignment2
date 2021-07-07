package com.myapplication.passosyncapp2.database;

import android.content.Context;
import androidx.room.Room;

public class DatabaseClient {

  private static DatabaseClient mInstance;
  private Context mContext;
  // app database
  private AppDatabase appDatabase;

  private DatabaseClient(Context mContext) {
    this.mContext = mContext;

    //creating the app database with Room database builder
    //users is the name of the database
    appDatabase = Room.databaseBuilder(mContext,
        AppDatabase.class,
        "newsDatabase")
        .fallbackToDestructiveMigration()
        .build();
  }

  public static synchronized DatabaseClient getInstance(Context context) {
    if (mInstance == null) {
      mInstance = new DatabaseClient(context);
    }

    return mInstance;
  }

  public AppDatabase getAppDatabase() {
    return appDatabase;
  }
}
