package com.example.json_gson_exercise;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainActivity extends AppCompatActivity {

  private class Date {

    @SerializedName("day")
    @Expose
    private int day;

    @SerializedName("month")
    @Expose
    private int month;

    @SerializedName("year")
    @Expose
    private int year;

    private int getDate() {
      return day;
    }

    private int getMonth() {
      return month;
    }

    private int getYear() {
      return year;
    }

    private void setDay(int day) {
      this.day = day;
    }

    private void setMonth(int month) {
      this.month = month;
    }

    private void setYear(int year) {
      this.year = year;
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    TextView json_text = findViewById(R.id.jsontext);
    Date date = new Date();
    date.setDay(31);
    date.setMonth(12);
    date.setYear(2019);
    json_text.setText(new Gson().toJson(date));
  }
}
