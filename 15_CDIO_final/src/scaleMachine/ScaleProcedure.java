package scaleMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.ConnectException;
import java.nio.channels.ShutdownChannelGroupException;
import java.util.ArrayList;
import java.util.List;

import dto.IngredientBatchDTO;
import dto.ProductBatchComponentDTO;
import dto.ProductBatchDTO;
import dto.UserDTO;
import exceptions.DALException;
import interfaces.IIngredientBatchDAO;
import interfaces.IProductBatchDAO;
import interfaces.IUserDAO;
import serDAO.SerIngredientBatchDAO;
import serDAO.SerProductBatchDAO;
import serDAO.SerUserDAO;

public class ScaleProcedure extends Thread {

	String answerFromServer = null;
	String answer;
	boolean existed;

	IUserDAO users = new SerUserDAO();
	IProductBatchDAO productBatches = new SerProductBatchDAO();
	IIngredientBatchDAO ingredientBatches = new SerIngredientBatchDAO();

	List<UserDTO> UserArray;
	List<ProductBatchDTO> productBatchArray;
	List<IngredientBatchDTO> ingredientBatchArray;

	PrintWriter outToServer;
	BufferedReader inFromServer;
	private ProductBatchDTO productBatch;
	private String input3;

	public void startScaleProcess() throws DALException, IOException, InputException {
		ScaleConnection connection = new ScaleConnection("127.0.0.1");// for

		//Get ID
		UserDTO user= null;
		boolean spasser = false;
		while (user==null){
			String input = connection.getInput((spasser ? "Du er en spade " : "") + "Indtast Bruger ID");
			Integer brugerId = Integer.parseInt(input);
			//validate user;
			user = users.getUser(brugerId);
			spasser = true;
		}
		//Get ProducktBatchIdd
		
		ProductBatchDTO PB = null;
		boolean spasser2 = false;
		
		while(PB == null){
		String input2 = connection.getInput((spasser2 ?" spade" : "" ) + "Indatst ProduktBatchID");
		Integer produktbatchId = Integer.parseInt(input2);
			
		productBatch = productBatches.getProductBatch(produktbatchId);
		 spasser2 = true;
		}
		
		
		//Start Weighing
		
		for (ProductBatchComponentDTO productBatchComponentDTO: productBatch.getComponents()) {
			
			// Unload weight
			Boolean scaleEmpty = false;
			
			while(scaleEmpty){
			String input3 = connection.getInput("Unload weight");
			if(input3.startsWith("RM20 A")){
				scaleEmpty = true;
			}
			
			// Tara efter vægten er tom.
			connection.tara();
			
			Boolean b = false;
			
			while(b){
			String input4 = connection.getInput("place tara");
			if(input3.startsWith("RM20 A")){
				b = true;
			}
			}
			
			double taraweight = connection.tara();
			
			productBatchComponentDTO.setTara(taraweight);
			
			
			//Weigh something
			boolean found = false;
			String ingredientname = productBatchComponentDTO.getIngredientName();
			while (!found){
				input3 = connection.getInput("Indtast RåvareBatchID på "+ingredientname);
				IngredientBatchDTO ingredientBatch = ingredientBatches.getIngredientBatch(Integer.parseInt(input3));
				
				boolean rigthIngredient = false; 
				
				// Skal rettes "!!
				if(productBatchComponentDTO.getIngredientID() == ingredientBatch.getIngredientID()){
					rigthIngredient=true;
				}
				if (ingredientBatch != null && rigthIngredient==true){
					found = true;
				}
			}	
				
			





			 


			// Start Tread for at kÃ¸re afvejningen.

		}}
		

	public void ikkenogetnavnendnuregnermedrun() {

		UserDTO user;
		ProductBatchDTO PB;
		ProductBatchComponentDTO PBC;
		List<ProductBatchComponentDTO> tempPBC = new ArrayList<ProductBatchComponentDTO>();

		System.out.println("Hello from a thread!");

		while (true) {

			user = registrationOperator();
			PB = registrationProductBatch();

			for (int i = 0; i < PB.getComponents().size(); i++) {

				PBC = registrationIngredientBatchComponent(PB.getComponents().get(i));
				PBC = weighingProcess(PBC);
				tempPBC.add(PBC);
			}

			PB.setComponents(tempPBC);

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

	public UserDTO registrationOperator() {

		int i = 0;

		UserDTO user = null;
		answer = outputToServer("RM20 8 \"Enter Operator-ID\" \"\" \"&3\"");
		while (answer.equals("RM20 C")) {
			answer = outputToServer("RM20 8 \"Enter Operator-ID\" \"\" \"&3\"");
		}
		answer = answer.split("\"")[1];

		existed = false;

		// tjek om den operator findes i databasen!!
		// Her skal man skabe en array med alle operatÃ¸rerne.
		try {
			UserArray = users.getUserList();
		} catch (Exception e) {
			System.out.println("Error happened: users.getUserList()");
		}

		while (true) {
			i = checkUserId();

			if (existed == true) {
				if (answer.startsWith("RM20 A")) {
					user = UserArray.get(i);
					break;
				} else {
					answer = outputToServer("RM20 8 \"Enter Operator-ID\" \"\" \"&3\"");
					answer = answer.split("\"")[1];
				}
			} else {
				answer = outputToServer("RM20 8 \"Try again\" \"\" \"&3\"");
				System.out.println(answer);
				answer = answer.split("\"")[1];
			}
		}
		return user;
	}

	private int checkUserId() {
		int i;
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
		return i;
	}

	public ProductBatchDTO registrationProductBatch() {

		ProductBatchDTO PB = null;
		int i = 0;

		answer = outputToServer("RM20 8 \"Enter Batch-ID\" \"\" \"&3\"");
		while (answer.equals("RM20 C")) {
			answer = outputToServer("RM20 8 \"Enter Batch-ID\" \"\" \"&3\"");
		}
		answer = answer.split("\"")[1];

		try {
			productBatchArray = productBatches.getProductBatchList();
		} catch (Exception e) {
			System.out.println("Error happened: productBatches.getProductBatchList() ");
		}

		existed = false;
		while (true) {
			for (i = 0; i < productBatchArray.size(); i++) {
				existed = true;
				if (answer.equals(String.valueOf(productBatchArray.get(i).getProductBatchID()))) {
					answer = outputToServer(
							"RM20 8 \"" + productBatchArray.get(i).getProductBatchName() + "?" + "\" \"\" \"&3\"");

					break;
				}
				existed = false;
			}
			if (existed == true) {
				if (answer.startsWith("RM20 A")) {
					outputToServer("P111 \"" + productBatchArray.get(i).getProductBatchName() + "\"");
					PB = productBatchArray.get(i);
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
		return PB;
	}

	public ProductBatchComponentDTO registrationIngredientBatchComponent(ProductBatchComponentDTO component) {
		int ingredientBatchId;
		ProductBatchComponentDTO productBatchComponentDTO = component;
		int i = 0;

		answer = outputToServer("RM20 8 \"Enter Ingredient Batch-ID\" \"\" \"&3\"");
		while (answer.equals("RM20 C")) {
			answer = outputToServer("RM20 8 \"Enter Ingredient Batch-ID\" \"\" \"&3\"");
		}
		answer = answer.split("\"")[1];

		try {
			ingredientBatchArray = ingredientBatches.getIngredientBatchList();
		} catch (Exception e) {
			System.out.println("Error happened: ingredientBatches.getIngredientBatchList() ");
		}

		existed = false;
		while (true) {
			for (i = 0; i < ingredientBatchArray.size(); i++) {
				existed = true;
				if (answer.equals(String.valueOf(ingredientBatchArray.get(i).getIngredientBatchID()))) {
					answer = outputToServer(
							"RM20 8 \"" + ingredientBatchArray.get(i).getIngredientBatchID() + "?" + "\" \"\" \"&3\"");
					break;
				}
				existed = false;
			}
			if (existed == true) { // muligvis ikke nødvendigt i forhold til
									// flow i bilag.
				if (answer.startsWith("RM20 A")) {
					outputToServer("P111 \"" + productBatchArray.get(i).getProductBatchName() + "\""); // productbatchname,
																										// er
																										// det
																										// ikke
																										// blevet
																										// bekræftet
																										// tidligere?
																										// Skal
																										// vi
																										// ikke
																										// vise
																										// den
																										// enkelte
																										// ingrediens?
					ingredientBatchId = ingredientBatchArray.get(i).getIngredientBatchID();
					productBatchComponentDTO.setIngredientBatchID(ingredientBatchId); // har
																						// rettet
																						// til
																						// setIngredientBatchID
																						// istedet
																						// for
																						// setingredientid
					break;
				} else {
					answer = outputToServer("RM20 8 \"Enter Batch-ID\" \"\" \"&3\""); // ingredient-batch
																						// id?
					answer = answer.split("\"")[1];
				}
			} else {
				answer = outputToServer("RM20 8 \"Try again\" \"\" \"&3\"");
				answer = answer.split("\"")[1];
			}

		}
		return productBatchComponentDTO;

	}

	public ProductBatchComponentDTO weighingProcess(ProductBatchComponentDTO component) {
		double taraWeight;
		double nettoWeight;
		double bruttoWeight;

		for (int i = 0; i < PB.getComponents().size(); i++) {

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
					// outputToServer("B 0.400");// This is for the virtual
					// // weight-simulator
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
			answer = outputToServer(
					"RM20 8 \"Place " + PB.getComponents().get(i).getIngredientName() + "\" \"\" \"&3\"");
			// answer = answer.split("\"")[1];
			while (true) {
				if (answer.startsWith("RM20 A")) {
					// outputToServer("B 1.000");// This is for the virtual
					// // weight-simulator
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
					// outputToServer("F");// This is for the virtual
					// weight-simulator
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
		return PB;
	}
	

}
