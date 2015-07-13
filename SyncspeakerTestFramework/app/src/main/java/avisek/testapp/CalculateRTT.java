package avisek.testapp;

import android.os.AsyncTask;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

/**
 * Created by user on 13/07/15.
 */
public class CalculateRTT extends AsyncTask<Object, Void, Double> {

    TextView message;
    @Override
    protected Double doInBackground(Object... params) {
        try
        {
            String remoteaddr = (String)params[0];
            message = (TextView)params[1];
            Socket rttClient = new Socket(remoteaddr, 5000);
            PrintWriter rttOutput = new PrintWriter(rttClient.getOutputStream(), true);
            BufferedReader rttInput = new BufferedReader(new InputStreamReader(rttClient.getInputStream()));
            int i = 10;
            long avgRTT = 0;
            while(i>0)
            {
                Date initDate = new Date();
                rttOutput.println(initDate.getTime());
                long initTimestamp = Long.parseLong(rttInput.readLine());
                long currentTimestamp = new Date().getTime();
                System.out.println(currentTimestamp-initTimestamp);
                avgRTT = avgRTT+(currentTimestamp-initTimestamp+1);
                i--;
            }
            return  avgRTT/10.0;
        }
        catch(Exception e)
        {
            System.out.println("Error");
        }
        return 0.0;
    }

    @Override
    protected void onPostExecute(Double rtt) {
        this.message.setText("Average RTT: "+ rtt);
    }
}
