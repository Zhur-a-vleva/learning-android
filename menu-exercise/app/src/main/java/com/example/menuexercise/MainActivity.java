package com.example.menuexercise;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  //Объявление menu
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu, menu);
    return super.onCreateOptionsMenu(menu);
  }

  //Обработка нажатий
  @Override
  public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    switch (item.getItemId()) {
      case R.id.profile:
        Toast.makeText(this, "Profile Clicked", Toast.LENGTH_SHORT).show();
      case R.id.home:
        Toast.makeText(this, "Home Clicked", Toast.LENGTH_SHORT).show();
      case R.id.settings:
        Toast.makeText(this, "Settings Clicked", Toast.LENGTH_SHORT).show();
      default:
        return super.onOptionsItemSelected(item);
    }
  }

  //Создание контекстного меню
  @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
    if (v.getId() == R.id.click) {
      menu.add(menu.NONE, R.id.settings, menu.NONE, "Context menu");
    } else {
      super.onCreateContextMenu(menu, v, menuInfo);
    }
  }

  @Override
  public boolean onContextItemSelected(@NonNull MenuItem item) {
    if (item.getItemId() == R.id.settings) {
      Toast.makeText(this, "Context menu clicked", Toast.LENGTH_SHORT).show();
    }
    return super.onContextItemSelected(item);
  }

  //Включение контекстного меню
  @Override
  protected void onResume() {
    registerForContextMenu(findViewById(R.id.click));
    super.onResume();
  }

  //Выключение контектного меню
  @Override
  protected void onPause() {
    unregisterForContextMenu(findViewById(R.id.click));
    super.onPause();
  }
}
