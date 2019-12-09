package com.example.fragmentexercise;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SecondFragment extends Fragment {

  private static String TEXT_KEY = "text";

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
    TextView textView = view.findViewById(R.id.text);

    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
    SharedPreferences.Editor editor = preferences.edit();
    Bundle bundle = getArguments();
    if (bundle != null) {
      String t = bundle.getString(TEXT_KEY);
      if (!t.equals("")) {
        editor.putString(TEXT_KEY, t);
        editor.apply();
      }
    }
    textView.setText(preferences.getString(TEXT_KEY, getString(R.string.nothing)));
  }
}
