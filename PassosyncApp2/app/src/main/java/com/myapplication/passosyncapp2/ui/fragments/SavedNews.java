package com.myapplication.passosyncapp2.ui.fragments;

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
import com.myapplication.passosyncapp2.adapter.SavedNewsAdapter;
import com.myapplication.passosyncapp2.core.BaseFragment;
import com.myapplication.passosyncapp2.database.News;
import com.myapplication.passosyncapp2.databinding.FragmentSavedNewsBinding;
import com.myapplication.passosyncapp2.viewModel.SavedNewsViewModel;
import java.util.ArrayList;
import java.util.List;


public class SavedNews extends BaseFragment {
  private FragmentSavedNewsBinding savedNewsBinding;
  private SavedNewsAdapter savedNewsAdapter;
  private List<News> savedNews = new ArrayList<>();
  private SavedNewsViewModel savedNewsViewModel;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {

    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    savedNewsBinding = DataBindingUtil.inflate(inflater,
        R.layout.fragment_saved_news,
        container,
        false);
    return savedNewsBinding.getRoot();
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  @Override
  public void onViewCreated(@NonNull @org.jetbrains.annotations.NotNull View view,
                            @Nullable @org.jetbrains.annotations.Nullable
                                Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    setViewModel();
    setEmptyAdapter();
    getData();
  }

  private void setEmptyAdapter() {
    savedNewsBinding.rvSavedNews.setLayoutManager(new LinearLayoutManager(getContext()));
    savedNewsAdapter = new SavedNewsAdapter(savedNews, getContext());

    savedNewsBinding.rvSavedNews.setAdapter(savedNewsAdapter);
  }

  @RequiresApi(api = Build.VERSION_CODES.N)
  private void getData() {
    savedNewsViewModel.getNews();
  }

  private void setViewModel() {
    savedNewsViewModel = ViewModelProviders.of(this).get(SavedNewsViewModel.class);
    savedNewsBinding.setSavedNewsViewModel(savedNewsViewModel);
    savedNewsBinding.setLifecycleOwner(this);
    savedNewsViewModel.init();
    savedNewsViewModel.getSavedNews().observe(getViewLifecycleOwner(), news -> {
      if (news != null) {
        savedNews.addAll(news);
        savedNewsAdapter.notifyDataSetChanged();
      }
    });
  }
}