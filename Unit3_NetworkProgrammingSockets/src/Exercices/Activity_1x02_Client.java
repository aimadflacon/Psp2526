package Exercices;

import java.io.*;
import java.net.*;

public class Activity_1x02_Client { 

	public static void main(String[] args) throws Exception { 

		String host = "localhost"; 
		int port = 60000; //remote port 

		System.out.println("*** CLIENT launched ***"); 
		Socket client = new Socket(host, port); 
		System.out.println("Connected to server: " + host); 

		// Standard input stream (keyboard)
		BufferedReader in = new BufferedReader (new InputStreamReader(System.in)); 
		String data; 

		// Output stream for the server
		DataOutputStream output = new DataOutputStream(client.getOutputStream()); 
		// Input stream for the server
		DataInputStream input = new DataInputStream(client.getInputStream()); 

		// Message to server
		System.out.println("Type a message: "); 
		data = in.readLine(); // keyboard reading
		output.writeUTF(data); 

		// Message from server
		System.out.println("Received from SERVER: " + input.readUTF()); 

		// Closing streams and sockets
		input.close(); 
		output.close(); 
		client.close(); 
		System.out.println("*** CLIENT closed ***");

	}

}