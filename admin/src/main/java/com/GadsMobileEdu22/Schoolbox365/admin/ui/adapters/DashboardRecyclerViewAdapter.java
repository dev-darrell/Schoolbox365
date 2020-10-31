package com.GadsMobileEdu22.Schoolbox365.admin.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.GadsMobileEdu22.Schoolbox365.admin.R;
import com.GadsMobileEdu22.Schoolbox365.admin.databinding.DashboardRecyclerItemBinding;
import com.GadsMobileEdu22.Schoolbox365.admin.ui.dashboard.DashboardItem;

import java.util.List;

public class DashboardRecyclerViewAdapter extends
        RecyclerView.Adapter<DashboardRecyclerViewAdapter.MyRecyclerViewHolder> {

    private final List<DashboardItem> dashboardItems;
    private final OnDashBoardClickListener onDashBoardClickListener;

    public DashboardRecyclerViewAdapter(List<DashboardItem> dashboardItems,
                                        OnDashBoardClickListener onDashBoardClickListener){
        this.dashboardItems = dashboardItems;
        this.onDashBoardClickListener = onDashBoardClickListener;
    }

    @NonNull
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dashboard_recycler_item, parent, false);
        return new MyRecyclerViewHolder(view, onDashBoardClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return dashboardItems.size();
    }


    public class MyRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView illustration;
        private final TextView headingText;
        private final OnDashBoardClickListener onDashBoardClickListener;

        public MyRecyclerViewHolder(@NonNull View itemView, OnDashBoardClickListener onDashBoardClickListener) {
            super(itemView);
            DashboardRecyclerItemBinding binding = DashboardRecyclerItemBinding.bind(itemView);
            illustration = binding.dashboardCardIllusImgvw;
            headingText = binding.dashboardCardTextTv;
            this.onDashBoardClickListener = onDashBoardClickListener;

            itemView.setOnClickListener(this);
        }

        public void bindData(int position) {
            DashboardItem currentItem = dashboardItems.get(position);
            illustration.setImageResource(currentItem.getItemIllustrationRes());
            headingText.setText(currentItem.getItemText());
        }

        @Override
        public void onClick(View view) {
            onDashBoardClickListener.onDashboardClick(getAdapterPosition());
        }
    }

    public interface OnDashBoardClickListener {
        void onDashboardClick(int position);
    }
}
