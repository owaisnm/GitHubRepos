package com.owais.example.githubrepos;

import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;

public interface NetworkInterface {
  @GET("/repositories")
  Observable<List<Repository>> getRepositories();
}
