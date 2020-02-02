package com.laioffer.matrix;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class OnBoardingActivity extends AppCompatActivity {

  private ViewPager viewPager;

  private FirebaseAuth mAuth;
  private final static String TAG = OnBoardingActivity.class.getSimpleName();
  private FirebaseAuth.AuthStateListener mAuthListener;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_on_boarding);

    mAuth = FirebaseAuth.getInstance();

    //Add listener to check sign in status
    mAuthListener = new FirebaseAuth.AuthStateListener() {
      @Override
      public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
          Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
        } else {
          Log.d(TAG, "onAuthStateChanged:signed_out");
        }
      }
    };

    //sign in anonymously
    mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
      @Override
      public void onComplete(@NonNull Task<AuthResult> task) {
        Log.d(TAG, "signInAnonymously:onComplete:" + task.isSuccessful());
        if (!task.isSuccessful()) {
          Log.w(TAG, "signInAnonymously", task.getException());
        }
      }
    });

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

  //Add authentification listener when activity starts
  @Override
  public void onStart() {
    super.onStart();
    mAuth.addAuthStateListener(mAuthListener);
  }

  //Remove authentification listener when activity starts
  @Override
  public void onStop() {
    super.onStop();
    if (mAuthListener != null) {
      mAuth.removeAuthStateListener(mAuthListener);
    }
  }

}
