package com.owais.example.githubrepos;

import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class MainPresenter implements MainPresenterInterface {
  private MainViewInterface mViewInterface;
  private static final String TAG = MainPresenter.class.getSimpleName();

  public MainPresenter(MainViewInterface viewInterface) {
    this.mViewInterface = viewInterface;
  }

  public Observable<List<Repository>> getObservable() {
    return NetworkClient.getRetrofit()
        .create(NetworkInterface.class)
        .getRepositories()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }

  public DisposableObserver<List<Repository>> getObserver() {
    return new DisposableObserver<List<Repository>>() {
      @Override
      public void onNext(List<Repository> repositories) {
        if (repositories != null) {
          Log.d(TAG, "OnNext repositories count: " + repositories.size());
          mViewInterface.displayRepositories(repositories);
        } else {
          Log.d(TAG, "OnNext null");
        }
        mViewInterface.dismissProgressBar();
      }

      @Override
      public void onError(Throwable e) {
        Log.d(TAG, "Error " + e);
        e.printStackTrace();
        mViewInterface.displayError("Error fetching Repository Data");
      }

      @Override
      public void onComplete() {
        Log.d(TAG, "Completed");
      }
    };
  }

  @Override
  public void getRepositories() {
    mViewInterface.showProgressBar();
    getObservable().subscribeWith(getObserver());
  }
}
