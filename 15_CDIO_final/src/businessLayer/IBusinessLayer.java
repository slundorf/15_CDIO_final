package businessLayer;

import java.util.List;

import dto.IngredientBatchDTO;
import dto.IngredientDTO;
import dto.ProductBatchComponentDTO;
import dto.ProductBatchDTO;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;
import dto.UserDTO;
import exceptions.DALException;

public interface IBusinessLayer {
	int login(int userID, String password) throws DALException;
	UserDTO getUser(int userID) throws DALException; 
	List<UserDTO> getUserList() throws DALException; 
	void createUser(UserDTO user) throws DALException; 
	void updateUser(UserDTO user) throws DALException;
	IngredientDTO getIngredient(int ingredientID) throws DALException;
	List<IngredientDTO> getIngredientList() throws DALException;
	void createIngredient(IngredientDTO ing) throws DALException;
	void updateIngredient(IngredientDTO ingredient) throws DALException;
	IngredientBatchDTO getIngredientBatch(int ibId) throws DALException;
	List<IngredientBatchDTO> getIngredientBatchList() throws DALException;
	void createIngredientBatch(IngredientBatchDTO iB) throws DALException;
	void updateIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException;
	ProductBatchDTO getProductBatch(int pbId) throws DALException;
	List<ProductBatchDTO> getProductBatchList() throws DALException;
	void createProductBatch(ProductBatchDTO pb) throws DALException; 
	void updateProductBatch(ProductBatchDTO productbatch) throws DALException;
	ProductBatchComponentDTO getProductBatchComponent(int pbId, int ingrbatchId) throws DALException;
	List<ProductBatchComponentDTO> getProductBatchComponentList(int pbId) throws DALException;
	List<ProductBatchComponentDTO> getProductBatchComponentList() throws DALException;
	void updateProductBatchComponent(ProductBatchComponentDTO productbatchComponent) throws DALException;
	RecipeDTO getRecipe(int recipeID) throws DALException;
	void updateRecipe(RecipeDTO recipe) throws DALException;
	void createRecipe(RecipeDTO recipe) throws DALException;
	void createRecipeComponent(RecipeComponentDTO recipeComponent) throws DALException;
	RecipeComponentDTO getRecipeComponent(int recipeComponentID) throws DALException;
}
