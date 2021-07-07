package com.myapplication.passosyncapp2.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "news_table")
public class News implements Serializable {

  @NonNull
  @PrimaryKey
  @ColumnInfo(name = "newsTitle")
  private String newsTitle;
  @ColumnInfo(name = "author")
  private String author;
  @ColumnInfo(name = "description")
  private String description;

  public News() {
  }

  public String getNewsTitle() {
    return newsTitle;
  }

  public void setNewsTitle(String newsTitle) {
    this.newsTitle = newsTitle;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
