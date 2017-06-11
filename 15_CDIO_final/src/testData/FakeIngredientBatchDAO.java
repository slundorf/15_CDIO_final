package testData;

import java.util.List;

import dto.IngredientBatchDTO;
import exceptions.DALException;
import interfaces.IIngredientBatchDAO;

public class FakeIngredientBatchDAO implements IIngredientBatchDAO {

	@Override
	public IngredientBatchDTO getIngredientBatch(int ibId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList(int ingredientId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		// TODO Auto-generated method stub
		
	}

}
