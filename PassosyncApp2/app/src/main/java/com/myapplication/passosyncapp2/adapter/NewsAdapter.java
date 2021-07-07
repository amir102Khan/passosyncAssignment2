package com.myapplication.passosyncapp2.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.myapplication.passosyncapp2.R;
import com.myapplication.passosyncapp2.interfaces.Constants;
import com.myapplication.passosyncapp2.interfaces.OnItemClickListener;
import com.myapplication.passosyncapp2.models.NewsModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> implements
    Constants {

  private final List<NewsModel.Article> news;
  private final Context context;
  private final OnItemClickListener onItemClickListener;

  public NewsAdapter(List<NewsModel.Article> news,
                     Context context,
                     OnItemClickListener onItemClickListener) {
    this.news = news;
    this.context = context;
    this.onItemClickListener = onItemClickListener;
  }

  @NonNull
  @NotNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
    View view = LayoutInflater
        .from(parent.getContext()).inflate(R.layout.item_news, parent, false);
    return new MyViewHolder(view);

  }

  @Override
  public void onBindViewHolder(@NonNull @NotNull NewsAdapter.MyViewHolder holder, int position) {

    try {
      Picasso.with(context)
          .load(news.get(position).getUrlToImage())
          .placeholder(R.drawable.ic_launcher_background)
          .networkPolicy(NetworkPolicy.OFFLINE)
          .into(holder.newsImage, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
              Picasso.with(context)
                  .load(news.get(position).getUrlToImage())
                  .into(holder.newsImage, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                      Log.v("Picasso", "Could not fetch image");
                    }
                  });
            }
          });
    } catch (Exception e) {
      holder.newsImage
          .setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_launcher_background));
    }

    holder.tvTitle.setText(news.get(position).getTitle());

    if (position == news.size() - 1) {
      holder.viewSeparator.setVisibility(View.GONE);
    } else {
      holder.viewSeparator.setVisibility(View.VISIBLE);
    }

    holder.itemView.setOnClickListener(view -> onItemClickListener.onClick(position, OPEN_ARTICLE));

    holder.btnSave.setOnClickListener(view -> onItemClickListener.onClick(position, SAVE_ARTICLE));
  }

  @Override
  public int getItemCount() {
    return news.size();
  }

  class MyViewHolder extends RecyclerView.ViewHolder {
    private final ImageView newsImage;
    private final TextView tvTitle;
    private final View viewSeparator;
    private final Button btnSave;

    public MyViewHolder(
        @NonNull @NotNull View itemView) {
      super(itemView);
      newsImage = itemView.findViewById(R.id.imgNews);
      tvTitle = itemView.findViewById(R.id.tvTitle);
      viewSeparator = itemView.findViewById(R.id.viewSeparator);
      btnSave = itemView.findViewById(R.id.btn_save);
    }
  }
}
