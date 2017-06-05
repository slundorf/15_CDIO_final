package scaleMachine;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.List;

import dao.SerUserDAO;
import dto.UserDTO;
import interfaces.IUserDAO;

public class ScaleProcedure extends Thread {

	String answerFromServer = null;
	String answer;
	boolean existed;
	int i = 0;
	IUserDAO users = new SerUserDAO();

	List<UserDTO> UserArray;

	PrintWriter outToServer;
	BufferedReader inFromServer;

	public void startScaleProcess() {
		ScaleConnection connection = new ScaleConnection("127.0.0.1");// for
		// Start Tread for at køre afvejningen.

	}

	public void ikkenogetnavnendnuregnermedrun() {
		System.out.println("Hello from a thread!");

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
			System.out.println("Error happened");
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
		
		answer = outputToServer("RM20 8 \"Enter Batch-ID\" \"\" \"&3\"");
		while (answer.equals("RM20 C")) {
			answer = outputToServer("RM20 8 \"Enter Batch-ID\" \"\" \"&3\"");
		}
		answer = answer.split("\"")[1];

		existed = false;
		while (true) {
			for (i = 0; i < batchArray.size(); i++) {
				existed = true;
				if (answer.equals(String.valueOf(batchArray.get(i).getID()))) {
					answer = outputToServer("RM20 8 \"" + batchArray.get(i).getName() + "?" + "\" \"\" \"&3\"");
					break;
				}
				existed = false;
			}
			if (existed == true) {
				if (answer.startsWith("RM20 A")) {
					outputToServer("P111 \"" + batchArray.get(i).getName() + "\"");
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

}
