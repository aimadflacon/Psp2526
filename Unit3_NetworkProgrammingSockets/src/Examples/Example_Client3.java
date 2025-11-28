package Examples;

import java.io.*; 
import java.net.*;

public class Example_Client3 { 
	
	public static void main(String[] args) throws IOException { 
		
		String host = "localhost"; 
		int port = 60000;// remote port (server)
		Socket client = new Socket(host, port); 
		
		// Output stream for server
		PrintWriter output = new PrintWriter (client.getOutputStream(), true); 
		
		// Input stream from server
		BufferedReader input = new BufferedReader (new InputStreamReader(client.getInputStream())); 
		
		// Standard input stream (keyboard)
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in)); 
		String data; 
		System.out.print("Type a message. [Use * to end communications]: "); 
		data = in.readLine(); // keyboard reading
		while(!data.equals("*")){ 
			output.println(data); // send data to server 
			data=input.readLine(); // received data from server
			System.out.println("---> SERVER received: " + data); 
			System.out.print("Type a message (string). [Use * to end communications]: "); 
			data = in.readLine(); // keyboard reading again 
		}
		output.println(data);
		
		// Closing streams and sockets
		output.close(); 
		input.close(); 
		System.out.println("Ending communication..."); 
		in.close(); 
		client.close(); 
		
	} 
	
}