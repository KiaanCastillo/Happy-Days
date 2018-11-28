package ca.bcit.snaydon.castillo.alvar.happydays;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_menu, null);

        Button about = v.findViewById(R.id.menu_abt);
        Button devs = v.findViewById(R.id.menu_developers);

        about.setOnClickListener(this);
        devs.setOnClickListener(this);

        return v;
    }

    public void onClick(View v) {
        String URL;
        if (v.getId() == R.id.menu_abt)
            URL = "http://www.bcit.ca";
        else
            URL = "https://github.com/alvarviktor";

        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        browserIntent.setData(Uri.parse(URL));
        startActivity(browserIntent);
    }
}
