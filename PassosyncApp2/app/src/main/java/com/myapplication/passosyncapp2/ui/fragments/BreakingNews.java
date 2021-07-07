package com.myapplication.passosyncapp2.ui.fragments;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.myapplication.passosyncapp2.R;
import com.myapplication.passosyncapp2.adapter.NewsAdapter;
import com.myapplication.passosyncapp2.core.BaseFragment;
import com.myapplication.passosyncapp2.databinding.FragmentBreakingNewsBinding;
import com.myapplication.passosyncapp2.interfaces.OnItemClickListener;
import com.myapplication.passosyncapp2.models.NewsModel;
import com.myapplication.passosyncapp2.ui.activities.ArticleWebView;
import com.myapplication.passosyncapp2.viewModel.NewsViewModel;
import java.util.ArrayList;
import java.util.List;


public class BreakingNews extends BaseFragment implements OnItemClickListener {

  private final List<NewsModel.Article> news = new ArrayList<>();
  private FragmentBreakingNewsBinding breakingNewsBinding;
  private NewsAdapter newsAdapter;
  private NewsViewModel newsViewModel;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    breakingNewsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_breaking_news,
        container,
        false);
    return breakingNewsBinding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view,
                            @Nullable @org.jetbrains.annotations.Nullable
                                Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);

    setViewModel();
    createEmptyAdapter();
    if (checkInternetConnection()) {
      newsViewModel.getNews();
    } else {
      showToast("Please check your internet connection");
    }
  }

  private void createEmptyAdapter() {
    breakingNewsBinding.rvBreakingNews.setLayoutManager(new LinearLayoutManager(getContext()));

    newsAdapter = new NewsAdapter(news,
        getContext(),
        this);
    breakingNewsBinding.rvBreakingNews.setAdapter(newsAdapter);
  }

  private void setViewModel() {
    newsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
    breakingNewsBinding.setNewsViewModel(newsViewModel);
    breakingNewsBinding.setLifecycleOwner(this);
    newsViewModel.init();
    newsViewModel.getNewsLiveData().observe(getViewLifecycleOwner(), articles -> {
      if (articles != null) {
        news.addAll(articles);
        newsAdapter.notifyDataSetChanged();
      }
    });
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  @Override
  public void onClick(int position, int type) {
    if (type == OPEN_ARTICLE) {
      if (news.get(position).getUrl() != null) {
        startActivity(new Intent(getContext(), ArticleWebView.class)
            .putExtra(ARTICLE_URL, news.get(position).getUrl()));
      }
    } else if (type == SAVE_ARTICLE) {
      newsViewModel.saveNews(news.get(position).getTitle(),
          news.get(position).getAuthor(),
          news.get(position).getDescription());
    }
  }
}