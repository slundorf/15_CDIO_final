package testDataSer;

import java.util.ArrayList;
import java.util.List;
import dto.IngredientBatchDTO;
import exceptions.DALException;
import interfaces.IIngredientBatchDAO;

public class FakeSerIngredientBatchDAO extends FakeSerDAO<IngredientBatchDTO> implements IIngredientBatchDAO {
	
	public FakeSerIngredientBatchDAO(){
		super("IngredientBatchDB.ser");
	}
	
	
	public FakeSerIngredientBatchDAO(String pathName){
		super(pathName);
	}


	@Override
	public IngredientBatchDTO getIngredientBatch(int ibId) throws DALException {
		loadInfo();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getIngredientBatchID() == ibId) {
				return list.get(i);
			}
		}
		throw new DALException("No Ingredient Batch has been found with id: " + ibId);
	}
	

	@Override
	public List<IngredientBatchDTO> getIngredientBatchList() throws DALException {
		loadInfo();
		return list;
	}


	@Override
	public List<IngredientBatchDTO> getIngredientBatchList(int ingredientId) throws DALException {
		loadInfo();
		List<IngredientBatchDTO> returnList = new ArrayList<IngredientBatchDTO>();
		for (int i = 0; i < list.size(); i++){
			if (ingredientId == list.get(i).getIngredientID()){
				returnList.add(list.get(i));
			}
			else 
				throw new DALException("No Ingredient Batches has been found containing ingredient with id: " + ingredientId);
		}
		return returnList;
	}

	@Override
	public void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		loadInfo();
		list.add(ingredientBatch);
		saveInfo();
	}
	
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
