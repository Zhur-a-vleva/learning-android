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
      //What is it???
      // TODO: ctrl + q на методе, читать доку
      // В двух словах: работа с фрагментами реализована через паттерн `Command`
      // see: https://ru.wikipedia.org/wiki/%D0%9A%D0%BE%D0%BC%D0%B0%D0%BD%D0%B4%D0%B0_(%D1%88%D0%B0%D0%B1%D0%BB%D0%BE%D0%BD_%D0%BF%D1%80%D0%BE%D0%B5%D0%BA%D1%82%D0%B8%D1%80%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F)
      transaction.commit();
    }
  }

  // FIXME: Открываю SecondFragment -> жму `onBackPressed` -> MainFragment не отображается
  @Override
  public void onBackPressed() {
    if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
      getSupportFragmentManager().popBackStack();
    } else {
      super.onBackPressed();
    }
  }
}
