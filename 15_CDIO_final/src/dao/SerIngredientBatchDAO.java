package dao;

import java.util.ArrayList;
import java.util.List;
import dto.IngredientBatchDTO;
import exceptions.DALException;
import interfaces.IIngredientBatchDAO;

public class SerIngredientBatchDAO extends SerDAO<IngredientBatchDTO> implements IIngredientBatchDAO {
	
	public SerIngredientBatchDAO(){
		super("IngredientBatchDB.ser");
	}

	/**
	 * 
	 * @param ibId (ingredient batch id)
	 * @return Ingredient Batch with specified ibId
	 * @throws DALException
	 */
	@Override
	public IngredientBatchDTO getIngredientBatch(int ibId) throws DALException {
		loadInfo();
		if (list.size() == 0)
			throw new DALException("The database is empty.");
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getIngredientBatchID() == ibId) {
				return list.get(i);
			}
		}
		throw new DALException("No Ingredient Batch has been found with id: " + ibId);
	}
	
	/**
	 * 
	 * @return A list of all ingredient batches
	 * @throws DALException
	 */
	@Override
	public List<IngredientBatchDTO> getIngredientBatchList() throws DALException {
		loadInfo();
		if (list.size() == 0)
			throw new DALException("There are no Ingredient batches in the database.");
		return list;
	}

	/**
	 * @param ingredientId 
	 * @return A list of all ingredient batches that contains the ingredientId
	 * @throws DALException
	 */
	@Override
	public List<IngredientBatchDTO> getIngredientBatchList(int ingredientId) throws DALException {
		loadInfo();
		List<IngredientBatchDTO> returnList = new ArrayList<IngredientBatchDTO>();
		if (list.size() == 0)
			throw new DALException("There are no Ingredient batches in the database.");
		for (int i = 0; i < list.size(); i++){
			if (ingredientId == list.get(i).getIngredientID()){
				returnList.add(list.get(i));
			}
			else 
				throw new DALException("No Ingredient Batches has been found containing ingredient with id: " + ingredientId);
		}
		return returnList;
	}
	/**
	 * Creates the Ingredient Batch given as parameter.
	 * @param ingredientBatch
	 * @throws DALException
	 */
	@Override
	public void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		loadInfo();
		list.add(ingredientBatch);
		saveInfo();
	}
	
	/**
	 * Updates the ingredient batch, given as parameter.
	 * @param ingredientBatch
	 * @throws DALException
	 */
	@Override
	public void updateIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		loadInfo();
		for (int i = 0; i < list.size(); i++) {
			if (ingredientBatch.getIngredientBatchID() == list.get(i).getIngredientBatchID()) {
				list.remove(i);
				list.add(ingredientBatch);
			}
		}
		saveInfo();
	}
}
