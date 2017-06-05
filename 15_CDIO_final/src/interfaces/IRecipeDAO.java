package interfaces;

import java.util.List;

import dto.RecipeDTO;
import exceptions.DALException;

public interface IRecipeDAO {
	RecipeDTO getRecipe(int recipeID) throws DALException;
	List<RecipeDTO> getRecipeList() throws DALException;
	void createRecipe(RecipeDTO Recipe) throws DALException;
	void updateRecipe(RecipeDTO Recipe) throws DALException;
}

