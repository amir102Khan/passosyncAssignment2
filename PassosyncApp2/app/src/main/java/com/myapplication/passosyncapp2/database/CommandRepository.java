package com.myapplication.passosyncapp2.database;

import android.content.Context;
import android.os.Build;
import androidx.annotation.RequiresApi;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


public class CommandRepository {

  private static CommandRepository commandRepository;
  private final Context context;

  public CommandRepository(Context context) {
    this.context = context;
  }

  public static synchronized CommandRepository initCommandRepository(Context context) {
    if (commandRepository == null) {
      commandRepository = new CommandRepository(context);
    }
    return commandRepository;
  }

  public static CommandRepository getCommandRepository() {
    return commandRepository;
  }

  public News createNews(String newsTitle, String author, String description) {
    News news = new News();
    news.setNewsTitle(newsTitle);
    news.setAuthor(author);
    news.setDescription(description);
    return news;
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  public void insertNews(News news) {
    CompletableFuture.runAsync(
        () -> DatabaseClient
            .getInstance(context)
            .getAppDatabase()
            .newsDao()
            .insert(news));

  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  public List<News> getNews() {
    CompletableFuture<List<News>> completableFuture = new CompletableFuture<>();
    try {
      completableFuture =
          CompletableFuture.supplyAsync(() -> DatabaseClient.getInstance(context)
              .getAppDatabase()
              .newsDao().getAll());
      return completableFuture.get();

    } catch (ExecutionException | InterruptedException executionException) {
      completableFuture.completeExceptionally(executionException);
      return null;
    }
  }
}
