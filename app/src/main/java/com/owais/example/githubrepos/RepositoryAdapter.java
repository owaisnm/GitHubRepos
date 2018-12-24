package com.owais.example.githubrepos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.List;

public class RepositoryAdapter
    extends RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder> {

  private List<Repository> mRepositories;
  private Context mContext;

  public RepositoryAdapter(Context context, List<Repository> repositories) {
    mContext = context;
    mRepositories = repositories;
  }

  @NonNull
  @Override
  public RepositoryAdapter.RepositoryViewHolder onCreateViewHolder(
      @NonNull
          ViewGroup viewGroup, int i) {
    View v = LayoutInflater.from(mContext).inflate(R.layout.repository_item, viewGroup, false);
    RepositoryViewHolder repositoryViewHolder = new RepositoryViewHolder(v);
    return repositoryViewHolder;
  }

  @Override
  public void onBindViewHolder(
      @NonNull
          RepositoryAdapter.RepositoryViewHolder repositoryHolder, int i) {
    repositoryHolder.mTitle.setText(mRepositories.get(i).getName());
    repositoryHolder.mDescription.setText(mRepositories.get(i).getDescription());
  }

  @Override
  public int getItemCount() {
    if (mRepositories != null) {
      return mRepositories.size();
    }
    return 0;
  }

  public class RepositoryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.title)
    TextView mTitle;
    @BindView(R.id.description)
    TextView mDescription;

    public RepositoryViewHolder(
        @NonNull
            View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
