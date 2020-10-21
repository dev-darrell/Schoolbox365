package com.GadsMobileEdu22.Schoolbox365.ui.onboarding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.GadsMobileEdu22.Schoolbox365.MainActivity;
import com.GadsMobileEdu22.Schoolbox365.R;
import com.GadsMobileEdu22.Schoolbox365.databinding.OnboardingScreenLayoutBinding;
import com.GadsMobileEdu22.Schoolbox365.storage.SharedPreferenceStorage;

import java.util.ArrayList;

public class OnboardingPagerAdapter extends RecyclerView.Adapter<OnboardingPagerAdapter.MyViewHolder> {
    private Context mContext;
    private ArrayList<OnboardingScreen> mOnboardingScreens;

    public OnboardingPagerAdapter(Context context, ArrayList<OnboardingScreen> screenList){
        mContext = context;
        mOnboardingScreens = screenList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.onboarding_screen_layout,
                parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return mOnboardingScreens.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mIllustration;
        private final TextView mTitle;
        private final TextView mMessage;
        private final ImageView mDot1;
        private final ImageView mDot2;
        private final ImageView mDot3;
        private final Button mSkipBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            OnboardingScreenLayoutBinding binding = OnboardingScreenLayoutBinding.bind(itemView);

            mIllustration = binding.onboardIllusImgvw;
            mTitle = binding.onboardTitleTv;
            mMessage = binding.onboardMsgTv;
            mDot1 = binding.onboardDot1Imgvw;
            mDot2 = binding.onboardDot2Imgvw;
            mDot3 = binding.onboardDot3Imgvw;
            mSkipBtn = binding.onboardSkipBtn;

            mSkipBtn.setOnClickListener(view -> finishOnboarding(itemView.getContext()));

            binding.onboardNextBtn.setOnClickListener(view -> {
                if (OnBoardingActivity.mViewPager.getCurrentItem() == 2){
                    finishOnboarding(itemView.getContext());
                } else {
                    OnBoardingActivity.mViewPager.setCurrentItem(
                            OnBoardingActivity.mViewPager.getCurrentItem() + 1);
                }
            });
        }

        public void finishOnboarding(Context context) {
            SharedPreferences preferences = SharedPreferenceStorage.getPreferences();
            preferences.edit()
                    .putBoolean(MainActivity.ONBOARDING_COMPLETE, true)
                    .apply();

            Intent mainActIntent = new Intent(context, MainActivity.class);
            context.startActivity(mainActIntent);
        }

        public void bindData(int position) {
            OnboardingScreen currentScreen = mOnboardingScreens.get(position);
            if(!(position < 2)){
                mSkipBtn.setVisibility(View.GONE);
            }

            mIllustration.setImageResource(currentScreen.getMainIllustration());
            mTitle.setText(currentScreen.getTitle());
            mMessage.setText(currentScreen.getMessage());
            mDot1.setImageResource(currentScreen.getDotIndicator1());
            mDot2.setImageResource(currentScreen.getDotIndicator2());
            mDot3.setImageResource(currentScreen.getDotIndicator3());
        }
    }
}