package scaleMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ScaleConnection {

	String ip;
	BufferedReader inFromUser;

	Socket clientSocket;
	PrintWriter outToServer;
	BufferedReader inFromServer;

	public ScaleConnection(String ip) {
		// TODO Auto-generated constructor stub

		// initializing Reader and operator array, batch array.
		inFromUser = new BufferedReader(new InputStreamReader(System.in));

		try {
			// String ip = inFromUser.readLine();
			clientSocket = new Socket(ip, 8000);
			outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		} catch (UnknownHostException e) {
			System.out.println("Could not connect to the specified IP");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error in constructor");
			e.printStackTrace();
		}
	}

}
