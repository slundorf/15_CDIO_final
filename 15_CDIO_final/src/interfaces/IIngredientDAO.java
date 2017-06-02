package interfaces;

import java.util.List;

import dto.IngredientDTO;

public interface IIngredientDAO {

	    IngredientDTO getIngredient(int ingredientID) throws DALException;
	    List<IngredientDTO> getIngredientList() throws DALException;
	    void createIngredient(IngredientDTO ingredient) throws DALException;
	    void updateIngredient(IngredientDTO ingredient) throws DALException;

}

