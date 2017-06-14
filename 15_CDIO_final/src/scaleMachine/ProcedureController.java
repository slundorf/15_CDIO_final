package scaleMachine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import dto.IngredientBatchDTO;
import dto.ProductBatchComponentDTO;
import dto.ProductBatchDTO;
import dto.RecipeComponentDTO;
import dto.UserDTO;
import exceptions.DALException;
import exceptions.scaleConnectionException;
import interfaces.IIngredientBatchDAO;
import interfaces.IIngredientDAO;
import interfaces.IProductBatchDAO;
import interfaces.IRecipeComponentDAO;
import interfaces.IRecipeDAO;
import interfaces.IUserDAO;
import serDAO.SerIngredientBatchDAO;
import serDAO.SerIngredientDAO;
import serDAO.SerProductBatchDAO;
import serDAO.SerRecipeComponentDAO;
import serDAO.SerRecipeDAO;
import serDAO.SerUserDAO;
import testData.FakeIngredientBatchDAO;
import testData.FakeIngredientDAO;
import testData.FakeProductBatchDAO;
import testData.FakeRecipeComponentsDAO;
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
	IRecipeComponentDAO recipecomponents;

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
			recipecomponents = new FakeRecipeComponentsDAO();
		} else {
			users = new SerUserDAO();
			productBatches = new SerProductBatchDAO();
			ingredientBatches = new SerIngredientBatchDAO();
			ingredients = new SerIngredientDAO();
			recipes = new SerRecipeDAO();
			recipecomponents = new SerRecipeComponentDAO();

		}
		// if(test){
		// connection = new FakeScaleConnection();
		// }
	}

	public void startScaleProcess() throws DALException, IOException, scaleConnectionException {

		enterUserId(connection);

		enterProductBatchId(connection);
		// Start Weighing

		for (ProductBatchComponentDTO productBatchComponentDTO : productBatch.getComponents()) {

			IngredientBatchDTO ingredientBatch = enterIngredientBatch(connection, productBatchComponentDTO);

			startweighing(connection, productBatchComponentDTO, ingredientBatch);
			System.out.println(" netto: " + productBatchComponentDTO.getNetto() + "Tara  "
					+ productBatchComponentDTO.getTara() + "  IngredientId "
					+ productBatchComponentDTO.getIngredientID() + " user id: " + productBatchComponentDTO.getUserId());
		}
		productBatches.updateProductBatch(productBatch);
		connection.displayMsg("Productbatch complete");

		connection.removeProductBatchID();
		connection.removeOperatorInitials();

	}

	private void startweighing(IScaleConnection connection, ProductBatchComponentDTO productBatchComponentDTO,
			IngredientBatchDTO ingredientBatch) throws scaleConnectionException, DALException {
		// Place Tara and note the mass.
		connection.displayMsg("Place Tara");
		// save Tara Weight.
		connection.setSoftKey();
		connection.waitForAnswer();
		connection.removeSoftKey();
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
			connection.setSoftKey();
			connection.waitForAnswer();
			connection.removeSoftKey();
			nettoweight = connection.getMass();

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
			int productBatchId = connection.getInteger(attempt ? "Enter ProductBatch ID" : "Try again");

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

				int roleId = user.getRole().getRoleID();
				Boolean status = user.isStatus();

				if (!status || (roleId == 1)) {

					user = null;
					attempt = false;
				}
			}

			catch (DALException e) {
				// Try again
				attempt = false;
			}
		}
		connection.setOperatorInitials(user.getIni());
	}

	private double getTolerance(ProductBatchComponentDTO dto) throws DALException {

		int ingredientID = dto.getIngredientID();
		int pbId = dto.getPbId();
		ProductBatchDTO PB = productBatches.getProductBatch(pbId);
		int rcId = PB.getRecipeID();

		RecipeComponentDTO RC = recipecomponents.getRecipeComponent(rcId, ingredientID);
		return RC.getTolerance();


	}

	private double getAmount(ProductBatchComponentDTO dto) throws DALException {

		int ingredientID = dto.getIngredientID();
		int pbId = dto.getPbId();
		ProductBatchDTO PB = productBatches.getProductBatch(pbId);
		int rcId = PB.getRecipeID();

		RecipeComponentDTO RC = recipecomponents.getRecipeComponent(rcId, ingredientID);
		return RC.getAmount();

	
	}
}

