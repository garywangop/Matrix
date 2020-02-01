package com.laioffer.matrix;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class OnBoardingPageAdapter extends FragmentPagerAdapter {

  private static int NUM_ITEMS = 2;
  private final Context context;

  public OnBoardingPageAdapter(Context context, @NonNull FragmentManager fm) {
    super(fm);
    this.context = context;
  }

  @NonNull
  @Override
  public Fragment getItem(int position) {
    switch (position) {
      case 0:
        return LoginFragment.newInstance();

      case 1:
        return RegisterFragment.newInstance();

      default:
        return null;
    }
  }

  @Override
  public int getCount() {
    return NUM_ITEMS;
  }

  @Nullable
  @Override
  public CharSequence getPageTitle(int position) {
    switch (position) {
      case 0:
        return context.getString(R.string.login);
      case 1:
        return context.getString(R.string.register);
      default:
        return null;
    }
  }
}
