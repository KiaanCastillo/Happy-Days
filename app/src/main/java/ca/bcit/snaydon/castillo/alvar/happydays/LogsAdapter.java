package ca.bcit.snaydon.castillo.alvar.happydays;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LogsAdapter extends ArrayAdapter<Log> {
    Context context;

    public LogsAdapter(Context context, ArrayList<Log> countries) {
        super(context, 0, countries);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final android.app.Activity activity = (Activity) context;
        // Get the data item for this position
        Country country = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_row_layout, parent, false);
        }
        // Lookup view for data population
        TextView countryName = (TextView) convertView.findViewById(R.id.countryName);

        // Populate the data into the template view using the data object
        countryName.setText(country.getName());


//        ImageView imgOnePhoto = (ImageView) convertView.findViewById(R.id.thumbImage);
//        if (country.getFlag() != null) {
//            new ImageDownloaderTask(imgOnePhoto).execute(country.getFlag());
//        }

        // Return the completed view to render on screen
        return convertView;
}
