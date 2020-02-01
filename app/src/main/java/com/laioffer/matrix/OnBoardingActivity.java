package com.laioffer.matrix;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;


public class OnBoardingActivity extends AppCompatActivity {

  private ViewPager viewPager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_on_boarding);

    // Find components
    viewPager = findViewById(R.id.viewpager);
    TabLayout tabLayout = findViewById(R.id.sliding_tabs);

    // Create adapter for providing fragments to the viewPager
    OnBoardingPageAdapter onBoardingPageAdapter = new OnBoardingPageAdapter(this, getSupportFragmentManager());
    viewPager.setAdapter(onBoardingPageAdapter);

    // Connect tablayout to the viewpager
    tabLayout.setupWithViewPager(viewPager);
  }


  // switch viewpage to #page
  public void setCurrentPage(int page) {
    viewPager.setCurrentItem(page);
  }

}
