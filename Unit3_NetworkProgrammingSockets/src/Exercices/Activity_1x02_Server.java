package Exercices;
import java.io.*;
import java.net.*; 

public class Activity_1x02_Server { 

	public static void main(String[] arg) throws IOException { 

		int port = 60000; 
		ServerSocket server = new ServerSocket(port); 
		//Socket clientConnected = null; 
		System.out.println("*** SERVER launched ***"); 
		System.out.println("...Waiting for client..."); 
		Socket clientConnected = server.accept(); 
		System.out.println("...Client connected..."); 

		// Server input stream 
		InputStream input = clientConnected.getInputStream(); 
		DataInputStream inputstream = new DataInputStream(input); 

		// Server output stream
		OutputStream output = clientConnected.getOutputStream(); 
		DataOutputStream outputstream = new DataOutputStream(output); 

		String data;
		data=inputstream.readUTF();
		// Messages from client
		System.out.println("Received from CLIENT: " + data); 

		// Message to client
		System.out.println("Sending to CLIENT: " + data.toUpperCase());
		outputstream.writeUTF(data.toUpperCase()); 

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