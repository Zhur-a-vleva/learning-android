package com.example.fragmentexercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
    return view;
  }

  @Override
  public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
    Button button = view.findViewById(R.id.click);
    button.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        switch (v.getId()) {
          case R.id.click:
            EditText editText = view.findViewById(R.id.edit);
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            Fragment fragment2 = SecondFragment.newInstance(editText.getText().toString());
            transaction.replace(R.id.container, fragment2);
            transaction.addToBackStack(fragment2.getClass().getSimpleName());
            transaction.commit();
          default:
            break;
        }
      }
    });
    super.onViewCreated(view, savedInstanceState);
  }
}
