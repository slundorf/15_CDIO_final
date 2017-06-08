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
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.println(inFromServer.readLine());
//			new Thread(listener).start();
		} catch (UnknownHostException e) {
			System.out.println("Could not connect to the specified IP");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error in constructor");
			e.printStackTrace();
		}
	}

	
	@Override
	public int getInteger(String msg) throws scaleConnectionException {
		outToServer.write("RM20 8 \"" + msg + "\" \"\" \"&3\"" );
		outToServer.flush();
		String readLine;
		try {
			readLine = readFromSocket();
			if ("RM20 B".equals(readLine)){
				String readLine2 = inFromServer.readLine();
				//Parse return string 
				return Integer.valueOf(readLine2.split("\"")[1]);	
			}else{
				throw new scaleConnectionException("unexpected answer");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public double getMass() throws scaleConnectionException {
		outToServer.write("S");
		outToServer.flush();
		String readLine;
		try{
			readLine = readFromSocket();
			return Double.valueOf(readLine.split("S")[1].split("kg")[0]);
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return 0;
	}

	@Override
	public double doTara() throws scaleConnectionException {
		outToServer.write("T");
		outToServer.flush();
		String readLine;
		try{
			readLine = readFromSocket();
			return Double.valueOf(readLine.split("S")[1].split("kg")[0]);
		}catch(IOException e){
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void setComponentName(String msg) throws scaleConnectionException {
		outToServer.write("P111 "+msg);
		outToServer.flush();
		try{
			readFromSocket();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void removeComponentName() throws scaleConnectionException {
		outToServer.write("P111 \"\"");
		outToServer.flush();
		try{
			readFromSocket();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void displayMsg(String msg) throws scaleConnectionException {
		outToServer.println("RM20 8 \"" + msg + "\" \"\" \"&3\"" );
		outToServer.flush();
		String readLine;
		try{
			readLine = readFromSocket();
			if ("RM20 B".equals(readLine)){
				String readLine2 = readFromSocket();	
			}else{
				throw new scaleConnectionException("unexpected answer");
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void setOperatorName(String operatorName){
		outToServer.write("P112 1 "+operatorName);
		outToServer.flush();
		try{
			readFromSocket();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void removeOperatorName(){
		outToServer.write("P113 1");
		outToServer.flush();
		try{
			readFromSocket();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void setProductBatchName(String productBatchName){
		outToServer.write("P112 2 "+productBatchName);
		outToServer.flush();
		try{
			readFromSocket();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void removeProductBatchName(){
		outToServer.write("P113 2");
		outToServer.flush();
		try{
			readFromSocket();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	
//	public String getInput(String msg) throws IOException, InputException{

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

	private String readFromSocket() throws IOException {
		String readLine = inFromServer.readLine();
	    while (readLine.startsWith("I4") || readLine.startsWith("RM20 I")){
			readLine = inFromServer.readLine();
		}
		return readLine;
	}
}
