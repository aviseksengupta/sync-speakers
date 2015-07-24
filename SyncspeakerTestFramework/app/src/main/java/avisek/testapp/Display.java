package avisek.testapp;

import android.os.Looper;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Handler;
import android.os.Message;

import org.w3c.dom.Text;

import java.net.ServerSocket;
import java.net.Socket;


public class Display extends ActionBarActivity {

    static Double lastRTT;
    static Double skew;

    //Time Updation Handler
    public Handler timeUpdationHandler = new Handler(Looper.getMainLooper()){
        public void handleMessage(Message msg)
        {
            String message = (String)msg.obj;
            TextView timeText = (TextView)findViewById(R.id.curSysTime);
            //String message1 = (String)msg.obj;
            TextView syncedTimeText = (TextView)findViewById(R.id.syncTime);
            Long syncedTime = Math.round(Long.parseLong(message) + skew);
            timeText.setText(message);
            syncedTimeText.setText(syncedTime.toString());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Initialize globals
        lastRTT = new Double(0);
        skew = new Double(0);

        setContentView(R.layout.activity_display);

        Log.d("Dummy", "Application Started");
        Runnable serverFramework = new ServerFramework();
        new Thread(serverFramework).start();

        Thread updateDisplayedClock = new Thread(new UpdateClock(timeUpdationHandler));
        updateDisplayedClock.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void calcRTT(View view)
    {
        EditText remoteAddrInput = (EditText)findViewById(R.id.ipaddr);
        String remoteAddr = remoteAddrInput.getText().toString();
        TextView messageDisp = (TextView)findViewById(R.id.message_view);
        messageDisp.setText("Calculating... ");
        TextView offsetDisp = (TextView)findViewById(R.id.timeOffset);
        new CalculateRTT().execute(new Object[]{remoteAddr, messageDisp, lastRTT});
    }

    public void updateSkew(View view)
    {
        EditText remoteAddrInput = (EditText)findViewById(R.id.ipaddr);
        String remoteAddr = remoteAddrInput.getText().toString();
    }
}
