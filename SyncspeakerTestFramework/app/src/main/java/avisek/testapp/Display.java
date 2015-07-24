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

import java.net.ServerSocket;
import java.net.Socket;


public class Display extends ActionBarActivity {

    static Double skewInMillis;

    //Time Updation Handler
    public Handler timeUpdationHandler = new Handler(Looper.getMainLooper()){
        public void handleMessage(Message msg)
        {
            String message = (String)msg.obj;
            TextView timeText = (TextView)findViewById(R.id.curSysTime);

            timeText.setText(message+ "HELLO!!");
            Log.d("Dummy", "debug thy lord: "+message);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        new CalculateRTT().execute(new Object[]{remoteAddr, messageDisp});
    }
}
