package Examples;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Example_Server1 {

	public static void main(String[] args) throws IOException {
		
		int port = 60000; 
		ServerSocket server = new ServerSocket(port);
				
		System.out.println("Listening on port: " + server.getLocalPort()); // server port
		
		Socket clientl = server.accept(); // wait UNTIL a client connects
		
		Socket client2 = server.accept(); // wait UNTIL a second client connects
		
		server.close(); // closing socket server
		
	}

}