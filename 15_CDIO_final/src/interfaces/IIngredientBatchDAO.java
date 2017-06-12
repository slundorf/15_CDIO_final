package interfaces;

import java.util.List;
import java.util.Map;

import dto.IngredientBatchDTO;
import exceptions.DALException;

public interface IIngredientBatchDAO {
	/**
	 * 
	 * @param ibId (ingredient batch id)
	 * @return Ingredient Batch with specified ibId
	 * @throws DALException
	 */
    IngredientBatchDTO getIngredientBatch(int ibId) throws DALException;
	/**
	 * 
	 * @return A list of all ingredient batches
	 * @throws DALException
	 */
    Map<Integer, IngredientBatchDTO> getIngredientBatchList() throws DALException;
	/**
	 * @param ingredientId 
	 * @return A list of all ingredient batches that contains the ingredientId
	 * @throws DALException
	 */
    List<IngredientBatchDTO> getIngredientBatchList(int ingredientId) throws DALException;
	/**
	 * Creates the Ingredient Batch given as parameter.
	 * @param ingredientBatch
	 * @throws DALException
	 */
    void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException;
	
	/**
	 * Updates the ingredient batch, given as parameter.
	 * @param ingredientBatch
	 * @throws DALException
	 */
    void updateIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException;

}
