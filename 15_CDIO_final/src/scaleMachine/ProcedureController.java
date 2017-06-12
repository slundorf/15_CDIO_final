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
import exceptions.scaleConnectionException;
import interfaces.IIngredientBatchDAO;
import interfaces.IIngredientDAO;
import interfaces.IProductBatchDAO;
import interfaces.IRecipeDAO;
import interfaces.IUserDAO;
import testData.FakeIngredientBatchDAO;
import testData.FakeIngredientDAO;
import testData.FakeProductBatchDAO;
import testData.FakeRecipeDAO;
import testData.FakeUserDAO;

public class ProcedureController {

	String answerFromServer = null;
	String answer;
	boolean existed;

	IUserDAO users = new FakeUserDAO();
	IProductBatchDAO productBatches = new FakeProductBatchDAO();
	IIngredientBatchDAO ingredientBatches = new FakeIngredientBatchDAO();
	IIngredientDAO ingredients = new FakeIngredientDAO();
	IRecipeDAO recipes = new FakeRecipeDAO();

	List<UserDTO> UserArray;
	List<ProductBatchDTO> productBatchArray;
	List<IngredientBatchDTO> ingredientBatchArray;

	PrintWriter outToServer;
	BufferedReader inFromServer;
	private ProductBatchDTO productBatch;
	private int input3;
	ScaleConnection connection;
	UserDTO user;
	
	public ProcedureController(ScaleConnection connection){
		this.connection=connection;
	}

	public void startScaleProcess() throws DALException, IOException, InputException, scaleConnectionException {
	

		enterUserId(connection);
		enterProductBatchId(connection);
		// Start Weighing
		for (ProductBatchComponentDTO productBatchComponentDTO : productBatch.getComponents()) {
			// Unload weight
			connection.displayMsg("Unload weigth");
			connection.doTara();

			boolean found = false;
			String ingredientname = ingredients.getIngredient(
					ingredientBatches.getIngredientBatch(
							productBatchComponentDTO.getIngredientBatchID()).getIngredientID()).getIngredientName();
			while (!found) {
				input3 = connection.getInteger("Indtast R�vareBatchID p� " + ingredientname);
				IngredientBatchDTO ingredientBatch = ingredientBatches.getIngredientBatch(input3);

				if (productBatchComponentDTO.getIngredientBatchID() == ingredientBatch.getIngredientID()) {
					found = true;
				}
				connection.setComponentName(ingredientname);

				connection.displayMsg("Place Tara");
				// save Tara Weight.
				double taraweight = connection.doTara();
				productBatchComponentDTO.setTara(taraweight);

				// Weigh something
				boolean b = false;
				int nettoweight = 0;
				while (b) {
//					String ingrdientName = productBatchComponentDTO.getIngredientName();
					nettoweight = connection.getInteger("Place " + ingredientname);
					// NÅr man trykke ok, tager den vægten herefter venter den i
					// 2 sek og viser vægten på displayet.
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					// Tjekker om vægten er inde for Tolerancen.
					double tolereanceWeight = productBatchComponentDTO.getIngredientBatchID().getTolerance()
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

	private double getTolerance(ProductBatchComponentDTO dto){
		
		
		for(int i=0;i<productBatches.getProductBatchList().size();i++){
			for(int j=0;j<productBatches.getProductBatch(i).getComponents().size();j++){
				if(productBatches.getProductBatchList().get(i).getComponents().get(j).get
			}
		}
		recipes.getRecipe(recipeID);
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

	private void enterProductBatchId(ScaleConnection connection) throws IOException, InputException, DALException, scaleConnectionException {
		ProductBatchDTO productBatch = null;
		boolean attempt = true;

		while (productBatch == null) {
			int productBatchId = connection.getInteger((attempt ? " " : "Try again:") + "Enter Product Batch ID");

			productBatch = productBatches.getProductBatch(productBatchId);
			attempt = false;
		}
		productBatch.setStatus("Producing");
		connection.setProductBatchID(productBatch.getProductBatchName());
		productBatch.setUserId(user.getUserID());

	}

	private void enterUserId(ScaleConnection connection) throws IOException, InputException, DALException, scaleConnectionException {
		user = null;
		boolean attempt = true;
		while (user == null) {
			int userId = connection.getInteger((attempt ? "" : "Try again: ") + "Enter User ID");

			// validate user;
			try {
				user = users.getUser(userId);
			} catch (DALException e) {
				//Try again
			}
			attempt = false;
		}
		connection.setOperatorInitials(user.getIni());
	}
}

// Start Tread for at køre afvejningen.
