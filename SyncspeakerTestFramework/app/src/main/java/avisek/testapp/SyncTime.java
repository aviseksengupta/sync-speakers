package avisek.testapp;

import android.os.AsyncTask;
import android.util.Log;
import android.util.MutableDouble;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Created by user on 13/07/15.
 */
public class SyncTime extends AsyncTask<Object, Void, Long> {

    MutableDouble skew;
    MutableDouble lastRTT;
    @Override
    protected Long doInBackground(Object... params) {
        try
        {
            String remoteaddr = (String)params[0];
            skew = (MutableDouble)params[1];
            lastRTT = (MutableDouble)params[2];
            Socket timeClient = new Socket(remoteaddr, 5000);
            PrintWriter timeOutput = new PrintWriter(timeClient.getOutputStream(), true);
            BufferedReader timeInput = new BufferedReader(new InputStreamReader(timeClient.getInputStream()));
            timeOutput.println("TIME");
            Long serverTime = Long.parseLong(timeInput.readLine());

            return  serverTime;
        }
        catch(Exception e)
        {
            System.out.println("Error");
        }
        return 0L;
    }

    @Override
    protected void onPostExecute(Long serverTime) {
        Double skewTime = System.currentTimeMillis() - (serverTime +lastRTT.value/2);
        skew.value = skewTime.doubleValue();
        Log.d("Dummy", "Updated Sync to "+skewTime);
    }
}
