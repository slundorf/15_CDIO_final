package scaleMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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

public class procedureController {

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
	ScaleConnection connection;
	UserDTO user;

	public void startScaleProcess() throws DALException, IOException, InputException {
		connection = new ScaleConnection("127.0.0.1");// for

		enterUserId(connection);
		enterProductBatchId(connection);
		// Start Weighing
		for (ProductBatchComponentDTO productBatchComponentDTO : productBatch.getComponents()) {
			// Unload weight
			connection.displayMsg("Unload weigth");
			connection.doTara();

			boolean found = false;
			String ingredientname = productBatchComponentDTO.getIngredientName();
			while (!found) {
				input3 = connection.getInput("Indtast R�vareBatchID p� " + ingredientname);
				IngredientBatchDTO ingredientBatch = ingredientBatches.getIngredientBatch(Integer.parseInt(input3));

				if (productBatchComponentDTO.getIngredientID() == ingredientBatch.getIngredientID()) {
					found = true;
				}
				connection.setComponentName(productBatchComponentDTO.getIngredientName());

				connection.displayMsg("Place Tara");
				// save Tara Weight.
				double taraweight = connection.tara();
				productBatchComponentDTO.setTara(taraweight);

				// Weigh something
				boolean b = false;
				int nettoweight = 0;
				while (b) {
					String ingrdientName = productBatchComponentDTO.getIngredientName();
					nettoweight = connection.getIngeter("Place " + ingrdientName);
					// NÅr man trykke ok, tager den vægten herefter venter den i
					// 2 sek og viser vægten på displayet.
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// Tjekker om vægten er inde for Tolerancen.
					double tolereanceWeight = productBatchComponentDTO.getTolerance()
							* productBatchComponentDTO.getAmount();
					double amount = productBatchComponentDTO.getAmount();
					// Først beregner jeg være den er på, hvorefter jeg om det
					// passe med vægten.
					if (tolereanceWeight + amount >= nettoweight && tolereanceWeight - amount <= nettoweight) {
						b = true;
					}
					connection.displayMsg("Forkert afvejning prøv igen");
				}
				productBatchComponentDTO.setNetto(nettoweight);
			}
			productBatches.updateProductBatch(productBatch);
			connection.displayMsg("Your done with the procedure");

		}
	}

	// Denne her skal nok væk fordi den komme i interfacet.

	// private void msg(String string) throws IOException, InputException {
	// Boolean b = false;
	//
	// while(b){
	// String input3 = connection.getInput(string);
	// if(input3.startsWith("RM20 A")){
	// b = true;
	// }
	// }
	// }

	private void enterProductBatchId(ScaleConnection connection) throws IOException, InputException, DALException {
		ProductBatchDTO productBatch = null;
		boolean attempt = true;

		while (productBatch == null) {
			int productBatchId = connection.getInteger((attempt ? " " : "Try again:") + "Enter Product Batch ID");

			productBatch = productBatches.getProductBatch(productBatchId);
			attempt = false;
		}
		productBatch.setStatus("Producing");
		connection.setProductBatchName(productBatch.getProductBatchName());
		productBatch.setUserId(user.getUserID());

	}

	private void enterUserId(ScaleConnection connection) throws IOException, InputException, DALException {
		user = null;
		boolean attempt = true;
		while (user == null) {
			int userId = connection.getInteger((attempt ? "" : "Try again: ") + "Enter User ID");

			// validate user;
			user = users.getUser(userId);
			attempt = false;
		}
		connection.setOperatorName(user.getUserName());
	}
}

// Start Tread for at køre afvejningen.
