package dao;

import java.util.List;
import dto.IngredientDTO;
import exceptions.DALException;
import interfaces.IIngredientDAO;

public class SerIngredientDAO extends SerDAO<IngredientDTO> implements IIngredientDAO {
	
	
	
	public SerIngredientDAO(){
		super("IngredientDB.ser");
	}
	@Override
	public IngredientDTO getIngredient(int ingredientID) throws DALException {
			loadInfo();
			if (list.size() == 0)
				throw new DALException("The database is empty.");
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getIngredientID() == ingredientID) {
					return list.get(i);
				}
			}
			throw new DALException("No ingredient has been found with id: " + ingredientID);
		}

	@Override
	public List<IngredientDTO> getIngredientList() throws DALException {
		loadInfo();
		if (list.size() == 0)
			throw new DALException("There are no ingredients in the database.");
		return list;
	}
	
	@Override
	public void createIngredient(IngredientDTO ingredient) throws DALException {
		loadInfo();
		list.add(ingredient);
		saveInfo();
	}

	@Override
	public void updateIngredient(IngredientDTO ingredient) throws DALException {
		loadInfo();
		for (int i = 0; i < list.size(); i++) {
			if (ingredient.getIngredientID() == list.get(i).getIngredientID()) {
				list.remove(i);
				list.add(ingredient);
			}
		}
		saveInfo();
	}
}
