package Examples;

import java.io.*;
import java.net.*;

public class Example_Client2 { 
	
	public static void main(String[] args) throws Exception { 
		
		String host = "localhost"; 
		int port = 60000; //remote port 
		
		System.out.println("*** CLIENT launched ***"); 
		Socket client = new Socket(host, port); 
		
		// Output stream for the server
		DataOutputStream output = new DataOutputStream(client.getOutputStream()); 
		
		// Message to server
		System.out.println("...Greeting Server...");
		output.writeUTF("Hello SERVER, I am the CLIENT."); 
		
		// Input stream for the server
		DataInputStream input = new DataInputStream(client.getInputStream()); 
		
		// Message from server
		System.out.println("Receiving from SERVER: \n - " + input.readUTF()); 
		
		// Closing streams and sockets
		input.close(); 
		output.close(); 
		client.close(); 
		System.out.println("*** CLIENT closed ***");
		
	}
	
}