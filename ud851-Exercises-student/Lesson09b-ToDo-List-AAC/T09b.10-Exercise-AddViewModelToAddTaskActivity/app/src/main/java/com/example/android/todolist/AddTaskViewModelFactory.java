package com.example.android.todolist;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import com.example.android.todolist.database.AppDatabase;

public class AddTaskViewModelFactory extends ViewModelProvider.NewInstanceFactory {

  private AppDatabase mDb;
  private int mTaskId;

  public AddTaskViewModelFactory(AppDatabase db, int taskId) {
    this.mDb = db;
    this.mTaskId = taskId;
  }

  // Note: This can be reused with minor modifications
  @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new AddTaskViewModel(mDb, mTaskId);
  }
}
