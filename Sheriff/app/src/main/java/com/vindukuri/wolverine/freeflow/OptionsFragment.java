package com.vindukuri.wolverine.freeflow;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by vindukuri on 7/24/2015.
 */
public class OptionsFragment extends ListFragment {
    public ArrayAdapter<String> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.optionsview, container, true);
        ListView listView = (ListView) view.findViewById(R.id.list);
        listView.setAdapter(data);
        return view;
    }
}
