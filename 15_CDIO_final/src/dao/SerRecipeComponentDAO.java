package dao;

import java.util.ArrayList;
import java.util.List;
import dto.RecipeComponentDTO;
import dto.RecipeDTO;
import exceptions.DALException;
import interfaces.IRecipeComponentDAO;

public class SerRecipeComponentDAO extends SerDAO<RecipeDTO> implements IRecipeComponentDAO{
	
	public SerRecipeComponentDAO(){
		super("SerFiles/RecipeDB.ser");
	}
	@Override
	public RecipeComponentDTO getRecipeComponent(int recipeID, int ingredientID) throws DALException {
		loadInfo();
		for (int i=0;i<list.size();i++){
			if(list.get(i).getRecipeID()==recipeID){
				for(int j=0;j<list.get(i).getComponents().size();j++){
					if(list.get(i).getComponents().get(j).getIngredientID()==ingredientID){
						return list.get(i).getComponents().get(j);
					}
				}
			}
		}
		throw new DALException("No recipe component found with recipeID = "+recipeID+" and ingredientID = "+ingredientID);
	}

	@Override
	public List<RecipeComponentDTO> getRecipeComponentList(int recipeID) throws DALException {
		loadInfo();
		for(int i=0;i<list.size();i++){
			if(list.get(i).getRecipeID()==recipeID){
				return list.get(i).getComponents();
			}
		}
		throw new DALException("No recipe found with recipeID = "+recipeID);
	}

	@Override
	public List<RecipeComponentDTO> getRecipeComponentList() throws DALException {
		if(list.size()==0)
			throw new DALException("There are no recipe components in the database");
		List<RecipeComponentDTO> returnList= new ArrayList<RecipeComponentDTO>(); 
		for(int i=0;i<list.size();i++){
			for(int j=0;j<list.get(i).getComponents().size();j++){
				returnList.add(list.get(i).getComponents().get(j));
			}
		}
		return returnList;
	}

	@Override
	public void createRecipeComponent(RecipeComponentDTO recipeComponent) throws DALException {
		loadInfo();
		boolean existed =false;
		for(int i=0;i<list.size();i++){
			if(list.get(i).getRecipeID()==recipeComponent.getRecipeID()){
				list.get(i).addComponent(recipeComponent);
				existed=true;
			}
		}
		if(!existed){
			throw new DALException("Recipe not found");
		}
		saveInfo();
	}

	@Override
	public void updateRecipeComponent(RecipeComponentDTO recipeComponent) throws DALException {
		loadInfo();
		boolean existed =false;
		for(int i=0;i<list.size();i++){
			if(list.get(i).getRecipeID()==recipeComponent.getRecipeID()){
				for(int j=0;j<list.get(i).getComponents().size();j++){
					if(list.get(i).getComponents().get(j).getIngredientID()==recipeComponent.getIngredientID()){
						list.get(i).getComponents().remove(j);
						list.get(i).addComponent(recipeComponent);
						existed=true;
					}
				}
			}
		}
		if(!existed){
			throw new DALException("Recipe not found");
		}
		saveInfo();
	}
}