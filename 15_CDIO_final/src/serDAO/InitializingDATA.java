package serDAO;

import java.io.IOException;
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

	public static void main(String[] args) throws DALException, IOException {

		IIngredientDAO ingredientDAO = new SerIngredientDAO();

		IngredientDTO I1 = new IngredientDTO(101, "Salt", "supplier1");
		IngredientDTO I2 = new IngredientDTO(102, "Water", "supplier2");
		IngredientDTO I3 = new IngredientDTO(103, "Lemon", "supplier3");
		ingredientDAO.createIngredient(I1);
		ingredientDAO.createIngredient(I2);
		ingredientDAO.createIngredient(I3);

		IIngredientBatchDAO ingredientBatchDAO = new SerIngredientBatchDAO();

		IngredientBatchDTO IB1 = new IngredientBatchDTO(200, 101, 25);
		IngredientBatchDTO IB2 = new IngredientBatchDTO(201, 102, 50);
		IngredientBatchDTO IB3 = new IngredientBatchDTO(202, 102, 60);
		IngredientBatchDTO IB4 = new IngredientBatchDTO(203, 103, 10);

		ingredientBatchDAO.createIngredientBatch(IB1);
		ingredientBatchDAO.createIngredientBatch(IB2);
		ingredientBatchDAO.createIngredientBatch(IB3);
		ingredientBatchDAO.createIngredientBatch(IB4);
		
		IRecipeDAO recipeDAO = new SerRecipeDAO();
		RecipeDTO R1 = new RecipeDTO(301, "SaltWater");
		RecipeDTO R2 = new RecipeDTO(302, "Lemon");
		recipeDAO.createRecipe(R1);
		recipeDAO.createRecipe(R2);
		
		IRecipeComponentDAO recipeComponentDAO = new SerRecipeComponentDAO();
		RecipeComponentDTO RC1 = new RecipeComponentDTO(301,101, 0.2, 0.1);
		RecipeComponentDTO RC2 = new RecipeComponentDTO(301,102, 2, 0.1);
		RecipeComponentDTO RC3 = new RecipeComponentDTO(302,103,1,0.5);
		recipeComponentDAO.createRecipeComponent(RC1);
		recipeComponentDAO.createRecipeComponent(RC2);
		recipeComponentDAO.createRecipeComponent(RC3);
		
		
		IProductBatchDAO productBatchDAO = new SerProductBatchDAO();
		
		ProductBatchDTO PB1 = new ProductBatchDTO(401, 301, "090693", "Created");
		ProductBatchDTO PB2 = new ProductBatchDTO(402, 302, "100693", "Created");
		ProductBatchDTO PB3 = new ProductBatchDTO(403, 302, "110693", "Created");
		
		productBatchDAO.createProductBatch(PB1);
		productBatchDAO.createProductBatch(PB2);
		productBatchDAO.createProductBatch(PB3);
		
		IProductBatchComponentDAO productBatchComponentDAO = new SerProductBatchComponentDAO();

		// PB1
		ProductBatchComponentDTO PBC1 = new ProductBatchComponentDTO(401, 101);
		ProductBatchComponentDTO PBC2 = new ProductBatchComponentDTO(401, 102);
		// PB2
		ProductBatchComponentDTO PBC3 = new ProductBatchComponentDTO(402, 103);
		// PB3
		ProductBatchComponentDTO PBC4 = new ProductBatchComponentDTO(403, 103);
		productBatchComponentDAO.createProductBatchComponent(PBC1);
		productBatchComponentDAO.createProductBatchComponent(PBC2);
		productBatchComponentDAO.createProductBatchComponent(PBC3);
		productBatchComponentDAO.createProductBatchComponent(PBC4);

		IRoleDAO roleDAO = new SerRoleDAO();
		RoleDTO role1 = new RoleDTO(1, "Administrator");
		RoleDTO role2 = new RoleDTO(2, "Pharmacist");
		RoleDTO role3 = new RoleDTO(3, "Foreman");
		RoleDTO role4 = new RoleDTO(4, "Operator");		
		roleDAO.createRole(role1);
		roleDAO.createRole(role2);
		roleDAO.createRole(role3);
		roleDAO.createRole(role4);
		
		IUserDAO userDAO = new SerUserDAO();
		UserDTO user1 = new UserDTO(11, "Steve", "STV", "101011-1234","e6+Easdasd1", role4, true);
		UserDTO user2 = new UserDTO(12, "Kurt", "Kru", "101112-1234","e6+Easdasd2", role1, true);
		UserDTO user3 = new UserDTO(13, "Lis", "LIS", "101113-1234","e6+Easdasd3", role2, false);
		userDAO.createUser(user1);
		userDAO.createUser(user2);
		userDAO.createUser(user3);
	}
}
