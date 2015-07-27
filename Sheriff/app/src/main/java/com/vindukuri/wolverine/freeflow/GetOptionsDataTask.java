package com.vindukuri.wolverine.freeflow;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;

/**
 * Created by vindukuri on 7/24/2015.
 */
public class GetOptionsDataTask extends AsyncTask<String, Void, Void> {

    private ArrayAdapter<String> calls, puts;

    public GetOptionsDataTask(ArrayAdapter<String> new_calls, ArrayAdapter<String> new_puts){
        this.calls = new_calls;
        this.puts = new_puts;
    }

    @Override
    protected Void doInBackground(String... params) {
        try {
            Thread.sleep(95000);
        } catch (InterruptedException e) { }
        return null;
    }

    @Override
    protected void onPostExecute(Void result){
        String[] data = new String[2];
        data[0] = "test";
        data[1] = "test2";
        calls.addAll(data);
        puts.addAll(data);

        calls.notifyDataSetChanged();
        puts.notifyDataSetChanged();
    }
}
