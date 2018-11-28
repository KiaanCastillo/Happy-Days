package ca.bcit.snaydon.castillo.alvar.happydays;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {
        this.context = context;
    }

    public int[] slide_images = {
            R.drawable.tut0,
            R.drawable.tut1,
            R.drawable.tut2,
            R.drawable.tut3
    };

    public String[] slide_headings = {
            "Happy Days",
            "Take a Break",
            "Know Yourself",
            "Never Forget"
    };

    public String[] slide_text = {
            "Welcome to Happy Days, your personal\ncompanion for your journey in self care!",
            "Put work on pause and make way for\nsome love with many self-care\nactivities for you to choose from!",
            "Every time you finish an activity, rate how it goes! \nEach day has an overall mood that you can look\nback at to see how your happiness-level-per-day\nprogresses and which activities made it happen!",
            "No matter how busy you get, you need\nsome self-love and, luckily, we've got you\n covered with scheduled reminders \n based on your day's busy-ness level."
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (RelativeLayout) o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_title);
        TextView slideText = (TextView) view.findViewById(R.id.slide_text);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideText.setText(slide_text[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
