package com.owais.example.githubrepos;

import java.util.List;

public interface MainViewInterface {

  void showProgressBar();

  void dismissProgressBar();

  void showToast(String s);

  void displayRepositories(List<Repository> repositories);

  void displayError(String s);
}
