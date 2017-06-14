/**
 * 
 */
package testData;

import java.util.ArrayList;
import java.util.List;

import dto.RecipeComponentDTO;
import dto.RecipeDTO;
import exceptions.DALException;
import interfaces.IRecipeDAO;

/**
 * @author s136335
 *
 */
public class FakeRecipeDAO implements IRecipeDAO {
	
	List<RecipeDTO> fakeRecipeList;
	List<RecipeComponentDTO> fakeRecipeComponentList;
	RecipeDTO R1;
	RecipeComponentDTO RC1;
	RecipeComponentDTO RC2;
	
	public FakeRecipeDAO() {
		fakeRecipeList = new ArrayList<RecipeDTO>();
		fakeRecipeComponentList = new ArrayList<RecipeComponentDTO>();
		RC1 = new RecipeComponentDTO(51,1, 0.2, 0.1);
		RC2 = new RecipeComponentDTO(52,2, 2, 0.1);
		fakeRecipeComponentList.add(RC1);
		fakeRecipeComponentList.add(RC2);
		R1 = new RecipeDTO(31, "SaltWater",fakeRecipeComponentList);
		fakeRecipeList.add(R1);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see interfaces.IRecipeDAO#getRecipe(int)
	 */
	@Override
	public RecipeDTO getRecipe(int recipeID) throws DALException {
		for(int i = 0; i < fakeRecipeList.size(); i++) {
			if (fakeRecipeList.get(i).getRecipeID() == recipeID) {
				return fakeRecipeList.get(i);
			}
		}
		throw new DALException("No recipe has been found with id: " + recipeID);
	}

	/* (non-Javadoc)
	 * @see interfaces.IRecipeDAO#getRecipeList()
	 */
	@Override
	public List<RecipeDTO> getRecipeList() throws DALException {
		return fakeRecipeList;
	}

	/* (non-Javadoc)
	 * @see interfaces.IRecipeDAO#createRecipe(dto.RecipeDTO)
	 */
	@Override
	public void createRecipe(RecipeDTO recipe) throws DALException {
		for(int i=0;i<fakeRecipeList.size();i++){
			if(fakeRecipeList.get(i).getRecipeID()==recipe.getRecipeID())
				throw new DALException("A recipe with id: "+recipe.getRecipeID()+" already exists");
		}
		fakeRecipeList.add(recipe);
	}

	/* (non-Javadoc)
	 * @see interfaces.IRecipeDAO#updateRecipe(dto.RecipeDTO)
	 */
	@Override
	public void updateRecipe(RecipeDTO recipe) throws DALException {
		for(int i = 0; i< fakeRecipeList.size(); i++) {
			if(recipe.getRecipeID() == fakeRecipeList.get(i).getRecipeID()) {
				fakeRecipeList.remove(i);
				fakeRecipeList.add(recipe);
			}
		}
	}

}
