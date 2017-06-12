package testData;

import java.util.ArrayList;
import java.util.List;

import dto.IngredientBatchDTO;
import exceptions.DALException;
import interfaces.IIngredientBatchDAO;

public class FakeIngredientBatchDAO implements IIngredientBatchDAO {
	List<IngredientBatchDTO> fakeIngredientBatchList;
	IngredientBatchDTO IB1;
	IngredientBatchDTO IB2;
	
	public FakeIngredientBatchDAO() {
		fakeIngredientBatchList = new ArrayList<IngredientBatchDTO>();
		IB1 = new IngredientBatchDTO(20,1, 20);
		IB2 = new IngredientBatchDTO(21,2, 50);
		fakeIngredientBatchList.add(IB1);
		fakeIngredientBatchList.add(IB2);
	}
	
	@Override
	public IngredientBatchDTO getIngredientBatch(int ibId) throws DALException {
		for (int i = 0; i < fakeIngredientBatchList.size(); i++) {
			if (fakeIngredientBatchList.get(i).getIngredientBatchID() == ibId) {
				return fakeIngredientBatchList.get(i);
			}
		}
		throw new DALException("No Ingredient Batch has been found with id: " + ibId);
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList() throws DALException {
		return fakeIngredientBatchList;
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList(int ingredientId) throws DALException {
		List<IngredientBatchDTO> returnList = new ArrayList<IngredientBatchDTO>();
		for (int i = 0; i < fakeIngredientBatchList.size(); i++){
			if (ingredientId == fakeIngredientBatchList.get(i).getIngredientID()){
				returnList.add(fakeIngredientBatchList.get(i));
			}
			else 
				throw new DALException("No Ingredient Batches has been found containing ingredient with id: " + ingredientId);
		}
		return returnList;
	}

	@Override
	public void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		fakeIngredientBatchList.add(ingredientBatch);
		
	}

	@Override
	public void updateIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		for (int i = 0; i < fakeIngredientBatchList.size(); i++) {
			if (ingredientBatch.getIngredientBatchID() == fakeIngredientBatchList.get(i).getIngredientBatchID()) {
				fakeIngredientBatchList.remove(i);
				fakeIngredientBatchList.add(ingredientBatch);
			}
		}
		
	}

}
