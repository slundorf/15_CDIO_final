package interfaces;

import java.util.List;

import dto.RecipeComponentDTO;
import exceptions.DALException;

public interface IRecipeComponentDAO {
	
	/**
	 * 
	 * @param receptId
	 * @param raavareId
	 * @return recipeComponent
	 * @throws DALException
	 */
	RecipeComponentDTO getRecipeComponent(int receptId, int raavareId) throws DALException;
	/**
	 * 
	 * @param receptId
	 * @return List of recipecomponents with specified receptID
	 * @throws DALException
	 */
	List<RecipeComponentDTO> getRecipeComponentList(int receptId) throws DALException;
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
	void createRecipeComponent(RecipeComponentDTO receptkomponent) throws DALException;
	/**
	 * 
	 * @param receptkomponent
	 * @throws DALException
	 */
	void updateRecipeComponent(RecipeComponentDTO receptkomponent) throws DALException;

}
