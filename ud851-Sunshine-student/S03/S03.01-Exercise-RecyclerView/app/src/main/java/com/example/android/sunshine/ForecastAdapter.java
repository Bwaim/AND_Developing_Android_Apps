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

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Fabien Boismoreau on 22/03/2019.
 * <p>
 */
public class ForecastAdapter extends
    RecyclerView.Adapter<ForecastAdapter.ForecastAdapterViewHolder> {

  private String[] mWeatherData;

  public ForecastAdapter() {
  }

  @Override
  public ForecastAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.forecast_list_item, parent, false);
    return new ForecastAdapterViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ForecastAdapterViewHolder holder, int position) {
    holder.mWeatherTextView.setText(mWeatherData[position]);
  }

  @Override
  public int getItemCount() {
    if (mWeatherData == null) {
      return 0;
    } else {
      return mWeatherData.length;
    }
  }

  public void setmWeatherData(String[] mWeatherData) {
    this.mWeatherData = mWeatherData;
    notifyDataSetChanged();
  }

  class ForecastAdapterViewHolder extends RecyclerView.ViewHolder {

    public final TextView mWeatherTextView;

    public ForecastAdapterViewHolder(View itemView) {
      super(itemView);
      mWeatherTextView = (TextView) itemView.findViewById(R.id.tv_weather_data);
    }
  }
}
