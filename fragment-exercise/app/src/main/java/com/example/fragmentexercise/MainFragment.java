package com.example.fragmentexercise;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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

  //Added: onClickListener for dialog_button
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
    Button show_dialog = view.findViewById(R.id.dialog_button);
    show_dialog.setOnClickListener(new OnClickListener() {
      @SuppressLint("ResourceType")
      @RequiresApi(api = VERSION_CODES.LOLLIPOP)
      @Override
      public void onClick(View v) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        final AlertDialog dialog = builder.create();

        View dialog_view = getLayoutInflater().inflate(R.layout.dialog, null);
        dialog.setView(dialog_view);

        Button exit = dialog_view.findViewById(R.id.button_exit);
        exit.setOnClickListener(new OnClickListener() {
          @Override
          public void onClick(View v) {
            dialog.cancel();
          }
        });

        dialog.show();
      }
    });
    super.onViewCreated(view, savedInstanceState);
  }
}
