package com.myapplication.passosyncapp2.viewModel;

import android.app.Application;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.myapplication.passosyncapp2.database.CommandRepository;
import com.myapplication.passosyncapp2.database.News;
import com.myapplication.passosyncapp2.interfaces.Constants;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class SavedNewsViewModel extends AndroidViewModel implements Constants {
  private MutableLiveData<List<News>> savedNews;
  private CommandRepository commandRepository;

  public SavedNewsViewModel(@NonNull
                            @NotNull Application application) {
    super(application);
  }

  public void init() {
    savedNews = new MutableLiveData<>();
    commandRepository = CommandRepository.getCommandRepository();
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  public void getNews() {
    savedNews.postValue(commandRepository.getNews());
  }

  public MutableLiveData<List<News>> getSavedNews() {
    return savedNews;
  }

}
