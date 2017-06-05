package interfaces;

import java.util.List;

import dto.RecipeDTO;
import exceptions.DALException;

public interface IRecipeDAO {
	/**
	 * 
	 * @param recipeID
	 * @return recipe
	 * @throws DALException
	 */
	RecipeDTO getRecipe(int recipeID) throws DALException;
	/**
	 * 
	 * @return list of recipies
	 * @throws DALException
	 */
	List<RecipeDTO> getRecipeList() throws DALException;
	/**
	 * 
	 * @param recipe
	 * @throws DALException
	 */
	void createRecipe(RecipeDTO recipe) throws DALException;
	/**
	 * 
	 * @param recipe
	 * @throws DALException
	 */
	void updateRecipe(RecipeDTO recipe) throws DALException;
}

