package com.example.android.sunshine;


import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends PreferenceFragmentCompat implements
    OnSharedPreferenceChangeListener {


  public SettingsFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    addPreferencesFromResource(R.xml.preference_screen);
    for (int i = 0; i < getPreferenceScreen().getPreferenceCount(); i++) {
      Preference preference = getPreferenceScreen().getPreference(i);
      if (!(preference instanceof CheckBoxPreference)) {
        setPreferenceSummary(preference,
            getPreferenceScreen().getSharedPreferences().getString(preference.getKey(), ""));
      }
    }
  }

  @Override
  public void onStart() {
    super.onStart();
    getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
  }

  @Override
  public void onStop() {
    getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    super.onStop();
  }

  private void setPreferenceSummary(Preference preference, Object value) {
    String stringValue = value.toString();
    String key = preference.getKey();

    if (preference instanceof ListPreference) {
      /* For list preferences, look up the correct display value in */
      /* the preference's 'entries' list (since they have separate labels/values). */
      ListPreference listPreference = (ListPreference) preference;
      int prefIndex = listPreference.findIndexOfValue(stringValue);
      if (prefIndex >= 0) {
        preference.setSummary(listPreference.getEntries()[prefIndex]);
      }
    } else {
      // For other preferences, set the summary to the value's simple string representation.
      preference.setSummary(stringValue);
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
}
