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
	// private SocketListener listener; LAvet af hjælpelærer. Vent med at
	// slet.
	private BufferedReader bufferedReader;

	public ScaleConnection(String ip) {

		try {
			clientSocket = new Socket(ip, 8000);
			outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
			inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			// new Thread(listener).start();
		} catch (UnknownHostException e) {
			System.out.println("Could not connect to the specified IP");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error in constructor ScaleConnection");
			e.printStackTrace();
		}
	}

	@Override
	public int getInteger(String msg) throws scaleConnectionException {
		
		if(msg.length()>24){
			throw new scaleConnectionException("msg too long: \""+msg+"\"");
		}
		
		outToServer.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
		outToServer.flush();
		String readLine;
		try {
			readLine = readFromSocket();
			if (readLine.equals("RM20 A \"\"")) {
				getInteger(msg);
			} else if (readLine.startsWith("RM20 A")) {
				// Parse return string
				return Integer.valueOf(readLine.split("\"")[1]);
			} else if (readLine.startsWith("RM20 C")) {
				getInteger(msg);
			}else {
				throw new scaleConnectionException("unexpected answer readline = "+readLine+" msg = " + msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	@Override
	public double getMass() throws scaleConnectionException {
		outToServer.println("S");
		outToServer.flush();
		String readLine;
		try {
			readLine = readFromSocket();
			while (readLine.startsWith("ES")) {
				outToServer.println("S");
				outToServer.flush();
				readLine = readFromSocket();
			}
			return Double.valueOf(readLine.split("S")[2].split("kg")[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public double doTara() throws scaleConnectionException {
		outToServer.println("T");
		outToServer.flush();
		String readLine;
		try {
			readLine = readFromSocket();
			while (readLine.startsWith("ES")) {
				outToServer.println("T");
				outToServer.flush();
				readLine = readFromSocket();
			}
			return Double.valueOf(readLine.split("S")[1].split("kg")[0]);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public void setComponentName(String msg) throws scaleConnectionException {
		outToServer.println("P111 \"" + msg + "\"");
		outToServer.flush();
		try {
			inFromServer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void removeComponentName() throws scaleConnectionException {
		outToServer.println("P111 \"\"");
		outToServer.flush();
		try {
			inFromServer.readLine();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void displayMsg(String msg) throws scaleConnectionException {
		if(msg.length()>24){
			throw new scaleConnectionException("msg too long: \""+msg+"\"");
		}
		outToServer.println("RM20 8 \"" + msg + "\" \"\" \"&3\"");
		outToServer.flush();
		String readLine;
		try {
			readLine = readFromSocket();
			if (readLine.startsWith("RM20 A")) {
				//Do nothing
			} else if(!readLine.startsWith("RM20 A")) {
				//Do nothing
				displayMsg(msg);
			}else {
				throw new scaleConnectionException("unexpected answer");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setOperatorInitials(String operatorInitials) {
		outToServer.println("P112 1 \"" + operatorInitials + "\"");
		outToServer.flush();
		try {
			inFromServer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeOperatorInitials() {
		outToServer.println("P113 1");
		outToServer.flush();
		try {
			inFromServer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setProductBatchID(int productBatchID) {
		String PBId = String.valueOf(productBatchID);
		outToServer.println("P112 2 \"" + PBId + "\"");
		outToServer.flush();
		try {
			inFromServer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void removeProductBatchID() {
		outToServer.println("P113 2");
		outToServer.flush();
		try {
			inFromServer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// public String getInput(String msg) throws IOException, InputException{

	//
	// }
	//
	// public void showMessageToUser(String msg) {
	// outToServer.write("P111 " + msg);
	// }
	// public void showMessageToUserRM20(String msg) throws IOException {
	// outToServer.write("RM20 8 " + msg + "" );
	// outToServer.flush();
	// String readLine = readFromSocket();
	//
	// if (!"RM20 A".equals(readLine)){
	// String readLine2 = inFromServer.readLine();
	// //Parse return string
	//
	// }
	//
	// }
	//

	//
	//
	//// Skulle vi have brugt, men har ikke tid til at implementere det.
	//
	//// private class SocketListener implements Runnable {
	////
	//// private BufferedReader bufferedReader;
	//// private ScaleProcedure scaleController;
	////
	//// public SocketListener(InputStream inputStream, ScaleProcedure
	// scaleController) {
	//// bufferedReader = new BufferedReader(new
	// InputStreamReader(inputStream));
	//// this.scaleController = scaleController;
	//// }
	////
	//// @Override
	//// public void run() {
	//// while (true){
	//// try {
	//// String readLine = bufferedReader.readLine();
	//// } catch (IOException e) {
	//// // TODO Auto-generated catch block
	//// e.printStackTrace();
	//// }
	////
	//// }
	////
	//// }
	////
	//// }
	//
	//
	//
	// public double tara(){
	// outToServer.write("T");
	// return 0;
	//
	// }
	//// Hvad sker der hvis det nu ikke er et nummer? Så skal den spørger
	// igen.
	//
	// public int getNumber(String string) throws IOException, InputException {
	// // TODO Auto-generated method stub
	// outToServer.write("RM20 8 " + string + "" );
	// outToServer.flush();
	// String readLine = readFromSocket();
	// if ("RM20 B".equals(readLine)){
	// String readLine2 = inFromServer.readLine();
	// //Parse return string
	// return Integer.parseInt(readLine2.split("RM20 A")[1]);
	//
	// } else {
	// throw new InputException();
	// }
	//
	// }

	private String readFromSocket() throws IOException {
		String answerFromServer;
		try {
			answerFromServer = inFromServer.readLine();
			if (answerFromServer.startsWith("I4")) {
				answerFromServer = inFromServer.readLine();
			} else if (answerFromServer.startsWith("RM20 I")) {
				answerFromServer = inFromServer.readLine();
			}

			// IF the message is the (RM 20 8 "TEXT" "" "&3") type, the
			// following if statement is initiated.
			// this is done because the RM type of message is answered two
			// times, confirmation of message received,
			// and then the answer from the user.
			if (answerFromServer.startsWith("RM20 B")) {
				answerFromServer = inFromServer.readLine();
				return answerFromServer;
			} else {
				return answerFromServer;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in outputToServer method");
			return "";// this is only to fulfill compiler demands.
		}
	}

	public void setSoftKey() throws scaleConnectionException {
		
		outToServer.println("RM30 \"OK\"");
		String answerFromServer;
		try {
			answerFromServer = inFromServer.readLine();
			if (answerFromServer.startsWith("RM30 B")) {
				outToServer.println("RM39 1");
				inFromServer.readLine();
			} else {
				setSoftKey();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error in outputToServer method");
			// this is only to fulfill compiler demands.
		}
	}
		
		
		
//		outToServer.println("RM30 \"Ok\"");
//		outToServer.flush();
//		String answerFromServer;
//
//		try {
//			answerFromServer = inFromServer.readLine();
//
//			if (answerFromServer.startsWith("RM30 B")) {
//				outToServer.print("RM39 5");
//				outToServer.flush();
//				answerFromServer = inFromServer.readLine();
//				if (!answerFromServer.startsWith("RM39 A"))
//					setSoftBotton();
//			} else if (!answerFromServer.startsWith("RM30 B")) {
//				setSoftBotton();
//			} else {
//				throw new scaleConnectionException("unexpected answer setSoftBotton");
//			}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	

	@Override
	public void waitForAnswer() throws scaleConnectionException {
		// TODO Auto-generated method stub
		while (true) {
			String answerFromServer;

			try {
				answerFromServer = inFromServer.readLine();
				if (answerFromServer.startsWith("RM30")) {
					break;
				}
				Thread.currentThread().sleep(555);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void removeSoftKey() throws scaleConnectionException {
		
		outToServer.println("RM39 0");
		outToServer.flush();
		try {
			inFromServer.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public void num1() throws scaleConnectionException {
//		outToServer.println("RM30 \"OK\"");
//		outToServer.flush();
//		try {
//			readFromSocket();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public void num2() throws scaleConnectionException{
//		outToServer.println("RM39 1");
//		outToServer.flush();
//		try {
//			readFromSocket();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	
}
