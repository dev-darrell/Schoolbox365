package com.GadsMobileEdu22.School365.admin.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.GadsMobileEdu22.School365.admin.R;
import com.GadsMobileEdu22.School365.admin.databinding.DashboardRecyclerItemBinding;

import java.util.List;

public class DashboardRecyclerViewAdapter extends
        RecyclerView.Adapter<DashboardRecyclerViewAdapter.MyRecyclerViewHolder> {

    private Context context;
    private List<DashboardItem> dashboardItems;
    private OnDashBoardClickListener onDashBoardClickListener;

    DashboardRecyclerViewAdapter(Context context, List<DashboardItem> dashboardItems,
                                 OnDashBoardClickListener onDashBoardClickListener){
        this.context = context;
        this.dashboardItems = dashboardItems;
        this.onDashBoardClickListener = onDashBoardClickListener;
    }

    @NonNull
    @Override
    public MyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.dashboard_recycler_item, parent, false);
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
        private ImageView illustration;
        private TextView headingText;
        private OnDashBoardClickListener onDashBoardClickListener;

        public MyRecyclerViewHolder(@NonNull View itemView, OnDashBoardClickListener onDashBoardClickListener) {
            super(itemView);
            DashboardRecyclerItemBinding binding = new DashboardRecyclerItemBinding();
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
