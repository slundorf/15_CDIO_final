package testData;

import java.util.ArrayList;
import java.util.List;

import dto.IngredientBatchDTO;
import exceptions.DALException;
import interfaces.IIngredientBatchDAO;

public class FakeIngredientBatchDAO implements IIngredientBatchDAO {
	List<IngredientBatchDTO> fakeIngredientBatchDTO;
	IngredientBatchDTO IB1;
	IngredientBatchDTO IB2;
	
	public FakeIngredientBatchDAO() {
		fakeIngredientBatchDTO = new ArrayList<IngredientBatchDTO>();
		IB1 = new IngredientBatchDTO(20,"ingredientBatchName1",1, 50, "supplier1");
		IB2 = new IngredientBatchDTO(21,"ingredientBatchName2",2, 25, "supplier2");
	}
	
	@Override
	public IngredientBatchDTO getIngredientBatch(int ibId) throws DALException {
		for (int i = 0; i < fakeIngredientBatchDTO.size(); i++) {
			if (fakeIngredientBatchDTO.get(i).getIngredientBatchID() == ibId) {
				return fakeIngredientBatchDTO.get(i);
			}
		}
		throw new DALException("No Ingredient Batch has been found with id: " + ibId);
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList() throws DALException {
		return fakeIngredientBatchDTO;
	}

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList(int ingredientId) throws DALException {
		List<IngredientBatchDTO> returnList = new ArrayList<IngredientBatchDTO>();
		for (int i = 0; i < fakeIngredientBatchDTO.size(); i++){
			if (ingredientId == fakeIngredientBatchDTO.get(i).getIngredientID()){
				returnList.add(fakeIngredientBatchDTO.get(i));
			}
			else 
				throw new DALException("No Ingredient Batches has been found containing ingredient with id: " + ingredientId);
		}
		return returnList;
	}

	@Override
	public void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		fakeIngredientBatchDTO.add(ingredientBatch);
		
	}

	@Override
	public void updateIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		for (int i = 0; i < fakeIngredientBatchDTO.size(); i++) {
			if (ingredientBatch.getIngredientBatchID() == fakeIngredientBatchDTO.get(i).getIngredientBatchID()) {
				fakeIngredientBatchDTO.remove(i);
				fakeIngredientBatchDTO.add(ingredientBatch);
			}
		}
		
	}

}
