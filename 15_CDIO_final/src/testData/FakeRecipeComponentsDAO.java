package testData;

import java.util.ArrayList;
import java.util.List;

import dto.RecipeComponentDTO;
import dto.RecipeDTO;
import exceptions.DALException;
import interfaces.IRecipeComponentDAO;

public class FakeRecipeComponentsDAO implements IRecipeComponentDAO {
	List<RecipeDTO> fakeRecipeList;
	List<RecipeComponentDTO> fakeRecipeComponentList;
	RecipeDTO R1;
	RecipeComponentDTO RC1;
	RecipeComponentDTO RC2;
	RecipeComponentDTO RC3;
	
	public FakeRecipeComponentsDAO() {
		fakeRecipeList = new ArrayList<RecipeDTO>();
		fakeRecipeComponentList = new ArrayList<RecipeComponentDTO>();
		RC1 = new RecipeComponentDTO(51,1, 0.2, 0.1);
		RC2 = new RecipeComponentDTO(52,2, 2, 0.1);
		RC3 = new RecipeComponentDTO(53,2, 1, 0.5);
		fakeRecipeComponentList.add(RC1);
		fakeRecipeComponentList.add(RC2);
		fakeRecipeComponentList.add(RC3);
		R1 = new RecipeDTO(31, "SaltWater",fakeRecipeComponentList);
		fakeRecipeList.add(R1);
		
	}

	@Override
	public RecipeComponentDTO getRecipeComponent(int recipeID, int ingredientID) throws DALException {
		// TODO Auto-generated method stub
		for (int i = 0; i < fakeRecipeList.size(); i++) {
			if (fakeRecipeList.get(i).getRecipeID() == recipeID) {
				for (int j = 0; j < fakeRecipeList.get(i).getComponents().size(); j++) {
					if (fakeRecipeList.get(i).getComponents().get(j).getIngredientID() == ingredientID) {
						return fakeRecipeList.get(i).getComponents().get(j);
					}
				}
			}
		}
		throw new DALException(
				"No recipe component found with recipeID = " + recipeID + " and ingredientID = " + ingredientID);
	}

	@Override
	public List<RecipeComponentDTO> getRecipeComponentList(int recipeID) throws DALException {
		// TODO Auto-generated method stub
		for (int i = 0; i < fakeRecipeList.size(); i++) {
			if (fakeRecipeList.get(i).getRecipeID() == recipeID) {
				return fakeRecipeList.get(i).getComponents();
			}
		}
		throw new DALException("No recipe found with recipeID = " + recipeID);
	}

	@Override
	public List<RecipeComponentDTO> getRecipeComponentList() throws DALException {
		// TODO Auto-generated method stub
		List<RecipeComponentDTO> returnList = new ArrayList<RecipeComponentDTO>();
		for (int i = 0; i < fakeRecipeList.size(); i++) {
			for (int j = 0; j < fakeRecipeList.get(i).getComponents().size(); j++) {
				returnList.add(fakeRecipeList.get(i).getComponents().get(j));
			}
		}
		return returnList;
	}

	@Override
	public void createRecipeComponent(RecipeComponentDTO recipeComponent) throws DALException {
		// TODO Auto-generated method stub
		boolean existed = false;
		for (int i = 0; i < fakeRecipeList.size(); i++) {
			for (int j = 0; j < fakeRecipeList.get(i).getComponents().size(); j++) {
				if (fakeRecipeList.get(i).getComponents().get(j).getRecipeComponentID() == recipeComponent.getRecipeComponentID()) {
					fakeRecipeList.get(i).getComponents().remove(j);
					fakeRecipeList.get(i).addComponent(recipeComponent);
					existed = true;
				}
			}
		}
		if (!existed) {
			throw new DALException("Recipe not found");
		}
		
	}

	@Override
	public void updateRecipeComponent(RecipeComponentDTO recipeComponent) throws DALException {
		// TODO Auto-generated method stub
		boolean existed = false;
		for (int i = 0; i < fakeRecipeList.size(); i++) {
			for (int j = 0; j < fakeRecipeList.get(i).getComponents().size(); j++) {
				if (fakeRecipeList.get(i).getComponents().get(j).getRecipeComponentID() == recipeComponent.getRecipeComponentID()) {
					fakeRecipeList.get(i).getComponents().remove(j);
					fakeRecipeList.get(i).addComponent(recipeComponent);
					existed = true;
				}
			}
		}
		if (!existed) {
			throw new DALException("Recipe not found");
		}	
	}

	@Override
	public RecipeComponentDTO getRecipeComponent(int recipeComponentID) throws DALException {
		for(int i =0;i<fakeRecipeList.size();i++){
			for(int j=0;j<fakeRecipeList.get(i).getComponents().size();j++){
				if(fakeRecipeList.get(i).getComponents().get(j).getRecipeComponentID()==recipeComponentID){
					return fakeRecipeList.get(i).getComponents().get(j);
				}
			}
		}
		throw new DALException("No recipecomponent found with ID = "+recipeComponentID);
	}

}
