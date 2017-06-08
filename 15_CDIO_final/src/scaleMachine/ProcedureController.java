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

public class ProcedureController {
	

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

	public void startScaleProcess() throws DALException, IOException, InputException {
		 connection = new ScaleConnection("127.0.0.1");// for

		//Get ID
		checkUserId(connection);
		//Get ProducktBatchIdd
		checkProductBatchId(connection);
	
		// set status on the productbatch to true
		productBatch.setStatus("Igang");

		//Start Weighing
		for (ProductBatchComponentDTO productBatchComponentDTO: productBatch.getComponents()) {	
			// Unload weight
			msg("Unload weigth");
			// Tara efter v�gten er tom.
			connection.tara();
			msg("Place Tara");
			
			//The Tara Weight.
			double taraweight = connection.tara();
			productBatchComponentDTO.setTara(taraweight);
			
			checkProductBatchComponent(productBatchComponentDTO);
			//Weigh something 
			boolean b = false;
			while(b){
			String ingrdientName = productBatchComponentDTO.getIngredientName();
			int nettoweight = connection.getNumber("Place "+ ingrdientName);
			// NÅr man trykke ok, tager den vægten herefter venter den i 2 sek og viser vægten på displayet.
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection.showMessageToUser("Vægt:" + nettoweight);
			// Tjekker om vægten er inde for Tolerancen.
			double tolereanceWeight = productBatchComponentDTO.getTolerance()*productBatchComponentDTO.getAmount();
			double amount = productBatchComponentDTO.getAmount(); 
			// Først beregner jeg være den er på, hvorefter jeg om det passe med vægten.
			if(tolereanceWeight+amount>nettoweight && tolereanceWeight-amount < nettoweight){
				b = true;
			}
			connection.showMessageToUser("Forkert afvejning prøv igen");
			}
			
			}
		
		productBatch.setStatus("Slut");
		
	}


	private void checkProductBatchComponent(ProductBatchComponentDTO productBatchComponentDTO)
			throws IOException, InputException, DALException {
		boolean found = false;
		String ingredientname = productBatchComponentDTO.getIngredientName();
		while (!found){
			input3 = connection.getInput("Indtast R�vareBatchID p� "+ingredientname);
			IngredientBatchDTO ingredientBatch = ingredientBatches.getIngredientBatch(Integer.parseInt(input3));
			
			if(productBatchComponentDTO.getIngredientID() == ingredientBatch.getIngredientID()){
				found = true;
			}
		}
	}


	private void msg(String string) throws IOException, InputException {
		Boolean b = false;
		
		while(b){
		String input3 = connection.getInput(string);
		if(input3.startsWith("RM20 A")){
			b = true;
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
				
			





			 


			// Start Tread for at køre afvejningen.

		

