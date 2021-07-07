package com.myapplication.passosyncapp2.ui.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import com.google.android.material.tabs.TabLayout;
import com.myapplication.passosyncapp2.R;
import com.myapplication.passosyncapp2.core.BaseActivity;
import com.myapplication.passosyncapp2.databinding.ActivityDashboardBinding;

public class Dashboard extends BaseActivity {

  private ActivityDashboardBinding dashboardBinding;
  private NavController navController;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    dashboardBinding = DataBindingUtil.setContentView(this, R.layout.activity_dashboard);
    navController = Navigation.findNavController(this, R.id.nav_host_fragment);

    setUpTab();
    setTabListener();
  }

  private void setUpTab() {
    setCustomTabView(R.drawable.sports_selcetor, "Breaking news");
    setCustomTabView(R.drawable.saved_news_selcetor, "Saved");
  }

  private void setCustomTabView(int icon, String tabName) {
    LinearLayout customView = (LinearLayout) LayoutInflater
        .from(this).inflate(R.layout.item_custom_tab, null);
    ((ImageView) customView.findViewById(R.id.ivIcon)).setImageResource(icon);
    ((TextView) customView.findViewById(R.id.tvName)).setText(tabName);

    dashboardBinding.tabs.addTab(dashboardBinding.tabs.newTab().setCustomView(customView));
  }

  private void setTabListener() {
    dashboardBinding.tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override
      public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
          case 0:
            navController.navigate(R.id.breaking_news_fragment);
            break;
          case 1:
            navController.navigate(R.id.saved_news_fragment);
            break;
        }
      }

      @Override
      public void onTabUnselected(TabLayout.Tab tab) {

      }

      @Override
      public void onTabReselected(TabLayout.Tab tab) {

      }
    });
  }
}