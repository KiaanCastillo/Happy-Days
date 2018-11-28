package ca.bcit.snaydon.castillo.alvar.happydays;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

public class OnboardingActivity extends AppCompatActivity {

    private ViewPager onboardingViewPager;
    private LinearLayout dotsLayout;

    private SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        onboardingViewPager = (ViewPager) findViewById(R.id.onboarding_viewPager);
        dotsLayout = (LinearLayout) findViewById(R.id.dots_layout);

        sliderAdapter = new SliderAdapter(this);
        onboardingViewPager.setAdapter(sliderAdapter);

    }

}
