package com.myapplication.passosyncapp2.viewModel;

import android.app.Application;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.myapplication.passosyncapp2.core.NewsRepository;
import com.myapplication.passosyncapp2.database.CommandRepository;
import com.myapplication.passosyncapp2.interfaces.Constants;
import com.myapplication.passosyncapp2.models.NewsModel;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class NewsViewModel extends AndroidViewModel implements Constants {
  private NewsRepository newsRepository;
  private MutableLiveData<List<NewsModel.Article>> newsLiveData;
  private CommandRepository commandRepository;

  public NewsViewModel(@NonNull
                       @NotNull Application application) {
    super(application);
  }

  public void init() {
    newsRepository = new NewsRepository();
    newsLiveData = newsRepository.getNewsLiveData();
    commandRepository = CommandRepository.getCommandRepository();
  }

  public void getNews() {
    newsRepository.getNews();
  }

  public MutableLiveData<List<NewsModel.Article>> getNewsLiveData() {
    return newsLiveData;
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  public void saveNews(String newsTitle, String author, String description) {
    commandRepository.insertNews(commandRepository
        .createNews(newsTitle,
            author,
            description));
  }


}
