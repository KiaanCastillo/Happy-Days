package ca.bcit.snaydon.castillo.alvar.happydays;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnboardingActivity extends AppCompatActivity {

    public static final int NUM_SLIDES = 4;

    private ViewPager onboardingViewPager;
    private LinearLayout dotsLayout;

    private TextView[] dots;

    private SliderAdapter sliderAdapter;

    private Button nextBtn;
    private Button backBtn;

    private int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        onboardingViewPager = (ViewPager) findViewById(R.id.onboarding_viewPager);
        dotsLayout = (LinearLayout) findViewById(R.id.dots_layout);

        nextBtn = (Button) findViewById(R.id.slide_next_btn);
        backBtn = (Button) findViewById(R.id.slide_back_btn);

        sliderAdapter = new SliderAdapter(this);
        onboardingViewPager.setAdapter(sliderAdapter);

        addDotsIndicator(0);
        onboardingViewPager.addOnPageChangeListener(viewListener);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nextBtn.getText().toString().equals("Get Started")) {
                    Intent i = new Intent(OnboardingActivity.this, UserInitializationActivity.class);
                    startActivity(i);
                } else {
                    onboardingViewPager.setCurrentItem(currentPage + 1);
                }
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onboardingViewPager.setCurrentItem(currentPage - 1);
            }
        });
    }

    public void addDotsIndicator(int position) {
        dots = new TextView[NUM_SLIDES];
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorPrimary));

            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDotsIndicator(i);

            currentPage = i;
            if (currentPage == 0) {
                nextBtn.setEnabled(true);
                backBtn.setEnabled(false);
                backBtn.setVisibility(View.INVISIBLE);

                nextBtn.setText(R.string.next_btn);
                backBtn.setText("");
            } else if (i == dots.length - 1) {
                nextBtn.setEnabled(true);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);

                nextBtn.setText(R.string.get_started_btn);
                backBtn.setText(R.string.back_btn);
            } else {
                nextBtn.setEnabled(true);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);

                nextBtn.setText(R.string.next_btn);
                backBtn.setText(R.string.back_btn);
            }
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

}
