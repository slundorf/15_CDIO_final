package serDAO;

import java.util.List;
import dto.RecipeDTO;
import exceptions.DALException;
import interfaces.IRecipeDAO;

public class SerRecipeDAO extends SerDAO<RecipeDTO> implements IRecipeDAO{ 
	
	public SerRecipeDAO(String pathName){
		super(pathName);
	}
	
	public SerRecipeDAO(){
		super("RecipeDB.ser");
	}
	
	@Override
	public RecipeDTO getRecipe(int recipeID) throws DALException {
		loadInfo();
		for(int i = 0; i < list.size(); i++) {
			if (list.get(i).getRecipeID() == recipeID) {
				return list.get(i);
			}
		}
		throw new DALException("No recipe has been found with id: " + recipeID);
	}

	@Override
	public List<RecipeDTO> getRecipeList() throws DALException {
		loadInfo();
		return list;
	}

	@Override
	public void createRecipe(RecipeDTO recipe) throws DALException {
		loadInfo();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getRecipeID()==recipe.getRecipeID())
				throw new DALException("A recipe with id: "+recipe.getRecipeID()+" already exists");
		}
		list.add(recipe);
		saveInfo();
	}

	@Override
	public void updateRecipe(RecipeDTO recipe) throws DALException {
		loadInfo();
		for(int i = 0; i< list.size(); i++) {
			if(recipe.getRecipeID() == list.get(i).getRecipeID()) {
				list.remove(i);
				list.add(recipe);
			}
		}
		saveInfo();
	}
}
