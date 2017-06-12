package serDAO;

import java.util.List;
import java.util.Map;

import dto.IngredientBatchDTO;
import exceptions.DALException;
import interfaces.IIngredientBatchDAO;

public class SerIngredientBatchDAO extends SerDAO<Integer,IngredientBatchDTO> implements IIngredientBatchDAO {
	
	public SerIngredientBatchDAO(){
		super("IngredientBatchDB.ser");
	}
	
	public SerIngredientBatchDAO(String pathName){
		super(pathName);
	}


	@Override
	public IngredientBatchDTO getIngredientBatch(int ibId)  {
		loadInfo();
		return list.get(ibId);
		
	}
	

	@Override
	public	Map<Integer,IngredientBatchDTO> getIngredientBatchList() throws DALException {
		loadInfo();
//		if (list.size() == 0)
//			throw new DALException("There are no Ingredient batches in the database.");
		return list;
	}
	
// Forstå ikke den her!!
//	@Override
//	public List<IngredientBatchDTO> getIngredientBatchList(int ingredientId) throws DALException {
//		loadInfo();
//		List<IngredientBatchDTO> returnList = new ArrayList<IngredientBatchDTO>();
//		for (int i = 0; i < list.size(); i++){
//			if (ingredientId == list.get(i).getIngredientID()){
//				returnList.add(list.get(i));
//			}
//			else 
//				throw new DALException("No Ingredient Batches has been found containing ingredient with id: " + ingredientId);
//		}
//		return returnList;
//	}
	@Override
	public List<IngredientBatchDTO> getIngredientBatchList(int ingredientId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		loadInfo();
		list.put(ingredientBatch.getIngredientBatchID(),	ingredientBatch);
		saveInfo();
	}
	
	@Override
	public void updateIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException {
		loadInfo();
		Integer id = ingredientBatch.getIngredientBatchID();
		if(list.get(id)!=null){
			list.remove(id);
			list.put(id, ingredientBatch);
		}else{
			throw new DALException("IngredientBatchDTO does not exist");
		}
		saveInfo();
	}

	
}
