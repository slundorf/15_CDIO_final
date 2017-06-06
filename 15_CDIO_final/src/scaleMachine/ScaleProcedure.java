package scaleMachine;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;

import dto.ProductBatchDTO;
import dto.UserDTO;
import interfaces.IProductBatchDAO;
import interfaces.IUserDAO;
import serDAO.SerProductBatchDAO;
import serDAO.SerUserDAO;

public class ScaleProcedure extends Thread {

	String answerFromServer = null;
	String answer;
	boolean existed;
	int i = 0;
	double taraWeight;
	double nettoWeight;
	double bruttoWeight;
	IUserDAO users = new SerUserDAO();
	IProductBatchDAO batches = new SerProductBatchDAO();

	List<UserDTO> UserArray;
	List<ProductBatchDTO> batchArray;

	PrintWriter outToServer;
	BufferedReader inFromServer;

	public void startScaleProcess() {
		ScaleConnection connection = new ScaleConnection("127.0.0.1");// for
		// Start Tread for at køre afvejningen.

	}

	public void ikkenogetnavnendnuregnermedrun() {
		System.out.println("Hello from a thread!");
		
		while(true){
		registrationOperator();
		registrationProductBatch();
	
		weighingProcess("ingredient");
		}

	}

	public String outputToServer(String outputToServer) {
		try {
			outToServer.println(outputToServer);
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
	public void registrationOperator(){
		
		answer = outputToServer("RM20 8 \"Enter Operator-ID\" \"\" \"&3\"");
		while (answer.equals("RM20 C")) {
			answer = outputToServer("RM20 8 \"Enter Operator-ID\" \"\" \"&3\"");
		}
		answer = answer.split("\"")[1];

		existed = false;

		// tjek om den operator findes i databasen!!
		// Her skal man skabe en array med alle operatørerne.
		try {
			UserArray = users.getUserList();
		} catch (Exception e) {
			System.out.println("Error happened: users.getUserList()");
		}

		while (true) {
			for (i = 0; i < UserArray.size(); i++) {
				existed = true;
				if (answer.equals(String.valueOf(UserArray.get(i).getUserID()))) {
					if (!UserArray.get(i).getRole().getRoleName().equals("Administrator")) {
						answer = outputToServer("RM20 8 \"" + UserArray.get(i).getUserName() + "?" + "\" \"\" \"&3\"");
						break;
					}
				}
				existed = false;
			}

			if (existed == true) {
				if (answer.startsWith("RM20 A"))
					break;
				else {
					answer = outputToServer("RM20 8 \"Enter Operator-ID\" \"\" \"&3\"");
					answer = answer.split("\"")[1];
				}
			} else {
				answer = outputToServer("RM20 8 \"Try again\" \"\" \"&3\"");
				System.out.println(answer);
				answer = answer.split("\"")[1];
			}
		}
		
	}
	
	public void registrationProductBatch(){
		
		answer = outputToServer("RM20 8 \"Enter Batch-ID\" \"\" \"&3\"");
		while (answer.equals("RM20 C")) {
			answer = outputToServer("RM20 8 \"Enter Batch-ID\" \"\" \"&3\"");
		}
		answer = answer.split("\"")[1];

		try {
			batchArray = batches.getProductBatchList();
		} catch (Exception e) {
			System.out.println("Error happened: batches.getProductBatchList() ");
		}

		existed = false;
		while (true) {
			for (i = 0; i < batchArray.size(); i++) {
				existed = true;
				if (answer.equals(String.valueOf(batchArray.get(i).getProductBatchID()))) {
					answer = outputToServer(
							"RM20 8 \"" + batchArray.get(i).getProductBatchName() + "?" + "\" \"\" \"&3\"");
					break;
				}
				existed = false;
			}
			if (existed == true) {
				if (answer.startsWith("RM20 A")) {
					outputToServer("P111 \"" + batchArray.get(i).getProductBatchName() + "\"");
					break;
				} else {
					answer = outputToServer("RM20 8 \"Enter Batch-ID\" \"\" \"&3\"");
					answer = answer.split("\"")[1];
				}
			} else {
				answer = outputToServer("RM20 8 \"Try again\" \"\" \"&3\"");
				answer = answer.split("\"")[1];
			}
		}
	}
	public void weighingProcess(String ingredient){
		
		answer = outputToServer("RM20 8 \"Unload weight\" \"\" \"&3\"");
		// answer = answer.split("\"")[1];
		while (true) {
			if (answer.startsWith("RM20 A")) {
				outputToServer("T");
				break;
			} else {
				answer = outputToServer("RM20 8 \"UNLOAD WEIGHT!\" \"\" \"&3\"");
			}
		}
		answer = outputToServer("RM20 8 \"Place tara\" \"\" \"&3\"");
		while (true) {
			if (answer.startsWith("RM20 A")) {
				outputToServer("B 0.400");// This is for the virtual
											// weight-simulator
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				taraWeight = Double.parseDouble(outputToServer("S").replaceAll("[^-\\d.]", ""));// check
																								// om
																								// det
																								// virker
				answer = outputToServer("RM20 8 \"Tara: " + taraWeight + " kg\" \"\" \"&3\"");
				if (answer.startsWith("RM20 A")) {
					outputToServer("T");
					break;
				}
			} else {
				answer = outputToServer("RM20 8 \"PLACE TARA!\" \"\" \"&3\"");
			}
		}
		answer = outputToServer("RM20 8 \"Place netto\" \"\" \"&3\"");
		// answer = answer.split("\"")[1];
		while (true) {
			if (answer.startsWith("RM20 A")) {
				outputToServer("B 1.000");// This is for the virtual
											// weight-simulator
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				nettoWeight = Double.parseDouble(outputToServer("S").replaceAll("[^-\\d.]", ""));
				answer = outputToServer("RM20 8 \"Netto: " + nettoWeight + " kg\" \"\" \"&3\"");
				if (answer.startsWith("RM20 A")) {
					outputToServer("T");
					break;
				}
			} else {
				answer = outputToServer("RM20 8 \"PLACE NETTO!\" \"\" \"&3\"");
			}
		}

		answer = outputToServer("RM20 8 \"Remove brutto\" \"\" \"&3\"");
		// answer = answer.split("\"")[1];
		while (true) {
			if (answer.startsWith("RM20 A")) {
				outputToServer("F");// This is for the virtual weight-simulator
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				bruttoWeight = Double.parseDouble(outputToServer("S").replaceAll("[^-\\d.]", ""));
				answer = outputToServer("RM20 8 \"Brutto: " + bruttoWeight + " kg\" \"\" \"&3\"");
				if (answer.startsWith("RM20 A")) {
					outputToServer("T");
					break;
				}
			} else {
				answer = outputToServer("RM20 8 \"REMOVE BRUTTO!\" \"\" \"&3\"");
			}
		}

		answer = outputToServer("RM20 8 \"OK or discard?\" \"\" \"&3\"");
		// answer = answer.split("\"")[1];
		while (true) {
			if (answer.startsWith("RM20 A")) {
				answer = outputToServer("RM20 8 \"Process done\" \"\" \"&3\"");
				System.out.println("Tara: " + taraWeight + "\nNetto: " + nettoWeight + "\nBrutto: " + bruttoWeight);
				outputToServer("P111 \"\"");
				System.exit(1);

				// inFromServer.readLine();
				break;
			} else {
				answer = outputToServer("RM20 8 \"Data deleted\" \"\" \"&3\"");
				outputToServer("P111 \"\"");
				outputToServer("Q");
				// inFromServer.readLine();
				break;
			}
		}
	}

}
