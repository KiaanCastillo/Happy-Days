package ca.bcit.snaydon.castillo.alvar.happydays;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BrowseItemAdapter extends ArrayAdapter<Route> {

    Context _context;
    ArrayList<Route> browseList;

    public BrowseItemAdapter(Context context, ArrayList<Route> browseList) {
        super(context, 0, browseList);
        this.browseList = browseList;
        _context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.browse_list, parent, false);
        }
        TextView tv_item = convertView.findViewById(R.id.browse_list_item);
        tv_item.setText(getItem(position).getName());
        // Return the completed view to render on screen
        return convertView;
    }
}
