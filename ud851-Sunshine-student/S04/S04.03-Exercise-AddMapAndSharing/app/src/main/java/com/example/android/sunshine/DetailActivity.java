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

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

  private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

  private String mForecast;
  private TextView mWeatherDisplay;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail);

    mWeatherDisplay = (TextView) findViewById(R.id.tv_display_weather);

    Intent intentThatStartedThisActivity = getIntent();

    if (intentThatStartedThisActivity != null) {
      if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
        mForecast = intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT);
        mWeatherDisplay.setText(mForecast);
      }
    }
  }

  // TODO (3) Create a menu with an item with id of action_share
  // TODO (4) Display the menu and implement the forecast sharing functionality
}