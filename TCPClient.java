import java.io.*;
import java.net.*;
import java.util.*;

public class TCPClient
{
	private static InetAddress host;
	private static final int PORT = 3210;

	public static void main(String[] args)
	{
		try
		{
			host = InetAddress.getLocalHost();
		}
		catch(UnknownHostException unknownhostEx)
		{
			System.out.println("Host ID not found!");
			System.exit(1);
		}
		accessServer();
	}

	private static void accessServer()
	{
		Socket link = null;						//Establish a connection to the server

		try
		{
			link = new Socket(host,PORT);		

			Scanner input = new Scanner(
								link.getInputStream());

			PrintWriter output =
				new PrintWriter(
					link.getOutputStream(),true);//Set up Input output streams - 

			//Set up stream for keyboard entry...
			Scanner userEntry = new Scanner(System.in);

			String message, response;
			do
			{
				System.out.print("Enter message: ");
				message =  userEntry.nextLine();
				output.println(message); 		//Send and receive data
				response = input.nextLine();	
				System.out.println("\nSERVER> " + response);
			}while (!message.equals("***CLOSE***"));
		}
		catch(IOException inputoutputEx)
		{
			inputoutputEx.printStackTrace();
		}

		finally
		{
			try
			{
				System.out.println("\n* Closing connection... Goodbye from Noori*");
				link.close();					//Close the connection .
			}
			catch(IOException inputoutputEx)
			{
				System.out.println("Unable to disconnect!");
				System.exit(1);
			}
		}
	}
}
