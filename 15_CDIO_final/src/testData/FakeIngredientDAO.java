package testData;

import java.util.List;

import dto.IngredientDTO;
import exceptions.DALException;
import interfaces.IIngredientDAO;

public class FakeIngredientDAO implements IIngredientDAO {

	@Override
	public IngredientDTO getIngredient(int ingredientID) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IngredientDTO> getIngredientList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createIngredient(IngredientDTO ingredient) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateIngredient(IngredientDTO ingredient) throws DALException {
		// TODO Auto-generated method stub

	}

}
