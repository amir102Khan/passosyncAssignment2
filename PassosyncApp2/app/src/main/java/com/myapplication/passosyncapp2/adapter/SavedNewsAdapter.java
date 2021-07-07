package com.myapplication.passosyncapp2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.myapplication.passosyncapp2.R;
import com.myapplication.passosyncapp2.database.News;
import com.myapplication.passosyncapp2.interfaces.Constants;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class SavedNewsAdapter extends RecyclerView.Adapter<SavedNewsAdapter.MyViewHolder> implements
    Constants {

  private final List<News> news;
  private final Context context;

  public SavedNewsAdapter(List<News> news,
                          Context context) {
    this.news = news;
    this.context = context;
  }

  @NonNull
  @NotNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    View view = LayoutInflater
        .from(parent.getContext()).inflate(R.layout.item_saved_news, parent, false);
    return new MyViewHolder(view);

  }

  @Override
  public void onBindViewHolder(@NonNull @NotNull SavedNewsAdapter.MyViewHolder holder,
                               int position) {

    holder.tvTitle.setText(news.get(position).getNewsTitle());
    holder.tvAuthor.setText(news.get(position).getAuthor());
    holder.tvDesc.setText(news.get(position).getDescription());

    if (position == news.size() - 1) {
      holder.viewSeparator.setVisibility(View.GONE);
    } else {
      holder.viewSeparator.setVisibility(View.VISIBLE);
    }

  }

  @Override
  public int getItemCount() {
    return news.size();
  }

  class MyViewHolder extends RecyclerView.ViewHolder {

    private final TextView tvTitle;
    private final TextView tvAuthor;
    private final TextView tvDesc;
    private final View viewSeparator;

    public MyViewHolder(
        @NonNull @NotNull View itemView) {
      super(itemView);
      tvTitle = itemView.findViewById(R.id.tvTitle);
      viewSeparator = itemView.findViewById(R.id.viewSeparator);
      tvAuthor = itemView.findViewById(R.id.tvAuthor);
      tvDesc = itemView.findViewById(R.id.tvDesc);
    }
  }
}
