package dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecipeDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2838706327250754989L;
	private int recipeID;
	private String recipeName;
	private List<RecipeComponentDTO> components;
	
	public RecipeDTO(){}
	public RecipeDTO(int recipeID, String recipeName, List<RecipeComponentDTO> components){
		this.setRecipeID(recipeID);
		this.setRecipeName(recipeName);
		this.setComponents(components);
		
	}
	/**
	 * @return the recipeID
	 */
	public int getRecipeID() {
		return recipeID;
	}
	/**
	 * @param recipeID the recipeID to set
	 */
	public void setRecipeID(int recipeID) {
		this.recipeID = recipeID;
	}
	/**
	 * @return the recipeName
	 */
	public String getRecipeName() {
		return recipeName;
	}
	/**
	 * @param recipeName the recipeName to set
	 */
	public void setRecipeName(String recipeName) {
		this.recipeName = recipeName;
	}
	/**
	 * @return the components
	 */
	public List<RecipeComponentDTO> getComponents() {
		return components;
	}
	/**
	 * @param components the components to set
	 */
	public void setComponents(List<RecipeComponentDTO> components) {
		this.components = components;
	}
	/**
	 * 
	 * @param component adds a component to the current list of components
	 */
	public void addComponent(RecipeComponentDTO component){
		components.add(component);
	}
}