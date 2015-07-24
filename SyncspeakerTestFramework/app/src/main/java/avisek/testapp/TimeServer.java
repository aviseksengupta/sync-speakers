package avisek.testapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by user on 13/07/15.
 */
public class TimeServer implements Runnable {
    BufferedReader in;
    PrintWriter out;
    Socket clientSocket;

    public TimeServer(Socket client)
    {
        clientSocket = client;
    }
    public void run()
    {
        try
        {
            PrintWriter writeToClient = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader readFromClient = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writeToClient.println(System.currentTimeMillis());
            clientSocket.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
