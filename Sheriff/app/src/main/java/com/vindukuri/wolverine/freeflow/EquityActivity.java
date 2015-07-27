package com.vindukuri.wolverine.freeflow;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.ArrayAdapter;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;

import java.util.ArrayList;

public class EquityActivity extends ActionBarActivity {

    private String stock;
    private CandleStickChart chart;
    private static final int delay = 60 * 1000;

    // Array adapters
    private ArrayAdapter<String> calls, puts;
    private ViewPager pager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equity);
        stock = "NFLX";

        // Set arrays
        calls = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        puts = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

        // Get viewpager and child arrays
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new OptionsFragmentAdapter(getSupportFragmentManager()));

        // Get options
        // new GetOptionsDataTask(calls, puts).execute(stock);

        // Config the chart at start up
        chart = (CandleStickChart) findViewById(R.id.equitygraph);
        chart.setNoDataText("Loading...");
        chart.setTouchEnabled(false);
        chart.setHighlightEnabled(true);
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setMaxVisibleValueCount(20);
        updateChart();
    }

    private void updateChart() {
        chart.setDescription(stock);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setSpaceBetweenLabels(2);
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setLabelCount(7);
        leftAxis.setDrawGridLines(false);
        leftAxis.setDrawAxisLine(false);
        leftAxis.setStartAtZero(false);

        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);

        new GetStockDataTask(chart).execute(stock);
    }

    public class OptionsFragmentAdapter extends FragmentStatePagerAdapter {
        public OptionsFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            OptionsFragment fragment = new OptionsFragment();
            if(i == 0) fragment.data = calls;
            else fragment.data = puts;
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if(position == 0) return "Calls";
            else return "Puts";
        }
    }
}
