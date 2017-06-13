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

	// DTO
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

	public void startScaleProcess() throws DALException, IOException, scaleConnectionException {
		// connection.setSoftBotton();
		connection.removeProductBatchID();
		connection.removeOperatorInitials();

		enterUserId(connection);
		enterProductBatchId(connection);
		// Start Weighing

		for (ProductBatchComponentDTO productBatchComponentDTO : productBatch.getComponents()) {

			IngredientBatchDTO ingredientBatch = enterIngredientBatch(connection, productBatchComponentDTO);

			startweighing(connection, productBatchComponentDTO, ingredientBatch);
		}
		productBatches.updateProductBatch(productBatch);
		connection.displayMsg("Your done with the procedure");

		;

	}

	private void startweighing(IScaleConnection connection, ProductBatchComponentDTO productBatchComponentDTO,
			IngredientBatchDTO ingredientBatch) throws scaleConnectionException, DALException {
		// Place Tara and note the mass.
		connection.displayMsg("Place Tara");
		// save Tara Weight.
		// connection.waitForAnswer();
		double taraweight = connection.doTara();
		productBatchComponentDTO.setTara(taraweight);

		String ingredientname = ingredients.getIngredient(productBatchComponentDTO.getIngredientID())
				.getIngredientName();
		// Weigh something
		boolean b = false;
		boolean attempt = true;
		double nettoweight = 0;
		String msg = null;
		while (!b) {
			// String ingrdientName =
			// productBatchComponentDTO.getIngredientName();
			connection.displayMsg(attempt ? "Place " + ingredientname : "Change amount");
			// connection.waitForAnswer();
			nettoweight = connection.getMass();
			// NÅr man trykke ok, tager den vægten herefter venter den i
			// 2 sek og viser vægten på displayet.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Calculate tolerancewaight of the ingredientbatch amount.
			double amount = getAmount(productBatchComponentDTO);
			double toleranceWeight = getTolerance(productBatchComponentDTO) * amount;
			// Weighed mass comply with the tolerance do this
			if (amount + toleranceWeight >= nettoweight && nettoweight >= amount - toleranceWeight) {
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
			connection.displayMsg("Exceeds tolerance");
			attempt = false;
		}
		productBatchComponentDTO.setNetto(nettoweight);
		connection.displayMsg("Weight noted as " + nettoweight);
		connection.removeComponentName();
	}

	private IngredientBatchDTO enterIngredientBatch(IScaleConnection connection,
			ProductBatchComponentDTO productBatchComponentDTO) throws scaleConnectionException {

		connection.displayMsg("Unload weigth");
		connection.doTara();

		IngredientBatchDTO ingredientBatch = null;

		String ingredientname = null;
		try {
			ingredientname = ingredients.getIngredient(productBatchComponentDTO.getIngredientID()).getIngredientName();
		} catch (DALException e1) {
			System.out.println("error ingredients.getIngredient(-----)");
			e1.printStackTrace();
		}
		boolean attempt = true;
		String msg = null;
		while (true) {
			input3 = connection.getInteger((attempt ? "Enter ingredientBatch ID" : msg));

			try {
				ingredientBatch = ingredientBatches.getIngredientBatch(input3);

				if (productBatchComponentDTO.getIngredientID() == ingredientBatch.getIngredientID()) {
					productBatchComponentDTO.setIngredientBatchID(input3);
					connection.setComponentName(ingredientname);
					productBatchComponentDTO.setUserId(user.getUserID());
					return ingredientBatch;

				} else {
					msg = "Wrong ingredient. ";
					attempt = false;
				}
			} catch (DALException e) {
				msg = "ID doesn't exist. ";
				attempt = false;
			}

		}

	}

	private void enterProductBatchId(IScaleConnection connection) throws scaleConnectionException {
		productBatch = null;
		boolean attempt = true;

		while (productBatch == null) {
			int productBatchId = connection.getInteger((attempt ? "" : "Try again: ") + "Enter Product Batch ID");

			try {
				productBatch = productBatches.getProductBatch(productBatchId);
			} catch (DALException e) {
				attempt = false;
			}

		}
		productBatch.setStatus("Producing");
		connection.setProductBatchID(productBatch.getProductBatchID());

	}

	private void enterUserId(IScaleConnection connection) throws scaleConnectionException {
		user = null;
		boolean attempt = true;
		while (user == null) {
			int userId = connection.getInteger((attempt ? "" : "Try again: ") + "Enter User ID");

			// validate user;
			try {
				user = users.getUser(userId);

				if (!user.isStatus()) {
					user = null;
					attempt = false;
				}

			} catch (DALException e) {
				// Try again
				attempt = false;
			}
		}
		connection.setOperatorInitials(user.getIni());
	}

	private double getTolerance(ProductBatchComponentDTO dto) throws DALException {
		for (int k = 0; k < recipes.getRecipe(productBatches.getProductBatch(dto.getPbId()).getRecipeID()).getComponents().size(); k++) {
			if (recipes.getRecipe(productBatches.getProductBatch(dto.getPbId()).getRecipeID()).getComponents().get(k).getIngredientID() == 
					dto.getIngredientID()) {
				return recipes.getRecipe(productBatches.getProductBatch(dto.getPbId()).getRecipeID())
						.getComponents().get(k).getTolerance();
			}
		}
		throw new DALException(
				"Recipecomponent corresponding to the productbatchcomponent could not be found");
	}

	private double getAmount(ProductBatchComponentDTO dto) throws DALException {
		for (int k = 0; k < recipes.getRecipe(productBatches.getProductBatch(dto.getPbId()).getRecipeID()).getComponents().size(); k++) {
			if (recipes.getRecipe(productBatches.getProductBatch(dto.getPbId()).getRecipeID()).getComponents().get(k).getIngredientID() == 
					dto.getIngredientID()) {
				return recipes.getRecipe(productBatches.getProductBatch(dto.getPbId()).getRecipeID())
						.getComponents().get(k).getTolerance();
			}
		}
		throw new DALException(
				"Recipecomponent corresponding to the productbatchcomponent could not be found");
	}
}

// Start Tread for at køre afvejningen.
