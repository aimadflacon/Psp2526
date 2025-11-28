package Examples;

import java.io.*; 
import java.net.*; 

public class Example_Server3 { 
	
	public static void main(String[] arg) throws IOException { 
		
		int port = 60000; 
		ServerSocket server = new ServerSocket(port); 
		String data=""; 
		
		System.out.println("Server waiting for client..."); 
		Socket client = server.accept(); 
		System.out.println("Client connected..."); 
		
		// Output stream for client
		PrintWriter output = new PrintWriter (client.getOutputStream(),true); 
		
		// Input stream from client
		BufferedReader input = new BufferedReader (new InputStreamReader(client.getInputStream())); 
		
		while (!(data=input.readLine()).equals("*")) // receive data from client 
		{ 
			output.println(data); // send data to client
			System.out.println("Receiving: " + data);
		} 
		
		// Closing streams and sockets
		System.out.println("Received: *.\nClosing server..."); 
		input.close(); 
		output.close(); 
		client.close(); 
		server.close(); 
	
	} 
	
}