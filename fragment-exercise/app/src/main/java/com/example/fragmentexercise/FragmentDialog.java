package com.example.fragmentexercise;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

public class FragmentDialog extends DialogFragment {

  private String TAG = FragmentDialog.class.getSimpleName();

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
    final AlertDialog.Builder builder = new Builder(getActivity());
    View view = getActivity().getLayoutInflater().inflate(R.layout.dialog, null);
    TextView textView = view.findViewById(R.id.useful_information);
    textView.setText(R.string.usefulInformation);
    builder.setView(view);
    final Dialog dialog = builder.create();

    Button exit = view.findViewById(R.id.button_exit);
    exit.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        dialog.cancel();
      }
    });
    return dialog;
  }

  //Show dialog
  public void show(FragmentManager manager) {
    FragmentDialog fragmentDialog = new FragmentDialog();
    fragmentDialog.show(manager, TAG);
  }
}
