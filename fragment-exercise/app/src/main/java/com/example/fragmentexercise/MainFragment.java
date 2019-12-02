package com.example.fragmentexercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainFragment extends Fragment {

  public static MainFragment newInstance() {
    return new MainFragment();
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.main_fragment, container, false);
    // TODO: все инициализации вьюх и обработка делаются в `onViewCreated()`. Перенести туда.
    Button button = view.findViewById(R.id.click);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        // FIXME: switch без дефолтного кейса
        switch (v.getId()) {
          case R.id.click:
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.replace(R.id.container, SecondFragment.newInstance());
            transaction.commit();
            // FIXME: не забывайте break ставить, хотя когда один кейс, не обязательно.
        }
      }
    });
    return view;
  }
}
