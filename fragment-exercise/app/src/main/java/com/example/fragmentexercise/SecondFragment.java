package com.example.fragmentexercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {

  private static String TEXT_KEY = "text";
  private static String new_text;

  //Creating bundle
  public static SecondFragment newInstance(String text) {
    Bundle bundle = new Bundle();
    bundle.putString(TEXT_KEY, text);
    SecondFragment fragment = new SecondFragment();
    fragment.setArguments(bundle);
    return fragment;
  }

  //Checking bundle and get text
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // TODO: перенести в onViewCreated(), тогда не придётся сохранять в статические поля
    // статические поля в фрагменте - ататат
    Bundle bundle = getArguments();
    if (bundle != null) {
      String t = bundle.getString(TEXT_KEY);
      if (t.equals("")) {
        new_text = getString(R.string.nothing);
      } else {
        new_text = bundle.getString(TEXT_KEY);
      }
    } else {
      new_text = getString(R.string.nothing);
    }
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.secons_fragment, container, false);
  }

  //Setting text
  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    // TODO: посмотреть в super.onViewCreated(), надо ли его вызывать?
    super.onViewCreated(view, savedInstanceState);
    TextView textView = view.findViewById(R.id.text);
    textView.setText(new_text);
  }
}
