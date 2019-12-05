package com.example.fragmentexercise;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

  private static MainFragment fragment1 = MainFragment.newInstance();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    if (savedInstanceState == null) {
      FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
      transaction.add(R.id.container, fragment1);
      transaction.addToBackStack(fragment1.getClass().getSimpleName());
      transaction.commit();
    }
  }

  @Override
  public void onBackPressed() {
    if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
      getSupportFragmentManager().popBackStack();
    } else if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
      getSupportFragmentManager().popBackStack();
      super.onBackPressed();
    } else {
      super.onBackPressed();
    }
  }
}
