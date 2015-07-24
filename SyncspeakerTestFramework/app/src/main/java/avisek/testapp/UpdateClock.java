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
                //Long syncedTime = System.currentTimeMillis();
//                Bundle bundle = new Bundle();
//                bundle.putString("time", timeInMillis.toString());
//                Message message = new Message();
//                message.setData(bundle);
//                timeUpdateHandler.sendMessage(message);

                //og.d("Dummy", "Time is "+timeInMillis.toString());
                Message message = timeUpdateHandler.obtainMessage(1, timeInMillis.toString());
                message.sendToTarget();
                //Message message1 = timeUpdateHandler.obtainMessage(1, syncedTime.toString());
                //message1.sendToTarget();
                Thread.sleep(500);
            }
            catch(Exception e)
            {

            }
        }
    }
}
