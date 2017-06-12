package scaleMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dto.IngredientBatchDTO;
import dto.IngredientDTO;
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
import serDAO.SerIngredientBatchDAO;
import serDAO.SerIngredientDAO;
import serDAO.SerProductBatchDAO;
import serDAO.SerRecipeDAO;
import serDAO.SerUserDAO;
import testData.FakeIngredientBatchDAO;
import testData.FakeIngredientDAO;
import testData.FakeProductBatchDAO;
import testData.FakeRecipeDAO;
import testData.FakeUserDAO;

public class ProcedureController {

	String answerFromServer = null;
	String answer;
	boolean existed;

	IUserDAO users;
	IProductBatchDAO productBatches;
	IIngredientBatchDAO ingredientBatches;
	IIngredientDAO ingredients;
	IRecipeDAO recipes;

	List<UserDTO> UserArray;
	List<ProductBatchDTO> productBatchArray;
	List<IngredientBatchDTO> ingredientBatchArray;

	PrintWriter outToServer;
	BufferedReader inFromServer;
	private ProductBatchDTO productBatch;
	private int input3;
	IScaleConnection connection;
	UserDTO user;

	public ProcedureController(IScaleConnection connection, boolean test) {
		this.connection = connection;
		if (test) {
			users = new FakeUserDAO();
			productBatches = new FakeProductBatchDAO();
			ingredientBatches = new FakeIngredientBatchDAO();
			ingredients = new FakeIngredientDAO();
			recipes = new FakeRecipeDAO();
		} else {
			users = new SerUserDAO();
			productBatches = new SerProductBatchDAO();
			ingredientBatches = new SerIngredientBatchDAO();
			ingredients = new SerIngredientDAO();
			recipes = new SerRecipeDAO();
		}
		// if(test){
		// connection = new FakeScaleConnection();
		// }
	}

	public void startScaleProcess() throws DALException, IOException, InputException, scaleConnectionException {

		enterUserId(connection);
		enterProductBatchId(connection);
		// Start Weighing
		for (ProductBatchComponentDTO productBatchComponentDTO : productBatch.getComponents()) {
			// Unload weight
			productBatchComponentDTO.setUserId(user.getUserID());
			connection.displayMsg("Unload weigth");
			connection.doTara();
			IngredientBatchDTO ingredientBatch = null;

			boolean found = false;
			String ingredientname = ingredients.getIngredient(productBatchComponentDTO.getIngredientID())
					.getIngredientName();
			while (!found) {
				input3 = connection.getInteger("Enter ingredientbatch ID of " + ingredientname);
				ingredientBatch = ingredientBatches.getIngredientBatch(input3);

				if (productBatchComponentDTO.getIngredientID() == ingredientBatch.getIngredientID()) {
					found = true;
					productBatchComponentDTO.setIngredientBatchID(input3);
				}
				connection.setComponentName(ingredientname);

			}
			// Place Tara and note the mass.
			connection.displayMsg("Place Tara");
			// save Tara Weight.
			double taraweight = connection.doTara();
			productBatchComponentDTO.setTara(taraweight);
			
			
			// Weigh something
			boolean b = false;
			double nettoweight = 0;
			while (!b) {
				// String ingrdientName =
				// productBatchComponentDTO.getIngredientName();
				connection.displayMsg("Place " + ingredientname);
				nettoweight = connection.getMass();
				// NÅr man trykke ok, tager den vægten herefter venter den i
				// 2 sek og viser vægten på displayet.
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				// Calculate tolerancewaight of the ingredientbatch amount.
				double amount = getAmount(productBatchComponentDTO);
				double toleranceWeight = getTolerance(productBatchComponentDTO) * amount;
				// Weighed mass comply with the tolerance do this
				if (toleranceWeight + amount >= nettoweight && toleranceWeight - amount <= nettoweight) {
					b = true;
					// Get current amount from IngredientBatch
					int IBId = ingredientBatch.getIngredientBatchID();
					IngredientBatchDTO IBDTO = ingredientBatches.getIngredientBatch(IBId);
					double currentAmount = IBDTO.getAmount();
					// Set new amount for IngredientBatch
					IBDTO.setAmount(currentAmount - nettoweight);
					// Update DAO
					ingredientBatches.updateIngredientBatch(ingredientBatch);
					break;
				}
				connection.displayMsg("Incorrect weight. Breaks tolerance.");
			}
			productBatchComponentDTO.setNetto(nettoweight);
			connection.displayMsg("Weight noted as " + nettoweight);
			connection.removeComponentName();
		}
		productBatches.updateProductBatch(productBatch);
		connection.displayMsg("Your done with the procedure");

	}

	private double getTolerance(ProductBatchComponentDTO dto) throws DALException {

		for (int i = 0; i < productBatches.getProductBatchList().size(); i++) {
			for (int j = 0; j < productBatches.getProductBatchList().get(i).getComponents().size(); j++) {
				if (productBatches.getProductBatchList().get(i).getComponents().get(j).getPbcId() == dto.getPbcId()) {
					for (int k = 0; k < recipes.getRecipe(productBatches.getProductBatchList().get(i).getRecipeID())
							.getComponents().size(); k++) {
						if (recipes.getRecipe(productBatches.getProductBatchList().get(i).getRecipeID()).getComponents()
								.get(k).getIngredientID() == ingredientBatches
										.getIngredientBatch(dto.getIngredientBatchID()).getIngredientID()) {
							return recipes.getRecipe(productBatches.getProductBatchList().get(i).getRecipeID())
									.getComponents().get(k).getTolerance();
						}
					}
				}
			}
		}
		throw new DALException(
				"Recipecomponent corresponding to productbatchcomponent " + dto.getPbcId() + " could not be found");
	}

	private double getAmount(ProductBatchComponentDTO dto) throws DALException {
		for (int i = 0; i < productBatches.getProductBatchList().size(); i++) {
			for (int j = 0; j < productBatches.getProductBatchList().get(i).getComponents().size(); j++) {
				if (productBatches.getProductBatchList().get(i).getComponents().get(j).getPbcId() == dto.getPbcId()) {
					for (int k = 0; k < recipes.getRecipe(productBatches.getProductBatchList().get(i).getRecipeID())
							.getComponents().size(); k++) {
						if (recipes.getRecipe(productBatches.getProductBatchList().get(i).getRecipeID()).getComponents()
								.get(k).getIngredientID() == ingredientBatches
										.getIngredientBatch(dto.getIngredientBatchID()).getIngredientID()) {
							return recipes.getRecipe(productBatches.getProductBatchList().get(i).getRecipeID())
									.getComponents().get(k).getAmount();
						}
					}
				}
			}
		}
		throw new DALException(
				"Recipecomponent corresponding to productbatchcomponent " + dto.getPbcId() + " could not be found");
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

	private void enterProductBatchId(IScaleConnection connection)
			throws IOException, InputException, DALException, scaleConnectionException {
		productBatch = null;
		boolean attempt = true;

		while (productBatch == null) {
			int productBatchId = connection.getInteger((attempt ? "" : "Try again:") + "Enter Product Batch ID");

			productBatch = productBatches.getProductBatch(productBatchId);
			attempt = false;
		}
		productBatch.setStatus("Producing");
		connection.setProductBatchID(productBatch.getProductBatchID());

	}

	private void enterUserId(IScaleConnection connection)
			throws IOException, InputException, DALException, scaleConnectionException {
		user = null;
		boolean attempt = true;
		while (user == null) {
			int userId = connection.getInteger((attempt ? "" : "Try again: ") + "Enter User ID");

			// validate user;
			try {
				user = users.getUser(userId);
			} catch (DALException e) {
				// Try again
			}
			attempt = false;
		}
		connection.setOperatorInitials(user.getIni());
	}
}

// Start Tread for at køre afvejningen.
