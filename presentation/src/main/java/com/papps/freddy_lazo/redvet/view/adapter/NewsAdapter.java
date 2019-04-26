package com.papps.freddy_lazo.redvet.view.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.papps.freddy_lazo.redvet.GlideApp;
import com.papps.freddy_lazo.redvet.R;
import com.papps.freddy_lazo.redvet.model.NewsModel;
import com.papps.freddy_lazo.redvet.model.ServicesModel;
import com.papps.freddy_lazo.redvet.view.fragment.BaseFragment;
import com.papps.freddy_lazo.redvet.view.fragment.NewsFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<NewsModel> data = new ArrayList<>();
    private Context context;
    private OnClickAdapter listener;


    @Inject
    public NewsAdapter() {
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_news, viewGroup, false);
        context = view.getContext();
        return new NewsViewHolder(view);
    }

    public void bindList(List<NewsModel> data) {
        if (data != null) {
            this.data = data;
            notifyDataSetChanged();
        }
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setView(BaseFragment fragment) {
        listener = (OnClickAdapter) fragment;
    }


    class NewsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.img_news)
        ImageView imgNews;
        @BindView(R.id.txt_title)
        TextView tvTitle;
        @BindView(R.id.txt_content)
        TextView tvContent;
        @BindView(R.id.tv_full_news)
        TextView tvFullNews;

        NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bind(int position) {
            tvFullNews.setPaintFlags(tvFullNews.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
            tvTitle.setText(data.get(position).getTitle());
            tvContent.setText(data.get(position).getContent());

            GlideApp.with(context)
                    .asBitmap()
                    .dontAnimate()
                    .load(data.get(position).getPhoto_url() != null ? data.get(position).getPhoto_url() : "")
                    .into(imgNews);
        }


        @OnClick
        public void itemClicked() {
            listener.itemClicked(data.get(getAdapterPosition()));
        }
    }

    public interface OnClickAdapter {
        void itemClicked(NewsModel model);
    }
}
