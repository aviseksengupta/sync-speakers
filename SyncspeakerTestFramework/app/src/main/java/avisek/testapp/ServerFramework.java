package avisek.testapp;

import android.util.Log;

import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by user on 13/07/15.
 */
public class ServerFramework implements Runnable{

    @Override
    public void run(){
        Log.d("Dummy", "Server is started");
        try {
            ServerSocket ssControlServer = new ServerSocket(5000);
            Log.d("Dummy", "Server to accept connections");
            while (true) {
                Socket ssControlClient = ssControlServer.accept();

                Runnable echoserver = new EchoServer(ssControlClient);
                Thread echoServerThread = new Thread(echoserver);
                echoServerThread.start();
                Thread.currentThread().sleep(100);
            }
        } catch (Exception e) {
            Log.d("Dummy","Server Framework Error: "+e.getMessage());
        }
    }
}
