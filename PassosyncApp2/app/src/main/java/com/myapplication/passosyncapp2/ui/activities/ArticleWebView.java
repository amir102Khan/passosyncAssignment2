package com.myapplication.passosyncapp2.ui.activities;

import android.os.Bundle;
import android.webkit.WebViewClient;
import androidx.databinding.DataBindingUtil;
import com.myapplication.passosyncapp2.R;
import com.myapplication.passosyncapp2.core.BaseActivity;
import com.myapplication.passosyncapp2.databinding.ActivityArticleWebViewBinding;

public class ArticleWebView extends BaseActivity {

  private ActivityArticleWebViewBinding webViewBinding;
  private String url;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    webViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_article_web_view);
    getIntentData();
    loadData();
  }

  private void getIntentData() {
    url = getIntent().getStringExtra(ARTICLE_URL);
  }

  private void loadData() {
    webViewBinding.webView.getSettings().setJavaScriptEnabled(true);
    webViewBinding.webView.setWebViewClient(new WebViewClient());
    webViewBinding.webView.loadUrl(url);
  }
}