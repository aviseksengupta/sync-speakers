package avisek.testapp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
                BufferedReader readFromClient = new BufferedReader(new InputStreamReader(ssControlClient.getInputStream()));
                String initMessage = readFromClient.readLine();
                if(initMessage.equalsIgnoreCase("RTT"))
                {
                    Runnable echoserver = new EchoServer(ssControlClient);
                    Thread echoServerThread = new Thread(echoserver);
                    echoServerThread.start();
                }
                else if(initMessage.equalsIgnoreCase("TIME"))
                {
                    Runnable timeserver = new TimeServer(ssControlClient);
                    Thread timeserverThread = new Thread(timeserver);
                    timeserverThread.start();
                }
                Thread.currentThread().sleep(100);
            }
        } catch (Exception e) {
            Log.d("Dummy","Server Framework Error: "+e.getMessage());
        }
    }
}
