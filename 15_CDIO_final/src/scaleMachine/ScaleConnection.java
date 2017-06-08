package scaleMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import exceptions.scaleConnectionException;

public class ScaleConnection implements IScaleConnection {

	String ip;
	BufferedReader inFromUser;

	Socket clientSocket;
	PrintWriter outToServer;
	BufferedReader inFromServer;
//	private SocketListener listener; LAvet af hjælpelærer. Vent med at slet.
	private BufferedReader bufferedReader;

	public ScaleConnection(String ip) {

		try {
			clientSocket = new Socket(ip, 8000);
			outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
			bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//			new Thread(listener).start();
		} catch (UnknownHostException e) {
			System.out.println("Could not connect to the specified IP");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error in constructor");
			e.printStackTrace();
		}
	}
	
//	public String getInput(String msg) throws IOException, InputException{
//		outToServer.write("RM20 8 " + msg + "" );
//		outToServer.flush();
//		String readLine = readFromSocket();
//		if ("RM20 B".equals(readLine)){
//			String readLine2 = inFromServer.readLine();
//			//Parse return string 
//			return readLine2.split("RM20 A")[1];
//			
//		} else {
//			throw new InputException();
//		}
//		
//	}
//	
//	public void showMessageToUser(String msg) {
//		outToServer.write("P111 " + msg);
//	}
//	public void showMessageToUserRM20(String msg) throws IOException {
//		outToServer.write("RM20 8 " + msg + "" );
//		outToServer.flush();
//		String readLine = readFromSocket();
//		
//		if (!"RM20 A".equals(readLine)){
//			String readLine2 = inFromServer.readLine();
//			//Parse return string 
//			
//		}
//		
//	}
//
//	private String readFromSocket() throws IOException {
//		String readLine = inFromServer.readLine();
//	    while (readLine.startsWith("IA") || readLine.startsWith("RM20 I")){
//			readLine = inFromServer.readLine();
//		}
//		return readLine;
//	}
//	
//	
////	Skulle vi have brugt, men har ikke tid til at implementere det.
//	
////	private class SocketListener implements Runnable {
////		
////		private BufferedReader bufferedReader;
////		private ScaleProcedure scaleController;
////
////		public SocketListener(InputStream inputStream, ScaleProcedure scaleController) {
////			bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
////			this.scaleController = scaleController;
////		}
////
////		@Override
////		public void run() {
////			while (true){
////				try {
////					String readLine = bufferedReader.readLine();
////				} catch (IOException e) {
////					// TODO Auto-generated catch block
////					e.printStackTrace();
////				}
////				
////			}
////			
////		}
////		
////	}
//
//
//
//	public double tara(){
//		outToServer.write("T");
//		return 0;
//		
//	}
//// Hvad sker der hvis det nu ikke er et nummer? Så skal den spørger igen.
//	
//	public int getNumber(String string) throws IOException, InputException {
//		// TODO Auto-generated method stub
//		outToServer.write("RM20 8 " + string + "" );
//		outToServer.flush();
//		String readLine = readFromSocket();
//		if ("RM20 B".equals(readLine)){
//			String readLine2 = inFromServer.readLine();
//			//Parse return string 
//			return Integer.parseInt(readLine2.split("RM20 A")[1]);
//			
//		} else {
//			throw new InputException();
//		}
//		
//	}

@Override
public String getString(String msg) throws scaleConnectionException {
	// TODO Auto-generated method stub
	return null;
}

@Override
public int getMass(String msg) throws scaleConnectionException {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public int doTara() throws scaleConnectionException {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public void setBottomMsg(String msg) throws scaleConnectionException {
	// TODO Auto-generated method stub
	
}

@Override
public void removeBottomMsg() throws scaleConnectionException {
	// TODO Auto-generated method stub
	
}

@Override
public void displayTemporaryMsg(String msg) throws scaleConnectionException {
	// TODO Auto-generated method stub
	
}

}
