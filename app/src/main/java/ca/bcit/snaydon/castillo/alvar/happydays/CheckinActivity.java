package ca.bcit.snaydon.castillo.alvar.happydays;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class CheckinActivity extends AppCompatActivity {

    private int checked_id;
    private ArrayList<Button> checkinBtns;
    private UserDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);
        checkinBtns = new ArrayList<>();
        checked_id = -1;

        Button checkinBtn1 = findViewById(R.id.checkin_superbusy);
        Button checkinBtn2 = findViewById(R.id.checkin_normal);
        Button checkinBtn3 = findViewById(R.id.checkin_chill);
        checkinBtns.add(checkinBtn1);
        checkinBtns.add(checkinBtn2);
        checkinBtns.add(checkinBtn3);

        dbHelper = new UserDatabaseHelper(this);
        User user = dbHelper.getUser();
        TextView tv = findViewById(R.id.home_first_name);
        tv.setText(user.getFirst_name());

    }

    public void checkin(View v) {
        switch (v.getId()) {
            case R.id.checkin_superbusy:
                checked_id = 3;
                break;
            case R.id.checkin_normal:
                checked_id = 2;
                break;
            default:
                checked_id = 1;
                break;
        }

        for (Button imgBtn : checkinBtns) {
            imgBtn.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary)));
        }
        Button selected = v.findViewById(v.getId());
        selected.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorAccent)));
    }

    public void submit(View v) {
        if (checked_id == -1) {
            Toast t = new Toast(this);
            t.makeText(this, "Please choose a day type", Toast.LENGTH_SHORT).show();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        String placeholder = "";

        Log newLog = new Log(month, day, year, checked_id, placeholder, placeholder, placeholder);
        dbHelper.insertLog(dbHelper.getReadableDatabase(), newLog);
        Intent i = new Intent(CheckinActivity.this, MainActivity.class);
        startActivity(i);
    }
}
