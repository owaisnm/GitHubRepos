package com.owais.example.githubrepos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainViewInterface {

  @BindView(R.id.recyclerview)
  RecyclerView mRecyclerView;
  @BindView(R.id.progress_bar)
  ProgressBar mProgressBar;

  private static final String TAG = MainActivity.class.getSimpleName();
  private RepositoryAdapter mAdapter;
  private MainPresenter mPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main_activity);
    ButterKnife.bind(this);
    setupMVP();
    setupViews();
    getRepositories();
  }

  private void setupMVP() {
    mPresenter = new MainPresenter(this);
  }

  private void setupViews() {

    mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    DividerItemDecoration dividerItemDecoration =
        new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
    mRecyclerView.addItemDecoration(dividerItemDecoration);
  }

  private void getRepositories() {
    mPresenter.getRepositories();
  }

  @Override
  public void showToast(String str) {
    Toast.makeText(MainActivity.this, str, Toast.LENGTH_LONG).show();
  }

  @Override
  public void displayRepositories(List<Repository> repositories) {
    if (repositories != null) {
      mAdapter = new RepositoryAdapter(this, repositories);
      mRecyclerView.setAdapter(mAdapter);
    } else {
      Log.d(TAG, "Repositories response null");
    }
  }

  @Override
  public void displayError(String e) {
    showToast(e);
  }

  @Override
  public void showProgressBar() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override
  public void dismissProgressBar() {
    mProgressBar.setVisibility(View.GONE);
  }
}
