package testData;

import java.util.ArrayList;
import java.util.List;

import dto.IngredientDTO;
import exceptions.DALException;
import interfaces.IIngredientDAO;

public class FakeIngredientDAO implements IIngredientDAO {
	List<IngredientDTO> fakeIngredientList;
	IngredientDTO I1;
	IngredientDTO I2;
	IngredientDTO I3;
	
	public FakeIngredientDAO() {
	fakeIngredientList = new ArrayList<IngredientDTO>();
	I1 = new IngredientDTO(1, "Salt", "supplier1");
	I2 = new IngredientDTO(2, "Water", "supplier2");
	I3 = new IngredientDTO(3, "Lemon", "supplier3");
	fakeIngredientList.add(I1);
	fakeIngredientList.add(I2);
	fakeIngredientList.add(I3);
	}

	@Override
	public IngredientDTO getIngredient(int ingredientID) throws DALException {
		for (int i = 0; i < fakeIngredientList.size(); i++) {
			if (fakeIngredientList.get(i).getIngredientID() == ingredientID) {
				return fakeIngredientList.get(i);
			}
		}
		throw new DALException("No ingredient has been found with id: " + ingredientID);
	}

	@Override
	public List<IngredientDTO> getIngredientList() throws DALException {
		return fakeIngredientList;
	}

	@Override
	public void createIngredient(IngredientDTO ingredient) throws DALException {
		fakeIngredientList.add(ingredient);

	}

	@Override
	public void updateIngredient(IngredientDTO ingredient) throws DALException {
		for (int i = 0; i < fakeIngredientList.size(); i++) {
			if (ingredient.getIngredientID() == fakeIngredientList.get(i).getIngredientID()) {
				fakeIngredientList.remove(i);
				fakeIngredientList.add(ingredient);
			}
		}

	}

}
