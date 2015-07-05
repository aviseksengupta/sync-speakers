package ss.testframework;
import java.io.*;
import java.net.Socket;
import java.util.Date;

/**
 * Created by user on 04/07/15.
 */
public class ControlClientFramework {
    public static void main(String args[])throws Exception
    {
        System.out.println("Average Rtt: "+calculateRoundTripTime());
    }
    public static long calculateRoundTripTime()
    {
        try
        {
            Socket rttClient = new Socket("localhost", 5256);
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
                avgRTT = avgRTT+(currentTimestamp-initTimestamp);
                i--;
            }
            return  avgRTT/10;
        }
        catch(Exception e)
        {
            System.out.println("Error");
        }
        return 0;
    }
}
