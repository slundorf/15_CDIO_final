package interfaces;

import java.util.List;

import dto.RecipeComponentDTO;
import exceptions.DALException;

public interface IRecipeComponentDAO {
	
	/**
	 * 
	 * @param receptComponentID
	 * @return recipeComponent
	 * @throws DALException
	 */
	RecipeComponentDTO getRecipeComponent(int recipeComponentID) throws DALException;
	/**
	 * 
	 * @param recipeID
	 * @param ingredientID
	 * @return
	 * @throws DALException
	 */
	RecipeComponentDTO getRecipeComponent(int recipeID, int ingredientID) throws DALException;
	/**
	 * 
	 * @param receptId
	 * @return List of recipecomponents with specified receptID
	 * @throws DALException
	 */
	List<RecipeComponentDTO> getRecipeComponentList(int recipeID) throws DALException;
	/**
	 * 
	 * @return all receptcomponents.
	 * @throws DALException
	 */
	List<RecipeComponentDTO> getRecipeComponentList() throws DALException;
	/**
	 * @param receptkomponent
	 * @throws DALException
	 */
	void createRecipeComponent(RecipeComponentDTO recipeComponent) throws DALException;
	/**
	 * 
	 * @param receptkomponent
	 * @throws DALException
	 */
	void updateRecipeComponent(RecipeComponentDTO recipeComponent) throws DALException;

}
