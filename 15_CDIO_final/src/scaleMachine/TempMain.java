package scaleMachine;

import java.io.IOException;
import java.util.List;

import dto.ProductBatchComponentDTO;
import dto.ProductBatchDTO;
import dto.RoleDTO;
import dto.UserDTO;
import exceptions.DALException;
import exceptions.scaleConnectionException;
import interfaces.IIngredientBatchDAO;
import interfaces.IProductBatchComponentDAO;
import interfaces.IProductBatchDAO;
import interfaces.IUserDAO;
import serDAO.SerIngredientBatchDAO;
import serDAO.SerProductBatchComponentDAO;
import serDAO.SerProductBatchDAO;
import serDAO.SerUserDAO;

public class TempMain {

	public static void main(String[] args) throws scaleConnectionException, DALException, IOException, InputException{
		System.out.println("Brah");

		
	 // test data: 
	
		IUserDAO userDAO = new SerUserDAO();
		
//		UserDTO user = new UserDTO(85, "Kurt", "kru", "010202-1234", " lkjdwsjgoiwkm3r", new RoleDTO(3,"Operator"), true);
//		userDAO.createUser(user);
		
		
				
//		private int productBatchID;
//		private int recipeID;
//		private String productBatchName;
//		private String createdDate;
//		private String status;
//		private List<ProductBatchComponentDTO> components;
//		private int UserId;
		
		IProductBatchComponentDAO PBCDAO = new SerProductBatchComponentDAO();
//		
		 int ingredientID = 1;
		 String ingredientName = "vand";
		 double amount= 0.5;
		 double tolerance = 0.1;
		 double tara;
		 double netto;
		 Integer ingredientBatchID;
		 int productBatchID = 11;
		
		ProductBatchComponentDTO component1 = new  ProductBatchComponentDTO( productBatchID,  ingredientID,  ingredientName
				,  amount,  tolerance,  tara,  netto,  ingredientBatchID);
		
		
		int ingredientID2 = 2;
		String ingredientName2 = "salt";
		 double amount2= 0.1;
		 double tolerance2 = 0.1;
		 double tara2;
		 double netto2;
		 Integer ingredientBatchID2;
		 int productBatchID2 = 11;
		
		ProductBatchComponentDTO component2 = new  ProductBatchComponentDTO( productBatchID2,  ingredientID2,  ingredientName2
				,  amount2,  tolerance2,  tara2,  netto2,  ingredientBatchID2);
		

		
		IIngredientBatchDAO IngredientBatchDAO = new SerIngredientBatchDAO();
		
		IProductBatchDAO productBatchDAO = new SerProductBatchDAO();
		
		ProductBatchDTO PB = new ProductBatchDTO("11","12","Saltvand","0909","Created",)
		
		
		ScaleConnection connection = new ScaleConnection("169.254.2.3");
		ProcedureController procedure = new ProcedureController(connection);
		
		
		
		
		procedure.startScaleProcess();
		
		
//		procedure.startScaleProcess();

		
		
//		
////		user.setIni("HOO");
////		
////		
//		UserDTO user;
//		user = null;
//		boolean attempt = true;
//		while (user == null) {
//			int userId = connection.getInteger((attempt ? "" : "Try again: ") + "Enter User ID");
//
//			// validate user;
//			try {
//				user = userDAO.getUser(userId);
//			} catch (DALException e) {
//				//Try again
//			}
//			attempt = false;
//		}
//		connection.setOperatorInitials(user.getIni());
////		
//		
	}
}
