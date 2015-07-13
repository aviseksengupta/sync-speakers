package ss.testframework;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.ServerSocket;
/**
 * Created by user on 04/07/15.
 */
public class ControlServerFramework {

    public static void main(String args[]) throws IOException, InterruptedException {

        ServerSocket ssControlServer  = new ServerSocket(5256);
        while(true)
        {
            Socket ssControlClient = ssControlServer.accept();

            Runnable echoserver = new EchoServerThread(ssControlClient);
            Thread echoServerThread = new Thread(echoserver);
            echoServerThread.start();
            Thread.currentThread().sleep(100);
            System.out.println("Client Accepted");
        }

    }
}
