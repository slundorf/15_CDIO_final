package interfaces;

import java.util.List;

import dto.IngredientDTO;
import exceptions.DALException;

public interface IIngredientDAO {
	/**
	 * 
	 * @param ingredientID
	 * @return ingredient
	 * @throws DALException
	 */
	IngredientDTO getIngredient(int ingredientID) throws DALException;
	/**
	 * 
	 * @return list of ingredients
	 * @throws DALException
	 */
	List<IngredientDTO> getIngredientList() throws DALException;
	/**
	 * 
	 * @param ingredient
	 * @throws DALException
	 */
	void createIngredient(IngredientDTO ingredient) throws DALException;
	/**
	 * 
	 * @param ingredient
	 * @throws DALException
	 */
	void updateIngredient(IngredientDTO ingredient) throws DALException;
}

