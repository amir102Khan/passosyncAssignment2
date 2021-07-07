package com.myapplication.passosyncapp2.core;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.material.snackbar.Snackbar;
import com.myapplication.passosyncapp2.interfaces.Constants;

public class BaseActivity extends AppCompatActivity implements Constants {

  public Activity mContext;
  public String TAG = "Error";
  public LinearLayoutManager layoutManager;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    /*Making notification bar transparent*/
    if (Build.VERSION.SDK_INT >= 21) {
      getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE |
          View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    } else {
      int hh = 90;
    }
    intitializeVariable();
    changeStatusBarColor();
  }

  public void intitializeVariable() {
    mContext = BaseActivity.this;
    layoutManager = new LinearLayoutManager(getApplicationContext());
  }

  /*Making notification bar transparent*/
  private void changeStatusBarColor() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      Window window = getWindow();
      window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
      window.setStatusBarColor(Color.TRANSPARENT);
    }
  }

  public void showToast(String message) {
    Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
  }

  public void showSnackBar(View v, String message) {
    Snackbar.make(v, message, Snackbar.LENGTH_SHORT).show();
  }

  public boolean checkInternetConnection() {
    ConnectivityManager conMgr =
        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
    return conMgr != null &&
        (conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() &&
            conMgr.getActiveNetworkInfo().isConnected());
  }

  public void hideKeyboard() {
    InputMethodManager inputMethodManager =
        (InputMethodManager) mContext.getSystemService(Activity.INPUT_METHOD_SERVICE);
      if (inputMethodManager != null && mContext.getCurrentFocus() != null) {
          inputMethodManager
              .hideSoftInputFromWindow(mContext.getCurrentFocus().getWindowToken(), 0);
      }
  }


  @Override
  public void onBackPressed() {
    super.onBackPressed();
  }
}
