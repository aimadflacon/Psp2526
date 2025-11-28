package Exercices;
import java.io.*;
import java.net.*;

public class Activity_1x03_Client { 

	public static void main(String[] args) throws Exception { 

		String host = "localhost"; 
		int port = 60000; //remote port 

		System.out.println("*** CLIENT launched ***"); 
		Socket client = new Socket(host, port); 
		System.out.println("Connected to server: " + host); 

		// Standard input stream (keyboard)
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in)); 
		int number; 

		// Output stream for the server
		DataOutputStream output = new DataOutputStream(client.getOutputStream()); 
		// Input stream for the server
		DataInputStream input = new DataInputStream(client.getInputStream()); 

		// Message to server
		System.out.println("Type an integer: "); 
		number = Integer.parseInt(in.readLine()); // keyboard reading
		output.writeInt(number); 

		// Message from server
		System.out.println("Received from SERVER: " + input.readInt()); 

		// Closing streams and sockets
		input.close(); 
		output.close(); 
		client.close(); 
		System.out.println("*** CLIENT closed ***");

	}

}