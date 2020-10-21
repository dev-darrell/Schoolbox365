package com.GadsMobileEdu22.Schoolbox365.ui.onboarding;


public class OnboardingScreen {
    private int title;
    private int message;
    private int mainIllustration;
    private int dotIndicator1;
    private int dotIndicator2;
    private int dotIndicator3;


    public OnboardingScreen(){ }


    public OnboardingScreen(int title, int message, int mainIllustration,
                            int dotIndicator1, int dotIndicator2, int dotIndicator3) {
        this.title = title;
        this.message = message;
        this.mainIllustration = mainIllustration;
        this.dotIndicator1 = dotIndicator1;
        this.dotIndicator2 = dotIndicator2;
        this.dotIndicator3 = dotIndicator3;
    }

    public int getTitle() {
        return title;
    }

    public int getMessage() {
        return message;
    }

    public int getMainIllustration() {
        return mainIllustration;
    }

    public int getDotIndicator1() {
        return dotIndicator1;
    }

    public int getDotIndicator2() {
        return dotIndicator2;
    }

    public int getDotIndicator3() {
        return dotIndicator3;
    }
}
