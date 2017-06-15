package test;

import dto.IngredientBatchDTO;
import dto.IngredientDTO;
import dto.ProductBatchComponentDTO;
import dto.ProductBatchDTO;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;
import dto.RoleDTO;
import dto.UserDTO;
import exceptions.DALException;
import interfaces.IIngredientBatchDAO;
import interfaces.IIngredientDAO;
import interfaces.IProductBatchComponentDAO;
import interfaces.IProductBatchDAO;
import interfaces.IRecipeComponentDAO;
import interfaces.IRecipeDAO;
import interfaces.IRoleDAO;
import interfaces.IUserDAO;
import testDataSer.FakeSerIngredientBatchDAO;
import testDataSer.FakeSerIngredientDAO;
import testDataSer.FakeSerProductBatchComponentDAO;
import testDataSer.FakeSerProductBatchDAO;
import testDataSer.FakeSerRecipeComponentDAO;
import testDataSer.FakeSerRecipeDAO;
import testDataSer.FakeSerRoleDAO;
import testDataSer.FakeSerUserDAO;

public class FakeSerDAOTest {

	public static void main(String[] args) throws DALException {
		// TODO Auto-generated method stub

		IIngredientBatchDAO ingredientBatchDAO = new FakeSerIngredientBatchDAO();

		IngredientBatchDTO IB1 = new IngredientBatchDTO(30, 1, 25);
		IngredientBatchDTO IB2 = new IngredientBatchDTO(31, 2, 50);
		IngredientBatchDTO IB3 = new IngredientBatchDTO(32, 2, 60);
		IngredientBatchDTO IB4 = new IngredientBatchDTO(33, 3, 10);

		ingredientBatchDAO.createIngredientBatch(IB1);
		ingredientBatchDAO.createIngredientBatch(IB2);
		ingredientBatchDAO.createIngredientBatch(IB3);
		ingredientBatchDAO.createIngredientBatch(IB4);

		System.out.println("30 IngredientBatch ID: "+ingredientBatchDAO.getIngredientBatch(30).getIngredientBatchID());
		System.out.println("31 IngredientBatch ID: "+ingredientBatchDAO.getIngredientBatch(31).getIngredientBatchID());
		System.out.println("32 IngredientBatch ID: "+ingredientBatchDAO.getIngredientBatch(32).getIngredientBatchID());
		System.out.println("33 IngredientBatch ID: "+ingredientBatchDAO.getIngredientBatch(33).getIngredientBatchID());
		
		
		IIngredientDAO ingredientDAO = new FakeSerIngredientDAO();

		IngredientDTO I1 = new IngredientDTO(4, "Salt", "supplier1");
		IngredientDTO I2 = new IngredientDTO(5, "Water", "supplier2");
		IngredientDTO I3 = new IngredientDTO(6, "Lemon", "supplier3");
		ingredientDAO.createIngredient(I1);
		ingredientDAO.createIngredient(I2);
		ingredientDAO.createIngredient(I3);
		
		System.out.println("Salt Ingredient Name: "+ingredientDAO.getIngredient(4).getIngredientName());
		System.out.println("Water Ingredient Name: "+ingredientDAO.getIngredient(5).getIngredientName());
		System.out.println("Lemon Ingredient Name: "+ingredientDAO.getIngredient(6).getIngredientName());
		

		IProductBatchDAO productBatchDAO = new FakeSerProductBatchDAO();

		ProductBatchDTO PB1 = new ProductBatchDTO(51, 41, "090693", "Created");
		ProductBatchDTO PB2 = new ProductBatchDTO(52, 42, "100693", "Created");
		ProductBatchDTO PB3 = new ProductBatchDTO(53, 42, "110693", "Created");

		productBatchDAO.createProductBatch(PB1);
		productBatchDAO.createProductBatch(PB2);
		productBatchDAO.createProductBatch(PB3);
		
		System.out.println("51 ProductbatchId: "+productBatchDAO.getProductBatch(51).getProductBatchID());
		System.out.println("52 ProductbatchId: "+productBatchDAO.getProductBatch(52).getProductBatchID());
		System.out.println("53 ProductbatchId: "+productBatchDAO.getProductBatch(53).getProductBatchID());

		IProductBatchComponentDAO productBatchComponentDAO = new FakeSerProductBatchComponentDAO();

		// PB1
		ProductBatchComponentDTO PBC1 = new ProductBatchComponentDTO(51, 4);
		
		ProductBatchComponentDTO PBC2 = new ProductBatchComponentDTO(51, 5);

		// PB2
		ProductBatchComponentDTO PBC3 = new ProductBatchComponentDTO(52, 6);

		// PB3
		ProductBatchComponentDTO PBC4 = new ProductBatchComponentDTO(53, 6);

		productBatchComponentDAO.createProductBatchComponent(PBC1);
		productBatchComponentDAO.createProductBatchComponent(PBC2);
		productBatchComponentDAO.createProductBatchComponent(PBC3);
		productBatchComponentDAO.createProductBatchComponent(PBC4);
		
		System.out.println("4 IngredientId: "+productBatchComponentDAO.getProduktBatchComp(51,4).getIngredientID());
		System.out.println("5 IngredientId: "+productBatchComponentDAO.getProduktBatchComp(51,5).getIngredientID());
		System.out.println("6 IngredientId: "+productBatchComponentDAO.getProduktBatchComp(52,6).getIngredientID());
		System.out.println("6 IngredientId: "+productBatchComponentDAO.getProduktBatchComp(53,6).getIngredientID());

		IRecipeDAO recipeDAO = new FakeSerRecipeDAO();

		RecipeDTO R1 = new RecipeDTO(41, "SaltWater");
		RecipeDTO R2 = new RecipeDTO(42, "Lemon");

		recipeDAO.createRecipe(R1);
		recipeDAO.createRecipe(R2);
		
		System.out.println("SaltWater RecipeName: "+ recipeDAO.getRecipe(41).getRecipeName());
		System.out.println("Lemon RecipeName: "+ recipeDAO.getRecipe(42).getRecipeName());

		IRecipeComponentDAO recipeComponentDAO = new FakeSerRecipeComponentDAO();

		RecipeComponentDTO RC1 = new RecipeComponentDTO(41, 4, 0.2, 0.1);
		RecipeComponentDTO RC2 = new RecipeComponentDTO(41, 5, 2, 0.1);
		RecipeComponentDTO RC3 = new RecipeComponentDTO(42, 6, 1, 0.5);

		recipeComponentDAO.createRecipeComponent(RC1);
		recipeComponentDAO.createRecipeComponent(RC2);
		recipeComponentDAO.createRecipeComponent(RC3);
		
		System.out.println("0.2 Amount: "+recipeComponentDAO.getRecipeComponent(41, 4).getAmount());
		System.out.println("2 Amount: "+recipeComponentDAO.getRecipeComponent(41, 5).getAmount());
		System.out.println("1 Amount: "+recipeComponentDAO.getRecipeComponent(42, 6).getAmount());

		IRoleDAO roleDAO = new FakeSerRoleDAO();

		RoleDTO role1 = new RoleDTO(5, "Administator");
		RoleDTO role2 = new RoleDTO(6, "Pharmacist");
		RoleDTO role3 = new RoleDTO(7, "Foreman");
		RoleDTO role4 = new RoleDTO(8, "Operator");

		roleDAO.createRole(role1);
		roleDAO.createRole(role2);
		roleDAO.createRole(role3);
		roleDAO.createRole(role4);
		
		System.out.println("Administator RoleName: "+roleDAO.getRole(5).getRoleName());
		System.out.println("Pharmacist RoleName: "+roleDAO.getRole(6).getRoleName());
		System.out.println("Foreman RoleName: "+roleDAO.getRole(7).getRoleName());
		System.out.println("Operator RoleName: "+roleDAO.getRole(8).getRoleName());
		
		
		
		IUserDAO userDAO = new FakeSerUserDAO();

		UserDTO user1 = new UserDTO(21, "Steve", "STV", "101010-1234", "wally", role4, true);
		UserDTO user2 = new UserDTO(22, "Kurt", "Kru", "101110-1234", "wally2", role1, true);
		UserDTO user3 = new UserDTO(23, "Lis", "LIS", "101110-1234", "wally3", role2, false);

		userDAO.createUser(user1);
		userDAO.createUser(user2);
		userDAO.createUser(user3);
		
		System.out.println("Steve UserName: "+userDAO.getUser(21).getUserName());
		System.out.println("Kurt UserName: "+userDAO.getUser(22).getUserName());
		System.out.println("Lis UserName: "+userDAO.getUser(23).getUserName());
	}

}
