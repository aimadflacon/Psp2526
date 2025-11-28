package Examples;

import java.io.*;
import java.net.*; 

public class Example_Server2 { 
	
	public static void main(String[] arg) throws IOException { 
		
		int port = 60000; 
		ServerSocket server = new ServerSocket(port); 
		//Socket clientConnected = null; 
		System.out.println("*** SERVER launched ***"); 
		System.out.println("...Waiting for client..."); 
		Socket clientConnected = server.accept(); 
		
		// Server input stream 
		InputStream input = clientConnected.getInputStream(); 
		DataInputStream inputstream = new DataInputStream(input); 
		
		// Messages from client
		System.out.println("Receiving from CLIENT: \n - " + inputstream.readUTF()); 
		
		// Server output stream
		OutputStream output = clientConnected.getOutputStream(); 
		DataOutputStream outputstream = new DataOutputStream(output); 
		
		// Message to client
		System.out.println("...Responding to client...");
		outputstream.writeUTF("Hello CLIENT, I am the SERVER."); 
		
		// Closing streams and sockets
		input.close(); 
		inputstream.close(); 
		output.close(); 
		outputstream.close();
		clientConnected.close(); 
		server.close(); 
		System.out.println("*** SERVER closed ***"); 
		
	} 
	
}