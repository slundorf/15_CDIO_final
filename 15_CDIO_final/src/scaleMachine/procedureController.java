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

	public void startScaleProcess() throws DALException, IOException, InputException {
		ScaleConnection connection = new ScaleConnection("127.0.0.1");// for

		//Get ID
		checkUserId(connection);
		
		//Get ProducktBatchIdd
		
		checkProductBatchId(connection);
		
		
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
			
			}
		
	}
}

	private void checkProductBatchId(ScaleConnection connection) throws IOException, InputException, DALException {
		ProductBatchDTO PB = null;
		boolean spasser2 = false;
		
		while(PB == null){
		String input2 = connection.getInput((spasser2 ?" spade" : "" ) + "Indatst ProduktBatchID");
		Integer produktbatchId = Integer.parseInt(input2);
			
		productBatch = productBatches.getProductBatch(produktbatchId);
		 spasser2 = true;
		}
	}

	private void checkUserId(ScaleConnection connection) throws IOException, InputException, DALException {
		UserDTO user= null;
		boolean spasser = false;
		while (user==null){
			String input = connection.getInput((spasser ? "Du er en spade " : "") + "Indtast Bruger ID");
			Integer brugerId = Integer.parseInt(input);
			//validate user;
			user = users.getUser(brugerId);
			spasser = true;
		}
	}
}
				
			





			 


			// Start Tread for at kÃ¸re afvejningen.

		

