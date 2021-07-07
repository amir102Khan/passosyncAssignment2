package com.myapplication.passosyncapp2.database;

import androidx.room.TypeConverter;
import java.util.Date;

/**
 * Helper class for db for inserting dates and converting dates
 * Default implementation suggested by google
 */
public class Converters {
  @TypeConverter
  public static Date fromTimestamp(Long value) {
    return value == null ? null : new Date(value);
  }

  @TypeConverter
  public static Long dateToTimestamp(Date date) {
    return date == null ? null : date.getTime();
  }


}
