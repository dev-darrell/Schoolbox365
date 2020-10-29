package com.GadsMobileEdu22.School365.admin.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.GadsMobileEdu22.Schoolbox365.admin.R;
import com.GadsMobileEdu22.Schoolbox365.admin.databinding.DashboardNewsItemBinding;

import java.util.List;

public class NewsPagerAdapter extends RecyclerView.Adapter<NewsPagerAdapter.MyPagerViewHolder> {

    private Context context;
    private List<NewsItem> newsItems;
    private OnNewsClickListener onNewsClickListener;
    public NewsPagerAdapter(Context context, List<NewsItem> newsItems, OnNewsClickListener onNewsClickListener) {
        this.context = context;
        this.newsItems = newsItems;
        this.onNewsClickListener = onNewsClickListener;
    }

    @NonNull
    @Override
    public MyPagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dashboard_news_item, parent, false);
        return new MyPagerViewHolder(view, onNewsClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyPagerViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public class MyPagerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView newsIllustration;
        private TextView newsHeading;
        private OnNewsClickListener onNewsClickListener;
        private final DashboardNewsItemBinding mBinding;

        public MyPagerViewHolder(@NonNull View itemView, OnNewsClickListener onNewsClickListener) {
            super(itemView);
            mBinding = DashboardNewsItemBinding.bind(itemView);

            newsIllustration = mBinding.newsIllusImgvw;
            newsHeading = mBinding.newsHeadingTv;
            this.onNewsClickListener = onNewsClickListener;

            itemView.setOnClickListener(this);
        }

        public void bindData(int position) {
            NewsItem currentItem = newsItems.get(position);
            newsIllustration.setImageResource(currentItem.getNewsImage());
            newsHeading.setText(currentItem.getHeading());
            mBinding.dotIndicator1.setImageResource(currentItem.getDot1());
            mBinding.dotIndicator2.setImageResource(currentItem.getDot2());
            mBinding.dotIndicator3.setImageResource(currentItem.getDot3());
            mBinding.dotIndicator4.setImageResource(currentItem.getDot4());
            mBinding.dotIndicator5.setImageResource(currentItem.getDot5());
        }

        @Override
        public void onClick(View view) {
            onNewsClickListener.onNewsClick(getAdapterPosition());
        }
    }

    public interface OnNewsClickListener {
        void onNewsClick(int position);
    }
}
