package interfaces;

import java.util.List;

import dto.IngredientBatchDTO;
import exceptions.DALException;

public interface IIngredientBatchDAO {
	
    IngredientBatchDTO getIngredientBatch(int ibId) throws DALException;
    List<IngredientBatchDTO> getIngredientBatchList() throws DALException;
    List<IngredientBatchDTO> getIngredientBatchList(int ingredientId) throws DALException;
    void createIngredientBatch(IngredientBatchDTO ingredientBatch) throws DALException;
    void updateRaavareBatch(IngredientBatchDTO ingredientBatch) throws DALException;

}
