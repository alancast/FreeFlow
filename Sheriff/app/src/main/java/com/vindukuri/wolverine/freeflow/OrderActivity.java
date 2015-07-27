package com.vindukuri.wolverine.freeflow;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import android.util.LogPrinter;


public class OrderActivity extends Activity {

    private Button button;
    private Spinner spinner;
    private EditText price ;
    private EditText ticker ;
    private EditText quantity ;

    URL url = null;
    URLConnection urlConnection = null;
    BufferedOutputStream os = null;

    LogPrinter printer = new LogPrinter(5, "SHERIFF");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_order);

        spinner = (Spinner) findViewById(R.id.spinner);
        button = (Button) findViewById(R.id.send_button);
        price = (EditText) findViewById(R.id.price_text);
        quantity = (EditText) findViewById(R.id.quantity_text);
        ticker = (EditText) findViewById(R.id.ticker_text);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.order_types, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void send_order(View v) {
        printer.println("Send order clicked!");
        String text = new String();
        text = text + "price=" + price.getText() + "\n";
        text = text + "quantity=" + quantity.getText() + "\n";
        text = text + "symbol=" + ticker.getText() + "\n" ;
        text = text + "side=buy\n" ;
        text = text + "ask_price=1\n" ;
        text = text + "bid_price=1\n" ;
        text = text + "ask_size=1\n" ;
        text = text + "bid_size=1\n";
        text = text + "~";

        data = text.getBytes();
        Thread thread = new Thread(runner);
        thread.start();
    }

    byte[] data;
    private Runnable runner = new Runnable() {
        @Override
        public void run() {
            try {
                //url = new URL("http://10.0.2.2:9999");
                //urlConnection = url.openConnection();
                //os = new BufferedOutputStream(urlConnection.getOutputStream()) ;
                Socket socket = new Socket("172.26.48.212", 9999);

                os = new BufferedOutputStream(socket.getOutputStream());
                os.write(data, 0, data.length);
                os.flush();
                printer.println(new String(data));

            } catch (IOException e){ printer.println(e.getMessage());}
        }
    };
}
