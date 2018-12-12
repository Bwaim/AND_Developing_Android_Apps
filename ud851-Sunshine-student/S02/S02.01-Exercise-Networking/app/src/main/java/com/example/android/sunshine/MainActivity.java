/*
 *    Copyright 2018 Fabien Boismoreau
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.example.android.sunshine;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.example.android.sunshine.data.SunshinePreferences;
import com.example.android.sunshine.utilities.NetworkUtils;
import com.example.android.sunshine.utilities.OpenWeatherJsonUtils;
import java.io.IOException;
import java.net.URL;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

  private TextView mWeatherTextView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_forecast);

    /*
     * Using findViewById, we get a reference to our TextView from xml. This allows us to
     * do things like set the text of the TextView.
     */
    mWeatherTextView = (TextView) findViewById(R.id.tv_weather_data);

    loadWeatherData();
  }

  private void loadWeatherData() {
    String preferredLocation = SunshinePreferences.getPreferredWeatherLocation(this);

    new WeatherAsyncTask().execute(preferredLocation);
  }

  public class WeatherAsyncTask extends AsyncTask<String, Void, String[]> {

    @Override
    protected String[] doInBackground(String... params) {
      if (params.length == 0) {
        return null;
      }

      String location = params[0];

      URL url = NetworkUtils.buildUrl(location);

      String response = null;
      try {
        response = NetworkUtils.getResponseFromHttpUrl(url);
        return OpenWeatherJsonUtils.getSimpleWeatherStringsFromJson(MainActivity.this, response);
      } catch (IOException e) {
        e.printStackTrace();
      } catch (JSONException e) {
        e.printStackTrace();
      }

      return null;
    }

    @Override
    protected void onPostExecute(String[] weatherData) {
      if (weatherData != null) {
        for (String weather : weatherData) {
          mWeatherTextView.append(weather + "\n\n\n");
        }
      }
    }
  }
}