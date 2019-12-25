package com.example.api;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import retrofit2.Retrofit;
import retrofit2.Retrofit.Builder;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

  private class NetworkService {

    private NetworkService service = null;
    private String BASE_URL = "https://genius.com/";
    private Builder retrofit = null;

    public NetworkService newInstance() {
      if (service == null) {
        service = new NetworkService();
      }
      return service;
    }

    private NetworkService() {
      retrofit = new Retrofit.Builder();
      retrofit.baseUrl(BASE_URL);
      retrofit.addConverterFactory(GsonConverterFactory.create());
      retrofit.build();
    }
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }
}
