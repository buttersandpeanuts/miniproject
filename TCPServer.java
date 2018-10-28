import java.io.*;
import java.net.*;
import java.util.*;

public class TCPServer
{
   private static ServerSocket serverSocket;
   private static final int PORT = 3210;    

   public static void main(String[] args)
   {
      System.out.println("Opening port...\n");
      try
      {
         serverSocket = new ServerSocket(PORT);     // create a ServerSocket
      }
      catch(IOException inputoutputEx)
      {
         System.out.println("Unable to attach to port!");
         System.exit(1);
      }
      do
      {
         handleClient();
      }while (true);
   }

   private static void handleClient()
   {
      Socket link = null;                        

      try
      {
         link = serverSocket.accept();              //Put the Server into a waiting state

         Scanner input = new Scanner(
			 				link.getInputStream()); //Set up input output streams
         PrintWriter output =
         		new PrintWriter(
                   	link.getOutputStream(),true); 

         int numMessages = 0;
         String message = input.nextLine();      // Send and receive data.
         while (!message.equals("***CLOSE***"))
         {
            System.out.println("Message received.");
            numMessages++;
            output.println("Message " + numMessages 
                          	+ ": " + message);  
            message = input.nextLine();
         }
         output.println(numMessages
						+ " messages received.");
		}
		catch(IOException inputoutputEx)
		{
			inputoutputEx.printStackTrace();
		}

		finally
		{
			try
			{
				System.out.println(
								"\n* Closing connection...Goodbye from N *"); 
				link.close();				    // close the connection after exchanging messages 
			}
			catch(IOException inputoutputEx)
			{
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}
