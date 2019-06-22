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

package android.example.com.visualizerpreferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

public class SettingsFragment extends PreferenceFragmentCompat implements
    OnSharedPreferenceChangeListener {

  @Override
  public void onCreatePreferences(Bundle bundle, String s) {

    // Add visualizer preferences, defined in the XML file in res->xml->pref_visualizer
    addPreferencesFromResource(R.xml.pref_visualizer);

    PreferenceScreen preferenceScreen = getPreferenceScreen();
    for (int i = 0; i < preferenceScreen.getPreferenceCount(); i++) {
      Preference preference = preferenceScreen.getPreference(i);
      if (preference instanceof ListPreference) {
        setPreferenceSummary(preference, ((ListPreference) preference).getValue());
      }
    }
  }

  @Override
  public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
    Preference preference = findPreference(key);
    if (preference != null) {
      if (!(preference instanceof CheckBoxPreference)) {
        setPreferenceSummary(preference, sharedPreferences.getString(key, ""));
      }
    }
  }

  private void setPreferenceSummary(Preference preference, String value) {
    if (preference instanceof ListPreference) {
      int index = ((ListPreference) preference).findIndexOfValue(value);
      if (index >= 0) {
        preference.setSummary(((ListPreference) preference).getEntries()[index]);
      }
    }
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
  }

  @Override
  public void onDestroy() {
    getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    super.onDestroy();
  }
}