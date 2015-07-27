package com.vindukuri.wolverine.freeflow;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.AsyncTask;

import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;

import java.util.ArrayList;

/**
 * Created by vindukuri on 7/24/2015.
 */
public class GetStockDataTask extends AsyncTask<String, Void, StockData>{
    private CandleStickChart chart;
    public GetStockDataTask(CandleStickChart chart_new){
        this.chart = chart_new;
    }

    private void addData(StockData data){
        int prog = 50;
        chart.resetTracking();

        ArrayList<CandleEntry> yVals1 = new ArrayList<CandleEntry>();

        for (int i = 0; i < prog; i++) {
            float mult = 50;
            float val = (float) (Math.random() * 40) + mult;
            float high = (float) (Math.random() * 9) + 8f;
            float low = (float) (Math.random() * 9) + 8f;
            float open = (float) (Math.random() * 6) + 1f;
            float close = (float) (Math.random() * 6) + 1f;

            boolean even = i % 2 == 0;
            yVals1.add(new CandleEntry(i, val + high, val - low, even ? val + open : val - open,
                    even ? val - close : val + close));
        }

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < prog; i++) {
            xVals.add("" + (1990 + i));
        }

        CandleDataSet set1 = new CandleDataSet(yVals1, "Data Set");
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
//        set1.setColor(Color.rgb(80, 80, 80));
        set1.setShadowColor(Color.DKGRAY);
        set1.setShadowWidth(Color.rgb(0xff, 0x44, 0x44));
        set1.setDecreasingColor(Color.RED);
        set1.setDecreasingPaintStyle(Paint.Style.STROKE);
        set1.setIncreasingColor(Color.rgb(0x0, 0x96, 0x88));
        set1.setIncreasingPaintStyle(Paint.Style.FILL);
        //set1.setHighlightLineWidth(1f);

        CandleData candle_data = new CandleData(xVals, set1);
        chart.setData(candle_data);
        chart.invalidate();
    }

    @Override
    protected StockData doInBackground(String... params) {
        String symbol = params[0];
        try { Thread.sleep(2000); } catch (InterruptedException e) { }
        StockData data = new StockData();
        return data;
    }

    @Override
    protected void onPostExecute(StockData result){
        addData(result);
    }
}


