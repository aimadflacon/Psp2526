package Examples;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Example_Client1 {

	public static void main(String[] args) throws IOException {
		
		String host = "localhost"; 
		int port = 60000;// remote port
		
		// OPEN SOCKET 
		Socket client = new Socket(host, port); 
		
		InetAddress i= client.getInetAddress (); // to get info from server 
		
		System.out.println("Local port: "+ client.getLocalPort()); // port of client
		
		System.out.println("Remote port: "+ client.getPort());  // port of server
		
		System.out.println("Remote host: "+ i.getHostName().toString());  // name of host
		
		System.out.println("Remote host IP: "+ i.getHostAddress().toString()); // IP of host
		
		client.close();// Closes socket
		    
	}
	
}