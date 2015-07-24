package avisek.testapp;

import android.os.AsyncTask;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by user on 16/07/15.
 */
public class UpdateClock implements Runnable{
//    Double skewInMillis;
//    String remoteAddr;
//
//    public UpdateClock(Double skewInMillis, String remoteAddr)
//    {
//        this.skewInMillis = skewInMillis;
//    }
//
//    @Override
//    public void run() {
//
//        Double rtt = new Double(0.0);
//        new CalculateRTT().execute(new Object[]{remoteAddr, rtt});
//
//
//        synchronized (skewInMillis)
//        {
//
//        }
//    }

    Handler timeUpdateHandler;
    UpdateClock(Handler timeUpdateHandler)
    {
        this.timeUpdateHandler = timeUpdateHandler;
    }
    @Override
    public void run() {
        while (true)
        {
            try
            {
                Long timeInMillis = System.currentTimeMillis();

//                Bundle bundle = new Bundle();
//                bundle.putString("time", timeInMillis.toString());
//                Message message = new Message();
//                message.setData(bundle);
//                timeUpdateHandler.sendMessage(message);

                Log.d("Dummy", "TIme is "+timeInMillis.toString());
                Message message = timeUpdateHandler.obtainMessage(1, timeInMillis.toString());
                message.sendToTarget();
                Thread.sleep(500);
            }
            catch(Exception e)
            {

            }
        }
    }
}
