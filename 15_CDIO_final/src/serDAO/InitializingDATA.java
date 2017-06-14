package serDAO;

import java.util.ArrayList;
import java.util.List;

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

public class InitializingDATA {

	public static void main(String[] args) throws DALException {
		IIngredientBatchDAO ingredientBatchDAO = new SerIngredientBatchDAO();

		IngredientBatchDTO IB1 = new IngredientBatchDTO(20, 1, 25);
		IngredientBatchDTO IB2 = new IngredientBatchDTO(21, 2, 50);
		IngredientBatchDTO IB3 = new IngredientBatchDTO(22, 2, 60);
		IngredientBatchDTO IB4 = new IngredientBatchDTO(23, 3, 10);

		ingredientBatchDAO.createIngredientBatch(IB1);
		ingredientBatchDAO.createIngredientBatch(IB2);
		ingredientBatchDAO.createIngredientBatch(IB3);
		ingredientBatchDAO.createIngredientBatch(IB4);

		IIngredientDAO ingredientDAO = new SerIngredientDAO();

		IngredientDTO I1 = new IngredientDTO(1, "Salt", "supplier1");
		IngredientDTO I2 = new IngredientDTO(2, "Water", "supplier2");
		IngredientDTO I3 = new IngredientDTO(3, "Lemon", "supplier3");
		ingredientDAO.createIngredient(I1);
		ingredientDAO.createIngredient(I2);
		ingredientDAO.createIngredient(I3);

		IProductBatchComponentDAO productBatchComponentDAO = new SerProductBatchComponentDAO();

		// PB1
		ProductBatchComponentDTO PBC1 = new ProductBatchComponentDTO(41, 1);
		ProductBatchComponentDTO PBC2 = new ProductBatchComponentDTO(41, 2);

		// PB2
		ProductBatchComponentDTO PBC3 = new ProductBatchComponentDTO(42, 3);

		// PB3
		ProductBatchComponentDTO PBC4 = new ProductBatchComponentDTO(43, 3);

		productBatchComponentDAO.createProductBatchComponent(PBC1);
		productBatchComponentDAO.createProductBatchComponent(PBC2);
		productBatchComponentDAO.createProductBatchComponent(PBC3);
		productBatchComponentDAO.createProductBatchComponent(PBC4);


		IProductBatchDAO productBatchDAO = new SerProductBatchDAO();
		//PB1
		List<ProductBatchComponentDTO> productBatchComponentList1 = new ArrayList<ProductBatchComponentDTO>();
		productBatchComponentList1.add(PBC1);
		productBatchComponentList1.add(PBC2);
		//PB2
		List<ProductBatchComponentDTO> productBatchComponentList2 = new ArrayList<ProductBatchComponentDTO>();
		productBatchComponentList2.add(PBC3);
		//PB3
		List<ProductBatchComponentDTO> productBatchComponentList3 = new ArrayList<ProductBatchComponentDTO>();
		productBatchComponentList3.add(PBC4);
		
		ProductBatchDTO PB1 = new ProductBatchDTO(41, 31, "090693", "Created", productBatchComponentList1);
		ProductBatchDTO PB2 = new ProductBatchDTO(42, 32, "100693", "Created", productBatchComponentList2);
		ProductBatchDTO PB3 = new ProductBatchDTO(43, 32, "110693", "Created", productBatchComponentList3);
		
		productBatchDAO.createProductBatch(PB1);
		productBatchDAO.createProductBatch(PB2);
		productBatchDAO.createProductBatch(PB3);
		
		
		IRecipeComponentDAO recipeComponentDAO = new SerRecipeComponentDAO();
		
		RecipeComponentDTO RC1 = new RecipeComponentDTO(1, 0.2, 0.1);
		RecipeComponentDTO RC2 = new RecipeComponentDTO(2, 2, 0.1);
		RecipeComponentDTO RC3 = new RecipeComponentDTO(3,1,0.5);
		
		recipeComponentDAO.createRecipeComponent(RC1);
		recipeComponentDAO.createRecipeComponent(RC2);
		recipeComponentDAO.createRecipeComponent(RC3);
		
		
		IRecipeDAO recipeDAO = new SerRecipeDAO();
		
		
		List<RecipeComponentDTO> recipeComponentList1 = new ArrayList<RecipeComponentDTO>();
		List<RecipeComponentDTO> recipeComponentList2 = new ArrayList<RecipeComponentDTO>();

		//R1
		recipeComponentList1.add(RC1);
		recipeComponentList1.add(RC2);
		//R2
		recipeComponentList2.add(RC3);
		
		
		RecipeDTO R1 = new RecipeDTO(31, "SaltWater",recipeComponentList1);
		RecipeDTO R2 = new RecipeDTO(32, "Lemon",recipeComponentList2);
		
		recipeDAO.createRecipe(R1);
		recipeDAO.createRecipe(R2);
		
		
		IRoleDAO roleDAO = new SerRoleDAO();
		
		RoleDTO role1 = new RoleDTO(1, "Administator");
		RoleDTO role2 = new RoleDTO(2, "Pharmacist");
		RoleDTO role3 = new RoleDTO(3, "Foreman");
		RoleDTO role4 = new RoleDTO(4, "Operator");
		
		roleDAO.createRole(role1);
		roleDAO.createRole(role2);
		roleDAO.createRole(role3);
		roleDAO.createRole(role4);
		
		IUserDAO userDAO = new SerUserDAO();
		
		UserDTO user1 = new UserDTO(11, "Steve", "STV", "101010-1234","wally", role4, true);
		UserDTO user2 = new UserDTO(12, "Kurt", "Kru", "101110-1234","wally2", role1, true);
		UserDTO user3 = new UserDTO(13, "Lis", "LIS", "101110-1234","wally3", role2, false);
		
		userDAO.createUser(user1);
		userDAO.createUser(user2);
		userDAO.createUser(user3);
		

	}

}
